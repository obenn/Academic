#Oliver Benning 7798804
#ITI1520 Devoir 1
#Question 5

def en_secondes(annees_lumiere):
    secondes_lumiere = annees_lumiere*365.26*24*60*60
    return(secondes_lumiere)

def en_km(secondes_lumiere):
    km = secondes_lumiere * 30000
    return(km)

question1 = float(input("Entrez un nombre d'annees-lumiere: "))
print("Le nombre de secondes-lumiere est ", en_secondes(question1))
print("La distance est ", en_km(en_secondes(question1)), "km")
question2 = float(input("Entrez la distance de la premiere etoile, en annees-lumieres: "))
question3 = float(input("Entrez la distance de la deuxieme etoile, en annees-lumieres: "))
print("La distance entre les deux etoiles est ",en_km(en_secondes(question2+question3)),"km.")
