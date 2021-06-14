# Joystick App
~picture~  
Joytick app allow you to remotely control the plane in flight gear.

## Introduction
This app is the final exerice in the course "Advanced Programming 2" (89211) in Bar Ilan University.
The app using MVVM degin pattren:
### View:
**MainActivity** - responsible for loading all the elements in the view.instilizaing a lodaing dialop and lister to the joystick movement.In this class I use the decoupling pronciple to send a data from the view to the viewmodel.(the lambda funtion for the joyustick listner)
**MynewJoystick** -the class contain the code for drawing the joystick and compute the movement values of the elevtor and alieron.

### ViewModel:
**LoginViewModel** - has the joystick and client info. this class responsible for sending iformation to the server from the view like ip,port alieron etc.
  
### Model: 
**Client** - contaion the basic information of the client,ip,port,getter and setter.  
**clientToServer** -client side.responsiple for the connetion with the flight gear server.  
**ConnectStatus** - has the status of the connetion to the sever.  
**MyThreadPool** - A thredpool using the runnable interface.the threadpool has one thread that get work contintusly on diffrent tasks to communticate the server.

## How To Use
1. download flight gear from [this link](https://www.flightgear.org/download/).
install and put the cmd in setting->additional setting:  
`--telnet=socket,in,10,127.0.0.1,5400,tcp`
2. **For phone: **[click here](https://pages.github.com/) to download the app and install it on your phone.(make sure intaliing unaturize app ia allow in the setting)  
**For PC: ** download the repo the make sure you have emultor in your PC
3. open the app and write your PC IP and port "5400" and press login.  
**Note:** you can get your PC IP from the cmd.open the cmd and write the commend:
`ipconfig`
your pc IP is the address in IPV4.  
right after press "fly" in the flight gear.the app will inform you if you succed to login in.

Enjoy!

## Additional Information
A short video contain intrudection and demo of the app:

UML diagrm:
