public class NthPrime_09_Sieve_Odd_Bit{

    // Further space reduction. A boolean takes at least a full
    // byte, but encodes only one bit of information. Packing
    // the flags improves cache locality so much that we're
    // faster although the access becomes more complicated and
    // slower. Readability doesn't increase, though.
    public static int nthPrime(int n) {
        if (n < 2) return 2;
        if (n == 2) return 3;
        int limit, root, count = 1;
        limit = (int)(n*(Math.log(n) + Math.log(Math.log(n)))) + 3;
        root = (int)Math.sqrt(limit) + 1;
        limit = (limit-1)/2;
        // Yeah, I could use a BitSet, but coming from C, an int
        // array seems more natural ;) (Well, there's another point too.)
        int dim = (limit+31) >> 5;
        root = root/2 - 1;
        // int arrays are initialised to 0, so composites are marked
        // with 1 bits. In addition to saving the initialisation pass,
        // that makes marking a tad easier (word |= (1 << index) instead of
        // word &= ~(1 << index)).
        int[] sieve = new int[dim];
        for(int i = 0; i < root; ++i) {
            if ((sieve[i >> 5] & (1 << (i&31))) == 0) {
                ++count;
                for(int j = 2*i*(i+3)+3, p = 2*i+3; j < limit; j += p) {
                    sieve[j >> 5] |= 1 << (j&31);
                }
            }
        }
        int p;
        for(p = root; count < n; ++p) {
            if ((sieve[p >> 5] & (1 << (p&31))) == 0) {
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
