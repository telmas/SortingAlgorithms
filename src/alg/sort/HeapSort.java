package alg.sort;

import java.nio.file.Path;
import java.nio.file.Paths;

public class HeapSort extends Sort  {
    @Override
    public long sort(int[] arr) {
        heapSort(arr);
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
        HeapSort heapSort = new HeapSort();
        heapSort.readTextFileInputArray(filePath);
        heapSort.sortArray();
    }

    private void heapSort(int[] array) {

        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int oldRoot = array[0];
            array[0] = array[i];
            array[i] = oldRoot;
            heapify(array, i, 0);
        }

    }

    private void heapify(int[] array, int n, int i) {
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < n && array[leftChild] > array[largest]) {//
            largest = leftChild;
        }

        if (rightChild < n && array[rightChild] > array[largest]) {//
            largest = rightChild;
        }

        if (largest != i) {//?
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            heapify(array, n, largest);
        }
    }

}
