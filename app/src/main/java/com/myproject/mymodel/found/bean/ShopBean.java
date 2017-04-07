package com.myproject.mymodel.found.bean;

import java.util.List;

/**
 * Created by chen on 2017/3/23 19:31.
 * 作用:XXXX
 */

public class ShopBean {

    /**
     * result : {"id":21,"createTime":1487068961000,"updateTime":1487068961000,"userName":"bichao","mark":"0","version":1,"title":"7f7d1ac0796211e6ab5352223301d29a","description":"偶像梦幻祭","resourceUrl":"http://i0.hdslb.com/bfs/travel/d0563ca84d6650c8a0bc38aa8ce9842cc038eed5.jpg","resourceLink":"//h5.m.taobao.com/awp/core/detail.htm?id=545041732350","modelDetails":[{"id":51,"createTime":1487069475000,"updateTime":1487069475000,"userName":"bichao","mark":"1","version":1,"bigTitle":"福袋","bigImageUrl":"","smallImageUrl":"http://i0.hdslb.com/bfs/travel/2ec02ad66ef4a5a0f035e32f1b41afb1698d554a.jpg","imgLink":"//h5.m.taobao.com/awp/core/detail.htm?id=535871470655","sortNumber":0,"modelMasterId":21},{"id":53,"createTime":1489574782000,"updateTime":1489574782000,"userName":"bichao","mark":"12","version":1,"bigTitle":"马克杯","bigImageUrl":"","smallImageUrl":"http://i0.hdslb.com/bfs/travel/98f7a72e4f8c3598993e1e0c0361619d7401ecea.jpg","imgLink":"//h5.m.taobao.com/awp/core/detail.htm?id=546613922505","sortNumber":2,"modelMasterId":21}]}
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
         * id : 21
         * createTime : 1487068961000
         * updateTime : 1487068961000
         * userName : bichao
         * mark : 0
         * version : 1
         * title : 7f7d1ac0796211e6ab5352223301d29a
         * description : 偶像梦幻祭
         * resourceUrl : http://i0.hdslb.com/bfs/travel/d0563ca84d6650c8a0bc38aa8ce9842cc038eed5.jpg
         * resourceLink : //h5.m.taobao.com/awp/core/detail.htm?id=545041732350
         * modelDetails : [{"id":51,"createTime":1487069475000,"updateTime":1487069475000,"userName":"bichao","mark":"1","version":1,"bigTitle":"福袋","bigImageUrl":"","smallImageUrl":"http://i0.hdslb.com/bfs/travel/2ec02ad66ef4a5a0f035e32f1b41afb1698d554a.jpg","imgLink":"//h5.m.taobao.com/awp/core/detail.htm?id=535871470655","sortNumber":0,"modelMasterId":21},{"id":53,"createTime":1489574782000,"updateTime":1489574782000,"userName":"bichao","mark":"12","version":1,"bigTitle":"马克杯","bigImageUrl":"","smallImageUrl":"http://i0.hdslb.com/bfs/travel/98f7a72e4f8c3598993e1e0c0361619d7401ecea.jpg","imgLink":"//h5.m.taobao.com/awp/core/detail.htm?id=546613922505","sortNumber":2,"modelMasterId":21}]
         */

        private int id;
        private long createTime;
        private long updateTime;
        private String userName;
        private String mark;
        private int version;
        private String title;
        private String description;
        private String resourceUrl;
        private String resourceLink;
        private List<ModelDetailsBean> modelDetails;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getResourceUrl() {
            return resourceUrl;
        }

        public void setResourceUrl(String resourceUrl) {
            this.resourceUrl = resourceUrl;
        }

        public String getResourceLink() {
            return resourceLink;
        }

        public void setResourceLink(String resourceLink) {
            this.resourceLink = resourceLink;
        }

        public List<ModelDetailsBean> getModelDetails() {
            return modelDetails;
        }

        public void setModelDetails(List<ModelDetailsBean> modelDetails) {
            this.modelDetails = modelDetails;
        }

        public static class ModelDetailsBean {
            /**
             * id : 51
             * createTime : 1487069475000
             * updateTime : 1487069475000
             * userName : bichao
             * mark : 1
             * version : 1
             * bigTitle : 福袋
             * bigImageUrl :
             * smallImageUrl : http://i0.hdslb.com/bfs/travel/2ec02ad66ef4a5a0f035e32f1b41afb1698d554a.jpg
             * imgLink : //h5.m.taobao.com/awp/core/detail.htm?id=535871470655
             * sortNumber : 0
             * modelMasterId : 21
             */

            private int id;
            private long createTime;
            private long updateTime;
            private String userName;
            private String mark;
            private int version;
            private String bigTitle;
            private String bigImageUrl;
            private String smallImageUrl;
            private String imgLink;
            private int sortNumber;
            private int modelMasterId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getMark() {
                return mark;
            }

            public void setMark(String mark) {
                this.mark = mark;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public String getBigTitle() {
                return bigTitle;
            }

            public void setBigTitle(String bigTitle) {
                this.bigTitle = bigTitle;
            }

            public String getBigImageUrl() {
                return bigImageUrl;
            }

            public void setBigImageUrl(String bigImageUrl) {
                this.bigImageUrl = bigImageUrl;
            }

            public String getSmallImageUrl() {
                return smallImageUrl;
            }

            public void setSmallImageUrl(String smallImageUrl) {
                this.smallImageUrl = smallImageUrl;
            }

            public String getImgLink() {
                return imgLink;
            }

            public void setImgLink(String imgLink) {
                this.imgLink = imgLink;
            }

            public int getSortNumber() {
                return sortNumber;
            }

            public void setSortNumber(int sortNumber) {
                this.sortNumber = sortNumber;
            }

            public int getModelMasterId() {
                return modelMasterId;
            }

            public void setModelMasterId(int modelMasterId) {
                this.modelMasterId = modelMasterId;
            }
        }
    }
}
