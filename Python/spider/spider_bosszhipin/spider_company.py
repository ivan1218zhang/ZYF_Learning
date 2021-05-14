# -*- coding: UTF-8 -*-
from settings import city_list
from selenium import webdriver
import json
from spider_login import SpiderLogin
from time import sleep


class CompanySpider:
    def __init__(self):
        self.wd = webdriver.Chrome('chromedriver.exe')


if __name__ == '__main__':
    spider_ = CompanySpider()
    data = {}
    for i in range(45, len(city_list)):
        url = "https://www.zhipin.com/gongsi/_zzz_c{}/".format(city_list[i]['code'])
        spider_.wd.get(url)
        urls = []
        while True:
            u = spider_.wd.find_elements_by_xpath("//div[@class='sub-li']/a[@class='company-info']")
            try:
                for j in u:
                    urls.append(j.get_attribute('href'))
            except:
                input("出错")
                spider_.wd.refresh()
            try:
                sleep(30)
                b = spider_.wd.find_element_by_xpath(
                    "/html[@class='standard']/body/div[@id='wrap']/div[@id='main']/div[@class='company-tab-box company-list']/div[@class='page']/a[@class='next']")

                b.click()
            except Exception as e:
                print(e)
                break
        data[city_list[i]['city']] = urls
    spider_.wd.close()
    with open('urls.txt', 'w', encoding='utf-8') as f:
        content = json.dumps(data)
        f.write(content)
    spider_ = SpiderLogin()
    header = ['单位名称', '统一信用代码', '所属行业', '单位类型', '人员规模', '荣誉资质', '成立时间', '法人代表', '注册资金', '所属地区', '企业地址', '经营范围',
              '经营状态', '单位简介', '公司_url']
    for i in data:
        company_data = []
        for j in data[i]:
            sleep(30)
            try:
                company_dic = spider_.spider_company(j)
            except:
                input("出现问题")
            company_data.append([company_dic['单位名称'], company_dic['统一信用代码'], company_dic['所属行业'], company_dic['单位类型'],
                                 company_dic['人员规模'], company_dic['荣誉资质'], company_dic['成立时间'], company_dic['法人代表'],
                                 company_dic['注册资金'], company_dic['所属地区'], company_dic['企业地址'], company_dic['经营范围'],
                                 company_dic['经营状态'], company_dic['单位简介'], company_dic['公司_url']])
        spider_.write(header, company_data, i + "_公司.csv")
