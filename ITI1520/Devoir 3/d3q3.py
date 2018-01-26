#Oliver Benning 7798804
#ITI1520: Devoir 3 Question 3

"""
Ce programme prend une liste comme entree et exprime une donne pour la longeur du sequence consecutifs de nombres
semblables la plus grand.
"""

#Fonction qui prend la liste de mon input et retourne la valeaur de la sequence consecutif de nombres la plus longue
def sequenceMax(liste):
	#Mes variables contenu dans cette fonction
	temp = None #Temporaire pour enregster le nombre precedent dans ma boucle
	consecutif = 1 #Prend la longeur de la sequence present
	finale = 1 #Prend la longeur de la sequence la plus longue de tout la liste

	#Boucle for, exectute 'longeur de liste' fois et loop est la valeur du nombre present
	for loop in liste:
		#Si mon nombre present est identique a la valeur precedent, increment consecutif par un
		if temp == loop:
			consecutif += 1
			#Un autre if pour aussi incrementer final si consecutif deviend plus grand,
			#pour que final egaleur la valeur plus hauit que consecutif a reussit
			if consecutif > finale:
				finale = consecutif
		#Si mon nombre n'est pas la valeur precedent, retourne consecutif a 0
		#Ma valeur important (le plus grand) reste enregistrer en finale
		else:
			consecutif = 1
		#Fais que temp est la valeur de 'loop' precedent
		temp = loop
	#Retourne finale, la valeur de la sequence conseciutif la plus grand
	return(finale)

#Prend mon input et convertisse en liste
liste = list(eval(input("Veuillez entrer une liste de valeurs séparées par des virgules: ")))\

#Imprime le resultat de ma fonction
print(sequenceMax(liste))