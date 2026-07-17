class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        // Frequency of each value
        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        // cnt[d] = numbers divisible by d
        long[] cnt = new long[max + 1];
        for (int d = 1; d <= max; d++) {
            for (int j = d; j <= max; j += d) {
                cnt[d] += freq[j];
            }
        }

        // exact[d] = number of pairs whose gcd is exactly d
        long[] exact = new long[max + 1];
        for (int d = max; d >= 1; d--) {
            exact[d] = cnt[d] * (cnt[d] - 1) / 2;
            for (int j = d + d; j <= max; j += d) {
                exact[d] -= exact[j];
            }
        }

        // Prefix sum of pair counts
        long[] prefix = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + exact[i];
        }

        // Answer queries
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];
            int left = 1, right = max;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (prefix[mid] > q) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            ans[i] = left;
        }

        return ans;
    }
}