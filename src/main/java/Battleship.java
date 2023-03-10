import com.sun.org.apache.xpath.internal.objects.XNumber;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Battleship {
    public static Scanner sc = new Scanner(System.in);
    private static final int MAX_SHIPS_ARMADA = 20;
    private static final boolean TEST = false;

    public static void main(String[] args) throws InterruptedException {

        String[][] player1Map = new String[BattleShipGame.SIZE_BOARD][BattleShipGame.SIZE_BOARD];
        String[][] player2Map = new String[BattleShipGame.SIZE_BOARD][BattleShipGame.SIZE_BOARD];

        for (int i = 0; i < player1Map.length; i++) {
            for (int j = 0; j < player1Map.length; j++) {
                player1Map[i][j] = "⬜";
                player2Map[i][j] = "⬜";
            }
        }

        if (TEST){
            DispositionShips.fillDefaultMap1(player1Map);
            DispositionShips.fillDefaultMap2(player2Map);

        } else {
            DispositionShips.shipTable(player1Map, 1);
            for (int i=0; i<40; i++) {
                System.out.println();
            }
            DispositionShips.shipTable(player2Map, 2);
            for (int i=0; i<40; i++) {
                System.out.println();
            }
        }

        BattleShipGame player1 = new BattleShipGame(player1Map);
        BattleShipGame player2 = new BattleShipGame(player2Map);

        System.out.println("Вы начали играть в игру: " +  "▂ ▃ ▅ ▆ █ Морской бой █ ▆ ▅ ▃ ▂");
        System.out.println();

        if (TEST) {
            player1.showMyMap();
            System.out.println();
            player2.showMyMap();
        }

        System.out.println();
        System.out.println("Введите случайное число - для опредения вероятности: кто будет ходить первым - " + "$$$$_" + "\uD83E\uDD11" + "_$$$$");

        String input = sc.nextLine()
                .replace(",", "")
                .replace(".", "")
                .replaceAll("\\D", "");
        int seed = 0;

        try {
            seed = Integer.parseInt(input);
        } catch (NumberFormatException e){
            System.out.println("Вы ввели неправильный ввод. Игра автоматически выберет случайное число");
            seed = (int) (new Date().getTime() % 44123);
        }
        Random random = new Random(seed);
        boolean currentMovePlayer1 = random.nextInt(2) == 1;


        while(player1.getCountDamagedShip() < MAX_SHIPS_ARMADA && player2.getCountDamagedShip() < MAX_SHIPS_ARMADA) {
            int number = currentMovePlayer1 ? 1 : 2;
            int numberAttack = currentMovePlayer1 ? 2 : 1;
            BattleShipGame wasAttacked = currentMovePlayer1 ? player2 : player1;
            BattleShipGame whoAttack = currentMovePlayer1 ? player1 : player2;

            int x = 0;
            int y = 0;

            System.out.println();
            System.out.println("◀ █ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ ▯▮ █ ▶");
            System.out.println("Карта игрока " + numberAttack);
            System.out.println("Loading… ███████████ 100%");

            whoAttack.showEnemyMap();
            if (TEST) {
                wasAttacked.showMyMap();
            }

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

            if (wasAttacked.checkStrike(x, y)) {
                System.out.println("Вы попали в корабль");
                whoAttack.markRedEnemyMap(x, y);
                wasAttacked.markRedMyMap(x, y);

                System.out.println();

                wasAttacked.hitOrDestroyed(x, y);
                wasAttacked.increaseCountDamagedShips();
            } else {
                currentMovePlayer1 = currentMovePlayer1 ? false : true;
                System.out.println("Вы промахнулись");

                whoAttack.writeDownMiss(x, y);

            }

            Thread.sleep(2000); // pause between moves
        }

        if (player2.getCountDamagedShip() == MAX_SHIPS_ARMADA){
            System.out.println("Победил первый игрок");
        } else if (player1.getCountDamagedShip() == MAX_SHIPS_ARMADA){
            System.out.println("Победил второй игрок");
        }

        sc.close();

    }


}