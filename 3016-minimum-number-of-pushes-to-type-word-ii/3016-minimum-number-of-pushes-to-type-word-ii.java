import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];

        // Count frequency of each letter
        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Sort in ascending order
        Arrays.sort(freq);

        int pushes = 0;
        int position = 0;

        // Traverse from highest frequency to lowest
        for (int i = 25; i >= 0 && freq[i] > 0; i--) {
            pushes += freq[i] * (position / 8 + 1);
            position++;
        }

        return pushes;
    }
}