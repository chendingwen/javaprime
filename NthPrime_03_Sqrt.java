public class NthPrime_03_Sqrt{

    // Stopping at n/2 is still way too slow to find the n-th
    // prime for larger n.
    // So we start to think. If n/2 is a divisor of n, then
    // n = 2*(n/2), so 2 is a divisor too, and the testing
    // loop never reaches n/2, hence we can stop even earlier.
    // Now, the next largest possible divisor of n is n/3.
    // But if n/3 is a divisor of n, so is 3, and the loop
    // won't go further than 3. The next largest -
    //
    // Hey, wait a minute. 2 <-> n/2, 3 <-> n/3 ...
    // The divisors of n come in pairs, d and n/d.
    // Unless d = sqrt(n), one of these two, say d, is smaller
    // than the other and hence d*d < d*(n/d) = n, so d < sqrt(n).
    //
    // If n is composite, at least one of its nontrivial divisors is
    // not larger than sqrt(n).
    // That means we can stop our loop at sqrt(n). First BIG step.
    //
    // This improvement reduces the complexity of the algorithm.
    // Testing primality of n has dropped from O(n) to O(sqrt(n)),
    // so the overall complexity of finding the n-th prime has
    // dropped from O(n^2 * log n) to O(n^1.5 * sqrt(log n)) -
    // or so, I haven't done it properly and may have gotten
    // the exponent of the log factor wrong.
    private static boolean isPrime(int n) {
        int m = (int)Math.sqrt(n) + 1;
        for(int i = 2; i < m; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int nthPrime(int n) {
        int candidate, count;
        for(candidate = 2, count = 0; count < n; ++candidate) {
            if (isPrime(candidate)) {
                ++count;
            }
        }
        return candidate-1;
    }

    public static void main(String[] args) {
        for(int i = 0; i < args.length; ++i) {
            try {
                int target = Integer.parseInt(args[i]);
                long start = System.currentTimeMillis();
                int prime = nthPrime(target);
                long end = System.currentTimeMillis();
                System.out.println(String.format("Prime No. %8d: %10d\nTime: %8dms",target,prime,(end-start)));
            }
            catch(NumberFormatException nfe) {
                System.out.println("Bad input: " + args[i]);
            }
        }
    }
}

