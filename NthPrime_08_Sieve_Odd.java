public class NthPrime_08_Sieve_Odd{

    // First optimisation of the sieve.
    // We remove even numbers from the sieve, since they are
    // never prime except for 2.
    // This yields a speedup in two ways. Firstly, fewer numbers are ticked
    // off, and secondly the sieve occupies only half as much space,
    // leading to better cache locality.
    //
    // Entry k in the array represents the number 2*k+3, so we have to do
    // a bit of arithmetic to get the indices right.
    public static int nthPrime(int n) {
        if (n < 2) return 2;
        if (n == 2) return 3;
        int limit, root, count = 1;
        limit = (int)(n*(Math.log(n) + Math.log(Math.log(n)))) + 3;
        root = (int)Math.sqrt(limit) + 1;
        limit = (limit-1)/2;
        root = root/2 - 1;
        boolean[] sieve = new boolean[limit];
        for(int i = 0; i < root; ++i) {
            if (!sieve[i]) {
                ++count;
                for(int j = 2*i*(i+3)+3, p = 2*i+3; j < limit; j += p) {
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
        return 2*p+1;
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
