package Model.Chess;

import Model.BoardGame.Position;

public class ChessPosition {
    // Parametros da classe
    private Character column;
    private Integer row;

    // Construtor
    public ChessPosition(Character column, Integer row) {
        if (column < 'a' || column > 'h' || row < 1 || row > 8) {
            throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8.");
        }
        this.column = column;
        this.row = row;
    }
    // Método para converter uma posicao padrão para position
    protected Position toPosition() {
        return new Position(8 - row, column - 'a');
        // Realiza o calculo de conversão para uma nova posicão, "recebendo" por exemplo: C2
        // Recebe o C2 e passa o C - 'A' que possui um valor definido, exemplo "0"
        // Recebe o 2 e passa por uma subtração sendo --> 8 - 2.

    }
    // Método para retornar uma posicao de xadrez referente a uma posicao padrão
    protected static ChessPosition fromPosition(Position position) {
        return new ChessPosition((char) ('a' + position.getColumns()), 8 - position.getRows());
    }

    @Override
    public String toString() {
        return "" + column + row;
    }

    // Métodos Get
    public Character getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }
}
