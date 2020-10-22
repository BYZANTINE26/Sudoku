package suduko_generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Generator {
    private static int[][] array = new int[9][9];

    private static int getRandom(ArrayList<Integer> arrayList) {
        if (arrayList.size() == 0){
            return arrayList.get(0);
        } else {
            int randomIndex = new Random().nextInt(arrayList.size());
            return arrayList.get(randomIndex);
        }
    }

    private static void fillCube(int p){
        ArrayList<Integer> tempList = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        for(int i = p ; i < p + 3 ; i++){
            for(int j = p ; j < p + 3 ; j++){
                int n = getRandom(tempList);
                array[i][j] = n;
                tempList.remove(Integer.valueOf(n));
            }
        }
    }

    private static void fillDiagonal(){
        for(int i = 0 ; i < 3 ; i++){
            fillCube(3*i);
        }
    }

    private static ArrayList<Integer> checkRow(int index , ArrayList<Integer> list){
        for(int j = 0 ; j < 9 ; j++){
            if (array[index][j] != 0){
                list.remove(Integer.valueOf(array[index][j]));
            }
        }
        return list;
    }

    private static ArrayList<Integer> checkColumn(int index , ArrayList<Integer> list){
        for(int i = 0 ; i < 0 ; i++){
            if (array[i][index] != 0){
                list.remove(Integer.valueOf(array[i][index]));
            }
        }
        return list;
    }

    private static ArrayList<Integer> checkCube(int r , int c , ArrayList<Integer> list){
        r = r / 3;
        c = c / 3;
        for(int a = 3*r ; a < (3*r)+3 ; a++){
            for(int b = 3*c ; b < (3*c)+3 ; b++){
                if (array[a][b] != 0){
                    list.remove(Integer.valueOf(array[a][b]));
                }
            }
        }
        return list;
    }

    private static void fillOthers(){
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 9 ; j++){
                if (array[i][j] == 0){
                    ArrayList<Integer> tempList = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
                    tempList = checkRow(i , tempList);
                    tempList = checkColumn(j , tempList);
                    tempList = checkCube(i , j , tempList);
                    int n = getRandom(tempList);
                    array[i][j] = n;
                }
            }
        }
    }

    public static void main(String[] args){
        print2D(array);
        fillDiagonal();
        fillOthers();
        print2D(array);
    }

    private static void print2D(int mat[][]){
        for (int i = 0; i < mat.length; i++){
            for (int j = 0; j < mat[i].length; j++){
                System.out.print(mat[i][j] + " ");
            }
        }
    }
}
