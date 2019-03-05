/*
 *
 *  ExtJsonObjectParser.java
 *  gogame-android
 *
 *  Created by Alan Keh on 19-1-13 下午8:35
 *  Copyright (c) 2019  melote. All rights reserved.
 * /
 */

package com.phubber.loopback;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.strongloop.android.loopback.callbacks.ObjectCallback;
import com.strongloop.android.remoting.Repository;
import com.strongloop.android.remoting.VirtualObject;
import com.strongloop.android.remoting.adapters.Adapter;

public class ExtJsonObjectParser<T extends VirtualObject>
        implements Adapter.Callback {
    private final Repository<T> repository;
    private final ObjectCallback<T> callback;
    private final Class<T> cls;

    public ExtJsonObjectParser(Repository repository, Class<T> cls, ObjectCallback<T> callback) {
        this.repository = repository;
        this.callback = callback;
        this.cls = cls;
    }

    @Override
    public void onSuccess(String response) {
        JsonParser parser = new JsonParser();
        JsonElement el = parser.parse(response);
        Gson gson = new Gson();
        T obj = gson.fromJson(el, cls);
        obj.setRepository(repository);
        if(callback != null)
        {
            callback.onSuccess(obj);
        }
    }

    @Override
    public void onError(Throwable t) {
        if(callback != null)
        {
            callback.onError(t);
        }
    }
}
