package Model.Chess.pieces;

import Model.BoardGame.Board;
import Model.BoardGame.Position;
import Model.Chess.ChessPiece;
import Model.Chess.Color;

public class Queen extends ChessPiece {

    public Queen(Board board, Color color) {
        super(board, color);
    }
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        // Acima
        p.setValues(position.getRows() - 1, position.getColumns());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
            p.setRows(p.getRows() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        // Esquerda
        p.setValues(position.getRows(), position.getColumns() - 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
            p.setColumns(p.getColumns() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        // Direita
        p.setValues(position.getRows(), position.getColumns() + 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
            p.setColumns(p.getColumns() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        // Baixo
        p.setValues(position.getRows() + 1, position.getColumns());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
            p.setRows(p.getRows() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        // NW ( Noroeste )
        p.setValues(position.getRows() - 1, position.getColumns() - 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
            p.setValues(p.getRows() - 1, p.getColumns() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }
        // NE ( Nordeste )
        p.setValues(position.getRows() - 1, position.getColumns() + 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
            p.setValues(p.getRows() - 1, p.getColumns() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }
        // SE ( Sudeste )
        p.setValues(position.getRows() + 1, position.getColumns() + 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
            p.setValues(p.getRows() + 1, p.getColumns() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }
        // SW ( Sudoeste )
        p.setValues(position.getRows() + 1, position.getColumns() - 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
            p.setValues(p.getRows() + 1, p.getColumns() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        return mat;
    }

    @Override
    public String toString() {
        return "Q";
    }
}

