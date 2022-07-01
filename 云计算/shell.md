# 变量

> 简单使用

```bash
[root@server1 ~]# key_1=123+123
[root@server1 ~]# echo $key_1
123+123
```

> 获得返回值

```bash
[root@server1 tmp]# cat test.sh 
#!/bin/bash

echo test

exit 1

[root@server1 tmp]# ./test.sh 
test
[root@server1 tmp]# echo $?
1
```

> 用语句赋值

```bash
[root@server1 tmp]# key_2=`date | awk -F "[ ]+" '{print $5}'` 
[root@server1 tmp]# echo $key_2
15:47:21
```

> 特殊变量

```bash
$1,$2,…：对应调用第1，第2等参数 
$0：命令本身 
$*：传递给脚本的所有参数（把所有参数当作整体） 
$@：传递给脚本的所有参数 
$#：传递给脚本的参数的个数
```

# 数组

```bash
[root@server1 tmp]# array_1=(1 2 3)
[root@server1 tmp]# echo ${array_1[0]}
1
[root@server1 tmp]# echo ${array_1[1]}
2
[root@server1 tmp]# echo ${array_1[2]}
3
[root@server1 tmp]# array_1[3]=4
[root@server1 tmp]# echo ${array_1[3]}
4
```

> 获得数组个数

```bash
[root@server1 tmp]# echo ${#array_1[@]}
4
```

# 数学运算

```bash
[root@server1 tmp]# let mathTest=2*3
[root@server1 tmp]# echo $mathTest 
6
```

