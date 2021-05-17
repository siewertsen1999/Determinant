package Determinanten;

public class det {
    public static int nrOfMult;
    public static int nrOfDiv;
    public static double calcDet(double[][] A){
        double reti = 1;
        normalform(A);
        for(int i = 0; i < A.length; i++){
            reti *= A[i][i];
        }
        return reti;
    }
    
    public static double calcDetRekursiv(double[][] A){
        double det = 0;
        if(A.length == 1){
            return A[0][0];
        }
        for(int i = 0; i < A.length; i++){
            if(i % 2 == 0) det += A[0][i] * calcDetRekursiv(removeRows(A, i));
            if(i % 2 != 0) det -= A[0][i] * calcDetRekursiv(removeRows(A, i));
        }
        return det;
    }

    public static void zeilentausch(double[][] a, int z1, int z2)
    {
        double[] temp = a[z1];
        a[z1] = a[z2];
        a[z2] = temp;
    }

    public static void normalform(double[][] a)
    {
        nrOfMult = 0;
        int zeilen = a.length;
        int spalten = a[0].length;
        //Wir iterieren durch jede Zahl in der Matrix mit for Schleifen
        for(int z = 0;z<zeilen;z++)
        {
            boolean allesNull = true;
            for(int s=0;s<spalten;s++)
            {
                if(a[z][s]!=0) {
                    
                    allesNull = false;
                    break;
                }
            }
            if(allesNull==true)
            {
                zeilentausch(a, z, zeilen-1);
                zeilen--;
            }
        }

        int p = 0;
        while(p<zeilen-1 && p<spalten-1)
        {
            int z=1;
            while(a[p][p]==0)
            {
                if(p+z<=zeilen)
                {
                    p++;
                    continue;
                }
                zeilentausch(a,p,p+z);
                z++;
            }
            for(z=1;z<zeilen-p;z++) 
            {
                if(a[p+z][p]!=0)
                {
                    
                    double x = -a[p+z][p]/a[p][p];
                    nrOfDiv++;
                    for(int s=p;s<spalten;s++)
                    {
                        a[p+z][s] += a[p][s]*x;
                        nrOfMult++;
                    }
                }
            }
            p++;
        }
    }
   
    public static double[][] removeRows(double[][] A, int x){
        double[][] ret = new double[A.length - 1][A.length - 1];
        int offset = 0;
        for(int f = 0; f < ret.length; f++){
            for(int i = 0; i < ret.length; i++){
                if(f == x) offset = 1;
                ret[i][f] = A[i + 1][f + offset];
            }
        }
        return ret;
    }

}
