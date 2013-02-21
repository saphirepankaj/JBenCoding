package jbencoding;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author Pankaj
 */
public class BenList {

    private ArrayList value;

    public BenList() {
        this.value = new ArrayList();
    }

    public boolean add(BenString benString) {
        return value.add(benString);
    }

    public boolean add(BenInteger benInetegr) {
        return value.add(benInetegr);
    }

    public boolean add(BenList benList) {
        return value.add(benList);
    }

    public boolean add(BenDictionary benDictionary) {
        return value.add(benDictionary);
    }

    public Object get(int index) {
        return value.get(index);
    }

    public int size() {
        return value.size();
    }

    public static BenList parseBenList(InputStream is, char preReadChar) throws InvalidBenCodingException, IOException {
        char ch = preReadChar;
        if (ch != 'l') {
            throw new InvalidBenCodingException("Invalid bencode list: doesn't starts with 'l'");
        }
        BenList list = new BenList();
        ch = (char) is.read();
        while (ch != 'e') {
            switch (ch) {
                case 'i':
                    list.add(BenInteger.parseBenInteger(is, ch));
                    break;
                case 'l':
                    list.add(BenList.parseBenList(is, ch));
                    break;
                case 'd':
                    list.add(BenDictionary.parseBenDictionary(is, ch));
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
                    list.add(BenString.parseBenString(is, ch));
                    break;
                default:
                    throw new InvalidBenCodingException("Invalid bencode");
            }
            ch = (char) is.read();
        }
        return list;
    }

    public Object[] toArray() {
        return value.toArray();
    }

    public String toString() {
        String str = "l";
        for (int i = 0; i < value.size(); i++) {
            str = str + value.get(i).toString();
        }
        str = str + "e";
        return str;
    }
}
