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
  private final BigInteger n, mu, lambda, p, q, nSquare, g;

  public PaillierPrivateKeyImpl(BigInteger p, BigInteger q, BigInteger n, BigInteger g, BigInteger mu, BigInteger lambda) {
    this.n = n;
    this.g = g;
    this.mu = mu;
    this.lambda = lambda;
    this.p = p;
    this.q = q;
    this.nSquare = n.pow(2);
  }

  public PaillierPrivateKeyImpl(BigInteger p, BigInteger q, BigInteger g, BigInteger mu, BigInteger lambda) {
    this.n = p.multiply(q);
    this.mu = mu;
    this.lambda = lambda;
    this.p = p;
    this.q = q;
    this.g = g;
    this.nSquare = n.pow(2);
  }

  @Override
  public BigInteger getN() {
    return n;
  }

  @Override
  public BigInteger getNSquare() {
    return nSquare;
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
    return this.p;
  }

  @Override
  public BigInteger getQ() {
    return this.q;
  }

  public BigInteger getG() {
    return g;
  }

}
