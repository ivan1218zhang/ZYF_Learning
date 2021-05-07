# -*- coding: UTF-8 -*-
from selenium import webdriver
from settings import city_list, target_url,base_url
from time import sleep
import csv
import re


class SpiderLogin:
    pattern = re.compile('[0-9-]+人[以上]*')

    def __init__(self):
        self.wd = webdriver.Chrome('chromedriver.exe')

    def login(self):
        self.wd.get(target_url)
        print("登陆成功后按任意键")
        input()

    def spider(self, url):
        try:
            self.wd.get(url)
            sleep(10)
            company_url='https://www.zhipin.com/gongsi/'+self.wd.find_element_by_xpath("//div[@class='company-info']/a[2]").get_attribute('href').split('/')[-1]
            address = self.wd.find_element_by_xpath("//div[@class='location-address']")
            try:
                require = self.wd.find_element_by_xpath("//div[@class='job-sec'][1]/div[@class='text']")
            except Exception:
                require = self.wd.find_element_by_xpath("//div[@class='job-sec company-info'][1]/div[@class='text']")
            dic = {'公司_url': company_url, '工作地点': address.text, '任职资格': require.text}
            return dic
        except Exception as e:
            sleep(15)
            if self.wd.current_url != url:
                return {'公司_url': '', '工作地点': '', '任职资格': ''}
            print(e)
            print("网页出错 问题解决后按任意键继续")
            input()
            return self.spider(url)

    def read(self, f_path):
        text = []
        with open(f_path, 'r', encoding='utf-8') as f:
            reader = csv.reader(f)
            for i in reader:
                text.append(i)
        return text

    def write(self, header_, data, f_path):
        try:
            with open(f_path, 'w', encoding='utf-8', newline='') as f:
                writer = csv.writer(f)
                writer.writerow(header_)
                writer.writerows(data)
        except Exception as e:
            print(e)
            print(data)
            print("csv出错 解决后按任意键继续")
            input()
            self.write(data)

    def wd_close(self):
        self.wd.close()

    def spider_company(self, url):
        try:
            self.wd.get(url)
            sleep(10)
            try:
                show1 = self.wd.find_element_by_xpath(
                    "//div[@class='job-sec'][1]/div[@class='text fold-text']/a[@class='more-view']")
                show1.click()
            except Exception:
                pass
            try:
                show2 = self.wd.find_element_by_xpath("//div[@class='business-detail']/label/span")
                show2.click()
            except Exception:
                pass
            name = self.wd.find_element_by_xpath("//div[@class='info']/h1[@class='name']").text
            try:
                code = self.wd.find_element_by_xpath("//li[@class='col-auto'][4]").text.split('：')[1]
            except Exception:
                code=''
            try:
                industry = self.wd.find_element_by_xpath("//a[@class='industry-link']").text
            except Exception:
                industry=''
            try:
                companytype = self.wd.find_element_by_xpath("//ul/li[@class='col-auto'][1]").text.split('：')[1]
            except Exception:
                companytype=''
            try:
                staffsize = self.pattern.search(self.wd.find_element_by_xpath("//div[@class='info']/p").text).group()
            except Exception:
                staffsize=''
            # qualification = self.wd.find_element_by_xpath().text
            qualification = ""
            try:
                foundtime = \
                    self.wd.find_element_by_xpath(
                        "//div[@class='business-detail show-business-all']/ul/li[3]").text.split('：')[
                        1]
            except Exception:
                foundtime=''
            try:
                representative = self.wd.find_element_by_xpath(
                    "//div[@class='business-detail show-business-all']/ul/li[1]").text.split('：')[1]
            except Exception:
                representative=''
            try:
                registerCapital = self.wd.find_element_by_xpath(
                    "//div[@class='business-detail show-business-all']/ul/li[2]").text.split('：')[1]
            except Exception:
                registerCapital=''
            # registerArea = self.wd.find_element_by_xpath().text
            registerArea = ""
            try:
                registerAddress = self.wd.find_element_by_xpath(
                    "//div[@class='business-detail show-business-all']/ul/li[@class='col-auto'][3]").text.split('：')[1]
            except Exception:
                registerAddress=''
            try:
                businessScope = self.wd.find_element_by_xpath("//li[@class='col-auto'][5]").text.split('：')[1]
            except Exception:
                businessScope=''
            try:
                businessState = self.wd.find_element_by_xpath("//li[@class='col-auto'][2]").text.split('：')[1]
            except Exception:
                businessState=''
            try:
                unitIntroduction = self.wd.find_element_by_xpath(
                    "//div[@class='job-sec'][1]/div[@class='text fold-text']").text
            except Exception:
                unitIntroduction = ''
            dic = {'单位名称': name, '统一信用代码': code, '所属行业': industry, '单位类型': companytype, '人员规模': staffsize,
                   '荣誉资质': qualification, '成立时间': foundtime, '法人代表': representative, '注册资金': registerCapital,
                   '所属地区': registerArea, '企业地址': registerAddress, '经营范围': businessScope, '经营状态': businessState,
                   '单位简介': unitIntroduction, '公司_url': url}
            return dic
        except Exception as e:
            sleep(15)
            if self.wd.current_url != url:
                return {'单位名称': '', '统一信用代码': '', '所属行业': '', '单位类型': '', '人员规模': '',
                   '荣誉资质': '', '成立时间': '', '法人代表': '', '注册资金': '',
                   '所属地区': '', '企业地址': '', '经营范围': '', '经营状态': '',
                   '单位简介': '', '公司_url': url}
            print(e)
            print("网页出错 问题解决后按任意键继续")
            input()
            return self.spider_company(url)

    def get_company(self, path):
        data = self.read(path)
        dic = {}
        cp_url = []
        for i in data[1:]:
            dic[i[9]] = ''
        for i in dic.keys():
            if i != '':
                cp_url.append(i)
        return cp_url

    def spider_1(self,city_id):
        driver =self.wd

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


if __name__ == '__main__':
    spider = SpiderLogin()
    spider.login()
    for k in range(3, 4):
        company_url = spider.get_company(city_list[k]['city'] + ".csv")
        l = len(company_url)
        flag = 0
        company_data = []
        for i in company_url:
            flag = flag + 1
            print('处理第{}个数据 共{}个'.format(flag, l))
            company_dic = spider.spider_company(i)
            print(company_dic)
            company_data.append([company_dic['单位名称'], company_dic['统一信用代码'], company_dic['所属行业'], company_dic['单位类型'],
                                 company_dic['人员规模'], company_dic['荣誉资质'], company_dic['成立时间'], company_dic['法人代表'],
                                 company_dic['注册资金'], city_list[k]['city'], company_dic['企业地址'], company_dic['经营范围'],
                                 company_dic['经营状态'], company_dic['单位简介'], company_dic['公司_url']])
        header = ['单位名称', '统一信用代码', '所属行业', '单位类型', '人员规模', '荣誉资质', '成立时间', '法人代表', '注册资金', '所属地区', '企业地址', '经营范围',
                  '经营状态', '单位简介', '公司_url']
        spider.write(header, company_data, city_list[k]['city'] + "_公司.csv")
        try:
            spider.wd.delete_all_cookies()
        except Exception as e:
            print(e)
            input()
    spider.wd_close()
