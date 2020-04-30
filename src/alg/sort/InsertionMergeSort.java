package alg.sort;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class InsertionMergeSort extends Sort {

    private int threshold = 6;

    @Override
    public long sort(int[] arr) {
        setThreshold(calculateLogBase2(arr.length));
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
        InsertionMergeSort insertionMergeSort = new InsertionMergeSort();
        insertionMergeSort.readTextFileInputArray(filePath);
        insertionMergeSort.sortArrayWithOutput();
    }

    private void mergeSort(int[] array) {
        if (array.length <= getThreshold()) {
            insertionSort(array);
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

    private void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && incrementAndCompare(array[j], key)) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    private boolean incrementAndCompare(int arrayValue, int key) {
        setComparisons(getComparisons() + 1);
        return arrayValue > key;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public static int calculateLogBase2(long n) {
        return (int)(Math.log(n) / Math.log(2));
    }
}