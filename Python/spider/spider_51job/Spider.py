#!/usr/bin/env python
# coding=utf-8
import csv
import re
from gevent import monkey
from gevent.pool import Pool
monkey.patch_all()
import socket
socket.setdefaulttimeout(20)
import time
import logging
from lxml import etree
import requests
from queue import Queue
from selenium import webdriver
requests.adapters.DEFAULT_RETRIES = 5  # 增加重连次数


def get_logger():
    """
    创建日志实例
    """
    formatter = logging.Formatter("%(asctime)s - %(message)s")
    logger = logging.getLogger("monitor")
    logger.setLevel(LOG_LEVEL)

    ch = logging.StreamHandler()
    ch.setFormatter(formatter)
    logger.addHandler(ch)
    return logger


HEADERS = {
    "X-Requested-With": "XMLHttpRequest",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36"
                  "(KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36",
}

LOG_LEVEL = logging.INFO  # 日志等级
POOL_MAXSIZE = 8  # 线程池最大容量

logger = get_logger()


class JobSpider:
    """
    51 job 网站爬虫类
    """

    def __init__(self):
        self.count = 1  # 记录当前爬第几条数据
        self.company = []
        self.desc_url_queue = Queue()  # 线程池队列
        self.pool = Pool(POOL_MAXSIZE)  # 线程池管理线程,最大协程数
        self.url_list = []
        self.wd = webdriver.Chrome("chromedriver.exe")
        self.fail_num = 0

    def job_spider(self):
        """
        爬虫入口
        """
        for b in self.url_list:
            try:
                self.desc_url_queue.put(b)
            except:
                pass
        # 打印队列长度,即多少条岗位详情 url
        logger.info("队列长度为 {} ".format(self.desc_url_queue.qsize()))

    def post_require(self):
        """
        爬取职位描述
        """
        while True:
            # 从队列中取 url
            url = self.desc_url_queue.get()
            try:
                s = requests.session()
                s.keep_alive = False  # 关闭多余连接
                resp = requests.get(url, headers=HEADERS, timeout=60)
            except Exception as ee:
                logger.error(ee)
                logger.warning(url)
                continue
            if resp.status_code == 200:
                logger.info("爬取第 {} 条岗位详情".format(self.count))
                html = resp.content
                resp.close()
                del (resp)
                self.desc_url_queue.task_done()
                self.count += 1
            else:
                self.desc_url_queue.put(url)
                continue
            try:
                # 数据分析
                data = etree.HTML(html)
                s_list = data.xpath("//div[@class='bmsg job_msg inbox']")
                s = []
                for s_ in s_list:
                    s.append(s_.xpath("string(.)"))
                s = ''.join(s)
                job_info = s.replace("微信", "").replace("分享", "").replace("邮件", "").replace(
                    "\t", ""
                ).strip()
                href = url
                job = ''.join(data.xpath("//div[@class='cn']/h1/text()"))
                salary = ''.join(data.xpath("//div[@class='cn']/strong/text()"))
                company_name = ''.join(data.xpath("//a[@class='catn']/text()"))
                job_cate = re.search("职能类别.*", job_info).group().strip()
                breviary = ''.join(data.xpath("//div[@class='cn']/p[@class='msg ltype']/text()"))
                company_in = data.xpath("//div[@class='com_tag']")
                company_info = []
                for ci_ in company_in:
                    company_info.append(ci_.xpath("string(.)"))
                company_info = '\n'.join(company_info)
                work_address = ''.join(data.xpath("//div[@class='bmsg inbox']/p[@class='fp']/text()"))
                item = {
                    "href": href,
                    "job": job,
                    "salary": salary,
                    "company_name": company_name,
                    "job_cate": job_cate,
                    "breviary": breviary,
                    "job_info": job_info,
                    "company_info": company_info,
                    "work_address": work_address
                }
                self.company.append(item)
                self.url_list.remove(url)
            except Exception as e:
                logger.error(e)
                logger.warning(url)

    def execute_more_tasks(self, target):
        """
        协程池接收请求任务,可以扩展把解析,存储耗时操作加入各自队列,效率最大化
        :param target: 任务函数
        :param count: 启动线程数量
        """
        for i in range(POOL_MAXSIZE):
            self.pool.apply_async(target)

    def run(self):
        """
        多线程爬取数据
        """
        self.job_spider()
        self.execute_more_tasks(self.post_require)
        self.desc_url_queue.join()  # 主线程阻塞,等待队列清空


def spider_main(target_name):
    START_URL = "https://search.51job.com/list/000000,000000,0000,00,9,99,{},2,{}.html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=".format(target_name,'{}')
    try:
        spider = JobSpider()
        spider.wd.get(START_URL)
        maxpage=spider.wd.find_element_by_xpath("//div[@class='rt rt_page']").text.split(' ')[-1]
        print("一共{}页".format(maxpage))
        urls = [START_URL.format(p) for p in range(1, int(maxpage)+1)]
        for url in urls:
            logger.info("爬取第 {} 页".format(urls.index(url) + 1))
            spider.wd.get(url)
            bs = spider.wd.find_elements_by_xpath("//a[@class='el']")
            for b in bs:
                try:
                    href = b.get_attribute("href")
                    with open(target_name+"_urls.txt", 'a', encoding='utf-8') as fi:
                        fi.write(href + '\n')
                except Exception as eee:
                    pass
        with open(target_name+'_urls.txt', 'r')as fi:
            bs = fi.readlines()
            for nn in range(len(bs)):
                bs[nn].strip()
            spider.url_list = bs
        with open(target_name+'fail_urls.txt', 'r')as fi:
            bs = fi.readlines()
            for nn in range(len(bs)):
                bs[nn].strip()
            spider.url_list = bs
        start = time.time()
        while True:
            start_url_num = len(spider.url_list)
            print(start_url_num)
            spider.run()
            end_url_num = len(spider.url_list)
            print(end_url_num)
            if start_url_num == end_url_num:
                break
            time.sleep(60 * 10)
        logger.info("总耗时 {} 秒".format(time.time() - start))
    except Exception as e:
        pass
    finally:
        spider.wd.close()
        with open(target_name+".csv", 'a', encoding='utf-8', newline='') as f:
            writer = csv.writer(f)
            for i in spider.company:
                writer.writerow(i.values())
    print("{}条数据没有成功获取:".format(len(spider.url_list)))
    with open(target_name+"fail_urls.txt", 'w') as f:
        f.writelines(spider.url_list)
