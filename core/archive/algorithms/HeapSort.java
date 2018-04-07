/*

study of sorting algorithms :

bubble sort
insertion sort
merge sort
quick sort
selection sort
***heap sort***
counting sort
radix sort

*/

/*************************************************
worst : O(n*log(n))
best : O(n*log(n))
average : O(n*log(n))

build heap = O(n*log(n))
pop n nodes off = O(n*log(n))

*************************************************
space : O(1)
*************************************************/

public class HeapSort {
    private static int[] a;
    private static int n;
    private static int left;
    private static int right;
    private static int largest;

    public static void buildheap(int [] a){
        n=a.length;

        //note that we start at the leaves and call heapifyDown() upwards
        for(int i=n/2;i>=0;i--){
            maxheap(a, i);
        }
    }

    //assumes left and right are valid heaps
    public static void maxheap(int[] a, int i){
        left=2*i+1;
        right=2*i+2;

        //find largest
        if(left < n && a[left] > a[i]){
            largest=left;
        }
        else{
            largest=i;
        }
        if(right < n && a[right] > a[largest]){
            largest=right;
        }

        if(largest != i){
            swap(i,largest);
            maxheap(a, largest);
        }
    }

    public static void swap(int i, int j){
        int t=a[i];
        a[i]=a[j];
        a[j]=t;
    }

    //performs the heapsort
    public static void sort(int[] a0){
        a=a0; //build a heap with the array and repeatedly pop
        buildheap(a);

        for(int i=n-1;i>0;i--){
            swap(0, i); //pop and place at end
            n=n-1; //decrease n so that maxheap() knows where to stop
            maxheap(a, 0);
        }
    }

    public static void main(String[] args) {
        int[] a1={4,1,3,2,16,9,10,14,8,7};
        sort(a1);
        for(int i=0; i<a.length; i++){
            System.out.print(a1[i] + " ");
        }
    }
}
