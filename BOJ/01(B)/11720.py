N = int(input())
x = int(input())
list = []
while x>0:
  list.append(x%10)
  x = x//10

print(sum(list))


# print(sum(map(int, input())))

