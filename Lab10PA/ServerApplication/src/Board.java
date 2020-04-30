import java.util.Arrays;

public class Board {
    int[][] boardMatrix = new int[10][10];

    public Board() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boardMatrix[i][j] = 0;
            }
        }
    }

    public int move(int i, int j, int type) {
        if (boardMatrix[i][j] == 1) {
            return 0;
        } else {
            boardMatrix[i][j] = type;
            return 1;
        }

    }

    public String viewMarix() {
        String result = "";
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // System.out.println(boardMatrix[i][j]);
                result = result + " " + boardMatrix[i][j] + " ";
            }
            result = result + "\n";
        }
        System.out.println(result);
        //return  Arrays.toString(boardMatrix);
        return result;
    }
/*
    @Override
    public String toString() {
        String result = new String();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                result = boardMatrix[i][j] + " ";
            }
            result = result + "\n";
        }
        System.out.println(result);
        //return  Arrays.toString(boardMatrix);
        return result;
    }

 */
}
