package com.myproject.bilibili.model.Cartoon.bean;

import java.util.List;

/**
 * Created by chen on 2017/3/24 8:59.
 * 作用:XXXX
 */

public class CartoonBean {

    /**
     * code : 0
     * message : success
     * result : {"ad":{"body":[],"head":[{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/83822a05fa70ae0a0fb82a5fb99dd9cff9d09408.jpg","link":"http://bangumi.bilibili.com/anime/5800","pub_time":"2017-03-22 23:30:00","title":"小林家的龙女仆"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/2d2ea08ce629fe22d3aa0ff67e19ce77a7f73070.jpg","link":"http://bangumi.bilibili.com/anime/5802","pub_time":"2017-03-23 02:00:00","title":"CHAOS;CHILD"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/7f2c6cb75ccd5c79a3007196505c3100492e950b.jpg","link":"http://bangumi.bilibili.com/anime/5773","pub_time":"2017-03-22 22:00:00","title":"秋叶原之旅"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/87ce44fc1c2897c4b849f6ca1a0323877c6bbfb7.jpg","link":"http://bangumi.bilibili.com/anime/5796","pub_time":"2017-03-22 01:35:00","title":"兽娘动物园"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/a368a33425cc0efb883366f9dc69a31a435a2246.jpg","link":"http://bangumi.bilibili.com/anime/2069","pub_time":"2017-03-24 00:00:00","title":"十二国记"}]},"previous":{"list":[{"cover":"http://i0.hdslb.com/bfs/bangumi/2673ac643b48eb5bda64c960a2ca850fbebb839d.jpg","favourites":"1675811","is_finish":1,"last_time":1482262210,"newest_ep_index":"11","pub_time":1475607600,"season_id":5550,"season_status":2,"title":"夏目友人帐 伍","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b75c55d209d156c8631f5ceb21e5c52c834dbb60.jpg","favourites":"1333957","is_finish":0,"last_time":1483196409,"newest_ep_index":"1","pub_time":1483196400,"season_id":5747,"season_status":2,"title":"Fate/Grand Order \u2010First Order\u2010","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b3633d2e5cafa0d048f4beef63618c92cfac4c4c.jpg","favourites":"786811","is_finish":1,"last_time":1482465609,"newest_ep_index":"12","pub_time":1475812800,"season_id":5534,"season_status":2,"title":"我太受欢迎了该怎么办","watching_count":0}],"season":4,"year":2016},"serializing":[{"cover":"http://i0.hdslb.com/bfs/bangumi/212345f11472cd39d6209652ca926f2249fdff36.jpg","favourites":"841696","is_finish":1,"is_started":1,"last_time":1490268602,"newest_ep_index":"720","pub_time":1452771000,"season_id":3287,"season_status":2,"title":"火影忍者 疾风传","watching_count":2097},{"cover":"http://i0.hdslb.com/bfs/bangumi/2cc5cd1644da95ca4aa5c39ef418d86461294772.jpg","favourites":"459737","is_finish":0,"is_started":1,"last_time":1490241602,"newest_ep_index":"36","pub_time":1469030400,"season_id":5352,"season_status":2,"title":"画江湖之不良人 第二季","watching_count":234},{"cover":"http://i0.hdslb.com/bfs/bangumi/5bf4ee170c207f0e793c8044bb668c3a2e2d80b6.jpg","favourites":"405698","is_finish":0,"is_started":1,"last_time":1490241600,"newest_ep_index":"7","pub_time":1486569600,"season_id":5849,"season_status":2,"title":"少年锦衣卫","watching_count":348},{"cover":"http://i0.hdslb.com/bfs/bangumi/950493f731008fc3e3de7bb2df3c833a2c6a9cc4.jpg","favourites":"153289","is_finish":0,"is_started":1,"last_time":1490234403,"newest_ep_index":"9","pub_time":1484150400,"season_id":5854,"season_status":2,"title":"超游世界","watching_count":4},{"cover":"http://i0.hdslb.com/bfs/bangumi/c1fc543021733e096104b277bd3469dbd3fd107d.jpg","favourites":"177769","is_finish":0,"is_started":1,"last_time":1490234402,"newest_ep_index":"18","pub_time":1466697600,"season_id":5291,"season_status":2,"title":"武庚纪","watching_count":46},{"badge":"付费抢先","cover":"http://i0.hdslb.com/bfs/bangumi/7fa8b91077bc09583a568f474184251e8111a0ca.jpg","favourites":"33102","is_finish":0,"is_started":1,"last_time":1490234402,"newest_ep_index":"22","pub_time":1478102400,"season_id":5846,"season_status":7,"title":"CMFU学院-王子碰碰球","watching_count":0}]}
     */

    private int code;
    private String message;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * ad : {"body":[],"head":[{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/83822a05fa70ae0a0fb82a5fb99dd9cff9d09408.jpg","link":"http://bangumi.bilibili.com/anime/5800","pub_time":"2017-03-22 23:30:00","title":"小林家的龙女仆"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/2d2ea08ce629fe22d3aa0ff67e19ce77a7f73070.jpg","link":"http://bangumi.bilibili.com/anime/5802","pub_time":"2017-03-23 02:00:00","title":"CHAOS;CHILD"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/7f2c6cb75ccd5c79a3007196505c3100492e950b.jpg","link":"http://bangumi.bilibili.com/anime/5773","pub_time":"2017-03-22 22:00:00","title":"秋叶原之旅"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/87ce44fc1c2897c4b849f6ca1a0323877c6bbfb7.jpg","link":"http://bangumi.bilibili.com/anime/5796","pub_time":"2017-03-22 01:35:00","title":"兽娘动物园"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/a368a33425cc0efb883366f9dc69a31a435a2246.jpg","link":"http://bangumi.bilibili.com/anime/2069","pub_time":"2017-03-24 00:00:00","title":"十二国记"}]}
         * previous : {"list":[{"cover":"http://i0.hdslb.com/bfs/bangumi/2673ac643b48eb5bda64c960a2ca850fbebb839d.jpg","favourites":"1675811","is_finish":1,"last_time":1482262210,"newest_ep_index":"11","pub_time":1475607600,"season_id":5550,"season_status":2,"title":"夏目友人帐 伍","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b75c55d209d156c8631f5ceb21e5c52c834dbb60.jpg","favourites":"1333957","is_finish":0,"last_time":1483196409,"newest_ep_index":"1","pub_time":1483196400,"season_id":5747,"season_status":2,"title":"Fate/Grand Order \u2010First Order\u2010","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b3633d2e5cafa0d048f4beef63618c92cfac4c4c.jpg","favourites":"786811","is_finish":1,"last_time":1482465609,"newest_ep_index":"12","pub_time":1475812800,"season_id":5534,"season_status":2,"title":"我太受欢迎了该怎么办","watching_count":0}],"season":4,"year":2016}
         * serializing : [{"cover":"http://i0.hdslb.com/bfs/bangumi/212345f11472cd39d6209652ca926f2249fdff36.jpg","favourites":"841696","is_finish":1,"is_started":1,"last_time":1490268602,"newest_ep_index":"720","pub_time":1452771000,"season_id":3287,"season_status":2,"title":"火影忍者 疾风传","watching_count":2097},{"cover":"http://i0.hdslb.com/bfs/bangumi/2cc5cd1644da95ca4aa5c39ef418d86461294772.jpg","favourites":"459737","is_finish":0,"is_started":1,"last_time":1490241602,"newest_ep_index":"36","pub_time":1469030400,"season_id":5352,"season_status":2,"title":"画江湖之不良人 第二季","watching_count":234},{"cover":"http://i0.hdslb.com/bfs/bangumi/5bf4ee170c207f0e793c8044bb668c3a2e2d80b6.jpg","favourites":"405698","is_finish":0,"is_started":1,"last_time":1490241600,"newest_ep_index":"7","pub_time":1486569600,"season_id":5849,"season_status":2,"title":"少年锦衣卫","watching_count":348},{"cover":"http://i0.hdslb.com/bfs/bangumi/950493f731008fc3e3de7bb2df3c833a2c6a9cc4.jpg","favourites":"153289","is_finish":0,"is_started":1,"last_time":1490234403,"newest_ep_index":"9","pub_time":1484150400,"season_id":5854,"season_status":2,"title":"超游世界","watching_count":4},{"cover":"http://i0.hdslb.com/bfs/bangumi/c1fc543021733e096104b277bd3469dbd3fd107d.jpg","favourites":"177769","is_finish":0,"is_started":1,"last_time":1490234402,"newest_ep_index":"18","pub_time":1466697600,"season_id":5291,"season_status":2,"title":"武庚纪","watching_count":46},{"badge":"付费抢先","cover":"http://i0.hdslb.com/bfs/bangumi/7fa8b91077bc09583a568f474184251e8111a0ca.jpg","favourites":"33102","is_finish":0,"is_started":1,"last_time":1490234402,"newest_ep_index":"22","pub_time":1478102400,"season_id":5846,"season_status":7,"title":"CMFU学院-王子碰碰球","watching_count":0}]
         */

        private AdBean ad;
        private PreviousBean previous;
        private List<SerializingBean> serializing;

        public AdBean getAd() {
            return ad;
        }

        public void setAd(AdBean ad) {
            this.ad = ad;
        }

        public PreviousBean getPrevious() {
            return previous;
        }

        public void setPrevious(PreviousBean previous) {
            this.previous = previous;
        }

        public List<SerializingBean> getSerializing() {
            return serializing;
        }

        public void setSerializing(List<SerializingBean> serializing) {
            this.serializing = serializing;
        }

        public static class AdBean {
            private List<?> body;
            private List<HeadBean> head;

            public List<?> getBody() {
                return body;
            }

            public void setBody(List<?> body) {
                this.body = body;
            }

            public List<HeadBean> getHead() {
                return head;
            }

            public void setHead(List<HeadBean> head) {
                this.head = head;
            }

            public static class HeadBean {
                /**
                 * id : 0
                 * img : http://i0.hdslb.com/bfs/bangumi/83822a05fa70ae0a0fb82a5fb99dd9cff9d09408.jpg
                 * link : http://bangumi.bilibili.com/anime/5800
                 * pub_time : 2017-03-22 23:30:00
                 * title : 小林家的龙女仆
                 */

                private int id;
                private String img;
                private String link;
                private String pub_time;
                private String title;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getPub_time() {
                    return pub_time;
                }

                public void setPub_time(String pub_time) {
                    this.pub_time = pub_time;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }

        public static class PreviousBean {
            /**
             * list : [{"cover":"http://i0.hdslb.com/bfs/bangumi/2673ac643b48eb5bda64c960a2ca850fbebb839d.jpg","favourites":"1675811","is_finish":1,"last_time":1482262210,"newest_ep_index":"11","pub_time":1475607600,"season_id":5550,"season_status":2,"title":"夏目友人帐 伍","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b75c55d209d156c8631f5ceb21e5c52c834dbb60.jpg","favourites":"1333957","is_finish":0,"last_time":1483196409,"newest_ep_index":"1","pub_time":1483196400,"season_id":5747,"season_status":2,"title":"Fate/Grand Order \u2010First Order\u2010","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b3633d2e5cafa0d048f4beef63618c92cfac4c4c.jpg","favourites":"786811","is_finish":1,"last_time":1482465609,"newest_ep_index":"12","pub_time":1475812800,"season_id":5534,"season_status":2,"title":"我太受欢迎了该怎么办","watching_count":0}]
             * season : 4
             * year : 2016
             */

            private int season;
            private int year;
            private List<ListBean> list;

            public int getSeason() {
                return season;
            }

            public void setSeason(int season) {
                this.season = season;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * cover : http://i0.hdslb.com/bfs/bangumi/2673ac643b48eb5bda64c960a2ca850fbebb839d.jpg
                 * favourites : 1675811
                 * is_finish : 1
                 * last_time : 1482262210
                 * newest_ep_index : 11
                 * pub_time : 1475607600
                 * season_id : 5550
                 * season_status : 2
                 * title : 夏目友人帐 伍
                 * watching_count : 0
                 */

                private String cover;
                private String favourites;
                private int is_finish;
                private int last_time;
                private String newest_ep_index;
                private int pub_time;
                private int season_id;
                private int season_status;
                private String title;
                private int watching_count;

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getFavourites() {
                    return favourites;
                }

                public void setFavourites(String favourites) {
                    this.favourites = favourites;
                }

                public int getIs_finish() {
                    return is_finish;
                }

                public void setIs_finish(int is_finish) {
                    this.is_finish = is_finish;
                }

                public int getLast_time() {
                    return last_time;
                }

                public void setLast_time(int last_time) {
                    this.last_time = last_time;
                }

                public String getNewest_ep_index() {
                    return newest_ep_index;
                }

                public void setNewest_ep_index(String newest_ep_index) {
                    this.newest_ep_index = newest_ep_index;
                }

                public int getPub_time() {
                    return pub_time;
                }

                public void setPub_time(int pub_time) {
                    this.pub_time = pub_time;
                }

                public int getSeason_id() {
                    return season_id;
                }

                public void setSeason_id(int season_id) {
                    this.season_id = season_id;
                }

                public int getSeason_status() {
                    return season_status;
                }

                public void setSeason_status(int season_status) {
                    this.season_status = season_status;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getWatching_count() {
                    return watching_count;
                }

                public void setWatching_count(int watching_count) {
                    this.watching_count = watching_count;
                }
            }
        }

        public static class SerializingBean {
            /**
             * cover : http://i0.hdslb.com/bfs/bangumi/212345f11472cd39d6209652ca926f2249fdff36.jpg
             * favourites : 841696
             * is_finish : 1
             * is_started : 1
             * last_time : 1490268602
             * newest_ep_index : 720
             * pub_time : 1452771000
             * season_id : 3287
             * season_status : 2
             * title : 火影忍者 疾风传
             * watching_count : 2097
             * badge : 付费抢先
             */

            private String cover;
            private String favourites;
            private int is_finish;
            private int is_started;
            private int last_time;
            private String newest_ep_index;
            private int pub_time;
            private int season_id;
            private int season_status;
            private String title;
            private int watching_count;
            private String badge;

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getFavourites() {
                return favourites;
            }

            public void setFavourites(String favourites) {
                this.favourites = favourites;
            }

            public int getIs_finish() {
                return is_finish;
            }

            public void setIs_finish(int is_finish) {
                this.is_finish = is_finish;
            }

            public int getIs_started() {
                return is_started;
            }

            public void setIs_started(int is_started) {
                this.is_started = is_started;
            }

            public int getLast_time() {
                return last_time;
            }

            public void setLast_time(int last_time) {
                this.last_time = last_time;
            }

            public String getNewest_ep_index() {
                return newest_ep_index;
            }

            public void setNewest_ep_index(String newest_ep_index) {
                this.newest_ep_index = newest_ep_index;
            }

            public int getPub_time() {
                return pub_time;
            }

            public void setPub_time(int pub_time) {
                this.pub_time = pub_time;
            }

            public int getSeason_id() {
                return season_id;
            }

            public void setSeason_id(int season_id) {
                this.season_id = season_id;
            }

            public int getSeason_status() {
                return season_status;
            }

            public void setSeason_status(int season_status) {
                this.season_status = season_status;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getWatching_count() {
                return watching_count;
            }

            public void setWatching_count(int watching_count) {
                this.watching_count = watching_count;
            }

            public String getBadge() {
                return badge;
            }

            public void setBadge(String badge) {
                this.badge = badge;
            }
        }
    }
}
