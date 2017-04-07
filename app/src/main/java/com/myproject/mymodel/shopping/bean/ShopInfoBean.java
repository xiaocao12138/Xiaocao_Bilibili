package com.myproject.mymodel.shopping.bean;

import java.util.List;

/**
 * Created by chen on 2017/3/28 23:06.
 * 作用:XXXX
 */

public class ShopInfoBean {

    /**
     * result : {"currSku":{"skuId":83,"skuName":"bilibili周边 魔性小电视长条抱枕 毛绒公仔90CM","oldPrice":299,"vipPlusPrice":179,"salePrice":199,"attribute":[{"id":821,"skuId":83,"name":"尺寸","value":"90CM"}],"skuInventory":56},"skuList":[{"skuId":83,"skuName":"bilibili周边 魔性小电视长条抱枕 毛绒公仔90CM","oldPrice":299,"vipPlusPrice":179,"salePrice":199,"attribute":[{"id":821,"skuId":83,"name":"尺寸","value":"90CM"}],"skuInventory":56}],"productRelate":[{"bfileLink":"http://i0.hdslb.com/bfs/travel/8bf24a1f803a439421d8101691b167e9f40b56ef.jpg","remark1":""},{"bfileLink":"http://i0.hdslb.com/bfs/travel/f83a99189efebaea815ecc9efca51a4151f9bcdb.jpg","remark1":""},{"bfileLink":"http://i0.hdslb.com/bfs/travel/d332dd383b10e8495bc50a4c2a8cd347eec6402c.jpg","remark1":""},{"bfileLink":"http://i0.hdslb.com/bfs/travel/2a43517aebfa50b73b3932f5e012689705576435.jpg","remark1":""}],"htmlDescription":"<img src=\"http://i0.hdslb.com/bfs/travel/d2e0e208491ac1cba135bbd20bdecd7b2b65a7e0.jpg\" alt=\"\" /><img src=\"http://i0.hdslb.com/bfs/travel/6c6cfc848779024bb8d29ef4f7fe178ef8c84085.jpg\" alt=\"\" /><img src=\"http://i0.hdslb.com/bfs/travel/e995a93716b0e618fd343acab3f58e9f7c37fcda.jpg\" alt=\"\" /><img src=\"http://i0.hdslb.com/bfs/travel/130d1fb1fce60b7997be8411bfc68b24b2c88e5c.jpg\" alt=\"\" />","attribute":"尺寸","imgUrl":"http://i0.hdslb.com/bfs/travel/8bf24a1f803a439421d8101691b167e9f40b56ef.jpg"}
     * code : 0
     * message : success
     */

    private ResultBean result;
    private int code;
    private String message;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ResultBean {
        /**
         * currSku : {"skuId":83,"skuName":"bilibili周边 魔性小电视长条抱枕 毛绒公仔90CM","oldPrice":299,"vipPlusPrice":179,"salePrice":199,"attribute":[{"id":821,"skuId":83,"name":"尺寸","value":"90CM"}],"skuInventory":56}
         * skuList : [{"skuId":83,"skuName":"bilibili周边 魔性小电视长条抱枕 毛绒公仔90CM","oldPrice":299,"vipPlusPrice":179,"salePrice":199,"attribute":[{"id":821,"skuId":83,"name":"尺寸","value":"90CM"}],"skuInventory":56}]
         * productRelate : [{"bfileLink":"http://i0.hdslb.com/bfs/travel/8bf24a1f803a439421d8101691b167e9f40b56ef.jpg","remark1":""},{"bfileLink":"http://i0.hdslb.com/bfs/travel/f83a99189efebaea815ecc9efca51a4151f9bcdb.jpg","remark1":""},{"bfileLink":"http://i0.hdslb.com/bfs/travel/d332dd383b10e8495bc50a4c2a8cd347eec6402c.jpg","remark1":""},{"bfileLink":"http://i0.hdslb.com/bfs/travel/2a43517aebfa50b73b3932f5e012689705576435.jpg","remark1":""}]
         * htmlDescription : <img src="http://i0.hdslb.com/bfs/travel/d2e0e208491ac1cba135bbd20bdecd7b2b65a7e0.jpg" alt="" /><img src="http://i0.hdslb.com/bfs/travel/6c6cfc848779024bb8d29ef4f7fe178ef8c84085.jpg" alt="" /><img src="http://i0.hdslb.com/bfs/travel/e995a93716b0e618fd343acab3f58e9f7c37fcda.jpg" alt="" /><img src="http://i0.hdslb.com/bfs/travel/130d1fb1fce60b7997be8411bfc68b24b2c88e5c.jpg" alt="" />
         * attribute : 尺寸
         * imgUrl : http://i0.hdslb.com/bfs/travel/8bf24a1f803a439421d8101691b167e9f40b56ef.jpg
         */

        private CurrSkuBean currSku;
        private String htmlDescription;
        private String attribute;
        private String imgUrl;
        private List<SkuListBean> skuList;
        private List<ProductRelateBean> productRelate;

        public CurrSkuBean getCurrSku() {
            return currSku;
        }

        public void setCurrSku(CurrSkuBean currSku) {
            this.currSku = currSku;
        }

        public String getHtmlDescription() {
            return htmlDescription;
        }

        public void setHtmlDescription(String htmlDescription) {
            this.htmlDescription = htmlDescription;
        }

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public List<SkuListBean> getSkuList() {
            return skuList;
        }

        public void setSkuList(List<SkuListBean> skuList) {
            this.skuList = skuList;
        }

        public List<ProductRelateBean> getProductRelate() {
            return productRelate;
        }

        public void setProductRelate(List<ProductRelateBean> productRelate) {
            this.productRelate = productRelate;
        }

        public static class CurrSkuBean {
            /**
             * skuId : 83
             * skuName : bilibili周边 魔性小电视长条抱枕 毛绒公仔90CM
             * oldPrice : 299
             * vipPlusPrice : 179
             * salePrice : 199
             * attribute : [{"id":821,"skuId":83,"name":"尺寸","value":"90CM"}]
             * skuInventory : 56
             */

            private int skuId;
            private String skuName;
            private int oldPrice;
            private int vipPlusPrice;
            private int salePrice;
            private int skuInventory;
            private List<AttributeBean> attribute;

            public int getSkuId() {
                return skuId;
            }

            public void setSkuId(int skuId) {
                this.skuId = skuId;
            }

            public String getSkuName() {
                return skuName;
            }

            public void setSkuName(String skuName) {
                this.skuName = skuName;
            }

            public int getOldPrice() {
                return oldPrice;
            }

            public void setOldPrice(int oldPrice) {
                this.oldPrice = oldPrice;
            }

            public int getVipPlusPrice() {
                return vipPlusPrice;
            }

            public void setVipPlusPrice(int vipPlusPrice) {
                this.vipPlusPrice = vipPlusPrice;
            }

            public int getSalePrice() {
                return salePrice;
            }

            public void setSalePrice(int salePrice) {
                this.salePrice = salePrice;
            }

            public int getSkuInventory() {
                return skuInventory;
            }

            public void setSkuInventory(int skuInventory) {
                this.skuInventory = skuInventory;
            }

            public List<AttributeBean> getAttribute() {
                return attribute;
            }

            public void setAttribute(List<AttributeBean> attribute) {
                this.attribute = attribute;
            }

            public static class AttributeBean {
                /**
                 * id : 821
                 * skuId : 83
                 * name : 尺寸
                 * value : 90CM
                 */

                private int id;
                private int skuId;
                private String name;
                private String value;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getSkuId() {
                    return skuId;
                }

                public void setSkuId(int skuId) {
                    this.skuId = skuId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }

        public static class SkuListBean {
            /**
             * skuId : 83
             * skuName : bilibili周边 魔性小电视长条抱枕 毛绒公仔90CM
             * oldPrice : 299
             * vipPlusPrice : 179
             * salePrice : 199
             * attribute : [{"id":821,"skuId":83,"name":"尺寸","value":"90CM"}]
             * skuInventory : 56
             */

            private int skuId;
            private String skuName;
            private int oldPrice;
            private int vipPlusPrice;
            private int salePrice;
            private int skuInventory;
            private List<AttributeBeanX> attribute;

            public int getSkuId() {
                return skuId;
            }

            public void setSkuId(int skuId) {
                this.skuId = skuId;
            }

            public String getSkuName() {
                return skuName;
            }

            public void setSkuName(String skuName) {
                this.skuName = skuName;
            }

            public int getOldPrice() {
                return oldPrice;
            }

            public void setOldPrice(int oldPrice) {
                this.oldPrice = oldPrice;
            }

            public int getVipPlusPrice() {
                return vipPlusPrice;
            }

            public void setVipPlusPrice(int vipPlusPrice) {
                this.vipPlusPrice = vipPlusPrice;
            }

            public int getSalePrice() {
                return salePrice;
            }

            public void setSalePrice(int salePrice) {
                this.salePrice = salePrice;
            }

            public int getSkuInventory() {
                return skuInventory;
            }

            public void setSkuInventory(int skuInventory) {
                this.skuInventory = skuInventory;
            }

            public List<AttributeBeanX> getAttribute() {
                return attribute;
            }

            public void setAttribute(List<AttributeBeanX> attribute) {
                this.attribute = attribute;
            }

            public static class AttributeBeanX {
                /**
                 * id : 821
                 * skuId : 83
                 * name : 尺寸
                 * value : 90CM
                 */

                private int id;
                private int skuId;
                private String name;
                private String value;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getSkuId() {
                    return skuId;
                }

                public void setSkuId(int skuId) {
                    this.skuId = skuId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }

        public static class ProductRelateBean {
            /**
             * bfileLink : http://i0.hdslb.com/bfs/travel/8bf24a1f803a439421d8101691b167e9f40b56ef.jpg
             * remark1 :
             */

            private String bfileLink;
            private String remark1;

            public String getBfileLink() {
                return bfileLink;
            }

            public void setBfileLink(String bfileLink) {
                this.bfileLink = bfileLink;
            }

            public String getRemark1() {
                return remark1;
            }

            public void setRemark1(String remark1) {
                this.remark1 = remark1;
            }
        }
    }
}
