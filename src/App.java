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
    PImage img;
    float imgX;
    float imgY;
    float speed3 = 9;
    boolean gameActive = true;

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
        img = loadImage("test.png");
        imgY = 0;
        imgX = random(0, width - img.width);

    }

    public void settings() {
        size(800, 600);

    }

    public void draw() {
        if (gameActive) {
            background(bgColor);
            image(img, imgX, imgY, 55, 55);
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

                imgY += speed3;
                if (imgY > height) {
                    ResetImg();
                }

                if (imgTouchesTriangle(imgX, imgY)) {
                    println("Game Over!");
                    gameActive = false; // Stop the game
                }
            }

            fill(0);
            textSize(20);
            text("Scoops collected:" + count, 20, 70);
        } else {
            fill(255, 0, 0);
            textSize(40);
            text("Game Over!", width / 2 - 100, height / 2);
            fill(14, 2, 2);
            text("Press space to play again", 200, 400);

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

        }else if (key == ' '){
            ResetGame();
        }
    }
    public void ResetGame(){
        gameActive = true;
            ResetImg();
    }
}
