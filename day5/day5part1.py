def conv(arr, curr_num):
    for nums in arr:
        dest = int(nums[0])
        src = int(nums[1])
        rng = int(nums[2])
        print(dest, src, rng)
        if src <= curr_num and src+rng-1 >= curr_num:
            curr_num = (curr_num - src ) + dest
        print(curr_num)
        print("\n")
    return curr_num

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
    for k in range(len(arr)):
        for l in range(len(arr[k])):
            arr[k][l][len(arr[k][l])-1] = arr[k][l][len(arr[k][l])-1].split("\n")[0]
    del arr[0]
    loc = []
    for seed in seeds:
        curr_num = int(seed)
        for j in arr:
            curr_num = conv(j, curr_num)
        if seed == "14":
            print("\n")
            print(curr_num)
        loc.append(curr_num)
    return (sorted(loc))

print(seeds("test.txt"))