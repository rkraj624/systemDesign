package dsa.practice.lld.ticTacToe;

import dsa.practice.lld.ticTacToe.enums.Piece;

public class DefaultWinStrategy implements WinStrategy {

    @Override
    public boolean checkWin(Board board, Piece piece) {
        int size = board.getSize();
        Cell[][] grid = board.getGrid();

        for (int i = 0; i < size; i++) {
            if (allMatch(grid, piece, i, true)) return true;
            if (allMatch(grid, piece, i, false)) return true;
        }

        return diagonalMatch(grid, piece, true) || diagonalMatch(grid, piece, false);
    }

    private boolean allMatch(Cell[][] g, Piece p, int idx, boolean rowCheck) {
        for (int i = 0; i < g.length; i++) {
            if (rowCheck && g[idx][i].piece != p) return false;
            if (!rowCheck && g[i][idx].piece != p) return false;
        }
        return true;
    }

    private boolean diagonalMatch(Cell[][] g, Piece p, boolean main) {
        for (int i = 0; i < g.length; i++) {
            if ((main ? g[i][i] : g[i][g.length - 1 - i]).piece != p)
                return false;
        }
        return true;
    }

}
