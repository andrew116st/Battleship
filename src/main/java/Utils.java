import java.io.IOException;

public class Utils {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public static int parseX (String line) throws NumberFormatException  {

        char temp1 = line.charAt(0);
        String temp2 = String.valueOf(temp1);
        return Integer.parseInt(temp2);

    }

    public static int parseY (String line) {
        line = line.replace(",", "").replace(".", "").toLowerCase();
        return convertLettertoNumber(line.charAt(1));
    }

    public static void printMap(String[][] map) {

        char c;

        for(c = 'A'; c <= 'J'; ++c)
            System.out.print(ANSI_BLUE +"\t" + c + ANSI_RESET);


        //System.out.print("\t");
        //for (int i = 0; i < map.length; i++) {
        //System.out.print(i + "\t");

        //}

        for (int i = 0; i < map.length; i++) {
            System.out.println();
            System.out.print(ANSI_BLUE + i + "\t" + ANSI_RESET);

            for (int j = 0; j < map.length; j++) {

                System.out.print(map[i][j] + "\t");

            }

            System.out.print(ANSI_BLUE + i + " " + ANSI_RESET);

        }


        System.out.println();
    }

    public static int convertLettertoNumber(char inputChar){
        int number = (int)inputChar - 'a';

        return number;
    }


    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
