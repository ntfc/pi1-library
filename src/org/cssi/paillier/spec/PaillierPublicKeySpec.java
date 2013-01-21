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
public final class PaillierPublicKeySpec implements KeySpec {
  private BigInteger n;
  private BigInteger g;

  public PaillierPublicKeySpec(BigInteger n, BigInteger g) {
    this.n = n;
    this.g = g;
  }

  public BigInteger getN() {
    return n;
  }

  public BigInteger getG() {
    return g;
  }

}
