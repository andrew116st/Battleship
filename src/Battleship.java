import com.sun.org.apache.xpath.internal.objects.XNumber;

import java.util.Random;
import java.util.Scanner;

public class Battleship {
    private static Scanner sc = new Scanner(System.in);
    private static final int MAX_SHIPS_ARMADA = 20;
    private static final boolean TEST = true;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) throws InterruptedException {

        String[][] player1 = new String[10][10];
        String[][] player2 = new String[10][10];

        String[][] map1 = new String[10][10];
        String[][] map2 = new String[10][10];

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

        for (int i = 0; i < map1.length; i++) {
            for (int j = 0; j < map1.length; j++) {
                map1[i][j] = "⬜";
            }
        }

        for (int i = 0; i < map2.length; i++) {
            for (int j = 0; j < map2.length; j++) {
                map2[i][j] = "⬜";
            }
        }

        System.out.println("Вы начали играть в игру: " +  "▂ ▃ ▅ ▆ █ Морской бой █ ▆ ▅ ▃ ▂");
        System.out.println();

        if(TEST){
            fillDefaultMap1(player1);
            fillDefaultMap2(player2);
         } else {
            shipTable(player1, 1);
            shipTable(player2, 2);
        }

        printMap(player1);
        System.out.println();
        printMap(player2);

        int countDamagedShip1 = 0;
        int countDamagedShip2 = 0;

        System.out.println();
        System.out.println("Введите случайное число - для опредения вероятности: кто будет ходить первым - " + "$$$$_" + "\uD83E\uDD11" + "_$$$$");
        int seed = Integer.parseInt(sc.nextLine());
        final Random random = new Random(seed);
        boolean currentMovePlayer1 = random.nextInt(2) == 1;

        while(countDamagedShip2 < MAX_SHIPS_ARMADA && countDamagedShip1 < MAX_SHIPS_ARMADA) {
            int number = currentMovePlayer1 ? 1 : 2;
            int numberAttack = currentMovePlayer1 ? 2 : 1;

            String[][] mapToCheck = currentMovePlayer1 ? player2 : player1;

            String[][] mapTemp = currentMovePlayer1 ? map2 : map1;

            int x = 0;
            int y = 0;

            System.out.println();
            System.out.println("◀ █ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ █ ▶");
            System.out.println("Карта игрока " + numberAttack);
            System.out.println("Loading… ███████████ 100%");
            printMap(mapTemp);


            while (true) {

                System.out.println();
                System.out.println("Ходит ИГРОК_" + number + " - введите координаты для атаки соперника:" + " x,y");
                String line = sc.nextLine(); //x,y;

                try {
                    x = parseX(line);
                    y = parseY(line);

                    break;

                } catch (NumberFormatException e){
                    System.out.println("Введите координат повторно - Вы ошиблись при вводе");
                }
            }

            if (mapToCheck[x][y].equals("\uD83D\uDEA2")) {
                System.out.println("Вы попали в корабль");
                mapToCheck[x][y] = "\uD83D\uDFE5";

                System.out.println();
                mapTemp[x][y] = "\uD83D\uDFE5";

                if (currentMovePlayer1) {
                    countDamagedShip2++;

                } else  {
                    countDamagedShip1++;
                }

            } else {
                currentMovePlayer1 = currentMovePlayer1 ? false : true;
                System.out.println("Вы промахнулись");

                mapTemp[x][y] = "●";

            }
            Thread.sleep(2000); // pause between moves
        }

        if (countDamagedShip2 == MAX_SHIPS_ARMADA){
            System.out.println("Победил первый игрок");
        } else if (countDamagedShip1 == MAX_SHIPS_ARMADA){
            System.out.println("Победил второй игрок");
        }

        sc.close();

    }

    public static int parseX (String line) throws NumberFormatException  {

        char temp1 = line.charAt(0);
        String temp2 = String.valueOf(temp1);
        return Integer.parseInt(temp2);

    }

    public static int parseY (String line) {
        return convertLettertoNumber(line.charAt(1));
    }

    public static void positionShips(String[][] player, String format, int sizeShip) {
        String line = "";
        while (true) {
            System.out.println("Введите координаты кораблей. формат: " + format); // 1 штука
            line = sc.nextLine(); //x,y;x,y;x,y;x,y


            if (checkCoordinates(line, player, sizeShip)) {
                break;
            }
        }

        String[] coords = line.split(";");

        for (int m = 0; m < coords.length; m++) {
            String coordFirst = coords[m];

            int x = parseX(coordFirst);
            int y = parseY(coordFirst);

            player[x][y] = "\uD83D\uDEA2";
        }

    }

    public static boolean controlOpportunityParsing(String line){
        String[] coords = line.split(";");

        for (int m = 0; m < coords.length; m++) {
            String coordFirst = coords[m];
            try {
                int x = parseX(coordFirst);
                int y = parseY(coordFirst);
            } catch (NumberFormatException e) {
                System.out.println("ОШИБКА - при вводе координат корабля");
                return false;
            }
        }
        return true;
    }

    private static boolean checkCellShip (String line, String[][] map){

        String[] coords = line.split(";");

        for (int m = 0; m < coords.length; m++) {
            String coordFirst = coords[m];

            int x = parseX(coordFirst);
            int y = parseY(coordFirst);

            if (map[x][y].equals("\uD83D\uDEA2")){
                System.out.println("Ячейка  - уже занята кораблем !!!");
                return false;
            }
        }
        return true;

    }

    public static boolean checkSizeShip (String line, int sizeShip){
        String[] coords = line.split(";");

        if (coords.length == sizeShip){
            System.out.println("Вы ввели правильные координаты ! " + "❤❤"+"❤❤");
            System.out.println();
            return true;
        }else{
            System.out.println("ОШИБКА - Введите координаты заново! " + "✖✖✖✖");
            System.out.println();
          return false;
        }

    }


    private static boolean checkCoordinates(String line, String[][] map, int sizeShip) {
        boolean resultOk = true;
        resultOk = resultOk && controlOpportunityParsing(line);
        resultOk = resultOk && checkSizeShip(line, sizeShip);
        resultOk = resultOk && checkCellShip(line, map);


        return resultOk;
    }

    public static void shipTable(String[][] player, int number){

        System.out.println();
        System.out.println("Необходимо раставить корабли - Игрок_" + number);

        positionShips(player, "x,y;x,y;x,y;x,y " + " ■|■|■|■", 4);

        for (int i = 0; i < 2; i++) {
            positionShips(player, "x,y;x,y;x,y " + " ■|■|■",3);
        }

        for (int i = 0; i < 3; i++) {
            positionShips(player, "x,y;x,y " + " ■|■",2);
        }

        for (int i = 0; i < 4; i++) {
            positionShips(player, "x,y " + " ■", 1);
        }

        //printMap(player);


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


        public static String convertNumbertoLetter(int input){

            char letterChar = (char)(input + (int)'a');
            String letterString = String.valueOf(letterChar);

            return letterString ;
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



    }

}