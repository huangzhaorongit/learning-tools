#!/usr/bin/env bash

# Settings
PORT=6999
TIMEOUT=2000
NODES=6
REPLICAS=1

# Computed vars
ENDPORT=$((PORT+NODES))

while [ $((PORT < ENDPORT)) != "0" ]; do
	PORT=$((PORT+1))
	echo "Starting $PORT"
	sudo redis-server --port $PORT --cluster-enabled yes --cluster-config-file nodes-${PORT}.conf --cluster-node-timeout $TIMEOUT --logfile ${PORT}.log --daemonize yes --protected-mode no
done

PORT=6999
HOSTS=""
while [ $((PORT < ENDPORT)) != "0" ]; do
	PORT=$((PORT+1))
	HOSTS="$HOSTS 127.0.0.1:$PORT"
done
sudo redis-cli --cluster create $HOSTS --cluster-replicas $REPLICAS

