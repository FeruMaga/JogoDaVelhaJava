package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ai.MiniMaxAI;

public class Jogo{
	
	PanelControl panelControl;

	private boolean gameOver;
	private char[][] board;
	private int turn;
	
	private String winner;
	
	private int typeGame;
	
	private char playerAI;
	private char player;
	
	private int row;
	private int col;
	
	public Jogo(PanelControl panelControl) {
		this.panelControl = panelControl;
		this.gameOver = false;
		this.board = new char[3][3];
		this.turn = 0;
		this.typeGame = 0;	
		this.player = ' ';
		this.playerAI = ' ';
	}
	
	public void nextTurn(MouseEvent e) {
		if(getTypeGame() == 1) {
			playersMode(e);
		}else if(getTypeGame() == 2){
			if(getPlayer() == ' ' && getPlayerAI() == ' ') {
				chooseXO(e);
			}else {
				aiMode(e);
			}
			
		}else {
			chooseTypeOfPlay(e);
		}
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
		panelControl.repaint();
	}
	
	public void playersMode(MouseEvent e) {
		int cellWidth = (panelControl.getWidth()-250)/3;
		int cellHeight = (panelControl.getHeight() - 100)/3;
		int row = (e.getY() - 140)/ cellHeight;
		int col = (e.getX() - 190)/ cellWidth;
		System.out.println("Clicked: " + row + ", " + col);
		insertXO(row, col);
		panelControl.repaint();
	}
	
	public void insertXOAI() {
		if(!gameOver) {
			MiniMaxAI minimax = new MiniMaxAI(player, playerAI);
			
			int aiMove[] = minimax.findBestMove(board);
			int rowAI = aiMove[0];
			int colAI = aiMove[1];
	
			insertXO(rowAI, colAI);
		}
		panelControl.repaint();
	}
	
	public void aiMode(MouseEvent e){
		int cellWidth = (panelControl.getWidth()-250)/3;
		int cellHeight = (panelControl.getHeight() - 100)/3;
		int row = (e.getY() - 140)/ cellHeight;
		int col = (e.getX() - 190)/ cellWidth;
		
		if(player == 'X') {
			if(turn == 1) {
				
			}else if(turn == 0) {
				insertXO(row, col);
				insertXOAI();
			}
		} else if(player == 'O') {
			if(turn == 1) {
				insertXO(row, col);
				insertXOAI();
			}
		}
	}
	
	public void chooseTypeOfPlay(MouseEvent e) {
	
		int x = e.getX();
		int y = e.getY();
		
		if(x >= 200 && x <= 200 + panelControl.getImgPlayersButton().getWidth(null) && 
				y>=290 && y <= 290 + panelControl.getImgPlayersButton().getHeight(null)) {
			typeGame = 1;
			
			
		} else if (x >= 420 && x <= 420 + panelControl.getImgAIButton().getWidth(null) &&
                   y >= 290 && y <= 290 + panelControl.getImgAIButton().getHeight(null)) {
            typeGame = 2;
            System.out.println("robo");
            panelControl.repaint();
		}
		panelControl.repaint();
	}
	
	public void chooseXO(MouseEvent e) {
		
		int x = e.getX();
		int y = e.getY();
		
		if(x >= 310 && x <= 310 + panelControl.getImgX().getWidth(null) && 
				y>=300 && x <= 300 + panelControl.getImgX().getHeight(null)) {
            	player = 'X';
            	playerAI = 'O';
            	System.out.println("X escolheu");
            	panelControl.repaint();
        }else if(x >= 500 && x <= 500 + panelControl.getImgO().getWidth(null) && 
        	y>=300 && y <= 300 + panelControl.getImgO().getHeight(null)) {
            player = 'O';
            playerAI = 'X';
            System.out.println("O escolheu");
            panelControl.repaint();
            	
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
			panelControl.panelFinish();
		}
	}
	
	public void gameRestart(){
		this.gameOver = false;
		this.board = new char[3][3];
		this.turn = 0;
		this.typeGame = 0;	
		this.player = ' ';
		this.playerAI = ' ';
		panelControl.repaint();
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


	public boolean isGameOver() {
		return gameOver;
	}



	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}



	public char[][] getBoard() {
		return board;
	}



	public void setBoard(char[][] board) {
		this.board = board;
	}



	public int getTurn() {
		return turn;
	}



	public void setTurn(int turn) {
		this.turn = turn;
	}



	public String getWinner() {
		return winner;
	}



	public void setWinner(String winner) {
		this.winner = winner;
	}



	public int getTypeGame() {
		return typeGame;
	}



	public void setTypeGame(int typeGame) {
		this.typeGame = typeGame;
	}



	public char getPlayerAI() {
		return playerAI;
	}



	public void setPlayerAI(char playerAI) {
		this.playerAI = playerAI;
	}



	public char getPlayer() {
		return player;
	}



	public void setPlayer(char player) {
		this.player = player;
	}
	
	
}
