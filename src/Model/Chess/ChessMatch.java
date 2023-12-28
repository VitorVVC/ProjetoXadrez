package Model.Chess;

import Model.BoardGame.Board;
import Model.Chess.pieces.King;
import Model.Chess.pieces.Rook;

public class ChessMatch {
    // Coração do projeto, classe onde nosso jogo de xadrez rodará


    // Recebe um board como atributo e o declara justamente na criação do xadrez
    private Board board;

    // Construtor
    public ChessMatch() {
        this.board = new Board(8, 8);
        initialSetup();
    }

    // Método "getPieces" que recebe as peças do board e as "salva" em um método ( aux )
    public ChessPiece[][] getPieces() {
        Integer boardRows = board.getRows();
        Integer boardColumns = board.getColumns();

        ChessPiece[][] aux = new ChessPiece[boardRows][boardColumns];
        for (int i = 0; i < boardRows; i++) {
            for (int j = 0; j < boardColumns; j++) {
                aux[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return aux;
    }

    // Método para colocar peças nas coordenadas do xadrez
    private void placeNewPiece(Character column, Integer row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }


    // Método para colocar peças nas coordenadas de matriz
    private void initialSetup() {
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }


}
