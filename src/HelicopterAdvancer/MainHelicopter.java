package HelicopterAdvancer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class MainHelicopter {
   
    public static JFrame window;
    public static Timer timer, timer2;
    private int proceed = 4;
    
    private MainHelicopter() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(GamePanel.WIDTH, GamePanel.HEIGHT);
        window.setLocationRelativeTo(null);
        window.setTitle("Helicopter Advancer");
        window.setResizable(false);
        window.setVisible(true);
    }
    
    private void render() {
       
       //MenuPanel Object
       MenuPanel mp = new MenuPanel();
       //GamePanel Object
       GamePanel gp = new GamePanel();
       
       // Timer Object
       timer = new Timer(20, new ActionListener() {
           
           @Override
           public void actionPerformed(ActionEvent e) {
               gp.repaint();
               gp.Move();
           }
       });
       
       // Add MenuPanel
       window.add(mp);
       window.setVisible(true);
       
       while(mp.StartingPoint == false) {
           try {
              Thread.sleep(10);
           } 
           catch(InterruptedException ex) {
               ex.printStackTrace();
           }
        }
       
       // Remove the menu panel
       window.remove(mp);
       
       //Add GamePanel
       window.add(gp);
       gp.setVisible(true);
       window.revalidate();
       
       //timer.start();
       
       timer2 = new Timer(500, new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              proceed--;
              GamePanel.proceed = proceed;
              GamePanel.starting = true;
              gp.repaint();
              
              if(proceed == 0) {
                  timer2.stop();
                  timer.start();
                  GamePanel.starting = false;
              }
           }
       });
       
       timer2.start();
       
    }
    
    /**
     *
     * @return window
     */
    public static JFrame getWindow() {
        return window;
    }
    
    public static void main(String[] args) {
        MainHelicopter mh = new MainHelicopter();
        mh.render();
    }
}
