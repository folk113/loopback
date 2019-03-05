/*
 *
 *  MsgHandler.java
 *  gogame-android
 *
 *  Created by Alan Keh on 19-3-4 下午1:29
 *  Copyright (c) 2019  melote. All rights reserved.
 * /
 */

package com.phubber.loopback;

import android.text.TextUtils;

import com.strongloop.android.loopback.RestAdapter;

public class MsgHandler extends ExtModelHandler {
    private final static String TAG = MsgHandler.class.getSimpleName();
    private static volatile MsgHandler sInstance;

    /**
     * 初始化的时候使用
     *
     * @param adapter
     * @return
     */
    public static MsgHandler getInstance(RestAdapter adapter) {
        if (sInstance == null) {
            synchronized (MsgHandler.class) {
                if (sInstance == null)
                    sInstance = new MsgHandler(adapter);
            }
        }
        return sInstance;
    }

    /**
     * 增删查改时调用
     *
     * @return
     */
    public static MsgHandler getInstance() {
        return sInstance;
    }

    private MsgHandler(RestAdapter adapter) {
        super(adapter.createRepository(MsgRepository.class));
    }


    public static class MsgRepository extends ExtModelRepository<MsgModel> {
        public MsgRepository() {
            super("Message", MsgModel.class);
        }
    }

    ///api/Messages?filter={"skip":0,"order":"createdTime DESC","where":{"recipientId":"5c7556abf21b62517f14cbd9"},"limit":30
    public void getMsg(String recipientId, String createdTime, boolean refresh, IArrayModelDataListener<MsgModel> listener) {
        FilterMapBuilder builder = new FilterMapBuilder();
        builder.appendUrl("?access_token=my_token_id");
        builder.where("recipientId", recipientId);
        if (!TextUtils.isEmpty(createdTime)) {
            builder.where("createdTime", createdTime, refresh);
        }
        builder.orderBy("createdTime", false);
        getModels(builder.build(), listener);
    }
}
