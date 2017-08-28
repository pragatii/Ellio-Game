import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * Created by Himanshu on 23/11/15.
 */
public class Demo {
    public static void main(String[] args) {
        JFrame jframe1 = new JFrame();
//        jframe1.setSize(800, 800);
        jframe1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        //add stuff to window here

        Stage2Screen mainPanel = new Stage2Screen();
        mainPanel.setPreferredSize(new Dimension(800, 450));
        mainPanel.setFocusable(true);
        mainPanel.requestFocus();


        mainPanel.addKeyListener(mainPanel);
        mainPanel.addMouseListener(mainPanel);

        jframe1.add(mainPanel);
        jframe1.setResizable(false);
        jframe1.pack();
        jframe1.setTitle("sourav's Ellio");

//        JButton button1 = new JButton("Click Me!");
//        mainPanel.add(button1);
//
//        JButton button2 = new JButton("And me as well!");
//        mainPanel.add(button2);
//
//        JTextField textField1 = new JTextField("enter text here");
//        mainPanel.add(textField1);

        jframe1.setVisible(true);


        int velocityX = 5;
        int velocityY = 5;


        BufferedImage image1 = Stage2Screen.loadImage("run_anim1.png");
        BufferedImage image2 = Stage2Screen.loadImage("run_anim2.png");
        BufferedImage image3 = Stage2Screen.loadImage("run_anim3.png");
        BufferedImage image4 = Stage2Screen.loadImage("run_anim4.png");
        BufferedImage image5 = Stage2Screen.loadImage("run_anim5.png");
        BufferedImage playerImages[] = {image1, image2, image3, image4, image5, image4, image3, image2};
        int counter = 0;
        Random generator = new Random();
        jframe1.setIconImage(image1);

        while(true) {

            if(mainPanel.playerX <= -50){
                mainPanel.gameoverscreen=true;
                mainPanel.isPaused=true;

            }
            if(mainPanel.closeframe){
                jframe1.dispose();
            }

            if(!mainPanel.isPaused) {

                counter++;


                counter = counter % 8;


                mainPanel.playerImage = playerImages[counter];

                mainPanel.playerVelocityY += mainPanel.playerAccY;
                mainPanel.playerY += mainPanel.playerVelocityY;

                if (mainPanel.playerY >= 450 - 45 - 90) {
                    mainPanel.playerY = 450 - 45 - 90;
                    mainPanel.playerVelocityY = 0;
                    mainPanel.playerAccY = 0;
                }

                if (mainPanel.cloud1X <= -100) {
                    mainPanel.cloud1Y = generator.nextInt(190) + 10;
                    mainPanel.cloud1X = 900;
                    mainPanel.cloud1Visible = true;
                }

                if (mainPanel.cloud2X <= -100) {
                    mainPanel.cloud2Y = generator.nextInt(200) + 10;
                    mainPanel.cloud2X = 900;
                }
                if(mainPanel.block1X <=-200){
                    mainPanel.block1Y = 450-90 - generator.nextInt(2)*75 ;
                    mainPanel.block1X = 900;
                    mainPanel.block1Visible=true;
                }
                if(mainPanel.block2X <=-200){
                    mainPanel.block2Y = 450-90 - generator.nextInt(2)*75 ;
                    mainPanel.block2X = 900;
                    mainPanel.block2Visible=true;
                }
                if(mainPanel.block3X <=-200){
                    mainPanel.block2Y = 450-90 - generator.nextInt(2)*85 ;
                    mainPanel.block3X = 900;
                    mainPanel.block3Visible=true;
                }
                if(mainPanel.sunVisible){
                    mainPanel.block1X -= 8;
                    mainPanel.block2X -= 8;
                    mainPanel.block3X -= 8;
                }
                else{
                    mainPanel.block1X -= 10;
                    mainPanel.block2X -= 10;
                    mainPanel.block3X -= 10;
                }



                mainPanel.cloud1X -= 2;
                mainPanel.cloud2X -= 2;

                /*mainPanel.block1X -= 8;
                mainPanel.block2X -= 8;
                mainPanel.block3X -= 8;*/

                if(mainPanel.jumpVisible||mainPanel.duckVisible){
                    mainPanel.playerVisible=false;
                }
                else{
                    mainPanel.playerVisible=true;
                }
              /*  if(mainPanel.duckVisible&&mainPanel.jumpVisible){
                    mainPanel.duckVisible=false;
                }*/



                Rectangle playerRectangle = new Rectangle(mainPanel.playerX, mainPanel.playerY, 70, 90);
                Rectangle duckRectangle = new Rectangle(mainPanel.playerX, 450-45-70, 70,90);
                Rectangle cloud1Rectangle = new Rectangle(mainPanel.cloud1X, mainPanel.cloud1Y, 128, 71);
                Rectangle block1Rectangle = new Rectangle(mainPanel.block1X, mainPanel.block1Y,20,50);
                Rectangle block2Rectangle = new Rectangle(mainPanel.block2X, mainPanel.block2Y,20,50);
                Rectangle block3Rectangle = new Rectangle(mainPanel.block3X, mainPanel.block3Y,20,50);

                if(playerRectangle.intersects(cloud1Rectangle)) {
                    mainPanel.cloud1Visible = false;
                }
                if(playerRectangle.intersects(block1Rectangle)&& (mainPanel.playerVisible||mainPanel.jumpVisible)) {
                    System.out.println("i rect jump " );
                    mainPanel.block1Visible=false;
                    mainPanel.playerX -=2;
                }
                if(duckRectangle.intersects(block1Rectangle)&&mainPanel.duckVisible){
                    System.out.println("i rect duck " );
                    mainPanel.block1Visible=false;
                    mainPanel.playerX -=2;
                }

                if(playerRectangle.intersects(block2Rectangle)&& (mainPanel.playerVisible||mainPanel.jumpVisible)) {
                    System.out.println("2 rect jump" );
                    mainPanel.block2Visible=false;
                    mainPanel.playerX -=2;
                }
                if(duckRectangle.intersects(block2Rectangle)&&mainPanel.duckVisible){
                    System.out.println("2rect duck" );
                    mainPanel.block2Visible=false;
                    mainPanel.playerX -=2;
                }
                if(playerRectangle.intersects(block3Rectangle)&& (mainPanel.playerVisible||mainPanel.jumpVisible) && !(mainPanel.sunVisible)) {
                    System.out.println("3 rect jump" );
                    mainPanel.block3Visible=false;
                    mainPanel.playerX -=2;
                }
                if(duckRectangle.intersects(block3Rectangle)&&mainPanel.duckVisible && !(mainPanel.sunVisible)){
                    System.out.println("3rect duck" );
                    mainPanel.block3Visible=false;
                    mainPanel.playerX -=2;
                }


            }
            mainPanel.repaint();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
