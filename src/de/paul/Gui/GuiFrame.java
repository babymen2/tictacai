package de.paul.Gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.paul.Genetic.Response;
import de.paul.Player.GeneticPlayer;
import de.paul.Player.MinMaxPlayer;
import de.paul.Player.PercentPlayer;
import de.paul.Player.RandomPlayer;
import de.paul.Simulation.Simulation;
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

public class GuiFrame {

    private JFrame frame;
    private Board b;
    private JButton[] buttons;


    /**
     * Launch the application.11
     */
    public static void main(String[] args) {
        GuiFrame window = new GuiFrame();
        window.frame.setVisible(true);
        window.frame.setTitle("Hey");
        PercentPlayer pPerc = new PercentPlayer(State.X);
        RandomPlayer pX = new RandomPlayer(State.O);

        while (window.getBoard().getCurrenState() == GameState.Running) {

            int sleepTIme = 100;

            try {
                Thread.sleep(sleepTIme);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(window.getBoard().getNextPlayer()==pPerc.getType()){
				int[] move = pPerc.bestMove(window.getBoard());
				window.getBoard().move(pPerc.getType(), move[0], move[1]);
				window.getButton(window.translateToBtnNumber(move[0], move[1])).setText(window.getBoard().getState(move[0], move[1]).toString());
				window.frame.repaint();
			}

            try {
                Thread.sleep(sleepTIme);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


		    if(window.getBoard().getNextPlayer()==pX.getType() && window.getBoard().getCurrenState() == GameState.Running){
		        int[] move = pX.getRandomTile(window.getBoard());
		        window.getBoard().move(pX.getType(), move[0], move[1]);
                window.getButton(window.translateToBtnNumber(move[0], move[1])).setText(window.getBoard().getState(move[0], move[1]).toString());
                window.frame.repaint();
            }


            if(window.getBoard().getCurrenState() != GameState.Running){
		        window.frame.dispose();
                window = new GuiFrame();
                window.frame.setVisible(true);
                window.frame.setLocation(-1000,500);
                window.frame.setTitle("Hey");
            }
        }

    }

    /**
     * Create the application.
     */
    public GuiFrame() {
        b = new Board(3);
        buttons = new JButton[9];
        initialize();
        for (JButton b : buttons) {
            b.setFont(new Font("SansSerif", Font.PLAIN, 100));
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 417);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(3, 3, 0, 0));


        for (int x = 0; x < b.getSize(); x++) {
            for (int y = 0; y < b.getSize(); y++) {
                JButton btnNewButton = new JButton("");
                btnNewButton.setBackground(Color.WHITE);
                btnNewButton.setFocusPainted(false);

                int finalX = x;
                int finalY = y;
                btnNewButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (b.getCurrenState() == GameState.Running) {
                            b.move(b.getNextPlayer(), finalX, finalY);
                            btnNewButton.setText(b.getState(finalX, finalY).toString());
                        }
                    }
                });
                panel.add(btnNewButton);
                buttons[x * b.getSize() + y] = btnNewButton;
            }
        }


        JPanel panel_1 = new JPanel();
        frame.getContentPane().add(panel_1, BorderLayout.NORTH);
    }

    public Board getBoard() {
        return b;
    }

    public JButton getButton(int index) {
        return buttons[index];
    }

    public int translateToBtnNumber(int x, int y) {
        if (x == 0 && y == 0) return 0;
        else if (x == 1 && y == 0) return 1;
        else if (x == 2 && y == 0) return 2;
        else if (x == 0 && y == 1) return 3;
        else if (x == 1 && y == 1) return 4;
        else if (x == 2 && y == 1) return 5;
        else if (x == 0 && y == 2) return 6;
        else if (x == 1 && y == 2) return 7;
        else if (x == 2 && y == 2) return 8;
        return -1;
    }
}
