class Quicksort
{
    public void qSort(int[] a, int p, int r)
    {
        if(p<r)
        {
            int q = Partition(a, p,r);
            qSort(a, p, q-1);
            qSort(a, q+1, r);
        }
     }

     private int Partition(int[] a, int p, int r)
     {
         int x = a[r];

         int i = p-1;
         int temp=0;

         for(int j=p; j<r; j++)
         {
             if(a[j]<=x)
             {
                 i++;
                 temp = a[i];
                 a[i] = a[j];
                 a[j] = temp;
             }
         }

         temp = a[i+1];
         a[i+1] = a[r];
         a[r] = temp;
         return (i+1);
     }
}
