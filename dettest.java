package Determinanten;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class testDet {
    public double[][] A;
    public int nrOfDigits;
    
    public testDet(String filename){
        test(filename);
    }
    public void test(String filename){
        A = readMatrixFromFile(filename);
        if (A==null) return;
        if (A.length!=A[0].length){
            System.out.println("Die Matrix in "+filename+" ist nicht quadratisch.");
            return;
        }
        nrOfDigits = 1;
        
        System.out.println("A:");
        showMatrix(A, nrOfDigits);
        System.out.println();
        
        det.nrOfMult = 0;
        System.out.println("normal: det(A) = "+det.calcDet(A));
        System.out.println("recursive det(A) = "+det.calcDetRekursiv(A));
        System.out.println("Anzahl der Multiplikationen: "+det.nrOfMult);
        System.out.println("Anzahl der Divisionen: "+det.nrOfDiv);

    }

    
    public static double[][] readMatrixFromFile(String filename){
        ArrayList<String> stringList = new ArrayList<String>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            while (line!=null){
                stringList.add(line);
                line = br.readLine();
            }
            br.close();
            
            String[] parts = stringList.get(0).split("  ");
            int m = stringList.size(), n = parts.length;
            double[][] M = new double[m][n];
            for (int i=0; i<m; i++){
                parts = stringList.get(i).split("  ");
                for (int j=0; j<n; j++) M[i][j] = Double.valueOf(parts[j]);
            }
            return M;
        }
        catch(IOException e){
            System.out.println(""+e);
            return null;
        }
    }

   
    public void showMatrix(double[][] M, int nrOfDigits){
        int m = M.length;
        int n = M[0].length;
        double max = 0.0;
        for (int j=0; j<n; j++){
            for (int i=0; i<m; i++){
                if (M[i][j]>max) max = M[i][j];
               }
        }
        int l;
        if (max==0) l = 5;
        else l = (int) Math.log10(Math.abs(max))+nrOfDigits+5;
        if (nrOfDigits==0) l--;
       
        String f, s;
        f = "%"+l+"."+nrOfDigits+"f";
        for (int i=0; i<m; i++){
            s = "";
            for (int j=0; j<n; j++){
                s = s+String.format(f, M[i][j]);    
            }
            System.out.println(s);
        }
    }
    public static void main(String[] args){
        testDet test = new testDet("src/Determinanten/Test1.txt");
        testDet test1 = new testDet("src/Determinanten/Test.txt");
        testDet test2 = new testDet("src/Determinanten/Test2.txt");
    }
}
