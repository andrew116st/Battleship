import com.sun.org.apache.xpath.internal.objects.XNumber;

import java.util.Scanner;

public class Battleship {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String[][] player1 = new String[10][10];
        String[][] player2 = new String[10][10];

        for (int i = 0; i < player1.length; i++) {
            for (int j = 0; j < player1.length; j++) {
                player1[i][j] = "⬜";
            }
        }

        for (int i = 0; i < player2.length; i++) {
            for (int j = 0; j < player2.length; j++) {
                player2[i][j] = "⬜";
            }
        }

        System.out.println("Вы начали играть в игру - Морской бой");

        shipTable(player1, 1);
        shipTable(player2,2);

        while(true) {

            System.out.println("Введите координаты для атаки игрок_1 - x,y");
            String line = sc.nextLine(); //x,y;

            int x = parseX(line);
            int y = parseY(line);


            if (player2[x][y].equals("\uD83D\uDEA2")) {
                System.out.println("Вы попали в корабль");
                player2[x][y] = "\uD83D\uDFE5";

            } else {
                System.out.println("Вы промахнулись");
            }

            System.out.println("Введите координаты для атаки игрок_2 - x,y");
            line = sc.nextLine(); //x,y;

            x = parseX(line);
            y = parseY(line);

            if (player1[x][y].equals("\uD83D\uDEA2")) {
                System.out.println("Вы попали в корабль");
                player1[x][y] = "\uD83D\uDFE5";

            } else {
                System.out.println("Вы промахнулись");
            }

        }

    }

    public static int parseX (String line) {

        char temp1 = line.charAt(0);
        String temp2 = String.valueOf(temp1);
        return Integer.parseInt(temp2);

    }

    public static int parseY (String line) {

        char temp1 = line.charAt(2);
        String temp2 = String.valueOf(temp1);
        return Integer.parseInt(temp2);

    }

    public static void positionShips(String[][] player, String format) {
        System.out.println("Введите координаты кораблей. формат: " + format); // 1 штука

        String line = sc.nextLine(); //x,y;x,y;x,y;x,y
        String[] coords = line.split(";");

        for (int m = 0; m < coords.length; m++) {
            String coordFirst = coords[m];

            int x = parseX(coordFirst);
            int y = parseY(coordFirst);

            player[x][y] = "\uD83D\uDEA2";
        }

    }

    public static void shipTable(String[][] player, int number){

        System.out.println();
        System.out.println("Необходимо раставить корабли - Игрок_" + number);

        positionShips(player, "x,y;x,y;x,y;x,y");

        for (int i =0; i<2; i++) {
            positionShips(player, "x,y;x,y;x,y");
        }

        for (int i = 0; i < 3; i++) {
            positionShips(player, "x,y;x,y");
        }

        for (int i = 0; i < 4; i++) {
            positionShips(player, "x,y");
        }

        printMap(player);


    }


    public static void printMap(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            System.out.println();
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + " ");
            }
        }
        System.out.println();
    }


}