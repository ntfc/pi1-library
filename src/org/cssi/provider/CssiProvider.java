package org.cssi.provider;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.security.Provider;

/**
 *
 * @author nc
 */
public final class CssiProvider extends Provider {

  /**
   * 
   */
  public CssiProvider() {
    super("CSSI", 1.0,
          "CSSI provider, implementing Paillier key pair generation\n"
            + "This library also provides some classes in cssi.paillier for the "
            + "Paillier encryption.");
    super.put("KeyPairGenerator.Paillier",
              org.cssi.paillier.keys.PaillierKeyPairGenerator.class.getCanonicalName());
    super.put("KeyFactory.Paillier",
              org.cssi.paillier.keys.PaillierKeyFactory.class.getCanonicalName());
  }
}
