package $nye.hu.progTech;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        size=7;
        for (int i = -1; i <= size; i++) {
            for (int j = -1; j <= size; j++) {
                char borderChar = 'W';

                if (i >= 0 && i < size && j >= 0 && j < size) {
                    borderChar = loadedWorld[i][j];
                }

                System.out.print(borderChar + " ");
            }
            System.out.println();

        }
    }
}
