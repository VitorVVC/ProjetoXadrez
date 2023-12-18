package Model.Chess;

import Model.BoardGame.Board;
import Model.BoardGame.Piece;

public class ChessPiece extends Piece {

    private Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    // Método GET
    public Color getColor() {
        return color;
    }
}
