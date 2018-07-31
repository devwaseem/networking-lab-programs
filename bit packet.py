word = input("Enter the bits:")
data = []
for i in word:
    data.append(int(i))
o=0
stuff_indexes = []
for i,e in enumerate(data):
    if e == 1:
        o += 1
        if o == 5:
            print("found",i)
            stuff_indexes.append(i)
    else:
        o = 0
print("data before destuffing:\n {}".format(data))
print("data size before destuffing: {}".format(len(data)))

for i,e in enumerate(stuff_indexes):
    e = e-i
    print(e+1,e+2)
    data = data[0:e+1] + data[e+2:]
print("data after destuffing:\n {}".format(data))
print("data size after destuffing: {}".format(len(data)))
