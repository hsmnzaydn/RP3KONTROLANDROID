import pygame


def baslat():
    pygame.init()
    pygame.mixer.music.load("/home/dvcc/PycharmProjects/proje/robokids.mp3")
    pygame.mixer.music.play()


def durdur():
    pygame.mixer.music.stop()
    print("durduruldu")