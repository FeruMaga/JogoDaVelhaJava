package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window {

	private JFrame mainWindow;
	private Jogo jogo;
	
	private static int Width = 900;
	private static int Height = 800;
	
	public Window() {
		this.createMainWindow();
		this.createMainPanel();
		this.defineIcon();
	}

    public void createMainWindow(){
        JFrame window = new JFrame("Jogo da Velha");
        window.setSize(Width, Height);
        window.setResizable(false);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        this.setMainWindow(window);
    }
    
    public void createMainPanel() {
    	PanelControl panelControl = new PanelControl();
		mainWindow.add(panelControl);
		mainWindow.setVisible(true);
    }
    
    public void defineIcon() {
    	mainWindow.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("resources/icon.jpeg")).getImage());
    }
    
    public JFrame getMainWindow() {
		return mainWindow;
	}
    
	public void setMainWindow(JFrame mainWindow) {
		this.mainWindow = mainWindow;
	}
}
