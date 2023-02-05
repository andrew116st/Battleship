import com.sun.org.apache.xpath.internal.objects.XNumber;

import java.util.Random;
import java.util.Scanner;

public class Battleship {
    public static Scanner sc = new Scanner(System.in);
    private static final int MAX_SHIPS_ARMADA = 20;

    private static final boolean TEST = false;
    private static final int SIZE_BOARD = 10;

    public static void main(String[] args) throws InterruptedException {

        String[][] player1 = new String[SIZE_BOARD][SIZE_BOARD];
        String[][] player2 = new String[SIZE_BOARD][SIZE_BOARD];

        String[][] map1 = new String[SIZE_BOARD][SIZE_BOARD];
        String[][] map2 = new String[SIZE_BOARD][SIZE_BOARD];

        for (int i = 0; i < player1.length; i++) {
            for (int j = 0; j < player1.length; j++) {
                player1[i][j] = "⬜";
                player2[i][j] = "⬜";
                map1[i][j] = "⬜";
                map2[i][j] = "⬜";
            }
        }

        System.out.println("Вы начали играть в игру: " +  "▂ ▃ ▅ ▆ █ Морской бой █ ▆ ▅ ▃ ▂");
        System.out.println();

        if(TEST){
            DispositionShips.fillDefaultMap1(player1);
            DispositionShips.fillDefaultMap2(player2);
         } else {
            DispositionShips.shipTable(player1, 1);
            DispositionShips.shipTable(player2, 2);
        }

        Utils.printMap(player1);
        System.out.println();
        Utils.printMap(player2);

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
            Utils.printMap(mapTemp);
            Utils.printMap(mapToCheck);



            while (true) {

                System.out.println();
                System.out.println("Ходит ИГРОК_" + number + " - введите координаты для атаки соперника:" + " 7f");
                String line = sc.nextLine(); //x,y;

                try {
                    x = Utils.parseX(line);
                    y = Utils.parseY(line);

                    if (y >= 0 && y <= 9) {
                        break;
                    }

                } catch (NumberFormatException e){

                }
                System.out.println("Введите координат повторно - Вы ошиблись при вводе");
            }

            if (mapToCheck[x][y].equals("\uD83D\uDEA2")) {
                System.out.println("Вы попали в корабль");
                mapToCheck[x][y] = "\uD83D\uDFE5";

                System.out.println();
                mapTemp[x][y] = "\uD83D\uDFE5";

                if (Checks.completeDestructionShip(x, y, x, y, mapToCheck)) {
                    System.out.println("Утопил");
                } else {
                    System.out.println("Ранил");
                }

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


}