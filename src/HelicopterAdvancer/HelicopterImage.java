package HelicopterAdvancer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class HelicopterImage {
    
    private BufferedImage img = null;
    private static final int helicopterDimension = 40;
    public static int x = (GamePanel.WIDTH/2)-helicopterDimension/2; // 450-20 = 430, 430 = 450 - dia/2
    public static int y = GamePanel.HEIGHT/2;
    
    private static int speed = 2;
    private final int acceleration = 1;
    
    public HelicopterImage() {
        LoadImage();
    }

    private void LoadImage() {
        try {
            img = ImageIO.read(new File("HelicopterAdvencer/images/helicopter.png"));
        } 
        
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void drawHelicopter(Graphics g) {
        g.drawImage(img, x, y, null);
    }
    
    public void helicopterMovement() {
        if(y >= 0 && y <= GamePanel.HEIGHT) {
            speed += acceleration;
            y += speed;
        } else {
            
            boolean option = GamePanel.popUpMessage();
        
        if(option) {
            try {
                Thread.sleep(500);
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
            reset();
         } else {
                JFrame frame = MainHelicopter.getWindow();
                frame.dispose();
                MainHelicopter.timer.stop();
            }
        }
    }
    
    public void goUpwards() {
        speed = -12;
    }

    public static void reset() {
        speed = 2; 
        y = GamePanel.HEIGHT/2;
        GamePanel.GameOver = true;
        GamePanel.score = 0;
    }
    
    public static Rectangle getHelicopterRect() {
        Rectangle HelicopterRect = new Rectangle(x, y, helicopterDimension, 35);
        return HelicopterRect;
    }
    
}

