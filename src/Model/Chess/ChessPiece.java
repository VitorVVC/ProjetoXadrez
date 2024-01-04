package Model.Chess;

import Model.BoardGame.Board;
import Model.BoardGame.Piece;
import Model.BoardGame.Position;

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

    // Método para identificar a peça que "irá" de encontro com o parametro e identificará se é ou não inimigo

    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position); // Pegamos a peca da posição fornecida

        return p != null && p.getColor() != color; // Testando se a cor é diferente da minha peça ( Logo uma peça adversaria ) && Verifica se realmente existe uma peça ou não
    }
}
