package de.paul.Gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.paul.Player.MinMaxPlayer;
import de.paul.TicTacGame.Board;
import de.paul.TicTacGame.GameState;
import de.paul.TicTacGame.State;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GuiFrame
{

	private JFrame frame;
	private Board b;
	private JButton[] buttons;


	/**
	 * Launch the application.11
	 */
	public static void main(String[] args)
	{
		GuiFrame window = new GuiFrame();
		window.frame.setVisible(true);
		window.frame.setTitle("Hey");
		MinMaxPlayer pMnMx = new MinMaxPlayer(State.O);
		while(window.getBoard().getCurrenState()==GameState.Running){
			if(window.getBoard().getNextPlayer()==pMnMx.getType()){
				pMnMx.minMax(window.getBoard(), 0);
				int[] move = pMnMx.choice;
				window.getBoard().move(pMnMx.getType(), move[0], move[1]);
				window.getButton(window.translateToBtnNumber(move[0], move[1])).setText(window.getBoard().getState(move[0], move[1]).toString());
			}
		}
	
	}

	/**
	 * Create the application.
	 */
	public GuiFrame()
	{
		b = new Board(3);
		buttons = new JButton[9];
		initialize();
		for(JButton b : buttons){
			b.setFont(new Font("SansSerif", Font.PLAIN, 100));
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 3, 0, 0));


		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (b.getCurrenState() == GameState.Running)
				{
					b.move(b.getNextPlayer(), 0, 0);
					btnNewButton.setText(b.getState(0, 0).toString());
				}
			}
		});
		panel.add(btnNewButton);
		buttons[0] = btnNewButton;

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (b.getCurrenState() == GameState.Running)
				{
					b.move(b.getNextPlayer(), 1, 0);
					btnNewButton_1.setText(b.getState(1, 0).toString());
				}
			}
		});
		panel.add(btnNewButton_1);
		buttons[1] = btnNewButton_1;

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (b.getCurrenState() == GameState.Running)
				{
					b.move(b.getNextPlayer(), 2, 0);
					btnNewButton_2.setText(b.getState(2, 0).toString());
				}
			}
		});
		panel.add(btnNewButton_2);
		buttons[2] = btnNewButton_2;

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (b.getCurrenState() == GameState.Running)
				{
					b.move(b.getNextPlayer(), 0, 1);
					btnNewButton_3.setText(b.getState(0, 1).toString());
				}
			}
		});
		panel.add(btnNewButton_3);
		buttons[3] = btnNewButton_3;

		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (b.getCurrenState() == GameState.Running)
				{
					b.move(b.getNextPlayer(), 1, 1);
					btnNewButton_4.setText(b.getState(1, 1).toString());
				}
			}
		});
		panel.add(btnNewButton_4);
		buttons[4] = btnNewButton_4;

		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (b.getCurrenState() == GameState.Running)
				{
					b.move(b.getNextPlayer(), 2, 1);
					btnNewButton_5.setText(b.getState(2, 1).toString());
				}
			}
		});
		panel.add(btnNewButton_5);
		buttons[5] = btnNewButton_5;

		JButton btnNewButton_6 = new JButton("");
		btnNewButton_6.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (b.getCurrenState() == GameState.Running)
				{
					b.move(b.getNextPlayer(), 0, 2);
					btnNewButton_6.setText(b.getState(0, 2).toString());
				}
			}
		});
		panel.add(btnNewButton_6);
		buttons[6] = btnNewButton_6;

		JButton btnNewButton_7 = new JButton("");
		btnNewButton_7.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (b.getCurrenState() == GameState.Running)
				{
					b.move(b.getNextPlayer(), 1, 2);
					btnNewButton_7.setText(b.getState(1, 2).toString());
				}
			}
		});
		panel.add(btnNewButton_7);
		buttons[7] = btnNewButton_7;

		JButton btnNewButton_8 = new JButton("");
		btnNewButton_8.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (b.getCurrenState() == GameState.Running)
				{
					b.move(b.getNextPlayer(), 2, 2);
					btnNewButton_8.setText(b.getState(2, 2).toString());
				}
			}
		});
		panel.add(btnNewButton_8);
		buttons[8] = btnNewButton_8;

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
	}
	
	public Board getBoard(){
		return b;
	}
	
	public JButton getButton(int index){
		return buttons[index];
	}

	public int translateToBtnNumber(int x, int y){
		if(x==0&&y==0)return 0;
		else if(x==1&&y==0)return 1;
		else if(x==2&&y==0)return 2;
		else if(x==0&&y==1)return 3;
		else if(x==1&&y==1)return 4;
		else if(x==2&&y==1)return 5;
		else if(x==0&&y==2)return 6;
		else if(x==1&&y==2)return 7;
		else if(x==2&&y==2)return 8;
		return -1;
	}
}
