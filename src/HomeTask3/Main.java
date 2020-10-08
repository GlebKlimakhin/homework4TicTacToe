package HomeTask3;

import com.sun.glass.ui.Size;

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
            humanTurn(SHuman);
            printMap();
            if (checkWin(SHuman)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn(SAi);
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
        if(checkWinDiagonals(symb) || checkWinHorAndVert(symb))
        {
            return true;
        }
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

        static void aiTurn(char SymAi) {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = SymAi;
    }

        static void humanTurn(char SymHum) {
        int x;
        int y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)
        map[y][x] = SymHum;
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
    static boolean checkWinDiagonals(char Sym)
    {
        int catcher = 0;
        int counter = 0;
        for(int i=0; i< map.length;i++) {
            if (map[i][i] == Sym) {
                catcher++;
            }
            if (map[map.length - i - 1][i] == Sym) {
                counter++;
            }
        }
        if(catcher == SIZE)
        {
            return true;
        }
        else if (counter == SIZE) {
            return true;
        }
            else
        {
            return false;
        }

    }
    static boolean checkWinHorAndVert(char Sym)
    {
        int catcher;
        int counter;
        for (int i=0;i<map.length;i++)
        {
            catcher = 0;
            counter = 0;
            for (int j=0;j< map.length;j++)
            {
                if(map[i][j] == Sym) {
                    catcher++;
                    if (catcher == SIZE) {
                        return true;
                    }
                }
                if(map[j][i] == Sym)
                {
                    counter++;
                    if(counter == SIZE)
                    {
                        return true;
                    }
                }

            }
        }
        return false;
    }
    /*
    static void AITurn() {
int catcher = 0;
int s = 0;
int x=0;
int y=0;
// do
//
//по строкам поиск 2 в ряд
for (int i = 0; i < map.length; i++) {
for (int j = 0; j < map.length; j++) {
if (map[i][j] != 0) {
s = s + map[i][j];
y = y + j;
}
}
if (s > 48 * 2)//48 - значение 0 в ascii, значение х - 88, 48*2>88, значит если ИИ найдёт один Х, этот if не сработает
{
if (y == 1)
map[i][2] = '1';
else if (y == 2)
map[i][1] = '1';
else
map[i][0] = '1';
catcher++;
s = 0;
x = 0;
y = 0;
}
}
//по столбцам поиск 2 в ряд
for (int j = 0; j < map.length; j++) {
for (int i = 0; i < map.length; i++) {
if (map[i][j] != 0) {
s = s + map[j][i];
x = x + i;
}}
if (s > 48 * 2)//48 - значение 0 в ascii, значение х - 88, 48*2>88, значит если ИИ найдёт один Х, этот if не сработает
{
if (x == 1)
map[j][2] = '1';
else if (x == 2)
map[j][1] = '1';
else
map[j][0] = '1';
catcher++;
s = 0;
x = 0;
y = 0;
}
}
//по главной диагонали
for (int i = 0; i < map.length; i++) {
for (int j = 0; j < map.length; j++) {
if (i == j) {
s = s + map[i][j];
x = x + i;
y = y + j;
}

}

}
int SumXY = x + y;
if (s > 48 * 2) {
catcher++;
if (SumXY == 6) {
map[0][0] = '1';
}
if (SumXY == 4) {
map[1][1] = '1';
}
if (SumXY == 2) {
map[2][2] = '1';
}
}
s = 0;
for (int i = 0; i < map.length; i++) {
for (int j = 0; j < map.length; j++) {
if (i + j == SIZE - 1) {
s = s + map[i][j];
x = x + i;
}
}


}
if (s > 48 * 2) {
catcher++;
if (x == 3) {
map[0][2] = '1';
}
if (x == 2) {
map[1][1] = '1';
}
if (x == 1) {
map[2][0] = '1';
}
}
if(catcher == 0){
int i=0;
int j=0;
while(!isCellValid(i,j))
{
i=(int)(Math.random()*(SIZE-1));
j=(int)(Math.random()*(SIZE-1));

map[i][j]= '1';
}
}
}
(единички - условные ходы ИИ)
     */
}