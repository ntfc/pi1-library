/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cssi.paillier.keys;

import org.cssi.paillier.spec.PaillierPublicKeySpec;
import org.cssi.paillier.spec.PaillierPublicKeyBetaSpec;
import org.cssi.paillier.spec.PaillierPrivateKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactorySpi;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 *
 * @author nc
 */
public class PaillierKeyFactory extends KeyFactorySpi {

  public PaillierKeyFactory() {
    super();
  }

  @Override
  protected PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
    // TODO: replace PaillierPublicKeySpec with a new Spec
    if(keySpec instanceof PaillierPublicKeySpec) {
      PaillierPublicKeySpec s = (PaillierPublicKeySpec) keySpec;
      return new PaillierPublicKeyImpl(s.getN(), s.getG());
    }
    else if(keySpec instanceof PaillierPublicKeyBetaSpec) {
      PaillierPublicKeyBetaSpec s = (PaillierPublicKeyBetaSpec) keySpec;
      return new PaillierPublicKeyImpl(s.getEncoded());
    }
    else {
      throw new InvalidKeySpecException("KeySpec " + keySpec.getClass() + " not supported.");
    }
  }

  @Override
  protected PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
    if(keySpec instanceof PaillierPrivateKeySpec) {
      PaillierPrivateKeySpec s = (PaillierPrivateKeySpec) keySpec;
      return new PaillierPrivateKeyImpl(s.getP(), s.getQ(), s.getN(), s.getG(), s.getMu(), s.getLambda());
    }
    else {
      throw new InvalidKeySpecException("KeySpec " + keySpec.getClass() + " not supported.");
    }
  }

  @Override
  protected <T extends KeySpec> T engineGetKeySpec(Key key,
                                                   Class<T> keySpec) throws InvalidKeySpecException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  protected Key engineTranslateKey(Key key) throws InvalidKeyException {
    throw new UnsupportedOperationException("Not supported yet.");
  }


}
