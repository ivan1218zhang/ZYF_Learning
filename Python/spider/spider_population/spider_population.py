# coding=utf-8
# 爬取http://www.chamiji.com/中的表格数据
# 导入程序需要的库
import requests
from requests.packages.urllib3.exceptions import InsecureRequestWarning
from lxml import etree
import csv
import matplotlib.pyplot as plt
import matplotlib

# requests库出现的报错不显示
requests.packages.urllib3.disable_warnings(InsecureRequestWarning)

# 设置matplotlib正常显示中文和负号
matplotlib.rcParams['font.sans-serif'] = ['SimHei']  # 用黑体显示中文
matplotlib.rcParams['axes.unicode_minus'] = False  # 正常显示负号


# 爬虫部分
def spider():
    # 对目标网站发起get请求，并用requests库获得结果
    # 目标网站的url
    url = "http://www.chamiji.com/"
    response = requests.get(url)
    # 用xpath解析html页面
    html = etree.HTML(response.text)
    # 用xpath定位元素，获取数据
    nations = html.xpath("//td[@class='column-1']")
    nations2 = html.xpath("//td[@class='column-1']/a")
    population = html.xpath("//td[@class='column-2']")
    acreage = html.xpath("//td[@class='column-3']")
    area = html.xpath("//td[@class='column-4']")
    rank = html.xpath("//td[@class='column-5']")
    # 将数据放入列表
    result = []
    j = 0
    a = type(nations[0].text)
    # 有些国家有超链接，将有超链接的国家和无超链接的国家分别处理
    for i in range(len(rank)):
        if type(nations[i].text) == a:
            if type(area[i].text) != a:
                result.append([nations2[j].text, population[i].text, acreage[i].text, area[i].text, rank[i].text])
            else:
                result.append([nations2[j].text, population[i].text, acreage[i].text, "", rank[i].text])
            j = j + 1
        else:
            if type(area[i].text) != a:
                result.append([nations[i].text, population[i].text, acreage[i].text, area[i].text, rank[i].text])
            else:
                result.append([nations[i].text, population[i].text, acreage[i].text, "", rank[i].text])
    return result


# 将数据写入csv
def write_csv(data):
    headers = ["名称", "总人口（万）", "面积（万)", "区域", "排名"]
    with open('renkou.csv', 'w', newline='', encoding='utf-8-sig')as f:
        f_csv = csv.writer(f)
        f_csv.writerow(headers)
        f_csv.writerows(data)


# 生成用于制作饼图的数据
def get_data(data):
    # 以字典形式保存数据
    data_pie = {}
    for data_ in data:
        key = data_[3]
        # 网站有些国家不显示区域 修改""为其它 便于饼图展示
        if key == "":
            key = "其它"
        # 巧用字典的get方法 做统计出现次数
        data_pie[key] = data_pie.get(key, 0) + 1
    return data_pie


# 制作区域饼图
def pie(nations, nums):
    plt.figure(figsize=(10, 10))
    # 修改饼图参数 让数据显示更清晰
    patches, l_text, p_text = plt.pie(x=nums, labels=nations, autopct='%.1f%%', shadow=True, labeldistance=1.3,
                                      pctdistance=1.2)
    # 修改饼图字体 观看体验更好
    for t in l_text:
        t.set_size(11)
    for t in p_text:
        t.set_size(9)
    # 为饼图添加标题
    plt.title('目标网站所统计的国家区域占比', y=1.1, size=20)
    # 保存饼图
    plt.savefig('./nations.jpg')
    # 显示饼图
    plt.show()


if __name__ == '__main__':
    data = spider()
    write_csv(data)
    data_pie = get_data(data)
    pie(data_pie.keys(), data_pie.values())
    print("程序完成！")
