def cards(file):
    with open(file) as my_file:
        f = my_file.readlines()
    sum = 0
    card_copies = [1 for i in range(len(f))]
    for line_num,line in enumerate(f):
        line_num += 1
        my_win_cards = 0
        valid = 0
        line = line.split(": ")[1]
        print("line " + str(line_num) + " : " + line)
        nums = line.split(" | ")
        win_nums = nums[0].split(" ")
        my_nums = nums[1].split(" ")
        try:
            win_nums.remove('')
        except:
            a = 1
        try:
            my_nums.remove('')
        except:
            a = 1
        my_nums[len(my_nums)-1] = my_nums[len(my_nums)-1].split("\n")[0]
        valid = getNew(win_nums, my_nums)
        print("worth " + str(my_win_cards) + "valid " + str(valid) )
        for j in range(card_copies[line_num-1]):
            for i in range(line_num, line_num+valid):
                if i < len(f):
                    card_copies[i] += 1
        print(card_copies)
    for i in card_copies:
        sum += i
    print(card_copies)
    return int(sum)

def getNew(win_nums, my_nums):
    my_win_cards = 0
    for i in win_nums:
        if i in my_nums and not i == "":
            my_win_cards += 1
    return my_win_cards

print(cards("input.txt"))