import board.Board;
import board.Tile;
import renderer.BoardPrinter;
import java.util.Scanner;
import input.textInputParser;
import pieces.Piece;
import java.util.List;
import pieces.Color;


//main driver class for the chess game
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

        //primary game loop
        while(true){
            //calculate turn info
            turnNumber = (moveCount + 1) / 2;
            //determine who's turn it is. odd is white even is black
            Color currentTurnColor = (moveCount % 2 != 0) ? Color.White : Color.Black;
            //store turns seperate from moves to keep track of who is moving and what color they are
            System.out.println("Turn " + turnNumber + ": It is " + currentTurnColor + "'s move.");

            Tile selectedTile = promptForSourceTile(scanner, board, currentTurnColor);

            // when user types quit it equals null and we can exit the game
            if (selectedTile == null) break;

            //display selected piece
            Piece selectedPiece = selectedTile.getPiece();
            System.out.println("Selected Piece: " + selectedPiece.toString());

            //display possible moves
            List<Tile> possibleMoves = selectedPiece.getPossibleMoves(board, selectedTile);
            BoardPrinter.printBoard(board, possibleMoves);

            //increment move count if a move was made
            boolean moveWasMade = promptForDestination(scanner, board, selectedTile, possibleMoves);
            if (moveWasMade) { moveCount++; }
        }
        scanner.close();
    }










        //helper method #1 - could maybe hide this somewhere else later
        private static Tile promptForSourceTile(Scanner scanner, Board board, Color currentTurnColor) {
        while (true) {
            System.out.print("Enter the piece to move (example: e2, g8...): ");
            String rawInput = scanner.nextLine();

            // secret option to exit
            if (rawInput.equals("q") || rawInput.equals("quit") || rawInput.equals("exit")) {
                return null; // Returning null means we quit the game
            }

            //use try catch to handle invalid input and restart loop
            try {
                int[] pieceLocation = textInputParser.parseInput(rawInput);
                Tile target = board.getTile(pieceLocation[0], pieceLocation[1]);

                // VALIDATION CHECKS
                //is tile occupied
                if (!target.isOccupied()) {
                    System.out.println("Error: There is no piece at " + rawInput + ". Try again!");
                    continue; 
                }

                //is piece the correct color for the current turn
                Piece pieceOnTile = target.getPiece();
                if (pieceOnTile.getColor() != currentTurnColor) {
                    System.out.println("Error: That is a " + pieceOnTile.getColor() + " piece! It is " + currentTurnColor + "'s turn. Try again!");
                    continue; 
                }

                //does the piece have any valid moves
                List<Tile> moves = pieceOnTile.getPossibleMoves(board, target);
                if (moves.isEmpty()) {
                    System.out.println("Error: That piece has no valid moves! Try again!");
                    continue; 
                }

                // If we get here then the piece exists, is proper color, and has valid moves. Return the tile!
                return target;

            } catch (Exception e) {
                System.out.println("Error: Invalid input format. Try again!");
            }
        }
    }


    //helper method #2 - could maybe hide this somewhere else later
    private static boolean promptForDestination(Scanner scanner, Board board, Tile startTile, List<Tile> allowedMoves) {
        Piece pieceToMove = startTile.getPiece();

        while (true) {
            System.out.print("Enter destination for " + pieceToMove + " (or type 'cancel'): ");
            String destInput = scanner.nextLine();

            if (destInput.equals("cancel")) {
                return false; // Returning false means move wasnt made so we can reprompt for source tile
            }

            //use try catch to handle invalid input
            try {
                //  parse input and get target tile
                int[] destCoords = textInputParser.parseInput(destInput);
                Tile targetTile = board.getTile(destCoords[0], destCoords[1]);

                if (allowedMoves.contains(targetTile)) {
                    // EXECUTE THE MOOOVE!!!!
                    startTile.setPiece(null);
                    targetTile.setPiece(pieceToMove);
                    
                    BoardPrinter.printBoard(board);
                    return true; // Returning true means the move was made successfully
                } else {
                    System.out.println("Invalid move! That tile is not highlighted. Try again!");
                }
            } catch (Exception e) {
                System.out.println("Invalid destination format.");
            }
        }
    }
}