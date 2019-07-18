public class NthPrime_02_Half{

    // The fully naive trial division is dreadfully slow.
    // The first step to a better algorithm is the realisation that
    // none of the numbers between n/2 and n can be a divisor of n,
    // so we can stop testing at n/2, reducing the running time by
    // roughly half. This is a surprisingly common implementation.
    private static boolean isPrime(int n) {
        int m = n/2 + 1;
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

