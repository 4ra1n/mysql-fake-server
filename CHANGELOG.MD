# CHANGELOG

## 0.0.1

init

## 0.0.2

- 处理payload包含_字符的BUG
- 允许参数Base64输入，防止特殊情况
- GUI版停止后再次启动存在BUG
- 命令行版本优化输出部分，添加HELP信息
- 处理非主要功能的BUG
- 自动检查新版本并提示更新

## 0.0.3

支持了自定义反序列化payload功能，内部仅保留最常见的链，其他情况自行导入

- 希望支持yso自定义 #3
- 支持调用自己的yso #2
- 命令行版本也支持了自定义序列化数据
- 修复了dockerfile版本问题
- 删除了基本不会使用的rpc模块
- 完善README信息

## 0.0.4

支持了两种JDBC的利用：`PgSQL`和`Apache Derby`

- 重要更新：支持了PgSQL RCE的利用Server
- 重要更新：支持了Apache Derby利用Slave功能的RCE
- 优化依赖减小体积，使用更标准的Maven插件打包配置
- 修复了某些情况下日志输出的问题
- 恢复日志模式到INFO级别避免打印过多无意义信息
- 对UI做了一些小改善

## 0.0.5