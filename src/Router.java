import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

/*** Класс поиска маршрута (реализует {@link RouteFinder}) */
public class Router implements RouteFinder{
    /** Реализация метода поиска кратчайшего пяти
     * @param map карта
     * @return карта с построенным маршрутом или null, если его нет
     */
    @Override
    public char[][] findRoute(char[][] map){
        LinkedBlockingQueue<Position> queue = new LinkedBlockingQueue<>();
        HashMap<Position, Integer> grayNodes = new HashMap<>();
        Position start = getPosition(map, '@');
        queue.add(start);
        grayNodes.put(start, 0);
        Position end = getPosition(map, 'X');
        while (queue.size() > 0){
            Position currentPosition;
            try {
                currentPosition = queue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            int currX = currentPosition.getX();
            int currY = currentPosition.getY();
            for (int i=-1; i<=1; i++)
                for (int j=-1; j<=1; j++) {
                    int newX = currX + i;
                    int newY = currY + j;
                    if (Math.abs(i+j) == 1 && isMoveCorrect(map, newX, newY)) {
                        Position newPosition = new Position(newX, newY, currentPosition);
                        if (newPosition.equals(end))
                            return getMapWithPath(map, start, currentPosition);
                        if (!grayNodes.containsKey(newPosition)) {
                            grayNodes.put(newPosition, 1);
                            queue.add(newPosition);
                        } else {
                            grayNodes.put(newPosition, grayNodes.get(newPosition) + 1);
                            if (reachesMaxNumberOfNeighbors(map, newX, newY, grayNodes.get(newPosition)))
                                grayNodes.remove(newPosition);
                        }
                    }
                }
        }
        return null;
    }

    /** Находит на карте соответствующий символ и возвращает его позицию
     * @param map карта
     * @param symbol символ
     * @return позиция символа
     */
    private Position getPosition(char[][] map, char symbol){
        for (int i=0; i < map.length; i++)
            for (int j=0; j < map[i].length; j++)
                if (map[i][j] == symbol)
                    return new Position(j, i, null);
        throw new IllegalArgumentException("No such position!");
    }

    /** Проверяет, является ли данная позиция доступной (т.е. она в рамках картах и в ней нет стены)
     * @param map карта
     * @param x положение на оси абсцисс
     * @param y положение на оси ординат
     * @return является ли позиция доступной?
     */
    private boolean isMoveCorrect(char[][] map, int x, int y) {
        return y >= 0 && y < map.length && x >= 0 && x < map[y].length && map[y][x] != '#';
    }

    /** Отображает на карте путь
     * @param map карта
     * @param beginPos начальная позиция
     * @param currentPosition позиция, предшествующая концу
     * @return карта с отображенным на ней путем
     */
    private char[][] getMapWithPath(char[][] map, Position beginPos, Position currentPosition){
        while (!currentPosition.equals(beginPos)){
            map[currentPosition.getY()][currentPosition.getX()] = '+';
            currentPosition = currentPosition.getPreviousPosition();
        }
        return map;
    }

    /**
     * @param map карта
     * @param x положение на оси абсцисс
     * @param y положение на оси ординат
     * @param number количество посещений данной позицции
     * @return достигнуто ли максимальное количество посещений
     */
    private boolean reachesMaxNumberOfNeighbors(char[][] map, int x, int y, int number){
        int maxNumber = 0;
        for (int i=-1; i <= 1; i++)
            for (int j=-1; j <= 1; j++)
                if (Math.abs(i+j) == 1 && isMoveCorrect(map, x + i, y + j)) maxNumber++;
        return maxNumber == number;
    }
}
