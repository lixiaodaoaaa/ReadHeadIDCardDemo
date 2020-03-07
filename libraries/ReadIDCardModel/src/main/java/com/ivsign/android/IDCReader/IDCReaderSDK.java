package com.ivsign.android.IDCReader;

public class IDCReaderSDK {

    private IDCReaderSDK() {
    }

    public static native int wltInit(String var0);

    public static native int wltGetBMP(byte[] var0, byte[] var1);

    static {
        System.loadLibrary("wltdecode");
    }

}
