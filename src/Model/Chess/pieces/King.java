package Model.Chess.pieces;

import Model.BoardGame.Board;
import Model.BoardGame.Position;
import Model.Chess.ChessPiece;
import Model.Chess.Color;

public class King extends ChessPiece {
    // Classe "KING" (REI) que herda os métodos de ChessPiece

    // Construtor
    public King(Board board, Color color) {
        super(board, color);
    }

    // Método toString, para retornar apenas qual a peça
    @Override
    public String toString() {
        return "K";
    }

    // Método auxiliar para a movimentação do REI
    private boolean canMove(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece == null || piece.getColor() != getColor();
    }

    // Método para mover o REI
    @Override
    public boolean[][] possibleMoves() {

        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        // above
        p.setValues(position.getRows() - 1, position.getColumns());
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }
        // below
        p.setValues(position.getRows() + 1, position.getColumns());
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }
        // left
        p.setValues(position.getRows(), position.getColumns() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }
        // right
        p.setValues(position.getRows(), position.getColumns() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }
        // nw
        p.setValues(position.getRows() - 1, position.getColumns() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }
        // ne
        p.setValues(position.getRows() - 1, position.getColumns() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }
        // sw
        p.setValues(position.getRows() + 1, position.getColumns() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }
        // se
        p.setValues(position.getRows() + 1, position.getColumns() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }
        return mat;
    }
}
