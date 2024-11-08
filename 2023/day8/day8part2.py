map = []
steps = []
from math import lcm

def route(file):
    with open(file) as fi:
        f = fi.readlines()
    
    for i, line  in enumerate(f):
        line = line.split("\n")[0]
        
        if i == 0:
            steps = line*1000000
        else:
            ind = line.split(" = ")
            if len(ind) == 2:
                src = ind[0]
                left = ind[1].split(", ")[0].split("(")[1]
                right = ind[1].split(", ")[1].split(")")[0]
                map.append([src, left, right])
    count = 0
    a_s = []
    for i in map:
        if i[0][2] == "A":
            a_s.append(i[0])
    print(a_s)
    l = []
    for next in a_s:
        count = 0
        for step in steps:
            next = getNext(next, step)
            count += 1
            print(next)
            if next[2] == "Z":
                l.append(count)
                break
    return lcm(*l)

def getNext(next, step):
    for i in map:
        if next in i[0]:
            if step == "L":
                return i[1]
            else:
                return i[2]


print(route("input.txt"))