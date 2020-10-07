package HomeTask3;

import java.util.Random;
import java.util.Scanner;
class Main{
    public static int SIZE = 3;
    public static int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = ' ';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        boolean Sym = chooseYourSymbol();
        char SHuman;
        char SAi;
        if(Sym)
        {
            SHuman = DOT_X;
            SAi = DOT_O;
        }
        else
        {
            SHuman = DOT_O;
            SAi = DOT_X;
        }
        initMap();
        printMap();
        while (true) {
            humanTurn(Sym);
            printMap();
            if (checkWin(SHuman)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn(!Sym);
            printMap();
            if (checkWin(SAi)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    // static booleam checkWin

        static boolean checkWin(char symb) {
        if (map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
        if (map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
        if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
        if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
        if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
        if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;
        return false;
    }

        static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

        static void aiTurn(boolean SymAi) {
        int x, y;
        char Symbol;
        if(SymAi)
        {
            Symbol = 'Х';
        }
        else
            Symbol = '0';
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = Symbol;
    }

        static void humanTurn(boolean SymHum) {
        int x;
        int y;
        char Symbol;
            if(SymHum)
            {
                Symbol = 'Х';
            }
            else
                Symbol = '0';
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)
        map[y][x] = Symbol;
    }

        static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

        static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

        static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean chooseYourSymbol() {

        System.out.println("Крестики или нолики? Введите 1 - для игры крестиками, 0 - для игры ноликами");
        Scanner scan = new Scanner(System.in);
        int xOr0;
            xOr0 = scan.nextInt();

                if(xOr0 == 1)
                {
                    return true;
                }
                return false;

}

}