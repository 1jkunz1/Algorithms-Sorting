package sorting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Arrays;

public class sorting {
    
    private static int[] arr;
    private static int[] arrCopy;
    
    private static int[] arrCopy2;
    private static BufferedReader read;
    private static Random randomGenerator;
    
    private static int size;
    private static int random;
    
    private static int n;
    
    
    private static void insertSort(int low, int high) {
      int sortIndex = low; 
      while (sortIndex < high) {
        int i = sortIndex + 1; 
        int valInsert = arr[i]; 
        while (i > low && arr[i - 1] > valInsert) {
          arr[i] = arr[i - 1];
          i--;
        }
        arr[i] = valInsert;
        sortIndex++;
      }
    }
    
    private static boolean isSorted(int low, int high) {
      int i = low;   
      while (i < high) {
        if (arr[i] > arr[i+1]) return false;
        i++;
      }
      return true;
    }
    
    
    
    private static int randomNum(int lower, int upper) {
        int randNum = randomGenerator.nextInt(upper - lower + 1) + lower;
        return randNum;
    }
    
    

    private static void printArray() {
      System.out.print("[" + arr[0]);
        for(int i=1; i<size; i++) {
            System.out.print(", " + arr[i]);
        }
        System.out.println("]");
    }
    
    public static void buildheap(){
        n=arr.length-1;
        for(int i=n/2;i>=0;i--){
            heapify(i);
        }
    }
    
    
    
    public static void heapify(int i){
        int largest;
        int left=2*i;
        int right=2*i+1;
        if(left <= n && arr[left] > arr[i]){
            largest=left;
        }
        else{
            largest=i;
        }
        
        if(right <= n && arr[right] > arr[largest]){
            largest=right;
        }
        if(largest!=i){
            exchange(i,largest);
            heapify(largest);
        }
    }
    
    public static void exchange(int i, int j){
        int k=arr[i];
        arr[i]=arr[j];
        arr[j]=k; 
   }
    
    public static void heapsort(){
        buildheap();    
        for(int i=n;i>0;i--){
            exchange(0, i);
            n=n-1;
            heapify(0);
        }
    }
    
    private static void mergesort(int low, int high) {
        if (low < high) {
          int middle = low + (high - low) / 2;
          mergesort(low, middle);
          mergesort(middle + 1, high);
          merge(low, middle, high);
        }
      }
    
    private static void mergesortA(int low, int high) {
        // Check if the array to be sorted is < 100.  If so, use insertSort
        if ((high - low) < 100) {
          insertSort(low, high);
        }
        // Otherwise, split it into smaller pieces.
        
        else {
          int middle = low + (high - low) / 2;
          mergesortA(low, middle);
          mergesortA(middle + 1, high);
          merge(low, middle, high);
        }
      }
    
    private static void mergesortB(int low, int high) {
        // Check if the array is already sorted
      if (!isSorted(low, high)) {
          int middle = low + (high - low) / 2;
          mergesort(low, middle);
          mergesort(middle + 1, high);
          merge(low, middle, high);
        }
      }
    
    private static void mergesortC(int low, int high) {
      // Check if the array is already sorted
      if (!isSorted(low, high)) {
        // If it's not sorted, but less than 100 ,  use insertSort.
        if ((high - low) < 100) {
          insertSort(low, high);
        }
        // Otherwise, divide it into smaller arrays.
        else {
          int middle = low + (high - low) / 2;
          mergesort(low, middle);
          mergesort(middle + 1, high);
          merge(low, middle, high);
        }
      }
    }

      private static void merge(int low, int middle, int high) {

        // Copy both parts into the arrCopy array
        for (int i = low; i <= high; i++) {
          arrCopy[i] = arr[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
          if (arrCopy[i] <= arrCopy[j]) {
            arr[k] = arrCopy[i];
            i++;
          } else {
            arr[k] = arrCopy[j];
            j++;
          }
          k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
          arr[k] = arrCopy[i];
          k++;
          i++;
        }

      }
      
      private static void quicksort(int low, int high) {
          int i = low, j = high;
          // Get the pivot element from the middle of the list
          int pivot = arr[(high+low)/2];

          // Divide into two lists
          while (i <= j) {
            
            while (arr[i] < pivot) {
              i++;
            }
            while (arr[j] > pivot) {
              j--;
            }

            if (i < j) {
              exchange(i, j);
              i++;
              j--;
            } else if (i == j) { i++; j--; }
          }

          // Recursion
          if (low < j)
            quicksort(low, j);
          if (i < high)
            quicksort(i, high);
        }
      
      
      
      private static int medianOfThree(int a, int b, int c) {
        if ((a <= b && b <= c) || (c <= b && b <= a)) return b;
        else if ((b <= a && a <= c) || (c <= a && a <= b)) return a;
        else return c;
      }
      
      
      
      private static void quicksort4A(int low, int high) {
        int i = low, j = high;
        int pivot = medianOfThree(arr[randomNum(low, high)], arr[randomNum(low, high)], arr[randomNum(low, high)]);
        while (i <= j) {
          while (arr[i] < pivot) {
            i++;
          }
          while (arr[j] > pivot) {
            j--;
          }
          if (i < j) {
            exchange(i, j);
            i++;
            j--;
          } else if (i == j) { i++; j--; }
        }
        if (low < j)
          quicksort(low, j);
        if (i < high)
          quicksort(i, high);
      }
      
      private static void quicksort4B(int low, int high) {
        int i = low, j = high;
        int m1 = medianOfThree(arr[randomNum(low, high)], arr[randomNum(low, high)], arr[randomNum(low, high)]);
        int m2 = medianOfThree(arr[randomNum(low, high)], arr[randomNum(low, high)], arr[randomNum(low, high)]);
        int m3 = medianOfThree(arr[randomNum(low, high)], arr[randomNum(low, high)], arr[randomNum(low, high)]);
        int pivot = medianOfThree(m1, m2, m3);
        while (i <= j) {
          while (arr[i] < pivot) {
            i++;
          }
          while (arr[j] > pivot) {
            j--;
          }
          if (i < j) {
            exchange(i, j);
            i++;
            j--;
          } else if (i == j) { i++; j--; }
        }
        if (low < j)
          quicksort(low, j);
        if (i < high)
          quicksort(i, high);
      }
      
      private static void quicksort5A(int low, int high) {
      if ((high - low) < 100) {
        insertSort(low, high);
      }
      else {
          int i = low, j = high;
          int pivot = arr[(high+low)/2];
          while (i <= j) {
            while (arr[i] < pivot) {
              i++;
            }
            while (arr[j] > pivot) {
              j--;
            }
            if (i < j) {
              exchange(i, j);
              i++;
              j--;
            } else if (i == j) { i++; j--; }
          }
          if (low < j)
            quicksort(low, j);
          if (i < high)
            quicksort(i, high);
        }
      }
      
      private static void quicksort5B(int low, int high) {
      if (!isSorted(low, high)) {
          int i = low, j = high;
          int pivot = arr[(high+low)/2];
          while (i <= j) {
            while (arr[i] < pivot) {
              i++;
            }
            while (arr[j] > pivot) {
              j--;
            }
            if (i < j) {
              exchange(i, j);
              i++;
              j--;
            } else if (i == j) { i++; j--; }
          }
          if (low < j)
            quicksort(low, j);
          if (i < high)
            quicksort(i, high);
        }
      }
      
      private static void quicksort5E(int low, int high) {
      if (!isSorted(low, high)) {
        if ((high - low) < 100) {
          insertSort(low, high);
        }
        else{
            int i = low, j = high;
            int pivot = medianOfThree(arr[randomNum(low, high)], arr[randomNum(low, high)], arr[randomNum(low, high)]);
            while (i <= j) {
              while (arr[i] < pivot) {
                i++;
              }
              while (arr[j] > pivot) {
                j--;
              }
              if (i < j) {
                exchange(i, j);
                i++;
                j--;
              } else if (i == j) { i++; j--; }
            }
            if (low < j)
              quicksort(low, j);
            if (i < high)
              quicksort(i, high);
        }
      }
      }
      
      private static void quicksort5F(int low, int high) {
      if (!isSorted(low, high)) {
        if ((high - low) < 100) {
          insertSort(low, high);
        }
        else{
            int i = low, j = high;
            int m1 = medianOfThree(arr[randomNum(low, high)], arr[randomNum(low, high)], arr[randomNum(low, high)]);
            int m2 = medianOfThree(arr[randomNum(low, high)], arr[randomNum(low, high)], arr[randomNum(low, high)]);
            int m3 = medianOfThree(arr[randomNum(low, high)], arr[randomNum(low, high)], arr[randomNum(low, high)]);
            int pivot = medianOfThree(m1, m2, m3);
            while (i <= j) {
              while (arr[i] < pivot) {
                i++;
              }
              while (arr[j] > pivot) {
                j--;
              }
              if (i < j) {
                exchange(i, j);
                i++;
                j--;
              } else if (i == j) { i++; j--; }
            }
            if (low < j)
              quicksort(low, j);
            if (i < high)
              quicksort(i, high);
        }
      }
      }

   public static void main(String[] args) {
        
        read = new BufferedReader(new InputStreamReader(System.in));
        
        randomGenerator = new Random();
        
        try
        {
          int n = 1000000;
          int range = 10000;
          while (n != 0) {
            // Initialize a total for each mergeSort variation.
            int m = 0;
            int mA = 0;
            int mB = 0;
            int mC = 0;
            int q = 0;
            int q4A = 0;
            int q4B = 0;
            int q5A = 0;
            int q5B = 0;
            int q5E = 0;
            int q5F = 0;
            arr = new int[n];
              arrCopy = new int[n];
              arrCopy2 = new int[n];
              for (int i=0; i<10; i++) {
                // Create the new array.
                for(int j=0; j<n; j++) {
                    arr[j] = arrCopy[j] = arrCopy2[j] = randomGenerator.nextInt(range);
                }
                
                // Then add the amount of time each sort took to its total.
                long start = System.currentTimeMillis();
                mergesort(0, n-1);
                long finish = System.currentTimeMillis();
                m += (finish-start);
                for(int j=0; j<n; j++) arr[j] = arrCopy[j] = arrCopy2[j];
                
                start = System.currentTimeMillis();
                mergesortA(0, n-1);
                finish = System.currentTimeMillis();
                mA += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopy[j] = arrCopy2[j];
                
                start = System.currentTimeMillis();
                mergesortB(0, n-1);
                finish = System.currentTimeMillis();
                mB += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopy[j] = arrCopy2[j];
                
                start = System.currentTimeMillis();
                mergesortC(0, n-1);
                finish = System.currentTimeMillis();
                mC += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopy[j] = arrCopy2[j];
                
                start = System.currentTimeMillis();
                quicksort(0, n-1);
                finish = System.currentTimeMillis();
                q += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopy2[j];
                
                start = System.currentTimeMillis();
                quicksort4A(0, n-1);
                finish = System.currentTimeMillis();
                q4A += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopy2[j];
                
                start = System.currentTimeMillis();
                quicksort4B(0, n-1);
                finish = System.currentTimeMillis();
                q4B += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopy2[j];
                
                start = System.currentTimeMillis();
                quicksort5A(0, n-1);
                finish = System.currentTimeMillis();
                q5A += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopy2[j];
                
                start = System.currentTimeMillis();
                quicksort5B(0, n-1);
                finish = System.currentTimeMillis();
                q5B += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopy2[j];
                
                start = System.currentTimeMillis();
                quicksort5E(0, n-1);
                finish = System.currentTimeMillis();
                q5E += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopy2[j];
                
                start = System.currentTimeMillis();
                quicksort5F(0, n-1);
                finish = System.currentTimeMillis();
                q5F += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopy2[j];
              }
              System.out.println("For an array size of " + n + ":");
              System.out.println("mergesort: " + (m/10) + " milliseconds");
              System.out.println("mergesortA: " + (mA/10) + " milliseconds");
              System.out.println("mergesortB: " + (mB/10) + " milliseconds");
              System.out.println("mergesortC: " + (mC/10) + " milliseconds");
              System.out.println("quicksort: " + (q/10) + " milliseconds");
              System.out.println("quicksortA: " + (q5A/10) + " milliseconds");
              System.out.println("quicksortB: " + (q5B/10) + " milliseconds");
              System.out.println("quicksortC: " + (q4A/10) + " milliseconds");
              System.out.println("quicksortD: " + (q4B/10) + " milliseconds");
              System.out.println("quicksortE: " + (q5E/10) + " milliseconds");
              System.out.println("quicksortF: " + (q5F/10) + " milliseconds");
              if (n == 1000000) n = 10000000;
              else if (n == 10000000) n = 50000000;
              else if (n == 50000000) n = 0;
          }
            
      
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

