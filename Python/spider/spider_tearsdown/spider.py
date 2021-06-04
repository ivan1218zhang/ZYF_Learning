# -*- coding: utf-8 -*-
import os

from settings import base_url
import requests
from lxml import etree
from time import sleep
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
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0"
    }

    def __init__(self):
        pass

    def get_url(self, p):
        response = requests.get(url=base_url.format(p),headers=self.data)
        text = response.text
        html = etree.HTML(text)
        urls = html.xpath("//a[@class='list-title text-md h-2x']/@href")
        result = []
        for url in urls:
            result.append(str(url))
        print(result)
        return result

    def detail(self, u):
        result1 = []
        result2 = []
        response = requests.get(url=u)
        text = response.text
        html = etree.HTML(text)
        f_img = html.xpath(
            "//div[@class='post']/div[@class='post-poster rounded mb-4']/div[@class='media media-3x1']/div[@class='media-content']/@style")
        f_img = self.pat.search(str(f_img)).group()
        title = html.xpath("//div[@class='post']/h1/text()")[0].split('/')
        title = ' '.join(title)
        print(title)
        content1 = html.xpath("//div[@class='nc-light-gallery']/p/span")
        content2 = html.xpath("//div[@class='nc-light-gallery']/p")

        img_urls1 = [f_img]
        img_urls2 = [f_img]
        try:
            os.mkdir(title)
        except Exception as e:
            print("已经存在该目录")

        result1.append("封面:" + f_img + '\n')
        result1.append("标题:" + title + '\n')

        result2.append("封面:" + f_img + '\n')
        result2.append("标题:" + title + '\n')

        for c in content1:
            img = c.findall('img')
            if len(img) != 0:
                result1.append('\n' + img[0].get('src'))
                img_urls1.append(img[0].get('src'))
            result1.append(c.xpath('string(.)'))

        for c in content2:
            img = c.findall('img')
            if len(img) != 0:
                result2.append('\n' + img[0].get('src'))
                img_urls2.append(img[0].get('src'))
            result1.append(c.xpath('string(.)'))
        print(img_urls2)
        if len(img_urls1) > len(img_urls2):
            result = result1
            img_urls = img_urls1
        else:
            result = result2
            img_urls = img_urls2

        with open('./' + title + '/' + title + '_文章.txt', 'w', encoding='utf-8') as f:
            f.write('\n'.join(result))
        with open('./' + title + '/' + title + '_图片urls.txt', 'w', encoding='utf-8') as f:
            f.write('\n'.join(img_urls))
        for p in img_urls:
            r = requests.get(p)
            open('./' + title + '/' + p.split('/')[-1], 'wb').write(r.content)


if __name__ == '__main__':
    spider = Spider()
    data = []
    for page in range(1, 120):
        print("第{}页：".format(page))
        for i in spider.get_url(page):
            data.append(i)
        sleep(10)
    with open('urls.txt', 'w', encoding='utf-8') as f:
        for i in data:
            f.write(i+'\n')

    with open('urls.txt', 'r', encoding='utf-8') as f:
        data = f.read()
    data = data.split('\n')
    num = 109
    for i in data[num:]:
        print("处理第{}个".format(num + 1))
        print(data[num])
        spider.detail(i)
        num = num + 1
