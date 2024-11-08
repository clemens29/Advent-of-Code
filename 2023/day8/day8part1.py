map = []
steps = []

def route(file):
    with open(file) as fi:
        f = fi.readlines()
    

    for i, line  in enumerate(f):
        line = line.split("\n")[0]
        
        if i == 0:
            steps = line*100
        else:
            ind = line.split(" = ")
            if len(ind) == 2:
                src = ind[0]
                left = ind[1].split(", ")[0].split("(")[1]
                right = ind[1].split(", ")[1].split(")")[0]
                map.append([src, left, right])
    count = 0
    for step in steps:
        if count == 0:
            next = "AAA"
        next = getNext(next, step)
        count += 1
        if next == "ZZZ":
            return count

def getNext(next, step):
    for i in map:
        if next in i[0]:
            if step == "L":
                return i[1]
            else:
                return i[2]


print(route("input.txt"))