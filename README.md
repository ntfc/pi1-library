# NOTES
 - The implementation classes (ie. `org.cssi.paillier.PaillierPrivateKeyImpl`) must
be `final class ClassNameImpl` so that they are only visible for the classes in
the same package (ie. in `org.cssi.paillier`)
 - The specifications classes (ie. `org.cssi.paillier.spec.PaillierPrivateKeySpec`)
can have the Key parameters (g, n, p, mu, etc) as instance variables. Those
variables can be obtained with `getG()`, `getN()`, etc
 - The interfaces in `org.cssi.paillier.interfaces` only have the methods (besides
the ones inherited from `PublicKey` or `PaillierKey`) specific from that class
   - `org.cssi.paillier.interfaces.PaillierPublicKey` only has the `getG()` method

# USAGE
- to add the provider do: `Security.addProvider(new CssiProvider());
- KeyPairGenerator for Paillier
  - Usage: `KeyPairGenerator.getInstance("Paillier", "CSSI");`
  - default modulus n bit length: 64
  - KeyPair = `<PaillierPublicKey, PaillierPrivateKey>`
- Paillier cryptosystem
  - instanciate `PaillierSimple()` or one of the variantes, and then use:
   - `enc(key,message,secureRandom)`
   - `enc(key,message,randomNumberK)`
   - `dec(key,ciphertext)`
- KeyFactory
  - `KeyFactory.getInstance("Paillier", "CSSI");`
  - `PaillierPrivateKeySpec spec = new PaillierPrivateKeySpec();`
  - `PublicKey p = keyFactory.generatePublic(spec);`
- `org.cssi.numbers.CryptoNumbers` contain the methods that generate random 
numbers and operate on (crypto) numbers

# TODO:
- see http://javasourcecode.org/html/open-source/jdk/jdk-6u23/sun/security/rsa/RSAKeyFactory.java.html
- DOUBT: move `org.utils` to [pi1-voting](https://github.com/ntfc/pi1-voting) ?? 
- improve the KeyImpl:
  - getEncoded() here
  - http://www.docjar.org/html/api/cryptix/jce/provider/rsa/RSAPublicKeyImpl.java.html
  - http://javasourcecode.org/html/open-source/jdk/jdk-6u23/sun/security/rsa/RSAPublicKeyImpl.java.html
  - in the impl classes, create two constructors: one with all the BigInteger
params and another with byte[] encoded
    -http://javasourcecode.org/html/open-source/jdk/jdk-6u23/sun/security/rsa/RSAPublicKeyImpl.java.html
- finish KeySpecs (`org.cssi.paillier.spec`)
  - after all, the `Paillier{Private,Public}KeySpec` are ok. but better check this.
  - http://javasourcecode.org/html/open-source/jdk/jdk-6u23/java/security/spec/RSAPrivateKeySpec.java.html
- improve `org.cssi.paillier.cipher`. Do it like in jca, call init() first, and
then doFinal() method
- create methods to add and multiply ciphertexts, maybe in PaillierSimple.java
  - make add and multiply abstract in `Paillier.java`
