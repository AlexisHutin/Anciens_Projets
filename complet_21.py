from PIL import Image
import random
from bisect import bisect
#import smbus
from time import sleep
import time

greyscale = [0,0,1,1,1,1,1,1]
 
zonebounds=[36,72,108,144,180,216,252]
 
print "Loading image..."
filename  = "lvh.jpg"
basewidth = 20

def img_resize(img_name,img_size):
    
    img = Image.open(img_name)
    pixels = img.load()
    width = img.size[0]
    height = img.size[1]
    print "%dx%d pixels \n" % img.size

    wpercent = (img_size/float(img.size[0]))
    hsize = int((float(img.size[1])*float(wpercent)))
    img = img.resize((img_size,hsize), Image.ANTIALIAS)
    img = img.rotate(180)
    img=img.convert("L")    
    img.save('convert_pic.jpg')
    print "image reduite %dx%d pixels \n" % img.size
    return img

img=img_resize(filename,basewidth)

print "Allocating..."

width = img.size[0]
height = img.size[1]

line = [[0]* width for x in range(height)]

print "Converting..."
for y in range(height):
    for x in range(width):
        lum=255-img.getpixel((x,y))
        row=bisect(zonebounds,lum)
        value=greyscale[row]
        line[y][x] = value
        
print "Displaying..."
for y in range(height):
    print line[y]
    time.sleep(0.1)
b=0
j=0
i=0
x=0
y=0
t=0
tableau = [[1,3,2],[2,3,3],[3,3,5],[4,3,6],[5,3,8],[6,3,10],[7,3,11],[8,3,12],[9,3,13],[10,4,2],[11,4,3],[12,4,5],[13,4,6],[14,4,8],[15,4,10],[16,4,11],[17,4,12],[18,4,13],[19,5,2],[20,5,3]]
for y in range(height):          
    #for k in range(width):
       # l=k+1
        #print "vanne",l,":",tableau[k]

    for x in range (width):
            #t=x+("a calculer")
	    j=x-1
            a = line [y][x]
            if a!=b :
                print"ouvert"    
                #bus=smbus.SMBus(1)
                #adress=tableau[j][1]
                #bus.write_byte(adress,tableau[j][2])
                b=a
  
   #time.sleep(t)
time.sleep(1)

for x in range (width):
        j=x-1
        a = line [y][x]
        if a!=0 :
            print"reset"   
            #bus=smbus.SMBus(1)
            #adress=tableau[j][1]
            #bus.write_byte(adress,tableau[j][2])

time.sleep(1)

print "FIN"    
