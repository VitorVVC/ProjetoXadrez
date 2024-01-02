package Model.Chess;

import Model.BoardGame.Board;
import Model.BoardGame.Piece;

public abstract class ChessPiece extends Piece {
    // Class de peça de xadrez, que herda de Piece e possue uma cor de identificação ( Branco OU Preto )
    private Color color;

    // Construtor que recebe como parametro um board e uma cor
    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    // Método GET
    public Color getColor() {
        return color;
    }
}
