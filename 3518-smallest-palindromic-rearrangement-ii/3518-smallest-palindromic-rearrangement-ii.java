class Solution {

    static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        int[] half = new int[26];
        int halfLen = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            halfLen += half[i];

            if ((freq[i] & 1) == 1)
                mid = (char) ('a' + i);
        }

        if (countWays(half) < k)
            return "";

        StringBuilder first = new StringBuilder();

        while (halfLen > 0) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0)
                    continue;

                half[c]--;

                long ways = countWays(half);

                if (ways >= k) {
                    first.append((char) ('a' + c));
                    halfLen--;
                    break;
                }

                k -= ways;
                half[c]++;
            }
        }

        StringBuilder ans = new StringBuilder();

        ans.append(first);

        if (mid != 0)
            ans.append(mid);

        ans.append(new StringBuilder(first).reverse());

        return ans.toString();
    }

    private long countWays(int[] cnt) {

        int total = 0;

        for (int x : cnt)
            total += x;

        long ans = 1;
        int rem = total;

        for (int x : cnt) {

            if (x == 0)
                continue;

            ans *= nCr(rem, x);

            if (ans >= LIMIT)
                return LIMIT;

            rem -= x;
        }

        return ans;
    }

    private long nCr(int n, int r) {

        r = Math.min(r, n - r);

        long res = 1;

        for (int i = 1; i <= r; i++) {

            res = res * (n - r + i) / i;

            if (res >= LIMIT)
                return LIMIT;
        }

        return res;
    }
}