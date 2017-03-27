package com.myproject.bilibili.model.found.bean;

import java.util.List;

/**
 * Created by chen on 2017/3/21 16:33.
 * 作用:XXXX
 */

public class HuaTiBean {

    /**
     * code : 0
     * list : [{"title":"频道精选 生活区 No.47","cover":"//activity.hdslb.com/blackboard/cover/895rr6w2o9.jpg","link":"http://www.bilibili.com/blackboard/activity-SkRDIXpol.html"},{"title":"频道精选 动画区 No.49","cover":"http://activity.hdslb.com/blackboard/cover/905v1258mp.jpg","link":"http://www.bilibili.com/blackboard/activity-BkZnOa9og.html"},{"title":"频道精选 科技区 No.49","cover":"http://activity.hdslb.com/blackboard/cover/m6lpl44owy.jpg","link":"http://www.bilibili.com/blackboard/activity-SyjENEtjl.html"},{"title":"鬼畜区 冷门推荐 No.28","cover":"http://activity.hdslb.com/blackboard/cover/m6lp9086qj.jpg","link":"http://www.bilibili.com/blackboard/kichiku-h528.html"},{"title":"【资讯档】番剧一周资讯Pick Up！","cover":"","link":"http://www.bilibili.com/blackboard/activity-Hkwjcpdje.html"},{"title":"频道精选 音乐区 No.49","cover":"http://activity.hdslb.com/blackboard/cover/495pr59804.jpg","link":"http://www.bilibili.com/blackboard/activity-HJKJ0ldig.html"},{"title":"频道精选 舞蹈区 No.49","cover":"http://activity.hdslb.com/blackboard/cover/p6ovrn5z3q.png","link":"http://www.bilibili.com/blackboard/activity-S1Ulz0Dox.html"},{"title":"周末放映室 第132期","cover":"http://activity.hdslb.com/blackboard/cover/l6konp89yz.jpg","link":"http://www.bilibili.com/blackboard/activity-Bk0hunwsl.html"},{"title":"打破\u201c三月不减肥五月徒伤悲\u201d魔咒","cover":"http://activity.hdslb.com/blackboard/cover/395nj8j845.jpg","link":"http://www.bilibili.com/blackboard/activity-SJH6zYvsx.html"},{"title":"你可能看了假动画","cover":"http://activity.hdslb.com/blackboard/cover/k7jpz82705.jpg","link":"http://www.bilibili.com/blackboard/activity-rkYr5oIjg.html"},{"title":"时尚区频道精选 NO.21","cover":"http://activity.hdslb.com/blackboard/cover/m6lr6yopyj.jpg","link":"http://www.bilibili.com/blackboard/activity-B1RVLvIix.html"},{"title":"频道精选 广告区 No.16","cover":"http://activity.hdslb.com/blackboard/cover/k7jp0285k7.jpg","link":"http://www.bilibili.com/blackboard/activity-cm16h5.html"},{"title":"游戏万华镜 第六期 兵器物语（下） \u2014\u2014那一弯致命的优雅","cover":"http://activity.hdslb.com/blackboard/cover/w41mmwz9zl.jpg","link":"http://www.bilibili.com/blackboard/activity-HJ70jl4ix.html"},{"title":"频道精选 鬼畜区 No.47","cover":"http://activity.hdslb.com/blackboard/cover/p6oz8jn99q.jpg","link":"http://www.bilibili.com/blackboard/kichiku-h5-047.html"},{"title":"频道精选 生活区 No.46","cover":"http://activity.hdslb.com/blackboard/cover/o6nkxjj4ly.jpg","link":"http://www.bilibili.com/blackboard/activity-S1XQLxeig.html"},{"title":"频道精选 科技区 No.48","cover":"http://activity.hdslb.com/blackboard/cover/6958z67ro3.jpg","link":"http://www.bilibili.com/blackboard/activity-r14oCyljx.html"},{"title":"频道精选 游戏区 No.50","cover":"http://activity.hdslb.com/blackboard/cover/m6l2y2rmyp.jpg","link":"http://www.bilibili.com/blackboard/activity-BkVEOkxox.html"},{"title":"鬼畜区 冷门推荐 No.27","cover":"http://activity.hdslb.com/blackboard/cover/8956z9l4zj.jpg","link":"http://www.bilibili.com/blackboard/kichiku-h527.html"},{"title":"频道精选 舞蹈区 No.48","cover":"http://activity.hdslb.com/blackboard/cover/o6n8z85xjz.png","link":"http://www.bilibili.com/blackboard/activity-ryfH120cl.html"},{"title":"周末放映室 第131期","cover":"http://activity.hdslb.com/blackboard/cover/395zm624l5.jpg","link":"http://www.bilibili.com/blackboard/activity-HkL_qYRql.html"}]
     * total : 457
     * pages : 23
     */

    private int code;
    private int total;
    private int pages;
    private List<ListBean> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * title : 频道精选 生活区 No.47
         * cover : //activity.hdslb.com/blackboard/cover/895rr6w2o9.jpg
         * link : http://www.bilibili.com/blackboard/activity-SkRDIXpol.html
         */

        private String title;
        private String cover;
        private String link;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
