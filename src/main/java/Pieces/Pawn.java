package pieces;
class Pawn extends Piece { 
    public Pawn(Color c) { super(c); } 
    /////FIX THIS WHOLE METHOD LATER
    @Override
    protected int[][] getMoveDirections() {
        ///definitely needs to be fixed for captures and initial double moves but for now
        return new int[][] { {1, 0}};
    }
    @Override
    protected boolean isSliding() { return false; }

    // for the dipslay of the piece
    @Override
    public String toString() {
        //  asciii code for chesss pieces
        return this.getColor() == Color.Black ? "\u2659" : "\u265F";
    }
}