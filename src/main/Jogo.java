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

	private BufferedImage imgGame;
	private BufferedImage imgX;
	private BufferedImage imgO;
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
			
			imgGame = ImageIO.read(new File("src/resources/JogoDaVelha.png"));
			
			BufferedImage x = ImageIO.read(new File("src/resources/X.png"));
			imgX = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2d = imgX.createGraphics();
		    g2d.drawImage(x.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
		    g2d.dispose();
			
		    BufferedImage o = ImageIO.read(new File("src/resources/O.png"));
			imgO = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2d2 = imgO.createGraphics();
		    g2d2.drawImage(o.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
		    g2d2.dispose();
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
		int xImageGame = (getWidth() - imgGame.getWidth())/2;
		int yImageGame = (getHeight() - imgGame.getHeight())/2;
		g.drawImage(imgGame, xImageGame, yImageGame, this);
			
		g.setFont(new Font("Gotham", Font.BOLD, 40));
		g.setColor(Color.BLUE);
			
		String title = "Jogo da Velha";
		int xTitulo = 320;
		int yTitulo = 50;
			
		g.drawString(title, xTitulo, yTitulo);
		
		String turno = "Turno: ";
		int xTurno = 320;
		int yTurno = 110;
		
		if (turn == 0) {
			turno = turno + "X";
		}else {
			turno = turno + "O";
		}
		
		g.setFont(new Font("Gotham", Font.BOLD, 60));
		g.setColor(Color.BLACK);
		
		g.drawString(turno, xTurno, yTurno);
		
		int cellWidth = getWidth()/3;
		int cellHeight = getHeight()/3;
		
		for(int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				char xo = board[i][j];
				int xImage, yImage;
				
				xImage = xImageGame + (cellWidth * j) + (cellWidth - imgX.getWidth()) / 2;
				yImage = yImageGame + (cellHeight * i) + (cellHeight - imgX.getHeight())/2;
				
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
				int cellWidth = getWidth()/3;
				int cellHeight = getHeight() /3;
				int row = e.getY()/ cellHeight;
				int col = e.getX()/cellWidth;
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
