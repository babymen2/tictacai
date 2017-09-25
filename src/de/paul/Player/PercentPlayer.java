package de.paul.Player;

import de.paul.TicTacGame.Board;
import de.paul.TicTacGame.GameState;
import de.paul.TicTacGame.State;

import java.util.ArrayList;
import java.util.Arrays;

public class PercentPlayer extends Player {

    public static int count = 0;
    public static int[] choice;


    public PercentPlayer(State type) {
        super(type);
    }


    public int[] bestMove(Board b) {
        ArrayList<int[]> moves = b.getPossibleMoves();
        double[][] winTieLoss = new double[moves.size()][3];

        for (int i = 0; i < moves.size(); i++) {
            Board copied = b.copy();
            copied.move(getType(), moves.get(i)[0], moves.get(i)[1]);
            winTieLoss[i] = calcOutcomes(copied, getType() == State.O ? State.X : State.O);
        }

        double max = Double.MAX_VALUE;
        int[] current = new int[2];

        for (int i = 0; i < moves.size(); i++) {
            if (winTieLoss[i][2] <= max) {
                max = winTieLoss[i][2];
                current = moves.get(i);
            }
        }
        return current;
    }


    private double[] calcOutcomes(Board b, State s) {

        if (score(b) != Integer.MIN_VALUE) {
            double[] wtl = new double[3];
            wtl[score(b)] = 1;
            return wtl;
        }

        ArrayList<int[]> moves = b.getPossibleMoves();
        double[] winTieLoss = new double[3];
        for (int i = 0; i < moves.size(); i++) {
            Board copied = b.copy();
            copied.move(s, moves.get(i)[0], moves.get(i)[1]);
            double[] outcome = calcOutcomes(copied, s == State.O ? State.X : State.O);
            winTieLoss[0] += outcome[0] / moves.size();
            winTieLoss[1] += outcome[1] / moves.size();
            winTieLoss[2] += outcome[2] / moves.size();
        }

        return winTieLoss;
    }


    public int score(Board b) {
        if (b.getCurrenState() == GameState.Win) {
            if (b.getWinner() == getType()) {
                return 0;
            } else if (b.getWinner() != getType()) {
                return 2;
            }
        } else if (b.getCurrenState() == GameState.Tie) {
            return 1;
        }
        return Integer.MIN_VALUE;
    }
}
