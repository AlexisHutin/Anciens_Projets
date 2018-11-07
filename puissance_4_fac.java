//package Projet_Puissance_4;
 
import es.*;
 
public class Puissance4 {
        static int [][] grille = new int [6][7];
       
        //1.representation et initialisation de la grille
        public static void initialiseGrille (){
                int i=0;
                for (i=0;i<6;i++){
                        for (int j=0;j<7;j++){
                                grille[i][j]=0;
                        }
                }
        }
 
        //2. representation d'un coup d'un jouer
        public static void jouer (int nj , int nc){
                int j=0;
                while (grille[j][nc] != 0){
                    j++;
                }
                grille[j][nc] = nj;
        }
 
        //3. Affichage de la grille
        public static void afficheGrille(){
                for (int i=5;i>=0;i--){
                        Ecriture.nouvelleLigne();
                        Ecriture.uneChaine("|");
                        for (int j=0;j<=6;j++){
                               
                                if (grille[i][j]==1){
                                        Ecriture.uneChaine("x");
                                }
                                else {
                                        if (grille[i][j]==2){
                                                Ecriture.uneChaine("O");
                                        }
                                        else {
                                                Ecriture.uneChaine(" ");
                                        }
                                }
                                Ecriture.uneChaine("|");
                        }
                }
                Ecriture.nouvelleLigne();
                Ecriture.uneChaine(" 0 1 2 3 4 5 6 ");
        }
 
        //4.1.detection d'un alignement de 4 cases Hor
        public static boolean aGagneHor(int nJ, int nL, int nC) {
                boolean val = true;
                int cpt = 0;
                int j = nC;
                nL = 0;
                if (nC > 3){
                    return false;
                }
                else {
                    while (j < nC + 4 & val) {
                        if (grille[nL][j] != nJ){
                            j++;
                            return val=false;
                           
                        }
                        else{
                            cpt++;
                            j++;
                        }
                    }
                        // rend le résultat
                        if (cpt == 4){
                            return val = true;
                        }
                        else{
                            return val = false;
                        }
                }
        }
 
        //4.2.detection d'un alignement de 4 cases ver
        public static boolean aGagneVer(int nJ, int nL, int nC) {
           
             boolean val = true;
             int cpt = 0;
             int j = nL;
             if (nL > 3){
                return false;
             }
             else {
                while (j < nL + 4 & val) {
                    if (grille[j][nC] != nJ){
                        return val=false;
                     }
                     else{
                        cpt++;
                         j++;
                     }
                }
                     // rend le résultat
                     if (cpt == 4){
                        return val = true;
                     }
                     else{
                        return val = false;
                     }
             }
           
        }
         
        //4.3.détection d'alignement en diag (fonction auxiliaire pour que les fonctions de test en diag ne soit pas trop longues)
        public static boolean trouver(int x, int y, int nC, int nL) {
            int joueur = 0;// pas de pion d'un des 2 joueurs
            int compteur = 0;
 
            int curCol = x;
            int curRow = y;
 
            while ((curCol >= 0) && (curCol <= 6) && (curRow >= 0) && (curRow <= 5)) {
              if (grille[curRow][curCol] != joueur) {
                joueur = grille[curRow][curCol];
                compteur = 1;
              } else {
                compteur++;
              }
              if ((joueur != 0) && (compteur == 4)) {
                return true;
              }
              curCol += nC;
              curRow += nL;
            }
            return false;
          }
       
        //4.4.detection d'un alignement de 4 cases diag mont
        public static boolean aGagneDiagMont() {
            // Diagonales (cherche depuis la ligne du bas)
            for (int col = 0; col < 7; col++) {
              // Première diagonale ( / )
              if (trouver(col, 0, 1, 1)) {
                return true;
              }
              // Deuxième diagonale ( \ )
              if (trouver(col, 0, -1, 1)) {
                return true;
              }
            }
            return false;
            }
 
        //4.5.detection d'un alignement de 4 cases diag desc
        public static boolean aGagneDiagDesc() {
          for (int ligne = 0; ligne < 6; ligne++) {
              // Première diagonale ( / )
              if (trouver(0, ligne, 1, 1)) {
                return true;
              }
              // Deuxième diagonale ( \ )
              if (trouver(7 - 1, ligne, -1, 1)) {
                return true;
              }
            }
 
            // On n'a rien trouvé
            return false;
          }
       
        //5.detection de la victoire d'un joueur
        public static boolean aGagne(int nJ){
            for (int j = 0; j < 6; j++){
                for (int i = 0; i < 7 ; i++){
                    if ( aGagneHor(nJ, j, i) == true | aGagneVer(nJ, j, i) == true | aGagneDiagMont() == true | aGagneDiagDesc() == true){
                        return true ;
                    }
                   
                }
            }
            return false ;
        }
       
        //6.detection de match nul
        public static boolean matchNul(){
            int i = 0;
            int j = 0;
            boolean val = false;
           
            while( i < 7 ){
                while( j <6){
                    if( grille[i][j] != 0){
                        i++;
                        j++;
                    }
                    else{
                        val = false;
                        return val;
                    }
                }
            }
            val = true;
            return val;
        }
       
        //7.fonction jeu qui permet de jouer une partie
        public static void jeu(){
            int i = 1;
            boolean val = false;
            initialiseGrille();
           
            if (matchNul()){
            	Ecriture.nouvelleLigne();
            	Ecriture.uneChaine("Match nul ... ");
            }
            else{
            	while( !matchNul() && !val ){
                    
                    if(i == 1){
                        afficheGrille();
                        Ecriture.nouvelleLigne();
                        Ecriture.uneChaine("Au joueur " +i);
                        Ecriture.uneChaine(" de jouer : ");
                        jouer(i, Lecture.unEntier());
                       
                        if( aGagne(i)){
                            afficheGrille();
                            Ecriture.nouvelleLigne();
                            Ecriture.uneChaine("le joueur "+ i);
                            Ecriture.uneChaine(" à gagné");
                            val = true;
                        }
                        else{
                            i++;
                        }
                    }
                    else if(i == 2){
                        afficheGrille();
                        Ecriture.nouvelleLigne();
                        Ecriture.uneChaine("Au joueur " +i);
                        Ecriture.uneChaine(" de jouer : ");
                        jouer(i, Lecture.unEntier());
                        if( aGagne(i)){
                            afficheGrille();
                            Ecriture.nouvelleLigne();
                            Ecriture.uneChaine("le joueur "+ i);
                            Ecriture.uneChaine(" à gagné");
                            val = true;
                        }
                        else{
                            i--;
                        }
                    }
                   
                Ecriture.nouvelleLigne();
                }
            	
            }
        }
       
        public static void main(String[] args) {
                jeu();
               
 
 
 
        }
}