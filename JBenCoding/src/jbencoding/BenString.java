package jbencoding;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 *
 * @author Pankaj
 */
public class BenString implements Comparable {

    private byte[] value;

    public BenString(String value) {
        this.value = value.getBytes();
    }

    public BenString(byte[] value) {
        this.value = value;
    }

    public String getValue() {
        return new String(value);
    }

    public int compareTo(Object o) {
        if (o instanceof BenString) {
            BenString bs = (BenString) o;
            return ByteBuffer.wrap(value).compareTo(ByteBuffer.wrap(bs.value));
        }
        return 0;
    }

    public static BenString parseBenString(InputStream is, char preReadChar)
            throws InvalidBenCodingException, IOException {
        char ch = preReadChar;
        if (!Character.isDigit(ch)) {
            throw new InvalidBenCodingException("Invalid bencode string: length is not numeric ");
        }
        String str = "" + preReadChar;
        ch = (char) is.read();
        while (ch != ':') {
            str = str + ch;
            ch = (char) is.read();
        }
        int count = Integer.parseInt(str);
        byte[] arr = new byte[count];
        for (int i = 0; i < count; i++) {
            arr[i] = (byte) is.read();
        }
        return new BenString(arr);
    }

    public String toString() {
        return value.length + ":" + new String(value);
    }
}
