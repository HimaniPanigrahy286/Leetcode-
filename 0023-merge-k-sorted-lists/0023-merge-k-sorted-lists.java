/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
         if(lists == null||lists.length==0)
         return null ;
        ListNode ans = lists[0] ;
        for(int i =1;i<lists.length;i++)
        {
            ListNode dummy = new ListNode(-1);
            ListNode temp =dummy ;

            ListNode list1 = ans ;
            ListNode list2 = lists[i] ;

            while(list1!=null && list2!=null)
            {
                if(list1.val <=list2.val)
                {   
                    temp.next = list1 ;
                    list1=list1.next ;
                }
                else
                {
                    temp.next = list2 ;
                    list2=list2.next ;
                }

                temp=temp.next ;
            }
            
            if(list1!=null)
            {
               temp.next =list1 ;
            }
            if(list2!=null)
            {
                temp.next =list2 ;
            }
            
            ans=dummy.next ;
        }
        return ans ;
    }
}