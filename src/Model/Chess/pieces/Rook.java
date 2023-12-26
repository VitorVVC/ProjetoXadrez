package Model.Chess.pieces;

import Model.BoardGame.Board;
import Model.Chess.ChessPiece;
import Model.Chess.Color;

public class Rook extends ChessPiece {
    // Classe Rook ( Torre ). Que herda os métodos de ChessPiece
    public Rook(Board board, Color color) {
        super(board, color);
    }

    // Método toString, para retornar apenas qual a peça
    @Override
    public String toString() {
        return "R";
    }
}
