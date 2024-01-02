package Model.Chess.pieces;

import Model.BoardGame.Board;
import Model.Chess.ChessPiece;
import Model.Chess.Color;

public class King extends ChessPiece {
    // Classe "KING" (REI) que herda os métodos de ChessPiece

    // Construtor
    public King(Board board, Color color) {
        super(board, color);
    }

    // Método toString, para retornar apenas qual a peça
    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean[][] possibleMoves() {
        return new boolean[getBoard().getRows()][getBoard().getColumns()];
    }
}
