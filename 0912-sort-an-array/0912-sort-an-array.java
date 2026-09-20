class Solution {
    public int[] sortArray(int[] arr) {
        mergeSort(arr,0,arr.length-1) ;
        return arr ;
    }
    void mergeSort(int arr[],int si,int ei)
    {   if(si>=ei)
        return ;
        //kaam 
        int mid = si+(ei-si)/2 ;//left part sorted by dividing
        mergeSort(arr,si,mid) ;//right part sorted by dividing
        mergeSort(arr,mid+1,ei) ;//merge the two arrays 
        merge(arr,si,mid,ei) ;
    }
    void merge(int arr[],int si,int mid,int ei )
    {
        int temp[] = new int[ei-si+1] ;
        int i =si;
        int j = mid+1 ;
        int k = 0 ;
        while(i<=mid && j<=ei)
        {
          if(arr[i]<=arr[j]){
            temp[k]=arr[i]; 
            i++ ;
          }
          else{
            temp[k]=arr[j]; 
            j++ ;
          }
          k++ ;
        }
        while(i<=mid)
        {
            temp[k++]=arr[i++] ;
        }
        while(j<=ei)
        {
            temp[k++]=arr[j++] ;
        }
        for (k = 0; k < temp.length; k++) {
            arr[si + k] = temp[k];
        }

    }
}