/*** Класс позиции на карте*/
public class Position {
    private final int x;
    private final int y;
    private final Position previousPosition;

    public Position(int x, int y, Position previous){
        this.x = x;
        this.y = y;
        previousPosition = previous;
    }

    /**Возвращает положение позиции на оси абсцисс*/
    public int getX() {
        return x;
    }

    /**Возвращает положение позиции на оси ординат*/
    public int getY() {
        return y;
    }

    /**Возвращает предыдущую позицию*/
    public Position getPreviousPosition() {
        return previousPosition;
    }

    /**Возвращает строковое представление позиции*/
    public String toString(){
        return String.format("(%d, %d)", x, y);
    }

    /**Проверяет на равенство два объекта*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    /**Возвращает значение хэш-функции, соответствующее позиции*/
    @Override
    public int hashCode() {
        return x * 10000 + y;
    }
}
