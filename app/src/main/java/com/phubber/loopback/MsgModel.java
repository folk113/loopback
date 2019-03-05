/*
 *
 *  MsgModel.java
 *  gogame-android
 *
 *  Created by Alan Keh on 19-3-4 下午1:27
 *  Copyright (c) 2019  melote. All rights reserved.
 * /
 */

package com.phubber.loopback;

public class MsgModel extends BaseModel {
    private String title;
    private boolean isRead;
    private String recipientId;
    private String senderId;
    private String content;
    private String targetableId;
    private String targetableType;
    private String createdTime;
    private String updatedTime;
}