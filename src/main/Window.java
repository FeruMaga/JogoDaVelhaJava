package main;

import javax.swing.JFrame;

public class Window {

	private JFrame mainWindow;

	private static int Width = 900;
	private static int Height = 800;
	
	public Window() {
		this.createWindow();
		Jogo jogo = new Jogo();
		mainWindow.add(jogo);
		mainWindow.setVisible(true);
	}

    public void createWindow(){
        JFrame window = new JFrame("Jogo da Velha");
        window.setSize(Width, Height);
        window.setResizable(false);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        this.setMainWindow(window);
    }
    
    public JFrame getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(JFrame mainWindow) {
		this.mainWindow = mainWindow;
	}
}
