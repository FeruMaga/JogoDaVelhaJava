package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Jogo extends JPanel{
	
	private JFrame window;
	private JPanel panel;

	private BufferedImage imgTitle;
	private BufferedImage imgGame;
	private BufferedImage imgX;
	private BufferedImage imgO;
	private BufferedImage imgBack;
	private BufferedImage imgPlaca;
	private BufferedImage imgPlayersButton;
	private BufferedImage imgAIButton;
	
	private boolean gameOver;
	private char[][] board;
	private int turn;
	
	private String winner;
	
	private int typeGame;
	
	private char playerAI;
	private char player;
	
	private int row;
	private int col;
	
	public Jogo() {
		this.gameOver = false;
		this.board = new char[3][3];
		this.turn = 0;
		this.typeGame = 0;	
		this.player = ' ';
		this.playerAI = ' ';
		loadImages();
		mouse();
		mainPanel();
	}
	
	public void mainPanel() {
		JPanel gridPanel = new JPanel(new GridLayout(3,3));
		gridPanel.setSize(new Dimension(500,500));
		add(gridPanel, BorderLayout.CENTER);
		repaint();
	}
	
	public void panelFinish() {
		if(gameOver) {
			JFrame gameOverWindow = new JFrame("Fim do Jogo");
	        gameOverWindow.setSize(400, 200);
	        gameOverWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	        gameOverWindow.setLocationRelativeTo(null);
			
			JPanel gameOverPanel = new JPanel(new BorderLayout());
			
			JLabel messageLabel = new JLabel(winner, SwingConstants.CENTER);
			gameOverPanel.add(messageLabel, BorderLayout.CENTER);
			
			JButton restartButton = new JButton("Restart");
			restartButton.addActionListener((ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameRestart();
					gameOverWindow.dispose();
				}
			});
			
			JButton exitButton = new JButton("Sair");
			exitButton.addActionListener((ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.add(restartButton);
			buttonPanel.add(exitButton);
			gameOverPanel.add(buttonPanel, BorderLayout.SOUTH);
			
			gameOverWindow.add(gameOverPanel);
			gameOverWindow.setVisible(true);
		}
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
		    
		    BufferedImage playersButton = ImageIO.read(new File("src/resources/Botao1.png"));
		    imgPlayersButton = new BufferedImage(220, 300, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2dPlayersButton = imgPlayersButton.createGraphics();
		    g2dPlayersButton.drawImage(playersButton.getScaledInstance(220, 300, Image.SCALE_SMOOTH), 0, 0, null);
		    g2dPlayersButton.dispose();
		    
		    BufferedImage aiButton = ImageIO.read(new File("src/resources/Botao2.png"));
		    imgAIButton = new BufferedImage(220, 300, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2dAIButton = imgAIButton.createGraphics();
		    g2dAIButton.drawImage(aiButton.getScaledInstance(220, 300, Image.SCALE_SMOOTH), 0, 0, null);
		    g2dAIButton.dispose();
			
		    repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(typeGame == 1 || (typeGame == 2 &&
				(player == 'X' || playerAI == 'X' || player == 'O' || playerAI == 'O'))) {
			
			g.drawImage(imgBack, 0, 0, null);
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
			
			whoWon();
			
		}else if(typeGame == 2) {
			if(player ==' ' && playerAI == ' ') {
				panelChooseXO(g);
			} else {
				
			}
		}else {
			panelTypeGame(g);
		}	
		
	}
	
	
	
	public void mouse() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(typeGame == 1) {
					playersMode(e);
				}else if(typeGame == 2){
					if(player == ' ' && playerAI == ' ') {
						chooseXO(e);
					}else {
						aiMode(e);
					}
					
				}else {
					chooseTypeOfPlay(e);
				}

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
	
	public void playersMode(MouseEvent e) {
		int cellWidth = (getWidth()-250)/3;
		int cellHeight = (getHeight() - 100)/3;
		int row = (e.getY() - 140)/ cellHeight;
		int col = (e.getX() - 190)/ cellWidth;
		System.out.println("Clicked: " + row + ", " + col);
		insertXO(row, col);
		repaint();
	}
	
	public void aiMode(MouseEvent e) {
		
	}
	
	public void chooseTypeOfPlay(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(x >= 200 && x <= 200 + imgPlayersButton.getWidth(null) && 
				y>=290 && y <= 290 + imgPlayersButton.getHeight(null)) {
			typeGame = 1;
			
			
		} else if (x >= 420 && x <= 420 + imgAIButton.getWidth(null) &&
                   y >= 290 && y <= 290 + imgAIButton.getHeight(null)) {
            typeGame = 2;
            System.out.println("robo");
            repaint();
		}
		repaint();
	}
	
	public void chooseXO(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("Coordenadas " + x + ", " + y);
		if(x >= 310 && x <= 310 + imgX.getWidth(null) && 
				y>=300 && x <= 300 + imgX.getHeight(null)) {
            	player = 'X';
            	playerAI = 'O';
            	System.out.println("X escolheu");
            	repaint();
        }else if(x >= 500 && x <= 500 + imgO.getWidth(null) && 
        	y>=300 && x <= 300 + imgO.getHeight(null)) {
            player = 'O';
            playerAI = 'X';
            System.out.println("O escolheu");
            repaint();
            	
        }
	}
	
	public boolean xWon() {
		for(int i = 0; i < 3; i ++) {
			if (board[i][0] == 'X' && board[i][1] == 'X' && board[i][2] == 'X'){
				System.out.println("GameOver");
				winner = "X ganhou!";
				gameOver = true;
				return true;
			}else if(board[0][i] == 'X' && board[1][i] == 'X' && board[2][i] == 'X'){
				System.out.println("GameOver");
				winner = "X ganhou!";
				gameOver = true;
				return true;
			}
		}
		
		if((board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') ||
		(board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X')) {
			System.out.println("GameOver");
			winner = "X ganhou!";
			gameOver = true;
			return true;
		}
		
		return false;
	}
	
	public boolean oWon() {
		for(int i = 0; i < 3; i ++) {
			if (board[i][0] == 'O' && board[i][1] == 'O' && board[i][2] == 'O'){
				System.out.println("GameOver");
				winner = "O ganhou!";
				gameOver = true;
				return true;
			}else if(board[0][i] == 'O' && board[1][i] == 'O' && board[2][i] == 'O'){
				System.out.println("GameOver");
				winner = "O ganhou!";
				gameOver = true;
				return true;
			}
		}
		
		if((board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') ||
		(board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O')) {
			System.out.println("GameOver");
			winner = "O ganhou!";
			gameOver = true;
			return true;
		}
		
		return false;
	}
	
	public boolean velhaWon() {
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j<3; j++) {
				if(board[i][j] == 0) {
					return false;
				}
			}
		}
		System.out.println("GameOver");
		winner = "A Velha ganhou!";
		gameOver = true;
		return true;
	}
	
	public void whoWon() {
		if(velhaWon() || xWon() || oWon()) {
			panelFinish();
		}
	}
	
	public void gameRestart(){
		this.gameOver = false;
		this.board = new char[3][3];
		this.turn = 0;
		repaint();
	}
	
	public boolean gameEmpty() {
		for(int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(board[i][j] == 'X' || board[i][j] == 'O') {
					return true;
				}
			}
		}
		return false;
	}	
	
	public void panelTypeGame(Graphics g) {
		
		g.drawImage(imgBack, 0, 0, null);
		
		Color retangulo = new Color(76, 237, 104);
		g.setColor(retangulo);
		g.fillRoundRect(150, 250, 550, 400, 30, 30);
		
		String EscolhaTipo = "Escolha com qual adversário deseja jogar: ";
		
		g.setFont(new Font("Gotham", Font.BOLD, 30));
		g.setColor(retangulo);
		
		g.drawString(EscolhaTipo, 110, 200);
		
		g.drawImage(imgPlayersButton, 200, 290, this);
		
		g.drawImage(imgAIButton, 420, 290, this);

	}
	
	public void panelChooseXO(Graphics g) {
		g.drawImage(imgBack, 0, 0, null);
		
		Color retangulo = new Color(76, 237, 104);
		g.setColor(retangulo);
		g.fillRoundRect(280, 280, 330, 110, 30, 30);
		
		String EscolhaXO = "Escolha entre X ou O: ";
		
		g.setFont(new Font("Gotham", Font.BOLD, 30));
		g.setColor(retangulo);
		
		g.drawString(EscolhaXO, 110, 200);
		
		g.drawImage(imgX, 310, 300, this);
		
		g.drawImage(imgO, 500, 300, this);
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
