symbs = ["+", "-", "/", "&", "@", "*", "=", "$", "%", "#", ","]

def adj_nums(file):
    with open(file) as my_file:
        f = my_file.readlines()
    sum = 0
    j = 0
    for line in f:
        num = ""
        print(line)
        isnum = False
        for i in range(len(line)):
            if line[i].isdigit():
                if not isnum:
                    num = ""
                    isnum = True
                num += line[i]
            
            if isnum and (not line[i].isdigit() or i == len(f[0]) - 1):
                
                first = i - len(num) - 1
                last = i
                print(str(j) + " " + num + " " + str(first) + " " + str(last))
                isnum = False
                check = True
                for c in range(first,last+1):
                        for d in range(j-1, j+2):
                                if d >= 0 and d < len(f) and c >= 0 and c < len(f[0]):
                                    if f[d][c] != "." and not f[d][c].isdigit():
                                        check = False   
                                        print(f[d][c]) 
                                    if f[d][c] == "*":
                                         
                if not check:
                    sum += int(num)
        j += 1
    return sum
        
print(adj_nums("test.txt"))