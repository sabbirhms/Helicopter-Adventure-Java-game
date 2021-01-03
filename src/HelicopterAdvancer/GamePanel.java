package HelicopterAdvancer;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    
    public static boolean GameOver = false;
    public static int score = 0;
    public static boolean starting = false;
    public static int proceed = -1;
    
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    
    private int xCoor = 0;
    private BufferedImage img = null;
    // Create Bee Object
    HelicopterImage bi = new HelicopterImage();
    TreeImage ti = new TreeImage(GamePanel.WIDTH);
    TreeImage ti2 = new TreeImage(GamePanel.WIDTH + (GamePanel.WIDTH/2));
    
    public GamePanel() {
        //Load Image
        LoadImage();
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                bi.goUpwards();
            }
        });
    }

    private void LoadImage() {
        try {
           
           img = ImageIO.read(new File("e:/flyingBee/images/gamepanel.jpg"));
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img, xCoor, 0, null);
        g.drawImage(img, xCoor+3600, 0, null);
        
        bi.drawHelicopter(g);
        ti.drawTree(g);
        ti2.drawTree(g);
        
        if(starting) {
            g.setFont(new Font("Tahoma", Font.BOLD, 100));
            g.drawString(Integer.toString(proceed), GamePanel.WIDTH/2, GamePanel.HEIGHT/2);       
        } else {
            g.setFont(new Font("Tahoma", Font.BOLD, 54));
            g.drawString(""+score, 500, 150);
        }
        
    }
    
    public void Move() {
        bi.helicopterMovement();
        ti.treeMovement();
        ti2.treeMovement();
        
        if(GameOver) {
            ti.X = GamePanel.WIDTH;
            ti2.X = GamePanel.WIDTH + (GamePanel.WIDTH/2);
            GameOver = false;
        }
        
        xCoor += TreeImage.speed;
        
        if(xCoor == -3600) {
            xCoor = 0;
        }
        
        //System.out.println(ti.X + " -> " + BeeImage.x + " : " +ti2.X + " -> " + BeeImage.x);
        if(ti.X == HelicopterImage.x || ti2.X == HelicopterImage.x) {
            score+=1;
        }
    }
    
    public static boolean popUpMessage() {
        
        int result = JOptionPane.showConfirmDialog(null, "Game Over! Your Score Is " +score+ 
                        "\n Do you want to restart the game", "Game Over", JOptionPane.YES_NO_OPTION);
    
        if(result == JOptionPane.YES_OPTION) {
            return true;
        }
        else {
            return false;
        }
        
    }
    
}
