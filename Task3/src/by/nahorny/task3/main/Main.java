package by.nahorny.task3.main;

import java.util.Scanner;

/**
 * Created by Dmitri_Nahorny on 2/16/2017.
 */
public class Main {
    public static void main (String[] args) {
        Scanner customScanner = new Scanner(System.in);
        System.out.println("Please enter the matrix order:");
        String userInput = customScanner.nextLine();
        try {
            int customMatrixOrder = Integer.parseInt(userInput);
            int[] oddRow = new int[customMatrixOrder];
            int[] evenRow = new int[customMatrixOrder];
            int[][] customMatrix = new int[customMatrixOrder][];
            //Creating odd and even row with according size
            for(int i = 0; i<customMatrixOrder; i++){
                oddRow[i] = i + 1;
                evenRow[i] = customMatrixOrder - i;
            }
            //Adding odd and even rows to the matrix
            for(int j = 0; j<customMatrixOrder; j +=2){
                customMatrix[j] = oddRow;
            }
            for(int j = 1; j<customMatrixOrder; j +=2){
                customMatrix[j] = evenRow;
            }
            //Printing the matrix
            for (int i = 0; i<customMatrixOrder; i++){
                for(int j = 0; j<customMatrixOrder; j++){
                    System.out.print(customMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }
        catch (NumberFormatException e) {
            e.getStackTrace();
        }
    }
}
