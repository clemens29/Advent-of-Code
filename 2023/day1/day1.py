nums = {
    "one": 1,
    "two": 2,
    "three": 3,
    "four": 4,
    "five": 5,
    "six": 6,
    "seven": 7,
    "eight": 8,
    "nine": 9
}


def sum(file):
    sum = 0
    f = open(file, 'r')
    line = "start"
    while (line != ""):
        first = -1
        last = -1
        line = f.readline()
        linereversed = line[::-1]
        i = 0

        for c in line:
            if first == -1:
                for j in range(i):
                    substring = line[j:i]
                    if substring in nums:
                        first = nums[substring]
                        break
            if first == -1 and c.isdigit():
                first = c
                break
            i += 1

        i = 0    
        for c in linereversed:
            if last == -1:
                for j in range(i):
                    substring = linereversed[j:i]
                    substring = substring[::-1]
                    if substring in nums:
                        last = nums[substring]
                        print(line + str(first) + str(last))
                        break
            if last == -1 and c.isdigit():
                last = c
                break
            i += 1

        if first == -1 or last == -1:
            num = 0
        else:
            num = int(str(first) + str(last))
            sum += num
    return sum

print(sum("input.txt"))