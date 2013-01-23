/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cssi.paillier.keys;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.cssi.paillier.interfaces.PaillierPublicKey;

/**
 *
 * @author nc
 */
/*public*/ final class PaillierPublicKeyImpl implements PaillierPublicKey {
  private final BigInteger n, g, nSquare;

  public PaillierPublicKeyImpl(BigInteger n, BigInteger g) {
    this.n = n;
    this.g = g;
    this.nSquare = n.pow(2);
  }
  public PaillierPublicKeyImpl(byte[] data) {
    byte[] len = new byte[4]; // length of n and g
    System.arraycopy(data, 0, len, 0, 4);
    int nLen = new BigInteger(len).intValue();
    System.arraycopy(data, 4, len, 0, 4);
    int gLen =  new BigInteger(len).intValue();
    byte[] pubN = new byte[nLen];
    byte[] pubG = new byte[gLen];
    System.arraycopy(data, 8, pubN, 0, nLen);
    System.arraycopy(data, 8 + nLen, pubG, 0, gLen);
    this.n = new BigInteger(pubN);
    this.g = new BigInteger(pubG);
  }

  @Override
  public BigInteger getN() {
    return this.n;
  }

  @Override
  public BigInteger getNSquare() {
    return nSquare;
  }

  @Override
  public BigInteger getG() {
    return this.g;
  }

  @Override
  public String getAlgorithm() {
    return "Paillier";
  }

  @Override
  public String getFormat() {
    return "BetaCSSI";
  }

  /**
   * Beta version: first 4 positions for modulus n size in bytes; next 4 positions
   * for g size in bytes; then just concatenate the two arrays: n || g
   *
   * @return
   */
  @Override
  public byte[] getEncoded() {
    byte[] nByte = getN().toByteArray();
    byte[] gByte = getG().toByteArray();
    ByteBuffer enc = ByteBuffer.allocate(4 + 4 + nByte.length + gByte.length);
    enc = enc.putInt(nByte.length);
    enc = enc.putInt(gByte.length);
    enc = enc.put(nByte);
    enc = enc.put(gByte);
    return enc.array();
  }

}
