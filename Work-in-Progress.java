//Project 9 
// by Justin Acosta
// CST112 EVE
//Work in Progress

float horizon;
float skyR, skyG, skyB, seaR, seaG, seaB;
boolean sunDown = false;
int score;
String [] squidNames = {"Mike", "Dave", "Fred", "Lucy", "Jay"};
String [] boatNames  = {"Tank", "Alpha", "Delta", "Duck"};
Boat [] navy;
Squid [] ink;

Sun star;

void setup(){
 size(800, 600); 
 horizon = height/4;
 reset();
}

void reset(){
   score = 0;

  star = new Sun();
  // fill squid array
  ink = new Squid[5];
  float squidX = width/4 - 100;
  for(int i = 0; i < ink.length; i++){
    ink[i] = new Squid(squidX, squidNames[i]);
    squidX += 150;
  }
  // fill boat array
  navy = new Boat[4];
  for(int i = 0; i < navy.length; i++){
    navy[i] = new Boat(boatNames[i]);
  }
  
  
  
}

void draw(){
  background(skyR, skyG, skyB);  // sky
  scene();
  info();
  
  
}
// show the scene, sea, boat, sun, fish etc...
void scene(){
  //text(navy[0].caught, width/2, horizon- 50);
  noStroke();
  fill(seaR, seaG, seaB);
  rect(0, horizon, width, height); // sea
  stroke(0);
  //tank.show();
  // show squid
  for(int i = 0; i < ink.length; i++){
    ink[i].show();
    ink[i].move();
  }
  // show boats
  for(int i = 0; i < navy.length; i++){
    navy[i].show();
    navy[i].move();
  }
  
  star.show();
  star.move();
  star.sunSet();
   if(sunDown == true){  // sky and sea darken when sun goes down
   skyR = 100;
   skyG = 100;
   skyB = 250;
   seaR = 0;
   seaG = 150;
   seaB = 150;
  }else{
    skyR = 0;
    skyG = 150;
    skyB = 200;
    seaR = 0;
    seaG = 250;
    seaB = 250;
 }
  
}
void info(){
  fill(0);
  text("Score", width*3/4 - 50, horizon - 100);
  text(score, width*3/4, horizon - 100);
}

// squid class
class Squid{
 float x, y, dx, dy;
 float r, g, b;
 int legs = 8;
 int count = 0;
 String name = "";
 
 Squid( float x, String name){
  this.name = name;
  this.x = x;
  r = random(255);
  g = random(255);
  b = random(255);
  bottom();
 }
 // bottom of the sea
 void bottom(){
   y = height - 30;
   dy = -random(0.1, 0.9);
   legs = int(random(1,5)); //int(random(1, 8));
 }
 // show squid
 // needs legs ++++++++++++++++++++++++++++++++++++++++++
 void show(){
   count++;
  noStroke();
  fill(r, g, b);
  ellipse(x, y, 40, 40);
  rect(x-20, y, 40, 20);
  fill(0);
  text(name, x-10, y);     // name
  text(legs, x-10, y + 10); // display number of legs
  //legs
  strokeWeight(3);
  stroke(r, g, b);
  float legX = x-18;
  for(int i = 0; i<legs; i++){
   if(count/30 % 2 ==0){
    line(legX, y+20, legX-8, y + 45);
  }else{
      line(legX, y+20, legX+8, y + 45);
}
     legX += 8;
  }

  stroke(0);
  strokeWeight(1);
 }
 
 void move(){
  y += dy; 
  if(y < horizon + 10 || y > height) dy *= - 1;
 }
}

// boat class
class Boat{
 float x, y, dx;
 float r, g, b;
 String name = "";
 int cargo= 0, caught = 0;

 Boat(String name){
   this.name = name;
   x = random(30, width- 60);
   y = horizon - 25;
   dx = random(-6, 6);
   r = random(255);
   g = random(255);
   b = random(255);
 }
 // show boat 
 void show(){
   noStroke();
  fill(r, g, b);
  rect(x, y, 50, 25);// body
  if(dx < 0){
    triangle( x, y+25, x - 40,y , x , y); // front
    rect(x, y - 20, 30, 30); // cabin
    fill(0);
    text(cargo, x + 10, y - 5); // display cargo

}else{
      triangle( x+49, y+25, x + 90,y , x+49 , y);
      rect(x+20, y - 20, 30, 30); // cabin
      fill(0);
      text(cargo, x + 30, y - 5); // display cargo

  }

  stroke(0);
  fill(0);
  text(name, x, y + 15);  // display boat name
}

void move(){
 x += dx;
 if(x < 25 || x > width - 50) dx *= -1;
 
 // catch squid
 for(int i = 0; i < ink.length; i++){
 if(dist(x, y, ink[i].x, ink[i].y) < 50){
   cargo += ink[i].legs;
   ink[i].bottom(); // caught squid is sent to the bottom, gets new random legs
 }
 
 }
 // store cargo as caught when you return to left of screen
 if(x < 25){
   caught += cargo;
   cargo = 0;
   score += caught;
   
 }
 
}
 
}

class Sun{
  float x, y, dx;
  float r, g, b;
  
  Sun(){
    x = width*3/4;
    y = height/4 - 100;
    dx = 1;
    r = 255;
    g = 255;
    b = 0;
    
 }
 
 void show(){     
  fill(r, g, b);
  ellipse(x, y, 60, 60);
 }
 // move the sun, reset its x position if it goes off screen
 void move(){
  x += dx; 
  if(x > width + 200) x = 0;
 }
 // when the sun goes off screen, the moon comes out & vice versa
 void sunSet(){
   if(x > width + 199) sunDown = !sunDown;
// change from sun to moon
   if(!sunDown){
     r = 255;
     g = 255;
     b = 0;
   }else{
     r = 150;
     g = 150;
     b = 150;
   }
     
  
 } 
}
