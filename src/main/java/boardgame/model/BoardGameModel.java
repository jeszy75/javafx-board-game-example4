package boardgame.model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class BoardGameModel {

    public static final int BOARD_SIZE = 5;

    private final ReadOnlyObjectWrapper<Square>[][] board;

    public BoardGameModel() {
        board = new ReadOnlyObjectWrapper[BOARD_SIZE][BOARD_SIZE];
        for (var i = 0; i < BOARD_SIZE; i++) {
            for (var j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new ReadOnlyObjectWrapper<>(Square.NONE);
            }
        }
    }

    public ReadOnlyObjectProperty<Square> squareProperty(int row, int col) {
        return board[row][col].getReadOnlyProperty();
    }

    public Square getSquare(int row, int col) {
        return board[row][col].get();
    }

    public void makeMove(int row, int col) {
        board[row][col].set(
                switch (board[row][col].get()) {
                    case NONE -> Square.HEAD;
                    case HEAD -> Square.TAIL;
                    case TAIL -> Square.NONE;
                }
        );
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (var i = 0; i < BOARD_SIZE; i++) {
            for (var j = 0; j < BOARD_SIZE; j++) {
                sb.append(board[i][j].get().ordinal()).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        var model = new BoardGameModel();
        System.out.println(model);
    }

}
