public class Checks {
    private static final int STEP = 1;
    private static final int SIZE_MIN = 0;
    private static final int SIZE_MAX = 9;

    public static boolean controlOpportunityParsing(String line){
        String[] coords = line.split(";");

        for (int m = 0; m < coords.length; m++) {
            String coordFirst = coords[m];
            try {
                int x = Utils.parseX(coordFirst);
            } catch (NumberFormatException e) {
                System.out.println("ОШИБКА - при вводе координат корабля");
                return false;
            }
            int y = Utils.parseY(coordFirst);
            if (y < 0 || y > 9) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkCellShip (String line, String[][] map){

        String[] coords = line.split(";");

        for (int m = 0; m < coords.length; m++) {
            String coordFirst = coords[m];

            int x = Utils.parseX(coordFirst);
            int y = Utils.parseY(coordFirst);

            if (map[x][y].equals("\uD83D\uDEA2")){
                System.out.println("Ячейка  - уже занята кораблем !!!");
                return false;
            }
        }
        return true;

    }


    private static boolean buildCellShip (String line, String[][] map) {
        int x = 0;
        int y = 0;

        String[] coords = line.split(";");

        for (int i = 0; i < coords.length; i++) {
            String coord = coords[i];


            x = Utils.parseX(coord);
            y = Utils.parseY(coord);

            coord = coord.toLowerCase();

            for (int newX = x - 1; newX < x + 2; newX++) {                       // проверка выхода за игровое поле
                if (newX < 0 || newX > 9) {
                    continue;
                }

                for (int newY = y - 1; newY < y + 2; newY++) {               // проверка выхода за игровое поле
                    if (newY < 0 || newY > 9) {
                        continue;
                    }

                    if (map[newX][newY].equals("\uD83D\uDEA2")) {              // Есть сосед
                        System.out.println("▓█▓█▓█▓" + " ◀  Ячейка - граница соседнего корабля !!! ▶ " +"▓█▓█▓█▓");
                        System.out.println();
                        return false;
                    }
                }
            }
        }
        return true;
    }




    public static boolean checkSizeShip (String line, int sizeShip){
        String[] coords = line.split(";");

        if (coords.length == sizeShip){
            System.out.println("Вы ввели координаты в правильном формате! " + "❤❤"+"❤❤");
            System.out.println();
            return true;
        }else{
            System.out.println("ОШИБКА - Введите координаты заново! " + "✖✖✖✖");
            System.out.println();
            return false;
        }

    }


    public static boolean checkCoordinates(String line, String[][] map, int sizeShip) {
        boolean resultOk = true;

        resultOk = resultOk && controlOpportunityParsing(line);   // проверка - при вводе координат корабля (не выходили за пределы игрового поля)
        resultOk = resultOk && checkSizeShip(line, sizeShip);     // правильность координат: разделитель - ";"
        resultOk = resultOk && checkShipCoordinatesALL(line);   // проверка что координаты для конкретного корабля - введены корректно (горизонталь - вертикаль)
        resultOk = resultOk && checkCellShip(line, map);          // проверка что введенная ячейка  - не занята кораблем
        resultOk = resultOk && buildCellShip(line, map);          // проверка что расставленные корабли - не соприкасаются границами

        return resultOk;
    }


    public static boolean resultLineStepCharSubtract(String[] coords, int index) {

        if (coords.length == 1) {
            return true;
        }

        boolean result = true;

        char prevValue = coords[0].charAt(index);

        for (int i = 1; i < coords.length; i++) {
            char currentValue = coords[i].charAt(index);
            result = result && (Math.abs(currentValue - prevValue) == STEP);
            prevValue = currentValue;
        }

        return result;

    }

    public static boolean resultLineStepCharEqual(String[] coords, int index) {
        if (coords.length == 1) {
            return true;
        }

        boolean result = true;
        char prevValue = coords[0].charAt(index);

        for (int i = 1; i < coords.length; i++) {
            char currentValue = coords[i].charAt(index);
            result = result && (currentValue == prevValue);
            // prevValue = currentValue;
        }

        return result;

    }


    public static boolean checkShipCoordinatesALL(String line) {
        line = line.replace(",", "").replace(".", "").toLowerCase();
        String[] coords = line.split(";");

        boolean byHorizontal = resultLineStepCharSubtract(coords, 0) && resultLineStepCharEqual(coords, 1); // по вертикали должно идти, цифры разные, буквы одинаковые
        boolean byVertical = resultLineStepCharEqual(coords, 0) && resultLineStepCharSubtract(coords, 1); // по вертикали должно идти, буквы разные, цифры одинаковые
        if (!byHorizontal && !byVertical) {
            System.out.println("ОШИБКА - координаты для конкретного корабля - введены некорректно: НЕПОСЛЕДОВАТЕЛЬНЫЕ КООРДИНАТЫ");
            return false;
        }

        return true;
    }

    public static boolean completeDestructionShip(int xToCheck, int yToCheck, int xToIgnore, int yToIgnore, String[][] map){
        boolean result = true; //false - только подбил, true - утопил полностью

        for (int newX = xToCheck - 1; newX < xToCheck + 2; newX++) {                       // проверка выхода за игровое поле
            if (newX < SIZE_MIN || newX > SIZE_MAX) {
                continue;
            }

            for (int newY = yToCheck - 1; newY < yToCheck + 2; newY++) {               // проверка выхода за игровое поле
                if (newY < SIZE_MIN || newY > SIZE_MAX) {
                    continue;
                }

                if ((newX == xToCheck) && (newY == yToCheck)) { //мы не проверяем сами себя (туда куда пришелся удар)
                    continue;
                }

                if (!((newX == xToIgnore) && (newY == yToIgnore))) { //проверяем только если это не те координаты, которые мы должны игнорить. И используем только координаты в рамках поля
                    String valueOfCell = map[newX][newY]; //либо корабль, либо красный квадрат, либо синий квадрат
                    if (valueOfCell.equals("\uD83D\uDEA2")) {//unicode корабля
                        result = result && false;
                    } else if (valueOfCell.equals("\uD83D\uDFE5")) {//unicode красного квадрата
                        result = result && completeDestructionShip(newX, newY, xToCheck, yToCheck, map); // если вокруг синие квадраты, то она вернет true
                    } else if (valueOfCell.equals("⬜")) {
                        result = result && true;
                    }
                }

            }

        }

        return result;

    }

}
