/**
 * Résolution de grille de Sudoku
 **/

// grille 9x9
// value can be EASY_GRID, MEDIUM_GRID, HARD_GRID, ROBOT_GRID
var grille = EASY_GRID;

/*
* Affiche la grille complète
*/
function printGrid() {
  for(var i = 0; i < grille.length; i++){
   
    for(var j = 0; j < grille[i].length; j++){
      
      console.log(grille[i][j]);

    }
    console.log("---------");
  }
};

/*
* Retourne faux si le nombre est compris dans la ligne, vrai dans les autres cas
*/
function absenceNbSurLigne (nb, ligne) {
  var n = nb;
  var l = grille[ligne];
  var v = true;
  for(var i = 0; i < l.length; i++){
    if (n == l[i]){
      v = false;
    }
  }
  return v;
};

/*
* Retourne faux si le nombre est compris dans la colonne, vrai dans les autres cas
*/
function absenceNbSurColonne (nb, colonne) {
  var n = nb;
  var c = colonne;
  var v = true
  for(var i = 0; i < grille.length; i++){
    if (n == grille[i][c]){
      v = false;
    }
  }
  return v;
};

/*
* Retourne faux si le nombre est compris dans le bloc, vrai dans les autres cas
*/
function absenceNbDansBloc (nb, ligne, colonne) {
  var n = nb;
  var nl = ligne;
  var c = colonne;
  var v = true;
  // var positionInitialLigne = nl - nl % 3; 
//------------------------------------------//
  if (nl % 3 == 1){
    nl = nl - 1;
    console.log("ligne div par 3 reste 1");
  }
  else if (nl % 3 == 2){
    nl = nl -2;
    console.log("ligne div par 3 reste 2");
  } 
  else{
    console.log("ligne div par 3 reste 0");
  }

  //var positionInitialColonne = c - c % 3;
//------------------------------------------//
  if (c % 3 == 1){
    c = c - 1;
    console.log("colonne div par 3 reste 1");
  }
  else if (c % 3 == 2){
    c = c - 2;
    console.log("colonne div par 3 reste 2");
  }
  else{
    console.log("colonne div par 3 reste 0"); 
  }
//------------------------------------------//
  for(var i = nl; i < nl + 3; i++){
    for (var j = c; j < c + 3; j++){
      if (n == grille[i][j]){
        v = false;
      }
    }
  }
  return v;
};

/*
* Fonction récursive permettant de valider la grille au fur et à mesure (donc de la résoudre)
*/
function isValid(position) {
  /* PSEUDO CODE pour vous aider
  * La "position" réprésente une case de la grille
  * Si c'est la dernière case, on arrête
  * Si la case est déja remplie, on passe à la case suivante
  * Pour chaque chiffre possible
  *   Si le chiffre n'est ni sur la ligne, ni sur la colonne, ni dans le bloc
  *     On met à jour la grille et on valide/invalide le choix
  * Si aucun chiffres ne fonctionnent on réinitialise la case
  */
  var p = position;
  
  
  if(p == grille.length^2){
    return true;
  }

  var l = Math.floor(position / 9);
  var c = position % 9;
  
  if (grille[l][c] != 0) {
    return isValid(p + 1);
  }

  for(var i = 1; i < 9; i++){
      
    if(absenceNbSurLigne(i,l) && absenceNbSurColonne(i,c) && absenceNbDansBloc(i,l,c)){
        
      grille[l][c] = i;
        
      if(isValid(p + 1)){
        return true;
      }
    }
  } 
  grille[l][c] = 0;   
  return false;
};

/*
* Main
*/
function sudokuSolver() {
  console.log('Grille initiale');
  printGrid();
  isValid(0); // initialisation du solver sur la position 0 (1ère case)
  console.log('-----------------------------------------------');
  console.log('-----------------------------------------------');
  console.log('-----------------------------------------------');
  console.log('Grille résolue');
  printGrid();
};
