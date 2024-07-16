package com.penske.cf.api.cv.util;

public interface Decryptor {

    Object decrypt(byte[] byteArray);

    String decrypt(String string);
    
}
