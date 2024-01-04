package Model.Application;

import Model.Chess.ChessException;
import Model.Chess.ChessMatch;
import Model.Chess.ChessPiece;
import Model.Chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        while (true) { // While apenas para testar a funcão de movimento das peç9as

            try {
                UI.clearScreen();

                UI.printBoard(chessMatch.getPieces()); // Método de UI ( User Interface ). Que serve para printar o board criado fora do While
                System.out.println();
                System.out.print("Source: "); // Pedimos uma "coordenada" de qual a peça
                ChessPosition source = UI.readChessPosition(sc); // Recebemos e armazenamos a mesma na função de ler estas coordenadas dadas em "formato xadrez"

                // Adicionando print de movimentos possiveis

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.print("\nTarget: "); // Pedimos uma "coordenada" de destino
                ChessPosition target = UI.readChessPosition(sc); // Recebemos e armazenamos tal coordenada na função antes usada

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target); // Fazemos um movimento passando a coordenada de entrada e o destino com a função "performChessMove".
            } catch (ChessException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }


    }
}