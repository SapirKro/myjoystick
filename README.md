# Joystick App

  <img src="https://github.com/SapirKro/myjoystick/blob/master/screenshot/intro.png" width=90% height=90%>  

**Joystick app allow you to remotely control the plane in flight gear.**  

## Introduction
This app is the final exercise in the course "Advanced Programming 2" (89211) in Bar Ilan University.
The app use MVVM design pattern.   Further Information and a demo can be seen in the video below:
[![Joystick App](https://github.com/SapirKro/myjoystick/blob/master/embed.png?raw=true)](https://youtu.be/TbgvJOa9pnE "Joystick App")
Class diagram:  

## How To Use
1. Download flight gear from [this link](https://www.flightgear.org/download/).
Install it and put the following commend in setting->additional setting:  
`--telnet=socket,in,10,127.0.0.1,5400,tcp`
2. **For phone:** [Click here](https://github.com/SapirKro/myjoystick/blob/master/myjoystickapp.apk) to download the app and install it on your phone.(make sure allow in the setting)  
**For PC:** Clone the repo and make sure you have emulator on your PC
3. Open the app and write your PC IP and port "5400" and press login.  
**Note:** you can get your PC IP from the cmd. open the cmd and write the commend:
`ipconfig`
your pc IP is the address in IPV4.  
right after press "fly" in the flight gear. The app will inform you if you succeed to login in.

Enjoy!
