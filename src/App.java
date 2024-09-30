import processing.core.*;

public class App extends PApplet{
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
    float speedcircle = 4;
    float r, g, b;
    boolean circleFalling = true; 
    int count = 0;
    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void setup(){
        bgColor = color (102, 178, 255);
        background(bgColor);
        ResetCircle();
        tipX = width/2;
        tipY = height;
        leftX = width/2-40;
        leftY = height-100;
        rightX = width/2+40;
        rightY = height-100;
        circleY=0;
        circleX= width/2;
         

    }

    public void settings(){
        size(800, 600);
        
    }

    public void draw(){
        background(bgColor);
        fill(0);
        textSize(20);
        text("Collect the ice cream scoops and avoid the poison!", 20, 40);
        fill(174, 124, 75);
        triangle(leftX, leftY, rightX, rightY, tipX, tipY);
        
        if (circleFalling){
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
            
            

        }
       
        

    }
    public void ResetCircle(){
        circleX=random(0, width);
        circleY = 0;
        circleFalling = true;
        r = random(0, 255); 
         g = random(0, 255); 
         b = random(0, 255); 

    }
    public void keyPressed() {
        if (keyCode == LEFT) {
            tipX -= speedtriangle; // Move rectangle left
            leftX -= speedtriangle;
            rightX -= speedtriangle;
        } else if (keyCode == RIGHT ) {
            tipX += speedtriangle; // Move rectangle right
            leftX += speedtriangle;
            rightX += speedtriangle;
        
        }
    }
}
