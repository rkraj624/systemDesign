package dsa.practice.lld.ticTacToe;

import dsa.practice.lld.ticTacToe.enums.Piece;

public class Cell {
    int row, col;
    Piece piece;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
