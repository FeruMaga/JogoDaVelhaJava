package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Jogo extends JPanel{
	
	private JFrame window;
	private JPanel panel;

	private BufferedImage imgGame;
	private boolean gameOver;
	private char[][] board;
	private int turn;
	
	public Jogo() {
		this.gameOver = false;
		this.board = new char[2][2];
		this.turn = 0;
		loadImages();
		
	}
	
	public void GameLoop() {

	}
	
	public void CreatePanel() {
		
	}
	
	public void loadImages() {
		try {
			imgGame = ImageIO.read(new File("src/resources/JogoDaVelha.png"));
			repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	
	public void Draw() {
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(imgGame != null) {
			int xImageGame = (getWidth() - imgGame.getWidth())/2;
			int yImageGame = (getHeight() - imgGame.getHeight())/2;
			g.drawImage(imgGame, xImageGame, yImageGame, this);
		}else {
			System.out.println("Error: Image not found");
		}
		
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	
}
