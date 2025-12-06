package dsa.practice.lld.ticTacToe;

import dsa.practice.lld.ticTacToe.enums.Piece;

public class TicTacToe {
    public static void main(String[] args) {
        Game game = new Game(3, new Player("Ravi", Piece.O),new Player("Raj", Piece.X));
        game.start();
    }
}
