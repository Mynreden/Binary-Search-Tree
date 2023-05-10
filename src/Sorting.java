public class Sorting<T extends Comparable<T>> {

    public T[] bubbleSort(T[] arr){
        int size = arr.length;
        for (int i = 0; i < size; i++){
            for (int j = i; j < size; j++){
                if (arr[i].compareTo(arr[j]) > 0){
                    T temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public T[] selectionSort(T[] arr){
        int size = arr.length;
        for (int i = 0; i < size - 1; i ++){
            int min_index = i;
            for (int j = i + 1; j < size; j ++){
                if (arr[min_index].compareTo(arr[j]) > 0) {
                    min_index = j;
                }
            }
            if (min_index != i){
                T temp = arr[min_index];
                arr[min_index] = arr[i];
                arr[i] = temp;
            }
        }
        return arr;
    }

    public T[] insertionSort(T[] arr){
        int size = arr.length;
        for (int i = 1; i < size; i ++){
            int sorted = i - 1;
            while(sorted > -1 && arr[sorted].compareTo(arr[sorted + 1]) > 0){
                T temp = arr[sorted];
                arr[sorted] = arr[sorted + 1];
                arr[sorted + 1] = temp;
                sorted --;
            }
        }
        return arr;
    }

    public T[] shakerSort(T[] arr){
        int size = arr.length;
        int left = 0, right = size - 1;
        while (right >= left){
            for (int i = right; i > left; i --){
                if (arr[left].compareTo(arr[i]) > 0){
                    T temp = arr[left];
                    arr[left] = arr[i];
                    arr[i] = temp;
                }
            }
            left ++;
            for (int i = left; i < right; i ++){
                if (arr[right].compareTo(arr[i]) < 0){
                    T temp = arr[right];
                    arr[right] = arr[i];
                    arr[i] = temp;
                }
            }
            right --;
        }
        return arr;
    }

    public T[] combSort(T[] arr){
        int size = arr.length;
        final double factor = 1.247;
        double distance = size;
        int step = size;
        while (distance >= 1){
            for (int i = 0; i + step < size; i++){
                if (arr[i].compareTo(arr[i + step]) > 0){
                    T temp = arr[i + step];
                    arr[i + step] = arr[i];
                    arr[i] = temp;
                }
            }
            distance = distance / factor;
            step = (int) distance;
        }
        return bubbleSort(arr);
    }

    public T[] quickSort(T[] arr) {
        return quickSort(arr, 0, arr.length - 1);
    }
    private T[] quickSort(T[] arr, int left, int right){
        if (left >= right) return arr;
        int less = Partition(arr, left, right);
        quickSort(arr, left, less -1);
        quickSort(arr, less + 1, right);
        return arr;
    }

    private int Partition(T[] arr, int left, int right) {
        T opora = arr[right];
        int less = left;

        for (int i = less; i < right; i ++){
            if (arr[i].compareTo(opora) < 1){
                T temp = arr[i];
                arr[i] = arr[less];
                arr[less] = temp;
                less ++;
            }
        }
        T temp = arr[right];
        arr[right] = arr[less];
        arr[less] = temp;
        return less;
    }

    public T[] mergeSort(T[] arr){
        return mergeSort(arr, 0, arr.length - 1);
    }

    private T[] mergeSort(T[] arr, int left, int right){
        if (left >= right) return arr;

        int middle = (left + right) / 2;
        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);

        merge(arr, left, right, middle);
        return arr;
    }

    private void merge(T[] arr, int left, int right, int middle){
        int size1 = middle - left + 1;
        int size2 = right - middle;

        Comparable[] arr1 = new Comparable[size1];
        Comparable[] arr2 = new Comparable[size2];

        for (int i = 0; i < size1; i ++){
            arr1[i] = arr[i + left];
        }
        for (int i = 0; i < size2; i ++){
            arr2[i] = arr[i + middle + 1];
        }

        int first = 0, second = 0, index = left;
        while (first < size1 && second < size2){
            if (arr1[first].compareTo(arr2[second]) > 0){
                arr[index] = (T) arr2[second];
                second++;
            }
            else {
                arr[index] = (T) arr1[first];
                first++;
            }
            index++;
        }
        while(first < size1){
            arr[index] = (T) arr1[first];
            first++;
            index++;
        }

        while(second < size2){
            arr[index] = (T) arr2[second];
            second++;
            index++;
        }
    }

}
