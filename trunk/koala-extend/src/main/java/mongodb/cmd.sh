$mongo --host [ip]
> show dbs
admin
config
Local
> use admin
> db.runCommand( { addshard : "127.0.0.1:10000", allowLocal : true } ) //���ӵ�һ���Ƭ,��Ƭ�����Ƕ��mongod�Զ��ŷָ�127.0.0.1:10000,127.0.0.1:10001
> db.runCommand( { addshard : "127.0.0.1:10001", allowLocal : true } )    //���ӵڶ�����Ƭ
> config = connect("127.0.0.1:20000")         //����config server����Ҫ��mongos�������ϲ���
> config = config.getSisterDB("config")    //��ȡ������Ϣ
> test = db.getSisterDB("test")       //�������ݿ�test
> db.runCommand( { enablesharding : "test" } )
> db.runCommand( { shardcollection : "test.people", key : {name : 1} } )    //������Ƭ���ݼ�people
> db.printShardingStatus();     //�鿴��Ƭ״̬��Ϣ���ῴ��shards��databases����ϸ��Ϣ�������ս�����test������Ƭ��Ϣ��test.people�ķ�Ƭ��Ϣ��
> db.runCommand({listshards:1}) //�鿴��Ƭ������ῴ����Ƭ����Ϣ��������
{
"_id" : ObjectId("4bd27bb39e2b00a5f7d5f0dd"),
"host" : "127.0.0.1:10000"
},
{
"_id" : ObjectId("4bd27bd39e2b00a5f7d5f0de"),
"host" : "127.0.0.1:10001"
}

  