def oasis(file):
    with open(file) as fi:
        f = fi.readlines()
    ans = 0
    for line in f:
        line = line.split("\n")[0]
        nums = line.split(" ")
        triangle = [nums]
        for j in range(len(nums)-1, 0, -1):
            triangle.append(["" for i in range(j)])
        for i,step in enumerate(triangle):
            for k in range(len(step)-1):
                diff = int(step[k+1]) - int(step[k])
                triangle[i+1][k] = diff
            c = 0
            for n in step:
                if n == 0:
                    c += 1
            if c == len(step):
                ans += getVal(triangle, i)
                break
    return ans

def getVal(triangle, i):
    print(triangle)
    for j in range(i-1, -1, -1):
        triangle[j].append(int(triangle[j][-1]) + int(triangle[j+1][-1]))
    return triangle[0][-1]


print(oasis("input.txt"))