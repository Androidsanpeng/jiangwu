package com.sanpeng.ourproject.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/8/3 0003.
 */
public class ZoneData {

    /**
     * id : 91
     * name : 陌么酒店
     * thumb : assets.jiangwoo.com/production/spaces/11700a39-74d9-41b8-8e8b-acb6291bb841
     * favs_count : 6
     * comments_count : 2
     * position : 7
     * shares_count : 5
     * username : 小匠
     */

    private List<SpacesBean> spaces;

    public List<SpacesBean> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<SpacesBean> spaces) {
        this.spaces = spaces;
    }

    public static class SpacesBean {
        private int id;
        private String name;
        private String thumb;
        private int favs_count;
        private int comments_count;
        private int position;
        private int shares_count;
        private String username;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public int getFavs_count() {
            return favs_count;
        }

        public void setFavs_count(int favs_count) {
            this.favs_count = favs_count;
        }

        public int getComments_count() {
            return comments_count;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getShares_count() {
            return shares_count;
        }

        public void setShares_count(int shares_count) {
            this.shares_count = shares_count;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
