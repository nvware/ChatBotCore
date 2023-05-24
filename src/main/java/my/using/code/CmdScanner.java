/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.using.code;

import java.util.Scanner;

/**
 *
 * @author hamid
 */
public class CmdScanner {

    public static void main(String[] args) {

        // Initialize Scanner object
        //String cmd="/gt/lang/word";   // initialize the string delimiter
        String cmd = "/gt/word";
        Scanner scan = new Scanner(cmd).useDelimiter("/");   // initialize the string delimiter       
        System.out.println(scan.next());//ignore gt
        String lang = "";
        String word = "";
        if (scan.hasNext()) {
            lang = scan.next();
        }
        if (scan.hasNext()) {
            word = scan.next();
        } else {
            word = lang;
            lang = "";
        }
        System.out.println(lang);
        System.out.println(word);
//        while (scan.hasNext()) {
//            System.out.println(scan.next());
//        }
        scan.close();// closing the scanner stream
    }

}
