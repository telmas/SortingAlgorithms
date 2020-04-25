package alg.sort;

import java.security.SecureRandom;
import java.util.HashMap;

public class Simulation {
    private static final int TEST_NUMBER = 5;
    private static final int RANGE_LIMIT = 10000;
    private static final int INCREMENT_CONSTANT = 100;

    public static void main(String[] args) {
        for (int i = 0; i < TEST_NUMBER; i++) {
            simulateSorting();
        }
    }

    private static long testMergeSort(int size, int[] array) {
        MergeSort mergeSort = new MergeSort();
        mergeSort.setArraySize(size);
        mergeSort.setArray(array);
        mergeSort.sortArray();
        return mergeSort.getComparisons();
    }

    private static long testHeapSort(int size, int[] array) {
        HeapSort heapSort = new HeapSort();
        heapSort.setArraySize(size);
        heapSort.setArray(array);
        heapSort.sortArray();
        return heapSort.getComparisons();
    }

    private static long testQuickSort(int size, int[] array) {
        QuickSort quickSort = new QuickSort();
        quickSort.setArraySize(size);
        quickSort.setArray(array);
        quickSort.sortArray();
        return quickSort.getComparisons();
    }

    private static long testInsertionMergeSort(int size, int[] array) {
        InsertionMergeSort insertionMergeSort = new InsertionMergeSort();
        insertionMergeSort.setArraySize(size);
        insertionMergeSort.setArray(array);
        insertionMergeSort.sortArray();
        return insertionMergeSort.getComparisons();
    }

    public static int[] generateRandomArray(int arrayLength) {
        SecureRandom secureRandom = new SecureRandom();
        int[] array = new int[arrayLength];
        for (int i = 0; i < array.length; i++) {
            array[i] = secureRandom.nextInt(Integer.MAX_VALUE);
        }
        return array;
    }

    private static void simulateSorting() {

        HashMap<Integer, Long> mergeSortMapData = new HashMap<>();
        HashMap<Integer, Long> heapSortMapData = new HashMap<>();
        HashMap<Integer, Long> quickSortMapData = new HashMap<>();
        HashMap<Integer, Long> insertionMergeSortMapData = new HashMap<>();

        for (int i = 100; i <= RANGE_LIMIT; i = i + INCREMENT_CONSTANT) {
            int[] randomArray = generateRandomArray(i);
            long mergeSortComparisons = testMergeSort(i, randomArray.clone());
            long heapSortComparisons = testHeapSort(i, randomArray.clone());
            long quickSortComparisons = testQuickSort(i, randomArray.clone());
            long insertionMergeSortComparisons = testInsertionMergeSort(i, randomArray.clone());

            mergeSortMapData.put(i, mergeSortComparisons);
            heapSortMapData.put(i, heapSortComparisons);
            quickSortMapData.put(i, quickSortComparisons);
            insertionMergeSortMapData.put(i, insertionMergeSortComparisons);
        }

        System.out.println("Size \t\t" + "MergeSort \t\t" + "HeapSort \t\t" + "QuickSort \t\t" + "InsertionMergeSort");

        for (int j = 100; j <= RANGE_LIMIT; j = j + INCREMENT_CONSTANT) {
            System.out.println(j + " \t\t"
                    + mergeSortMapData.get(j) + " \t\t\t"
                    + heapSortMapData.get(j) + " \t\t\t"
                    + quickSortMapData.get(j) +  " \t\t\t"
                    + insertionMergeSortMapData.get(j));
        }
    }
}
