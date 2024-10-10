import processing.core.*;

public class App extends PApplet {
    int bgColor;
    float tipX;
    float tipY;
    float leftX;
    float leftY;
    float rightX;
    float rightY;
    float speedtriangle = 35;
    float circleY;
    float circleX;
    float speedcircle = 6;
    float r, g, b;
    boolean circleFalling = true;
    boolean imgFalling = true;
    int count = 0;
    float circleRadius = 25;
    PImage imgPoison;
    float imgX;
    float imgY;
    float speedimg = 9;
    boolean gameActive = true;
    int scene = 0; // 0 = Start Scene, 1 = Game Scene, 2 = Game Over Scene
    float playX1 = 360; // Left point
    float playY1 = 350;
    float playX2 = 360; // Bottom point
    float playY2 = 250;
    float playX3 = 440; // Right point
    float playY3 = 300;


    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        bgColor = color(102, 178, 255);
        background(bgColor);
        ResetCircle();
        tipX = width / 2;
        tipY = height;
        leftX = width / 2 - 40;
        leftY = height - 100;
        rightX = width / 2 + 40;
        rightY = height - 100;
        circleY = 0;
        circleX = width / 2;
        imgPoison = loadImage("test.png");
        imgY = 0;
        imgX = random(0, width - imgPoison.width);

    }

    public void settings() {
        size(800, 600);

    }

    public void draw() {
        if (scene ==0){
            StartScreen();
        }

        if (scene == 1) {
            background(bgColor);
            image(imgPoison, imgX, imgY, 55, 55);
            fill(0);
            textSize(20);
            text("Collect the ice cream scoops and avoid the poison!", 20, 40);
            fill(174, 124, 75);
            triangle(leftX, leftY, rightX, rightY, tipX, tipY);

            if (circleFalling) {
                fill(r, g, b);
                ellipse(circleX, circleY, 50, 50);
                circleY += speedcircle;
                if (circleY > height) {
                    circleY = 0;
                    r = random(0, 255);
                    g = random(0, 255);
                    b = random(0, 255);
                }
                if (circleY + 50 >= tipY) {
                    circleFalling = false;
                    ResetCircle();

                }
                if (circleTouchesTriangle(circleX, circleY)) {
                    circleFalling = false; // Stop the circle from falling
                    ResetCircle(); // Reset the circle to a new random position
                    count++;

                }

                imgY += speedimg;
                if (imgY > height) {
                    ResetImg();
                }

                if (imgTouchesTriangle(imgX, imgY)) {
                    println("Game Over!");
                    scene = 2; // Stop the game
                }
            }

            fill(0);
            textSize(20);
            text("Scoops collected:" + count, 20, 70);
        } else if(scene == 2){
            background(bgColor);
            fill(255, 0, 0);
            textSize(40);
            text("Game Over!", width / 2 - 100, height / 2);
            fill(14, 2, 2);
            text("Press space to play again", 200, 400);

        }else if(scene == 0){

        }
    }

    public void ResetCircle() {
        circleX = random(0, width);
        circleY = 0;
        circleFalling = true;
        r = random(0, 255);
        g = random(0, 255);
        b = random(0, 255);
    }

    public void ResetImg() {
        imgX = random(0, width);
        imgY = 0;
        imgFalling = true;

    }

    boolean circleTouchesTriangle(float cx, float cy) {
        return (cy + circleRadius >= leftY &&
                cx > leftX && cx < rightX);
    }

    boolean imgTouchesTriangle(float imgX, float imgY) {
        return (imgY + 50 >= leftY &&
                imgX + 50 / 2 > leftX && imgX + 50 / 2 < rightX);
    }

    public void keyPressed() {
        if (keyCode == LEFT) {
            tipX -= speedtriangle; // Move rectangle left
            leftX -= speedtriangle;
            rightX -= speedtriangle;
        } else if (keyCode == RIGHT) {
            tipX += speedtriangle; // Move rectangle right
            leftX += speedtriangle;
            rightX += speedtriangle;

        }else if (key == ' ' && scene == 2){
            ResetGame();
        }
        else if(key == 'p'){
            scene++;
        }
    }
    public void ResetGame(){
        scene = 1;
            ResetImg();
            count=0;
    }
    public void StartScreen(){
        textSize(60);
        textAlign(CENTER, TOP);
        text("Welcome to Scoop Survival!", width / 2, 20);
        fill(0,0,102);
        triangle(playX1, playY1, playX2, playY2, playX3, playY3);
        textSize(50);
        textAlign(CENTER, CENTER);
        fill(0,0,101);
        text("Play", (playX1 + playX3) / 2, playY1 + 30);
        textAlign(LEFT, LEFT);
    }
    public void mousePressed() {
        if (scene == 0 && mouseInButton()) {
            scene = 1; // Start the game when play button is clicked
        }
    }
    boolean mouseInButton () {
        if(get(mouseX, mouseY) == color(0,0,102)){
            System.out.println("you found the color");
            return true;
        }
        return false;
        
    }
}
   
    


