/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cssi.paillier.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

/**
 * 
 * @author nc
 */
public final class PaillierPrivateKeySpec implements KeySpec {
  private BigInteger n;
  private BigInteger mu;
  private BigInteger lambda;
  private BigInteger p;
  private BigInteger q;

  public PaillierPrivateKeySpec(BigInteger p, BigInteger q, BigInteger n, BigInteger mu, BigInteger lambda) {
    this.n = n;
    this.mu = mu;
    this.lambda = lambda;
    this.p = p;
    this.q = q;
  }

  public BigInteger getN() {
    return n;
  }

  public BigInteger getMu() {
    return mu;
  }

  public BigInteger getLambda() {
    return lambda;
  }

  public BigInteger getP() {
    return p;
  }

  public BigInteger getQ() {
    return q;
  }
  

}
