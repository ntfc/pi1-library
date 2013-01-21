/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cssi.paillier.keys;

import java.math.BigInteger;
import org.cssi.paillier.interfaces.PaillierPrivateKey;

/**
 *
 * @author nc
 */
/*public */final class PaillierPrivateKeyImpl implements PaillierPrivateKey {
  private final BigInteger n, mu, lambda, p, q;

  public PaillierPrivateKeyImpl(BigInteger p, BigInteger q, BigInteger n, BigInteger mu, BigInteger lambda) {
    this.n = n;
    this.mu = mu;
    this.lambda = lambda;
    this.p = p;
    this.q = q;
  }

  public PaillierPrivateKeyImpl(BigInteger p, BigInteger q, BigInteger mu, BigInteger lambda) {
    this.n = p.multiply(q);
    this.mu = mu;
    this.lambda = lambda;
    this.p = p;
    this.q = q;
  }

  @Override
  public BigInteger getN() {
    return n;
  }

  @Override
  public BigInteger getMu() {
    return mu;
  }

  @Override
  public BigInteger getLambda() {
    return lambda;
  }

  @Override
  public String getAlgorithm() {
    return "Paillier";
  }

  @Override
  public String getFormat() {
    return "BetaCSSI";
  }

  @Override
  public byte[] getEncoded() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public BigInteger getP() {
    return BigInteger.ONE;
  }

  @Override
  public BigInteger getQ() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
