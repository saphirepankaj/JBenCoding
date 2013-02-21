package jbencoding;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/**
 *
 * @author Pankaj
 */
public class BenDictionary {

    private TreeMap value;

    public BenDictionary() {
        this.value = new TreeMap();
    }

    public Object add(BenString key, BenString value) {
        return this.value.put(key, value);
    }

    public Object add(BenString key, BenInteger value) {
        return this.value.put(key, value);
    }

    public Object add(BenString key, BenList value) {
        return this.value.put(key, value);
    }

    public Object add(BenString key, BenDictionary value) {
        return this.value.put(key, value);
    }

    public Object get(BenString key) {
        return value.get(key);
    }

    public Collection values() {
        return value.values();
    }

    public static BenDictionary parseBenDictionary(InputStream is, char preReadChar)
            throws InvalidBenCodingException, IOException {
        char ch = preReadChar;
        if (ch != 'd') {
            throw new InvalidBenCodingException(
                    "Invalid bencode dictionary: doesn't starts with 'd'");
        }
        ch = (char) is.read();
        BenDictionary benDictionary = new BenDictionary();
        while (ch != 'e') {
            BenString key = BenString.parseBenString(is, ch);
            ch = (char) is.read();
            switch (ch) {
                case 'i':
                    benDictionary.add(key, BenInteger.parseBenInteger(is, ch));
                    break;
                case 'l':
                    benDictionary.add(key, BenList.parseBenList(is, ch));
                    break;
                case 'd':
                    benDictionary.add(key, BenDictionary.parseBenDictionary(is, ch));
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    benDictionary.add(key, BenString.parseBenString(is, ch));
                    break;
                default:
                    throw new InvalidBenCodingException("Invalid bencode");
            }
            ch = (char) is.read();
        }
        return benDictionary;
    }

    public String toString() {
        String str = "d";
        Iterator keys = value.keySet().iterator();
        while (keys.hasNext()) {
            BenString key = (BenString) keys.next();
            str = str + key.toString();
            str = str + value.get(key).toString();
        }
        str = str + "e";
        return str;
    }
}
