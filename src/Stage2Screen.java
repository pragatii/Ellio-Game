import javafx.scene.text.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * Created by Himanshu on 23/11/15.
 */
public class Stage2Screen extends JPanel implements KeyListener, MouseListener {
    public int cloud1X = 400;
    public int cloud1Y = 150;
    public int cloud2X = 900;
    public int cloud2Y = 70;
    public int block1X=100;
    public int block1Y=358;
    public int block2X=380;
    public int block2Y=280;
    public int block3X=675;
    public int block3Y=358;

    public int playerVelocityY = 0;
    public int playerAccY = 0;
    public int playerX =100;
    public int playerY = 450 - 45 - 90;
    public boolean cloud1Visible = true;
    public boolean block1Visible = false;
    public boolean block2Visible = false;
    public boolean block3Visible = false;
    public boolean duckVisible = false;
    public boolean playerVisible = true;
    public boolean startVisible = true;
    public int score=0;
    public boolean jumpVisible = false;
    public boolean closeframe = false;
    public boolean gameoverscreen = false;
    public boolean sunVisible = true;


    public boolean isPaused = false;
    BufferedImage welcomeimage = Stage2Screen.loadImage("welcome.png");
    BufferedImage playerImage = Stage2Screen.loadImage("run_anim1.png");
    BufferedImage grassImage = Stage2Screen.loadImage("grass.png");
    BufferedImage cloud1Image = Stage2Screen.loadImage("cloud1.png");
    BufferedImage cloud2Image = Stage2Screen.loadImage("cloud2.png");
    BufferedImage sunImage = Stage2Screen.loadImage("sun.png");
    BufferedImage sun2Image = Stage2Screen.loadImage("sun2.png");
    BufferedImage sun3Image = Stage2Screen.loadImage("sun3.png");
    BufferedImage jumpImage = Stage2Screen.loadImage("jump.png");

    BufferedImage PauseImage = Stage2Screen.loadImage("pause.png");
    BufferedImage blockImage = Stage2Screen.loadImage("block.png");
    BufferedImage menu = Stage2Screen.loadImage("selector.png");
    BufferedImage duckImage = Stage2Screen.loadImage("duck.png");
    BufferedImage gameoverImage = Stage2Screen.loadImage("gameover.png");

    public Color skyBlueColor = new Color(208, 244, 247);

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(welcomeimage,400,400,null);

       if(this.score>=5000){
            g.setColor(Color.black);
          // g.drawImage(backgroundimage,0,0,null);
            this.sunVisible=false;


        }
        else {

            g.setColor(skyBlueColor);


       }
        g.fillRect(0,0,800,450);


        if (this.cloud1Visible) {
            g.drawImage(cloud1Image, cloud1X, cloud1Y, null);
        }
        g.drawImage(menu,0,0,40,40,null);


        g.drawImage(cloud2Image, cloud2X, cloud2Y, null);
        if(this.sunVisible) {
            if(this.score%2==0) {
                g.drawImage(sunImage, 600, 50, null);

            }
            else{
                g.drawImage(sun2Image, 600, 50, null);
                g.drawImage(sun3Image, 600, 50, null);
            }
        }
        if(this.playerVisible) {
            g.drawImage(playerImage, playerX, playerY, null);
        }
        if(this.duckVisible){
            g.drawImage(duckImage,playerX,playerY,null);
        }
        if(this.jumpVisible){
            g.drawImage(jumpImage,playerX,playerY,null);
        }
        if(!this.isPaused){
            g.setColor(Color.black);
            g.drawRect(418,9,80,14);
            g.fillRect(418,9,80,14);
            g.setColor(Color.white);
            if(this.score>=5000) {
                g.drawString("SCORE   " + (score++ / 7), 420, 20);
            }
            else{
                g.drawString("SCORE   " + (score++ / 10), 420, 20);
            }

            g.getFont();
        }

        g.drawImage(grassImage, 0, 450 - 45, null);
        g.drawImage(PauseImage, 750,1, null);
        if (this.block1Visible) {
            g.drawImage(blockImage, block1X,block1Y, null);
        }
        if(this.block2Visible) {
            g.drawImage(blockImage, block2X, block2Y, null);
        }
        if(this.block3Visible && score>=5000) {

             g.drawImage(blockImage, block3X, block3Y, null);
        }


        if(this.startVisible){
            this.isPaused=true;
            g.drawImage(welcomeimage,0,0,800,450,null);
            g.drawImage(menu,340,237,25,45,null);
        }
        if(this.gameoverscreen) {
            g.drawImage(gameoverImage, 0, 0, 800, 450, null);
            g.setColor(Color.white);
            Font b=new Font("Courier",Font.BOLD,20);
            g.setFont(b);
            g.drawString("" + (score / 10), 600, 430 - 120);
            Font a=new Font("Courier",Font.BOLD,15);
            g.setFont(a);
            g.setColor(Color.green);
            if(this.score<3000){

                g.drawString("trust me...this is not your" , 500, 465 - 120);
                g.drawString("cup of tea" , 500, 480 - 120);

            }
            else if(this.score>=3000&&this.score<5000){
                g.drawString("pretty good but not like" , 500, 465 - 120);
                g.drawString(" as perfect player" , 500, 480 - 120);
            }
           else if(this.score>=5000&&this.score<7000){
                g.drawString("good job dude" , 600, 465 - 120);
            }
           else if(this.score>=7000&&this.score<=9000){
                g.drawString("it seems you are the real" , 500, 465 - 120);
                g.drawString("Player" , 500, 480 - 120);
            }
            else if(this.score>9000){

                g.drawString("whoompi...man you are " , 500, 465 - 120);
                g.setColor(Color.GREEN);
                g.drawString("the real king" , 500, 480 - 120);
            }
            g.getFont();
        }

    }

    public static BufferedImage loadImage(String filename) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(Stage2Screen.class
                    .getResourceAsStream("/" + filename));
        } catch (IOException e) {
            System.out.println("Error while reading: " + filename);
            e.printStackTrace();
        }
        return img;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE  && this.startVisible) {
            this.isPaused = false;
            this.startVisible=false;
            score=0;
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE && this.playerY == 450 - 45 -90) {



            this.jumpVisible=true;
            this.playerVelocityY = -15;
            this.playerAccY = 1;

        }
        /*else if(e.getKeyCode()==KeyEvent.VK_DOWN && this.startVisible){
            this.

        }*/
        else if(e.getKeyCode() == KeyEvent.VK_DOWN && this.jumpVisible) {
            this.playerVelocityY= 30;
        }
         else if(e.getKeyCode() == KeyEvent.VK_DOWN && this.playerY == 450 - 45 -90 ){
            this.duckVisible=true;

        }
        else if(e.getKeyCode() == KeyEvent.VK_ESCAPE ) {
            this.closeframe=true;
        }
        else {
            System.out.println("some other key pressed");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.duckVisible=false;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE && this.playerY!=450-45-90) {
            this.jumpVisible=false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getX() > 750 && e.getY() < 50) {
            this.isPaused = !this.isPaused;
        }
        if(e.getX() <40 && e.getY() <40 ){
            this.isPaused=true;
            this.startVisible = true;
        }
        if(e.getX()>370 &&e.getX()<470 && e.getY()<450-170 && e.getY()>450-210 && this.startVisible  ){
            this.isPaused=false;
            this.startVisible=false;
            this.playerX = 100;
            score=0;
        }
        if(e.getX()>270 &&e.getX()<370 && e.getY()<450-200 && e.getY()>450-230 && this.gameoverscreen  ){
            this.isPaused=false;
            this.gameoverscreen=false;
            score=0;
            this.playerX = 100;
            this.sunVisible=true;
        }
        if(e.getX()>370 &&e.getX()<470 && e.getY()<450-120 && e.getY()>450-155 && this.startVisible  ){
            this.closeframe=true;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {


        }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
