package Determinanten;
/*
@authors:
Nguyen, To Uyen 574330
Siewertsen, Paul 572933
Valentin, Leonard 5756986
 */
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
    /*
    dies ist eine rekursive Implementation der Determinanten Berechnung nach Vorlage im Skript
    https://moodle.htw-berlin.de/pluginfile.php/985008/mod_resource/content/3/TEXTL41neu.pdf :
    die Abbruchbedingung ist eine Matrix der groesse 1. Wenn dies gegeben ist gibt  der code das einzige Element im Array zurueck,
    was in dem Fall die Determinante ist. im anderen fall wird die die Determinaten-Funktion rekursiv aufgerufen, mit einer kleineren matrix, in der
    doe oberste Zeile, und die Spalte, die durch die Lauf-varaible i angegben wird, entefernt sind( i itereriert uber die gesamte erste Zeile).
    diese werden alternierend auf den return value addiert und subrahiert

     */
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
                    // Wenn die nicht 0 ist, springen wir aus der Schleife
                    allesNull = false;
                    break;
                }
            }
            if(allesNull==true) // Wir tauschen die Zeile mit der 0 in die letzte Zeile der Matrix
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
            for(z=1;z<zeilen-p;z++) // Wir gehen die Zeilen unter der ersten durch
            {
                if(a[p+z][p]!=0)//Wenn a[p+z][p] nicht 0 ist
                {
                    //Faktor zu Bildung des Minuenden, um an der Stelle a[p+z][p] eine 0 zu bilden
                    double x = -a[p+z][p]/a[p][p];
                    nrOfDiv++;
                    for(int s=p;s<spalten;s++)
                    {
                        a[p+z][s] += a[p][s]*x;
                        nrOfMult++;
                    }
                }
            }
            p++;//Wir gehen in die nächste Spalte
        }
    }
    /*
    dies ist eine Helferfunktion fuer die rekursive Determinantenberechnug. Sie entfernt einfach die durch x angegebene spalte
    ein angeben der zu entfernenden zeile ist nicht noetig, da fuer unsere funktion immer die kopfzeile entfernt wird
     */
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
