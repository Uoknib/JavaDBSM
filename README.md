# JavaDatabase
使用Java实现一个简单的数据库管理系统.
#  statement
代码是在VS Code编辑的，应该任一文本编辑器都可以正常打开
这是我的第一个Java语言写的项目，如项目描述所言本项目是基于machi学长的MyDatabase实现的
# essentials
优化源码逻辑，优化运行逻辑
实现了用户登入，创建用户，授予用户权限等多用户操作
实现了创建，删除库表用户的日志文件生成
等
# supplement
上传github主要是留一个源代码地址，学习交流

### Other Notes
## 源代码文件说明
源代码包括两个文件夹javaDBSM与diskCAT
## javaDBSM
包含两个文件夹AllCatTest与src
AllCatTest为项目的工具类测试test
src文件夹下的子目录zcmu\cat为项目java源码文件夹
## diskCAT
diskCAT文件夹模拟数据库存储空间
diskCAT文件夹中，文件夹模拟数据库，其中数据库文件夹内的文本文件.txt模拟数据表；
而diskCAT路径下的文本文件.txt模拟用户信息文件
# text file
当前路径C:\Users\desktop\diskCAT，默认数据库根路径
其中 four , oo , two 为三个数据库，其路径下的.txt文件为表
username.txt 为用户，其中 zcmucatcat.txt 为管理员用户root
username加上logfile.txt，如zcmucatcatlogfile.txt为zcmucatcat用户的日志文件
记录了用户zcmucatcat的操作
