package com.myproject.mymodel.shopping.utils;

/**
 * Created by chen on 2017/2/27 14:13.
 * 作用:购物车操作类
 */

public class CartStorage {

//    public static final String JSON_CART = "json_cart";
//    private final Context context;
//    private static CartStorage instance;
//    private final GoodsDao dao;
//    //SparseArray替代HashMap
//
//    private CartStorage(Context context) {
//        this.context = context;
//        dao = new GoodsDao(context);
//        //从本地获取数据
//        listToSparseArray();
//    }
//
//    /**
//     *  把List的数据转换成SparseArray
//     */
//    private void listToSparseArray() {
//        List<GoodsBean> goodsBeen = getAllData();
//        for (int i=0 ; i<goodsBeen.size() ; i++){
//            GoodsBean goodsBean = goodsBeen.get(i);
//            dao.AddGoods(goodsBean);
//        }
//    }
//
//    /**
//     *  得到所有数据
//     * @return
//     */
//    public List<GoodsBean> getAllData() {
//        return getLocalData();
//    }
//
//    /**
//     *  得到本地缓存的数据
//     * @return
//     */
//    private List<GoodsBean> getLocalData() {
//        List<GoodsBean> goodsBeen = new ArrayList<>();
//        //从本地获取数据
////        String json = CacheUtils.getString(context, JSON_CART);
////        if (!TextUtils.isEmpty(json)){
////            goodsBeen = new Gson().fromJson(json, new TypeToken<List<GoodsBean>>() {
////                        }.getType());
////        }
//        //把json数据解析成List数据
//        return goodsBeen;
//    }
//
//    /**
//     *  懒汉模式
//     * @param context
//     * @return
//     */
//    public static CartStorage getInstance(Context context){
//        if (instance == null){
//            synchronized (CartStorage.class){
//                if (instance == null){
//                    instance = new CartStorage(context);
//                }
//            }
//        }
//        return instance;
//    }
//
//    /**
//     * 添加数据
//     * @param goodsBean
//     */
//    public void addData(GoodsBean goodsBean){
//
//        GoodsBean tempGoodsbean = dao.getGoodsBySkuID(goodsBean.getSkuid());
//
//        if (tempGoodsbean != null){
//            tempGoodsbean.setNumber(tempGoodsbean.getNumber() + goodsBean.getNumber());
//        }else {
//            tempGoodsbean = goodsBean;
//        }
//
//        dao.AddGoods(tempGoodsbean);
//
//    }
//
//    /**
//     * 删除数据
//     * @param goodsBean
//     */
//    public void deleteData(GoodsBean goodsBean){
//        //删除数据
//        dao.deleteGoods(goodsBean);
//
//    }
//
//    /**
//     * 更新数据
//     * @param goodsBean
//     */
//    public void updataData(GoodsBean goodsBean){
//        //更新数据
////        dao.updataGoods(goodsBean.getSkuid(), goodsBean);
//
//    }
//
//    /**
//     * 保存到本地
//     */
//   /* private void saveLocal() {
//        //1.把sparseArray转成List
//        List<GoodsBean> goodsBeanList = sparseArrayToList();
//        //2.使用Gson把List转json的String类型数据
//        String saveJson = new Gson().toJson(goodsBeanList);
////        //3.使用CacheUtils缓存数据
//        CacheUtils.setString(context, JSON_CART , saveJson);
//    }*/
//
//    /**
//     *  把sparseArray转成List
//     * @return
//     */
//    /*private List<GoodsBean> sparseArrayToList() {
//        List<GoodsBean> list = new ArrayList<>();
//
//        for (int i=0 ; i<sparseArray.size() ; i++){
//            GoodsBean goodsBean = sparseArray.valueAt(i);
//            list.add(goodsBean);
//        }
//
//        return list;
//    }
//*/
//    /**
//     * 判断在购物车是否存在
//     * @param skuid
//     * @return
//     */
//    public GoodsBean findDele(int skuid) {
//        return dao.getGoodsBySkuID(skuid);
//    }
}
