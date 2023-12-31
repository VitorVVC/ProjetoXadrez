package Model.Chess;

import Model.BoardGame.Board;
import Model.BoardGame.Piece;
import Model.BoardGame.Position;
import Model.Chess.pieces.King;
import Model.Chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {
    // Coração do projeto, classe onde nosso jogo de xadrez rodará


    // Recebe um board como atributo e o declara justamente na criação do xadrez
    private Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    // Construtor
    public ChessMatch() {
        this.board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    // Método "getPieces" que recebe as peças do board e as "salva" em um método ( aux )
    public ChessPiece[][] getPieces() {
        Integer boardRows = board.getRows();
        Integer boardColumns = board.getColumns();

        ChessPiece[][] aux = new ChessPiece[boardRows][boardColumns];
        for (int i = 0; i < boardRows; i++) {
            for (int j = 0; j < boardColumns; j++) {
                aux[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return aux;
    }

    // Método para colocar peças nas coordenadas do xadrez
    private void placeNewPiece(Character column, Integer row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    // Método para mover peças, por agora apenas "sobrescreve" as posicoes, no futuro capturaremos as pecas.
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPoint) {
        // Recebe uma posicao de entrada e onde é o seu "alvo".
        // Cria duas posicões padrões que serão as passadas no argumento, porém "convertidas" para toPosition();
        Position source = sourcePosition.toPosition();
        Position target = targetPoint.toPosition();
        // Valida a posição de entrada
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        // Realiza o possivel movimento de captura com ambas as coordenadas
        Piece capturedPiece = makeMove(source, target);
        // Retorna uma possivel peça capturada

        if (testCheck(currentPlayer)) {
            // Jogador se colocou em cheque
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }

        check = (testCheck(opponent(currentPlayer))) ? true : false;

        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    // Método auxiliar performChessMove. Valida uma posicao
    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("There is no piece on source position");
        }
        if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
            throw new ChessException("The chosen piece is not yours");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible moves for the chosen piece");
        }
    }

    // Método para validar a posição de destino
    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

    // Método auxiliar para performChessMove. Realiza um movimento perante uma posicao inicial e final
    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source); // Removendo a peca na posicao de origem
        Piece capturedPiece = board.removePiece(target); // Removendo uma possivel peca da posicao de destino
        board.placePiece(p, target); // Colocamos a peça de origem na posicao de destino

        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        return capturedPiece; // Retornamos apenas a peca capturada
    }

    // Método para desfazer o movimento, para não colocar-se em cheque ( Fazer os movimentos contrários do makeMove )
    private void undoMove(Position source, Position target, Piece capturedPiece) {
        Piece p = board.removePiece(target);
        board.placePiece(p, source);

        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    // Identificar quem é o oponente
    private Color opponent(Color color) {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    // Método para ler a lista de pecas e filtrar por cor para retornar o rei
    private ChessPiece king(Color color) {
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());

        for (Piece p : list) {
            if (p instanceof King) {
                return (ChessPiece) p;
            }
        }
        throw new IllegalStateException("There is no " + color + " king on the board");
    }

    // Método para verificar se o REI de determinada cor não está em check
    private boolean testCheck(Color color) {
        // Pegamos a posição em formato de matriz do nosso rei de X cor
        Position kingPosition = king(color).getChessPosition().toPosition();
        // Criamos uma lista de pecas do oponente, filtrando pela cor com o método opponent
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());
        for (Piece p : opponentPieces) {
            boolean[][] mat = p.possibleMoves();
            if (mat[kingPosition.getRows()][kingPosition.getColumns()]) {
                // Rei em cheque
                return true;
            }
        }
        return false;
    }

    // Método para colocar peças nas coordenadas de matriz
    private void initialSetup() {
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck() {
        return check;
    }
}
