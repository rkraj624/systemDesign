package dsa.practice.lld.ticTacToe;

import dsa.practice.lld.ticTacToe.enums.Piece;

public interface WinStrategy {
    boolean checkWin(Board board, Piece piece);
}
