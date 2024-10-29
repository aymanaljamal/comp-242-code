package application;
import java.util.*;

public class SortingComparison {

    public static void main(String[] args) {
        int size = 1000000;
        int[] randomArray = generateRandomArray(size);

      
        int[] array1 = Arrays.copyOf(randomArray, size);
        int[] array2 = Arrays.copyOf(randomArray, size);
        int[] array3 = Arrays.copyOf(randomArray, size);
        int[] array4 = Arrays.copyOf(randomArray, size);
        int[] array5 = Arrays.copyOf(randomArray, size);

      
        List<Integer> list = new ArrayList<>();
        for (int num : array1) {
            list.add(num);
        }
        long startTime = System.currentTimeMillis();
        Collections.sort(list);
        long endTime = System.currentTimeMillis();
        System.out.println("Collections.sort: " + (endTime - startTime) + " ms");

      
        startTime = System.currentTimeMillis();
        Arrays.sort(array2);
        endTime = System.currentTimeMillis();
        System.out.println("Arrays.sort: " + (endTime - startTime) + " ms");

       
        startTime = System.currentTimeMillis();
        insertionSort(array3);
        endTime = System.currentTimeMillis();
        System.out.println("Insertion Sort: " + (endTime - startTime) + " ms");

       
        startTime = System.currentTimeMillis();
        shellSort(array4);
        endTime = System.currentTimeMillis();
        System.out.println("Shell Sort: " + (endTime - startTime) + " ms");

        
        startTime = System.currentTimeMillis();
        mergeSort(array5);
        endTime = System.currentTimeMillis();
        System.out.println("Merge Sort: " + (endTime - startTime) + " ms");
    }

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    private static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    private static void shellSort(int[] array) {
        int n = array.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int key = array[i];
                int j = i;
                while (j >= gap && array[j - gap] > key) {
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = key;
            }
        }
    }

    private static void mergeSort(int[] array) {
        if (array.length < 2) {
            return;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }

    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
}
