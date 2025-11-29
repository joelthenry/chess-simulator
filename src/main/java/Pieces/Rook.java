package pieces;

class Rook extends Piece { 
    public Rook(Color c) { super(c); } 

    // for the dipslay of the piece
    @Override
    public String toString() {
        return this.getColor() == Color.White ? "\u2656" : "\u265C";
    }
}
