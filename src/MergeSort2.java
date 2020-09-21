import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MergeSort2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("How long should the array be?");
        int chosenLength = scanner.nextInt();
        System.out.println("What should the highest possible number be?");
        int highestNumber = scanner.nextInt();

        int[] numbers = new int[chosenLength];
        for (int i = 0; i < chosenLength; i++) {  //Fills the array with random numbers
            if (rand.nextInt(10) % 5 != 0) {     //Just so there are a few negative numbers in the array. The Ratio 2 out of 10 was chosen arbitrary
                numbers[i] = rand.nextInt(highestNumber);
            } else {
                numbers[i] = rand.nextInt(highestNumber) * -1;
            }
        }
        System.out.println("The generated array:");
        System.out.println(Arrays.toString(numbers));
        System.out.println("The sorted array:");
        System.out.println(Arrays.toString(SplitTheArray(numbers)));


    }

    private static int[] SplitTheArray(int[] numbers) {
        if (numbers.length < 2) {
            return numbers;
        } else {
            int[] numbersLeft = new int[numbers.length / 2];
            for (int i = 0; i < numbers.length / 2; i++) {      //fills the numbersLeft-array with the left half of the original numbers-array
                numbersLeft[i] = numbers[i];
            }
            int[] numbersRight = new int[numbers.length / 2 + numbers.length % 2];
            for (int i = 0; i < numbers.length / 2 + numbers.length % 2; i++) {     //fills the numbersRight-array with the right half of the original
                numbersRight[i] = numbers[numbers.length / 2 + i];                  //numbers-array plus the "left over-number" if the array-length is uneven
            }

            numbersLeft = SplitTheArray(numbersLeft);
            numbersRight = SplitTheArray(numbersRight);

            return MergeTheArrays(numbersLeft, numbersRight);
        }
    }

    private static int[] MergeTheArrays(int[] numbersLeft, int[] numbersRight) {
        int[] sortedNumbers = new int[numbersRight.length + numbersLeft.length];
        int leftCounter = 0;
        int rightCounter = 0;
        for (int i = 0; i < numbersRight.length + numbersLeft.length; i++) {
            if (leftCounter == numbersLeft.length) {            //If the left array is empty, just fill the rest of the sorted array with the rest of the right array
                sortedNumbers[i] = numbersRight[rightCounter];
                rightCounter++;
            } else if (rightCounter == numbersRight.length) {   //If the right array is empty, vice versa
                sortedNumbers[i] = numbersLeft[leftCounter];
                leftCounter++;
            } else if (numbersLeft[leftCounter] < numbersRight[rightCounter]) { //Sort the array with the smallest number first
                sortedNumbers[i] = numbersLeft[leftCounter];
                leftCounter++;
            } else {
                sortedNumbers[i] = numbersRight[rightCounter];
                rightCounter++;
            }
        }
        return sortedNumbers;
    }
}
