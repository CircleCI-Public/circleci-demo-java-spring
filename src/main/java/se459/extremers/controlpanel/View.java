package se459.extremers.controlpanel;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class View extends JFrame {
    private final GridLayout grid;
    private JButton  pauseButton, startButton, shutdownButton, resumeButton, chooseFPButton;
    
    
    public View() {
        super("0-Dirt Control Panel");
        grid = new GridLayout(0, 5);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(getContentPane());
        pack();
        setVisible(true);
        setResizable(false);
    }

 
    private void addComponentsToPane(final Container pane) {
        final JPanel panel = new JPanel();
        panel.setLayout(grid);        
        panel.setPreferredSize(new Dimension(500, 75));

        initializeComponents();
        
       
        
        panel.add(startButton);
        panel.add(pauseButton);
        panel.add(resumeButton);
        panel.add(shutdownButton);
        panel.add(chooseFPButton);

               
        pane.add(panel);
    }
        
    private void initializeComponents() {
              
        pauseButton = new JButton("||");
        startButton = new JButton(">");
        shutdownButton = new JButton("X");
        resumeButton = new JButton("|>");
        chooseFPButton = new JButton("FP");
        
       

        // addIcon(pauseButton, "icons/pauseButton.png");
        // addIcon(startButton, "icons/startButton.png");
        // addIcon(shutdownButton, "icons/shutdownButton.png");
        // addIcon(resumeButton, "icons/resumeButton.png");
        // addIcon(chooseFPButton, "icons/chooseFPButton.png");

        pauseButton.getPreferredSize();
        startButton.getPreferredSize();
        shutdownButton.getPreferredSize();
        resumeButton.getPreferredSize();
        chooseFPButton.getPreferredSize();
  
      
    }
    
    private void addIcon(JButton button, String iconPath) {
        try {
            Image icon = ImageIO.read(View.class.getResource(iconPath));
            button.setIcon(new ImageIcon(icon));
        } catch (IOException ex) {
            System.out.println(iconPath + " not found.");
        }
    }
    
 
    
    public JButton getPauseButton() {
        return pauseButton;
    }
    
    public JButton getStartButton() {
        return startButton;
    }
    
    public JButton getShutdownButton() {
        return shutdownButton;
    }
    
    public JButton getResumeButton() {
        return resumeButton;
    }
 
    public JButton getChooseFPButton() {
        return chooseFPButton;
    }
    
    
}