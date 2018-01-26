#Oliver Benning 7798804
#ITI1520: Devoir 2 Question 1
#Oct 5 2016

"""
Le but du program est de prendre les valeurs de poids et taille de l'utilisateur,
de calculer leur IMC parmis le formulair reconnu, afficher leur IMC, et ensuite
afficher leur categorie de poid parmis leur IMC.
"""

#Definie la foncion qui prend poids et taille, et retourne la valeur IMC correspondant.
def IMC(poids, taille):
    IMC = poids/(taille**2)
    return(IMC)

#Prend les valeurs poir poids et taille de l'utilisateur.
poids = float(input("SVP entre votre poids en kilogrammes: "))
taille = float(input("SVP entre votre taille en metres: "))

#Affiche le IMC des valeurs pris.
print("Votre IMC est", IMC(poids, taille))

"""
Affiche les differentes categories de poids dependant de la valeur du IMC
"Plus grand que" n'est pas definie a cause que la structure elif vas executer
et arreter si un parametre precedent est obtenu.
"""
if IMC(poids, taille) < 18.5:
    print("Maigreur")
elif IMC(poids, taille) < 25:
    print("Poid ideal")
elif IMC(poids, taille) < 30:
    print("Surpoids")
else:
    print("Obesite")
