import requests
from requests.packages.urllib3.exceptions import InsecureRequestWarning
from lxml import etree
import datetime
from dateutil.relativedelta import relativedelta
import csv

requests.packages.urllib3.disable_warnings(InsecureRequestWarning)

end = datetime.date.today()  # 今天日期
begin = end - relativedelta(years=1)  # 去年日期 也就是第一天
# 处理今天日期用于post的data
today = str(end)
today = today[0:4] + today[5:7] + today[8:10]


# 爬虫部分
def spider(date, weekday_):
    url = "https://www.hkexnews.hk/sdw/search/mutualmarket.aspx?t=sh"
    data = {
        "__VIEWSTATE": "/wEPDwUJNjIxMTYzMDAwZGSFj8kdzCLeVLiJkFRvN5rjsPotqw==",
        "__VIEWSTATEGENERATOR": "3C67932C",
        "__EVENTVALIDATION": "/wEdAAdbi0fj+ZSDYaSP61MAVoEdVobCVrNyCM2j+bEk3ygqmn1KZjrCXCJtWs9HrcHg6Q64ro36uTSn/Z2SUlkm9HsG7WOv0RDD9teZWjlyl84iRMtpPncyBi1FXkZsaSW6dwqO1N1XNFmfsMXJasjxX85ju3P1WAPUeweM/r0/uwwyYLgN1B8=",
        "today": today,
        "sortBy": "stockcode",
        "sortDirection": "asc",
        "alertMsg": "",
        "txtShareholdingDate": date,
        "btnSearch": "Search"
    }
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36'
    }
    # post请求
    response = requests.post(url, headers=headers, data=data)
    html = response.text
    # 用xpath解析html
    tree = etree.HTML(html)
    # 定位数据
    currentday = tree.xpath("//h2[@class='ccass-heading']/span")
    if currentday[0].text[19:] == date:
        codes = tree.xpath("//td[@class='col-stock-code']/div[@class='mobile-list-body']")
        names = tree.xpath("//td[@class='col-stock-name']/div[@class='mobile-list-body']")
        numbers = tree.xpath("//td[@class='col-shareholding']/div[@class='mobile-list-body']")
        percents = tree.xpath("//td[@class='col-shareholding-percent']/div[@class='mobile-list-body']")
        # 二维列表 以便写进csv
        datas = []
        for i in range(len(codes)):
            datas.append([date, weekday_, codes[i].text, names[i].text, numbers[i].text, percents[i].text])
        print(date, "  ", response.status_code)
        return datas


if __name__ == '__main__':
    # 表头
    header = ['Date', 'Day', 'Stock Code', 'Stock Name', 'Shareholding in CCASS',
              '% of the total number of Issued Shares']
    # 表头写进csv
    with open('stock.csv', 'w', newline='', encoding='utf-8-sig')as f:
        f_csv = csv.writer(f)
        f_csv.writerow(header)

    for i in range((end - begin).days + 1):
        day = begin + datetime.timedelta(days=i)
        # 增加星期几的数据
        weekday = day.strftime("%w")
        if weekday == '1':
            weekday = 'Monday'
        if weekday == '2':
            weekday = 'Tuesday'
        if weekday == '3':
            weekday = 'Wednesday'
        if weekday == '4':
            weekday = 'Thursday'
        if weekday == '5':
            weekday = 'Friday'
        if weekday == '6':
            weekday = 'Saturday'
        if weekday == '0':
            weekday = 'Sunday'
        # 处理日期用于post的data
        day_ = str(day)
        day_ = day_[0:4] + '/' + day_[5:7] + '/' + day_[8:10]
        datas = spider(day_, weekday)
        # 数据写进csv
        if datas != None:
            with open('stock.csv', 'a', newline='', encoding='utf-8-sig')as f:
                f_csv = csv.writer(f)
                f_csv.writerows(datas)
    print("完成")
