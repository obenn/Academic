#Oliver Benning 7798804
#ITI1520: Devoir 2 Question 2
#Oct 5 2016

"""
Le but de ce programe est d'afficher les valeurs entre a et b, incluant eux
memes, qui sont donne par l'utilisateur.
"""

#La foncionne qui calcule et affiche les valeurs entre a et b.
def valeurs(a, b):
    #Si b est plus grand que a, affiche les valeurs de a justqu'a b.
    #(b+1 car les ranges excluent la dernier valeur propre)
    if b > a:
    	for loop in range(a, b+1):
        	print(loop)
    #Sinon, a est plus grand que b, imprime une range alenvers, utilisant une troisieme parametre -1.
    #(b-1 a cause des proprietes des ranges)
    else:
    	for loop in range(a, b-1, -1):
        	print(loop)
    
#Prend les valeurs donne par l'utilisateur.
a = int(input("SVP donner la valeur de a: "))
b = int(input("SVP donner la valeur de b: "))

#Appele a la fonction 'valeurs' pour executer.
valeurs(a, b)
