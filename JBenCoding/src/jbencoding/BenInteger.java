package jbencoding;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Pankaj
 */
public class BenInteger {

    private long value;

    public BenInteger(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public static BenInteger parseBenInteger(InputStream is, char preReadChar) throws InvalidBenCodingException, IOException {
        char ch = preReadChar;
        if (ch != 'i') {
            throw new InvalidBenCodingException("Invalid bencode integer: doesn't starts with 'i'");
        }
        String str = "";
        ch = (char) is.read();
        while (ch != 'e') {
            if (!Character.isDigit(ch)) {
                throw new InvalidBenCodingException("Invalid bencode integer: contains char " + ch);
            }
            str = str + ch;
            ch = (char) is.read();
        }
        return new BenInteger(Long.parseLong(str));
    }

    public String toString() {
        return "i" + Long.toString(value) + "e";
    }
}
