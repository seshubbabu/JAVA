package com.penske.cf.api.cv.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import org.jasypt.encryption.ByteEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class DefaultEncryptorDecryptor implements Decryptor {
    
    private ByteEncryptor byteEncryptor;
    
    private StringEncryptor stringEncryptor;
    
    public DefaultEncryptorDecryptor(@Qualifier("binary") ByteEncryptor byteEncryptor,
                                     @Qualifier("string") StringEncryptor stringEncryptor) {
        this.byteEncryptor = byteEncryptor;
        this.stringEncryptor = stringEncryptor;
    }
    
    
    @Override
    public String decrypt(String string) {
        return stringEncryptor.decrypt(string);
    }
    
    @Override
    public Object decrypt(byte[] byteArray) {
        ByteArrayInputStream bis = null;
        ObjectInput in = null;
        try {
            
            byte[] decriptBytes = byteEncryptor.decrypt(byteArray);
            bis = new ByteArrayInputStream(decriptBytes);
            in = new ObjectInputStream(bis);
            return in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null)
                    bis.close();
                if (in != null)
                    in.close();
            } catch (IOException io) {
            
            }
        }
        
        return null;
    }
}
