from cStringIO import StringIO

def printArr(arr):
    printer = StringIO()
    for i in arr: printer.write(str(i))
    finalVal = printer.getvalue()
    printer.close()
    return finalVal

def wtob(c):
    ascii = ord(c)
    bit = []
    while ascii != 0:
        bit.append(ascii % 2)
        ascii = ascii / 2
    bit.reverse()
    return bit
word = str(raw_input("Enter Word: "))
binary = []
for i in word:
   for j in  wtob(i):
       binary.append(j)


print("Data size before stuffing: {}".format(len(binary)))
print("Data before stuffing: {}".format(printArr(binary)))
o=0
stuff_indexes = []
for i,e in enumerate(binary):
    if e == 1:
        o += 1
        if o == 5: stuff_indexes.append(i)
    else: o = 0
for i in stuff_indexes: binary.insert(i+1,0)
print("Data size after stuffing: {}".format(len(binary)))
print("Data after stuffing: {}".format(printArr(binary)))
packet_length = int(raw_input("\nEnter Packet Size: "))
final_length = len(binary)
while final_length % packet_length != 0: final_length += 1
while len(binary) < final_length: binary.append(0)
packets = len(binary) // packet_length
for i,e in enumerate((lambda A, n=packets: [A[i:i+n] for i in range(0, len(A), n)])(binary)):
    print("\nPACKET NO: {}".format(i+1))
    print("PACKET DATA: {}".format(printArr(e)))
