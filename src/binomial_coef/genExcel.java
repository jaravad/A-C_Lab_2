/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binomial_coef;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author Jesus David
 */
public class genExcel {

    public void generarExcel(LinkedList<LinkedList<BigInteger>> a, String ruta) {
        try {
            WorkbookSettings conf = new WorkbookSettings();
            conf.setEncoding("ISO-8859-1");
            WritableWorkbook workBook = Workbook.createWorkbook(new File(ruta), conf);

            WritableSheet sheet = workBook.createSheet("Resultado", 0);
            WritableFont h = new WritableFont(WritableFont.COURIER, 16, WritableFont.NO_BOLD);
            WritableCellFormat hformat = new WritableCellFormat(h);
            int i = 0, j;
            for (LinkedList<BigInteger> x : a) {
                j = 0;
                for (BigInteger y : x) {
                    try {
                        sheet.addCell(new jxl.write.Label(j, i, String.valueOf(y), hformat));
                    } catch (WriteException ex) {
                        Logger.getLogger(genExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    j += 1;
                }
                i += 1;
            }

            workBook.write();
            workBook.close();

        } catch (IOException ex) {
            Logger.getLogger(genExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(genExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
