/*
 *
 *  ExtModelRepository.java
 *  gogame-android
 *
 *  Created by Alan Keh on 19-1-13 下午8:36
 *  Copyright (c) 2019  melote. All rights reserved.
 * /
 */

package com.phubber.loopback;

import com.google.gson.Gson;
import com.strongloop.android.loopback.Model;

import java.util.Map;

public class ExtModelRepository<T extends Model> extends com.strongloop.android.loopback.ModelRepository<T> {
    private Class<T> modelClass;
    public ExtModelRepository(String className) {
        this(className, null);
    }

    public ExtModelRepository(String className, Class<T> modelClass) {
        this(className, null, modelClass);
    }

    public ExtModelRepository(String className, String nameForRestUrl, Class<T> modelClass) {
        super(className, nameForRestUrl, modelClass);
        this.modelClass = modelClass;
    }

    @Override
    public com.strongloop.android.remoting.adapters.RestContract createContract()
    {
        ExtRestContract contract = new ExtRestContract();
        String className = getClassName();
        String nameForRestUrl = getNameForRestUrl();

        contract.addItem(new com.strongloop.android.remoting.adapters.RestContractItem("/" + nameForRestUrl, "POST"),
                className + ".prototype.create");
        contract.addItem(new com.strongloop.android.remoting.adapters.RestContractItem("/" + nameForRestUrl + "/:id", "PUT"),
                className + ".prototype.save");
        contract.addItem(new com.strongloop.android.remoting.adapters.RestContractItem("/" + nameForRestUrl + "/:id", "PATCH"),
                className + ".prototype.patch");
        contract.addItem(
                new com.strongloop.android.remoting.adapters.RestContractItem("/" + nameForRestUrl + "/:id", "DELETE"),
                className + ".prototype.remove");
        contract.addItem(new com.strongloop.android.remoting.adapters.RestContractItem("/" + nameForRestUrl + "/:id", "GET"),
                className + ".findById");
        contract.addItem(new com.strongloop.android.remoting.adapters.RestContractItem("/" + nameForRestUrl + "/findOne", "GET"),
                className + ".findOne");
        contract.addItem(new com.strongloop.android.remoting.adapters.RestContractItem("/" + nameForRestUrl, "GET"),
                className + ".all");
        return contract;
    }

    @Override
    public T createObject(Map<String, ? extends Object> creationParameters) {
        Gson gson = new Gson();
        String json = gson.toJson(creationParameters);
        T instance = gson.fromJson(json,modelClass);
        instance.setRepository(this);
        return instance;
    }
}
