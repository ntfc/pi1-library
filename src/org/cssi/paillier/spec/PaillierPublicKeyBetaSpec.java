/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cssi.paillier.spec;

import java.security.spec.KeySpec;

/**
 *
 * @author nc
 */
public final class PaillierPublicKeyBetaSpec implements KeySpec {
  private final byte[] encoded;
  //see http://javasourcecode.org/html/open-source/jdk/jdk-6u23/java/security/spec/EncodedKeySpec.java.html
  /**
   * Creates a new empty instance of <code>PaillierPublicKeyBetaSpec</code>.
   */
  public PaillierPublicKeyBetaSpec(byte[] data) {
    this.encoded = data.clone();
  }

  public byte[] getEncoded() {
    return encoded.clone();
  }
}
