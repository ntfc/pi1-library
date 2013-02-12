/*
 * Pailler, default variant
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
 * Simple Paillier implementation, with no variant
 *
 * @author nc
 */
public final class PaillierSimple extends Paillier {
  public static final int CODE = 0x01;
  /**
   * Implementation of a simple Paillier cryptosystem
   */
  public PaillierSimple() {
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
    // if m is not in Z_n
    if (m.compareTo(BigInteger.ZERO) < 0 || m.compareTo(n) >= 0) {
      throw new PaillierException("Message m must be in Z_n");
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
    // if m is not in Z_n
    if (m.compareTo(BigInteger.ZERO) < 0 || m.compareTo(n) >= 0) {
      throw new PaillierException("Message m must be in Z_n");
    }
    // k in Z_n^*
    if (k.compareTo(n) >= 0 || k.gcd(n).intValue() != 1) {
      throw new PaillierException("Random parameter k is not in Z_n^*");
    }

    BigInteger c = (g.modPow(m, nSquare)).multiply(k.modPow(n, nSquare)).mod(
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

    BigInteger n, nSquare, lambda, mu;
    n = ((PaillierPrivateKey) key).getN();
    nSquare = ((PaillierPrivateKey) key).getNSquare();
    mu = ((PaillierPrivateKey) key).getMu();
    lambda = ((PaillierPrivateKey) key).getLambda();
    // if c is not in Z_{n^2}^*
    if (c.compareTo(BigInteger.ZERO) < 0 || c.compareTo(nSquare) >= 0) {
      throw new PaillierException("Ciphered text must be in Z_{n^2}^*");
    }
    //m = (L(c^lambda mod n^2) * mu) mod n
    BigInteger l = CryptoNumbers.L(c.modPow(lambda, nSquare).multiply(mu), n);
    return l.mod(n);
  }
}
