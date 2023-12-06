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
    dist = [0,0,0,0]
    for i in distance:
        if i.isdigit():
            dist[j] = i
            j += 1
    j = 0
    tim = [0,0,0,0]
    for i in time:
        if i.isdigit():
            tim[j] = i
            j += 1
    sum = 1
    for i in range(len(tim)):
        wins = 0
        time = tim[i]
        distance = dist[i]
        print(time, distance)
        full_time = 0
        for i in range(1,int(time)):
            full_time += i
            full_distance = i*(int(time)-i)
            if full_distance > int(distance):
                wins += 1
        sum *= wins
    return sum


            

print(game("input.txt"))