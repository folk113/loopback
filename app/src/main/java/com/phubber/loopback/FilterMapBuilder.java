/*
 *
 *  FilterMapBuilder.java
 *  gogame-android
 *
 *  Created by Alan Keh on 19-1-13 下午8:36
 *  Copyright (c) 2019  melote. All rights reserved.
 * /
 */

package com.phubber.loopback;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FilterMapBuilder {
    private int skip = 0;
    private int limit = 30;
    private String appendUrl;
    private HashMap<String, Object> where = new HashMap<>();

    public FilterMapBuilder() {
    }

    public FilterMapBuilder appendUrl(String url) {
        appendUrl = url;
        return this;
    }

    public FilterMapBuilder limit(int limit) {
        this.limit = limit;
        return this;
    }

    public FilterMapBuilder skip(int skip) {
        this.skip = skip;
        return this;
    }
    //GET /api/Circles/5bac56f8e26dd96bf371bc89/comments?filter={"order":"createdTime DESC","skip":0,"include":["owner",{"replies":["owner","replyee"]}],"limit":20}
    ArrayList<Object> includes = new ArrayList<>();

    public FilterMapBuilder include(String key, Object value) {
        HashMap<String, Object> include = new HashMap<>();
        include.put(key, value);
        includes.add(include);
        return this;
    }

    public FilterMapBuilder include(Object value) {
        includes.add(value);
        return this;
    }

    /**
     * 过滤值等于value的内容
     *
     * @param key
     * @param value
     * @return
     */
    public FilterMapBuilder where(String key, Object value) {
        where.put(key, value);
        return this;
    }

    public FilterMapBuilder where(String key, Object value, boolean isGreatThan) {
        HashMap<String, Object> whereSubClause = new HashMap<>();
        whereSubClause.put(isGreatThan ? "gt" : "lt", value);
        where.put(key, whereSubClause);
        return this;
    }

    /**
     * @param column 搜索的字段
     * @param search 搜索的内容
     * @param sensitive 搜索内容是否大小写敏感
     * @return
     */
    public FilterMapBuilder like(String column, String search, boolean sensitive) {
        HashMap<String, Object> whereSubClause = new HashMap<>();
        whereSubClause.put("like", search);
        if (!sensitive) {
            whereSubClause.put("options", "i");
        }
        where.put(column, whereSubClause);
        return this;
    }

    /**
     * @param column 搜索字段
     * @param searchContent 搜索内容
     * @param sensitive 搜索内容是否大小写敏感
     * @param and  搜索内容之间的关系 and is true,or is false
     * @return
     */
    public FilterMapBuilder like(@NonNull String[] column, @NonNull String[] searchContent, @NonNull boolean sensitive, @NonNull boolean and) {

        ArrayList<Map<String,Object>> relativeParam = new  ArrayList<>();
        for (int index = 0; index < column.length; index++) {
            HashMap<String, Object> likeParam = new HashMap<>();
            likeParam.put("like", searchContent[index]);
            if (!sensitive) {
                likeParam.put("options", "i");
            }
            HashMap<String, Object> likeParams = new HashMap<>();
            likeParams.put(column[index], likeParam);
            relativeParam.add(likeParams);
        }
        if (relativeParam.size() > 1) {
            where.put(and ? "and" : "or", relativeParam);
        } else if (relativeParam.size() == 1) {
            where.putAll(relativeParam.get(0));
        }
        return this;
    }

    private ArrayList<String> order = new ArrayList<>();

    public FilterMapBuilder orderBy(String propertyName, boolean isASC) {
        order.add(propertyName + " " + (isASC ? "ASC" : "DESC"));
        return this;
    }

    public HashMap<String, Object> build() {
        HashMap<String, Object> ret = new HashMap<>();
        HashMap<String, Object> filter = new HashMap<>();
        if (!TextUtils.isEmpty(appendUrl)) {
            ret.put(ExtRestContract.URL_APPEND, appendUrl);
        }
        filter.put("skip", skip);
        filter.put("limit", limit);
        if (!includes.isEmpty()) {
            filter.put("include", includes);
        }
        if (!where.isEmpty()) {
            filter.put("where", where);
        }
        if (!order.isEmpty()) {
            filter.put("order", order);
        }
        ret.put("filter", filter);
        return ret;
    }
}
