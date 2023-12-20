package Model.Chess.pieces;

import Model.BoardGame.Board;
import Model.Chess.ChessPiece;
import Model.Chess.Color;

public class Rook extends ChessPiece {
    // Classe torre
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R"; // Retorna apenas o "R" para identificarmos que é um rook ( torre )
    }
}
