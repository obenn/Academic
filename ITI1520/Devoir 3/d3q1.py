#Oliver Benning 7798804
#ITI1520: Devoir 3 Question 1

"""
Cette programme prend une liste de nombres comme entree et retourne une valeur pour le nombre de nombres qui
sont superieures a 0 (positifs) dans cette liste.
"""

#Fonction qui compte les valeurs superieur a 0
def compte_pos(liste):
	compte = 0
	#Boucle qui execute un nombres de fois correspondant a la longeueur de liste
	#et loop qui devient la valeur de ce point dans la liste a chaque exemple
	for loop in liste:
		#Si ma valeur est superieur a 0, compte une valeur de plus
		if loop > 0:
			compte += 1
	#Retourne le nombre de chiffres superieur a 0
	return(compte)

#Prend la donne comme etant une liste, utilise eval parceque j'ai une liste des valeurs propres et non les characteres
#e.g. je ne veut pas inclure les espaces et non plus les virgules
liste = list(eval(input("Veuillez entrer une liste de valeurs séparées par des virgules: ")))

#Imprime le resultat apres que la fonction compte_pos est effectue
print(compte_pos(liste))
