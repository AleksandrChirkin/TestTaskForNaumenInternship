/*import java.time.Duration;
import java.time.Instant;
import java.util.Random;*/

public class Program {
    private static final char[][] testOne = new char[][]{
            new char[]{'.', '.', '.', '@', '.'},
            new char[]{'.', '#', '#', '#', '#'},
            new char[]{'.', '.', '.', '.', '.'},
            new char[]{'#', '#', '#', '#', '.'},
            new char[]{'.', 'X', '.', '.', '.'}
    };

    private static final char[][] testTwo = new char[][]{
            new char[]{'.', '.', 'X', '.', '.'},
            new char[]{'#', '#', '#', '#', '#'},
            new char[]{'.', '.', '.', '.', '.'},
            new char[]{'.', '@', '.', '.', '.'},
            new char[]{'.', '.', '.', '.', '.'}
    };

    private static final char[][] testThree = new char[][]{
            new char[]{'.', '.', '.', '.', '@'},
            new char[]{'#', '.', '#', '#', '#'},
            new char[]{'.', '.', '.', '.', '.'},
            new char[]{'.', '.', '.', '.', 'X'},
            new char[]{'.', '.', '.', '.', '.'}
    };

    public static void main(String[] args){
        Router router = new Router();
        for (char[] line: router.findRoute(testOne))
            System.out.println(line);
        System.out.println();
        System.out.println(router.findRoute(testTwo));
        System.out.println();
        for (char[] line: router.findRoute(testThree))
            System.out.println(line);
        /*char[][] bigTest = new char[10000][];
        for (var i=0; i < bigTest.length; i++) {
            bigTest[i] = new char[bigTest.length];
            for (var j=0; j < bigTest.length; j++)
                bigTest[i][j] = '.';
        }
        Random rnd = new Random();
        bigTest[rnd.nextInt(10000)][rnd.nextInt(10000)] = '@';
        bigTest[rnd.nextInt(10000)][rnd.nextInt(10000)] = 'X';
        Instant start = Instant.now();
        router.findRoute(bigTest);
        Instant finish = Instant.now();
        long length = Duration.between(start, finish).toMillis();
        System.out.printf("%d ms for long test", length);*/
    }
}
