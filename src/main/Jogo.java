package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	private BufferedImage imgTitle;
	private BufferedImage imgGame;
	private BufferedImage imgX;
	private BufferedImage imgO;
	private BufferedImage imgBack;
	private BufferedImage imgPlaca;
	
	private boolean gameOver;
	private char[][] board;
	private int turn;
	
	private int row;
	private int col;
	
	public Jogo() {
		this.gameOver = false;
		this.board = new char[3][3];
		this.turn = 0;
		JPanel gridPanel = new JPanel(new GridLayout(3,3));
		gridPanel.setSize(new Dimension(500,500));
		add(gridPanel, BorderLayout.CENTER);
		loadImages();
		mouse();
	}
	
	public void GameLoop() {

	}
	
	public void loadImages() {
		try {
			int newHeight = 70;
			int newWidth = 70;
			
			int gameHeight = 700;
			int gameWidth = 800;
			
			int placaHeight = 95;
			int placaWidth = 220;
			
			int tituloHeight = 100;
			int tituloWidth = 600;
			
			
			imgBack = ImageIO.read(new File("src/resources/BackgroundEdit.png"));
			
			BufferedImage titulo = ImageIO.read(new File("src/resources/Placa1.jpg"));
			imgTitle = new BufferedImage(tituloWidth, tituloHeight, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2dTitulo = imgTitle.createGraphics();
		    g2dTitulo.drawImage(titulo.getScaledInstance(tituloWidth, tituloHeight, Image.SCALE_SMOOTH), 0, 0, null);
		    g2dTitulo.dispose();
			
			BufferedImage placa = ImageIO.read(new File("src/resources/Placa2.jpg"));
			imgPlaca = new BufferedImage(placaWidth, placaHeight, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2dPlaca = imgPlaca.createGraphics();
		    g2dPlaca.drawImage(placa.getScaledInstance(placaWidth, placaHeight, Image.SCALE_SMOOTH), 0, 0, null);
		    g2dPlaca.dispose();
			
			BufferedImage game = ImageIO.read(new File("src/resources/JogoDaVelha.png"));
			imgGame = new BufferedImage(gameWidth, gameHeight, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2dGame = imgGame.createGraphics();
		    g2dGame.drawImage(game.getScaledInstance(gameWidth, gameHeight, Image.SCALE_SMOOTH), 0, 0, null);
		    g2dGame.dispose();
			
			BufferedImage x = ImageIO.read(new File("src/resources/X.png"));
			imgX = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2dX = imgX.createGraphics();
		    g2dX.drawImage(x.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
		    g2dX.dispose();
			
		    BufferedImage o = ImageIO.read(new File("src/resources/O.png"));
			imgO = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2dO = imgO.createGraphics();
		    g2dO.drawImage(o.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
		    g2dO.dispose();
			
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
		try {
			g.drawImage(imgBack, 0, 0, null);
		}catch(Exception e) {
			System.out.println(e);
		}
		int xImageGame = (getWidth() - imgGame.getWidth())/2;
		int yImageGame = (getHeight() - imgGame.getHeight())/2 + 100;
		g.drawImage(imgGame, xImageGame, yImageGame, this);
			
		String title = "Jogo da Velha";
		int xTitulo = (getWidth() - imgTitle.getWidth())/2;
		int yTitulo = 30;
			
		g.drawImage(imgTitle, xTitulo, yTitulo, this);
		
		g.setFont(new Font("Gotham", Font.BOLD, 50));
		g.setColor(Color.BLACK);
		
		xTitulo += 130;
		yTitulo += 53;
		
		g.drawString(title, xTitulo, yTitulo);
		
		String turno = "Turno: ";
		int xTurno = 30;
		int yTurno = 145;
		
		if (turn == 0) {
			turno = turno + "X";
		}else {
			turno = turno + "O";
		}
		
		g.drawImage(imgPlaca, xTurno, yTurno, this);
		
		g.setFont(new Font("Gotham", Font.BOLD, 30));
		g.setColor(Color.BLACK);
		
		yTurno += 60;
		xTurno += 50;
		
		g.drawString(turno, xTurno, yTurno);
		
		int cellWidth = getWidth()/3 - 90;
		int cellHeight = getHeight()/3 - 85;
		
		for(int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				char xo = board[i][j];
				int xImage, yImage;
				
				xImage = (cellWidth * j) + (cellWidth - imgX.getWidth()) / 2 + 130;
				yImage = (cellHeight * i) + (cellHeight - imgX.getHeight()) /2 + 210;
				
				if(xo == 'X') {
					g.drawImage(imgX, xImage, yImage, this);
				}else if(xo == 'O') {
					g.drawImage(imgO, xImage, yImage, this);
				}
			}
		}
	}
	
	public void mouse() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int cellWidth = (getWidth()-200)/3;
				int cellHeight = (getHeight() - 100)/3;
				int row = (e.getY() - 140)/ cellHeight;
				int col = (e.getX() - 190)/ cellWidth;
				System.out.println("Clicked: " + row + ", " + col);
				insertXO(row, col);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Released");
			}
			
		});
	}
	
	public void insertXO(int row, int col) {
		if(board[row][col] == 0) {
			if(turn == 0) {
				board[row][col] = 'X';
				turn = 1;
			}else {
				board[row][col] = 'O';
				turn = 0;
			}
		}
		repaint();
	}
	
	public void win() {
		for(int i = 0; i < 3; i ++) {
			
		}
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
}
