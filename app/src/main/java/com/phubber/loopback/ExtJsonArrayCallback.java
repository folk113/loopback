/*
 *
 *  ExtJsonArrayCallback.java
 *  gogame-android
 *
 *  Created by Alan Keh on 19-1-13 下午8:35
 *  Copyright (c) 2019  melote. All rights reserved.
 * /
 */

package com.phubber.loopback;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.strongloop.android.loopback.callbacks.ListCallback;
import com.strongloop.android.remoting.Repository;
import com.strongloop.android.remoting.adapters.Adapter;

import java.util.ArrayList;
import java.util.Iterator;

public class ExtJsonArrayCallback<T extends BaseModel> implements Adapter.Callback {
    private final Repository<T> repository;
    private final ListCallback<T> callback;
    private final Class<T> cls;

    public ExtJsonArrayCallback(Repository<T> repository, Class<T> cls, ListCallback<T> callback) {
        this.repository = repository;
        this.callback = callback;
        this.cls = cls;
    }

    @Override
    public void onSuccess(String response) {
        ArrayList<T> ret = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonElement el = parser.parse(response);
        JsonArray jsonArray;
        Gson gson = new Gson();
        if (el.isJsonArray()) {
            jsonArray = el.getAsJsonArray();
            Iterator it = jsonArray.iterator();
            while (it.hasNext()) {
                JsonElement e = (JsonElement) it.next();
                T obj = gson.fromJson(e, cls);
                obj.setRepository(repository);
                ret.add(obj);
            }
        } else {
            T obj = gson.fromJson(el, cls);
            ret.add(obj);
        }
        callback.onSuccess(ret);
    }

    @Override
    public void onError(Throwable t) {
        callback.onError(t);
    }
}
