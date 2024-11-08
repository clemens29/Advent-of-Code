import itertools

def hot_springs(file):
    ans = 0
    with open(file) as fi:
        f = fi.read().splitlines()
    for i,line in enumerate(f):
        field = line.split(" ")[0]
        field += '?'
        field *= 5
        field = field[:-1]
        print(i)
        nums = line.split(" ")[1].split(",")*5
        for i in range(len(nums)):
            nums[i] = int(nums[i])
        ans += getCount(field, nums)

    return ans

def getCount(field, nums):
    f1 = field
    count = 0
    idxes = []
    for i,l in enumerate(field):
         if l == "?":
              idxes.append(i)
    n = len(idxes)
    lst = list(itertools.product(["#", "."], repeat=n))
    for i in lst:
            for j in range(n):
                f1 = f1[:idxes[j]] + i[j] + f1[idxes[j]+1:]
            if isValid(f1, nums):
                count += 1
    return count

def isValid(field, nums):
    vals = []
    val = 0
    for l,i in enumerate(field):
        if i == "#":
            val += 1
        if (i == "." and val != 0) or (l == len(field)-1 and val != 0):
            vals.append(val)
            val = 0

    return nums == vals

print(hot_springs("test.txt"))