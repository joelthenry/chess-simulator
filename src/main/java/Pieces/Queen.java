package pieces;
class Queen extends Piece { 
    public Queen(Color c) { super(c); } 

    // for the dipslay of the pieces
    @Override
    public String toString() {
        return this.getColor() == Color.White ? "\u2655" : "\u265B";
    }
}