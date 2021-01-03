package HelicopterAdvancer;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
    
    private BufferedImage img = null;
    public boolean StartingPoint = false;
    
    public MenuPanel() {
        LoadImage();
        
        //handler mouse point
        
        this.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
               super.mouseClicked(e);
               StartingPoint = true;
           }
        });
    }
    
    private void LoadImage() {      
        try {
            img = ImageIO.read(new File("e:/flyingBee/images/menupanel.jpg"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
    }
    
}
