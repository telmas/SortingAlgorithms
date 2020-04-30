package alg.sort;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.sql.Array;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Simulation {
    private static final int TEST_NUMBER = 5;
    private static final int RANGE_LIMIT = 10000;
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
            System.out.println("Input Size: " + i);
//        int i = 100;
//                System.out.println("Test Number: " + j);
        for (int j = 0; j < TEST_NUMBER; j++) {
            int[] randomArray = generateRandomArray(i);
            long mergeSortComparisons = testMergeSort(i, randomArray.clone());
            System.out.println(mergeSortComparisons);
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        for (int j = 0; j < TEST_NUMBER; j++) {
            int[] randomArray = generateRandomArray(i);
            long heapSortComparisons = testHeapSort(i, randomArray.clone());
            System.out.println(heapSortComparisons);
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        for (int j = 0; j < TEST_NUMBER; j++) {
            int[] randomArray = generateRandomArray(i);
            long quickSortComparisons = testQuickSort(i, randomArray.clone());
            System.out.println(quickSortComparisons);
        }

//        for (int j = 0; j < TEST_NUMBER; j++) {
//            int[] randomArray = generateRandomArray(i);
//            long insertionMergeSortComparisons = testInsertionMergeSort(i, randomArray.clone());
//            System.out.println(insertionMergeSortComparisons);
//        }
        }

    }

//    private static void simulateInsertionMergeSortingByThreshold() {
//
////            System.out.println("Threshold: " + m);
//        for (int i = 100; i <= RANGE_LIMIT; i = i * INCREMENT_CONSTANT) {
////                System.out.println("Input Size: " + i);
//            int[] randomArray = generateRandomArray(i);
//            for (int j = 0; j < TEST_NUMBER; j++) {
////                    int[] randomArray = readFile((j + 1) + "_" + i + ".txt");
//                for (int m = 1; m <= 18; m++) {
//                    long insertionMergeSortComparisons = testInsertionMergeSortByThreshold(i, randomArray.clone(), m);
//                    System.out.print(insertionMergeSortComparisons + " ");
//                }
//                System.out.println();
//
//            }
//            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        }
//    }

    private static void simulateInsertionMergeSortingByThreshold() {
        for (int m = 1; m <= 18; m++) {
            System.out.println("M: " + m);
//            for (int i = 100; i <= RANGE_LIMIT; i = i * INCREMENT_CONSTANT) {
               int i = 10000;
               long[] results = new long[100];
               for (int j = 0; j < TEST_NUMBER; j++) {
                   int[] randomArray = generateRandomArray(i);
                   long insertionMergeSortComparisons = testInsertionMergeSortByThreshold(i, randomArray.clone(), m);
                   results[j] = insertionMergeSortComparisons;
                }
            System.out.printf("%f\n", Arrays.stream(results).average().getAsDouble());
//                System.out.println();
//            }
//            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }
    }

    public static int[] readFile(String fileName) {
        try {
            Pattern pattern = Pattern.compile(" ");
            return Files.lines(Paths.get(new File(fileName).getPath()))
                    .flatMap(pattern::splitAsStream)
                    .skip(1)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[0];
    }
}
