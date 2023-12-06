from sys import argv

def converter(nums,i,converts):
    #convertPossibs = convert.split("\n")
    for j,num in enumerate(nums):
        for k,convert in enumerate(converts.split("\n")):
            if num>=int(convert.split(" "))[1] and num<=int(convert.split(" ")[0])+int(convert.split(" ")[2]):
                nums[i]=int(convert.split(" ")[0])+int(convert.split(" ")[2])
    return nums

f = open(argv[1])
content = f.read()
converts = content.split("\n\n")
for i in range(1,len(converts)):
    converts[i]=converts[i].split(":\n")[1]
converts[0] = converts[0].split(": ")[1]

nums=converts[0].split(" ")
for i,convert in enumerate(converts):
    nums=converter(nums,i,convert)
lowest=nums[0]
for i,n in nums:
    if n<lowest:
        lowest=n
print(n)