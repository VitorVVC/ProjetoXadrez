package Model.Application;

import Model.Chess.ChessMatch;
import Model.Chess.ChessPiece;
import Model.Chess.ChessPosition;
import Model.Chess.Color;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    // Código para limpar as telas
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public static void printBoard(ChessPiece[][] pieces) { // Considerando matriz quadrada
        int pieceLenght = pieces.length;
        for (int i = 0; i < pieceLenght; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieceLenght; j++) {
                printPiece(pieces[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    // Método auxiliar para imprimir uma peça
    private static void printPiece(ChessPiece piece, boolean background) {
        if (background) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        } else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    // Método para ler uma posição do usuário
    public static ChessPosition readChessPosition(Scanner sc) { // Método que recebemos como entrada um Scanner SC para lermos oque for digitado pelo usuario em forma de "coordenadas" de xadrez. ( Exemplo: C3 )
        try {
            String s = sc.nextLine();  // Salvamos oque foi escrito
            char column = s.charAt(0); // Pegamos apenas a coluna "C"
            int row = Integer.parseInt(s.substring(1)); // Pegamos apenas o inteiro "3" +  --> // Recortar a minha string na posicao 1 e converteremos o resultado para um "int".

            return new ChessPosition(column, row); // Retornamos uma nova coordenada do board de xadrez
        } catch (RuntimeException e) {
            throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
        }
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) { // Considerando matriz quadrada
        int pieceLenght = pieces.length;
        for (int i = 0; i < pieceLenght; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieceLenght; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printMatch(ChessMatch chessMatch) {
        printBoard(chessMatch.getPieces());
        System.out.println();
        System.out.println("Turn : " + chessMatch.getTurn());
        System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
    }

}
