package org.spongycastle.crypto.test;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.engines.AESEngine;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.modes.CFBBlockCipher;
import org.spongycastle.crypto.modes.OFBBlockCipher;
import org.spongycastle.crypto.modes.SICBlockCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.encoders.Hex;
import org.spongycastle.util.test.SimpleTest;

/**
 * Test vectors from the NIST standard tests and Brian Gladman's vector set
 * <a href="http://fp.gladman.plus.com/cryptography_technology/rijndael/">
 * http://fp.gladman.plus.com/cryptography_technology/rijndael/</a>
 */
public class AESTest
    extends CipherTest
{
    private static final byte[] tData   = Hex.decode("AAFE47EE82411A2BF3F6752AE8D7831138F041560631B114F3F6752AE8D7831138F041560631B1145A01020304050607");
    private static final byte[] outCBC1 = Hex.decode("a444a9a4d46eb30cb7ed34d62873a89f8fdf2bf8a54e1aeadd06fd85c9cb46f021ee7cd4f418fa0bb72e9d07c70d5d20");
    private static final byte[] outCBC2 = Hex.decode("585681354f0e01a86b32f94ebb6a675045d923cf201263c2aaecca2b4de82da0edd74ca5efd654c688f8a58e61955b11");
    private static final byte[] outSIC1 = Hex.decode("82a1744e8ebbd053ca72362d5e570326e0b6fdaf824ab673fbf029042886b23c75129a015852913790f81f94447475a0");
    private static final byte[] outSIC2 = Hex.decode("146cbb581d9e12c3333dd9c736fbb93043c92019f78580da48f81f80b3f551d58ea836fed480fc6912fefa9c5c89cc24");
    private static final byte[] outCFB1 = Hex.decode("82a1744e8ebbd053ca72362d5e5703264b4182de3208c374b8ac4fa36af9c5e5f4f87d1e3b67963d06acf5eb13914c90");
    private static final byte[] outCFB2 = Hex.decode("146cbb581d9e12c3333dd9c736fbb9303c8a3eb5185e2809e9d3c28e25cc2d2b6f5c11ee28d6530f72c412b1438a816a");
    private static final byte[] outOFB1 = Hex.decode("82a1744e8ebbd053ca72362d5e5703261ebf1fdbec05e57b3465b583132f84b43bf95b2c89040ad1677b22d42db69a7a");
    private static final byte[] outOFB2 = Hex.decode("146cbb581d9e12c3333dd9c736fbb9309ea4c2a7696c84959a2dada49f2f1c5905db1f0cec3a31acbc4701e74ab05e1f");

    static SimpleTest[]  tests = 
            {
                new BlockCipherVectorTest(0, new AESEngine(),
                        new KeyParameter(Hex.decode("80000000000000000000000000000000")),
                        "00000000000000000000000000000000", "0EDD33D3C621E546455BD8BA1418BEC8"),
                new BlockCipherVectorTest(1, new AESEngine(),
                        new KeyParameter(Hex.decode("00000000000000000000000000000080")),
                        "00000000000000000000000000000000", "172AEAB3D507678ECAF455C12587ADB7"),
                new BlockCipherMonteCarloTest(2, 10000, new AESEngine(),
                        new KeyParameter(Hex.decode("00000000000000000000000000000000")),
                        "00000000000000000000000000000000", "C34C052CC0DA8D73451AFE5F03BE297F"),
                new BlockCipherMonteCarloTest(3, 10000, new AESEngine(),
                        new KeyParameter(Hex.decode("5F060D3716B345C253F6749ABAC10917")),
                        "355F697E8B868B65B25A04E18D782AFA", "ACC863637868E3E068D2FD6E3508454A"),
                new BlockCipherVectorTest(4, new AESEngine(),
                        new KeyParameter(Hex.decode("000000000000000000000000000000000000000000000000")),
                        "80000000000000000000000000000000", "6CD02513E8D4DC986B4AFE087A60BD0C"),
                new BlockCipherMonteCarloTest(5, 10000, new AESEngine(),
                        new KeyParameter(Hex.decode("AAFE47EE82411A2BF3F6752AE8D7831138F041560631B114")),
                        "F3F6752AE8D7831138F041560631B114", "77BA00ED5412DFF27C8ED91F3C376172"),
                new BlockCipherVectorTest(6, new AESEngine(),
                        new KeyParameter(Hex.decode("0000000000000000000000000000000000000000000000000000000000000000")),
                        "80000000000000000000000000000000", "DDC6BF790C15760D8D9AEB6F9A75FD4E"),
                new BlockCipherMonteCarloTest(7, 10000, new AESEngine(),
                        new KeyParameter(Hex.decode("28E79E2AFC5F7745FCCABE2F6257C2EF4C4EDFB37324814ED4137C288711A386")),
                        "C737317FE0846F132B23C8C2A672CE22", "E58B82BFBA53C0040DC610C642121168"),
                new BlockCipherVectorTest(8, new AESEngine(),
                        new KeyParameter(Hex.decode("80000000000000000000000000000000")),
                        "00000000000000000000000000000000", "0EDD33D3C621E546455BD8BA1418BEC8"),
                new BlockCipherVectorTest(9, new AESEngine(),
                        new KeyParameter(Hex.decode("00000000000000000000000000000080")),
                        "00000000000000000000000000000000", "172AEAB3D507678ECAF455C12587ADB7"),
                new BlockCipherMonteCarloTest(10, 10000, new AESEngine(),
                        new KeyParameter(Hex.decode("00000000000000000000000000000000")),
                        "00000000000000000000000000000000", "C34C052CC0DA8D73451AFE5F03BE297F"),
                new BlockCipherMonteCarloTest(11, 10000, new AESEngine(),
                        new KeyParameter(Hex.decode("5F060D3716B345C253F6749ABAC10917")),
                        "355F697E8B868B65B25A04E18D782AFA", "ACC863637868E3E068D2FD6E3508454A"),
                new BlockCipherVectorTest(12, new AESEngine(),
                        new KeyParameter(Hex.decode("000000000000000000000000000000000000000000000000")),
                        "80000000000000000000000000000000", "6CD02513E8D4DC986B4AFE087A60BD0C"),
                new BlockCipherMonteCarloTest(13, 10000, new AESEngine(),
                        new KeyParameter(Hex.decode("AAFE47EE82411A2BF3F6752AE8D7831138F041560631B114")),
                        "F3F6752AE8D7831138F041560631B114", "77BA00ED5412DFF27C8ED91F3C376172"),
                new BlockCipherVectorTest(14, new AESEngine(),
                        new KeyParameter(Hex.decode("0000000000000000000000000000000000000000000000000000000000000000")),
                        "80000000000000000000000000000000", "DDC6BF790C15760D8D9AEB6F9A75FD4E"),
                new BlockCipherMonteCarloTest(15, 10000, new AESEngine(),
                        new KeyParameter(Hex.decode("28E79E2AFC5F7745FCCABE2F6257C2EF4C4EDFB37324814ED4137C288711A386")),
                        "C737317FE0846F132B23C8C2A672CE22", "E58B82BFBA53C0040DC610C642121168"),
                new BlockCipherVectorTest(16, new AESEngine(),
                        new KeyParameter(Hex.decode("80000000000000000000000000000000")),
                        "00000000000000000000000000000000", "0EDD33D3C621E546455BD8BA1418BEC8"),
                new BlockCipherVectorTest(17, new AESEngine(),
                        new KeyParameter(Hex.decode("00000000000000000000000000000080")),
                        "00000000000000000000000000000000", "172AEAB3D507678ECAF455C12587ADB7"),
                new BlockCipherMonteCarloTest(18, 10000, new AESEngine(),
                        new KeyParameter(Hex.decode("00000000000000000000000000000000")),
                        "00000000000000000000000000000000", "C34C052CC0DA8D73451AFE5F03BE297F"),
                new BlockCipherMonteCarloTest(19, 10000, new AESEngine(),
                        new KeyParameter(Hex.decode("5F060D3716B345C253F6749ABAC10917")),
                        "355F697E8B868B65B25A04E18D782AFA", "ACC863637868E3E068D2FD6E3508454A"),
                new BlockCipherVectorTest(20, new AESEngine(),
                        new KeyParameter(Hex.decode("000000000000000000000000000000000000000000000000")),
                        "80000000000000000000000000000000", "6CD02513E8D4DC986B4AFE087A60BD0C"),
                new BlockCipherMonteCarloTest(21, 10000, new AESEngine(),
                        new KeyParameter(Hex.decode("AAFE47EE82411A2BF3F6752AE8D7831138F041560631B114")),
                        "F3F6752AE8D7831138F041560631B114", "77BA00ED5412DFF27C8ED91F3C376172"),
                new BlockCipherVectorTest(22, new AESEngine(),
                        new KeyParameter(Hex.decode("0000000000000000000000000000000000000000000000000000000000000000")),
                        "80000000000000000000000000000000", "DDC6BF790C15760D8D9AEB6F9A75FD4E"),
                new BlockCipherMonteCarloTest(23, 10000, new AESEngine(),
                        new KeyParameter(Hex.decode("28E79E2AFC5F7745FCCABE2F6257C2EF4C4EDFB37324814ED4137C288711A386")),
                        "C737317FE0846F132B23C8C2A672CE22", "E58B82BFBA53C0040DC610C642121168")
            };
    
    private BlockCipher _engine = new AESEngine();

    public AESTest()
    {
        super(tests, new AESEngine(), new KeyParameter(new byte[16]));
    }

    public String getName()
    {
        return "AES";
    }

    private void testNullSIC()
        throws InvalidCipherTextException
    {
        BufferedBlockCipher b = new BufferedBlockCipher(new SICBlockCipher(new AESEngine()));
        KeyParameter kp = new KeyParameter(Hex.decode("5F060D3716B345C253F6749ABAC10917"));

        b.init(true, new ParametersWithIV(kp, new byte[16]));

        byte[] out = new byte[b.getOutputSize(tData.length)];

        int len = b.processBytes(tData, 0, tData.length, out, 0);

        len += b.doFinal(out, len);

        if (!areEqual(outSIC1, out))
        {
            fail("no match on first nullSIC check");
        }

        b.init(true, new ParametersWithIV(null, Hex.decode("000102030405060708090a0b0c0d0e0f")));

        len = b.processBytes(tData, 0, tData.length, out, 0);

        len += b.doFinal(out, len);

        if (!areEqual(outSIC2, out))
        {
            fail("no match on second nullSIC check");
        }
    }

    private void testNullCBC()
        throws InvalidCipherTextException
    {
        BufferedBlockCipher b = new BufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
        KeyParameter kp = new KeyParameter(Hex.decode("5F060D3716B345C253F6749ABAC10917"));

        b.init(true, new ParametersWithIV(kp, new byte[16]));

        byte[] out = new byte[b.getOutputSize(tData.length)];

        int len = b.processBytes(tData, 0, tData.length, out, 0);

        len += b.doFinal(out, len);

        if (!areEqual(outCBC1, out))
        {
            fail("no match on first nullCBC check");
        }

        b.init(true, new ParametersWithIV(null, Hex.decode("000102030405060708090a0b0c0d0e0f")));

        len = b.processBytes(tData, 0, tData.length, out, 0);

        len += b.doFinal(out, len);

        if (!areEqual(outCBC2, out))
        {
            fail("no match on second nullCBC check");
        }
    }

    private void testNullOFB()
        throws InvalidCipherTextException
    {
        BufferedBlockCipher b = new BufferedBlockCipher(new OFBBlockCipher(new AESEngine(), 128));
        KeyParameter kp = new KeyParameter(Hex.decode("5F060D3716B345C253F6749ABAC10917"));

        b.init(true, new ParametersWithIV(kp, new byte[16]));

        byte[] out = new byte[b.getOutputSize(tData.length)];

        int len = b.processBytes(tData, 0, tData.length, out, 0);

        len += b.doFinal(out, len);

        if (!areEqual(outOFB1, out))
        {
            fail("no match on first nullOFB check");
        }

        b.init(true, new ParametersWithIV(null, Hex.decode("000102030405060708090a0b0c0d0e0f")));

        len = b.processBytes(tData, 0, tData.length, out, 0);

        len += b.doFinal(out, len);

        if (!areEqual(outOFB2, out))
        {
            fail("no match on second nullOFB check");
        }
    }

    private void testNullCFB()
        throws InvalidCipherTextException
    {
        BufferedBlockCipher b = new BufferedBlockCipher(new CFBBlockCipher(new AESEngine(), 128));
        KeyParameter kp = new KeyParameter(Hex.decode("5F060D3716B345C253F6749ABAC10917"));

        b.init(true, new ParametersWithIV(kp, new byte[16]));

        byte[] out = new byte[b.getOutputSize(tData.length)];

        int len = b.processBytes(tData, 0, tData.length, out, 0);

        len += b.doFinal(out, len);

        if (!areEqual(outCFB1, out))
        {
            fail("no match on first nullCFB check");
        }

        b.init(true, new ParametersWithIV(null, Hex.decode("000102030405060708090a0b0c0d0e0f")));

        len = b.processBytes(tData, 0, tData.length, out, 0);

        len += b.doFinal(out, len);

        if (!areEqual(outCFB2, out))
        {
            fail("no match on second nullCFB check");
        }
    }

    public void performTest()
        throws Exception
    {
        super.performTest();

        byte[] keyBytes = new byte[16];
        
        _engine.init(true, new KeyParameter(keyBytes));
        
        //
        // init tests
        //
        try
        {
            byte[]      dudKey = new byte[6];
            
            _engine.init(true, new KeyParameter(dudKey));
            
            fail("failed key length check");
        }
        catch (IllegalArgumentException e)
        {
            // expected 
        }
        
        try
        {
            byte[]      iv = new byte[16];

            _engine.init(true, new ParametersWithIV(null, iv));
            
            fail("failed parameter check");
        }
        catch (IllegalArgumentException e)
        {
            // expected 
        }

        testNullCBC();
        testNullSIC();
        testNullOFB();
        testNullCFB();
    }

    public static void main(
        String[]    args)
    {
        runTest(new AESTest());
    }
}
