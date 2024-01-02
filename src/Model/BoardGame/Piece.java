package Model.BoardGame;

public abstract class Piece {
    // Classe piece que recebe como parametros uma posição e um BOARD
    protected Position position; // Protected para ser usado em outros métodos do mesmo package
    private Board board; // Private para evitar erros e acessibilidade do usuário

    // Construtor
    public Piece(Board board) {
        this.position = null;
        this.board = board;
    }

    // Método para operação de movimentos possiveis
    public abstract boolean[][] possibleMoves();

    // Rook methods
    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRows()][position.getColumns()];
    }

    // Método para conferir se a peça tem condicoes de realizar algum movimento
    public boolean isThereAnyPossibleMove() {
        boolean[][] mat = possibleMoves();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j]) { // Existe movimento possivel
                    return true;
                }
            }
        }// Não existe movimento possivel
        return false;
    }

    // Métodos Get e set
    protected Board getBoard() {
        return board;
    }

}
