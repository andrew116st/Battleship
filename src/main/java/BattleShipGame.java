public class BattleShipGame {
    public static final int SIZE_BOARD = 10;

    String[][] myShips;       // карта, где будут расставлены мои корабли
    String[][] enemyMap;      // карта, где я буду отмечать куда я стрелял у противника.
    int countDamagedShip;

    public BattleShipGame(String[][] myShips){
        this.myShips = myShips;
        this.countDamagedShip = 0;
        this.enemyMap = new String[BattleShipGame.SIZE_BOARD][BattleShipGame.SIZE_BOARD];

        for (int i = 0; i < SIZE_BOARD; i++) {
            for (int j = 0; j < SIZE_BOARD; j++) {

                enemyMap[i][j] = "⬜";
            }
        }

    }

    public int getCountDamagedShip() {
        return countDamagedShip;
    }


    public void showEnemyMap(){
        Utils.printMap(enemyMap);
    }

    public void showMyMap(){
        Utils.printMap(myShips);
    }

    public boolean checkStrike(int x, int y) { //true - попал, а false - промах
        if (myShips[x][y].equals("\uD83D\uDEA2")) {
            return true;
        } else {
            return false;
        }

    }

    public void markRedEnemyMap(int x, int y) {
        enemyMap[x][y] = "\uD83D\uDFE5";
    }

    public void markRedMyMap(int x, int y) {
        myShips[x][y] = "\uD83D\uDFE5";
    }

    public void hitOrDestroyed(int x ,int y) {
        if (Checks.completeDestructionShip(x, y, x, y, myShips)) {
            System.out.println("Утопил");
        } else {
            System.out.println("Ранил");
        }
    }

    public void increaseCountDamagedShips(){
        countDamagedShip++;
    }

    public void writeDownMiss(int x, int y) {
        if (enemyMap[x][y].equals("\uD83D\uDEA2")) {
            enemyMap[x][y] = "●";
        }
    }

}
