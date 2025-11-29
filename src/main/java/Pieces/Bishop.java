package pieces;

class Bishop extends Piece { 
    public Bishop(Color c) { super(c); } 

    // for the dipslay of the piece
    @Override
    public String toString() {
        return this.getColor() == Color.White ? "\u2657" : "\u265D";
    }
}