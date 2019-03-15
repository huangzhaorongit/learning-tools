# -*- mode: ruby -*-
# vi: set ft=ruby :

VAGRANTFILE_API_VERSION = "2"
unless Vagrant.has_plugin?("vagrant-hostmanager")
  raise 'vagrant-hostmanager plugin is not installed! https://github.com/devopsgroup-io/vagrant-hostmanager'
end


boxes = [
    {
        :name => "vm1",
        :eth1 => "192.168.33.23",
        :mem => "1024",
        :cpu => "1"
    },
    {
        :name => "vm2",
        :eth1 => "192.168.33.24",
        :mem => "1024",
        :cpu => "1"
    },
    {
        :name => "vm3",
        :eth1 => "192.168.33.25",
        :mem => "1024",
        :cpu => "1"
    }
]


Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

  config.hostmanager.enabled = true
  config.hostmanager.manage_host = false
  config.hostmanager.manage_guest = true
  config.hostmanager.ignore_private_ip = false

  boxes.each_with_index do |opts, index|
    config.vm.define opts[:name] do |config|
      config.vm.box = 'centos/6'
      config.ssh.insert_key = false
      config.vm.hostname = opts[:name]
      config.vm.provider "virtualbox" do |v|
        v.name = opts[:name]
        v.customize ["modifyvm", :id, "--memory", opts[:mem]]
        v.customize ["modifyvm", :id, "--cpus", opts[:cpu]]

        v.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
        v.customize ["modifyvm", :id, "--ioapic", "on"]
      end
      config.vm.network :private_network, ip: opts[:eth1]

      # forward ports on 1st box
      # forward ports on 1st box
      if index == 0
        config.vm.network "forwarded_port", guest: 8088, host: 18080
        config.vm.network "forwarded_port", guest: 2181, host: 12181
      end

      #TODO this is work in progress ....

      if index == boxes.size - 1

        config.vm.provision "ansible" do |ansible|
          ansible.playbook = "provisioning/playbooks/storm.yml"
          ansible.groups = {
              "storm-nodes" => ["vm[1:2:3]"],
          }
          ansible.limit = "all"
          ansible.inventory_path = "provisioning/inventory"
        end
      end
    end
  end

end