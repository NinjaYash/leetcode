class Solution {

    private static final long LIMIT = 1_000_000L;
    private List<Integer> primes;

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int[] half = new int[26];
        int halfLen = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            halfLen += half[i];
            if ((freq[i] & 1) == 1) {
                mid = (char) ('a' + i);
            }
        }

        sieve(halfLen);

        if (countWays(half, halfLen) < k) {
            return "";
        }

        StringBuilder firstHalf = new StringBuilder();
        int remaining = halfLen;

        while (remaining > 0) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;

                half[c]--;

                long ways = countWays(half, remaining - 1);

                if (ways >= k) {
                    firstHalf.append((char) ('a' + c));
                    remaining--;
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(firstHalf);

        if (mid != 0) {
            ans.append(mid);
        }

        ans.append(new StringBuilder(firstHalf).reverse());

        return ans.toString();
    }

    private void sieve(int n) {
        primes = new ArrayList<>();
        boolean[] composite = new boolean[n + 1];

        for (int i = 2; i <= n; i++) {
            if (!composite[i]) {
                primes.add(i);
                if ((long) i * i <= n) {
                    for (int j = i * i; j <= n; j += i) {
                        composite[j] = true;
                    }
                }
            }
        }
    }

    private int exponentInFactorial(int n, int p) {
        int res = 0;
        while (n > 0) {
            n /= p;
            res += n;
        }
        return res;
    }

    private long countWays(int[] cnt, int total) {
        long ans = 1;

        for (int p : primes) {
            if (p > total) break;

            int exp = exponentInFactorial(total, p);

            for (int x : cnt) {
                if (x > 1) {
                    exp -= exponentInFactorial(x, p);
                }
            }

            while (exp-- > 0) {
                ans *= p;
                if (ans >= LIMIT) {
                    return LIMIT;
                }
            }
        }

        return ans;
    }
}