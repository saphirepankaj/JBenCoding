/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbencoding;

/**
 *
 * @author Pankaj
 */
public class InvalidBenCodingException extends Exception {

    /**
     * Creates a new instance of
     * <code>InvalidBenCodingException</code> without detail message.
     */
    public InvalidBenCodingException() {
    }

    /**
     * Constructs an instance of
     * <code>InvalidBenCodingException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidBenCodingException(String msg) {
        super(msg);
    }
}
