import RPi.GPIO as GPIO #Çıkış vermek için bu kütüphaneleri import etmeniz gerekiyor
import time #Ledleri yakınca birer saniye bekleyeceğiz
# blinking function
def blink(pin): #Bir pin i parametre olarak alan metodumuz
        GPIO.output(pin,GPIO.HIGH)
        time.sleep(1)
        GPIO.output(pin,GPIO.LOW)
        time.sleep(1)
        return
# to use Raspberry Pi board pin numbers
GPIO.setmode(GPIO.BOARD)
# set up GPIO output channel
GPIO.setup(11, GPIO.OUT)
# blink GPIO17 50 times
for i in range(0,50):
        blink(11)#11.pini parametre olarak verip ledi 11.pine bağlıyoruz
GPIO.cleanup()