#include <Servo.h>

Servo myservo;  // create servo object to control a servo

int pinOff = 6;
int pinOn = 8;
int valveOpen = 180;
int valveClose = 90;

void setup() {
  Serial.begin(9600);
  pinMode(pinOn, INPUT);
  pinMode(pinOff, INPUT);
  myservo.attach(9);  // attaches the servo on pin 9 to the servo object
}

void loop() {
  Serial.println(myservo.read());
  if(digitalRead(pinOn)){
    if(myservo.read() != valveOpen){
      myservo.write(valveOpen);
      delay(3000);
    }
  }

  if(digitalRead(pinOff)){
    if(myservo.read() != valveClose){
      myservo.write(valveClose);
      delay(3000);
    }
  }
  
//  if(digitalRead(raspPinIn)){
//    if(myservo.read() == valveOpen){
//      myservo.write(valveClose);
//    }
//    else if(digitalRead(pinOn)){
//      myservo.write(valveOn);
//    }
//    delay(1000);
//  }
}
