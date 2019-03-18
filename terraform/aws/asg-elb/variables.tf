variable "user_region" {
  type        = "string"
  description = "AWS region in which all resources will be created"
  default     = "us-east-1"
}

variable "keypair" {
  type        = "string"
  description = "AWS SSH keypair to use to connect to instances"
  default     = "~/.ssh/id_rsa.vagrantaws"
}

variable "flavor" {
  type        = "string"
  description = "AWS type to use when creating instances"
  default     = "t2.micro"
}

variable "secgrp" {
  type        = "string"
  description = "ID of VPC security group to use"
  default     = "vpc-0f729099a559a446a"
}
