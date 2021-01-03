package HelicopterAdvancer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class TreeImage {
    
    private Random r = new Random();
    public int X;
    public int Y = r.nextInt(GamePanel.HEIGHT-300)+150;
    private int widthTree = 60;
    private int height = GamePanel.HEIGHT-Y;
    private int gap = 150;
    
    public static int speed = -10;
    
    private BufferedImage img = null;

    public TreeImage(int X) {
        this.X = X;
        LoadImage();
    }

    private void LoadImage() {
        try {
            img = ImageIO.read(new File("e:/flyingBee/images/tree.png"));
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void drawTree(Graphics g) {
      g.drawImage(img, X, Y, null); // Lower Tree
      g.drawImage(img, X, (-GamePanel.HEIGHT) + (Y-gap), null);// Upper Tree
    }
    
    public void treeMovement() {
        
        X += speed;
        
        if(X <=-widthTree) {
            X = GamePanel.WIDTH;
            Y = r.nextInt(GamePanel.HEIGHT-300)+150;
            height = GamePanel.HEIGHT-Y;
        }
    
        Rectangle lowerRect = new Rectangle( X, Y, widthTree, height );
        Rectangle upperRect = new Rectangle( X, 0, widthTree, GamePanel.HEIGHT - (height+gap));
            
        if(lowerRect.intersects(HelicopterImage.getHelicopterRect()) || upperRect.intersects(HelicopterImage.getHelicopterRect()))  {
        
        boolean option = GamePanel.popUpMessage();
        
        if(option) {
            try {
                Thread.sleep(500);
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
           HelicopterImage.reset();
           treeReset();
        } else {
            // Close Window
            JFrame frame = MainHelicopter.getWindow();
                frame.dispose();
            MainHelicopter.timer.stop();
        }
            
        }
    }
    
    private void treeReset() {
        Y = r.nextInt(GamePanel.HEIGHT-300)+150;
        height = GamePanel.HEIGHT-Y;
        GamePanel.GameOver = true;
    }
}
