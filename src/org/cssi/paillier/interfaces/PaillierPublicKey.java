/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cssi.paillier.interfaces;

import java.math.BigInteger;
import java.security.PublicKey;

/**
 *
 * @author nc
 */
public interface PaillierPublicKey extends PaillierKey, PublicKey {

  /**
   * Return the public generator <code>g</code>
   * @return
   */
  BigInteger getG();
}
