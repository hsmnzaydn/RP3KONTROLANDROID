from firebase import firebase
import os
import mp3cal

firebase=firebase.FirebaseApplication('https://python-b7487.firebaseio.com')



sayac_led=0
sayac_mp3=0
sayac_mp3_durdur=0
sayac=1
while sayac==1:
    result = firebase.get('/kontroller', 'isik_kontrol')
    result2 = firebase.get('/kontroller', 'mp3_kontrol')
    print('Isik kontrol:',result,'Mp3 kontrol:',result2)
    if result=='off':
        sayac_led=0
    if result2=='off':
        sayac_mp3=0
    if result2=="off" and sayac_mp3_durdur==1:
        mp3cal.durdur()
        sayac_mp3_durdur=0
    if result=='on' and sayac_led==0:
        os.system('python LedYak.py')
        print('Led Yakildi')
        sayac_led=1
    if result2=='on' and sayac_mp3==0:
        mp3cal.baslat()
        print('MP3 Caliniyor')
        sayac_mp3=1
        sayac_mp3_durdur=1



