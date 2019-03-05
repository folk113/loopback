/*
 *
 *  IArrayModelDataListener.java
 *  gogame-android
 *
 *  Created by Alan Keh on 19-2-17 下午12:02
 *  Copyright (c) 2019  melote. All rights reserved.
 * /
 */

package com.phubber.loopback;

import java.util.List;

public interface IArrayModelDataListener<T> {
    void onReceiveModelData(boolean success, List<T> data, ErrorModel errorModel);
}
