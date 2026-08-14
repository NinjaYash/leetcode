class Solution {
    public int maximumLengthSubstring(String s) {
        int[] counts = new int[26];
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            counts[rightChar - 'a']++;
            
            // Shrink the window if the current character exceeds 2 occurrences
            while (counts[rightChar - 'a'] > 2) {
                char leftChar = s.charAt(left);
                counts[leftChar - 'a']--;
                left++;
            }
            
            // Calculate the maximum window size found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
