/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cssi.paillier.interfaces;

import java.math.BigInteger;
import java.security.PrivateKey;

/**
 *
 * @author nc
 */
public interface PaillierPrivateKey extends PaillierKey, PrivateKey {
  /**
   * Return the secret mu
   * @return
   */
  BigInteger getMu();
  /**
   * Returns the secret lambda
   * @return
   */
  BigInteger getLambda();

  /**
   * Returns the p used to create this key
   * @return
   */
  BigInteger getP();

  /**
   * Returns the q used to create this key
   * @return
   */
  BigInteger getQ();

  BigInteger getG();
}
