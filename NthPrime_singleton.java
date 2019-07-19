public synchronized int getNthPrime(int n) {
    validatePositive(n); 

    // The nth prime should be approximately n ln(n).  Let's overestimate by 20%.
    assert primes.size() >= 5;
    for (int size = (int)(1.2 * n * Math.log(n)); primes.size() < n; size = (int)(1.2 * size)) {
        computePrimesUptoN(size);
    }
    return primes.get(n - 1);
}

/**
 * Given an input number, return the next prime.
 * ie, if n == 13 or n == 14 then return 17.
 * 
 * @param n         the number whose next prime number should be found
 * @return          the next prime of the input.
 */
public synchronized int getNextPrime(int n) {
    if (n < 2) {
        return 2;
    }
    int primeIndex;
    while (Math.abs(primeIndex = Collections.binarySearch(primes, n)) >= primes.size()) {
        int size = Math.max(n, primes.get(primes.size() - 1));
        computePrimesUptoN(size + (int)(1.2 * Math.log(size)));
    }
    return primes.get((primeIndex < 0 ? ~primeIndex : primeIndex + 1));
}

private List<Integer> computePrimesUptoN(int n) {
    if (n <= 0) {
        throw new ArithmeticException("Arithmetic overflow");
    }

    // composite is name of the sieve, ie nothing else but the sieve.
    // optimizing the sieve size, but trimming it to "n - cacheprime"
    boolean[] composites = new boolean[n - cachedMaxPrime];
    int root = (int)Math.sqrt(n); 

    // loop through all "first prime upto max-cached-primes"

    /*
     * We need i <= root, and NOT i < root
     * Try cache of {3, 5, 7} and n of 50. you will really why
     */
    for (int i = 1; i < primes.size() && primes.get(i) <= root; i++) {
        int prime = primes.get(i);
        // get the first odd multiple of this prime, greater than max-prime
        int firstPrimeMultiple = prime * ((cachedMaxPrime / prime + 1) | 1);
        filterComposites(composites, prime, firstPrimeMultiple, n);
    }

    // loop through all primes in the range of max-cached-primes upto root.
    for (int prime = cachedMaxPrime + 2; prime < root; prime = prime + 2) {
        if (!composites[prime]) {
            // selecting all the prime numbers.
            filterComposites(composites, prime, prime, n);
        }
    }

    // by doing i + 2, we essentially skip all even numbers
    // also skip cachedMaxPrime, since quite understandably its already cached.
    for (int i = 1; i < composites.length; i = i + 2) {
        if (!composites[i]) {
            primes.add(i + cachedMaxPrime + 1);
        }
    }

    cachedMaxPrime = primes.get(primes.size() - 1);
    return primes;
}
