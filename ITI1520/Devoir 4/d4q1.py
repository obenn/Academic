# Devoir 4 | Question 1
# Vincent Harvey | 8780303
# Oliver Benning | 7798804

"""
Ce program prend des valeurs numerique comme donnee
et cree une matrice. Elle utilise une fonction pour additioner 1 a la
matrice original et une autre fonction pour creer une matrice copie
incrementer par 1, sans modifier l'originale.
"""

#Pour copier contenu et non pointer d'une matrice
from copy import deepcopy as contenu_de

def ajoute(matrice):
	"""
	(list)->list
	Additione 1 a chaque place dans la matrice, modifie l'orginale
	"""
	#Additionne 1
	for rg in range(len(matrice)):
		for col in range(len(matrice[rg])):
			matrice[rg][col]+=1
	#Retoune matrice avec l'originale modifie
	return matrice

def ajoute_V2(matrice):
	"""
	(list)->list
	Additione 1 a chaque place dans la matrice, utilise copy.deepcopy
	pour generer une nouvelle matrice, pour ne pas changer l'originale.
	"""
	matrice_local = contenu_de(matrice) #Copie matrice sans pointer
	#Additionne 1
	for rg in range(len(matrice_local)):
		for col in range(len(matrice_local[rg])):
			matrice_local[rg][col]+=1
	#Retourne matrice sans avoir modifie l'originale
	return matrice_local


'''
Corps
'''

#Dialogue introductoire
print('Entrer les éléments de la matrice avec espaces entre les colonnes.')
print('Une rangée par ligne, et une ligne vide à la fin.')

#Demande entree et cree matrice
matrice_input = [] #Matrice entree, commence vide
in_temp = [1] #List temporaire
while(in_temp[0] != ''): #Pour quitter avec une ligne vide
	in_temp = input().strip().split(" ") #Prend les valeurs de l'input
	#Additionne les valeurs du input temporaire a la matrice finale
	if(in_temp[0] != ''):
		for i in range(len(in_temp)): 
			in_temp[i] = int(in_temp[i])
		matrice_input.append(in_temp)

#Imprime mon dialogue et appele les deux fonctions
print("La matrice est :")
print(matrice_input)

print('Apres exécution de la fonction ajoute, la matrice est:')
print(ajoute(matrice_input))

print('Une nouvelle matrice créée avec ajoute_V2:')
print(ajoute_V2(matrice_input))

print('Apres exécution de la fonction ajoute_V2, la matrice initiale est:')
print(matrice_input)