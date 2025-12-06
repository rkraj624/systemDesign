package dsa.practice.lld.ticTacToe;

import dsa.practice.lld.ticTacToe.enums.Piece;
import dsa.practice.lld.ticTacToe.enums.Status;

import java.util.*;

public class Game {
    private final Board board;
    private final List<Player> players;
    private Status status;
    private int currPlayerIdx;

    public Game(int size, Player p1, Player p2) {
        this.board = new Board(size, new DefaultWinStrategy());
        this.players = Arrays.asList(p1, p2);
        this.status = Status.IN_PROGRESS;
        this.currPlayerIdx = 0;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        while (status == Status.IN_PROGRESS) {
            Player curr = players.get(currPlayerIdx);
            System.out.println("Player " + curr.getName() + " turn");

            System.out.print("Enter row col: ");
            int r = sc.nextInt();
            int c = sc.nextInt();

            if (!board.place(r, c, curr.getPiece())) {
                System.out.println("Invalid Move! Try Again!");
                continue;
            }

            displayBoard();

            if (board.checkWinner(curr.getPiece())) {
                System.out.println("Player " + curr.getName() + " Wins!");
                status = Status.WIN;
                break;
            }

            if (board.isFull()) {
                System.out.println("Game Draw!");
                status = Status.DRAW;
                break;
            }

            currPlayerIdx = (currPlayerIdx + 1) % players.size();
        }

        sc.close();
    }

    private void displayBoard() {
        int size = board.getSize();
        System.out.println();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = board.getCell(i, j);
                System.out.print(cell.isEmpty() ? "." : cell.piece);
                if (j < size - 1) System.out.print(" | ");
            }
            System.out.println();

            if (i < size - 1) {
                for (int j = 0; j < size; j++) {
                    System.out.print("---");
                    if (j < size - 1) System.out.print("+");
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}

