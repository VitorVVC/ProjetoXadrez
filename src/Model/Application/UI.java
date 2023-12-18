package Model.Application;

import Model.Chess.ChessMatch;
import Model.Chess.ChessPiece;

public class UI {
    public static void printBoard(ChessPiece[][] pieces) { // Considerando matriz quadrada
        Integer pieceLenght = pieces.length;
        for (int i = 0; i < pieceLenght; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieceLenght; j++) {
                printPiece(pieces[i][j]);
            }
            System.out.println();
        }
        System.out.print("  a b c d e f g h");
    }

    // Método auxiliar para imprimir uma peça
    private static void printPiece(ChessPiece piece) {
        if (piece == null) {
            System.out.print("-");
        } else {
            System.out.print(piece);
        }
        System.out.print(" ");
    }

}
