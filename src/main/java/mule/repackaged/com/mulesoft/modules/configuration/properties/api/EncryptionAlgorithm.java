// 
// Decompiled by Procyon v0.5.36
// 

package mule.repackaged.com.mulesoft.modules.configuration.properties.api;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;

import mule.repackaged.com.mulesoft.modules.configuration.properties.internal.jce.factories.AsymmetricEncrypterBuilder;
import mule.repackaged.com.mulesoft.modules.configuration.properties.internal.jce.factories.SymmetricEncrypterBuilder;

public enum EncryptionAlgorithm
{
    AES(16, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    Blowfish(1, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    DES(8, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    DESede(16, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    Camellia(16, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    CAST5(1, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    CAST6(1, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    Noekeon(16, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    Rijndael(16, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    SEED(16, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    Serpent(16, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    Skipjack(16, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    TEA(16, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    Twofish(8, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    XTEA(16, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    RC2(1, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    RC5(1, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    RC6(1, algorithm -> new SymmetricEncrypterBuilder(algorithm)), 
    RSA(16, algorithm -> new AsymmetricEncrypterBuilder());
    
    private int minSize;
    private EncrypterBuilderFactory factory;
    
    private EncryptionAlgorithm(final int minSize, final EncrypterBuilderFactory factory) {
        this.minSize = minSize;
        this.factory = factory;
    }
    
    public int getMinKeySize() {
        return this.minSize;
    }
    
    public int getMaxKeySize() {
        try {
            return Cipher.getMaxAllowedKeyLength(this.name()) / 8;
        }
        catch (NoSuchAlgorithmException e) {
            return 0;
        }
    }
    
    public EncrypterBuilder getBuilder() {
        return this.factory.createFor(this);
    }
}
