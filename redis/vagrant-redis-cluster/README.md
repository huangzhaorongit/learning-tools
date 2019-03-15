Vagrant Redis Cluster
=====================

vagrant 로 ubuntu에 redis Cluster 설치 하기
1. Install [vagrant](http://www.vagrantup.com/)
1. Install [VirtualBox](https://www.virtualbox.org/wiki/Downloads)

```
$ vagrant plugin install vagrant-vbguest
$ vagrant box add ubuntu/trusty64
$ vagrant up
$ vagrant ssh
$ create-cluster.sh create

```

접속후에 

redis-cli -p 7000 접속


재시작
 
```

$ vagrant reload
$ vagrant ssh
$ sh create-cluster.sh start
$ sh create-cluster.sh create
$ sh redis_stat_run.sh


```

현재 실행 상태가 저장되고 중지
 
```

$ vagrant suspend

```
정상적으로 게스트 머신 다운 게스트 운영 체제 및 전원을 차단

```

$ vagrant halt

```
시스템에서 게스트 시스템의 모든 흔적이 제거

```

$ vagrant destroy

```
* `127.0.0.1:7000`
* `127.0.0.1:7001`
* `127.0.0.1:7002`
* `127.0.0.1:7003`
* `127.0.0.1:7004`
* `127.0.0.1:7005`

redis-stat 도 접속 가능 127.0.0.1:8080

지금 레디스 버전은 5.0 브런치 기준인데 레디스를 바꾸고 싶으면 build_redis.sh의 git clone을 바꿔주고 생성 하는것을 바꿔야 된다.



Redis 5

```

redis-cli --cluster create 127.0.0.1:7000 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005 --cluster-replicas 1

``` 

Using redis-trib.rb for Redis 4 or 3 type:

```
./redis-trib.rb create --replicas 1 127.0.0.1:7000 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005

``` 

