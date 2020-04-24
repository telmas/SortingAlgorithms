package alg.sort;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class Sort implements Sorter {

    private int[] array;
    private int arraySize;
    private long comparisons;

    public void readTextFileInputArray(Path filePath) {
        Pattern pattern = Pattern.compile("\\s+");
        try {
            List<String> collect = Files.lines(filePath, StandardCharsets.ISO_8859_1)
                    .flatMap(pattern::splitAsStream)
                    .collect(Collectors.toList());

            setArraySize(Integer.parseInt(collect.get(0)));
            setArray(collect.stream().skip(1).mapToInt(Integer::parseInt).toArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sortArray() {
        System.out.println("Array size:" + getArraySize());
        long comparisons = sort(getArray());
        System.out.println("Sorted array: " + Arrays.toString(getArray()));
        System.out.println("Number of comparisons: " + comparisons);
    }

    public int getArraySize() {
        return arraySize;
    }

    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public long getComparisons() {
        return comparisons;
    }

    public void setComparisons(long comparisons) {
        this.comparisons = comparisons;
    }
}
