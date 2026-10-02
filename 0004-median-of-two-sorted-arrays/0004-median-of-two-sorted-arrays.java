class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i =0,j=0 ;
        int n = nums1.length +nums2.length ;
        int k =0 ;
        int arr[] = new int[n] ;
        while(i<nums1.length && j<nums2.length)
        {
            if(nums1[i]<nums2[j])
            {
                arr[k++]=nums1[i++] ;
            }
            else
            {
                arr[k++]=nums2[j++] ;
            }
        }

        while(i<nums1.length)
        {
           arr[k++]=nums1[i++] ;
        }

        while(j<nums2.length)
        {
            arr[k++]=nums2[j++] ;
        }

        int l =0;
        int r = arr.length-1 ;
        int mid= l+(r-l)/2 ;
        if(arr.length%2==0)
        {
            int m = mid+1 ;
            double avg =(arr[m]+arr[mid])/2.0 ;
            return avg ;
        }
        
        else
        {   return (double)arr[mid] ;
        }
        
    }
}