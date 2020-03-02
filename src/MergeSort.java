import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MergeSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int length;
        int maxNumber;
        System.out.println("How long should your array be?");
        length = scanner.nextInt();
        System.out.println("What should be the highest possible number?");
        maxNumber = scanner.nextInt();
        int[] numbers = new int[length];
        for (int i = 0; i < length; i++) {
            Random rand = new Random();
            numbers[i] = rand.nextInt(maxNumber);
        }
        for (int i = 0; i < length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        split(numbers);

        System.out.println(Arrays.toString(split(numbers)));


    }

    private static int[] split(int[] numbers) {

        if (numbers.length < 2) {
            return numbers;
        } else {
            int[] numbersLeft = new int[numbers.length / 2];
            for (int i = 0; i < numbers.length / 2; i++) {
                numbersLeft[i] = numbers[i];
            }
            int[] numbersRight;
            if (numbers.length % 2 == 0) {
                numbersRight = new int[numbers.length / 2];
                for (int i = 0; i < (numbers.length / 2); i++) {
                    numbersRight[i] = numbers[numbers.length / 2 + i];
                }
            } else {
                numbersRight = new int[numbers.length / 2 + 1];
                for (int i = 0; i < numbers.length / 2 + 1; i++) {
                    numbersRight[i] = numbers[numbers.length / 2 + i];
                }
            }
            numbersLeft = split(numbersLeft);
            numbersRight = split(numbersRight);

            return merge(numbersLeft, numbersRight);
        }

    }

    private static int[] merge(int[] numbersLeft, int[] numbersRight) {

        int indexLeft = 0;
        int indexRight = 0;
        int indexMerged = 0;
        int[] sorted = new int[numbersLeft.length + numbersRight.length];

        while (indexMerged < sorted.length) {                                           //Es wird so lange gemerged bis ein neuer Array mit der Gesammtlänge der Summer der beiden früheren Arrays angefüllt wurde.
            if (indexLeft < numbersLeft.length && indexRight < numbersRight.length) {      //So lange in beiden Arrays noch was drin ist, muss verglichen werden


                if (numbersLeft[indexLeft] > numbersRight[indexRight]) {
                    sorted[indexMerged] = numbersLeft[indexLeft];
                    indexLeft++;
                    indexMerged++;
                } else {
                    sorted[indexMerged] = numbersRight[indexRight];
                    indexRight++;
                    indexMerged++;
                }
            } else if (indexLeft < numbersLeft.length) {                        //Hier wird nur mehr der "Rest", der in einer der beiden Arrays drinnen ist, an das sorted-array hinten drann gehängt
                sorted[indexMerged] = numbersLeft[indexLeft];
                indexLeft++;
                indexMerged++;
            } else if (indexRight < numbersRight.length) {
                sorted[indexMerged] = numbersRight[indexRight];
                indexRight++;
                indexMerged++;
            }

        }
        return sorted;
    }
}
