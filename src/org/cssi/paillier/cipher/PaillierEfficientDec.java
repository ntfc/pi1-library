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

/**
 *
 * @author nc
 */
public class PaillierEfficientDec extends Paillier {
  public static final int CODE = 0x02;
  /**
   * Implementation of a simple Paillier cryptosystem
   */
  public PaillierEfficientDec() {
    super();
  }

  @Override
  public int getCODE() {
    return CODE;
  }

  @Override
  public BigInteger enc(PublicKey key, BigInteger m, SecureRandom random) throws PaillierException,
    InvalidKeyException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public BigInteger enc(PublicKey key, BigInteger m, BigInteger k) throws PaillierException,
    InvalidKeyException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public BigInteger dec(PrivateKey key, BigInteger c) throws PaillierException,
    InvalidKeyException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public BigInteger mult(PrivateKey key,
                         Set<BigInteger> l) throws PaillierException,
    InvalidKeyException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
