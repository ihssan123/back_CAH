package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xwpf.usermodel.BreakType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class data {


    public static ArrayList<String> chargerFichierExcel(String cheminFichier) throws IOException {
        ArrayList<String> tableauData=new ArrayList<String>();
        // Ouvrir le fichier Excel en lecture
        FileInputStream fichierExcel = new FileInputStream(new File(cheminFichier));

        // Charger le contenu du fichier Excel
        Workbook workbook = WorkbookFactory.create(fichierExcel);

        // Parcourir les feuilles de calcul du fichier Excel
        for (Sheet feuille : workbook) {
            System.out.println("Feuille de calcul : " + feuille.getSheetName());

            // Parcourir les lignes de la feuille de calcul
            for (Row ligne : feuille) {
                // Parcourir les cellules de la ligne
                for (Cell cellule : ligne) {
                    // Lire la valeur de la cellule
                    String valeur = cellule.getStringCellValue();
                    tableauData.add(valeur);
                }

            }
        }

        // Fermer le fichier Excel
        fichierExcel.close();
        return tableauData;
    }

}
