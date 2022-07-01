# 配置文件

> /etc/redis.conf
>
> RDB是对数据库快照进行备份
>
> AOF是对数据库的语句进行保存用来保存 可以优化语句（删除无用语句）AOF的文件大小

```bash
bind 127.0.0.1

protected-mode yes

port 6379

tcp-backlog 511

timeout 0

tcp-keepalive 300

daemonize no

supervised no
# pid文件
pidfile /var/run/redis_6379.pid

loglevel notice

logfile /var/log/redis/redis.log

databases 16
# RDB触发条件
save 900 1
save 300 10
save 60 10000
# 当启用了RDB且最后一次后台保存数据失败，Redis是否停止接收数据
stop-writes-on-bgsave-error yes
# 对于存储到磁盘中的快照，可以设置是否进行压缩存储
rdbcompression yes
# 在存储快照后，我们还可以让redis使用CRC64算法来进行数据校验
rdbchecksum yes
# 设置快照的文件名
dbfilename dump.rdb
# 设置快照文件的存放路径
dir /var/lib/redis
# 主从复制
slave-serve-stale-data yes
# 配置Redis的Slave实例是否接受写操作
slave-read-only yes
# 主从数据复制是否使用无硬盘复制功能
repl-diskless-sync no
# 等待时间
repl-diskless-sync-delay 5
# 同步之后是否禁用从站上的TCP_NODELAY
repl-disable-tcp-nodelay no

slave-priority 100

# AOF相关配置
# 默认redis使用的是rdb方式持久化
appendonly no
# 文件名
appendfilename "appendonly.aof"
# aof持久化策略的配置
appendfsync everysec
# 在aof重写或者写入rdb文件的时候，不执行持久化策
no-appendfsync-on-rewrite no
# 当目前aof文件大小超过上一次重写的aof文件大小的百分之多少进行重写
auto-aof-rewrite-percentage 100
# 设置允许重写的最小aof文件大小
auto-aof-rewrite-min-size 64mb
# 当截断的aof文件被导入的时候，会自动发布一个log给客户端然后 load。
aof-load-truncated yes
# 一个lua脚本执行的最大时间
lua-time-limit 5000

slowlog-log-slower-than 10000

slowlog-max-len 128

latency-monitor-threshold 0

notify-keyspace-events ""

hash-max-ziplist-entries 512

hash-max-ziplist-value 64

list-max-ziplist-size -2

list-compress-depth 0

set-max-intset-entries 512

zset-max-ziplist-entries 128

zset-max-ziplist-value 64

hll-sparse-max-bytes 3000

activerehashing yes

client-output-buffer-limit normal 0 0 0
client-output-buffer-limit slave 256mb 64mb 60
client-output-buffer-limit pubsub 32mb 8mb 60

hz 10

aof-rewrite-incremental-fsync yes
```

# 启动

```bash
[root@localhost ~]# redis-server /etc/redis.conf
```

# 客户端

```bash
[root@localhost ~]# redis-cli -h 127.0.0.1 -p 6379
```

# 查看所有键

> 慎用 可能造成堵塞

```bash
127.0.0.1:6379> KEYS *
```

# 所有键的数量

```
127.0.0.1:6379> DBSIZE
(integer) 4
```

# 查看是否存在某个键

```bash
127.0.0.1:6379> EXISTS k1
(integer) 1
127.0.0.1:6379> EXISTS k5
(integer) 0
```

# 删除键

```bash
127.0.0.1:6379> DEL k1 k4
(integer) 2
```

# 设置键的过期时间

```bash
expire key seconds
```

# 查看键的过期时间

```bash
ttl key
```

# 字符串

## 增/改

```bash
SET key value
```

## 查

```bash
get key

# 截取
getrange key start end
```

# 列表

## 增

```bash
# 右边加入
rpush key value [value ...]
# 左边加入
lpush key value [value ...]
```

## 删

```bash
# 从左侧弹出
lpop key
# 从右侧弹出
rpop key
# lrem命令会从列表中找到等于value的元素进行删除，根据count的不同
# count>0，从左到右，删除最多count个元素
# count<0，从右到左，删除最多count绝对值个元素
# count=0，删除所有。
lrem key count value
# 按照索引范围修剪列表
ltrim key start end
```

## 改

```bash
# 修改指定下标元素
lset key index newValue
```

## 查

```bash
# 查看所有元素
lrange listkey 0 -1
# 查看指定下标
lindex key index
```

# 哈希

## 增/改

```bash
hset key field value

# 批量
hmset key field value [field value ...]
```

## 删

```bash
hdel key field [field ...]
```

## 查

```bash
hget key field

# 计算filed个数
hlen key

# 判断field是否存在
hexists key field

# 获取所有filed
hkeys key

# 获取所有value
hvals key

# 获取所有键值对
hgetall key
```

# 集合

> 不存在重复元素

## 增

```bash
sadd key element [element ...]
```

## 删

```bash
# 删除元素
srem key element [element ...]
# 随机弹出元素
spop key
```

## 查

```bash
# 计算元素个数 
scard key
# 查看集合是否存在某个元素
sismember key element
# 随机返回count个元素
srandmember key [count]
# 获取所有元素
smembers key
```

## 集合运算

```bash
# 交集
sinter key [key ...]
# 并集
suinon key [key ...]
# 差集
sdiff key [key ...]
# 保存集合运算
sinterstore destination key [key ...]
suionstore destination key [key ...]
sdiffstore destination key [key ...]
```

# 有序集合

## 增

```bash
zadd key score member [score member ...]
```

## 删

```bash
zrem key member [member ...]
# 删除指定排名的成员
zremrangebyrank key start end
# 删除指定权值的成员 +inf代表最大 -inf代表最小
zremrangebyscore key min max
```

## 改

```bash
# 增加成员权值
zincrby key increment member
```

## 查

```bash
# 计算成员个数
zcard key
# 查看成员权值
zscore key member
# 查看成员排名
zrank key member
# 从低到高的权值给出指定范围的成员
zrange key start end [withscores]
# 从高到低
zrevrange key start end [withscores
# 从低到高给出权值范围的成员
zrangebyscore key min max [withscores] [limit offset count]
# 从高到低
zrevrangebyscore key max min [withscores] [limit offset count]
# 指定权值范围的成员个数
zcount key min max
```

## 集合运算

```bash
# 保存交集
zinterstore destination numkeys key [key ...] [weights weight [weight ...]] [aggregate sum|min|max]
# 保存并集
zunionstore destination numkeys key [key ...] [weights weight [weight ...]] [aggregate sum|min|max]
```

# 慢查询

> 找出运行慢的语句进行优化

## 配置

```bash
config set slowlog-log-slower-than 20000 # 单位为微秒 
config set slowlog-max-len 1000 config rewrite
```

## 获取日志

```bash
slowlog get [n]
```

## 重置慢查询日志

```bash
slowlog reset
```

