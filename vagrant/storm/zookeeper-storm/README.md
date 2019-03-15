ansible-kafka
---------
This Ansible playbook will build a Storm cluster with Zookeeper based on https://github.com/hpcloud-mon/ansible-storm with a few modifications to work on ancient centos 6.8. Upstart configs had to be changed. 


You can pre-build a Rackspace cloud environment or run the playbook against an existing environment.

## [Requirements] (id:requirements)

- Ansible >= 2.0.

- Expects RHEL/CentOS 6

## [Features] (id:features)

- It installs Zookeeper 

- It supports static inventory if the environment is pre-built (`inventory/static` file).

- The `id` is automatically set (on the inventory items in alphabetical order).

- It includes init scripts for both Zookeeper and upstart config for Storm components


## [Inventory] (id:inventory)

- When provisioning Kafka on existing infrastructure edit `inventory/static` and add the nodes.


## [Configuration] (id:configuration)

To customize, change the variables under `playbooks/group_vars` and `playbooks/roles` folders:

1. **playbooks/group_vars/all**: contains cluster and cloud settings
1. **playbooks/roles/zookeeper/defaults/main.yml**: Zookeeper specific settings
1. **playbooks/roles/storm/defaults/main.yml**: Storm specific settings

(This is from https://github.com/hpcloud-mon/ansible-storm )


For a one-node cluster, set `nodes_count` in `playbooks/group_vars/all` to 1.


## [Scripts] (id:scripts)

- For static / prebuilt environments:
  ````
  bash stormn_static.sh
  
  ````
## Vagrant

Tested on Mac OS. Windows setup for vagrant is not possible except from another linux VM or docker container. 

Install vagrant from https://www.vagrantup.com/

Install VirtualBox from https://www.virtualbox.org/wiki/Downloads

Install Ansible with pip (see http://docs.ansible.com/ansible/intro_installation.html#latest-releases-via-pip)

To bring up three centos nodes with vagrant type


```
Vagrant up
```
The nodes will boot up and be 'provisioned' with provisioning/storm.yml

TODO: dynamic host inventory is not working yet


When ansible is finished you should be able to browse to http://192.168.33.23:8088/ to see the storm UI and everything running nicely
