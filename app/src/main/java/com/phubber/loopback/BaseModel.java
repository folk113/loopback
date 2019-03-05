/*
 *
 *  BaseModel.java
 *  gogame-android
 *
 *  Created by Alan Keh on 19-1-13 下午8:36
 *  Copyright (c) 2019  melote. All rights reserved.
 * /
 */

package com.phubber.loopback;


import com.strongloop.android.loopback.Model;

import java.io.Serializable;

public class BaseModel extends Model implements Serializable, Cloneable {

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (Throwable e) {
            throw new AssertionError();
        }
    }
}
