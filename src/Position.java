public class Position {
    private final int x;
    private final int y;
    private final Position previousPosition;

    public Position(int x, int y, Position previous){
        this.x = x;
        this.y = y;
        previousPosition = previous;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position getPreviousPosition() {
        return previousPosition;
    }

    public String toString(){
        return String.format("(%d, %d)", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
