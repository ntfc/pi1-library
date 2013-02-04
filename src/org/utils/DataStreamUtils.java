/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;

/**
 * Read and write from/to DataStream
 *
 * @author nc
 */
public class DataStreamUtils {

  private DataInputStream dis;
  private DataOutputStream dos;

  public DataStreamUtils(InputStream is, OutputStream os) {
    this.dis = new DataInputStream(is);
    this.dos = new DataOutputStream(os);

  }

  /**
   * Write the array <code>data</code> to a DataOutputStream
   * @param data
   */
  public void writeBytes(byte[] data) throws IOException {
    int len = data.length;
    dos.writeInt(len);
    dos.flush();

    dos.write(data, 0, len);
    dos.flush();
  }

  /**
   * Read an array of byte from the DataInputStream
   * <p>
   * First, it reads an int with the number of bytes to be transfered, and then,
   * it reads the byte array with the data
   * @return
   * @throws IOException
   */
  public byte[] readBytes() throws IOException {
    int len = dis.readInt();
    byte[] data = new byte[len];
    dis.readFully(data, 0, len);
    return data;
  }

  public void close() throws IOException {
    if(this.dis != null)
      dis.close();
    if(this.dos != null)
      dos.close();
  }

  /**
   * Converts a <code>BigInteger</code> to <code>byte[]</code> and then calls
   * <code>DataStreamUtils.writeBytes(byte[])</code>
   * @param n
   */
  public void writeBigInteger(BigInteger n) throws IOException {
    writeBytes(n.toByteArray());
  }

  public BigInteger readBigInteger() throws IOException {
    byte[] data = this.readBytes();
    return new BigInteger(data);
  }

  public void writeInt(int n) throws IOException {
    dos.writeInt(n);
    dos.flush();
  }

  public int readInt() throws IOException {
    return dis.readInt();
  }

}
