/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cssi.paillier.cipher;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import org.cssi.numbers.CryptoNumbers;
import org.cssi.paillier.interfaces.PaillierPrivateKey;
import org.cssi.paillier.interfaces.PaillierPublicKey;

/**
 *
 * @author nc
 */
public class PaillierTrapdoor extends Paillier {
  public static final int CODE = 0x02;
  /**
   * Implementation of a Paillier cryptosystem with trapdoor
   */
  public PaillierTrapdoor() {
    super();
  }

  @Override
  public int getCODE() {
    return CODE;
  }

  @Override
  public BigInteger enc(PublicKey key, BigInteger m, SecureRandom random)
          throws PaillierException, InvalidKeyException {
    if (!key.getAlgorithm().equals("Paillier")) {
      throw new InvalidKeyException("PublicKey algorithm must be Paillier. "
              + "Found " + key.getAlgorithm() + " instead.");
    }

    BigInteger n = ((PaillierPublicKey) key).getN();
    BigInteger nSquare = ((PaillierPublicKey)key).getNSquare();

    // if m is not in Z_n^2
    if (m.compareTo(BigInteger.ZERO) < 0 || m.compareTo(nSquare) >= 0) {
      throw new PaillierException("Message m must be in Z_n^2");
    }
    // k in Z_n^*
    BigInteger k = CryptoNumbers.genRandomZStarN(n, random);

    return enc(key, m, k);
  }

  @Override
  public BigInteger enc(PublicKey key, BigInteger m, BigInteger k)
          throws PaillierException, InvalidKeyException {
    if (!key.getAlgorithm().equals("Paillier")) {
      throw new InvalidKeyException("PublicKey algorithm must be Paillier. "
              + "Found " + key.getAlgorithm() + " instead.");
    }

    BigInteger n, nSquare, g;
    n = ((PaillierPublicKey) key).getN();
    nSquare = ((PaillierPublicKey) key).getNSquare();
    g = ((PaillierPublicKey) key).getG();
    // if m is not in Z_n^2
    if (m.compareTo(BigInteger.ZERO) < 0 || m.compareTo(nSquare) >= 0) {
      throw new PaillierException("Message m must be in Z_n^2");
    }
    // k in Z_n^*
    if (k.compareTo(n) >= 0 || k.gcd(n).intValue() != 1) {
      throw new PaillierException("Random parameter k is not in Z_n^*");
    }

    // divide m such that m = m1 + n*m2
    BigInteger aux[], m1, m2;
    aux = m.divideAndRemainder(n);
    m1 = aux[1];
    m2 = aux[0];

    BigInteger c = (g.modPow(m1, nSquare)).multiply(m2.modPow(n, nSquare)).mod(
            nSquare);
    return c;
  }

  @Override
  public BigInteger dec(PrivateKey key, BigInteger c) throws
          PaillierException, InvalidKeyException {
    if (!key.getAlgorithm().equals("Paillier")) {
      throw new InvalidKeyException("PrivateKey algorithm must be Paillier. "
              + "Found " + key.getAlgorithm() + " instead.");
    }

    BigInteger n, nSquare, lambda, mu, g;
    n = ((PaillierPrivateKey) key).getN();
    nSquare = ((PaillierPrivateKey) key).getNSquare();
    mu = ((PaillierPrivateKey) key).getMu();
    lambda = ((PaillierPrivateKey) key).getLambda();
    g = ((PaillierPrivateKey) key).getG();
    // if c is not in Z_{n^2}^*
    if (c.compareTo(BigInteger.ZERO) < 0 || c.compareTo(nSquare) >= 0) {
      throw new PaillierException("Ciphered text must be in Z_{n^2}^*");
    }
    //m = (L(c^lambda mod n^2) * mu) mod n
    BigInteger l = CryptoNumbers.L(c.modPow(lambda, nSquare).multiply(mu), n);

    BigInteger l1 = CryptoNumbers.L(c.modPow(lambda, nSquare), n);
    BigInteger l2 = CryptoNumbers.L(g.modPow(lambda, nSquare), n);
    BigInteger m1 = l1.divide(l2).mod(n);
    BigInteger cc = (c.multiply(BigInteger.ONE.divide(m1))).mod(n);
    BigInteger m2 = cc.modPow(n.modInverse(lambda), n);
    return m1.add(m2);
  }

}
