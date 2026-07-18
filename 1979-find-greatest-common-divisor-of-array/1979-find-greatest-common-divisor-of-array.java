class Solution {
    public int findGCD(int[] nums) {
        int min = nums[0];
        int max = nums[0];
// Find minimum and maximum
        for (int num : nums) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        // Brute force to find GCD
        for (int i = Math.min(min, max); i >= 1; i--) {
            if (min % i == 0 && max % i == 0) {
                return i;
            }
        }

        return 1;
    }
}