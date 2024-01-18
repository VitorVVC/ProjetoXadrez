package Model.Chess.pieces;

import Model.BoardGame.Board;
import Model.BoardGame.Position;
import Model.Chess.ChessMatch;
import Model.Chess.ChessPiece;
import Model.Chess.Color;

public class Pawn extends ChessPiece {
    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
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
            // #specialmove en passant white
            if (position.getRows() == 3) {
                Position left = new Position(position.getRows(), position.getColumns() - 1);
                if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                    mat[left.getRows() - 1][left.getColumns()] = true;
                }
                Position right = new Position(position.getRows(), position.getColumns() + 1);
                if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                    mat[right.getRows() - 1][right.getColumns()] = true;
                }
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
            // #specialmove en passan black
            if (position.getRows() == 4) {
                Position left = new Position(position.getRows(), position.getColumns() - 1);
                if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                    mat[left.getRows() + 1][left.getColumns()] = true;
                }
                Position right = new Position(position.getRows(), position.getColumns() + 1);
                if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                    mat[right.getRows() + 1][right.getColumns()] = true;
                }
            }
        }
        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}

