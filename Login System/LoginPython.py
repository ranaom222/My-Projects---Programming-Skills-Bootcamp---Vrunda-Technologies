e1="om@gmail.com"
e2="om@gmail.com"
p1="om123"
p2="om123"

print("email-1:",e1,",","pass-1:",p1)
print("email-1:",e2,",","pass-1:",p2)

if e1 == e2:
    if p1 == p2:
        print("Success !")
    else:
        print("Wrong Credentials !")
else:
    print("Wrong Credentials !")
