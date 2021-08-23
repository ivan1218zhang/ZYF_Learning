import csv
import re


def resultProcess(file_name):
    header = ['标题链接', '职位', '薪酬', '公司', '职能类别', '上班地址', '区域', '经验要求', "学历要求", "招聘人数", "发布时间", "口语要求", "专业要求", '公司类型',
              '公司人数', '公司业务', "职位信息(原始数据)", '岗位职责', '岗位要求', '薪资福利', '职位信息']
    data = []
    with open(file_name + '.csv', 'r', encoding='utf-8') as f:
        reader = csv.reader(f)
        for row in reader:
            row_ = []
            for i in row[:5]:
                row_.append(i)
            row_.append(row[8])
            data.append(row_)

    with open(file_name + '.csv', 'r', encoding='utf-8') as f:
        reader = csv.reader(f)
        num = 0
        for row in reader:
            model = {"区域": "", "经验要求": "", "学历要求": "", "招聘人数": "", "发布时间": "", "口语要求": "", "专业要求": ""}
            cates = row[5].split('    ')
            for cate in cates:
                if re.search("[经验]", cate.strip()):
                    model["经验要求"]=cate
                elif re.search("[本科博士大专硕士高中]", cate.strip()):
                    model["学历要求"] = cate
                elif re.search("[招人]", cate.strip()):
                    model["招聘人数"] = cate
                elif re.search("[发布]", cate.strip()):
                    model["发布时间"] = cate
                elif re.search("[良好熟练精通]", cate.strip()):
                    model["口语要求"] = cate
                elif re.search("[-省]", cate.strip()):
                    model["区域"] = cate
                else:
                    if len(cate)<=3:
                        if re.search("[学]", cate.strip()):
                            model["专业要求"] = cate
                        else:
                            model["区域"]=cate
                    else:
                        model["专业要求"] = cate
            for i in model.values():
                data[num].append(i)
            num = num + 1

    with open(file_name + '.csv', 'r', encoding='utf-8') as f:
        reader = csv.reader(f)
        num = 0
        for row in reader:
            model = {'公司类型': '', '公司人数': '', '公司业务': ''}
            tmp = row[7].split()
            l = len(tmp)
            if l == 2:
                model['公司类型'] = tmp[0]
                model['公司业务'] = tmp[1]
            elif l == 3:
                model['公司类型'] = tmp[0]
                model['公司人数'] = tmp[1]
                model['公司业务'] = tmp[2]
            elif l == 4:
                model['公司类型'] = tmp[0]
                model['公司人数'] = tmp[1]
                model['公司业务'] = tmp[2] + ',' + tmp[3]
            if l != 0 and l != 1:
                if not re.search(r'\d', model['公司人数']) and model['公司人数'] != '':
                    model['公司业务'] = model['公司人数'] + ',' + model['公司业务']
                    model['公司人数'] = ''
                if re.search(r'\d', model['公司类型']):
                    model['公司人数'] = model['公司类型']
                    model['公司类型'] = ""
            for i in model.values():
                data[num].append(i)
            num = num + 1

    # 类别所含字
    pattern1 = re.compile(
        '[\d一二三四五六七八九十、\.]{0,2}【?[城市学历公司岗位能年度突破奖员力基本薪资酬劳任职工作要求责待遇福利描述内容专业格带休假如下时间期在这里，你可以]{4,}?[】:：1]{1}')
    with open(file_name + '.csv', 'r', encoding='utf-8') as f:
        reader = csv.reader(f)
        nu = 0
        for row in reader:
            tmp = re.sub('职能类别.*\n*.*\n*.*\n*.*\n*.*', '', row[6]).strip()
            r = re.split(pattern1, tmp)
            t = re.findall(pattern1, tmp)

            first = ""
            model = {"岗位职责": "", "岗位要求": "", "薪资福利": ""}

            data[nu].append(tmp)

            if r[0] != "":
                data[nu].append("")
                data[nu].append("")
                data[nu].append("")
            else:
                if len(t) <= 4:
                    num = 0
                    for i in r[1:]:
                        if len(i) < 8:
                            continue
                        tmp_ = t[num] + i
                        if re.search("[责内容]", t[num]):
                            model['岗位职责'] = tmp_
                        elif re.search("[要求格]", t[num]):
                            model["岗位要求"] = tmp_
                        elif re.search("[薪资福利待遇休假年度]", t[num]):
                            model["薪资福利"] = tmp_
                        num = num + 1
                    data[nu].append(model["岗位职责"])
                    data[nu].append(model["岗位要求"])
                    data[nu].append(model["薪资福利"])
                else:
                    data[nu].append("")
                    data[nu].append("")
                    data[nu].append("")
            num = 0
            if r[0] != "":
                first = r[0]
                data[nu].append(first)
            for i in r[1:]:
                # 补漏
                if len(i) < 8:
                    if re.search('城市', t[num]):
                        if re.search('\d', i) or i == "":
                            continue
                    else:
                        continue
                tmp_ = t[num] + i
                num = num + 1
                data[nu].append(tmp_)
            nu = nu + 1

    with open(file_name + '_处理完.csv', 'w', encoding='utf-8', newline="") as f:
        writer = csv.writer(f)
        writer.writerow(header)
        writer.writerows(data)
