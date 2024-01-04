package Model.BoardGame;

import Model.Chess.ChessPiece;
import Model.Chess.ChessPosition;

public class Board {
    // Classe BOARD que recebe linha + coluna e dois vetores de peças ( Peças "A", Peças "B" )
    private Integer rows;
    private Integer columns;
    private Piece[][] pieces;

    public Board(Integer rows, Integer columns) {
        if (rows < 1 || columns < 1) {
            throw new BoardException("Error creating board: There must be at least 1 row and 1 column");
        }
        this.rows = rows;
        this.columns = columns;
        this.pieces = new Piece[rows][columns];
    }

    // Método para retornar uma peça dada a linha e coluna fornecida ( SOBRECARGA [1] )
    public Piece piece(Integer rows, Integer columns) {
        if (!positionExists(rows, columns)) {
            throw new BoardException("Position not on the board");
        }
        return pieces[rows][columns];
    }

    // Método para retornar uma peça dada a posição fornecida ( SOBRECARGA [2] )
    public Piece piece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }
        return pieces[position.getRows()][position.getColumns()];
    }

    // Método para colocação de peças no tabuleiro
    public void placePiece(Piece piece, Position position) {
        if (thereIsAPiece(position)) {
            throw new BoardException("There is already a piece on position " + position);
        }
        pieces[position.getRows()][position.getColumns()] = piece;
        piece.position = position;
    }

    // Método para remoção de peças do no tabuleiro
    public Piece removePiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Position not on the board"); // Confere se a posição existe no tabuleiro
        }
        if (piece(position) == null) { // Caso não haja nenhuma peça nesta posição ele retornará nulo
            return null;
        } else { // Caso contrario criamos uma peca auxiliar que receberá a posição que desejamos remover, declarará a mesma como null, passará que nosso vetor de peças na posição fornecida seja também null e por fim , retorna a "peça" auxiliar.
            Piece aux = piece(position);
            aux.position = null;
            pieces[position.getRows()][position.getColumns()] = null;
            return aux;
        }
    }

    // Método auxiliar para o método "positionExistis)
    private boolean positionExists(Integer row, Integer column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }
    // Método para retornar true or false referente a uma posicão fornecida, conferindo se a posicão de fato existe

    public boolean positionExists(Position position) { // Mudando para public para podermos utilizar em ROOK
        return positionExists(position.getRows(), position.getColumns());
    }

    // Método para retornar true or false referente a posição ser ou não uma peça
    public boolean thereIsAPiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }
        return piece(position) != null;
    }


    // Métodos Get e Set
    public Integer getRows() {
        return rows;
    }

    public Integer getColumns() {
        return columns;
    }

}
