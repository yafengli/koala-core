#��������
#mongod --shardsvr:��Ƭ�洢���� --configsvr:���ô洢����
#mongos --configdb [host:port],[host:port],[host:port]...    �Զ��ŷָ�ͻ������Ӷ˿�ȱʡΪ27017
mongod --shardsvr --dbpath /tmp/data/1 --port 10000
mongod --shardsvr --dbpath /tmp/data/2 --port 10001
mongod --configsvr --dbpath /tmp/data/config --port 20000
mongos --configdb localhost:20000