import com.sun.org.apache.xpath.internal.objects.XNumber;

import java.util.Scanner;

public class Battleship {
    private static Scanner sc = new Scanner(System.in);
    private static final int MAX_SHIPS_ARMADA = 20;
    private static final boolean TEST = true;

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

        if(TEST){
            fillDefaultMap1(player1);
            fillDefaultMap2(player2);
         } else {
            shipTable(player1, 1);
            shipTable(player2, 2);
        }

        int countDamagedShip1 = 0;
        int countDamagedShip2 = 0;

        boolean currentMovePlayer1 = true;

        while(countDamagedShip2 < MAX_SHIPS_ARMADA && countDamagedShip1 < MAX_SHIPS_ARMADA) {
            int number = currentMovePlayer1 ? 1 : 2;
            String[][] mapToCheck = currentMovePlayer1 ? player2 : player1;

            System.out.println();
            System.out.println("Введите координаты для атаки игрок_" + number  + "- x,y");
            String line = sc.nextLine(); //x,y;

            int x = parseX(line);
            int y = parseY(line);

            if (mapToCheck[x][y].equals("\uD83D\uDEA2")) {
                System.out.println("Вы попали в корабль");
                mapToCheck[x][y] = "\uD83D\uDFE5";

                if (currentMovePlayer1) {
                    countDamagedShip2++;
                } else  {
                    countDamagedShip1++;
                }

            } else {
                currentMovePlayer1 = currentMovePlayer1 ? false : true;
                System.out.println("Вы промахнулись");
            }

        }

        if (countDamagedShip2 == MAX_SHIPS_ARMADA){
            System.out.println("Победил первый игрок");
        } else if (countDamagedShip1 == MAX_SHIPS_ARMADA){
            System.out.println("Победил второй игрок");
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

    public static void fillDefaultMap1(String[][] map) {
        map[0][1] = "\uD83D\uDEA2";
        map[0][2] = "\uD83D\uDEA2";
        map[0][3] = "\uD83D\uDEA2";
        map[0][4] = "\uD83D\uDEA2";

        map[1][2] = "\uD83D\uDEA2";
        map[1][3] = "\uD83D\uDEA2";
        map[1][4] = "\uD83D\uDEA2";

        map[3][1] = "\uD83D\uDEA2";
        map[3][2] = "\uD83D\uDEA2";
        map[3][3] = "\uD83D\uDEA2";

        map[4][4] = "\uD83D\uDEA2";
        map[4][6] = "\uD83D\uDEA2";

        map[5][1] = "\uD83D\uDEA2";
        map[5][2] = "\uD83D\uDEA2";

        map[6][4] = "\uD83D\uDEA2";
        map[6][5] = "\uD83D\uDEA2";

        map[8][1] = "\uD83D\uDEA2";

        map[7][6] = "\uD83D\uDEA2";

        map[9][1] = "\uD83D\uDEA2";

        map[8][6] = "\uD83D\uDEA2";

        printMap(map);
    }

   ///////////////////////////////////////
   public static void fillDefaultMap2(String[][] map) {
       map[9][1] = "\uD83D\uDEA2";
       map[9][2] = "\uD83D\uDEA2";
       map[9][3] = "\uD83D\uDEA2";
       map[9][4] = "\uD83D\uDEA2";

       map[1][6] = "\uD83D\uDEA2";
       map[1][7] = "\uD83D\uDEA2";
       map[1][8] = "\uD83D\uDEA2";

       map[2][1] = "\uD83D\uDEA2";
       map[2][2] = "\uD83D\uDEA2";
       map[2][3] = "\uD83D\uDEA2";

       map[7][5] = "\uD83D\uDEA2";
       map[7][6] = "\uD83D\uDEA2";

       map[3][1] = "\uD83D\uDEA2";
       map[3][2] = "\uD83D\uDEA2";

       map[4][6] = "\uD83D\uDEA2";
       map[4][5] = "\uD83D\uDEA2";

       map[5][5] = "\uD83D\uDEA2";

       map[3][4] = "\uD83D\uDEA2";

       map[7][1] = "\uD83D\uDEA2";

       map[8][2] = "\uD83D\uDEA2";

       printMap(map);


    }

}