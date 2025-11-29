package board;
import pieces.Piece;

public class Tile {
    private Piece piece;

    public Tile(int rank, int file) {
        this.piece = null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public boolean isOccupied() {
        return this.piece != null;
    }
}