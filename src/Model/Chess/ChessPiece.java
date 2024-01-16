package Model.Chess;

import Model.BoardGame.Board;
import Model.BoardGame.Piece;
import Model.BoardGame.Position;

public abstract class ChessPiece extends Piece {
    // Class de peça de xadrez, que herda de Piece e possue uma cor de identificação ( Branco OU Preto )
    private Color color;
    private int moveCount;

    // Construtor que recebe como parametro um board e uma cor
    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    // Métodos para incrementar e decrementar o moveCount
    public void incrieaseMoveCount() {
        moveCount++;
    }

    public void decreaseMoveCount() {
        moveCount--;
    }


    // Método para identificar a peça que "irá" de encontro com o parametro e identificará se é ou não inimigo
    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p.getColor() != color;
    }

    // Métodos GET
    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(position);
    }

    // Método GET
    public Color getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
