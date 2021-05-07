# -*- coding: UTF-8 -*-
from settings import city_list
from spider_login import SpiderLogin

if __name__ == '__main__':
    spider = SpiderLogin()
    spider.login()
    for k in range(10, len(city_list)):
        spider.spider_1(city_list[k]['code'])
        text = spider.read(city_list[k]['city'] + "_part1.csv")
        flag = 0
        l = len(text)
        for i in text:
            flag = flag + 1
            print('处理第{}个数据 共{}个'.format(flag, l))
            detail = spider.spider(i[10])
            i[3] = detail['工作地点']
            i[8] = detail['任职资格']
            i[9] = detail['公司_url']
        header = ['职位名称', '薪资下限', '薪资上限', '工作地点', '年限要求', '学历要求', '福利待遇', '工作职责', '任职资格', '公司_url', 'detail_url']
        spider.write(header, text, city_list[k]['city'] + ".csv")
        company_url = spider.get_company(city_list[k]['city'] + ".csv")
        l = len(company_url)
        flag = 0
        company_data = []
        for i in company_url:
            flag = flag + 1
            print('处理第{}个数据 共{}个'.format(flag, l))
            company_dic = spider.spider_company(i)
            company_data.append([company_dic['单位名称'], company_dic['统一信用代码'], company_dic['所属行业'], company_dic['单位类型'],
                                 company_dic['人员规模'], company_dic['荣誉资质'], company_dic['成立时间'], company_dic['法人代表'],
                                 company_dic['注册资金'], company_dic['所属地区'], company_dic['企业地址'], company_dic['经营范围'],
                                 company_dic['经营状态'], company_dic['单位简介'], company_dic['公司_url']])
        header = ['单位名称', '统一信用代码', '所属行业', '单位类型', '人员规模', '荣誉资质', '成立时间', '法人代表', '注册资金', '所属地区', '企业地址', '经营范围',
                  '经营状态', '单位简介', '公司_url']
        spider.write(header, company_data, city_list[k]['city'] + "_公司.csv")
