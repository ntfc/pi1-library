/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cssi.numbers;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * In this class you have some methods used in group theory, such as generate a
 * random prime number, a random integer in <code>Z_{n}^*</code> or to test if
 * two primes are valid parameters for a given cryptosystem
 *
 * @author nc
 */
public class CryptoNumbers {

  private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  /**
   * Generate a random prime, with max value equal to <code>(2^bitLength)-1</code>
   *
   * @param bitLength
   * @param random SecureRandom
   * @return the new random prime
   */
  public static BigInteger generateRandomPrime(int bitLength,
                                               SecureRandom random) {
    return BigInteger.probablePrime(bitLength, random);
  }

  /**
   * Generate a random integer in <code>Z_n</code>
   *
   * @param n
   * @param random
   * @return
   */
  public static BigInteger genRandomZN(BigInteger n, SecureRandom random) {
    BigInteger g;
    int modLength = n.bitCount();
    do {
      log.logp(Level.CONFIG, "Primes.java", "genRandomZN",
               "Prime generation iteration");
      g = new BigInteger(modLength, random);
    } while (g.compareTo(BigInteger.ZERO) <= 0 || g.compareTo(n) >= 0);
    return g;
  }

  /**
   * Generate a random integer in <code>Z_n^*</code>
   *
   * @param n
   * @param random
   * @return
   */
  public static BigInteger genRandomZStarN(BigInteger n, SecureRandom random) {
    BigInteger g;
    int modLength = n.bitLength();
    do {
      log.logp(Level.CONFIG, "Primes.java", "genRandomZStarN",
               "Prime generation iteration");
      g = new BigInteger(modLength, random);
    } while (g.compareTo(n) >= 0 || g.gcd(n).intValue() != 1);
    return g;
  }

  /**
   * Generate a random integer in <code>Z_{n^2}^*</code>
   *
   * @param nSquare
   * @param random
   * @return
   */
  public static BigInteger genRandomZStarNSquare(BigInteger nSquare, SecureRandom random) {
    BigInteger g;
    int modLength = nSquare.bitLength();
    do {
      log.logp(Level.CONFIG, "Primes.java", "genRandomZStarNSquare",
               "Prime generation iteration");
      g = new BigInteger(modLength, random);
    } while (g.compareTo(nSquare) >= 0 || g.gcd(nSquare).intValue() != 1);
    return g;
  }

  /**
   * Tests if two prime p and q obey to: <code>gcd(pq, (p-1)(q-1) = 1</code>
   * This is must be true for p and q of Paillier
   *
   * @param p
   * @param q
   * @return true if <code>gcd(pq, (p-1)(q-1) = 1</code>, false otherwise
   */
  public static boolean paillierTestPrimes(BigInteger p, BigInteger q) {
    BigInteger pMinusOne, qMinusOne;
    pMinusOne = p.subtract(BigInteger.ONE);
    qMinusOne = q.subtract(BigInteger.ONE);
    // return gcd(pq, (p-1)(q-1) = 1
    return p.multiply(q).gcd(pMinusOne.multiply(qMinusOne)).intValue() == 1;
  }

  /**
   * Calculate <code>L(u) = (u-1)/n</code>
   * This function is used on Paillier
   *
   * @param u
   * @param n
   * @return
   */
  public static BigInteger L(BigInteger u, BigInteger n) {
    return (u.subtract(BigInteger.ONE)).divide(n);
  }

  /**
   * Compute carmichael's function <code>lcm(p-1, q-1)</code>
   *
   * @param p
   * @param q
   * @return
   */
  public static BigInteger carmichael(BigInteger p, BigInteger q) {
    BigInteger pMinusOne, qMinusOne;
    pMinusOne = p.subtract(BigInteger.ONE);
    qMinusOne = q.subtract(BigInteger.ONE);
    return pMinusOne.multiply(qMinusOne).divide(pMinusOne.gcd(qMinusOne));
  }
}
