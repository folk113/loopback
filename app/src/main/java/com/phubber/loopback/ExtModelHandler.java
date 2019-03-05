/*
 *
 *  BaseModelHandler.java
 *  gogame-android
 *
 *  Created by Alan Keh on 19-2-14 上午10:50
 *  Copyright (c) 2019  melote. All rights reserved.
 * /
 */

package com.phubber.loopback;

import android.support.annotation.NonNull;
import android.util.Log;

import com.strongloop.android.loopback.Model;
import com.strongloop.android.loopback.callbacks.JsonObjectParser;
import com.strongloop.android.loopback.callbacks.ListCallback;
import com.strongloop.android.loopback.callbacks.ObjectCallback;
import com.strongloop.android.loopback.callbacks.VoidCallback;

import java.util.List;
import java.util.Map;

public class ExtModelHandler {
    private final static String TAG = ExtModelHandler.class.getSimpleName();

    private ExtModelRepository mRepository;

    protected ExtModelHandler(ExtModelRepository repository) {
        mRepository = repository;
    }


    /**
     * 由于RestAdapter归所有Repository类共享，所以此处由任意BaseModeHandler派生类调用都一样
     *
     * @param tokenId
     */
    public void setAccessToken(String tokenId) {
        mRepository.getRestAdapter().setAccessToken(tokenId);
    }

    public void clearAccessToken() {
        mRepository.getRestAdapter().clearAccessToken();
    }


    public void post(@NonNull BaseModel model, @NonNull IModelDataListener listener) {
        post(model.toMap(), listener);
    }

    public <T extends BaseModel> void post(Map<String, ?> parameters, @NonNull final IModelDataListener<T> listener) {
        mRepository.invokeStaticMethod("prototype.create",
                parameters,
                new JsonObjectParser<T>(mRepository, new ObjectCallback<T>() {
                    @Override
                    public void onSuccess(T data) {
                        try {
                            listener.onReceiveModelData(true, data, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        try {
                            listener.onReceiveModelData(false, null, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                }));
    }

    public <T extends BaseModel> void post(Map<String, ?> parameters, Class<T> cls, final IModelDataListener<T> listener) {
        mRepository.invokeStaticMethod("prototype.create",
                parameters,
                new ExtJsonObjectParser(mRepository, cls, new ObjectCallback<T>() {
                    @Override
                    public void onSuccess(T data) {
                        try {
                            listener.onReceiveModelData(true, data, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        try {
                            listener.onReceiveModelData(false, null, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                }));
    }

    public <T extends BaseModel> void post(Map<String, ?> parameters, Class<T> cls, final IArrayModelDataListener<T> listener) {
        mRepository.invokeStaticMethod("prototype.create",
                parameters,
                new ExtJsonArrayCallback<T>(mRepository, cls, new ListCallback<T>() {
                    @Override
                    public void onSuccess(List<T> data) {
                        try {
                            listener.onReceiveModelData(true, data, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        try {
                            listener.onReceiveModelData(false, null, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                }));
    }

    public <T extends BaseModel> void put(Map<String, ? extends Object> params, @NonNull final IModelDataListener<T> listener) {
        mRepository.invokeStaticMethod("prototype.save",
                params,
                new JsonObjectParser<T>(mRepository, new ObjectCallback<T>() {
                    @Override
                    public void onSuccess(T data) {
                        try {
                            listener.onReceiveModelData(true, data, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        try {
                            listener.onReceiveModelData(false, null, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                }));
    }

    public <T extends BaseModel> void put(T model, @NonNull final IModelDataListener<T> listener) {
        mRepository.invokeStaticMethod("prototype.save",
                model.toMap(),
                new JsonObjectParser<T>(mRepository, new ObjectCallback<T>() {
                    @Override
                    public void onSuccess(T data) {
                        try {
                            listener.onReceiveModelData(true, data, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        try {
                            listener.onReceiveModelData(false, null, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                }));
    }

    public <T extends BaseModel> void patch(Map<String, ? extends Object> params, @NonNull final IModelDataListener<T> listener) {
        mRepository.invokeStaticMethod("prototype.patch",
                params,
                new JsonObjectParser<T>(mRepository, new ObjectCallback<T>() {
                    @Override
                    public void onSuccess(T data) {
                        try {
                            listener.onReceiveModelData(true, data, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        try {
                            listener.onReceiveModelData(false, null, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                }));
    }

    public <T extends BaseModel> void patch(T model, @NonNull final IModelDataListener<T> listener) {
        mRepository.invokeStaticMethod("prototype.patch",
                model.toMap(),
                new JsonObjectParser<T>(mRepository, new ObjectCallback<T>() {
                    @Override
                    public void onSuccess(T data) {
                        try {
                            listener.onReceiveModelData(true, data, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        try {
                            listener.onReceiveModelData(false, null, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                }));
    }


    public <T extends BaseModel> void getModel(Map<String, ?> parameters, final IModelDataListener<T> listener) {
        mRepository.invokeStaticMethod("all",
                parameters,
                new JsonObjectParser<T>(mRepository, new ObjectCallback<T>() {
                    @Override
                    public void onSuccess(T data) {
                        try {
                            listener.onReceiveModelData(true, data, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        try {
                            listener.onReceiveModelData(false, null, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                }));
    }

    public <T extends BaseModel> void getModel(Map<String, ?> parameters, Class<T> cls, final IModelDataListener<T> listener) {
        mRepository.invokeStaticMethod("all",
                parameters,
                new ExtJsonObjectParser(mRepository, cls, new ObjectCallback<T>() {
                    @Override
                    public void onSuccess(T data) {
                        try {
                            listener.onReceiveModelData(true, data, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        try {
                            listener.onReceiveModelData(false, null, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                }));
    }

    public <T extends BaseModel> void getModels(Map<String, ?> parameters, Class<T> cls, final IArrayModelDataListener<T> listener) {
        mRepository.invokeStaticMethod("all",
                parameters,
                new ExtJsonArrayCallback<T>(mRepository, cls, new ListCallback<T>() {
                    @Override
                    public void onSuccess(List<T> data) {
                        try {
                            listener.onReceiveModelData(true, data, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        try {
                            listener.onReceiveModelData(false, null, null);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                }));
    }

    public <T extends BaseModel> void getModels(final Map<String, ?> parameters, final IArrayModelDataListener<T> listener) {
        mRepository.find(parameters, new ListCallback<T>() {
            @Override
            public void onSuccess(List<T> models) {
                Log.d(TAG, "getModels onSuccess:" + models.toString());
                try {
                    listener.onReceiveModelData(true, models, null);
                } catch (Throwable throwable) {
                    Log.e(TAG, "getModels:" + parameters.toString());
                    throwable.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable t) {
                Log.e(TAG, "getModels error param:" + t.toString());
                try {
                    listener.onReceiveModelData(true, null, null);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }

    public <T extends BaseModel> void getModels(final IArrayModelDataListener<T> listener) {
        mRepository.findAll(new ListCallback<T>() {
            @Override
            public void onSuccess(List<T> models) {
                Log.d(TAG, models.toString());
                try {
                    listener.onReceiveModelData(true, models, null);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, t.getMessage());
                try {
                    listener.onReceiveModelData(true, null, null);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }


    public <T extends BaseModel> void getModelById(final String id, final IModelDataListener<T> listener) {
        mRepository.findById(id, new ObjectCallback<T>() {
            @Override
            public void onSuccess(T data) {
                try {
                    listener.onReceiveModelData(true, data, null);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                Log.d(TAG, "getGame onError id:" + id);
                try {
                    listener.onReceiveModelData(true, null, null);
                } catch (Throwable throwable1) {
                    throwable1.printStackTrace();
                }
            }
        });
    }


    /**
     * 新建或者更新，id不为空时是更新值，为空时是新建
     *
     * @param model
     */
    public void postModel(BaseModel model, IModelDataListener listener) {
        post(model.toMap(), listener);
    }


    public <T extends BaseModel> void deleteModel(final T model, final IModelDataListener<T> listener) {
        Model newModel = new Model(mRepository, model.toMap());
        newModel.destroy(new VoidCallback() {
            @Override
            public void onSuccess() {
                try {
                    listener.onReceiveModelData(true, model, null);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                try {
                    listener.onReceiveModelData(false, model, null);
                } catch (Throwable throwable1) {
                    throwable1.printStackTrace();
                }
            }
        });
    }
}
