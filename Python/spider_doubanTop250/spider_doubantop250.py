# 豆瓣top250

import requests
from requests.packages.urllib3.exceptions import InsecureRequestWarning
from lxml import etree
import csv
import matplotlib.pyplot as plt
import matplotlib

requests.packages.urllib3.disable_warnings(InsecureRequestWarning)

# 设置matplotlib正常显示中文和负号
matplotlib.rcParams['font.sans-serif'] = ['SimHei']  # 用黑体显示中文
matplotlib.rcParams['axes.unicode_minus'] = False  # 正常显示负号


def spider(x):
    # 目标网站
    url = "https://movie.douban.com/top250?start=" + str(25 * x)
    # 模拟get请求的请求头
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36',
    }
    # 使用requests库 获取get请求的结果
    response = requests.get(url=url, headers=headers)
    html = etree.HTML(response.text)
    # 获得电影名字
    titles = html.xpath("//span[@class = 'title'][1]")
    # 获得电影导演演员和年份
    p = html.xpath("//div[@class='bd']/p[1]/text()")
    staff = p[0::2]
    year = p[1::2]
    # 获得电影评分
    star = html.xpath("//span[@class='rating_num']")
    # 将获取数据整合并返回
    result = []
    n = x * 25
    for i in range(len(titles)):
        n = n + 1
        s = str(staff[i]).strip('\n').strip(' ')
        print(s)
        result.append([str(n), titles[i].text, str(year[i]).strip('\n').strip(' ')[0:4], star[i].text, s])
    return result


# 制作年份直方图
def histogram(data):
    ax = plt.hist(data, bins=30, facecolor="blue", edgecolor="black", alpha=0.7)
    # 显示横轴标签
    plt.xlabel("年份")
    # 显示纵轴标签
    plt.ylabel("频数/频率")
    # 显示图标题
    plt.title("豆瓣Top250年份的频数/频率分布直方图")
    plt.xticks(fontsize=14)
    plt.savefig('./star.jpg')
    plt.show()


# 制作评分饼图
def pie(years, nums):
    plt.figure(figsize=(10, 10))
    patches, l_text, p_text = plt.pie(x=nums, labels=years, autopct='%.1f%%', shadow=True, labeldistance=1.3,
                                      pctdistance=1.2)
    for t in l_text:
        t.set_size(11)
    for t in p_text:
        t.set_size(9)
    plt.title('豆瓣Top250各评分占比', y=1.1, size=20)
    plt.savefig('./year.jpg')
    plt.show()


def main():
    # 将数据写入csv
    headers = ["排名", "电影", "年份", "评分", "导演演员"]
    with open('doubantop250.csv', 'w', newline='', encoding='utf-8-sig')as f:
        f_csv = csv.writer(f)
        f_csv.writerow(headers)
        for i in range(10):
            rows = spider(i)
            f_csv.writerows(rows)
    # 从csv读取年份数据
    with open('doubantop250.csv', 'r', encoding='utf-8-sig') as csvfile:
        reader = csv.reader(csvfile)
        year = [row[2] for row in reader]
    year_ = []
    for i in year[1:]:
        year_.append(int(i))
    year_.sort()
    histogram(year_)
    # 从csv读取评分数据
    with open('doubantop250.csv', 'r', encoding='utf-8-sig') as csvfile:
        reader = csv.reader(csvfile)
        star = [row[3] for row in reader]
    dic = {}
    for i in star[1:]:
        dic[i] = dic.get(i, 0) + 1
    pie(dic.keys(), dic.values())


if __name__ == '__main__':
    main()
    print("完成！")
