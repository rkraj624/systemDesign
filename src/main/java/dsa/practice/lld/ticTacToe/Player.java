package dsa.practice.lld.ticTacToe;

import dsa.practice.lld.ticTacToe.enums.Piece;

public class Player {
    private String name;
    private final Piece piece;

    public Player(String name, Piece piece) {
        this.name = name;
        this.piece = piece;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Piece getPiece() {
        return piece;
    }
}
