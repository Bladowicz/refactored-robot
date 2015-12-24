#!/usr/bin/python
# coding: utf-8

import calendar
import datetime
import gzip
import random

def fillFile(dateFrom, dateTo, filename, n):
    dateFrom = datetime.datetime.strptime(dateFrom, "%Y%m%d")
    dateTo = datetime.datetime.strptime(dateTo, "%Y%m%d")
    if not dateFrom < dateTo:
        raise
    tsFrom = calendar.timegm(dateFrom.timetuple())
    tsTo = calendar.timegm(dateTo.timetuple())
    with gzip.open(filename, 'w') as fw:
        for x in range(n):
            fw.write("0 'XX{}zzz |a 1 2 3 |b 4 5 6\n".format( random.randint(tsFrom, tsTo)))
            

for Z in range(10):
    fillFile(str(20151201 + Z), str(20151204 + Z), "modelData_2015-12-{:02}.gz".format(1 + Z), 100000)
