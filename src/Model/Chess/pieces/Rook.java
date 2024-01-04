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
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getRows()];


        Position p = new Position(0, 0);

        // Acima
        p.setValues(position.getRows() - 1, position.getColumns()); // Assim nós obtemos a linha ACIMA de nossa peça
        while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) { // Enquanto a posição p existir e não houver peça nesta posição nos marcaremos ela como "true".
            mat[p.getRows()][p.getColumns()] = true;
            p.setRows(p.getRows() - 1);
        }
        if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        // Esquerda
        p.setValues(position.getRows(), position.getColumns() - 1); // Assim nós obtemos a coluna ESQUERDA de nossa peça
        while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) { // Enquanto a posição p existir e não houver peça nesta posição nos marcaremos ela como "true".
            mat[p.getRows()][p.getColumns()] = true;
            p.setColumns(p.getColumns() - 1);
        }
        if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        // Direita
        p.setValues(position.getRows(), position.getColumns() + 1); // Assim nós obtemos a coluna DIREITA de nossa peça
        while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) { // Enquanto a posição p existir e não houver peça nesta posição nos marcaremos ela como "true".
            mat[p.getRows()][p.getColumns()] = true;
            p.setColumns(p.getColumns() + 1);
        }
        if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }

        // Baixo
        p.setValues(position.getRows() + 1, position.getColumns()); // Assim nós obtemos a linha ABAIXO de nossa peça
        while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) { // Enquanto a posição p existir e não houver peça nesta posição nos marcaremos ela como "true".
            mat[p.getRows()][p.getColumns()] = true;
            p.setRows(p.getRows() + 1);
        }
        if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
            mat[p.getRows()][p.getColumns()] = true;
        }
        return mat;
    }
}
