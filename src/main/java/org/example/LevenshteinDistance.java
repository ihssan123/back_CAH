package org.example;

import java.util.ArrayList;

public class LevenshteinDistance {
    public static int levenshteinDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] d = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) {
            d[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            d[0][j] = j;
        }

        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                int substitutionCost = s1.charAt(i-1) == s2.charAt(j-1) ? 0 : 1;
                d[i][j] = Math.min(d[i-1][j] + 1, Math.min(d[i][j-1] + 1, d[i-1][j-1] + substitutionCost));
            }
        }

        return d[m][n];
    }


    public  int[][]  MatriceDistance(ArrayList<String> data){
        int size= data.size();
        int[][] matrice = new int[size][size];
        for(int i=0;i< data.size();i++){
            for(int j=0;j<data.size();j++){
                int distance=-1;
                if(data.get(i).contains("-") || data.get(j).contains("§")){
                    int min = Integer.MAX_VALUE;
                    for (String e : data.get(i).split("§")) {
                        for (String f : data.get(j).split("§")) {
                            var dis = levenshteinDistance(e,f);
                            if(dis <min) min = dis;
                        }
                    }
                    distance = min;
                }
                else {
                    distance = levenshteinDistance(data.get(i), data.get(j));

                }
                matrice[i][j] = distance;
            }
        }
        return  matrice;
    }
    public static void fusionnerVilles(ArrayList<String> data,String ville1, String ville2) {
        // Fusionner les deux villes en une nouvelle ville
        String nouvelleVille = ville1 + "§" + ville2;

        // Remplacer la première ville par la nouvelle ville dans la liste
        int index1 = data.indexOf(ville1);
        data.set(index1, nouvelleVille);

        // Supprimer la deuxième ville de la liste
        data.remove(ville2);
    }
//    public ArrayList<Cluster> cluster() {
//        while (clusters.size() > 1) {
//            Cluster c1 = null;
//            Cluster c2 = null;
//            double minDistance = Double.MAX_VALUE;
//            for (int i = 0; i < clusters.size(); i++) {
//                for (int j = i + 1; j < clusters.size(); j++) {
//                    double distance = calculateDistance(clusters.get(i), clusters.get(j));
//                    if (distance < minDistance) {
//                        c1 = clusters.get(i);
//                        c2 = clusters.get(j);
//                        minDistance = distance;
//                    }
//                }
//            }
//            clusters.remove(c1);
//            clusters.remove(c2);
//            clusters.add(mergeClusters(c1, c2));
//        }
//        return clusters;
//    }
    public ArrayList<ArrayList<String>> MHierarchiqueAscendent(int[][] MatriceSimilarite, Cluster data) {
        int ligne=0;
        int colonne=0;
        var result = new ArrayList<ArrayList<String>>();
        while (data.getVilles().size() > 1) {

            int min = MatriceSimilarite[0][1];
            for (int i = 0; i < MatriceSimilarite.length; i++) {
                for (int j = i+1; j < MatriceSimilarite.length; j++) {
                    if (i != j) {
                        if (MatriceSimilarite[i][j] < min)
                            min = MatriceSimilarite[i][j];
                            ligne=i;
                            colonne=j;


                    }

                }
            }
            fusionnerVilles(data.getVilles(),data.getVilles().get(ligne),data.getVilles().get(colonne));
            result.add(new ArrayList<>(data.getVilles()));
             MatriceSimilarite = MatriceDistance(data.getVilles());
//            for(int i=0;i<MatriceSimilarite.length;i++){
//                if(MatriceSimilarite[ligne][i]>=MatriceSimilarite[colonne][i]&&MatriceSimilarite[i][ligne]>=MatriceSimilarite[i][colonne]) {
//                    MatriceSimilarite[ligne][i] = MatriceSimilarite[colonne][i];
//                    MatriceSimilarite[i][ligne] = MatriceSimilarite[i][colonne];
//
//                }
//
//            }
//            data.getVilles().remove(colonne+1);
//            data.getVilles().remove(ligne+1);




        }
        return result;

    }
    public static int[][] deleteElement(int[][] matrix, int rowToDelete, int colToDelete) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        if (rowToDelete < 0 || rowToDelete >= numRows || colToDelete < 0 || colToDelete >= numCols) {
            // invalid row or column index, return the original matrix
            return matrix;
        }
        int[][] newMatrix = new int[numRows - 1][numCols - 1];
        int newRow = 0;
        for (int i = 0; i < numRows; i++) {
            if (i == rowToDelete) {
                // skip the row to delete
                continue;
            }
            int newCol = 0;
            for (int j = 0; j < numCols; j++) {
                if (j == colToDelete) {
                    // skip the column to delete
                    continue;
                }
                newMatrix[newRow][newCol] = matrix[i][j];
                newCol++;
            }
            newRow++;
        }
        return newMatrix;
    }


    public static void printMtx(int [][] matrice) {
        for (int[] row : matrice) {

            for (int element : row) {

                System.out.print(element + "   ");
            }
            // Print a newline after each row
            System.out.println();
            }
    }

    public static void printMatrix(ArrayList<ArrayList<String>> matrix) {
        // Get the number of rows in the matrix
        int numRows = matrix.size();

        // Get the maximum number of columns in any row of the matrix
        int maxCols = 0;
        for (ArrayList<String> row : matrix) {
            maxCols = Math.max(maxCols, row.size());
        }

        // Print the matrix
        for (int i = 0; i < numRows; i++) {
            ArrayList<String> row = matrix.get(i);
            int numCols = row.size();
            // Print the index at the start of each row
            System.out.print(i + " ");
            // Print each element in the row
            for (int j = 0; j < numCols; j++) {
                System.out.print(row.get(j));
                // Add ** symbol except for last column
                if (j != numCols - 1) {
                    System.out.print(" ** ");
                }
            }
            // Add empty elements for columns that don't exist in this row
            for (int j = numCols; j < maxCols; j++) {
                System.out.print(" ** ");
            }
            System.out.println();
        }
    }




}
