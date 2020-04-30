package alg.sort;

import java.security.SecureRandom;

public class Simulation {
    private static final int TEST_NUMBER = 5;
    private static final int RANGE_LIMIT = 100_000;
    private static final int INCREMENT_CONSTANT = 500;

    public static void main(String[] args) {
        if (args.length > 0 && args[0] != null && args[0].equals("insertionmergesort")) {
            simulateInsertionMergeSortingByThreshold();
        } else {
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

    private static long testInsertionMergeSortByThreshold(int size, int[] array, int threshold) {
        InsertionMergeSort insertionMergeSort = new InsertionMergeSort();
        insertionMergeSort.setArraySize(size);
        insertionMergeSort.setArray(array);
        insertionMergeSort.setThreshold(threshold);
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
        for (int i = 500; i <= RANGE_LIMIT; i = i + INCREMENT_CONSTANT) {
            System.out.println("Input size: " + i + "\n");
            for (int j = 0; j < TEST_NUMBER; j++) {
                int[] randomArray = generateRandomArray(i);
                long mergeSortComparisons = testMergeSort(i, randomArray.clone());
                System.out.println(mergeSortComparisons);
            }
            System.out.println("\n\n\n\n");
            for (int j = 0; j < TEST_NUMBER; j++) {
                int[] randomArray = generateRandomArray(i);
                long heapSortComparisons = testHeapSort(i, randomArray.clone());
                System.out.println(heapSortComparisons);
            }
            System.out.println("\n\n\n\n");
            for (int j = 0; j < TEST_NUMBER; j++) {
                int[] randomArray = generateRandomArray(i);
                long quickSortComparisons = testQuickSort(i, randomArray.clone());
                System.out.println(quickSortComparisons);
            }
            System.out.println("\n\n\n\n");
            for (int j = 0; j < TEST_NUMBER; j++) {
                int[] randomArray = generateRandomArray(i);
                long insertionMergeSortComparisons = testInsertionMergeSort(i, randomArray.clone());
                System.out.println(insertionMergeSortComparisons);
            }
            System.out.println("\n\n\n\n");
        }
    }

    private static void simulateInsertionMergeSortingByThreshold() {
        for (int m = 1; m <= 18; m++) {
            for (int i = 100; i <= RANGE_LIMIT; i = i * INCREMENT_CONSTANT) {
                for (int j = 0; j < TEST_NUMBER; j++) {
                    int[] randomArray = generateRandomArray(i);
                    long insertionMergeSortComparisons = testInsertionMergeSortByThreshold(i, randomArray.clone(), m);
                    System.out.println(insertionMergeSortComparisons);
                }
                System.out.println("\n\n\n\n");
            }
            System.out.println("----------------------------------------------------------------------------");
        }
    }
}