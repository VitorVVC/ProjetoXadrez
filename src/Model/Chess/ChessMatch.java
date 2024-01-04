package Model.Chess;

import Model.BoardGame.Board;
import Model.BoardGame.Piece;
import Model.BoardGame.Position;
import Model.Chess.pieces.King;
import Model.Chess.pieces.Rook;

public class ChessMatch {
    // Coração do projeto, classe onde nosso jogo de xadrez rodará


    // Recebe um board como atributo e o declara justamente na criação do xadrez
    private Board board;

    // Construtor
    public ChessMatch() {
        this.board = new Board(8, 8);
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
        return (ChessPiece) capturedPiece;
    }

    // Método auxiliar performChessMove. Valida uma posicao
    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) { // Apenas recebe uma posicão de entrada e a valida de forma mais adequada para o caso
            throw new ChessException("There is no piece on source position");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) { // Testando se a peça tem movimentos possiveis, caso não tenha lança um erro
            throw new ChessException("There is no possible moves for the chosen piece");
        }
    }

    // Método para validar a posição de destino
    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) { // Pegamos a peça que há no source e verificamos se o movimento dela é possivel até o target
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

    // Método auxiliar para performChessMove. Realiza um movimento perante uma posicao inicial e final
    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source); // Removendo a peca na posicao de origem
        Piece capturedPiece = board.removePiece(target); // Removendo uma possivel peca da posicao de destino

        board.placePiece(p, target); // Colocamos a peça de origem na posicao de destino
        return capturedPiece; // Retornamos apenas a peca capturada
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


}
