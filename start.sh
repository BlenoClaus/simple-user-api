#!/bin/sh
java -jar ./discovery/target/discovery.jar &
java -jar ./gateway/target/gateway.jar &
java -jar ./user/target/user.jar &