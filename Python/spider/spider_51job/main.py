from ResultProcess import resultProcess
from Spider import spider_main
if __name__ == '__main__':
    key_word="专利"
    spider_main(key_word)
    resultProcess(key_word)