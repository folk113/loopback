/*
 *
 *  IModelDataListener.java
 *  gogame-android
 *
 *  Created by Alan Keh on 19-2-17 下午12:02
 *  Copyright (c) 2019  melote. All rights reserved.
 * /
 */

package com.phubber.loopback;

public interface IModelDataListener<T> {
    void onReceiveModelData(boolean success, T data, ErrorModel errorModel);
}
