# -*- coding: utf-8 -*- 
'''
Created on 2016-09-27

@author: 鄢真强
'''
import urllib.request
from bs4 import BeautifulSoup
import re
import math
import os




counter = 1
url = "http://disclosure.szse.cn/m/search0425.jsp"


def down(link):
    global counter
    url = 'http://disclosure.szse.cn/'
    url = url + link
    print("完整下载链接："+url)
    urllib.request.urlretrieve(url,'./files/'+str(counter)+".pdf")
    print('已下载'+str(counter)+'个文件')
    counter = counter+1
def post(starttime,endtime):
    postdata = urllib.parse.urlencode({'noticeType': '010301', 'startTime': starttime, 'endTime':endtime})  
    postdata = postdata.encode('gb2312')  
    res = urllib.request.urlopen(url,postdata)
    data = res.read().decode('gb2312')
    soup = BeautifulSoup(data,"html.parser")    
    if None != soup.find(text="没有找到你搜索的公告!"):
        print("查询时间段内无报告")
        return
    pattern = r'(pdf|PDF)'
    herflist = soup.find_all('a')
    if herflist != []:
        for link in herflist:        
            tmp = link.get('href')
            result = re.search(pattern, tmp)
            if result:
                print(tmp)
                down(tmp)    
        
    
if __name__ == '__main__':
    if os.path.exists('files')== False:
        os.mkdir('files')        
    for year in range(2001,2016):
        for month in range(1,12):
            if month<=9:
                startmonth = '0'+str(month)
            else:
                startmonth = str(month)
            if month+1<=9:
                endmonth = '0'+str(month+1)
            else:
                endmonth = str(month+1)
                if endmonth == '12':
                    post(str(year)+'-'+endmonth+'-01',str(year)+'-'+endmonth+'-30')
            starttime = str(year)+'-'+startmonth+'-01' 
            endtime = str(year)+'-'+endmonth+'-01'
            print('starttime='+starttime+'\tendtime='+endtime)         
            post(starttime,endtime)
    

            
            
        
        
        




  
