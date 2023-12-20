package Model.Chess.pieces;

import Model.BoardGame.Board;
import Model.Chess.ChessPiece;
import Model.Chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }
}
