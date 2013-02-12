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
import java.util.Set;
import org.cssi.paillier.interfaces.PaillierPrivateKey;

/**
 * Abstract class that define the basic methods of a Paillier cryptosystem
 *
 * @author nc
 */
public abstract class Paillier {
  private final BigInteger ONE = BigInteger.ONE;

  public abstract int getCODE();
  /**
   * Encrypts a BigInteger
   * <code>m</code>. This method generates a random
   * <code>k</code> in
   * <code>Z_{n}^*</code>, and then calls
   * <code>enc(PublicKey, BigInteger, BigInteger)</code>
   *
   * @param key Paillier <code>PublicKey</code>
   * @param m The message to be encrypted
   * @param random A <code>SecureRandom</code>
   * @return The ciphertext of message <code>m</code>
   * @throws PaillierException if <code>m</code> is bigger than modulus n
   */
  public abstract BigInteger enc(PublicKey key, BigInteger m,
                                 SecureRandom random) throws PaillierException,
          InvalidKeyException;

  /**
   * Encrypts a BigInteger
   * <code>m</code>, but the random
   * <code>k</code> is already provided
   *
   * @param key Paillier <code>PublicKey</code>
   * @param m The message to be encrypted
   * @param k A random integer, in <code>Z_{n}^</code>
   *
   * @return The ciphertext of message <code>m</code>
   * @throws PaillierException if <code>m</code> or <code>k</code> is bigger
   * than modulus n
   */
  public abstract BigInteger enc(PublicKey key, BigInteger m,
                                 BigInteger k) throws PaillierException,
          InvalidKeyException;

  /**
   * Decrypts a BigInteger
   * <code>c</code>
   *
   * @param key Paillier <code>PrivateKey</code>
   * @param c The ciphertext
   * @return The original message
   * @throws PaillierException if <code>c</code> is bigger than modulus n^2
   */
  public abstract BigInteger dec(PrivateKey key, BigInteger c) throws
          PaillierException, InvalidKeyException;

  /**
   * Applies the homomorphic property of Paillier to a List of ciphertexts
   * @param key
   * @param l
   * @return
   * @throws PaillierException
   * @throws InvalidKeyException
   */
  public BigInteger mult(PrivateKey key, Set<BigInteger> l) throws
          PaillierException, InvalidKeyException {
    if(l.isEmpty())
      return null;
    BigInteger h = BigInteger.ONE;
    for(BigInteger b : l) {
      h = h.multiply(b);
    }
    return h.mod(((PaillierPrivateKey)key).getNSquare());
  }
}
