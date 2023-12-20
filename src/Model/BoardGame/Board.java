package Model.BoardGame;

public class Board {

    private Integer rows;
    private Integer columns;
    private Piece[][] pieces;

    public Board(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
        this.pieces = new Piece[rows][columns];
    }

    public Piece piece(Integer rows, Integer columns) {
        return pieces[rows][columns];
    }

    public Piece piece(Position position) {
        return pieces[position.getRows()][position.getColumns()];
    }

    public void placePiece(Piece piece, Position position){
        pieces[position.getRows()][position.getColumns()] = piece;
        piece.position = position;
    }

    // Métodos Get e Set
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
}
