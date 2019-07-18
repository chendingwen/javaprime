public class NthPrime_07_Sieve_Full{

    // Now we start becoming serious.
    // A simple sieve of Eratosthenes, the only optimisation -
    // already known in antiquity - is to start ticking off
    // multiples of primes at the square.
    // The complexity of sieving to the limit N is O(N * log (log N)),
    // since the n-th prime is approximately n*log n, the complexity
    // of finding the n-th prime with the sieve is O(n*log n*log(log n)).
    public static int nthPrime(int n) {
        if (n < 2) return 2;
        if (n == 2) return 3;
        if (n == 3) return 5;
        int limit, root, count = 0;
        // Approximation of the n-th prime, overestimates for n >= 3.
        limit = (int)(n*(Math.log(n) + Math.log(Math.log(n)))) + 3;
        root = (int)Math.sqrt(limit) + 1;
        boolean[] sieve = new boolean[limit];
        for(int i = 2; i < root; ++i) {
            // Since Java initialises the array to false,
            // we mark composites as true to save the extra
            // initialisation pass.
            if (!sieve[i]) {
                ++count;
                for(int j = i*i; j < limit; j += i) {
                    sieve[j] = true;
                }
            }
        }
        int p;
        for(p = root; count < n; ++p) {
            if (!sieve[p]) {
                ++count;
            }
        }
        return p-1;
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
