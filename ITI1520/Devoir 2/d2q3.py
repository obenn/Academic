#Oliver Benning 7798804
#ITI1520: Devoir 2 Question 3
#Oct 5 2016

"""
Ce programme demande a l'utilisateur s'il veut faire des exercises de addition ou multiplication par un 1 ou un 0.
Ensuite ca demande 10 questions parmis leur chois, compte un point pour une bonne reponse,
et montre la bonne reponse s'ils fassent une faute. Apres que les 10 questions sont finit, le programe montre
leurs point ets une Felicitation s'ils reussisent ou un conseil s'ils ont besoin de l'aide.
"""

#Besoin d'importer random pour utiliser 'random.randint' pour afficher des numeros aleatoires.
import random

#Fonction qui demande les questions, et sauvegarde les points parmis le nombre de reponses correctes.
def exercise(operation):
    #Etablis le variable a modifier et retourne plus tard.
    points = 0

    #Si l'addition est choisi:
    if operation == 0:
        #Boucle execute 10 fois pout 10 questions.
        for loop in range(10):
            #Genere deux nombres aleatoires et sauvgarde leurs somme,
            #cree des nouveux valeurs si c'est un nouveau cycle de la boucle.
            a = random.randint(0, 9)
            b = random.randint(0, 9)
            correct = a + b
            #Demande pour une reponse et sauvegarde la valeur.
            reponse = int(input(str(a)+" + "+str(b)+" = "))
            #Si la reponse est correct (egale la valeur correct etabli avant), donne un point.
            if reponse == correct:
                points += 1
            #Si c'est fausse, affiche la reponse correcte.
            else:
                print("Incorrect - la reponse est ", correct)

    #Si multiplication est choisi, meme procedure saufe la difference qui est commente:
    elif operation == 1:
        for loop in range(10):
            a = random.randint(0, 9)
            b = random.randint(0, 9)
            #Difference: Multiplie a et b, pas l'addition cette fois.
            correct = a * b
            reponse = int(input(str(a)+" * "+str(b)+" = "))
            if reponse == correct:
                points += 1
            else:
                print("Incorrect - la reponse est ", correct)
    return(points)
        

#Introduit le program et demande de selectionner 0 ou 1 pour choissir l'operation.
print("Ce logiciel va effectuez un test avec 10 questions ……")
operation = int(input("SVP choisisser l'operation 0) Addition 1) Multiplication (0 ou 1): "))

#Une boucle qui brise immediatement si 0 ou 1 est choisi, mais continue a demander si une autre valeur est mis.
while True:
    if (operation == 0) or (operation == 1):
        break
    operation = int(input("Valeur inconnu, choisis 1 ou 0: "))
    
#Demande a la fonction d'executer et sauvegarde la valeur pour points, qui sera retourne.
points = exercise(operation)

#Affiche leurs points et une message dependant de leur reussite.
print(points," reponses correctes")
if points > 6:
    print("Felicitations!")
else:
    print("Demandez a votre enseignant(e) de vous aider.")
