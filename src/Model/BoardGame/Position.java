package Model.BoardGame;

public class Position {
    private Integer rows;
    private Integer columns;

    public Position(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public void setValues(Integer rows, Integer columns) {
        /*if (rows < 0 || columns < 0) {
            throw new DomainException();
        }
         */
        this.rows = rows;
        this.columns = columns;
    }

    // Métodos Get E Set \\
    public Integer getrows() {
        return rows;
    }

    public void setrows(Integer rows) {
        this.rows = rows;
    }

    public Integer getcolumns() {
        return columns;
    }

    public void setcolumns(Integer columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return rows + ", " + columns;
    }
}
