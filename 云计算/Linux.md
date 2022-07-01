# 基本命令

## cal

``` bash
[root@localhost ~]# cal
     十一月 2021
日 一 二 三 四 五 六
    1  2  3  4  5  6
 7  8  9 10 11 12 13
14 15 16 17 18 19 20
21 22 23 24 25 26 27
28 29 30
```

## ls

> 左侧一栏 第一个字符可以看出文件类型 d为文件夹 -为文件

``` bash
[root@localhost ~]# ls -l -a
总用量 24
dr-xr-x---.  2 root root  114 11月 21 22:23 .
dr-xr-xr-x. 17 root root  224 11月 21 22:22 ..
-rw-------.  1 root root 1241 11月 21 22:23 anaconda-ks.cfg
-rw-r--r--.  1 root root   18 12月 29 2013 .bash_logout
-rw-r--r--.  1 root root  176 12月 29 2013 .bash_profile
-rw-r--r--.  1 root root  176 12月 29 2013 .bashrc
-rw-r--r--.  1 root root  100 12月 29 2013 .cshrc
-rw-r--r--.  1 root root  129 12月 29 2013 .tcshrc
```

> 根据时间排序，新的在上

``` bash
[root@localhost ~]# ls -l -a -t
总用量 24
-rw-------.  1 root root 1241 11月 21 22:23 anaconda-ks.cfg
dr-xr-x---.  2 root root  114 11月 21 22:23 .
dr-xr-xr-x. 17 root root  224 11月 21 22:22 ..
-rw-r--r--.  1 root root   18 12月 29 2013 .bash_logout
-rw-r--r--.  1 root root  176 12月 29 2013 .bash_profile
-rw-r--r--.  1 root root  176 12月 29 2013 .bashrc
-rw-r--r--.  1 root root  100 12月 29 2013 .cshrc
-rw-r--r--.  1 root root  129 12月 29 2013 .tcshrc
```

> 文件大小显示合适的单位

``` bash
[root@localhost ~]# ls -l -a -h
总用量 24K
dr-xr-x---.  2 root root  114 11月 21 22:23 .
dr-xr-xr-x. 17 root root  224 11月 21 22:22 ..
-rw-------.  1 root root 1.3K 11月 21 22:23 anaconda-ks.cfg
-rw-r--r--.  1 root root   18 12月 29 2013 .bash_logout
-rw-r--r--.  1 root root  176 12月 29 2013 .bash_profile
-rw-r--r--.  1 root root  176 12月 29 2013 .bashrc
-rw-r--r--.  1 root root  100 12月 29 2013 .cshrc
-rw-r--r--.  1 root root  129 12月 29 2013 .tcshrc
```

> 短命令可以写在一起

```bash
[root@localhost ~]# ls -la 
总用量 24
dr-xr-x---.  2 root root  114 11月 21 22:23 .
dr-xr-xr-x. 17 root root  224 11月 21 22:22 ..
-rw-------.  1 root root 1241 11月 21 22:23 anaconda-ks.cfg
-rw-r--r--.  1 root root   18 12月 29 2013 .bash_logout
-rw-r--r--.  1 root root  176 12月 29 2013 .bash_profile
-rw-r--r--.  1 root root  176 12月 29 2013 .bashrc
-rw-r--r--.  1 root root  100 12月 29 2013 .cshrc
-rw-r--r--.  1 root root  129 12月 29 2013 .tcshrc
```

## file

> 也可以用来查看文件类型

```bash
[root@server1 ~]# file file
file: ASCII text
```

## history

> 显示最近运行的命令

```bash
[root@localhost bin]# history
    1  yum -y install nano 
    2  ls
    3  cd hosts
    4  rm -f *
    5  touch file
    6  nano file
    7  cat file
    8  less file
    9  grep file
   10  grep test  file
   11  vi file

```

> 删除某个历史命令

```bash
[root@localhost bin]# history -d 1
[root@localhost bin]# history
    1  ls
    2  cd hosts
    3  rm -f *
    4  touch file
```

> 引用上个命令的参数:!$

> 快捷键:!

## mkdir

> 生成文件夹

``` bash
[root@localhost ~]# mkdir testDir
[root@localhost ~]# ls -la
总用量 24
dr-xr-x---.  3 root root  129 11月 22 16:01 .
dr-xr-xr-x. 17 root root  224 11月 21 22:22 ..
-rw-------.  1 root root 1241 11月 21 22:23 anaconda-ks.cfg
-rw-r--r--.  1 root root   18 12月 29 2013 .bash_logout
-rw-r--r--.  1 root root  176 12月 29 2013 .bash_profile
-rw-r--r--.  1 root root  176 12月 29 2013 .bashrc
-rw-r--r--.  1 root root  100 12月 29 2013 .cshrc
-rw-r--r--.  1 root root  129 12月 29 2013 .tcshrc
drwxr-xr-x.  2 root root   22 11月 22 16:02 testDir
```

## touch

> 生成文件

``` bash
[root@localhost testDir]# touch testFile
[root@localhost testDir]# ls -la
总用量 0
drwxr-xr-x. 2 root root  22 11月 22 16:02 .
dr-xr-x---. 3 root root 129 11月 22 16:01 ..
-rw-r--r--. 1 root root   0 11月 22 16:02 testFile
```

## cd

> 到某个文件夹

```bash
[root@localhost ~]# cd testDir
[root@localhost testDir]#
```

## pwd

> 显示当前的工作目录

```bash
[root@localhost testDir]# pwd
/root/testDir
```

## clear

> 清除屏幕

## echo

> 打印，-e翻译转义字符

```bash
[root@localhost testDir]# echo -e "test\ntest"
test
test
```

## help

``` bash
[root@localhost testDir]# help echo
echo: echo [-neE] [参数 ...]
    将参数写到标准输出。

    在标准输出上显示 ARG 参数后跟一个换行。

    选项：
      -n        不要追加换行
      -e        启用下列反斜杠转义的解释
      -E        显式地抑制对于反斜杠转义的解释

    `echo' 对下列反斜杠字符进行转义：
      \a        警告（响铃）
      \b        退格
      \c        抑制更多的输出
      \e        转义字符
      \f        格式提供
      \n        换行
      \r        回车
      \t        横向制表符
      \v        纵向制表符
      \\        反斜杠
      \0nnn     以 NNN （八进制）为 ASCII 码的字符。 NNN 可以是
        0到3个八进制数字
      \xHH      以 HH （十六进制）为值的八比特字符。HH可以是
        一个或两个十六进制数字

    退出状态：
    返回成功除非有写错误发生。
```

## alias

> 查看别名

```bash
[root@localhost testDir]# alias
alias cp='cp -i'
alias egrep='egrep --color=auto'
alias fgrep='fgrep --color=auto'
alias grep='grep --color=auto'
alias l.='ls -d .* --color=auto'
alias ll='ls -l --color=auto'
alias ls='ls --color=auto'
alias mv='mv -i'
alias rm='rm -i'
alias which='alias | /usr/bin/which --tty-only --read-alias --show-dot --show-tilde'
```

> 设置别名

```bash
[root@localhost testDir]# alias ?="help"
[root@localhost testDir]# ?
GNU bash， 版本 4.2.46(2)-release (x86_64-redhat-linux-gnu)
这些 shell 命令是内部定义的。请输入 `help' 以获取一个列表.
输入 `help 名称' 以得到有关函数`名称'的更多信息.
使用 `info bash' 来获得关于 shell 的更多一般性信息
使用 `man -k' 或 `info' 来获取不在列表中的命令的更多信息.

名称旁边的星号 (*) 意味着该命令被禁用.

 job_spec [&]                            history [-c] [-d 偏移量] [n] 或 >
 (( 表达式 ))                         if 命令; then 命令; [ elif 命▒>
 . 文件名 [参数]                    jobs [-lnprs] [任务声明 ...] 或>
 :                                       kill [-s 信号声明 | -n 信号编>
 [ 参数... ]                           let 参数 [参数 ...]
 [[ 表达式 ]]                         local [option] 名称[=值] ...
 alias [-p] [名称[=值] ... ]          logout [n]
 bg [任务声明 ...]                   mapfile [-n 计数] [-O 起始序号>
 bind [-lpvsPVS] [-m 键映射] [-f ▒>  popd [-n] [+N | -N]
 break [n]                               printf [-v var] 格式 [参数]
 builtin [shell 内嵌 [参数 ...]]     pushd [-n] [+N | -N | 目录]
 caller [表达式]                      pwd [-LP]
 case 词 in [模式 [| 模式]...) ▒>  read [-ers] [-a 数组] [-d 分隔▒>
 cd [-L|[-P [-e]]] [dir]                 readarray [-n 计数] [-O 起始序▒>
 command [-pVv] 命令 [参数 ...]      readonly [-aAf] [name[=value] ...] o>
 compgen [-abcdefgjksuv] [-o 选项]  >  return [n]
 complete [-abcdefgjksuv] [-pr] [-DE] >  select NAME [in 词语 ... ;] do 命>
 compopt [-o|+o 选项] [-DE] [名称 >  set [-abefhkmnptuvxBCHP] [-o option->
 continue [n]                            shift [n]
 coproc [名称] 命令 [重定向]      shopt [-pqsu] [-o] [选项名 ...]
 declare [-aAfFgilrtux] [-p] [name[=va>  source 文件名 [参数]
 dirs [-clpv] [+N] [-N]                  suspend [-f]
 disown [-h] [-ar] [任务声明 ...]    test [表达式]
 echo [-neE] [参数 ...]                time [-p] 管道
 enable [-a] [-dnps] [-f 文件名] [▒>  times
 eval [参数 ...]                       trap [-lp] [[参数] 信号声明 ..>
 exec [-cl] [-a 名称] [命令 [参▒>  真
 exit [n]                                type [-afptP] 名称 [名称 ...]
 export [-fn] [名称[=值] ...] 或 e>  typeset [-aAfFgilrtux] [-p] name[=va>
 伪                                     ulimit [-SHacdefilmnpqrstuvx] [限▒>
 fc [-e 编辑器名] [-lnr] [起始] >  umask [-p] [-S] [模式]
 fg [任务声明]                       unalias [-a] 名称 [名称 ...]
 for 名称 [in 词语 ... ] ; do 命▒>  unset [-f] [-v] [名称 ...]
 for (( 表达式1; 表达式2; 表达>  until 命令; do 命令; done
 function 名称 { 命令 ; } 或 name>  variables - 一些 shell 变量的▒>
 getopts 选项字符串 名称 [参▒>  wait [编号]
 hash [-lr] [-p 路径名] [-dt] [名▒>  while 命令; do 命令; done
 help [-dms] [模式 ...]                { 命令 ; }
```

## unalias

> 取消别名

```bash
[root@localhost bin]# unalias test
```

## cp

> 复制 -r递归 很多操作里面也有-r 可理解为递归地做xxx

```bash
[root@localhost ~]# cp -r testDir/ newDir
[root@localhost ~]# ls
anaconda-ks.cfg  newDir  testDir
[root@localhost ~]# cd newDir
[root@localhost newDir]# ls
testFile
```

## mv

> 移动

```bash
[root@localhost testDir]# mv test ..
mv：是否覆盖"../test"？ y
```

## rm

> 删除

```bash
-f，--force
          忽略不存在的文件，并且从不向用户提示。

-i，--interactive
          提示是否移除每个文件。如果回答是否定的，文件将被跳过。

-r，-R，--recursive
          递归地移除目录中的内容。

-v，--verbose
          在移除每个文件之前打印其名称。
```



```bash
[root@localhost ~]# ls
anaconda-ks.cfg  newDir  test  testDir
[root@localhost ~]# rm -f *
rm: 无法删除"newDir": 是一个目录
rm: 无法删除"testDir": 是一个目录
[root@localhost ~]# ls
newDir  testDir
[root@localhost ~]# rm -f -r *
[root@localhost ~]# ls
[root@localhost ~]#
```

## cat

> 查看文件

```bash
[root@localhost ~]# cat file
testtest
```

## less

> 查看文件，支持翻页。

## head

> 查看文件，只看头部内容。

## tail

> 查看文件，只看尾部内容。

## grep

> 查找指定内容所在行

```bash
[root@localhost ~]# grep test  file
testtest
```

## stat

> 查看文件状态

```bash
[root@localhost ~]# stat file
  文件："file"
  大小：20              块：8          IO 块：4096   普通文件
设备：fd00h/64768d      Inode：33574988    硬链接：1
权限：(0644/-rw-r--r--)  Uid：(    0/    root)   Gid：(    0/    root)
环境：unconfined_u:object_r:admin_home_t:s0
最近访问：2021-11-23 18:36:39.280178095 +0800
最近更改：2021-11-23 18:36:36.069181514 +0800
最近改动：2021-11-23 18:36:36.070181513 +0800
创建时间：-
```

## which

> 在环境变量中查询

```bash
[root@localhost ~]# which file
/usr/bin/file
```

## locate

> 在文件数据库中搜索

```bash
[root@localhost ~]# updatedb
[root@localhost ~]# locate fileTest
/root/fileTest
```

## find

> 全盘搜索

```bash
[root@localhost ~]# find . -iname fileTest
./fileTest
```

## who

> 查看用户信息

# 管道和重定向

> 1. 重定向和管道符都有改变数据输入输出的作用
> 2. 重定向操作符 > 是将命令和文件连接起来，用文件来接受命令的输出
> 3. 管道符 | 是将命令和命令连接起来，用上一个命令的输出作为下一个命令的输入

## 管道

> 输出内容为下一个命令的输入内容

```bash
[root@server1 ~]# cat fileTest
this is fileTest
that is fileTest
[root@server1 ~]# cat fileTest | grep that
that is fileTest
```

## 配合管道的一些命令

### tr

> translate

> -d 用作删除

> 使用场景:保存用户信息的最后一行到who.out

```bash
[root@server1 ~]# who | tail -n 1 > who.out
[root@server1 ~]# cat who
cat: who: 没有那个文件或目录
[root@server1 ~]# cat who.out
root     pts/1        2022-01-12 11:39 (192.168.111.1)
```

### wc

```bash
SYNOPSIS 总览
       wc [选项列表]... [文件名列表]...

DESCRIPTION 描述
       对每个文件输出行、单词、和字节统计数，如果指定了多于一个文件则还有一 个行数的总计。没有指定文件或指定的文件是 -，则读取标准输入。

       -c, --bytes, --chars
              输出字节统计数。

       -l, --lines
              输出换行符统计数。

       -L, --max-line-length
              输出最长的行的长度。

       -w, --words
              输出单词统计数。

       --help 显示帮助并退出

       --version
              输出版本信息并退出
```

```bash
[root@server1 ~]# wc fileTest
1 2 9 fileTest
[root@server1 ~]# cat fileTest 
cat file
```

### cut

> 截取每行的第三个字符

```bash
[root@server1 ~]# cat fileTest 
hello
world
Test
[root@server1 ~]# cat fileTest | cut -c 3
l
r
s
```

> 配合分割

```bash
[root@server1 ~]# cat fileTest | cut -d ' ' -f 2
test2
[root@server1 ~]# cat fileTest
test1 test2 test3
```

### sort

> 行不变 改变行间顺序

```bash
[root@server1 ~]# cat fileTest
test1 test2 test3
abc
123
[root@server1 ~]# sort fileTest
123
abc
test1 test2 test3
```

### uniq

```bash
[root@server1 ~]# cat fileTest
abc
abc
123
123
test
[root@server1 ~]# cat fileTest | uniq -u
test
[root@server1 ~]# cat fileTest | uniq -c
      2 abc
      2 123
      1 test
```



## 重定向

> 重写覆盖

```bash
[root@server1 ~]# cat file
test
test
[root@server1 ~]# cat > file <<EOF
> TEST
> TEST
> EOF
[root@server1 ~]# cat file
TEST
TEST
```

> 追加

```bash
[root@server1 ~]# cat file
TEST
TEST
[root@server1 ~]# cat >> file <<EOF
> test
> EOF
[root@server1 ~]# cat file
TEST
TEST
test
[root@server1 ~]# 
```

# 用户权限

> Linux中用户信息都是用文件的方式保存

> 用户文件 /etc/passwd
>
> 用户密码/etc/shadow
>
> 创建用户时会自动复制到家目录的文件:/etc/skel

> 用户名: x:uid:gid:描述:家目录:shell目录
>
> 注意：shell目录为nologin的时候一般是这个用户负责的是一些服务，防止黑客登录。

```bash
root:x:0:0:root:/root:/bin/bash
bin:x:1:1:bin:/bin:/sbin/nologin
daemon:x:2:2:daemon:/sbin:/sbin/nologin
adm:x:3:4:adm:/var/adm:/sbin/nologin
lp:x:4:7:lp:/var/spool/lpd:/sbin/nologin
sync:x:5:0:sync:/sbin:/bin/sync
shutdown:x:6:0:shutdown:/sbin:/sbin/shutdown
halt:x:7:0:halt:/sbin:/sbin/halt
mail:x:8:12:mail:/var/spool/mail:/sbin/nologin
operator:x:11:0:operator:/root:/sbin/nologin
games:x:12:100:games:/usr/games:/sbin/nologin
ftp:x:14:50:FTP User:/var/ftp:/sbin/nologin
nobody:x:99:99:Nobody:/:/sbin/nologin
systemd-network:x:192:192:systemd Network Management:/:/sbin/nologin
dbus:x:81:81:System message bus:/:/sbin/nologin
polkitd:x:999:998:User for polkitd:/:/sbin/nologin
sshd:x:74:74:Privilege-separated SSH:/var/empty/sshd:/sbin/nologin
postfix:x:89:89::/var/spool/postfix:/sbin/nologin
chrony:x:998:996::/var/lib/chrony:/sbin/nologin
user02:x:1001:1001::/home/user02:/bin/bash
ivan:x:1002:10::/home/ivan:/bin/bash
user01:x:1003:1003::/home/user01:/bin/bash
userTest:x:1004:1004::/home/userTest:/bin/bash
```

## id

> 用户信息

```bash
[root@localhost ~]# id
uid=0(root) gid=0(root) 组=0(root) 环境=unconfined_u:unconfined_r:unconfined_t:s0-s0:c0.c1023
```

## useradd

> 添加用户

```bash
[root@localhost ~]# useradd user01
[root@localhost ~]# passwd user01
更改用户 user01 的密码 。
新的 密码：
无效的密码： 密码是一个回文
重新输入新的 密码：
passwd：所有的身份验证令牌已经成功更新。
[root@localhost ~]# id -u user01
1000
```

## userdel

> 删除用户并删除他的家目录

```bash
[root@server1 etc]# userdel -r userTest
userdel：/var/spool/mail/userTest 并不属于 userTest，所以不会删除
```



## groupadd

> 添加组

```bash
[root@localhost ~]# groupadd testGroup -g 6666
[root@localhost ~]# tail -n 1 /etc/group
testGroup:x:6666:
```

## gpasswd

```bash
[root@localhost ~]# gpasswd -a user01 testGroup
正在将用户“user01”加入到“testGroup”组中
[root@localhost ~]# tail -n 1 /etc/group
testGroup:x:6666:user01
```

```bash
[root@server1 etc]# id user01
uid=1000(user01) gid=1000(user01) 组=1000(user01),10(wheel)
[root@server1 etc]# gpasswd -d user01 wheel
正在将用户“user01”从“wheel”组中删除
[root@server1 etc]# id user01
uid=1000(user01) gid=1000(user01) 组=1000(user01)
```

## usermod

```bash
[root@localhost ~]# usermod -u 1001 user01
[root@localhost ~]# tail -n 1 /etc/passwd
user01:x:1001:1000::1001:/bin/bash
```

```bash
[root@localhost ~]# usermod -d /test user01
```

## passwd

```bash
[root@localhost home]# passwd userTest
更改用户 userTest 的密码 。
新的 密码：
无效的密码： 密码是一个回文
重新输入新的 密码：
passwd：所有的身份验证令牌已经成功更新。
```

## chage

```bash
[root@localhost ~]# chage -d 0 ivan
```

```bash
Last login: Wed Nov 24 17:22:16 2021
WARNING: Your password has expired.
You must change your password now and login again!
更改用户 ivan 的密码 。
为 ivan 更改 STRESS 密码。
（当前）UNIX 密码：
```

## sudo

> 过程:在sudoers文件里面确定用户是否有权限，然后让用户输入密码确定

```bash
[root@localhost ~]# usermod -g wheel ivan
[root@localhost ~]# id ivan
uid=1002(ivan) gid=10(wheel) 组=10(wheel)
[ivan@localhost ~]$ su - ivan
密码：
警告：您的密码将在 0 天后过期
上一次登录：三 11月 24 17:33:49 CST 2021tty1 上
[ivan@localhost ~]$ sudo touch f
```

```bash
[ivan@localhost ~]$ sudo -l
匹配 %2$s 上 %1$s 的默认条目：
    !visiblepw, always_set_home, match_group_by_gid, always_query_group_plugin, env_reset, env_keep="COLORS DISPLAY HOSTNAME HISTSIZE
    KDEDIR LS_COLORS", env_keep+="MAIL PS1 PS2 QTDIR USERNAME LANG LC_ADDRESS LC_CTYPE", env_keep+="LC_COLLATE LC_IDENTIFICATION
    LC_MEASUREMENT LC_MESSAGES", env_keep+="LC_MONETARY LC_NAME LC_NUMERIC LC_PAPER LC_TELEPHONE", env_keep+="LC_TIME LC_ALL LANGUAGE
    LINGUAS _XKB_CHARSET XAUTHORITY", secure_path=/sbin\:/bin\:/usr/sbin\:/usr/bin

用户 ivan 可以在 localhost 上运行以下命令：
    (ALL) ALL
    (ALL) NOPASSWD: ALL
```

# 文件权限

## chown

> 改变属主或属组

```bash
[root@localhost ivan]# ll
总用量 0
-rw-r--r--. 1 root user02 0 11月 24 19:56 file
[root@localhost ivan]# chown ivan.wheel file
[root@localhost ivan]# ll
总用量 0
-rw-r--r--. 1 ivan wheel 0 11月 24 19:56 file
```

## 基本权限

> 对创建者 组用户 其他人的权限
>
> r w x三种权限 读 写 可执行
>
> 1代表有0代表没有 可以得到0-7种权限 每个分类的人又有一种权限 比如：755 rwx r-x r-x

## chmod

> 更改文件权限
>
> a:全体用户
>
> u:创建者
>
> g:组用户
>
> o:其它人
>
> +:加上权限
>
> -:减去权限
>
> =:权限赋值

```bash
[root@server1 test]# ll testFile
-rw-r--r--. 1 root root 0 1月  12 17:07 testFile
[root@server1 test]# chmod 755 testFile
[root@server1 test]# ll testFile
-rwxr-xr-x. 1 root root 0 1月  12 17:07 testFile
[root@server1 test]# ll testFile
-rw-r--r--. 1 root root 0 1月  12 17:07 testFile
[root@server1 test]# chmod g=rwx testFile
[root@server1 test]# ll testFile
-rw-rwxr--. 1 root root 0 1月  12 17:07 testFile
```

## setfacl

> 针对某个用户的权限限制

```bash
[root@server1 test]# setfacl -m u:user01:rwx testFile
```

> 删除权限

```bash
[root@localhost home]# setfacl -x u:user01 testFile
```

## getfacl

```bash
[root@server1 test]# getfacl testFile
# file: testFile
# owner: root
# group: root
user::rw-
user:user01:rwx
group::rwx
mask::rwx
other::rw-
```

## mask

> 决定了用户的最高权限

> 设置mask

```bash
[root@server1 test]# setfacl -m m:--- testFile
[root@server1 test]# getfacl testFile
# file: testFile
# owner: root
# group: root
user::rw-
user:user01:rwx			#effective:---
group::rwx			#effective:---
mask::---
other::rw-
```

> 

## umask

> 当前线程用户的创建的文件或目录的权限会减去umask的权限
>
> 四个数字分别代表 特殊权限 属主权限 属组权限 其它人权限

```bash
[root@server1 test]# umask 0777
[root@server1 test]# umask -S
u=,g=,o=
[root@server1 test]# touch file
[root@server1 test]# ll file
----------. 1 root root 0 1月  12 19:12 file

```

## default

> 针对目录 目录生成的文件的默认权限

```bash
[root@server1 test]# setfacl -m d:u::000 testacldir/
[root@server1 test]# getfacl testacldir/
# file: testacldir/
# owner: root
# group: root
user::rwx
group::r-x
other::rwx
default:user::---
default:user:centos:---
default:group::r-x
default:mask::r-x
default:other::r-x

[centos@server1 testacldir]$ ll
总用量 4
-rw-r--r--+ 1 centos centos 5 1月  12 19:29 file # +说明继承了default设定的权限
----r--r--+ 1 centos centos 0 1月  12 19:29 test

[centos@server1 testacldir]$ cat test
cat: test: 权限不够
```



## 特殊权限

### suid

> 对文件命令操作的时候，用的是命令所有者的权限
>
> 比如使用passwd命令的时候 是对shadow这个文件进行操作 但是shadow的权限是000 但是用户通过passwd获得到root的权限 （因为passwd的权限是s） 因此能够修改自己的密码

```bash
[root@server1 test]# ll /usr/bin/passwd /etc/passwd
-rw-r--r--. 1 root root   932 1月  12 19:00 /etc/passwd
-rwsr-xr-x. 1 root root 27832 6月  10 2014 /usr/bin/passwd

[root@server1 test]# chmod u+s file
[root@server1 test]# ll
总用量 4
---S--S---. 1 root root  0 1月  12 19:12 file
```

### sgid

> 借出文件用户组的权限

```bash
[root@server1 test]# chmod u+s file
```

### sbit

> 共享目录 只有root和属主能修改删除文件 其它人只能读

```bash
[root@server1 test]# chmod o+t testsbit/
[root@server1 test]# ll -d testsbit/
drwxr-xr-t. 2 root root 6 1月  12 21:32 testsbit/
```

# 进程

## ps

> 查看进程
>
> a:与终端相关
>
> x:与终端无关
>
> u:以用户相关显示
>
> user:进程的使用者
>
> pid:进程id
>
> ppid:parent pid 父进程的pid
>
> %cpu:cpu占用率
>
> %mem:内存占用率
>
> stat:状态 r,运行 t,停止 +,前台进程 l,多线程进程 n,低优先级进程 <,高优先级进程
>
> start:启动时间
>
> time:运行累计时长
>
> command:运行的具体命令

```bash
[root@server1 test]# ps -aux
USER        PID %CPU %MEM    VSZ   RSS TTY      STAT START   TIME COMMAND
root          1  0.0  0.6 128192  6728 ?        Ss   10:12   0:01 /usr/lib/systemd/systemd --switched-root --system --deserialize 22
root          2  0.0  0.0      0     0 ?        S    10:12   0:00 [kthreadd]
root          3  0.0  0.0      0     0 ?        S    10:12   0:00 [ksoftirqd/0]
root          5  0.0  0.0      0     0 ?        S<   10:12   0:00 [kworker/0:0H]
root          7  0.0  0.0      0     0 ?        S    10:12   0:00 [migration/0]
root          8  0.0  0.0      0     0 ?        S    10:12   0:00 [rcu_bh]
root          9  0.0  0.0      0     0 ?        R    10:12   0:00 [rcu_sched]
root         10  0.0  0.0      0     0 ?        S<   10:12   0:00 [lru-add-drain]
root         11  0.0  0.0      0     0 ?        S    10:12   0:00 [watchdog/0]
root         13  0.0  0.0      0     0 ?        S    10:12   0:00 [kdevtmpfs]
root         14  0.0  0.0      0     0 ?        S<   10:12   0:00 [netns]
root         15  0.0  0.0      0     0 ?        S    10:12   0:00 [khungtaskd]
root         16  0.0  0.0      0     0 ?        S<   10:12   0:00 [writeback]
root         17  0.0  0.0      0     0 ?        S<   10:12   0:00 [kintegrityd]
root         18  0.0  0.0      0     0 ?        S<   10:12   0:00 [bioset]
root         19  0.0  0.0      0     0 ?        S<   10:12   0:00 [bioset]
root         20  0.0  0.0      0     0 ?        S<   10:12   0:00 [bioset]
root         21  0.0  0.0      0     0 ?        S<   10:12   0:00 [kblockd]
root         22  0.0  0.0      0     0 ?        S<   10:12   0:00 [md]
root         23  0.0  0.0      0     0 ?        S<   10:12   0:00 [edac-poller]
root         24  0.0  0.0      0     0 ?        S<   10:12   0:00 [watchdogd]
root         30  0.0  0.0      0     0 ?        S    10:12   0:00 [kswapd0]
root         31  0.0  0.0      0     0 ?        SN   10:12   0:00 [ksmd]
root         32  0.0  0.0      0     0 ?        SN   10:12   0:00 [khugepaged]
root         33  0.0  0.0      0     0 ?        S<   10:12   0:00 [crypto]
root         41  0.0  0.0      0     0 ?        S<   10:12   0:00 [kthrotld]
root         42  0.0  0.0      0     0 ?        S    10:12   0:00 [kworker/u256:1]
root         43  0.0  0.0      0     0 ?        S<   10:12   0:00 [kmpath_rdacd]
root         44  0.0  0.0      0     0 ?        S<   10:12   0:00 [kaluad]
root         45  0.0  0.0      0     0 ?        S<   10:12   0:00 [kpsmoused]
root         47  0.0  0.0      0     0 ?        S<   10:12   0:00 [ipv6_addrconf]
root         60  0.0  0.0      0     0 ?        S<   10:12   0:00 [deferwq]
root         91  0.0  0.0      0     0 ?        S    10:12   0:00 [kauditd]
root        675  0.0  0.0      0     0 ?        S<   10:12   0:00 [ata_sff]
root        684  0.0  0.0      0     0 ?        S    10:12   0:00 [scsi_eh_0]
root        693  0.0  0.0      0     0 ?        S<   10:12   0:00 [scsi_tmf_0]
root        695  0.0  0.0      0     0 ?        S    10:12   0:00 [scsi_eh_1]
root        703  0.0  0.0      0     0 ?        S<   10:12   0:00 [scsi_tmf_1]
root        713  0.0  0.0      0     0 ?        S    10:12   0:00 [kworker/u256:3]
root        849  0.0  0.0      0     0 ?        S<   10:12   0:00 [ttm_swap]
root        853  0.0  0.0      0     0 ?        S    10:12   0:00 [irq/16-vmwgfx]
root       1716  0.0  0.0      0     0 ?        S<   10:12   0:00 [nfit]
root       1733  0.0  0.0      0     0 ?        S<   10:12   0:00 [mpt_poll_0]
root       1742  0.0  0.0      0     0 ?        S<   10:12   0:00 [mpt/0]
root       1832  0.0  0.0      0     0 ?        S    10:12   0:00 [scsi_eh_2]
root       1837  0.0  0.0      0     0 ?        S<   10:12   0:00 [scsi_tmf_2]
root       2983  0.0  0.0      0     0 ?        S<   10:12   0:00 [kdmflush]
root       2984  0.0  0.0      0     0 ?        S<   10:12   0:00 [bioset]
root       2998  0.0  0.0      0     0 ?        S<   10:12   0:00 [kdmflush]
root       2999  0.0  0.0      0     0 ?        S<   10:12   0:00 [bioset]
root       3017  0.0  0.0      0     0 ?        S<   10:12   0:00 [bioset]
root       3022  0.0  0.0      0     0 ?        S<   10:12   0:00 [xfsalloc]
root       3027  0.0  0.0      0     0 ?        S<   10:12   0:00 [xfs_mru_cache]
root       3032  0.0  0.0      0     0 ?        S<   10:12   0:00 [xfs-buf/dm-0]
root       3033  0.0  0.0      0     0 ?        S<   10:12   0:00 [xfs-data/dm-0]
root       3036  0.0  0.0      0     0 ?        S<   10:12   0:00 [xfs-conv/dm-0]
root       3037  0.0  0.0      0     0 ?        S<   10:12   0:00 [xfs-cil/dm-0]
root       3038  0.0  0.0      0     0 ?        S<   10:12   0:00 [xfs-reclaim/dm-]
root       3039  0.0  0.0      0     0 ?        S<   10:12   0:00 [xfs-log/dm-0]
root       3040  0.0  0.0      0     0 ?        S<   10:12   0:00 [xfs-eofblocks/d]
root       3041  0.0  0.0      0     0 ?        S    10:12   0:01 [xfsaild/dm-0]
root       3042  0.0  0.0      0     0 ?        S<   10:12   0:00 [kworker/0:1H]
root       3108  0.0  0.4  37112  3988 ?        Ss   10:12   0:00 /usr/lib/systemd/systemd-journald
root       3134  0.0  0.4 192912  4200 ?        Ss   10:12   0:00 /usr/sbin/lvmetad -f
root       3142  0.0  0.5  48240  5716 ?        Ss   10:12   0:00 /usr/lib/systemd/systemd-udevd
root       4979  0.0  0.0      0     0 ?        S<   10:12   0:00 [xfs-buf/sda1]
root       4980  0.0  0.0      0     0 ?        S<   10:12   0:00 [xfs-data/sda1]
root       4981  0.0  0.0      0     0 ?        S<   10:12   0:00 [xfs-conv/sda1]
root       4983  0.0  0.0      0     0 ?        S<   10:12   0:00 [xfs-cil/sda1]
root       4984  0.0  0.0      0     0 ?        S<   10:12   0:00 [xfs-reclaim/sda]
root       4990  0.0  0.0      0     0 ?        S<   10:12   0:00 [xfs-log/sda1]
root       4991  0.0  0.0      0     0 ?        S<   10:12   0:00 [xfs-eofblocks/s]
root       4993  0.0  0.0      0     0 ?        S    10:12   0:00 [xfsaild/sda1]
root       5104  0.0  0.1  62048  1080 ?        S<sl 10:12   0:00 /sbin/auditd
polkitd    5884  0.0  1.5 612996 15064 ?        Ssl  10:12   0:00 /usr/lib/polkit-1/polkitd --no-debug
dbus       5885  0.0  0.2  66456  2628 ?        Ssl  10:12   0:00 /usr/bin/dbus-daemon --system --address=systemd: --nofork --nopidfile --systemd-activation
root       5896  0.0  0.1  26444  1804 ?        Ss   10:12   0:00 /usr/lib/systemd/systemd-logind
chrony     5903  0.0  0.1 117784  1816 ?        S    10:12   0:00 /usr/sbin/chronyd
root       5910  0.0  0.1 126288  1704 ?        Ss   10:12   0:00 /usr/sbin/crond -n
root       5916  0.0  0.2  96560  2464 ?        Ss   10:12   0:00 login -- root
root       5943  0.0  2.9 358520 29420 ?        Ssl  10:12   0:00 /usr/bin/python -Es /usr/sbin/firewalld --nofork --nopid
root       6167  0.0  0.9 701604  9072 ?        Ssl  10:12   0:00 /usr/sbin/NetworkManager --no-daemon
root       6905  0.0  0.5 107356  5476 ?        S    10:12   0:00 /sbin/dhclient -d -q -sf /usr/libexec/nm-dhcp-helper -pf /var/run/dhclient-ens33.pid -lf /var/lib/NetworkManager/dhclien
root       7097  0.0  0.4 112756  4352 ?        Ss   10:12   0:00 /usr/sbin/sshd -D
root       7100  0.0  1.7 573820 17104 ?        Ssl  10:12   0:02 /usr/bin/python2 -Es /usr/sbin/tuned -l -P
root       7101  0.0  0.4 220776  4956 ?        Ssl  10:12   0:01 /usr/sbin/rsyslogd -n
root       7109  0.0  3.7 1006692 37544 ?       Ssl  10:12   0:34 /usr/bin/containerd
root       7180  0.0  6.3 1169676 63172 ?       Ssl  10:12   0:02 /usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock
root       7250  0.0  0.2  89544  2160 ?        Ss   10:12   0:00 /usr/libexec/postfix/master -w
postfix    7260  0.0  0.4  89716  4088 ?        S    10:12   0:00 qmgr -l -t unix -u
root       7605  0.0  0.2 115436  2036 tty1     Ss+  10:22   0:00 -bash
root       7757  0.0  0.0      0     0 ?        S<   10:27   0:00 [kworker/u257:0]
root       7759  0.0  0.0      0     0 ?        S<   10:27   0:00 [hci0]
root       7760  0.0  0.0      0     0 ?        S<   10:27   0:00 [hci0]
root       7771  0.0  0.0      0     0 ?        S<   10:27   0:00 [kworker/u257:2]
root       9398  0.0  0.5 158760  5580 ?        Ds   11:39   0:00 sshd: root@pts/1
root       9402  0.0  0.2 116304  2964 pts/1    Ss   11:39   0:00 -bash
root       9965  0.0  0.5 149416  5216 pts/1    T    13:37   0:00 vim passwd
root      10897  0.0  0.5 158760  5524 ?        Ss   19:00   0:00 sshd: centos [priv]
centos    10901  0.0  0.2 158760  2460 ?        S    19:00   0:00 sshd: centos@pts/0
centos    10902  0.0  0.2 116216  2844 pts/0    Ss+  19:00   0:00 -bash
root      11416  0.0  0.0      0     0 ?        R    21:40   0:00 [kworker/0:1]
root      11418  0.0  0.0      0     0 ?        S    21:50   0:00 [kworker/0:2]
postfix   11444  0.0  0.4  89648  4068 ?        S    21:53   0:00 pickup -l -t unix -u
root      11445  0.0  0.0      0     0 ?        S    21:55   0:00 [kworker/0:0]
root      11447  0.0  0.1 155360  1888 pts/1    R+   21:57   0:00 ps -aux
```

## pstree

> yum -y install psmisc

```bash
[root@localhost ~]# pstree
systemd─┬─NetworkManager─┬─dhclient
        │                └─2*[{NetworkManager}]
        ├─agetty
        ├─anacron
        ├─auditd───{auditd}
        ├─chronyd
        ├─crond
        ├─dbus-daemon───{dbus-daemon}
        ├─firewalld───{firewalld}
        ├─lvmetad
        ├─master─┬─pickup
        │        └─qmgr
        ├─polkitd───6*[{polkitd}]
        ├─rsyslogd───2*[{rsyslogd}]
        ├─sshd─┬─sshd───bash───pstree
        │      └─sshd───sftp-server
        ├─systemd-journal
        ├─systemd-logind
        ├─systemd-udevd
        └─tuned───4*[{tuned}]
```

## systemctl

## top

> 任务管理器 实时刷新
>
> NI:nice级别和后面的nice命令有关 优先度
>
> pr：把ni映射到另一个数 -20到0 0到20

```bash
[root@localhost ~]# top -d 1
top - 21:29:22 up 29 min,  1 user,  load average: 0.00, 0.01, 0.05
Tasks: 101 total,   1 running, 100 sleeping,   0 stopped,   0 zombie
%Cpu(s):  0.0 us,  0.0 sy,  0.0 ni, 99.0 id,  0.0 wa,  0.0 hi,  1.0 si,  0.0 st
KiB Mem :   995892 total,   573380 free,   143456 used,   279056 buff/cache
KiB Swap:  2097148 total,  2097148 free,        0 used.   662944 avail Mem

   PID USER      PR  NI    VIRT    RES    SHR S %CPU %MEM     TIME+ COMMAND
     1 root      20   0  128000   6544   4136 S  0.0  0.7   0:01.01 systemd
     2 root      20   0       0      0      0 S  0.0  0.0   0:00.00 kthreadd
     3 root      20   0       0      0      0 S  0.0  0.0   0:00.06 ksoftirqd/0
     5 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 kworker/0:0H
     6 root      20   0       0      0      0 S  0.0  0.0   0:00.04 kworker/u256:0
     7 root      rt   0       0      0      0 S  0.0  0.0   0:00.00 migration/0
     8 root      20   0       0      0      0 S  0.0  0.0   0:00.00 rcu_bh
     9 root      20   0       0      0      0 S  0.0  0.0   0:00.32 rcu_sched
    10 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 lru-add-drain
    11 root      rt   0       0      0      0 S  0.0  0.0   0:00.00 watchdog/0
    13 root      20   0       0      0      0 S  0.0  0.0   0:00.00 kdevtmpfs
    14 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 netns
    15 root      20   0       0      0      0 S  0.0  0.0   0:00.00 khungtaskd
    16 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 writeback
    17 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 kintegrityd
    18 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 bioset
    19 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 bioset
    20 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 bioset
    21 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 kblockd
    22 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 md
    23 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 edac-poller
    24 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 watchdogd
    30 root      20   0       0      0      0 S  0.0  0.0   0:00.00 kswapd0
    31 root      25   5       0      0      0 S  0.0  0.0   0:00.00 ksmd
    32 root      39  19       0      0      0 S  0.0  0.0   0:00.01 khugepaged
    33 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 crypto
    41 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 kthrotld
    43 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 kmpath_rdacd
    44 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 kaluad
    45 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 kpsmoused
    47 root       0 -20       0      0      0 S  0.0  0.0   0:00.00 ipv6_addrconf
```

> 相当于watch -n 1 ps aux

> 相关命令
>
> h|? 帮助
>
> M 按内存的使用排序
>
> P 按CPU使用排序
>
> N 以PID的大小排序
>
> R 对排序进行反转
>
> f 自定义显示字段
>
> 1 显示所有CPU的负载
>
> k 输入信号
>
> < 向前
>
> \> 向后

## kill

> 给进程发送信号

```bash
[root@localhost ~]# kill 7821
```

> 重新加载配置

```bash
[root@localhost ~]# kill -HUP 7916
```

## pkill

> 根据命令名杀死进程 相比较kill由pid杀死，特点是批量

```bash
[root@localhost ~]# pkill ping
```

## pgrep

> 找出命令名的pid

```bash
[root@localhost ~]# pgrep -l ping
7885 ping
```

> 实用

```bash
[root@localhost ~]# kill `pgrep ping` # 反引号
```

 ## nice

> 启动时默认继承父进程的有限度
>
> 启动时指定优先度

```bash
[root@localhost ~]# nice -n 20 ping baidu.com
```

## renice

> 修改优先度 （-20高) - - - 0 - - - (19低)

```bash
[root@localhost ~]# renice -20 10267
```

## jobs

```bash
[root@localhost ~]# jobs
[1]+  已停止               ping baidu.com
```

## fg

```bash
[root@localhost ~]# jobs
[1]+  已停止               ping baidu.com
[root@localhost ~]# fg % 1
ping baidu.com
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=3 ttl=128 time=29.7 ms
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=4 ttl=128 time=29.8 ms
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=5 ttl=128 time=29.5 ms
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=6 ttl=128 time=29.6 ms
```

## bg

> 从后台恢复到控制台

## nohup

```bash
[root@localhost ~]# nohup ping baidu.com &
[1] 7901
[root@localhost ~]# nohup: 忽略输入并把输出追加到"nohup.out"
[root@localhost ~]# jobs
[1]+  运行中               nohup ping baidu.com &
```

# 硬盘管理

## 分区

1. 从磁盘分区
2. 刷一个文件系统
3. 挂载目录
4. 修改文件使其永久挂载 /etc/fstab

### fdisk

> 查看硬盘的使用情况

```bash
[root@server1 /]# fdisk -l

磁盘 /dev/sda：21.5 GB, 21474836480 字节，41943040 个扇区
Units = 扇区 of 1 * 512 = 512 bytes
扇区大小(逻辑/物理)：512 字节 / 512 字节
I/O 大小(最小/最佳)：512 字节 / 512 字节
磁盘标签类型：dos
磁盘标识符：0x000a6510

   设备 Boot      Start         End      Blocks   Id  System
/dev/sda1   *        2048     2099199     1048576   83  Linux
/dev/sda2         2099200    41943039    19921920   8e  Linux LVM

磁盘 /dev/sdb：21.5 GB, 21474836480 字节，41943040 个扇区
Units = 扇区 of 1 * 512 = 512 bytes
扇区大小(逻辑/物理)：512 字节 / 512 字节
I/O 大小(最小/最佳)：512 字节 / 512 字节


磁盘 /dev/sdc：21.5 GB, 21474836480 字节，41943040 个扇区
Units = 扇区 of 1 * 512 = 512 bytes
扇区大小(逻辑/物理)：512 字节 / 512 字节
I/O 大小(最小/最佳)：512 字节 / 512 字节


磁盘 /dev/sde：21.5 GB, 21474836480 字节，41943040 个扇区
Units = 扇区 of 1 * 512 = 512 bytes
扇区大小(逻辑/物理)：512 字节 / 512 字节
I/O 大小(最小/最佳)：512 字节 / 512 字节


磁盘 /dev/sdd：21.5 GB, 21474836480 字节，41943040 个扇区
Units = 扇区 of 1 * 512 = 512 bytes
扇区大小(逻辑/物理)：512 字节 / 512 字节
```

> 用来分区

> n:添加分区
>
> d:删除分区

### mkfs.ext4

> 格式化

```bash
[root@localhost ~]# mkfs.ext4 /dev/md0
```

### mount

> 挂载到某个目录

```bash
[root@node-1 ~]# mount /dev/sda2 /root/backup
```

### umount

> 取消挂载

```bash
[root@node-1 ~]#u mount /dev/sda2
```

### df

> 查看是否挂载

```bash
[root@server1 /]# df -h
文件系统                 容量  已用  可用 已用% 挂载点
/dev/mapper/centos-root   17G  1.9G   16G   12% /
devtmpfs                 475M     0  475M    0% /dev
tmpfs                    487M     0  487M    0% /dev/shm
tmpfs                    487M  7.7M  479M    2% /run
tmpfs                    487M     0  487M    0% /sys/fs/cgroup
/dev/sda1               1014M  133M  882M   14% /boot
tmpfs                     98M     0   98M    0% /run/user/0
```

### lsblk

> 查看硬盘分区

```bash
[root@server1 /]# lsblk
NAME            MAJ:MIN RM  SIZE RO TYPE MOUNTPOINT
sda               8:0    0   20G  0 disk 
├─sda1            8:1    0    1G  0 part /boot
└─sda2            8:2    0   19G  0 part 
  ├─centos-root 253:0    0   17G  0 lvm  /
  └─centos-swap 253:1    0    2G  0 lvm  [SWAP]
sdb               8:16   0   20G  0 disk 
sdc               8:32   0   20G  0 disk 
sdd               8:48   0   20G  0 disk 
sde               8:64   0   20G  0 disk 
sr0              11:0    1  918M  0 rom  
```

## 交换分区

> 磁盘空间用来给活跃的进程分配资源

### mkswap

> 添加交换分区

```bash
[root@localhost ~]# mkswap /dev/sdb2 mkswap: /dev/sdb2: warning: wiping old ext4 signature. Setting up swapspace version 1, size = 5241852 KiB no label, UUID=6707cde0-c7df-4557-b99b-45fbf5b36dcd
```

### free

> 查看swap分区

```bash
[root@server1 /]# free -h
              total        used        free      shared  buff/cache   available
Mem:           972M        165M        409M        7.6M        396M        614M
```

### swapon 

> 开启交换分区

```bash
[root@server1 /]# swapon -a
```

### swapoff

> 关闭交换分区

```bash
[root@server1 /]# swapoff -a
```

## 软硬链接

> 软硬链接的区别

> 软链接：
>
> 有自己的inode和block
>
> block存放的是指向的文件的inode和文件路径
>
> 移动文件可能会造成失效
>
> 指向文件删除 链接失效

> 硬链接：
>
> 没有自己的inode和block 用的是指向文件的inode和block
>
> 原文件删除不影响硬链接

## 建立raid

1. 准备磁盘
2. 构建raid
3. 格式化raid ext4
4. 永久挂载

### mdadm -C

> 构建raid C:创建 v:显示创建过程

```bash
[root@localhost ~]# mdadm -Cv /dev/md0 -a yes -n 4 -l 10 /dev/sdb /dev/sdc /dev/sdd /dev/sde
```

### mdadm -D

> 查看raid情况

```bash
[root@localhost ~]# mdadm -D /dev/md0
```

## 恢复损坏硬盘数据

1. 取消raid挂载
2. 把raid中的损坏硬盘移除
3. 把新硬盘加入
4. 重新挂载

### mdadm -f

> 删除raid中的损坏硬盘

```bash
[root@localhost ~]# mdadm /dev/md0 -f /dev/sdb
```

### mdadm -a

> 加入新的硬盘

```bash
[root@localhost ~]# mdadm /dev/md0 -a /dev/sdb
```

## LVM

> LVM技术
>
> 硬盘上进行物理分区（物理卷） 物理卷之间组合构成一个卷组 卷组上可以进行逻辑分区
>
> 目的是为了更方便的扩展

1. 进行物理分区
2. 建立卷组
3. 建立逻辑分区
4. 格式化
5. 永久挂载

### pvcreate

> 让硬盘支持LVM功能

```bash
[root@localhost ~]# pvcreate /dev/sdb
```

### vgcreate

> 构建卷组

``` bash
[root@localhost ~]# vgcreate storage /dev/sdb /dev/sdc
```

### vgdisplay 

> 查看卷组

```bash
[root@server1 /]# vgdisplay
  --- Volume group ---
  VG Name               centos
  System ID             
  Format                lvm2
  Metadata Areas        1
  Metadata Sequence No  3
  VG Access             read/write
  VG Status             resizable
  MAX LV                0
  Cur LV                2
  Open LV               1
  Max PV                0
  Cur PV                1
  Act PV                1
  VG Size               <19.00 GiB
  PE Size               4.00 MiB
  Total PE              4863
  Alloc PE / Size       4863 / <19.00 GiB
  Free  PE / Size       0 / 0   
  VG UUID               0W9JfK-z5jE-qKzw-GjiI-TBeb-P92p-p3HBcz
```

### lvcreate

> 进行逻辑分区

```bash
[root@localhost ~]# lvcreate -n vo -l 37 storage
Logical volume "vo" created.
```

### lvdisplay

> 查看逻辑卷

```bash
[root@server1 /]# vgdisplay
  --- Volume group ---
  VG Name               centos
  System ID             
  Format                lvm2
  Metadata Areas        1
  Metadata Sequence No  3
  VG Access             read/write
  VG Status             resizable
  MAX LV                0
  Cur LV                2
  Open LV               1
  Max PV                0
  Cur PV                1
  Act PV                1
  VG Size               <19.00 GiB
  PE Size               4.00 MiB
  Total PE              4863
  Alloc PE / Size       4863 / <19.00 GiB
  Free  PE / Size       0 / 0   
  VG UUID               0W9JfK-z5jE-qKzw-GjiI-TBeb-P92p-p3HBcz
   
[root@server1 /]# lvdisplay
  --- Logical volume ---
  LV Path                /dev/centos/swap
  LV Name                swap
  VG Name                centos
  LV UUID                nrWoJg-DwLL-5HQK-vyz1-OaL3-ctYX-UjvHvN
  LV Write Access        read/write
  LV Creation host, time localhost.localdomain, 2021-11-22 06:02:24 +0800
  LV Status              available
  # open                 0
  LV Size                2.00 GiB
  Current LE             512
  Segments               1
  Allocation             inherit
  Read ahead sectors     auto
  - currently set to     8192
  Block device           253:1
   
  --- Logical volume ---
  LV Path                /dev/centos/root
  LV Name                root
  VG Name                centos
  LV UUID                0J66wV-9Oz2-qhHK-TxSx-X5hO-e2fs-7JmxkY
  LV Write Access        read/write
  LV Creation host, time localhost.localdomain, 2021-11-22 06:02:24 +0800
  LV Status              available
  # open                 1
  LV Size                <17.00 GiB
  Current LE             4351
  Segments               1
  Allocation             inherit
  Read ahead sectors     auto
  - currently set to     8192
  Block device           253:0
```

## 扩展逻辑卷

1. 取消挂载
2. 扩展逻辑卷
3. 检查逻辑卷并重置容量
4. 重新挂载

### lvextend 

> 扩展逻辑卷

```bash
[root@localhost ~]# lvextend -L 290M /dev/storage/vo
```

### e2fsck

> 检查逻辑卷

```bash
[root@localhost ~]# e2fsck -f /dev/storage/vo
```

### resize2fs

> 重置逻辑卷容量

```bash
[root@localhost ~]# resize2fs /dev/storage/vo
```

# 网络设置

## yum

>  配置阿里源

```bash
[root@server yum.repos.d]# pwd
/etc/yum.repos.d
[root@server yum.repos.d]# rm -rf * wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
[root@server yum.repos.d]# yum clean all
[root@server yum.repos.d]# yum makecache
```

> ftp搭建支持update的yum仓库

+ 防火墙

```bash
[root@localhost ~]# firewall-cmd --permanent --add-service=ftp
[root@localhost ~]# firewall-cmd --reload
```

+ 关闭selinux

```bash
[root@localhost ~]# setenforce 0
[root@localhost ~]# sed -i "s/=enforcing/=disabled/g" /etc/sysconfig/selinux
```

+ ftp服务

```bash
[root@localhost ~]# yum -y install vsftpd
[root@localhost ~]# systemctl start vsftpd
[root@localhost ~]# systemctl enable vsftpd
```

+ 搭建仓库

```bash
[root@localhost ~]# vim /etc/yum.conf keepcache=1 # 开启yum缓存
[root@localhost ~]# yum update -y
[root@localhost ~]# mkdir /var/ftp/update
[root@localhost ~]# find /var/cache/yum/x86_64/7/ -iname "*.rpm" -exec cp - rf {} /var/ftp/update/ \;
[root@localhost ~]# yum -y install createrepo
[root@localhost ~]# createrepo /var/ftp/update/ # 创建源的数据库
```

## 配置网卡

>  修改配置文件

```bash
TYPE="Ethernet"
PROXY_METHOD="none"
BROWSER_ONLY="no"
BOOTPROTO=static # 静态获取ip
DEFROUTE="yes"
IPV4_FAILURE_FATAL="no"
IPV6INIT="yes"
IPV6_AUTOCONF="yes"
IPV6_DEFROUTE="yes"
IPV6_FAILURE_FATAL="no"
IPV6_ADDR_GEN_MODE="stable-privacy"
NAME="ens33" # 网卡名字
UUID="3c3b377e-30e6-4386-9ff8-101a2261444d"
DEVICE="ens33"
ONBOOT="yes"
IPADDR=192.168.188.131 # 静态ip
GATEWAY=192.168.188.128 # 网关
NETMASK=255.255.255.0 # 子网掩码
DNS1=114.114.114.114 # DNS解析服务器
```

## iptables配置防火墙

+ 查看规则

> [root@localhost ~]# iptables [-t tables] [-L] [-nv] 
>
> 参数： 
>
> -t：指定表名，默认是filter表 
>
> -L：指定链名，默认是所有链 
>
> -v：显示详细信息 
>
> -x：展开数字 

+ 删除规则

> 展示行号

```bash
[root@server1 ~]# iptables -vxL INPUT --line-numbers
```

> 根据行号删除

```bash
[root@server1 ~]# iptables -t filter -D INPUT 3
```

+ 匹配规则

> 基本匹配条件
>
> -s：指定源ip地址
>
> -d：指定目的ip地址
>
> -p：指定协议类型
>
> -i：指定网卡流入，PREROUTING/INPUT/FORWARD
>
> -o：指定网卡流出，OUTPUT/POSTROUTING



> 扩展条件
>
> 
>
> -m 扩展模块
>
> tcp/udp
>
> --dport：指定目的端口
>
> --sport：指定源端口
>
> 
>
> iprange：匹配报文的源/目的地址所在范围
>
> --src-range
>
> --dst-range
>
> string：指定匹配报文中的字符串
>
> --algo：指定匹配算法，可以是bm/kmp
>
> --string：指定需要匹配的字符串
>
> 
>
> time：指定匹配报文的时间
>
> --timestart
>
> --timestop
>
> --weekdays
>
> --mouthdays
>
> --datestart
>
> --datestop
>
> **可做负载平衡**
>
> connlimit：限制每个ip连接到server端的数量，不需要指定ip默认就是针对每个ip地址
>
> --connlimit-above：最大连接数
>
> limit：对报文到达的速率继续限制，限制包数量
>
> 10/second
>
> 10/minute
>
> 10/hour
>
> 10/day
>
> --limit-burst：空闲时可放行的包数量，默认为5
>
> 
>
> state模块：用于针对tcp连接进行限制
>
> --state NEW：连接中的第一个包的状态是NEW
>
> ESTABLISHED：NEW状态后面的包是ESTABLISHED
>
> RELATED：与命令连接中的报文有关系，比如ftp服务有连个进程，一个是命令进程一个是数据
>
> 进程
>
> INVALID：如果一个包没有办法被识别，或者这个包没有任何装填，我们是能够主动屏蔽状态为
>
> INVALID的报文
>
> UNTRACKED：表示报文为未被追踪状态

## NAT转发

1. 内网服务器和外网服务器联通

   > 配置内网服务器的网卡

   ```bash
   TYPE="Ethernet"
   PROXY_METHOD="none"
   BROWSER_ONLY="no"
   BOOTPROTO=static
   DEFROUTE="yes"
   IPV4_FAILURE_FATAL="no"
   IPV6INIT="yes"
   IPV6_AUTOCONF="yes"
   IPV6_DEFROUTE="yes"
   IPV6_FAILURE_FATAL="no"
   IPV6_ADDR_GEN_MODE="stable-privacy"
   NAME="ens33"
   UUID="3c3b377e-30e6-4386-9ff8-101a2261444d"
   DEVICE="ens33"
   ONBOOT="yes"
   IPADDR=192.168.188.131
   GATEWAY=192.168.188.128
   NETMASK=255.255.255.0
   DNS1=114.114.114.114
   ```

2. 外网服务器设置支持NAT（Linux默认不支持NAT）

   ```bash
   [root@localhost ~]# echo "net.ipv4.ip_forward=1" >> /usr/lib/sysctl.d/50-default.conf
   [root@localhost ~]# sysctl -w net.ipv4.ip_forward=1
   [root@localhost ~]# sysctl -p
   ```

3. 外网服务器设置iptables实现数据转发

   > 数据被路由转发到目标客户端 （数据的目标ip地址改变）

   ```bash
   [root@localhost ~]# iptables -t nat -A POSTROUTING -s 192.168.12.0/24[内网服务器客户端] -j MASQUERADE
   ```

4. 端口映射

   ```bash
   [root@localhost ~]# iptables -t nat -A PREROUTING -p tcp --dport 8822[被转发的端口] -j DNAT --to-destination 192.168.188.131:22[内网服务器的ssh服务]
   ```

# 日志

## 计划任务 

### at

> 定时执行的命令

```bash
[root@server1 tmp]# at now+1min < at.jobs
job 3 at Mon Jan 17 19:15:00 2022
[root@server1 tmp]# cat date.txt 
2022年 01月 17日 星期一 19:14:00 CST
[root@server1 tmp]# cat date.txt 
2022年 01月 17日 星期一 19:15:00 CST
[root@server1 tmp]# cat at.jobs 
date > date.txt
```

### cron

> 语法

```bash
分 时 日 月 星期 命令 * 表示任何数字都符合
0 2 * * * /run.sh # 每天的2点 
0 2 14 * * /run.sh # 每月14号2点 
0 2 14 2 * /run.sh # 每年2月14号2点 
0 2 * * 5 /run.sh # 每个星期5的2点 
0 2 * 6 5 /run.sh # 每年6月份的星期5的2点 
0 2 2 * 5 /run.sh # 每月2号或者星期5的2点 星期和日同时存在，那么就是或的关系 
0 2 2 6 5 /run.sh # 每年6月2号或者星期5的2点 
*/5 * * * * /run.sh # 每隔5分钟执行一次 
0 2 1,4,6 * * /run.sh # 每月1号，4号，6号的2点 
0 2 5-9 * * /run.sh # 每月5-9号的2点 
* * * * * /run.sh # 每分钟 
0 * * * * /run.sh # 每整点 
* * 2 * * /run.sh # 每月2号的每分钟
```

> 管理命令

```bash
[root@localhost ~]# crontab -l # 列出当前用户所有计划任务 
[root@localhost ~]# crontab -r # 删除当前用户计划任务 
[root@localhost ~]# crontab -e # 编辑当前用户计划任务 管理员可以使用 -u username,去管理其他用户的计划任务 
[root@localhost ~]# vi /etc/cron.deny # 这个文件中加入的用户名无法使用cron
```

## ssh日志参数

```bash
[root@server1 ~]# vim /etc/rsyslog.conf

# rsyslog configuration file

# For more information see /usr/share/doc/rsyslog-*/rsyslog_conf.html
# If you experience problems, see http://www.rsyslog.com/doc/troubleshoot.html

#### MODULES ####

# The imjournal module bellow is now used as a message source instead of imuxsock.
$ModLoad imuxsock # provides support for local system logging (e.g. via logger command)
$ModLoad imjournal # provides access to the systemd journal
#$ModLoad imklog # reads kernel messages (the same are read from journald)
#$ModLoad immark  # provides --MARK-- message capability

# Provides UDP syslog reception
$ModLoad imudp
$UDPServerRun 514

# Provides TCP syslog reception
$ModLoad imtcp
$InputTCPServerRun 514


#### GLOBAL DIRECTIVES ####

# Where to place auxiliary files
$WorkDirectory /var/lib/rsyslog

# Use default timestamp format
$ActionFileDefaultTemplate RSYSLOG_TraditionalFileFormat

# File syncing capability is disabled by default. This feature is usually not required,
# not useful and an extreme performance hit
#$ActionFileEnableSync on

# Include all config files in /etc/rsyslog.d/
```

## ssh远程日志

> 修改ssh的日志文件到另一台服务器

> 另一台服务器上修改监听端口

```bash
[root@server1 httpd]# vim /etc/rsyslog.conf 
# Provides UDP syslog reception 
$ModLoad imudp 
$UDPServerRun 514 
# Provides TCP syslog reception 
$ModLoad imtcp 
$InputTCPServerRun 514
```

> 修改ssh服务器

```bash
[root@server2 ~]# vim /etc/ssh/sshd_config SyslogFacility LOCAL0
[root@server2 ~]# vim /etc/rsyslog.conf 
local0.* @192.168.80.193 
[root@server2 ~]# systemctl restart rsyslog.service
```

## 日志轮转

> 日志文件会越来越大 对其进行分割减少资源浪费  基于计划任务实现

> logrotate相关目录和文件

```bash
[root@localhost ~]# cat /etc/cron.daily/logrotate 
#!/bin/sh 

/usr/sbin/logrotate -s /var/lib/logrotate/logrotate.status
/etc/logrotate.conf 
# 日志轮转状态/var/lib/logrotate/logrotate.status 
# 日志轮转规则按照/etc/logrotate.conf中来
```

> 主配置文件

```bash
[root@localhost ~]# vim /etc/logrotate.conf 
weekly # 一周轮转一次 
rotate 4 # 保留4份日志 
create # 主动创建新的日志文件 
dateext # 使用日期来作为文件名的后缀 
#compress # 每次轮转需不需要进行压缩 
include /etc/logrotate.d # 导入其他应用的日志轮转规则 
/var/log/wtmp { 
# 对该日志文件设置轮转的方法 
monthly # 一个月轮转一次 
create 0664 root utmp # 轮转后创建新文件，并设置权限 
minsize 1M # 最小达到1M才会轮转 
rotate 1 
}
/var/log/btmp { 
missingok # 丢失不提醒 
monthly 
create 0600 root utmp 
rotate 1 
}

#下面两个没匹配到时采用上方默认配置
```
