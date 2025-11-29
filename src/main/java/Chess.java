import board.Board;
import board.Tile;
import renderer.BoardPrinter;
import java.util.Scanner;
import input.textInputParser;
import pieces.Piece;
import java.util.List;


///THIS FILE IS MAINLY USED FOR TESTING PURPOSES RIGHT NOW
public class Chess {
    public static void main(String[] args) {
        Board board = new Board();
        BoardPrinter.printBoard(board);
        while(true){
            ///simple ascii board print test
            ////////USES DEPENDENCY INJECTION!!!!!!!!!!!!!!!
            


            //GEt what piece user wants to move in chess notation
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the piece to move (e.g., e2): ");
            String rawInput = scanner.nextLine();
            int[] pieceLocation = textInputParser.parseInput(rawInput);

            int rank = pieceLocation[0];
            int file = pieceLocation[1];

            // get the tile they selected
            Tile selectedTile = board.getTile(rank, file);

            // check if its empty
            if (selectedTile.isOccupied()) {
                Piece selectedPiece = selectedTile.getPiece();
                System.out.println("Selected Piece: " + selectedPiece.toString());

                //calculate possible moves
                List<Tile> possibleMoves = selectedPiece.getPossibleMoves(board, selectedTile);

                //print board with possible moves highlighted
                BoardPrinter.printBoard(board, possibleMoves);
            } else {
                System.out.println("There is no piece at " + rawInput);
            }


        }
    }




    
}