package Model.Chess.pieces;

import Model.BoardGame.Board;
import Model.BoardGame.Position;
import Model.Chess.ChessPiece;
import Model.Chess.Color;

public class Pawn extends ChessPiece {
    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        if (getColor() == Color.WHITE) {
            p.setValues(position.getRows() - 1, position.getColumns());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // Se a posição acima existir e estiver vazia ele poderá mover ( True )
                mat[p.getRows()][p.getColumns()] = true;
            }
            p.setValues(position.getRows() - 2, position.getColumns());
            Position p2 = new Position(position.getRows() - 1, position.getColumns());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) { // Se a posição acima existir e estiver vazia ele poderá mover ( True ) && for a primeira jogada deste peão
                mat[p.getRows()][p.getColumns()] = true;
            }
            p.setValues(position.getRows() - 1, position.getColumns() - 1); // Vertical esquerda
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRows()][p.getColumns()] = true;
            }
            p.setValues(position.getRows() - 1, position.getColumns() + 1); // Vertical direita
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRows()][p.getColumns()] = true;
            }
        } else {
            p.setValues(position.getRows() + 1, position.getColumns());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // Se a posição acima existir e estiver vazia ele poderá mover ( True )
                mat[p.getRows()][p.getColumns()] = true;
            }
            p.setValues(position.getRows() + 2, position.getColumns());
            Position p2 = new Position(position.getRows() + 1, position.getColumns());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) { // Se a posição acima existir e estiver vazia ele poderá mover ( True ) && for a primeira jogada deste peão
                mat[p.getRows()][p.getColumns()] = true;
            }
            p.setValues(position.getRows() + 1, position.getColumns() + 1); // Vertical esquerda
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRows()][p.getColumns()] = true;
            }
            p.setValues(position.getRows() + 1, position.getColumns() + 1); // Vertical direita
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRows()][p.getColumns()] = true;
            }
        }
        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}

