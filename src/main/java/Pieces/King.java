package pieces;
class King extends Piece { 
    public King(Color c) { super(c); } 

    // for the dipslay of the piece
    @Override
    public String toString() {
        return this.getColor() == Color.White ? "\u2654" : "\u265A";
    }
}