#启动服务
#mongod --shardsvr:分片存储服务 --configsvr:配置存储服务
#mongos --configdb [host:port],[host:port],[host:port]...    以逗号分割，客户端连接端口缺省为27017
mongod --shardsvr --dbpath /tmp/data/1 --port 10000
mongod --shardsvr --dbpath /tmp/data/2 --port 10001
mongod --configsvr --dbpath /tmp/data/config --port 20000
mongos --configdb localhost:20000