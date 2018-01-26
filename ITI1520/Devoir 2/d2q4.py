#Oliver Benning 7798804
#ITI1520: Devoir 2 Question 4
#Oct 5 2016

"""
Ce program genere 10 questions de muliplication ou addition pour que l'utilisateur les reponds
Chaque reponse correct donne un point, chaque reponse faut montre la solution. A la fin les points
resussites sonts montrees et un message est montre dependant du nombre de points resussit.
"""
#Importer random pour utiliser random.randint pour afficher des numeros aleatoires
import random

#Fonction qui donne une question parmis l'operation demander:
def exercise(operation):
    #Addition
    if operation == 0:
        #Deux nombres aleatoires, sauvegard leur somme.
        a = random.randint(0, 9)
        b = random.randint(0, 9)
        correct = a + b
        #Prend la reponse de l'utilisateur.
        reponse = int(input(str(a)+" + "+str(b)+" = "))
    #Multiplication:
    elif operation == 1:
        #Deux nombres aleatoires, sauvegard leur produit.
        a = random.randint(0, 9)
        b = random.randint(0, 9)
        correct = a * b
        #Prend la reponse de l'utilisateur
        reponse = int(input(str(a)+" * "+str(b)+" = "))
    #Si l'utilisater donne la bonne reponse une valeur boolean de Vrai est retourne.
    if reponse == correct:
        return(True)
    #Si non, la valeur correct est retourne, pour faire le program plus efficasse plus tard.
    else:
        return(correct)

#Message intro:
print("Ce logiciel va effectuez un test avec 10 questions ……")
#Etablis mes points, qui le program vas ajouter a.
points = 0

#Boucle de 10 pour demander 10 questions:
for loop in range (10):
    #Demande pour une operation aleatoire a chaque question.
    operation = random.randint(0, 1)
    #Met le retourne de la fonction dans un variable, qui serait un boolean vrai ou un int.
    reponse = exercise(operation)
    #Si la question est reussit et Vrai est retourne, donne un point.
    if reponse == True:
        points += 1
    #Sinon, affiche la reponse, ce qui etait la valeur retourne.
    else:
        print("Incorrecte - la reponse est: ", reponse)

#Affiche a la fin les points reussit et un message de felicitations out de conseil cependant du nombre de points reussit.
print(points, "reponses correctes")
if points > 6:
    print("Felicitations!")
else:
    print("Demandez a votre enseignant(e) de vous aider.")

        
        
