#!/usr/bin/env bash

sudo redis-stat localhost:7000 localhost:7001 localhost:7002 localhost:7003 localhost:7004 localhost:7005 --server=8080 --verbose 1  --daemon

