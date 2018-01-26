# Devoir 4 | Question 2 Lib
# Vincent Harvey | 8780303
# Oliver Benning | 7798804

def effaceTableau (tab):
   '''
   (list) -> None
   Cette fonction prepare le tableau de jeu (la matrice) 
   en mettant '-' dans tous les elements.
   Elle ne crée pas une nouvelle matrice
   Preconditions: tab est une reference a une matrice n x n qui contient '-', 'X' ou 'O'
   '''

   #Remplace tous la matrice avec '-'
   for row in range(len(tab)):
      for col in range(len(tab[row])):
         tab[row][col] = '-'

      
def verifieGagner(tab):  
   '''(list) ->  bool
   * Preconditions: tab est une reference a une matrice n x n qui contient '-', 'X' ou 'O'
   * Verifie s'il y a un gagnant.
   * Cherche pour 3 X's et O's dans une ligne, colonne, et diagonal.
   * Si on a trouvé, affiche le gagnant (le message "Joueur X a gagné!" 
   * ou "Joueur O a gagné!") et retourne True.
   * S'il y a un match nul (verifie ca avec la fonction testMatchNul),
   * affiche "Match nul" et retourne True.
   * Si le jeu n'est pas fini, retourne False.
   * La fonction appelle les fonctions testLignes, testCols, testDiags
   * pour verifier s'il y a un gagnant.
   * Ces fonctions retournent le gagnant 'X' ou 'O', ou '-' s'il n'y a pas de gagnant
   '''

   game = False
   #Essay chaque fontion pour trouver un gagnant
   winner = testLignes(tab)
   if(winner == '-'):
      winner = testCols(tab)
      if(winner == '-'):
         winner = testDiags(tab)
         if(winner == '-'):
            if(testMatchNul(tab)):
               game = True
               print("Oops, partie nulle...")

   #S'il y a un gagnant met comme vrai
   if(winner != '-'):
      game = True
      print("Nous avons un gagnant! Félicitation :,", winner)
   
   return game

 
def testLignes(tab):
   ''' (list) ->  str
   * verifie s’il y a une ligne gagnante.
   * cherche trois 'X' ou trois 'O' dans une ligne.  
   * Si on trouve, le caractere 'X' ou 'O' et retourné, sinon '-' est retourné.
   * Preconditions: tab est une reference a une matrice n x n qui contient '-', 'X' ou 'O'
   '''
   
   winner = '-'
   row = 0
   #Essay chaque ligne pour une suite de trois
   while row < len(tab) and winner == '-': 
       for col in range(len(tab[row]) - 1):
           if(tab[row][col] == tab[row][col + 1]):
               winner = tab[row][col]
           else:
               winner = '-'
               break
       row += 1
  
   return winner 

  
  
def testCols(tab):
   ''' (list) ->  str
   * verifie s’il y a une colonne gagnante.
   * cherche trois 'X' ou trois 'O' dans une colonne.  
   * Si on trouve, le caractere 'X' ou 'O' et retourné, sinon '-' est retourné.
   * Preconditions: tab est une reference a une matrice n x n qui contient '-', 'X' ou 'O'
   '''
   winner = '-'

   col = 0
   #Essay chaque colonne pour une suite de trois
   while(col < len(tab) and winner == '-'):   
       for row in range(len(tab) - 1):
           if(tab[row][col] == tab[row + 1][col]):
               winner = tab[row][col]
           else:
               winner = '-'
               break
       col += 1
  
   return winner

   
def testDiags(tab):
   ''' (list) ->  str
   * cherche trois 'X' ou trois 'O' dans une diagonale.  
   * Si on trouve, le caractere 'X' ou 'O' et retourné
   * sinon '-' est retourné.
   * Preconditions: tab est une reference a une matrice n x n qui contient '-', 'X' ou 'O'
   '''

   winner = '-'

   row = 0
   #Essay chaque diagonale pour une suite de trois
   for col in range(len(tab) - 1):
      if(row == col):
         if(tab[row][col] == tab[row + 1][col + 1]):
            winner = tab[row][col]
         else:
            winner = '-'
            break
      row += 1
   if(winner != '-'):
      return winner
   else:
      row = (len(tab) - 1)
      for col in range(len(tab) - 1):
         if(tab[row][col] == tab[row - 1][col + 1]):
            winner = tab[row][col]
         else:
            winner = '-'
            break
         row -= 1
   return winner
  
  
def testMatchNul(tab):
   ''' (list) ->  bool
   * verifie s’il y a un match nul
   * verifie si tous les elements de la matrice contiennent X ou O, pas '-'.  
   * Si on ne trouve pas de '-' dans la matrice, retourne True. 
   * S'il y a de '-', retourne false.
   * Preconditions: tab est une reference a une matrice n x n qui contient '-', 'X' ou 'O'
   '''
   match_nul = True
   #Verifie chaque valeur de la matrice pour un '-'
   for row in tab:
       for col in row:
           if(col == '-'):
               match_nul = False
               break
  
   return match_nul

