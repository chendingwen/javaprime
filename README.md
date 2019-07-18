This is a suite of Java programmes to calculate the n-th prime. They have been written for pedagogical purposes, to illustrate the effect of several fundamental algorithmic optimisations in finding primes.

The first six are using trial division, starting with a fully naive implementation and going through some optimisation steps to reach a trial division that only tests divisibility by primes.

The next five use an Eratosthenes type sieve to find the primes to the target and therefore are much faster than the trial division programmes. None of them is optimised very much, but the last three are at least not obscenely slow.

The much faster way of finding the n-th prime by approximating it using the prime number theorem, counting the primes to that approximation with the Meissel-Lehmer algorithm and sieving forward or backward from the approximation to find the relatively few missing/excess primes is not implemented here. I have implemented it in Haskell in the arithmoi package, but I am not motivated enough to port it to Java.

Some timings in milliseconds (single runs, in particular the very short times are not to be taken seriously):

prog | 1000 | 10000 | 20000 | 100000 | 200000 | 1000000 | 10000000 | 100000000
-----+------+-------+-------+--------+--------+---------+----------+-----------
  01 |   18 |  2431 | 10498 | no run | no run |  no run |  no run  |   no run
  02 |   10 |  1213 |  5220 | no run | no run |  no run |  no run  |   no run
  03 |    5 |    17 |    44 |    499 |   1410 |   16050 |  no run  |   no run
  04 |    3 |    11 |    22 |    253 |    702 |    8057 |  no run  |   no run
  05 |    1 |     8 |    14 |    168 |    466 |    5361 |  no run  |   no run
  06 |    1 |    10 |    10 |    101 |    251 |    2476 |   68564  |  1954048
  07 |    0 |     6 |     3 |      7 |     17 |     180 |    2910  |   no run
  08 |    0 |     2 |     5 |      6 |      8 |      88 |    1288  |    18378
  09 |    0 |     4 |     4 |      5 |      9 |      57 |    1076  |    14641
  10 |    0 |     2 |     6 |      3 |      6 |      36 |     647  |     9025
  11 |    0 |     1 |     4 |      3 |      4 |      19 |     481  |     7376
  12 |    2 |    15 |    28 |    186 |    406 |    2431 |   30262  |   368748
In contrast, using the Meissel-Lehmer algorithm:

Prelude Math.NumberTheory.Primes> nthPrime $ 10^6
15485863
(0.03 secs, 4004776 bytes)
Prelude Math.NumberTheory.Primes> nthPrime $ 10^7
179424673
(0.04 secs, 7634336 bytes)
Prelude Math.NumberTheory.Primes> nthPrime $ 10^8
2038074743
(0.04 secs, 23623368 bytes)
Prelude Math.NumberTheory.Primes> nthPrime $ 10^9
22801763489
(0.10 secs, 88600552 bytes)
Prelude Math.NumberTheory.Primes> nthPrime $ 10^10
252097800623
(0.34 secs, 368720112 bytes)
Prelude Math.NumberTheory.Primes> nthPrime $ 10^11
2760727302517
(1.55 secs, 1653243064 bytes)
