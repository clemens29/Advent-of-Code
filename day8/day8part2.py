map = []
steps = []

def route(file):
    with open(file) as fi:
        f = fi.readlines()
    
    fac = 100
    for i, line  in enumerate(f):
        line = line.split("\n")[0]
        
        if i == 0:
            steps = line*fac
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
            next = []
            for i in map:
                if i[0][2] == "A":
                    next.append(i[0])
        next = getNext(next, step)
        count += 1
        z = 0
        for i in next:
            if i[2] == "Z":
                z += 1
        if z == len(next):
            return count
        if count % 100000000 == 0:
            print(count, next)

def getNext(next, step):
    tmp = ["" for i in range(len(next))]
    for i in map:
        for n in range(len(next)):
            if next[n] in i[0]:
                if step == "L":
                    tmp[n] = i[1]
                else:
                    tmp[n] = i[2]
    return tmp


print(route("input.txt"))