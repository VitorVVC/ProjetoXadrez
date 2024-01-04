package Model.BoardGame;

public class Position {
    // Toda possição só existe perante uma linha e coluna
    private Integer rows;
    private Integer columns;

    // Construtor
    public Position(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
    }

    // Método para "setar" os valores de linha e columa
    public void setValues(int row, int column) {
        this.rows = row;
        this.columns = column;
    }

    // Métodos Get E Set \\
    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return rows + ", " + columns;
    }
}
