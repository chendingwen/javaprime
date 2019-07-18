public class NthPrime_04_Sqrt_Odd{

    // While we're at it, let's think about further improvements.
    // All primes except 2 are odd, and odd numbers cannot have
    // even divisors, so let's skip these in the test.
    // This reduces the running time by another factor of about 2.
    private static boolean isPrime(int n) {
        if ((n & 1) == 0) return n == 2;
        int m = (int)Math.sqrt(n) + 1;
        for(int i = 3; i < m; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int nthPrime(int n) {
        if (n < 2) return 2;
        int candidate, count;
        for(candidate = 3, count = 1; count < n; candidate += 2) {
            if (isPrime(candidate)) {
                ++count;
            }
        }
        return candidate-2;
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

