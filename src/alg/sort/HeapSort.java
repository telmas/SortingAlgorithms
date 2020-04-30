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
        System.out.println("Algorithm: HeapSort");

        Path filePath = Paths.get(filePathString);
        HeapSort heapSort = new HeapSort();
        heapSort.readTextFileInputArray(filePath);
        heapSort.sortArrayWithOutput();
    }

    private void heapSort(int[] array) {
        buildMaxHeap(array);

        for (int i = getArraySize() -1; i > 0; i--) {
            swap(array, 0, i);
            heapify(array, i,0);
        }
    }

    private void buildMaxHeap(int[] array) {
        for (int i =  getArraySize() / 2 - 1; i >= 0; i--) {
            heapify(array, getArraySize(), i);
        }
    }

    private void heapify(int[] array, int size, int i) {
        int max = i, left = 2 * i + 1, right = 2 * i + 2;

        if (left < size) {
            if (array[max] < array[left]) {
                max = left;
            }
            setComparisons(getComparisons() + 1);
        }

        if (right < size) {
            if (array[max] < array[right]) {
                max = right;
            }
            setComparisons(getComparisons() + 1);
        }

        if (i != max) {
            swap(array, i, max);
            heapify(array, size, max);
        }
    }

    private void swap(int[] array, int index0, int index1) {
        int swapValue = array[index0];
        array[index0] = array[index1];
        array[index1] = swapValue;
    }
}