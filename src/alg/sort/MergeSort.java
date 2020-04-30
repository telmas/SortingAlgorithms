package alg.sort;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class MergeSort extends Sort {
    @Override
    public long sort(int[] arr) {
        mergeSort(arr);
        return getComparisons();
    }

    public static void main(String[] args) {

        if (args.length < 1 || args[0] == null || args[0].trim().equals("") || args[0].isEmpty()) {
            System.out.printf("%s", "Attention! The required file path is not passed as argument!");
            return;
        }

        String filePathString = args[0].trim();
        System.out.println("Got the file path argument:" + filePathString);
        System.out.println("Algorithm: MergeSort");

        Path filePath = Paths.get(filePathString);
        MergeSort mergesort = new MergeSort();
        mergesort.readTextFileInputArray(filePath);
        mergesort.sortArrayWithOutput();
    }

    private void mergeSort(int[] array) {
        if (array.length <= 1) {
            return;
        }
        int n = array.length;
        int middle = (n) / 2;
        int[] arrayL = Arrays.copyOfRange(array, 0, middle);
        int[] arrayR = Arrays.copyOfRange(array, middle, n);
        mergeSort(arrayL);
        mergeSort(arrayR);
        merge(array, arrayL, arrayR);
    }

    private void merge(int[] array, int[] arrayL, int[] arrayR) {
        int arrayLSize = arrayL.length;
        int arrayRSize = arrayR.length;

        int arrayIndex = 0, arrayLIndex = 0, arrayRIndex = 0;

        while (arrayLIndex < arrayLSize && arrayRIndex < arrayRSize) {
            if (arrayL[arrayLIndex] <= arrayR[arrayRIndex])  {
                array[arrayIndex] = arrayL[arrayLIndex];
                arrayLIndex++;
            } else {
                array[arrayIndex] = arrayR[arrayRIndex];
                arrayRIndex++;
            }
            arrayIndex++;
            setComparisons(getComparisons() + 1);
        }

        if (arrayLIndex == arrayLSize) {
            while (arrayRIndex < arrayRSize) {
                array[arrayIndex] = arrayR[arrayRIndex];
                arrayRIndex++;
                arrayIndex++;
            }
        } else {
            while (arrayLIndex < arrayLSize) {
                array[arrayIndex] = arrayL[arrayLIndex];
                arrayLIndex++;
                arrayIndex++;
            }
        }
    }
}