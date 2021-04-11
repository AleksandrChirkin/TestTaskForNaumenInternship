import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Router implements RouteFinder{
    @Override
    public char[][] findRoute(char[][] map){
        LinkedBlockingQueue<Position> queue = new LinkedBlockingQueue<>();
        ArrayList<Position> visited = new ArrayList<>();
        Position start = getPosition(map, '@');
        queue.add(start);
        Position end = getPosition(map, 'X');
        while (queue.size() > 0){
            Position currentPosition;
            try {
                currentPosition = queue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            visited.add(currentPosition);
            int currX = currentPosition.getX();
            int currY = currentPosition.getY();
            for (int i=-1; i<=1; i++)
                for (int j=-1; j<=1; j++) {
                    int newX = currX + i;
                    int newY = currY + j;
                    if (Math.abs(i+j) == 1 && isMoveCorrect(map, newX, newY) &&
                            !visited.contains(new Position(newX, newY, null)) &&
                            !queue.contains(new Position(newX, newY, null))) {
                        if (newX == end.getX() && newY == end.getY())
                            return getMapWithPath(map, start, currentPosition);
                        queue.add(new Position(newX, newY, currentPosition));
                    }
                }
        }
        return null;
    }

    private Position getPosition(char[][] map, char symbol){
        for (int i=0; i < map.length; i++)
            for (int j=0; j < map[i].length; j++)
                if (map[i][j] == symbol)
                    return new Position(j, i, null);
        throw new IllegalArgumentException("No such position!");
    }

    private boolean isMoveCorrect(char[][] map, int x, int y) {
        return y >= 0 && y < map.length && x >= 0 && x < map[y].length && map[y][x] != '#';
    }

    private char[][] getMapWithPath(char[][] map, Position beginPos, Position currentPosition){
        while (!currentPosition.equals(beginPos)){
            map[currentPosition.getY()][currentPosition.getX()] = '+';
            currentPosition = currentPosition.getPreviousPosition();
        }
        return map;
    }
}
