north = 0
east = 1
south = 2
west = 3

symbs = {'|':(north,south), '-':(east,west), 'L': (north,east), 'J':(north,west), '7':(south,west), 'F':(south,east)}
start_s = (0,0)

def pipe_maze(file):
    with open(file) as fi:
        f = fi.readlines()
    for i,line in enumerate(f):
        f[i] = line.strip()
        if "S" in line:
            start_s = (i,line.index("S"))
    for l in f:
        print(l)
    for i in symbs:
        start = start_s
        f[start[0]] = f[start[0]][:start[1]] + i + f[start[0]][start[1]+1:]
        if i == "F":
            print(getNext(i, start, f))

def getNext(i, start, f):
        next = (f[start[0]-1][start[1]], f[start[0]][start[1]+1] , f[start[0]+1][start[1]], f[start[0]][start[1]-1])
        print(next)
        for s in next:
                if s[0] == start_s[0] and s[1] == start_s[1]:
                    return i
        print()
            
        print(symbs[f[start[0]][start[1]]])
        for n_i,n in enumerate(next):
                print(symbs[n])
                if n_i == 0:
                    if symbs[i][0] == 0 or symbs[i][1] == 0 and symbs[n][0] == 2 or symbs[n][1] == 2:
                        start = (start[0]-1,start[1])
                elif n_i == 1:
                    if symbs[i][0] == 1 or symbs[i][1] == 1 and symbs[n][0] == 3 or symbs[n][1] == 3:
                        start = (start[0],start[1]+1)
                elif n_i == 2:
                    if symbs[i][0] == 2 or symbs[i][1] == 2 and symbs[n][0] == 0 or symbs[n][1] == 0:
                        start = (start[0]+1,start[1])
                elif n_i == 3:
                    if symbs[i][0] == 3 or symbs[i][1] == 3 and symbs[n][0] == 1 or symbs[n][1] == 1:
                        start = (start[0],start[1]-1)
        getNext(i, start, f)
                
        



        
    

print(pipe_maze('test.txt'))