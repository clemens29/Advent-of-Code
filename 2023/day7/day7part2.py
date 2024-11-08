class Cards:
    def __init__(self, hand, bid):
        self.hand = hand
        self.bid = bid
        self.value = 0

values = {"J":1, "2":2, "3":3, "4":4, "5":5, "6":6, "7":7, "8":8, "9":9, "T":10 , "Q":12, "K":13, "A":14}


def getVal(hand, symb):
    for i in range(len(hand)):
        if hand[i] == "J":
            hand = hand[:i] + str(symb) + hand[i + 1:]
            

    pair = threes = fours = fives = full_house = two_pair = False
    two_pair_count = 0
    duplicateFrequencies = {}
    for i in set(hand):
        duplicateFrequencies[i] = hand.count(i)
    for key,value in duplicateFrequencies.items():
        if value == 2:
            pair = True
            two_pair_count += 1
        elif value == 3:
            threes = True
        elif value == 4:
            fours = True
        elif value == 5:
            fives = True
    if two_pair_count == 2:
        two_pair = True
    if pair and threes:
        full_house = True
    vals = [pair, two_pair, threes, full_house, fours, fives]
    for i in range(len(vals)-1, -1, -1):
        if vals[i]:
            return i+1
    return 0

def getRank(h1, h2):
    #print(h1, h2)
    for i in range(len(h1)):
        if values[h1[i]] > values[h2[i]]:
           # print(values[h1[i]], values[h2[i]])
            return True
        elif values[h1[i]] < values[h2[i]]:
            return False
    return False

def getValJ(h):
    high = 0
    for i in range(2,14):
        symb = i
        if i == 10:
            symb = "T"
        elif i == 11:
            symb = "Q"
        elif i == 12:
            symb = "K"
        elif i == 13:
            symb = "A"
        new = getVal(h, symb)
        if new > high:
            high = new
    return high

def compare(h1, h2):
    if "J" not in h1:
        v1 = getVal(h1, "x")
    if "J" not in h2:
        v2 = getVal(h2, "x")
    if "J" in h1:
        v1 = getValJ(h1)
    if "J" in h2:
        v2 = getValJ(h2)
    if v1 > v2:
        return True
    elif v1 == v2:
        if getRank(h1,h2):
            return True
    return False

def camelCards(file):
    ans = 0
    with open(file) as fi:
        f = fi.readlines()
    hands = []
    for card in f:
        hand = card.split(" ")[0]
        bid = card.split(" ")[1]
        bid = bid.split("\n")[0]
        c = Cards(hand, bid)
        hands.append(c)
    for i in range(len(hands)):
        for j in range(i, len(hands)):
            if compare(hands[i].hand, hands[j].hand):
                hands[i], hands[j] = hands[j], hands[i]
    for i,hand in enumerate(hands):
        print(hand.hand)
        ans += int(hand.bid) * (i+1)
    return ans

print(camelCards("input.txt"))