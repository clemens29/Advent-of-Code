def convert(arr, table):
    for i in arr:
        dest = i[0]
        src = i[1]
        ran = i[2]
        for j in range(int(ran)):
            table[int(src)+j] = int(dest)+j
    return table

def seeds(file):
    arr = []
    
    with open(file) as fi:
        f = fi.readlines()
    j = -1
    for line in f:
        if not line[0].isdigit() and line[0] != "\n":
            arr.append([])
            j += 1
        if line[0].isdigit():
            arr[j].append(line.split(" "))
    seeds = arr[0][0]
    table = [i for i in range(int(max(seeds))+1)]
    for k in range(len(arr)):
        for l in range(len(arr[k])):
            arr[k][l][len(arr[k][l])-1] = arr[k][l][len(arr[k][l])-1].split("\n")[0]
    del arr[0]
    for conv in arr:
        table = convert(conv, table)
    min = int(seeds[0])
    for i in seeds:
        if int(table[int(i)]) < min:
            min = int(table[int(i)])
    return min

print(seeds("input.txt"))