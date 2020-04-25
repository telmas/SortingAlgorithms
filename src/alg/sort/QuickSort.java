package alg.sort;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class QuickSort extends Sort {
    @Override
    public long sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
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
        QuickSort quickSort = new QuickSort();
        quickSort.readTextFileInputArray(filePath);
        quickSort.sortArray();
    }

    private void quickSort(int[] array, int p, int q) {

        if (p >= q) {
            return;
        }

        int i = partitionRandomized(array, p, q);
        quickSort(array, p, i - 1);
        quickSort(array, i + 1, q);
    }

    private int partitionRandomized(int[] array, int low, int high) {
        int r = generatePivotIndex(low, high);
        exchange(array, r, high);
        return partition(array, low, high);
    }

    private int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int pi = start;

        for (int i = start; i <= end - 1; i++) {
            if (array[i] <= pivot) {
                exchange(array, pi, i);
                pi++;
            }
            setComparisons(getComparisons() + 1);
        }
        exchange(array, pi, end);
        return pi;
    }

    private int generatePivotIndex(int low, int high) {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(high-low) + low;
    }

    private void exchange(int[] array, int index0, int index1) {
        int exchangeValue = array[index0];
        array[index0] = array[index1];
        array[index1] = exchangeValue;
    }
}