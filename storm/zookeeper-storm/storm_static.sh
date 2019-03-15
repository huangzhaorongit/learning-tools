#!/bin/bash

VARS="${VARS} ANSIBLE_SCP_IF_SSH=y ANSIBLE_HOST_KEY_CHECKING=False"
VAGRANT_OPTS='--private-key=~/.vagrant.d/insecure_private_key --timeout=20'
ansible-playbook -vv ${VAGRANT_OPTS} -f 20 -i inventory/static provisioning/playbooks/storm.yml
