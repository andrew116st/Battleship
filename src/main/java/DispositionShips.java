import java.util.Scanner;

public class DispositionShips {


    public static void positionShips(String[][] player, String format, int sizeShip) {
        String line = "";
        while (true) {
            System.out.println("Введите координаты кораблей. формат: " + format); // 1 штука
            line = Battleship.sc.nextLine(); //x,y;x,y;x,y;x,y
            if (Checks.checkCoordinates(line, player, sizeShip)) {
                break;
            }
        }

        String[] coords = line.split(";");

        for (int m = 0; m < coords.length; m++) {
            String coordFirst = coords[m];

            int x = Utils.parseX(coordFirst);
            int y = Utils.parseY(coordFirst);

            player[x][y] = "\uD83D\uDEA2";
        }

    }

    public static void shipTable(String[][] player, int number){

        System.out.println();
        System.out.println("Необходимо раставить корабли - Игрок_" + number);

        positionShips(player, "5a;5b;5c;5d " + " ■|■|■|■", 4);
        Utils.printMap(player);

        for (int i = 0; i < 2; i++) {
            positionShips(player, "5a;5b;5c " + " ■|■|■",3);
            Utils.printMap(player);
        }

        for (int i = 0; i < 3; i++) {
            positionShips(player, "5c;5d " + " ■|■",2);
            Utils.printMap(player);
        }

        for (int i = 0; i < 4; i++) {
            positionShips(player, "5d" + " ■", 1);
            Utils.printMap(player);
        }

        //printMap(player);


    }

    public static void fillDefaultMap1(String[][] map) {
        map[0][1] = "\uD83D\uDEA2";
        map[0][2] = "\uD83D\uDEA2";
        map[0][3] = "\uD83D\uDEA2";
        map[0][4] = "\uD83D\uDEA2";

        map[1][7] = "\uD83D\uDEA2";
        map[1][8] = "\uD83D\uDEA2";
        map[1][9] = "\uD83D\uDEA2";

        map[3][1] = "\uD83D\uDEA2";
        map[3][2] = "\uD83D\uDEA2";
        map[3][3] = "\uD83D\uDEA2";

        map[4][5] = "\uD83D\uDEA2";
        map[4][6] = "\uD83D\uDEA2";

        map[5][1] = "\uD83D\uDEA2";
        map[5][2] = "\uD83D\uDEA2";

        map[6][4] = "\uD83D\uDEA2";
        map[6][5] = "\uD83D\uDEA2";

        map[8][3] = "\uD83D\uDEA2";

        map[7][9] = "\uD83D\uDEA2";

        map[9][1] = "\uD83D\uDEA2";

        map[8][6] = "\uD83D\uDEA2";



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

        map[4][1] = "\uD83D\uDEA2";
        map[4][2] = "\uD83D\uDEA2";

        map[4][8] = "\uD83D\uDEA2";
        map[4][9] = "\uD83D\uDEA2";

        map[5][5] = "\uD83D\uDEA2";

        map[3][5] = "\uD83D\uDEA2";

        map[7][1] = "\uD83D\uDEA2";

        map[8][9] = "\uD83D\uDEA2";



    }


}
