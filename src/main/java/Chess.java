import board.Board;
import renderer.BoardPrinter;

public class Chess {
    public static void main(String[] args) {
        ///simple ascii board print test
        Board board = new Board();
        BoardPrinter printer = new BoardPrinter();
        printer.printBoard(board);
    }
}