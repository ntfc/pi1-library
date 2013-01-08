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

  public PaillierPrivateKeySpec(BigInteger n, BigInteger mu, BigInteger lambda) {
    this.n = n;
    this.mu = mu;
    this.lambda = lambda;
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
  

}
