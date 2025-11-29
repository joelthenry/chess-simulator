package renderer;

import board.Board;
import board.Tile;

public class BoardPrinter {
    private static final int firstRankAndCollumn = 0;
    private static final int finalRankAndCollumn = 7;

    ///////////WARNNING : Due to unicode decisions the white pieces are technicallly "black" in the unicode and vice versa but they appear correctly on the board

    ////////// DEPENDENCY INJECTION!!!!!!!!!!!!!!!!!!!!!
    public static void printBoard(Board board) {


        //newline for spacing
        System.out.println();
        System.out.println("   ===============================");


        //start high rank and go down to low rank since high ranks are black side and should appear at the top
        for (int rank = finalRankAndCollumn; rank >= firstRankAndCollumn; rank--) {
        //goes through all tiles in a rank

            //Rank labels
            System.out.print((rank + 1) + " |");


            //goes through all tiles in a collumn
            for (int file = firstRankAndCollumn; file <= finalRankAndCollumn; file++) {
                Tile tile = board.getTile(rank, file);
                
                if (tile.isOccupied()) {
                    // if tile is occupied, print the piece
                    System.out.print(' ' + tile.getPiece().toString() + ' ');
                } else {
                    // If empty, print a placeholder
                    System.out.print("   ");
                }

                System.out.print('|');

            }
            // Newline after every rank
            System.out.println();
            if (rank != firstRankAndCollumn) {
                System.out.println("   -------------------------------");
            }
        }
        
        // Print column labels
        System.out.println("   ===============================");
        System.out.println("    a   b   c   d   e   f   g   h");
        //newline for spacing
        System.out.println();
    }
}