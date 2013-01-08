/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cssi.paillier.interfaces;

import java.math.BigInteger;

/**
 *
 * @author nc
 */
public interface PaillierKey {

  /**
   * Returns the public modulus n
   * @return
   */
  BigInteger getN();
}
