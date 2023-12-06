symbs = ["+", "-", "/", "&", "@", "*", "=", "$", "%", "#", ","]

def adj_nums(file):
    with open(file) as my_file:
        f = my_file.readlines()
    sum = 0
    
    for i,line in enumerate(f):
        if "*" in line:
            box = [[0 for i in range(7)], [0 for i in range(7)], [0 for i in range(7)]]
            box_middle = line.index("*")
            print("line " + str(i+1) + ": " + line)
            print(box_middle)
            nums = []
            for l in range(3):
                for c in range(7):
                    box[l][c] = f[i-1+l][box_middle-3+c]
            
            print(nums)

                    
                    


        


print(adj_nums("test.txt"))
