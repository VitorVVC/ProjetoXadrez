package Model.Chess;

import Model.BoardGame.BoardException;

public class ChessException extends BoardException {
    // Classe de exceptions para o pacote "Chess". Herda do pacote de excessoes de tabuleiro
    public ChessException(String msg) {
        super(msg);
    }
}
