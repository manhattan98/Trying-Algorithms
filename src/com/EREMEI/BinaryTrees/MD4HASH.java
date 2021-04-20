package com.EREMEI.BinaryTrees;
import jcifs.util.*;

public class MD4HASH implements HASHER {
    private MD4 md4;
    {
        this.md4 = new MD4();
    }

    @Override
    public byte[] getHASH(byte[] bytes) {
        this.md4.engineReset();
        this.md4.engineUpdate(bytes, 0, bytes.length);
        return this.md4.engineDigest();
    }
}
