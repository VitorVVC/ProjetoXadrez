package Model.Chess.pieces;

import Model.BoardGame.Board;
import Model.BoardGame.Position;
import Model.Chess.ChessPiece;
import Model.Chess.Color;

public class Knight extends ChessPiece {

    public Knight(Board board, Color color) {
        super(board, color);
    }

    private boolean canMove(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece == null || piece.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {

        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        p.setValues(position.getRows() - 1, position.getColumns() - 2);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        p.setValues(position.getRows() - 2, position.getColumns() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        p.setValues(position.getRows() - 2, position.getColumns() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        p.setValues(position.getRows() - 1, position.getColumns() + 2);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        p.setValues(position.getRows() + 1, position.getColumns() + 2);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        p.setValues(position.getRows() + 2, position.getColumns() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        p.setValues(position.getRows() + 2, position.getColumns() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        p.setValues(position.getRows() + 1, position.getColumns() - 2);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }
        return mat;
    }

    @Override
    public String toString() {
        return "N";
    }
}
