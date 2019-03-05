# loopback
基于strongloop社区loopback android修改而来，支持patch，json数据递归解析

使用示例
<pre><code>RestAdapter adapter = new RestAdapter(getApplicationContext(), "http://xxx.xxx.com/api");
adapter.setContract(new ExtRestContract());        
MsgHandler.getInstance(adapter);
MsgHandler.getInstance().getMsg("recipientId", "createdTime", true, new IArrayModelDataListener<MsgModel>() {
      @Override
      public void onReceiveModelData(boolean success, List<MsgModel> data, ErrorModel errorModel) {       
  });</code><pre>
