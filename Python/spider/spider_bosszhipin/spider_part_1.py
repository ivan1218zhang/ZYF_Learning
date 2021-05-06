# -*- coding: UTF-8 -*-
from selenium import webdriver
import csv
import re
from settings import base_url, city_list


def spider_1(city_id):
    driver = webdriver.Chrome('chromedriver.exe')

    result = []
    p_years0 = re.compile('.*以')
    p_years1 = re.compile('.*年')
    p_years2 = re.compile('经验不限')
    p_sal = re.compile('[0-9]+')
    for i in range(1, 11):
        url = base_url.format(city_id, i, i)
        driver.get(url)
        name = driver.find_elements_by_xpath("//span[@class='job-name']/a")
        sal = driver.find_elements_by_xpath("//span[@class='red']")
        require = driver.find_elements_by_xpath("//div[@class='job-limit clearfix']/p")
        welfare = driver.find_elements_by_xpath("//div[@class='info-desc']")
        duty_tags = driver.find_elements_by_xpath("//div[@class='tags']")
        detail_hrefs = driver.find_elements_by_xpath("//span[@class='job-name']/a")
        for j in range(len(name)):
            data = {"职位名称": name[j].text, "薪资下限": "", "薪资上限": "", "工作地点": "", "年限要求": "", "学历要求": "", "福利待遇": "",
                    "工作职责": "", "任职资格": "", "公司_url": "", "detail_url": ""}
            sal_ = sal[j].text.split('-')
            data["薪资下限"] = sal_[0] + 'K'
            data["薪资上限"] = p_sal.search(sal_[1][0:2]).group() + 'K'
            data["工作地点"] = ''
            require_ = require[j].text
            m = p_years0.match(require_)
            if None != m:
                data["年限要求"] = require[j].text[:len(m.group()) + 1]
                data["学历要求"] = require[j].text[len(m.group()) + 1:]
            else:
                m = p_years1.match(require_)
                if None != m:
                    data["年限要求"] = m.group()
                    data["学历要求"] = require[j].text[len(m.group()):]
                else:
                    m = p_years2.match(require_)
                    if None != m:
                        data["年限要求"] = m.group()
                        data["学历要求"] = require[j].text[len(m.group()):]
                    else:
                        data["年限要求"] = ''
                        data["学历要求"] = require[j].text
            data["福利待遇"] = welfare[j].text
            data["工作职责"] = duty_tags[j].text
            data["任职资格"] = ''
            data["公司_url"] = ''
            data["detail_url"] = detail_hrefs[j].get_attribute('href')
            result.append(data)

    # header = ['职位名称', '薪资下限', '薪资上限', '工作地点', '年限要求', '学历要求', '福利待遇', '工作职责', '任职资格', '公司_url', 'detail_url']
    driver.close()
    target = ''
    for i in city_list:
        if i['code'] == city_id:
            target = i['city']
            break
    with open('{}_part1.csv'.format(target), 'w', encoding='utf-8', newline='') as f:
        writer = csv.writer(f)
        # writer.writerow(header)
        for i in result:
            writer.writerow(
                [i["职位名称"], i["薪资下限"], i["薪资上限"], i["工作地点"], i["年限要求"], i["学历要求"], i["福利待遇"], i["工作职责"], i["任职资格"],
                 i["公司_url"], i["detail_url"]])
