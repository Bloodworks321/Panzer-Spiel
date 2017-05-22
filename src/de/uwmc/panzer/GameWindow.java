/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uwmc.panzer;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Patrick Wiehle
 */
public class GameWindow extends JFrame{
    
    private final GamePanel panzerGamePanel;
    private final ChooseTankColorDialog chooseTankColorDialog;
    
    public GameWindow() {   
        
        this.panzerGamePanel = new GamePanel();
        chooseTankColorDialog  = new ChooseTankColorDialog(this, panzerGamePanel);
                
        registerWindowListener();
        createMenu();
          
        add(panzerGamePanel);
        pack();
        
        setTitle("Panzer");
        setLocation(10, 10);
        setResizable(false);
        
        setVisible(true);
    }

    private void registerWindowListener() {        
        addWindowListener(new WindowAdapter() {            
            @Override
            public void windowClosing(WindowEvent e) { 
                System.exit(0); 
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
                // hier wird das Spiel pausiert   
                panzerGamePanel.pauseGame();
            }
            @Override
            public void windowActivated(WindowEvent e) {
                // hier wird das Spiel fortsetzt
                panzerGamePanel.continueGame();
            }            
        });        
    }
    
    private void createMenu() {
        
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        
        JMenu fileMenu = new JMenu("Datei");
        JMenu gameMenu = new JMenu("Spiel");
        JMenu prefMenu = new JMenu("Einstellungen");
        
        menuBar.add(fileMenu);        
        menuBar.add(gameMenu);        
        menuBar.add(prefMenu);
        
        addFileMenuItems(fileMenu);
        addGameMenuItems(gameMenu);
        addPrefMenuItems(prefMenu);
    }
    
    private void addFileMenuItems(JMenu fileMenu) {
        
        JMenuItem quitItem = new JMenuItem("Beenden");
        fileMenu.add(quitItem);
        quitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }); 
    }
    
    private void addGameMenuItems(JMenu gameMenu) {
    JMenuItem pauseItem = new JMenuItem("Pausieren");
    gameMenu.add(pauseItem);        
    pauseItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panzerGamePanel.pauseGame();
        }
    });
        
    JMenuItem continuetItem = new JMenuItem("Fortsetzen");
    gameMenu.add(continuetItem);       
    continuetItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {                
            panzerGamePanel.continueGame();
        }
    });
        
    gameMenu.addSeparator();
    JMenuItem restartItem = new JMenuItem("Neustarten");
    gameMenu.add(restartItem);       
    restartItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {                
            panzerGamePanel.restartGame();
        }
    });
}
    private void addPrefMenuItems(JMenu prefMenu) {
        
    JMenuItem changeColorItem = new JMenuItem("Panzerfarbe ändern...");
        prefMenu.add(changeColorItem);
       
        changeColorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                //panzerGamePanel.getPlayersTank().setTurretColor(java.awt.Color.YELLOW);
                
                panzerGamePanel.pauseGame();
                chooseTankColorDialog.pack();
                chooseTankColorDialog.setLocation(300, 200);
                chooseTankColorDialog.setVisible(true);

                
            }
        });
        
    JMenu submenu = new JMenu("Hintergrund auswählen");
    prefMenu.add(submenu);

    JMenuItem menuItem = new JMenuItem("Schlammfläche");
    submenu.add(menuItem);
    menuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panzerGamePanel.setBackgroundImage(0);
            repaint();
        }
    });

    menuItem = new JMenuItem("Schneefläche");
    submenu.add(menuItem);
    menuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panzerGamePanel.setBackgroundImage(1);
            repaint();
        }
    });
        
    menuItem = new JMenuItem("Wüstenfläche");
    submenu.add(menuItem);
    menuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panzerGamePanel.setBackgroundImage(2);
            repaint();
        }
    });
}
}