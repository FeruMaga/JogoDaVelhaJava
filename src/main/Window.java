package main;

import java.awt.Color;

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
    	Jogo jogo = new Jogo();
		mainWindow.add(jogo);
		mainWindow.setVisible(true);
    }
    
    public JFrame getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(JFrame mainWindow) {
		this.mainWindow = mainWindow;
	}
}
