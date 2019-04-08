/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binomial_coef;

import java.math.BigInteger;
import java.util.LinkedList;


/**
 *
 * @author jaravad
 */
public class Binomial_Coef {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        LinkedList<LinkedList<BigInteger>> VNR = new LinkedList<>();//Valores no recursivo
        LinkedList<LinkedList<BigInteger>> VR = new LinkedList<>();//Valores recursivo
        LinkedList<LinkedList<Long>> TNR = new LinkedList<>();//Tiempos no recursivo
        LinkedList<LinkedList<Long>> TR = new LinkedList<>();//Tiempos recursivo
        long startTime;
        int n=15;//tope

        for (int i = 0; i <= n; i += 50) {
            
            LinkedList<BigInteger> v = new LinkedList<>();
            LinkedList<Long> t = new LinkedList<>();
            if (i>0) {
                System.out.print("┊");
            }
            for (int j = i; j > 0; j--) {
                
                if (String.valueOf(Bin_CNR(new BigInteger(String.valueOf(i)), new BigInteger(String.valueOf(j)))).length() <= 5) {
                    startTime = 0;
                    v.add(Bin_CNR(new BigInteger(String.valueOf(i)), new BigInteger(String.valueOf(j))));
                    System.out.print(v.getLast()+"┊");
                    t.add(System.nanoTime() - startTime);
                } else {
                    startTime = 0;
                    v.add(new BigInteger(String.valueOf(Bin_CNR(new BigInteger(String.valueOf(i)), new BigInteger(String.valueOf(j)))).substring(0, 5)));
                    System.out.print(v.getLast()+"┊");
                    t.add(System.nanoTime() - startTime);
                }
                
            }
            System.out.println("");
            VNR.add(v);
            TNR.add(t);
            
        }
        
        String ruta="no_recursivo.xls"; //Exportar valores del no recursivo a Excel
        genExcel g = new genExcel();
        g.generarExcel(VNR, ruta);
        
//        ShowVTable(VNR);
//        ShowTTable(TNR);
        for (int i = 0; i <= n; i += 1) {
            LinkedList<BigInteger> v = new LinkedList<>();
            LinkedList<Long> t = new LinkedList<>();
            if (i>0) {
                System.out.print("┊");
            }
            for (int j = i; j > 0; j--) {
                if (String.valueOf(Bin_CR(new BigInteger(String.valueOf(i)), new BigInteger(String.valueOf(j)))).length() <= 5) {
                    startTime = 0;
                    v.add(Bin_CR(new BigInteger(String.valueOf(i)), new BigInteger(String.valueOf(j))));
                    System.out.print(v.getLast()+"┊");
                    t.add(System.nanoTime() - startTime);
                } else {
                    startTime = 0;
                    v.add(new BigInteger(String.valueOf(Bin_CR(new BigInteger(String.valueOf(i)), new BigInteger(String.valueOf(j)))).substring(0, 5)));
                    System.out.print(v.getLast()+"┊");
                    t.add(System.nanoTime() - startTime);
                }
                
            }
            VR.add(v);
            TR.add(t);
            System.out.println("");
        }
//        ShowVTable(VR);
//        ShowTTable(TR);
        
    }

    public static void ShowVTable(LinkedList<LinkedList<BigInteger>> a) {
        
        for (LinkedList<BigInteger> x : a) {
            System.out.print("┊");
            for (BigInteger y : x) {
                System.out.print(y + "┊");
            }
            System.out.println("");
        }
    }
    public static void ShowTTable(LinkedList<LinkedList<Long>> a){
        for (LinkedList<Long> x : a) {
            System.out.print("┊");
            for (Long y : x) {
                System.out.print(y+"┊");
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
