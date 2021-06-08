# -*- coding: utf-8 -*-
import os
from time import sleep

import threadpool as threadpool

from settings import base_url
import requests
from lxml import etree
import re


class Spider:
    pat = re.compile("https.*.g")
    data = {
        "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
        "Accept-Encoding": "gzip, deflate, br",
        "Accept-Language": "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2",
        "Cache-Control": "max-age=0",
        "Connection": "keep-alive",
        "Cookie": "UM_distinctid=17984d53b7d1bd-0061378f0bd244-4c3f2c72-f8100-17984d53b7e30d; CNZZDATA1253190062=672080122-1621429242-%7C1622824181",
        "Host": "www.chongdiantou.com",
        "Referer": "https://www.chongdiantou.com/wp/archives/category/reviews/%e5%85%85%e7%94%b5%e5%99%a8",
        "Upgrade-Insecure-Requests": "1",
        'Connection': 'close',
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0"
    }

    def __init__(self):
        pass

    def get_url(self, p):
        response = requests.get(url=base_url.format(p), headers=self.data)
        text = response.text
        html = etree.HTML(text)
        urls = html.xpath("//a[@class='list-title text-md h-2x']/@href")
        result = []
        for url in urls:
            result.append(str(url))
        print(result)
        return result

    def detail(self, u):
        response = requests.get(u)

        html = response.text
        content = etree.HTML(html)
        text = content.xpath("//div[@class='post-content']//div/p")
        img_urls = content.xpath("//div[@class='post-content']//img/@src")
        num = 0
        data = []
        for i in text:
            if len(i.findall('img')) !=0:
                data.append(img_urls[num])
                num = num + 1
            else:
                data.append(i.xpath("string(.)"))

        title = content.xpath("//div[@class='post']/h1/text()")[0].split('/')
        title = ' '.join(title)
        print(title)
        print(len(img_urls))
        try:
            os.mkdir(title)
        except Exception as e:
            print("已经存在该目录")

        with open('./' + title + '/' + title + '_文章.txt', 'w', encoding='utf-8') as f:
            f.write("\n".join(data))
        with open('./' + title + '/' + title + '_图片urls.txt', 'w', encoding='utf-8') as f:
            f.write('\n'.join(img_urls))
        sleep(10)
        for p in img_urls:
            r_ = self.get_img(p)
            open('./' + title + '/' + p.split('/')[-1], 'wb').write(r_)

    def get_img(self, img_u):
        try:
            r = requests.get(url=img_u,headers=self.data)
            return r.content
        except Exception as e:
            print(e)
            sleep(10)
            return self.get_img(img_u)


if __name__ == '__main__':
    s = requests.session()
    s.keep_alive = False
    spider = Spider()
    # data = []
    # for page in range(1, 120):
    #     print("第{}页：".format(page))
    #     for i in spider.get_url(page):
    #         data.append(i)
    #     sleep(10)
    # with open('urls.txt', 'w', encoding='utf-8') as f:
    #     for i in data:
    #         f.write(i+'\n')

    with open('urls.txt', 'r', encoding='utf-8') as f:
        data = f.read()
    data_ = data.split('\n')
    num_ = 973
    for i in data_[num_:]:
        print("处理第{}个：".format(num_ + 1))
        print(i)
        try:
            spider.detail(i)
        except Exception as e:
            print(e)
            spider.detail(i)
        num_ = num_ + 1
