package Model.BoardGame;

public class Piece {

    protected Position[] position;
    private Board board;

    public Piece(Board board) {
        this.position = null;
        this.board = board;
    }

    // Métodos Get e set
    protected Board getBoard() {
        return board;
    }

}
