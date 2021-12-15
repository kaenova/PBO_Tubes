/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Timestamp;

/**
 *
 * @author kaenova
 */
public class Logger {

    public static void Info(String a) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[INFO]\t\t[" + timestamp + "]\t" + a);
    }

    public static void Warning(String a) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[WARNING]\t[" + timestamp + "]\t" + a);
    }

    public static void Error(String a) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[ERROR]\t[" + timestamp + "]\t" + a);
    }
}
