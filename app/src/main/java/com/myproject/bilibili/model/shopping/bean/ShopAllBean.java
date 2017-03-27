package com.myproject.bilibili.model.shopping.bean;

import java.util.List;

/**
 * Created by chen on 2017/3/27 16:44.
 * 作用:XXXX
 */

public class ShopAllBean {

    /**
     * result : {"pageNumber":1,"pageSize":6,"totalCount":7,"pageCount":2,"hasNextPage":true,"records":[{"title":"bilibili周边 魔性小电视长条抱枕 毛绒公仔90CM","skuId":83,"oldPrice":299,"vipPlusPrice":179,"salvePrice":199,"imgUrl":"http://i0.hdslb.com/bfs/travel/8bf24a1f803a439421d8101691b167e9f40b56ef.jpg"},{"title":"bilibili周边 B站经典小电视毛绒抱枕3件套 大号+小号+mini号","skuId":90,"oldPrice":257,"vipPlusPrice":179,"salvePrice":199,"imgUrl":"http://i0.hdslb.com/bfs/travel/b324578e21d5db38bfb99e1340ceba900c082ce0.jpg"},{"title":"官方授权  梦王国与沉睡的100王子马口铁徽章一套12枚","skuId":104,"oldPrice":180,"vipPlusPrice":133,"salvePrice":150,"imgUrl":"http://i0.hdslb.com/bfs/travel/4113536262e39261685109582fb9b5feaa16fc6a.jpg"},{"title":"【少量现货】在下坂本 有何贵干 现货可动手办","skuId":109,"oldPrice":450,"vipPlusPrice":350,"salvePrice":450,"imgUrl":"http://i0.hdslb.com/bfs/travel/96cf8efe67f251a550fb2dd18cb8d6f0a00283db.jpg"},{"title":"bilibili周边 哔哩哔哩2233娘便携式蓝牙音箱","skuId":139,"oldPrice":266,"vipPlusPrice":179,"salvePrice":199,"imgUrl":"http://i0.hdslb.com/bfs/travel/95c619aedefcc667e58e0a43134bdfaafff729a5.jpg"},{"title":"bilibili官方周边 哔哩哔哩机械之心移动电源便携充电宝","skuId":162,"oldPrice":233,"vipPlusPrice":179,"salvePrice":199,"imgUrl":"http://i0.hdslb.com/bfs/travel/4aafc5144c15e44c1777eb9dd295b335b9053425.jpg"}],"startIndex":0}
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
         * pageNumber : 1
         * pageSize : 6
         * totalCount : 7
         * pageCount : 2
         * hasNextPage : true
         * records : [{"title":"bilibili周边 魔性小电视长条抱枕 毛绒公仔90CM","skuId":83,"oldPrice":299,"vipPlusPrice":179,"salvePrice":199,"imgUrl":"http://i0.hdslb.com/bfs/travel/8bf24a1f803a439421d8101691b167e9f40b56ef.jpg"},{"title":"bilibili周边 B站经典小电视毛绒抱枕3件套 大号+小号+mini号","skuId":90,"oldPrice":257,"vipPlusPrice":179,"salvePrice":199,"imgUrl":"http://i0.hdslb.com/bfs/travel/b324578e21d5db38bfb99e1340ceba900c082ce0.jpg"},{"title":"官方授权  梦王国与沉睡的100王子马口铁徽章一套12枚","skuId":104,"oldPrice":180,"vipPlusPrice":133,"salvePrice":150,"imgUrl":"http://i0.hdslb.com/bfs/travel/4113536262e39261685109582fb9b5feaa16fc6a.jpg"},{"title":"【少量现货】在下坂本 有何贵干 现货可动手办","skuId":109,"oldPrice":450,"vipPlusPrice":350,"salvePrice":450,"imgUrl":"http://i0.hdslb.com/bfs/travel/96cf8efe67f251a550fb2dd18cb8d6f0a00283db.jpg"},{"title":"bilibili周边 哔哩哔哩2233娘便携式蓝牙音箱","skuId":139,"oldPrice":266,"vipPlusPrice":179,"salvePrice":199,"imgUrl":"http://i0.hdslb.com/bfs/travel/95c619aedefcc667e58e0a43134bdfaafff729a5.jpg"},{"title":"bilibili官方周边 哔哩哔哩机械之心移动电源便携充电宝","skuId":162,"oldPrice":233,"vipPlusPrice":179,"salvePrice":199,"imgUrl":"http://i0.hdslb.com/bfs/travel/4aafc5144c15e44c1777eb9dd295b335b9053425.jpg"}]
         * startIndex : 0
         */

        private int pageNumber;
        private int pageSize;
        private int totalCount;
        private int pageCount;
        private boolean hasNextPage;
        private int startIndex;
        private List<RecordsBean> records;

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getStartIndex() {
            return startIndex;
        }

        public void setStartIndex(int startIndex) {
            this.startIndex = startIndex;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
            /**
             * title : bilibili周边 魔性小电视长条抱枕 毛绒公仔90CM
             * skuId : 83
             * oldPrice : 299
             * vipPlusPrice : 179
             * salvePrice : 199
             * imgUrl : http://i0.hdslb.com/bfs/travel/8bf24a1f803a439421d8101691b167e9f40b56ef.jpg
             */

            private String title;
            private int skuId;
            private int oldPrice;
            private int vipPlusPrice;
            private int salvePrice;
            private String imgUrl;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getSkuId() {
                return skuId;
            }

            public void setSkuId(int skuId) {
                this.skuId = skuId;
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

            public int getSalvePrice() {
                return salvePrice;
            }

            public void setSalvePrice(int salvePrice) {
                this.salvePrice = salvePrice;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }
    }
}
