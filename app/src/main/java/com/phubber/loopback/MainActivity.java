package com.phubber.loopback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.strongloop.android.loopback.RestAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //你的服务器提供的Restful Api
        RestAdapter adapter = new RestAdapter(getApplicationContext(), "http://xxx.xxx.com/api");
        adapter.setContract(new ExtRestContract());

        //调用示例
        MsgHandler.getInstance(adapter);
        MsgHandler.getInstance().getMsg("recipientId", "createdTime", true, new IArrayModelDataListener<MsgModel>() {
            @Override
            public void onReceiveModelData(boolean success, List<MsgModel> data, ErrorModel errorModel) {

            }
        });
    }

}
