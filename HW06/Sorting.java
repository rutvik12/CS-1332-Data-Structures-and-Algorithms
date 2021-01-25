import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author - Rutvik Marakana
 * @version 1.0
 * @userid - rmarakana3
 * @GTID - 903401647
 *
 * Collaborators: None
 *
 * Resources: None
 */
public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {

        if (arr == null) {
            throw new IllegalArgumentException("Array that needs to be sorted can't be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator can't be null");
        }

        for (int n = 1; n < arr.length; n++) {
            int i = n;
            while (i != 0 && comparator.compare(arr[i], arr[i - 1]) < 0) {
                T temp = arr[i - 1];
                arr[i - 1] = arr[i];
                arr[i] = temp;
                i--;
            }
        }

    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {

        if (arr == null) {
            throw new IllegalArgumentException("Array that needs to be sorted can't be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator can't be null");
        }

        if (arr.length <= 1) {
            return;
        }

        int length = arr.length;
        int mid = length / 2;
        T[] left = (T[]) new Object[mid];
        T[] right = (T[]) new Object[length - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }

        int counter = 0;
        for (int j = mid; j < length; j++) {
            right[counter] = arr[j];
            counter++;
        }

        mergeSort(left, comparator);
        mergeSort(right, comparator);

        int i = 0;
        int j = 0;

        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                arr[i + j] = left[i];
                i++;
            } else {
                arr[i + j] = right[j];
                j++;
            }

        }

        while (i < left.length) {
            arr[i + j] = left[i];
            i++;
        }

        while (j < right.length) {
            arr[i + j] = right[j];
            j++;
        }
    }


    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array to be sorted can't be null");
        }
        LinkedList<Integer>[] buckets = (LinkedList<Integer>[]) new LinkedList[19];

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }

        int maximum = 0;
        int count = 0;
        int div = 1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == Integer.MIN_VALUE) {
                maximum = Integer.MIN_VALUE;
                break;
            }
            if (Math.abs(arr[i]) > maximum) {
                maximum = Math.abs(arr[i]);
            }
        }

        while (maximum != 0) {
            count++;
            maximum = maximum / 10;
        }

        while (count != 0) {

            for (int k = 0; k < arr.length; k++) {
                int index = arr[k] / div;
                buckets[index % 10 + 9].addLast(arr[k]);
            }

            int index = 0;
            for (int j = 0; j < buckets.length; j++) {
                if (buckets[j] != null) {
                    for (int num : buckets[j]) {
                        arr[index] = num;
                        index++;
                    }
                    buckets[j].clear();
                }
            }
            count--;
            div *= 10;
        }
    }

    /**
     * Implement kth select.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param k          the index to retrieve data from + 1 (due to
     *                   0-indexing) if the array was sorted; the 'k' in "kth
     *                   select"; e.g. if k == 1, return the smallest element
     *                   in the array
     * @param arr        the array that should be modified after the method
     *                   is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @return the kth smallest element
     * @throws java.lang.IllegalArgumentException if the array or comparator
     *                                            or rand is null or k is not
     *                                            in the range of 1 to arr
     *                                            .length
     */
    public static <T> T kthSelect(int k, T[] arr, Comparator<T> comparator,
                                  Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("Array from which the Kth element needs to be selected can't be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator can't be null");
        }
        if (rand == null) {
            throw new IllegalArgumentException("Object of type Random can't be null");
        }
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("K can't be less than 1 or greater than arr.length");
        }
        return kthSelectHelper(arr, 0, arr.length - 1, k, comparator, rand);
    }

    /**
     * Helper method for kthSelect
     *
     * Method is used to find element at k-1 index.
     *
     * @param <T> data type to sort
     * @param arr The array from which kth element is to be found
     * @param start The start index of array
     * @param end The last index of array
     * @param k The index to retrieve data from + 1
     * @param comparator The comparator used to compare data in the array
     * @param rand Random object used to select pivot
     * @return The element present at k - 1 of sorted array
     */
    private static <T> T kthSelectHelper(T[] arr, int start, int end, int k, Comparator<T> comparator, Random rand) {

        if (end - start < 0) {
            return null;
        }
        int pivotIndex = rand.nextInt(end - start + 1) + start;
        T pivotValue = arr[pivotIndex];
        T temp = arr[start];
        arr[start] = pivotValue;
        arr[pivotIndex] = temp;

        int i = start + 1;
        int j = end;

        while (i <= j) {
            while (i <= j && comparator.compare(arr[i], pivotValue) <= 0) {
                i++;
            }
            while (i <= j && comparator.compare(arr[j], pivotValue) >= 0) {
                j--;
            }

            if (i <= j) {
                T temp1 = arr[i];
                arr[i] = arr[j];
                arr[j] = temp1;
                i++;
                j--;
            }
        }
        T temp2 = arr[start];
        arr[start] = arr[j];
        arr[j] = temp2;

        if (j == (k - 1)) {
            return arr[j];
        } else if (j < (k - 1)) {
            return kthSelectHelper(arr, j + 1, end, k, comparator, rand);
        } else {
            return kthSelectHelper(arr, start, j - 1, k, comparator, rand);
        }
    }
}
