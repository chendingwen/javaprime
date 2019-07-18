public class NthPrime_01_Naive{

    // A fully naive primality test using trial division.
    // In school we have learned that a prime is a number
    // (greater than 1) which is divisible only by 1 and by itself.
    // This tests all candidate divisors.
    // The argument must be greater than 1, no input checking is performed.
    private static boolean isPrime(int n) {
        for(int i = 2; i < n; ++i) {
            if (n % i == 0) {
                // We are naive, but not stupid, if
                // the number has a divisor other
                // than 1 or itself, we return immediately.
                return false;
            }
        }
        return true;
    }

    // Find the n-th prime.
    // The argument muust be positive and smaller than 105097566
    // (2^31-1 is the 105097565-th prime number).
    // No input checking is performed.
    public static int nthPrime(int n) {
        int candidate, count;
        for(candidate = 2, count = 0; count < n; ++candidate) {
            if (isPrime(candidate)) {
                ++count;
            }
        }
        return candidate-1;
    }

    // Find the n-th prime for all command line arguments and time how long
    // that takes.
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
