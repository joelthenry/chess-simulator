import board.Board;
import board.Tile;
import renderer.BoardPrinter;
import java.util.Scanner;
import input.textInputParser;
import pieces.Piece;
import java.util.List;
import pieces.Color;


///THIS FILE IS MAINLY USED FOR TESTING PURPOSES RIGHT NOW
public class Chess {
    public static void main(String[] args) {
        int moveCount = 1; // odd = white's turn, even = black's turn
        int turnNumber = moveCount;

        /// Initialize a new chess board
        /// ///simple ascii board print test
            ////////USES DEPENDENCY INJECTION!!!!!!!!!!!!!!!
        Board board = new Board();
        BoardPrinter.printBoard(board);
        Scanner scanner = new Scanner(System.in);
        while(true){
            
            turnNumber = (moveCount + 1) / 2;
            //determine who's turn it is. odd is white even is black
            Color currentTurnColor = (moveCount % 2 != 0) ? Color.White : Color.Black;
            //store turns seperate from moves to keep track of who is moving and what color they are
            System.out.println("Turn " + turnNumber + ": It is " + currentTurnColor + "'s move.");

            //defaults to null vals and enters another while loop until you select a valid piece to move
            Piece selectedPiece = null;
            Tile selectedTile = null;

            while (selectedPiece == null) {
                //GEt what piece user wants to move in chess notation
                System.out.print("Enter the piece to move (example: e2, g8, b1...): ");
                String rawInput = scanner.nextLine();

                //secret option to exit the game by typing q, quit, or exit when prompted for the piece
                if (rawInput.equals("q") || rawInput.equals("quit") || rawInput.equals("exit")) {
                    scanner.close();
                    return; // Ends the program
                }

                //uses try because the parser might throw an exception if the input is invalid
                try {
                    int[] pieceLocation = textInputParser.parseInput(rawInput);
                    int rank = pieceLocation[0];
                    int file = pieceLocation[1];
                    
                    // get the tile they selected
                    Tile target = board.getTile(rank, file);

                    // check if its empty
                    if (!target.isOccupied()) {
                        System.out.println("Error: There is no piece at " + rawInput + ". Try again!");
                        continue; // Restarts the inner while loop without incrementing moveCount
                    }

                    // check if its the correct color
                    Piece pieceOnTile = target.getPiece();
                    if (pieceOnTile.getColor() != currentTurnColor) {
                        System.out.println("Error: That is a " + pieceOnTile.getColor() + " piece! It is " + currentTurnColor + "'s turn. Try again!");
                        continue; // Restarts the inner loop without incrementing moveCount
                    }

                    //checks if the piece has any valid moves
                    List<Tile> moves = pieceOnTile.getPossibleMoves(board, target);
                    if (moves.isEmpty()) {
                        System.out.println("Error: That piece has no valid moves! Try again!");
                        continue; // Restarts the inner loop without incrementing moveCount
                    }

                    // if we reach here the piece exists, is the correct color, and has valid moves
                    selectedTile = target;
                    selectedPiece = pieceOnTile;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage() + " Try again!");
                }
            }


            System.out.println("Selected Piece: " + selectedPiece.toString());
            List<Tile> possibleMoves = selectedPiece.getPossibleMoves(board, selectedTile);
            BoardPrinter.printBoard(board, possibleMoves);

            while (true) {
                System.out.print("Enter destination for " + selectedPiece + " (or type 'cancel' to pick different piece): ");
                String destInput = scanner.nextLine();

                if (destInput.equals("cancel")) {
                    // Break this inner loop, which goes back to the top of the MAIN loop
                    // effectively just restarts the turn selection so you can pick a different piece if wanted
                    break; 
                }

                try {
                    int[] destCoords = textInputParser.parseInput(destInput);
                    Tile targetTile = board.getTile(destCoords[0], destCoords[1]);

                    // check if target tile is in our possible moves
                    if (possibleMoves.contains(targetTile)) {
                        
                        //if it is then remove piece from the starting tile and add it to the target tile
                        selectedTile.setPiece(null);
                        targetTile.setPiece(selectedPiece);
                        
                        //print the updated board
                        BoardPrinter.printBoard(board);
                        
                        //now we can increment the move count and break the loop to switch turns
                        moveCount++; 
                        break; 
                    } else {
                        System.out.println("Invalid move! That tile is not highlighted. Try again!");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid destination format.");
                }
            }
        }
    }
    
}