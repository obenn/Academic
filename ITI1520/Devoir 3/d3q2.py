#Oliver Benning 7798804
#ITI1520: Devoir 3 Question 2

"""
Cette programme prend une liste comme entree et retourne une valeure bouleene si il y a une
sequence de deux nombres consecutifs.
"""

#Fonction qui trouve si il y a une sequence de deux
def sequenceDesDeux(liste):
	#Dit au program que temp, une valeur temporaire, est un int
	#mais ne donne aucune valeur, pour ne pas tromper mon 'if' dans la premiere instance de la boucle
	temp = None
	#For loop pour une liste
	for loop in liste:
		#temp commence avec valeur 'None' et deviend la valeur precedente de loop, si c'est la valeur precedente ce loop
		#fait que la fonction retourne True et arrete immediatement
		if temp == loop:
			return(True)
			break
		temp = loop
	#So mon boucle 'for' fini sans activer mon 'if', il y a aucune sequence de deux et alors retourne False	
	return(False)

#Prend mon input comme etre une liste
liste = list(eval(input("Veuillez entrer une liste de valeurs séparées par des virgules: ")))

#Imprime le resultat de la fonction qui regarde pour une sequence de deux
print(sequenceDesDeux(liste))