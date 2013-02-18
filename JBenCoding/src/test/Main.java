/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import jbencoding.BenDictionary;
import jbencoding.InvalidBenCodingException;

/**
 *
 * @author Pankaj
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, InvalidBenCodingException {
        File torrentFile = new File("C:\\Users\\Pankaj\\Desktop\\a.torrent");
        FileInputStream fis = new FileInputStream(torrentFile);
        BenDictionary metaInfo = BenDictionary.parseBenDictionary(fis, (char) fis.read());
        System.out.print(metaInfo.toString());
    }
}
