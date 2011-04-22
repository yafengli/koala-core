#!/bin/sh
#http://tangent.org/552/libmemcached.html
LIBMEM=libmemcached-0.35
#http://memcached.org
MEM=memcached-1.4.4

echo 'download...'
if [ ! -e ${MEM}.tar.gz ];then
	wget -c http://memcached.googlecode.com/files/${MEM}.tar.gz
fi
if [ ! -e ${LIBMEM}.tar.gz ];then
	wget -c http://download.tangent.org/${LIBMEM}.tar.gz
fi
tar zxf ${MEM}.tar.gz
tar zxf ${LIBMEM}.tar.gz
echo 'install...'
cd $MEM
./configure
make && make install
cd ../$LIBMEM
./configure
make && make install
