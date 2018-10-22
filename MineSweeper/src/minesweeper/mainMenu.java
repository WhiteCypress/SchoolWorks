/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

/**
 *
 * @author cstuser
 */
public class mainMenu extends JFrame{
    private static JLabel winLabel;
    private static JLabel loseLabel;
    public static JButton startButton;
    public static JLabel progressLabel;
    private JMenu startMenu;
    private JMenuBar menuBar;
    private mainMenu instance;
    public static int winCounter = 0;
    public static int loseCounter = 0;
    private JMenuItem debug;
    private JMenuItem normal;
    private gameWindow game;
    
    public mainMenu() {
        super("Mine Sweeper Menu!");
        instance = this;
        
        winLabel = new JLabel("Games Won: " + winCounter);
        loseLabel = new JLabel("Games Lost: " + loseCounter);
        startButton = new JButton("NEW GAME!!!");
        progressLabel = new JLabel("Press NEW GAME to start");
        startMenu = new JMenu("MODE");
        menuBar = new JMenuBar();
        debug = new JMenuItem("DEBUG MODE");
        normal = new JMenuItem("NORMAL MODE");
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                startButton.setEnabled(false);
                progressLabel.setText("Processing");
                
                game = new gameWindow(instance);
            }
        });
        
        debug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                game.debug = true;
            }
        });
 
        normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                game.debug = false;
            }
        });
        
        this.setSize(300, 200);
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(winLabel, BorderLayout.LINE_START);
        container.add(loseLabel, BorderLayout.LINE_END);
        container.add(startButton, BorderLayout.CENTER);
        container.add(progressLabel, BorderLayout.PAGE_END);
        menuBar.add(startMenu);
        startMenu.add(debug);
        startMenu.add(normal);
        container.add(menuBar, BorderLayout.PAGE_START);
        progressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public static void win(){
        winCounter++;
        winLabel.setText("Games Won: " + winCounter);
        try {
            File wavFile = new File("win.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(wavFile);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        }catch(Exception name){
            System.out.println("ERROR");
        }
    }
    
    public static void lose(){
        loseCounter++;
        loseLabel.setText("Games Lost: " + loseCounter);
        try {
            File wavFile = new File("lose.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(wavFile);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        }catch(Exception name){
            System.out.println("ERROR");
        }
    }
    
}
