def cosmic(file):
    with open(file) as fi:
        f = fi.read().splitlines()
    newlines = []
    newcols = []
    for i,line in enumerate(f):
        if "#" not in line:
            newlines.append(i)
    for i in range(len(f[0])):
        c = 0
        for j in range(len(f)):
            if "#" not in f[j][i]:
                c += 1
        if c == len(f):
            newcols.append(i)
    print(newlines, newcols)
    for l in newlines:
        f.insert(l+1, '.'*len(f[0]))
    offset = 0
    for c in newcols:
        for j in range(len(f)):
            f[j] = f[j][:(c+offset)] + '.' + f[j][(c+offset):]
        offset += 1
    c = 1
    nums_index = []
    for i in range(len(f)):
        for j in range(len(f[i])):
            if f[i][j] == '#':
                f[i] = f[i][:j] + str(c) + f[i][j+1:]
                nums_index.append([i,j])
                c += 1
    for i,line in enumerate(f):
        print(i, line)
    print(nums_index)
    ans = 0
    for i in range(len(nums_index)):
        for j in range(i+1, len(nums_index)):
            first = abs(nums_index[j][0] - nums_index[i][0])
            second = abs(nums_index[j][1] - nums_index[i][1])
            diff = first + second
            print(diff)
            ans += diff
    return(ans)

print(cosmic("input.txt"))