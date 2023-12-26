package Model.BoardGame;

public class Piece {
    // Classe piece que recebe como parametros uma posição e um BOARD
    protected Position position; // Protected para ser usado em outros métodos do mesmo package
    private Board board; // Private para evitar erros e acessibilidade do usuário

    // Construtor
    public Piece(Board board) {
        this.position = null;
        this.board = board;
    }

    // Métodos Get e set
    protected Board getBoard() {
        return board;
    }

}
