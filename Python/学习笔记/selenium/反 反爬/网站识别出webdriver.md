+2021.1.8
+网页通过查看浏览器的navigator.webdriver的值 确认是否是webdriver
+所以改变这个值就能不被识别出来


'''python
# -*- coding: utf-8 -*-
from selenium.webdriver import Chrome, ChromeOptions
chrome_driver= Chrome(executable_path=r'C:\chromedriver.exe')


# 用JS在打开网页前先执行预定的代码 改变navigator.webdriver的值
chrome_driver.execute_cdp_cmd("Page.addScriptToEvaluateOnNewDocument", {
  "source": """
    Object.defineProperty(navigator, 'webdriver', {
      get: () => undefined
    })
  """
})



chrome_driver.get("https://kyfw.12306.cn/otn/resources/login.html")

'''