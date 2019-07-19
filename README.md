# javaprime singleton
Consider making the class a singleton so that computations can be shared. There may be lock contention issues, though.

Input Validation
getNextPrime() could be lenient: why require the parameter to be prime itself? It seems reasonable to ask, "What is the next prime after 14?" Also, why require the input to be positive? The next prime after -50 is 2.
validatePositive() and validateOutOfBound() do essentially the same thing, with a slightly different error message. 
Only getNthPrime() and computePrimesUptoN() need to check that they have positive parameters. 

Prime distribution theory
The nth prime should be approximately pn ≈ n ln(n). You can use that estimate in getNthPrime().
What's the probable gap between successive prime numbers? The average gap is ln(p), so you could take that into consideration instead of blindly expanding by 100 at a time.
Expanding 100 at a time might have better overflow behaviour, though. I haven't thought that through.


Recommended tests
Assert.assertEquals(Arrays.asList(), primeUtil.getPrimesUptoN(-5)); — to check for the more lenient validation
Assert.assertEquals(17, primeUtil.getNextPrime(15)); — to check for the more lenient validation
Assert.assertEquals(30529, primeUtil.getNextPrime(30526)); — to see what happens with a sudden large expansion of the sieve


