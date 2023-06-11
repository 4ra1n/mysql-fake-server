# MySQL Fake Server

![](https://img.shields.io/badge/build-passing-brightgreen)
![](https://img.shields.io/badge/build-Java%208-orange)
![](https://img.shields.io/github/downloads/4ra1n/mysql-fake-server/total)
![](https://img.shields.io/github/v/release/4ra1n/mysql-fake-server)

[English Version](doc/README.md)

## 0x00 介绍

该项目是 [MySQL_Fake_Server](https://github.com/fnmsd/MySQL_Fake_Server) 高级版

当`JDBC URL`可控时，特殊的`MySQL`服务端可以读取`JDBC`客户端任意文件或执行反序列化操作

完全使用`Java`实现部分`MySQL`协议，内置常见`ysoserial`链，一键启动，自动生成可用的`payload`用于测试

参考[MySQL_Fake_Server](https://github.com/fnmsd/MySQL_Fake_Server)项目，`payload`从`user`参数传递。反序列化应以`deser_`开头，规则为`deser_[gadget]_[cmd]`；文件读取以`fileread_`开头，规则为`fileread_[name]`

由于某些文件名或命令存在特殊字符，支持使用`base64`传递方式，方式为原有`user`基础上进行`base64`并以`base64`开头，例如`user=deser_CB_calc.exe`等于`user=base64ZGVzZXJfQ0JfY2FsYy5leGU=`

默认文件保存在当前目录的`fake-server-files`下的当前时间戳目录内（自动创建目录）

## 0x01 GUI

使用`GUI`版本一键启动，启动后可以根据自己的环境输入参数，生成`payload`

启动：`java -jar fake-mysql-gui.jar`

![](img/001.png)

## 0x02 CLI

当你的环境不允许使用`GUI`版时，可以使用命令行版启动，同样可以使用`GUI`辅助生成`payload`

启动：`java -jar fake-mysql-cli.jar -p [port]`

![](img/002.png)

## 0x03 Docker

构建：`docker build -t fake-mysql-server .`

启动：`docker run -p 3306:3306 -d fake-mysql-server`

![](img/003.png)

## 0x04 RPC

本项目提供了`GRPC`调用的方式

启动：`java -jar fake-mysql-rpc.jar`（默认启动端口9999）

proto:

```protobuf
syntax = "proto3";

option java_package = "me.n1ar4.fake.rpc";
option java_outer_classname = "FakeServerRPC";
option java_multiple_files = true;

package me.n1ar4.fake.rpc;

service RPCStart {
  rpc start (RPCNull) returns (RPCResp) {}
  rpc stop (RPCPort) returns(RPCResp) {}
}

message RPCNull{}

message RPCResp {
  string status = 1;
  int32 port = 2;
}

message RPCPort {
  int32 port = 1;
}
```

## 0x05 其他

怎样测试：

```java
    String url = "jdbc:mysql://...";
    try {
        Class.forName("com.mysql.jdbc.Driver");
        // Class.forName("com.mysql.cj.jdbc.Driver");
        DriverManager.getConnection(url);
    } catch (Exception e) {
        e.printStackTrace();
    }
```

## 0x06 免责申明

本项目仅面向安全研究与学习，禁止任何非法用途

如您在使用本项目的过程中存在任何非法行为，您需自行承担相应后果

除非您已充分阅读、完全理解并接受本协议，否则，请您不要使用本项目

## 0x07 致谢与参考

- https://github.com/frohoff/ysoserial
- https://github.com/fnmsd/MySQL_Fake_Server
- https://pyn3rd.github.io/2022/06/06/Make-JDBC-Attacks-Brillian-Again-I/
