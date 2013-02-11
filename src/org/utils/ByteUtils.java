/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.utils;

import java.math.BigInteger;

/**
 *
 * @author nc
 */
public class ByteUtils {

  private static final int INT_LENGTH_IN_BYTES = 4;

  public static byte[] arrayBytetoByte(byte[] ... a) {
    if(a.length == 0) {
      throw new IllegalArgumentException("You must supply at least one BigInteger");
    }
    int[] bytesLength = new int[a.length];
    int len = 0;
    for(int i = 0; i < a.length; i++) {
      bytesLength[i] = a[i].length;
      len += INT_LENGTH_IN_BYTES + bytesLength[i];
    }
    if(len > Integer.MAX_VALUE) {
      // TODO: ....
      throw new OutOfMemoryError("Could not send the array of BigIntegers");
    }

    byte[] res = new byte[INT_LENGTH_IN_BYTES + (int)len];
    System.arraycopy(intToByte(a.length), 0, res, 0, INT_LENGTH_IN_BYTES);
    for(int i = 0, offset = INT_LENGTH_IN_BYTES; i < a.length; i++) {
      // append byte[] length
      System.arraycopy(intToByte(bytesLength[i]), 0, res, offset, INT_LENGTH_IN_BYTES);
      // append BigInteger
      System.arraycopy(a[i], 0, res, offset + INT_LENGTH_IN_BYTES, bytesLength[i]);
      offset += bytesLength[i] + INT_LENGTH_IN_BYTES;
    }
    return res;
  }
  /**
   * Sends an array of BigInteger through the channel
   * <p>
   * The first INT_LENGTH_IN_BYTES positions is the number of BigInteger's to be
   * transfered. Then, for every number, the first INT_LENGTH_IN_BYTES bytes is
   * the size of the number, and the rest is the number. This is repeated for
   * every number in the array.
   * @param a
   * @return the array that was created
   */
  public static byte[] arrayBigIntegerToByte(BigInteger... a) {
    if(a.length == 0) {
      throw new IllegalArgumentException("You must supply at least one BigInteger");
    }

    int[] bigIntLengths = new int[a.length];
    byte[][] bigInts = new byte[a.length][];
    int len = 0; // length of the array of numbers
    for(int i = 0; i < a.length; i++) {
      // first INT_LENGTH_IN_BYTES bytes = number length
      bigInts[i] = a[i].toByteArray();
      bigIntLengths[i] = bigInts[i].length;
      len += INT_LENGTH_IN_BYTES + bigIntLengths[i]; // INT_LENGTH_IN_BYTES to save the length of the number
    }

    if(len > Integer.MAX_VALUE) {
      // TODO: ....
      throw new OutOfMemoryError("Could not send the array of BigIntegers");
    }
    byte[] res = new byte[INT_LENGTH_IN_BYTES + (int)len];
    System.arraycopy(intToByte(a.length), 0, res, 0, INT_LENGTH_IN_BYTES);
    for(int i = 0, offset = INT_LENGTH_IN_BYTES; i < a.length; i++) {
      // append BigInteger length
      System.arraycopy(intToByte(bigIntLengths[i]), 0, res, offset, INT_LENGTH_IN_BYTES);
      // append BigInteger
      System.arraycopy(bigInts[i], 0, res, offset + INT_LENGTH_IN_BYTES, bigIntLengths[i]);
      offset += bigIntLengths[i] + INT_LENGTH_IN_BYTES;
    }

    return res;
  }

  public static byte[][] byteToArrayByte(byte[] a) {
    byte[] lenByteA = new byte[INT_LENGTH_IN_BYTES];
    System.arraycopy(a, 0, lenByteA, 0, INT_LENGTH_IN_BYTES);
    int len = byteToInt(lenByteA);
    byte[][] res = new byte[len][];
    int offset = INT_LENGTH_IN_BYTES;

    for(int i = 0; i < len; i++) {
      byte[] lenByte = new byte[INT_LENGTH_IN_BYTES];
      System.arraycopy(a, offset, lenByte, 0, INT_LENGTH_IN_BYTES);
      int lenByteArray = byteToInt(lenByte);
      byte[] byteArray = new byte[lenByteArray];
      System.arraycopy(a, offset+INT_LENGTH_IN_BYTES, byteArray, 0, lenByteArray);

      res[i] = byteArray;

      offset += INT_LENGTH_IN_BYTES + lenByteArray;
    }

    return res;
  }

  public static BigInteger[] byteToArrayBigInteger(byte[] a) {
    // receive number of BigIntegers
    byte lenByte[] = new byte[INT_LENGTH_IN_BYTES];
    System.arraycopy(a, 0, lenByte, 0, INT_LENGTH_IN_BYTES);
    int len = byteToInt(lenByte);
    BigInteger[] res = new BigInteger[len];
    int offset = INT_LENGTH_IN_BYTES;

    for(int i = 0; i < len; i++) {
      byte[] lenBigIntegerByte = new byte[INT_LENGTH_IN_BYTES];
      System.arraycopy(a, offset, lenBigIntegerByte, 0, INT_LENGTH_IN_BYTES);
      int lenBigInteger = byteToInt(lenBigIntegerByte);
      byte[] bigInteger = new byte[lenBigInteger];
      System.arraycopy(a, offset+INT_LENGTH_IN_BYTES, bigInteger, 0, lenBigInteger);

      res[i] = new BigInteger(bigInteger);

      offset += INT_LENGTH_IN_BYTES + lenBigInteger;
    }

    return res;
  }

  /**
   * Converts an integer into its 4-byte equivalent in Big-endian form.
   * @param i
   * @return
   */
  public static byte[] intToByte(int i) {
    return new byte[] {(byte)(i>>24), (byte)(i>>16), (byte)(i>>8), (byte)i};
  }

  public static int byteToInt(byte[] a) {
    return ((a[0] & 0xff) << 24) | ((a[1] & 0xff)<< 16) |
					((a[2] & 0xff) << 8) | (a[3] & 0xff);
  }

}
