# Devoir 5 | Question 2b
# Vincent Harvey | 8780303
# Oliver Benning | 7798804

'''
Ce program prend comme entree une liste et retourne la somme
des elements positif de maniere recursif.
'''

def sommeListePos_rec(liste, n):
	if n > 0:
		#Si l'entier est positif, ajoute sa valeure a la chaine d'additions recursive
		if liste[n-1] > 0:
			return liste[n-1] + sommeListePos_rec(liste,n-1)
		#Si l'entier n'est pas positive, n'additione pas mais continue a traverser la liste
		else:
			return sommeListePos_rec(liste, n-1)
	else:
		#Return qui arrete la recursivite
		return 0

#Demande liste comme donne
liste = list(eval(input("Veuillez entrer une liste de valeurs séparées par des virgules:")))
#Imprime valeur et appele fonction, deuxieme parametre est la longeur
print(sommeListePos_rec(liste, len(liste)))