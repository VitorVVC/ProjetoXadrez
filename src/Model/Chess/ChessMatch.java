package Model.Chess;

import Model.BoardGame.Board;

public class ChessMatch {
    // Coração do projeto

    private Board board;

    public ChessMatch() {
        this.board = new Board(8, 8);
    }

    public ChessPiece[][] getPieces() {
        Integer boardRows = board.getRows();
        Integer boardColumns = board.getRows();

        ChessPiece[][] aux = new ChessPiece[boardRows][boardColumns];
        for (int i = 0; i < boardRows; i++) {
            for (int j = 0; j < boardColumns; j++) {
                aux[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return aux;
    }


}
