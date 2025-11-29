package pieces;
class Knight extends Piece { 
    public Knight(Color c) { super(c); } 

    // for the dipslay of the piece
    @Override
    public String toString() {
        return this.getColor() == Color.White ? "\u2658" : "\u265E";
    }
}