# 51job多线程爬虫
## 使用注意
+ 安装需要的库（详情见程序）
+ 安装对应版本的chrome和chrome的webdriver
+ 在main.py中修改关键词，运行main.py即可使用。（由于bug爬虫部分可能运行完后线程结束不了，无法自动进行数据处理，如果爬虫部分长时间停止，表示爬虫结束，手动运行一下main中的数据处理部分，注释掉爬虫部分再运行一下即可。）

## 部分功能说明
+ 关于爬取详情url采用webdriver的方式是因为用requests库爬取不太稳定
+ 因为太多访问会导致服务器给ip分配的资源不足，导致一些爬取失败，对失败url进行二次爬取几乎可以爬取所有的url了。

###### tips：如产生相关法律问题，尽快联系我进行删改。
