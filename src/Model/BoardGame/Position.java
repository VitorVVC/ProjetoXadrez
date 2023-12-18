package Model.BoardGame;

public class Position {
    private Integer linha;
    private Integer coluna;

    public Position(Integer linha, Integer coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public void setValues(Integer linha, Integer coluna) {
        /*if (linha < 0 || coluna < 0) {
            throw new DomainException();
        }
         */
        this.linha = linha;
        this.coluna = coluna;
    }

    // Métodos Get E Set \\
    public Integer getLinha() {
        return linha;
    }

    public void setLinha(Integer linha) {
        this.linha = linha;
    }

    public Integer getColuna() {
        return coluna;
    }

    public void setColuna(Integer coluna) {
        this.coluna = coluna;
    }

    @Override
    public String toString() {
        return linha + ", " + coluna;
    }
}
