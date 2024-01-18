package Model.Chess.pieces;

import Model.BoardGame.Board;
import Model.BoardGame.Position;
import Model.Chess.ChessMatch;
import Model.Chess.ChessPiece;
import Model.Chess.Color;

public class King extends ChessPiece {
    // Classe "KING" (REI) que herda os métodos de ChessPiece

    private ChessMatch chessMatch;

    // Construtor
    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
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

    // Método auxiliar para testar a condicao de roque
    private boolean testRookCastling(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);

        return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
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

        // #specialmove ~Castling~
        if (getMoveCount() == 0 && !chessMatch.getCheck()) {
            // #special move ~Castling kingside rook~ ( Roque pequeno )
            Position posT1 = new Position(position.getRows(), position.getColumns() + 3);
            if (testRookCastling(posT1)) {
                Position p1 = new Position(position.getRows(), position.getColumns() + 1);
                Position p2 = new Position(position.getRows(), position.getColumns() + 2);
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
                    mat[position.getRows()][position.getColumns() + 2] = true; // O rei pode ir duas casas a direita
                }

            }
            // #special move ~Castling queenside rook~ ( Roque grande )
            Position posT2 = new Position(position.getRows(), position.getColumns() - 4);
            if (testRookCastling(posT2)) {
                Position p1 = new Position(position.getRows(), position.getColumns() - 1);
                Position p2 = new Position(position.getRows(), position.getColumns() - 2);
                Position p3 = new Position(position.getRows(), position.getColumns() - 3);
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
                    mat[position.getRows()][position.getColumns() - 2] = true; // O rei pode ir duas casas a esquerda
                }

            }
        }

        return mat;
    }
}
