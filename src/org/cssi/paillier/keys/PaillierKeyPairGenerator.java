/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cssi.paillier.keys;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGeneratorSpi;
import java.security.SecureRandom;
import org.cssi.numbers.CryptoNumbers;
import org.cssi.paillier.interfaces.PaillierPrivateKey;
import org.cssi.paillier.interfaces.PaillierPublicKey;

/**
 *
 * @author nc
 */
public final class PaillierKeyPairGenerator extends KeyPairGeneratorSpi {

  private int modulusLength;
  private SecureRandom secRan;
  private final BigInteger ONE = BigInteger.ONE;

  private static final int
          KEYSIZE_MIN = 8,
          KEYSIZE_DEFAULT = 64,
          KEYSIZE_MAX = 2048;

  private boolean initialized = false;

  /**
   * Default initialization: Modulus n length = 64
   */
  public PaillierKeyPairGenerator() {
    super();
  }

  /**
   * Initializes this
   * <code>KeyPairGenerator</code>
   *
   * @param modLen Bit length of modulus n
   * @param random
   */
  @Override
  public void initialize(int keySize, SecureRandom random) {
    if(keySize < KEYSIZE_MIN || keySize > KEYSIZE_MAX)
      throw new IllegalArgumentException("Invalid keysize (" + keySize + ")");
    this.modulusLength = keySize;
    this.secRan = random;
    this.initialized = true;
  }

  /**
   * Default initializer
   */
  private void init() {
    initialize(KEYSIZE_DEFAULT, new SecureRandom());
  }
  /**
   *
   * @return
   */
  @Override
  public KeyPair generateKeyPair() {
    if(!initialized) {
      init();
    }
    BigInteger p, q;
    BigInteger n, nSquare;
    BigInteger lambda;
    BigInteger g;
    BigInteger mu;
    p = CryptoNumbers.generateRandomPrime(modulusLength / 2, secRan);
    do {
      // while gcd(pq, (p-1)(q-1) != 1
      q = CryptoNumbers.generateRandomPrime(modulusLength / 2, secRan);
    } while (!CryptoNumbers.paillierTestPrimes(p, q));
    n = p.multiply(q);
    nSquare = n.pow(2);
    lambda = CryptoNumbers.carmichael(p, q);
    do {
      // generate a generator of Z_{n^2}^*
      g = CryptoNumbers.genRandomZStarNSquare(nSquare, secRan);
      // calculate the modular inverse of paillier
      mu = calcModInv(g, lambda, n, nSquare);
    } while (mu == null);
    PaillierPublicKey pubKey = new PaillierPublicKeyImpl(n, g);
    PaillierPrivateKey privKey = new PaillierPrivateKeyImpl(p, q, g, mu, lambda);
    return new KeyPair(pubKey, privKey);
  }

  /**
   * 
   * @param g
   * @param lambda
   * @param n
   * @param nSquare
   * @return
   */
  protected BigInteger calcModInv(BigInteger g, BigInteger lambda, BigInteger n,
                                  BigInteger nSquare) {
    try {
      // l = L(g^lambda mod n^2)^-1 mod n
      BigInteger l = (CryptoNumbers.L(g.modPow(lambda, nSquare), n)).modInverse(
              n);
      return l;
    }
    catch (ArithmeticException e) {
      return null;
    }
  }
}
