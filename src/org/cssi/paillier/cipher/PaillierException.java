/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cssi.paillier.cipher;

import java.security.GeneralSecurityException;

/**
 *
 * @author nc
 */
public class PaillierException extends GeneralSecurityException {

  /**
   * Creates a new instance of <code>PaillierException</code> without detail message.
   */
  public PaillierException() {
    super();
  }


  /**
   * Constructs an instance of <code>PaillierException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public PaillierException(String msg) {
    super(msg);
  }
}
