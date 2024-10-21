import processing.core.*;

public class App extends PApplet {
    int bgColor;
    //create ice cream cone
    float tipX;
    float tipY;
    float leftX;
    float leftY;
    float rightX;
    float rightY;
    float speedtriangle = 35;
    //create scoops
    float circleY;
    float circleX;
    float speedcircle = 7;
    float r, g, b;
    boolean circleFalling = true;
    boolean imgFalling = true;
    int count = 0;
    float circleRadius = 25;
    PImage imgPoison;
    float imgX;
    float imgY;
    float speedimg = 10;
    boolean gameActive = true;
    int scene = 0; // 0 = Start Scene, 1 = Game Scene, 2 = Game Over Scene
    //coordintes for play button
    float playX1 = 360; // Left point
    float playY1 = 350;
    float playX2 = 360; // Bottom point
    float playY2 = 250;
    float playX3 = 440; // Right point
    float playY3 = 300;
    int highScore = 0; //assign high score variable 
    PImage IceCreamImg;
    PImage SadFace;
    PImage star;
    


    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        bgColor = color(102, 178, 255); //create background color
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
        imgPoison = loadImage("PotionDeath.png"); //load posion image
        imgY = 0;
        imgX = random(0, width - imgPoison.width);
        IceCreamImg = loadImage("iceCream.png"); //load ice cream image
        SadFace = loadImage("sadFace.png"); //load sad face image 
        star= loadImage("star.png"); //load star image 

    }

    public void settings() {
        size(800, 600); //set the size of screen

    }

    public void draw() {
        if (scene ==0){
            StartScreen(); //call the start screen method which display a play button for users
        }

        if (scene == 1) {
            background(bgColor);
            image(imgPoison, imgX, imgY, 92, 92);
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
                //Detect if the cone and scoop collide
                if (circleTouchesTriangle(circleX, circleY)) {
                    circleFalling = false; // Stop the circle from falling
                    ResetCircle(); // Reset the circle to a new random position
                    count++;

                }

                imgY += speedimg;
                if (imgY > height) {
                    ResetImg();
                }
                //Detect if poison and cone collide
                if (imgTouchesTriangle(imgX, imgY)) {
                    println("Game Over!");
                    scene = 2; // Stop the game
                }
            }

            fill(0);
            textSize(20);
            text("Scoops collected:" + count, 20, 70);
            text("High Score: " + highScore, 20, 100);
        } else if(scene == 2){
            EndScreen();

        }else if(scene == 0){

        }
        Highscore();
    }

    public void ResetCircle() {
        //reset scoop at a new random location
        circleX = random(0, width);
        circleY = 0;
        circleFalling = true;
        r = random(0, 255);
        g = random(0, 255);
        b = random(0, 255);
    }

    public void ResetImg() {
        //rests poison at a new random location 
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
        //Have arrow keys move the cone
        if (keyCode == LEFT) {
            if (0 < leftX && leftX < 800){
            tipX -= speedtriangle; // Move rectangle left
            leftX -= speedtriangle;
            rightX -= speedtriangle;}
        } else if (keyCode == RIGHT) {
            if (0< rightX && rightX < 800){
            tipX += speedtriangle; // Move rectangle right
            leftX += speedtriangle;
            rightX += speedtriangle;}

        }else if (key == ' ' && scene == 2){
            ResetGame();
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
        image(IceCreamImg, playX1 - 200, playY1 - 170, 170, 410); 
        image(IceCreamImg, playX3 + 50, playY3 - 120, 170, 410);
        fill(0);
        textSize(18);
        text("Collect the ice cream scoops and avoid the poison using the arrow keys to move!", 110, 150);
        CreateStars();
    }
    public void mousePressed() {
        if (scene == 0 && mouseInButton()) {
            scene = 1; // Start the game when play button is clicked
        }
    }
    boolean mouseInButton () {
        //Detect if the play button is clicked by checking the color
        if(get(mouseX, mouseY) == color(0,0,102)){
            System.out.println("you found the color");
            return true;
        }
        return false;
        
    }
    public void Highscore(){
        if (scene == 2) {  // losing Scene
            if (count > highScore) {
                highScore = count; // Update high score if the current score is higher
            }
    }
}
    public void CreateStars(){
        //Make stars on start screen
        image(star, 40, 200, 100, 100);
        image(star, 40, 280, 100, 100);
        image(star, 40, 360, 100, 100);
        image(star, 40, 360, 100, 100);
        image(star, 40, 440, 100, 100);
        
        image(star, 650, 200, 100, 100);
        image(star, 650, 280, 100, 100);
        image(star, 650, 360, 100, 100);
        image(star, 650, 440, 100, 100);
    }
    public void EndScreen(){
        background(bgColor);
            image(SadFace, 290, 50, 220, 190);
            fill(255, 0, 0);
            textSize(60);
            text("Game Over!", 250, 300);
            fill(14, 2, 2);
            textSize(40);
            text("Press space to play again", 200, 500);
            textSize(30);
            fill(255, 255, 255);
            
            text("Your Score: " + count, 325, 455); // Display the score of the current game
            text("High Score: " + highScore, 325, 405); // Display the high score
        }
    }

   
    


