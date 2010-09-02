$mongo --host [ip]
> show dbs
admin
config
Local
> use admin
> db.runCommand( { addshard : "127.0.0.1:10000", allowLocal : true } ) //增加第一组分片,分片可以是多个mongod以逗号分割127.0.0.1:10000,127.0.0.1:10001
> db.runCommand( { addshard : "127.0.0.1:10001", allowLocal : true } )    //增加第二个分片
> config = connect("127.0.0.1:20000")         //连接config server，需要在mongos服务器上操作
> config = config.getSisterDB("config")    //获取配置信息
> test = db.getSisterDB("test")       //新增数据库test
> db.runCommand( { enablesharding : "test" } )
> db.runCommand( { shardcollection : "test.people", key : {name : 1} } )    //建立分片数据集people
> db.printShardingStatus();     //查看分片状态信息，会看到shards和databases的详细信息，包括刚建立的test的主分片信息和test.people的分片信息。
> db.runCommand({listshards:1}) //查看分片情况，会看到分片的信息，共两块
{
"_id" : ObjectId("4bd27bb39e2b00a5f7d5f0dd"),
"host" : "127.0.0.1:10000"
},
{
"_id" : ObjectId("4bd27bd39e2b00a5f7d5f0de"),
"host" : "127.0.0.1:10001"
}

  