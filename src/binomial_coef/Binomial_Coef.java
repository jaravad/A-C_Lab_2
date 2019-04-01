/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binomial_coef;

import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author jaravad
 */
public class Binomial_Coef {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        long startTime;
        System.out.println("Tabla de valores (NR):");
        for (int i = 0; i <=30; i += 1) {
            System.out.print("┊");
            for (int j = i; j >= 0; j--) {
                if (String.valueOf(Bin_CNR(new BigInteger(String.valueOf(i)), new BigInteger(String.valueOf(j)))).length() <= 5) {
                    System.out.print(Bin_CNR(new BigInteger(String.valueOf(i)), new BigInteger(String.valueOf(j))) + "┊");
                } else {
                    System.out.print(String.valueOf(Bin_CNR(new BigInteger(String.valueOf(i)), new BigInteger(String.valueOf(j)))).substring(0, 5) + "┊");
                }
            }
            System.out.println("");
        }
        System.out.println("\nTabla de tiempos en ms (NR):");
        for (int i = 0; i <=30; i += 1) {
            System.out.print("┊");
            startTime=0;
            for (int j = i; j >= 0; j--) {
                startTime = System.nanoTime();
                Bin_CNR(new BigInteger(String.valueOf(i)), new BigInteger(String.valueOf(j)));
                System.out.print(System.nanoTime() - startTime+"┊");
            }
            System.out.println("");
        }
        
        System.out.println("Tabla de valores (R):");
        for (int i = 0; i <=30; i += 1) {
            System.out.print("┊");
            for (int j = i; j >= 0; j--) {
                if (String.valueOf(Bin_CR(new BigInteger(String.valueOf(i)), new BigInteger(String.valueOf(j)))).length() <= 5) {
                    System.out.print(Bin_CR(new BigInteger(String.valueOf(i)), new BigInteger(String.valueOf(j))) + "┊");
                } else {
                    System.out.print(String.valueOf(Bin_CR(new BigInteger(String.valueOf(i)), new BigInteger(String.valueOf(j)))).substring(0, 5) + "┊");
                }
            }
            System.out.println("");
        }
        System.out.println("\nTabla de tiempos en ms (R):");
        for (int i = 0; i <=30; i += 1) {
            System.out.print("┊");
            for (int j = i; j >= 0; j--) {
                startTime = System.nanoTime();
                Bin_CR(new BigInteger(String.valueOf(i)), new BigInteger(String.valueOf(j)));
                System.out.print(System.nanoTime() - startTime+"┊");
            }
            System.out.println("");
        }
    }

    public static BigInteger Bin_CNR(BigInteger n, BigInteger k) {//No recursivo
        BigInteger coef = Fact(n).divide(Fact(k).multiply(Fact(n.subtract(k))));
        return coef;
    }

    public static BigInteger Bin_CR(BigInteger n, BigInteger k) {//Recursivo
        if (k.compareTo(BigInteger.ZERO) == 0 || k.compareTo(n) == 0) {
            return BigInteger.ONE;
        } else {
            return Bin_CR(n.subtract(BigInteger.ONE), k.subtract(BigInteger.ONE)).add(Bin_CR(n.subtract(BigInteger.ONE), k));
        }
    }

    public static BigInteger Fact(BigInteger x) {
        BigInteger fac = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE; i.compareTo(x) <= 0; i = i.add(BigInteger.ONE)) {
            fac = fac.multiply(i);
        }
        return fac;
    }

}
