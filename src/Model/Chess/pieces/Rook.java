package Model.Chess.pieces;

import Model.BoardGame.Board;
import Model.BoardGame.Position;
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

    // Método para mover a peça TORRE, que deve seguir tais regras:
    // Pode se mover apenas na horizontal / vertical. Até ir de encontro com uma peça aliada ou do inimigo.
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        // above
        p.setValues(position.getRows() - 1, position.getColumns());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
            p.setRows(p.getRows() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        // left
        p.setValues(position.getRows(), position.getColumns() - 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
            p.setColumns(p.getColumns() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        // right
        p.setValues(position.getRows(), position.getColumns() + 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
            p.setColumns(p.getColumns() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        // below
        p.setValues(position.getRows() + 1, position.getColumns());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
            p.setRows(p.getRows() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        return mat;
    }
}
