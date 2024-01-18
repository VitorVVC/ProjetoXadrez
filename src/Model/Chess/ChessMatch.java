package Model.Chess;

import Model.BoardGame.Board;
import Model.BoardGame.Piece;
import Model.BoardGame.Position;
import Model.Chess.pieces.*;

import java.security.InvalidParameterException;
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
    private boolean checkMate;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;

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

        ChessPiece movedPiece = (ChessPiece) board.piece(target);

        // #specialmove promotion
        promoted = null;
        if (movedPiece instanceof Pawn) {
            if (movedPiece.getColor() == Color.WHITE && target.getRows() == 0 || movedPiece.getColor() == Color.BLACK && target.getRows() == 7) {
                promoted = (ChessPiece) board.piece(target);
                promoted = replacePromotedPiece("Q");
            }
        }

        check = (testCheck(opponent(currentPlayer))) ? true : false;
        // Testa se o jogo acabou
        if (testCheckMate(opponent(currentPlayer))) {
            checkMate = true;
        } else {
            nextTurn();
        }

        // #specialmove en passant
        if (movedPiece instanceof Pawn && (target.getRows() == source.getRows() - 2) || (target.getRows() == source.getRows() + 2)) {
            enPassantVulnerable = movedPiece;
        } else {
            enPassantVulnerable = null;
        }

        return (ChessPiece) capturedPiece;
    }

    // Método auxiliar de #specialMove promotion
    public ChessPiece replacePromotedPiece(String type) {
        if (promoted == null) {
            throw new IllegalStateException("There is no piece to be promoted");
        }
        if (!type.equalsIgnoreCase("b") && !type.equalsIgnoreCase("n") && !type.equalsIgnoreCase("r") && !type.equalsIgnoreCase("q")) {
            throw new InvalidParameterException("Invalid type for promotion");
        }

        Position pos = promoted.getChessPosition().toPosition();
        Piece p = board.removePiece(pos);
        piecesOnTheBoard.remove(p);

        ChessPiece newPiece = newPiece(type, promoted.getColor());
        board.placePiece(newPiece, pos);
        piecesOnTheBoard.add(newPiece);

        return newPiece;
    }

    // Método auxiliar para replacePromotedPiece
    private ChessPiece newPiece(String type, Color color) {
        if (type.equalsIgnoreCase("b")) {
            return new Bishop(board, color);
        }
        if (type.equalsIgnoreCase("n")) {
            return new Knight(board, color);
        }
        if (type.equalsIgnoreCase("q")) {
            return new Queen(board, color);
        } else {
            return new Rook(board, color);
        }
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
        ChessPiece p = (ChessPiece) board.removePiece(source); // Removendo a peca na posicao de origem
        p.incrieaseMoveCount();
        Piece capturedPiece = board.removePiece(target); // Removendo uma possivel peca da posicao de destino
        board.placePiece(p, target); // Colocamos a peça de origem na posicao de destino

        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        // #specialmove castling kingside rook
        if (p instanceof King && target.getColumns() == source.getColumns() + 2) { // Significa que o rei andou duas casas a direita ( Roque pequeno )
            Position sourceT = new Position(source.getRows(), source.getColumns() + 3);
            Position targetT = new Position(source.getRows(), source.getColumns() + 1);
            ChessPiece rook = (ChessPiece) board.removePiece(sourceT); // Retirar a torre de onde ela esta
            board.placePiece(rook, targetT); // Recoloca esta torre na sua posicao de destino
            rook.incrieaseMoveCount();
        }
        // #specialmove castling queenside rook
        if (p instanceof King && target.getColumns() == source.getColumns() - 2) { // Significa que o rei andou duas casas a esquerda ( Roque grande )
            Position sourceT = new Position(source.getRows(), source.getColumns() - 4);
            Position targetT = new Position(source.getRows(), source.getColumns() - 1);
            ChessPiece rook = (ChessPiece) board.removePiece(sourceT); // Retirar a torre de onde ela esta
            board.placePiece(rook, targetT); // Recoloca esta torre na sua posicao de destino
            rook.incrieaseMoveCount();
        }

        // #specialmove en passant
        if (p instanceof Pawn) {
            if (source.getColumns() != target.getColumns() && capturedPiece == null) {
                Position pawnPosition;
                if (p.getColor() == Color.WHITE) {
                    pawnPosition = new Position(target.getRows() + 1, target.getColumns());
                } else {
                    pawnPosition = new Position(target.getRows() - 1, target.getColumns());
                }
                capturedPiece = board.removePiece(pawnPosition);
                capturedPieces.add(capturedPiece);
                piecesOnTheBoard.remove(capturedPiece);
            }
        }

        return capturedPiece; // Retornamos apenas a peca capturada
    }

    // Método para desfazer o movimento, para não colocar-se em cheque ( Fazer os movimentos contrários do makeMove )
    private void undoMove(Position source, Position target, Piece capturedPiece) {
        ChessPiece p = (ChessPiece) board.removePiece(target);
        p.decreaseMoveCount();
        board.placePiece(p, source);

        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }

        // #specialmove castling kingside rook
        if (p instanceof King && target.getColumns() == source.getColumns() + 2) { // Significa que o rei andou duas casas a direita ( Roque pequeno )
            Position sourceT = new Position(source.getRows(), source.getColumns() + 3);
            Position targetT = new Position(source.getRows(), source.getColumns() + 1);
            ChessPiece rook = (ChessPiece) board.removePiece(targetT); // Retirar a torre de onde ela esta ( destino )
            board.placePiece(rook, sourceT); // Recoloca esta torre na sua posicao de origem
            rook.decreaseMoveCount();
        }
        // #specialmove castling queenside rook
        if (p instanceof King && target.getColumns() == source.getColumns() - 2) { // Significa que o rei andou duas casas a esquerda ( Roque grande )
            Position sourceT = new Position(source.getRows(), source.getColumns() - 4);
            Position targetT = new Position(source.getRows(), source.getColumns() - 1);
            ChessPiece rook = (ChessPiece) board.removePiece(targetT); // Retirar a torre de onde ela esta ( destino )
            board.placePiece(rook, sourceT); // Recoloca esta torre na sua posicao de origem
            rook.decreaseMoveCount();
        }

        // #specialmove en passant
        if (p instanceof Pawn) {
            if (source.getColumns() != target.getColumns() && capturedPiece == enPassantVulnerable) { // Testando se a peca andou na diagonal sem capturar peça, que caracteriza um en passant
                ChessPiece pawn = (ChessPiece) board.removePiece(target);
                Position pawnPosition;
                if (p.getColor() == Color.WHITE) {
                    pawnPosition = new Position(3, target.getColumns());
                } else {
                    pawnPosition = new Position(4, target.getColumns());
                }
                board.placePiece(pawn, pawnPosition);
            }
        }

    }

    // Método para checar os possiveis movimentos de uma peça em determinada posicao de origem
    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    // Método para passar o turno. Referente ao player e sua cor
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

    private boolean testCheckMate(Color color) {
        if (!testCheck(color)) {
            return false;
        }
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list) {
            boolean[][] mat = p.possibleMoves();
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getColumns(); j++) {
                    if (mat[i][j]) {
                        Position source = ((ChessPiece) p).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(source, target);
                        boolean testCheck = testCheck(color);
                        undoMove(source, target, capturedPiece);
                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    // Método para colocar peças nas coordenadas de matriz
    private void initialSetup() {
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE, this));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK, this));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
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

    public boolean getCheckMate() {
        return checkMate;
    }

    public ChessPiece getEnPassantVulnerable() {
        return enPassantVulnerable;
    }

    public ChessPiece getPromoted() {
        return promoted;
    }
}
