def game(file):
    f = open(file, "r")
    line = f.readline()
    i = 1
    sum = 0
    while(line != ""):
        x = line.split(": ")
        l = x[1].split("; ")
        check = True
        for wurf in l:
            if not legal(wurf):
                check = False
        if check:
            sum += i
        line = f.readline()
        i += 1
    return sum
        

def legal(wurf):
    r = 0
    g = 0
    b = 0
    wurf = wurf.split(", ")
    for col in wurf:
        if " r" in col:
            r = int(col[0:2])
        elif " g" in col:
            g = int(col[0:2])
        elif " b" in col:
            b = int(col[0:2])
    if r < 13 and g < 14 and b < 15:
        return True


print(game("input.txt"))