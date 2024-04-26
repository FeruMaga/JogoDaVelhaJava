package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelControl extends JPanel{

	Jogo jogo;
	
	private BufferedImage imgTitle;
	private BufferedImage imgGame;
	private BufferedImage imgX;
	private BufferedImage imgO;
	private BufferedImage imgBack;
	private BufferedImage imgPlaca;
	private BufferedImage imgPlayersButton;
	private BufferedImage imgAIButton;
	
	public PanelControl() {
		this.jogo = new Jogo(this);
		mainPanel();
		loadImages();
		mouse();
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
	
	public void mouse() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(jogo.getTypeGame() == 1) {
					jogo.playersMode(e);
				}else if(jogo.getTypeGame() == 2){
					if(jogo.getPlayer() == ' ' && jogo.getPlayerAI() == ' ') {
						jogo.chooseXO(e);
					}else {
						jogo.aiMode(e);
					}
					
				}else {
					jogo.chooseTypeOfPlay(e);
				}

			}
			
			@Override
			public void mouseReleased(MouseEvent e) {

			}
			
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(jogo.getTypeGame() == 1 || (jogo.getTypeGame() == 2 &&
				(jogo.getPlayer() == 'X' || jogo.getPlayerAI() == 'X' || jogo.getPlayer() == 'O' || jogo.getPlayerAI() == 'O'))) {
			
			jogoDaVelhaPanel(g);
			
			jogo.whoWon();
			
		}else if(jogo.getTypeGame() == 2) {
			if(jogo.getPlayer() ==' ' && jogo.getPlayerAI() == ' ') {
				panelChooseXO(g);
			} else {
				
			}
		}else {
			panelTypeGame(g);
		}	
		
	}
	
	public void jogoDaVelhaPanel(Graphics g) {
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
		
		if (jogo.getTurn() == 0) {
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
				char xo = jogo.getBoard()[i][j];
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
	
	public void mainPanel() {
		JPanel gridPanel = new JPanel(new GridLayout(3,3));
		gridPanel.setSize(new Dimension(500,500));
		add(gridPanel, BorderLayout.CENTER);
		repaint();
	}
	
	public void panelFinish() {
		if(jogo.isGameOver()) {
			JFrame gameOverWindow = new JFrame("Fim do Jogo");
	        gameOverWindow.setSize(400, 200);
	        gameOverWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	        gameOverWindow.setLocationRelativeTo(null);
			
			JPanel gameOverPanel = new JPanel(new BorderLayout());
			
			JLabel messageLabel = new JLabel(jogo.getWinner(), SwingConstants.CENTER);
			gameOverPanel.add(messageLabel, BorderLayout.CENTER);
			
			JButton restartButton = new JButton("Restart");
			restartButton.addActionListener((ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jogo.gameRestart();
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

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public BufferedImage getImgTitle() {
		return imgTitle;
	}

	public void setImgTitle(BufferedImage imgTitle) {
		this.imgTitle = imgTitle;
	}

	public BufferedImage getImgGame() {
		return imgGame;
	}

	public void setImgGame(BufferedImage imgGame) {
		this.imgGame = imgGame;
	}

	public BufferedImage getImgX() {
		return imgX;
	}

	public void setImgX(BufferedImage imgX) {
		this.imgX = imgX;
	}

	public BufferedImage getImgO() {
		return imgO;
	}

	public void setImgO(BufferedImage imgO) {
		this.imgO = imgO;
	}

	public BufferedImage getImgBack() {
		return imgBack;
	}

	public void setImgBack(BufferedImage imgBack) {
		this.imgBack = imgBack;
	}

	public BufferedImage getImgPlaca() {
		return imgPlaca;
	}

	public void setImgPlaca(BufferedImage imgPlaca) {
		this.imgPlaca = imgPlaca;
	}

	public BufferedImage getImgPlayersButton() {
		return imgPlayersButton;
	}

	public void setImgPlayersButton(BufferedImage imgPlayersButton) {
		this.imgPlayersButton = imgPlayersButton;
	}

	public BufferedImage getImgAIButton() {
		return imgAIButton;
	}

	public void setImgAIButton(BufferedImage imgAIButton) {
		this.imgAIButton = imgAIButton;
	}
	
	
	
	
}
