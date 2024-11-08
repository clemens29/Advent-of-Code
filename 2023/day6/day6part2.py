def game(file):
    with open(file) as fi:
        f = fi.readlines()

    time = f[0].split(": ")[1]
    distance = f[1].split(": ")[1]
    time = time.split(" ")
    distance = distance.split(" ")
    time[len(time) -1] = time[len(time) - 1].split("\n")[0]
    distance[len(distance) -1] = distance[len(distance) - 1].split("\n")[0]
    print(time, distance)
            
    j = 0
    dist = ""
    for i in distance:
        if i.isdigit():
            dist += str(i)
            j += 1
    j = 0
    tim = ""
    for i in time:
        if i.isdigit():
            tim += str(i)
    
    
    print(tim, dist)

    sum = 1
    for i in range(1):
        wins = 0
        time = tim
        distance = dist
        full_time = 0
        for i in range(1,int(time)):
            full_time += i
            full_distance = i*(int(time)-i)
            if full_distance > int(distance):
                wins += 1
        sum *= wins
    return sum



print(game("input.txt"))