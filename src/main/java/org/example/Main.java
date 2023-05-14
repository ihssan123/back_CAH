package org.example;

import java.io.IOException;
import java.util.ArrayList;

import static org.example.data.chargerFichierExcel;

public class Main {

    public static void main(String[] args) throws IOException {
        LevenshteinDistance LD = new LevenshteinDistance();
        Cluster cls = new Cluster();
        cls.setVilles(chargerFichierExcel("C:/Users/youss/Desktop/datasetDM/Book1.xlsx"));
            for (String ville : cls.getVilles()) {
                //System.out.println(ville);

            }




//        ArrayList<String> newList = new ArrayList<String>(cls.getVilles().subList(0, 4));
//            var newCls = new Cluster();
//            newCls.setVilles(newList);

        int[][] matrice=LD.MatriceDistance(cls.getVilles());
        var t = LD.MHierarchiqueAscendent( matrice,cls);
        LevenshteinDistance.printMatrix(t);
        //LevenshteinDistance.printMtx(matrice);


//        for (int[] row : matrice) {
//
//            for (int element : row) {
//
//                System.out.print(element + " ");
//            }
//            // Print a newline after each row
//            System.out.println();
//            }
//        LD.MHierarchiqueAscendent( matrice,cls);
////

            }





    }
