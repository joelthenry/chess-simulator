package pieces;
class Pawn extends Piece { 
    public Pawn(Color c) { super(c); } 
    
    // for the dipslay of the piece
    @Override
    public String toString() {
        return this.getColor() == Color.White ? "\u2659" : "\u265F";
    }
}