import java.util.Scanner;

public class Battleship {
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

        Scanner sc = new Scanner(System.in);
        System.out.println("Вы начали играть в игру - Морской бой");
        System.out.println("Необходимо раставить корабли - Игрок_1");
        System.out.println("Введите координаты кораблей. формат: x,y;x,y;x,y;x,y "); // 1 штука

        String line = sc.nextLine(); //x,y;x,y;x,y;x,y
        String[] coords = line.split(";");


        for (int m = 0; m < coords.length; m++) {
            String coordFirst = coords[m];
            int x, y;
            char temp1 = coordFirst.charAt(0);
            String temp2 = String.valueOf(temp1);
            x = Integer.parseInt(temp2);

            char temp3 = coordFirst.charAt(2);
            String temp4 = String.valueOf(temp3);
            y = Integer.parseInt(temp4);

            player1[x][y] = "\uD83D\uDEA2";
        }
        for (int i =0; i<2; i++) {

            System.out.println("Введите координаты корабля. формат: x,y;x,y;x,y"); //2 штуки
            line = sc.nextLine(); //x,y;x,y;x,y;
            coords = line.split(";");

            for (int m = 0; m < coords.length; m++) {
                String coordFirst = coords[m];
                int x, y;
                char temp1 = coordFirst.charAt(0);
                String temp2 = String.valueOf(temp1);
                x = Integer.parseInt(temp2);

                char temp3 = coordFirst.charAt(2);
                String temp4 = String.valueOf(temp3);
                y = Integer.parseInt(temp4);

                player1[x][y] = "\uD83D\uDEA2";

            }
        }

        for (int i =0; i<3; i++) {

            System.out.println("Введите координаты корабля. формат: x,y;x,y"); //3 штуки
            line = sc.nextLine(); //x,y;x,y
            coords = line.split(";");

            for (int m = 0; m < coords.length; m++) {
                String coordFirst = coords[m];
                int x, y;
                char temp1 = coordFirst.charAt(0);
                String temp2 = String.valueOf(temp1);
                x = Integer.parseInt(temp2);

                char temp3 = coordFirst.charAt(2);
                String temp4 = String.valueOf(temp3);
                y = Integer.parseInt(temp4);

                player1[x][y] = "\uD83D\uDEA2";

            }
        }

        for (int i =0; i<4; i++) {

            System.out.println("Введите координаты корабля. формат: x,y"); //4 штуки
            line = sc.nextLine(); //x,y;
            coords = line.split(";");

            for (int m = 0; m < coords.length; m++) {
                String coordFirst = coords[m];
                int x, y;
                char temp1 = coordFirst.charAt(0);
                String temp2 = String.valueOf(temp1);
                x = Integer.parseInt(temp2);

                char temp3 = coordFirst.charAt(2);
                String temp4 = String.valueOf(temp3);
                y = Integer.parseInt(temp4);

                player1[x][y] = "\uD83D\uDEA2";

            }
        }

        printMap(player1);

    }


    public static void printMap(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            System.out.println();
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + " ");
            }
        }
    }


}