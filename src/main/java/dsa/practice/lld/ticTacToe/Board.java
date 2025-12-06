package dsa.practice.lld.ticTacToe;

import dsa.practice.lld.ticTacToe.enums.Piece;

public class Board {
    private final int size;
    private final Cell[][] grid;
    private final WinStrategy winStrategy;

    public Board(int size, WinStrategy winStrategy) {
        this.size = size;
        this.winStrategy = winStrategy;
        grid = new Cell[size][size];

        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++)
                grid[i][j] = new Cell(i, j);
    }

    public boolean place(int r, int c, Piece piece) {
        if (!grid[r][c].isEmpty()) {
            System.out.println("Cell is already filled, try again!");
            return false;
        }
        grid[r][c].setPiece(piece);
        return true;
    }

    public boolean checkWinner(Piece piece) {
        return winStrategy.checkWin(this, piece);
    }

    public boolean isFull() {
        for (Cell[] row : grid)
            for (Cell c : row)
                if (c.isEmpty()) return false;
        return true;
    }

    public int getSize() { return size; }

    public Cell[][] getGrid() { return grid; }

    public Cell getCell(int i, int j){
        return grid[i][j];
    }
}
