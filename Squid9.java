//Project 9 
// by Justin Acosta
// CST112 EVE
//Work in Progress

float horizon;
float skyR, skyG, skyB, seaR, seaG, seaB; // sky and sea colors
boolean sunDown = false; // turns sun into moon and darkens sky and sea
int score;
String [] squidNames = {"Mike", "Dave", "Fred", "Lucy", "Jay"};
String [] boatNames  = {"Tank", "Alpha", "Delta", "Duck"};
Boat [] navy; // array of boat objects
Squid [] ink; // array of squid objects
Sun star; // sun object

void setup() {
  size(800, 600); 
  horizon = height/4;
  reset();
}

void reset() {
  score = 0;
  star = new Sun();  // sun object
  // fill squid array
  ink = new Squid[5];
  float squidX = width/4 - 100;
  for (int i = 0; i < ink.length; i++) {
    ink[i] = new Squid(squidX, squidNames[i]);
    squidX += 150;
  }
  // fill boat array
  navy = new Boat[4];
  for (int i = 0; i < navy.length; i++) {
    navy[i] = new Boat(boatNames[i]);
  }
}

void draw() {
  background(skyR, skyG, skyB);  // sky
  scene();
  info();
  if (key >= 'A' && key <= 'Z') {
    squidCard(ink, ink.length);
    boatCard(navy, navy.length);
    messages();
  } else {
    action();
  }
}
// makes scorecard for Squid
void squidCard(Squid[] a, int m) {
  float x = width/2;  
  float y = height/2;
  float w = width/2;
  float h = height/2 - 50;
  // column lables
  fill(200);
  rect(x-20, y-30, w, h);
  fill(255, 0, 0);
  textSize(20);
  text("Squid Score Card", x, y);
  textSize(12);
  text("Name", x, y+30 );
  text("Legs", x + 60, y+30);
  text("X", x + 120, y+30);
  text("Y", x + 200, y+30);
  text("DY", x + 280, y+30);
// name, legs, x, y, and dy
  for (int i= 0; i < m; i++) {
    fill(0);
    text(a[i].name, x, y+60);
    text(a[i].legs, x + 60, y+60);
    text(a[i].x, x + 120, y+60);
    text(a[i].y, x + 200, y+60);
    text(a[i].dy, x + 280, y+60);
    y += 30;
  }
}
// makes scorecard for Boat
void boatCard(Boat[] a, int m) {
  float x = width/4 - 150;  
  float y = height/2 - 200;
  float w = width/2 - 100;
  float h = height/2 - 80;
  // column lables
  fill(200);
  rect(x-20, y-30, w, h);
  fill(255, 0, 0 );
  textSize(20);
  text("Boat Score Card", x, y);
  textSize(12);
  text("Name", x, y+30 );
  text("Cargo", x + 60, y+30);
  text("X", x + 120, y+30);
  text("DX", x + 200, y+30);
// name , cargo, x and dx
  for (int i= 0; i < m; i++) {
    fill(0);
    text(a[i].name, x, y+60);
    text(a[i].cargo, x + 60, y+60);
    text(a[i].x, x + 120, y+60);
    text(a[i].dx, x + 200, y+60);
    y += 30;
  }
}
// move boats and squid.  Stops moving when you bring up scorecards
void action() {
  for (int i = 0; i < ink.length; i++) {
    ink[i].move();
  }

  for (int i = 0; i < navy.length; i++) {
    navy[i].move();
  }
}

// show the scene, sea, boat, sun, fish etc...
void scene() {
  noStroke();
  fill(seaR, seaG, seaB);
  rect(0, horizon, width, height); // sea
  stroke(0);
  // show squid
  for (int i = 0; i < ink.length; i++) {
    ink[i].show();
  }
  // show boats
  for (int i = 0; i < navy.length; i++) {
    navy[i].show();
  }
  // sun
  star.show();
  star.move();
  star.sunSet();
  if (sunDown == true) {  // sky and sea darken when sun goes down
    skyR = 100;
    skyG = 100;
    skyB = 250;
    seaR = 0;
    seaG = 150;
    seaB = 150;
  } else {
    skyR = 0;
    skyG = 150;
    skyB = 200;
    seaR = 0;
    seaG = 250;
    seaB = 250;
  }
}
// show messages on How to Sort
void messages() {
  float x = width/2;
  float y = horizon + 50;
  float x2 = width/4 - 150;
  float y2 = height - 290;
  textSize(15);
  // squid info
  text("Press X to sort by Squid X", x, y);
  text("Press Y to sort by Squid Y", x, y + 20);
  text("Press S to sort by Squid Speed", x, y + 40);
  text("Press L to sort by Squid Legs", x, y + 60);
  // boat info
  text("Press B to sort by Boat X", x2, y2);
  text("Press D to sort by Boat Speed", x2, y2 + 20 );
  text("Press F to sort by Boat Cargo", x2, y2 + 40);
  textSize(12);
}

// name and score
void info() {
  textSize(15);
  fill(0);
  text("Justin Acosta", width/4 - 180, horizon - 125);
  text("Score", width*3/4, horizon - 100);
  text(score, width*3/4 + 50, horizon - 100);
  text("Project 9", width*3/4, horizon - 120);
  text("Shift + any key for Scorecards", width/4- 180, horizon - 105);
  text("Press 'r' to reset", width/4- 180, horizon - 85);

  textSize(12);
}

// sort by squid x coordinates
void sortSquidX(Squid[] a, int many) {

  for (int m = many; m > 1; m--) {
    int k = 0;
    for (int j = 1; j<m; j++) {
      if (a[j].x > a[k].x) k = j;
    }
    swapSquid(a, m-1, k);
  }
}
// sort  by squid y
void sortSquidY(Squid[] a, int many) {
  for (int m = many; m > 1; m--) {
    int k = 0;
    for (int j = 1; j<m; j++) {
      if (a[j].y > a[k].y) k = j;
    }
    swapSquid(a, m-1, k);
  }
}
// sort by squid speed
void sortSquidDY(Squid[] a, int many) {
  for (int m = many; m > 1; m--) {
    int k = 0;
    for (int j = 1; j<m; j++) {
      if (a[j].dy > a[k].dy) k = j;
    } 
    swapSquid(a, m-1, k);
  }
}
// sort by squid legs
void sortSquidLegs(Squid[] a, int many) {
  for (int m = many; m > 1; m--) {
    int k = 0;
    for (int j = 1; j<m; j++) {
      if (a[j].legs > a[k].legs) k = j;
    }
    swapSquid(a, m-1, k);
  }
}

// swap squid x, y, dy, legs and name
void swapSquid(Squid[] a, int k, int j) {
  Squid tmp;

  tmp = a[k];
  a[k] = a[j];
  a[j] = tmp;

}

// sort by boat x coordinate
void sortBoatX(Boat[] a, int many) { 
  for (int m = many; m > 1; m--) {
    int k = 0;
    for (int j = 1; j<m; j++) {
      if (a[j].x > a[k].x) k = j;
    }
    swapBoat(a, m-1, k);
  }
}

//sort by boat dx fastest to slowest
void sortBoatDX(Boat[] a, int many) { 
  for (int m = many; m > 1; m--) {
    int k = 0;
    for (int j = 1; j<m; j++) {
      if (a[j].dx < a[k].dx) k = j;
    }
    swapBoat(a, m-1, k);
  }
}

// sort by boat cargo, greatest to lowest
void sortBoatCargo(Boat[] a, int many) { 
  for (int m = many; m > 1; m--) {
    int k = 0;
    for (int j = 1; j<m; j++) {
      if (a[j].cargo < a[k].cargo) k = j;
    }
    swapBoat(a, m-1, k);
  }
}

// swap boat x, dx, and cargo
void swapBoat(Boat[] a, int k, int j) {
  Boat tmp;
  
  tmp = a[k];
  a[k] = a[j];
  a[j] = tmp;

  
}

void keyPressed() {
  if (key == 'r') {reset();}
  if (key == 'X') {sortSquidX(ink, ink.length);}
  if (key == 'Y') {sortSquidY(ink, ink.length);}
  if (key == 'S') {sortSquidDY(ink, ink.length);}
  if (key == 'L') {sortSquidLegs(ink, ink.length);}
  if (key == 'B') {sortBoatX(navy, navy.length);}
  if (key == 'D') {sortBoatDX(navy, navy.length);}
  if (key == 'F') {sortBoatCargo(navy, navy.length);}
}

// squid class
class Squid {
  float x, y, dx, dy;
  float r, g, b;
  int legs = 8;
  int count = 0;
  String name = "";

  Squid( float x, String name) {
    this.name = name;
    this.x = x;
    r = random(255);
    g = random(255);
    b = random(255);
    bottom();
  }
  // bottom of the sea
  void bottom() {
    y = height - 30;
    dy = -random(0.5, 1.5);
    legs = int(random(1, 6)); 
  }
  // show squid
  void show() {
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
    for (int i = 0; i<legs; i++) {
      if (count/30 % 2 ==0) {
        line(legX, y+20, legX-8, y + 45);
      } else {
        line(legX, y+20, legX+8, y + 45);
      }
      legX += 8;  // spacing
    }

    stroke(0);
    strokeWeight(1);
  }
  // move squid
  void move() {
    y += dy; 
    if (y < horizon + 10 || y > height) dy *= - 1;
  }
}

// boat class
class Boat {
  float x, y, dx;
  float r, g, b;
  String name = "";
  int cargo= 0, caught = 0;

  Boat(String name) {
    this.name = name;
    x = random(30, width- 60);
    y = horizon - 25;
    dx = random(-6, 6);
    r = random(255);
    g = random(255);
    b = random(255);
  }
  // show boat 
  void show() {
    noStroke();
    fill(r, g, b);
    rect(x, y, 50, 25);// body
    if (dx < 0) {
      triangle( x, y+25, x - 40, y, x, y); // front
      rect(x, y - 20, 30, 30); // cabin
      fill(0);
      text(cargo, x + 10, y - 5); // display cargo
    } else {
      triangle( x+49, y+25, x + 90, y, x+49, y);
      rect(x+20, y - 20, 30, 30); // cabin
      fill(0);
      text(cargo, x + 30, y - 5); // display cargo
    }
    stroke(0);
    fill(0);
    text(name, x, y + 15);  // display boat name
  }
  // move boat, catch squid legs, and store cargo as score
  void move() {
    x += dx;
    if (x < 25 || x > width - 50) dx *= -1;

    // catch squid
    for (int i = 0; i < ink.length; i++) {
      if (dist(x, y, ink[i].x, ink[i].y) < 50) {
        cargo += ink[i].legs;
        ink[i].bottom(); // caught squid is sent to the bottom, gets new random legs
      }
    }
    // store cargo as caught when you return to left of screen
    if (x < 25) {
      caught += cargo;
      cargo = 0;
      score += caught;
    }
  }
}
// Sun class
class Sun {
  float x, y, dx;
  float r, g, b;

  Sun() {
    x = width*3/4;
    y = height/4 - 100;
    dx = 1;
    r = 255;
    g = 255;
    b = 0;
  }

  void show() {     
    fill(r, g, b);
    ellipse(x, y, 60, 60);
  }
  // move the sun, reset its x position if it goes off screen
  void move() {
    x += dx; 
    if (x > width + 200) x = 0;
  }
  // when the sun goes off screen, the moon comes out & vice versa
  void sunSet() {
    if (x > width + 199) sunDown = !sunDown;
    // change from sun to moon
    if (!sunDown) {
      r = 255; // Sun colors
      g = 255;
      b = 0;
    } else {
      r = 150;  // Moon colors
      g = 150;
      b = 150;
    }
  }
}
