class Solution {
    public int maximumProduct(int[] nums) {

         Arrays.sort(nums);
        int n = nums.length;
        int i = n- 2 ;
        int j = n -1 ;
        int k = n-3;

        int total1 = nums[i]*nums[j]*nums[k] ;
        int total2 =nums[0]*nums[1]*nums[n-1];
        
    
     return Math.max(total1,total2);
    }
}