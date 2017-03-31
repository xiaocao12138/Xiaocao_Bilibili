package com.myproject.bilibili.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.myproject.bilibili.R;
import com.myproject.bilibili.download.listener.ResponseListener;
import com.myproject.bilibili.download.utils.RetrofitUtils;
import com.myproject.bilibili.download.view.MyProgressBar;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DownLoadingActivity extends AppCompatActivity {

    @BindView(R.id.tv_seekbar)
    TextView tvSeekbar;
    @BindView(R.id.seekbar)
    SeekBar seekbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private int maxDownloadNubmer; //最大同时下载数量
    private int downloadNumber; //当前下载数量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_loading);
        ButterKnife.bind(this);

        recyclerview.setAdapter(new Adapter());
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeekbar.setText("同时下载数量  " + (progress + 1) + "  ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(DownLoadingActivity.this).inflate(R.layout.item_download, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setData();
        }

        @Override
        public int getItemCount() {
            return 10;
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.progresss)
        MyProgressBar progresss;
        @BindView(R.id.tv_progress)
        TextView tvProgress;
        @BindView(R.id.bt_download)
        Button btDownload;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
            btDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //seekbar 拖动范围0~4 +1相当于 同时下载数量范围为1~5
                    if (maxDownloadNubmer == 0) { //max=0 表示第一次下载赋值最大下载数量
                        maxDownloadNubmer = seekbar.getProgress() + 1;
                        Log.e("TAG", "ViewHolder onClick()" + maxDownloadNubmer);
                    }
                    if (downloadNumber >= maxDownloadNubmer) {
                        Toast.makeText(DownLoadingActivity.this, "同时下载数量过多 无法继续下载", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    downloadNumber++;
                    btDownload.setText("下载中!");
                    btDownload.setEnabled(false);
                    RetrofitUtils.getInstance().download(new File(getExternalFilesDir(null), getLayoutPosition() + ".apk"), new ResponseListener() {
                        @Override
                        public void onProgress(final long progress, final long total, boolean done) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    long l = progress * 100 / total;
                                    progresss.setProgress((int) l);

                                    String pro = (int) progress * 100 / (int) total + "%";
                                    String p = RetrofitUtils.formetFileSize(progress);
                                    String t = RetrofitUtils.formetFileSize(total);
                                    tvProgress.setText(p + " / " + t + "\t" + pro);
                                }
                            });
                        }

                        @Override
                        public void onResponse() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    downloadNumber--;
                                    btDownload.setText("下载完成!");
                                }
                            });
                        }

                        @Override
                        public void onFailure(final String error) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    downloadNumber--;
                                    btDownload.setText("下载出错!");
                                    btDownload.setEnabled(true);
                                    Log.e("TAG", "DownloadActivity onFailure()" + error);
                                }
                            });

                        }
                    });
                }
            });

        }
    }
}
