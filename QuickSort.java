import java.util.*;
public class QuickSort{
    public static void sort(int [] arr){
        if(arr==null){
            return;
        }
        quickSort(0,arr.length-1,arr);
    }
    public static void quickSort(int left, int right,int [] arr){
        //divide into partition
        if(left<right){
            int partition = findPartition(left,right,arr);
            quickSort(left,partition-1,arr);
            quickSort(partition+1,right,arr);
        }

    }
    //   315 -> 135
    public static int findPartition(int left, int right, int [] arr){
        int pivot = arr[left];
        int cursor = left;
        for(int i = left+1; i < right+1; i++){
            if(pivot>arr[i]){
                cursor++;
                int temp = arr[cursor];
                arr[cursor] = arr[i];
                arr[i] = temp;
            }
        }
        int temp = arr[cursor];
        arr[cursor] = pivot;
        arr[left] = temp;
        return cursor;
    }
    public static int [] createRandomArray(int num, int max){
        int [] arr = new int [num];
        Random rand = new Random();
        for(int i = 0; i < num; i++){
            arr[i] = rand.nextInt(max);
        }
        return arr;
    }

    public static void main(String args[]){
        int [] example1 = {3,1,2,3,3};
        sort(example1);
        System.out.println("Example 1:");
        for(int i = 0; i < example1.length; i++){
            System.out.print(example1[i]+" ");
        }
        System.out.println("");
        int [] example2 = {1,5,2,7,3,8,3,9};
        sort(example2);
        System.out.println("Example 2:");
        for(int i = 0; i < example2.length; i++){
            System.out.print(example2[i]+" ");
        }
        System.out.println("");
        int [] example3 = createRandomArray(100,100);
        sort(example3);
        System.out.println("Example 3:");
        for(int i = 0; i < example3.length; i++){
            System.out.print(example3[i]+" ");
        }
        System.out.println("");
        int [] example4 = createRandomArray(50,100);
        sort(example4);
        System.out.println("Example 4:");
        for(int i = 0; i < example4.length; i++){
            System.out.print(example4[i]+" ");
        }
        System.out.println("");
        
    }
}
