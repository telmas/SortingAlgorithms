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

        Path filePath = Paths.get(filePathString);
        MergeSort mergesort = new MergeSort();
        mergesort.readTextFileInputArray(filePath);
        mergesort.sortArray();
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

        int arrayPointer = 0, arrayLPointer = 0, arrayRPointer =0;

        while (arrayLPointer < arrayLSize && arrayRPointer < arrayRSize) {
            if (arrayL[arrayLPointer] <= arrayR[arrayRPointer])  {
                array[arrayPointer] = arrayL[arrayLPointer];
                arrayLPointer++;
            } else {
                array[arrayPointer] = arrayR[arrayRPointer];
                arrayRPointer++;
            }
            arrayPointer++;
            setComparisons(getComparisons() + 1);
        }

        if (arrayLPointer == arrayLSize) {
            while (arrayRPointer < arrayRSize) {
                array[arrayPointer] = arrayR[arrayRPointer];
                arrayRPointer++;
                arrayPointer++;
            }
        } else {
            while (arrayLPointer < arrayLSize) {
                array[arrayPointer] = arrayL[arrayLPointer];
                arrayLPointer++;
                arrayPointer++;
            }
        }
    }
}