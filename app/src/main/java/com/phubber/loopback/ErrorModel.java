/*
 *
 *  ErrorModel.java
 *  gogame-android
 *
 *  Created by Alan Keh on 19-1-21 下午1:45
 *  Copyright (c) 2019  melote. All rights reserved.
 * /
 */

package com.phubber.loopback;

import java.util.HashMap;

public class ErrorModel extends BaseModel
{
    private int statusCode;
    private String name;
    private String message;
    private HashMap<String,Object> details;
}