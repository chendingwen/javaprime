public class NthPrime_06_Sqrt_Primes{

    // Pursuing the wheel idea - which doesn't reduce the complexity
    // of the algorithm, only the constant factors - further, in our
    // particular setting, we can get an algorithmic improvement.
    // Since we find all primes below the target, we can store them
    // as we find them and use only primes as candidate divisors.
    // For trial division, this is optimal, we never divide by a number
    // that has previously been determined not to be a divisor of n.
    // Reduces the complexity of the algorithm by a factor of log n or so
    // compared to the wheel.
    private static boolean isPrime(int n, int[] primes, int primeCount) {
        int m = (int)Math.sqrt(n) + 1;
        for(int i = 0, p; i < primeCount && (p = primes[i]) < m; ++i) {
            if (n % p == 0) {
                return false;
            }
        }
        return true;
    }

    public static int nthPrime(int n) {
        if (n < 2) return 2;
        if (n == 2) return 3;
        if (n == 3) return 5;
        // We're skipping multiples of 2 or 3, so we can cheat and never test
        // our candidates for divisibility by those. Thus we start with 5 as
        // our first prime and have to adjust the target accordingly.
        n -= 2;
        // Wasting space, we never divide by a prime that large, but
        // it's simpler to store all and reaching the range where
        // space becomes a problem would still take far too long.
        int[] primes = new int[n];
        primes[0] = 5;
        int candidate, count, step = 2;
        for(candidate = 7, count = 1; count < n; step = 6-step, candidate += step) {
            if (isPrime(candidate,primes,count)) {
                primes[count] = candidate;
                ++count;
            }
        }
        return primes[n-1];
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
