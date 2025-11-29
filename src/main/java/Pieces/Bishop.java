package pieces;

class Bishop extends Piece { 
    public Bishop(Color c) { super(c); } 
    @Override
    public String toString() {
        return this.getColor() == Color.White ? "B" : "b";
    }
}