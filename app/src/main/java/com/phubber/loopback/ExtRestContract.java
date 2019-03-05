/*
 *
 *  ExtRestContract.java
 *  gogame-android
 *
 *  Created by Alan Keh on 19-1-13 下午8:36
 *  Copyright (c) 2019  melote. All rights reserved.
 * /
 */

package com.phubber.loopback;

import java.util.Map;

public class ExtRestContract extends com.strongloop.android.remoting.adapters.RestContract {
    public static String URL_APPEND = "url_append";
    @Override
    public String getVerbForMethod(String method) {
        return super.getVerbForMethod(method);
    }

    @Override
    public String getUrlForMethod(String method,
                                  Map<String, ? extends Object> parameters) {
        String path = super.getUrlForMethod(method, parameters);
        if(parameters != null) {
            Object urlAppend = parameters.get(URL_APPEND);
            if (urlAppend != null && urlAppend instanceof String) {
                path += urlAppend;
                parameters.remove(URL_APPEND);
            }
        }
        return path;
    }
}
