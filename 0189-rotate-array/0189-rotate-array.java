class Solution {
    public void rotate(int[] arr, int k) {
        k =k%arr.length ;
        int l =0;
        int r = arr.length-1 ;
      //reverse the whole array 
      while(l<r)
      {
        int temp = arr[r] ;
        arr[r]= arr[l] ;
        arr[l]=temp ;
        l++ ;
        r-- ;
      }

    //reverse the first k elements of the reversed array
    int n=0;
    int m =k-1 ;
     while(n<m)
      {
        int temp1 = arr[m] ;
        arr[m]= arr[n] ;
        arr[n]=temp1 ;
        n++ ;
        m-- ;
      }

      ///reverse the rest elements of the array 

      int p =k ;
      int q = arr.length-1 ;
       while(p<q)
      {
        int temp2 = arr[q] ;
        arr[q]= arr[p] ;
        arr[p]=temp2 ;
        p++ ;
        q-- ;
      }
    }
}