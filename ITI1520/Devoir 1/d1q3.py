#Oliver Benning 7798804
#ITI1520 Devoir 1
#Question 3
#Note Question 1 etait fait comme ca avant de savoir question 3

def convert(livres, onces):
    kilo = (livres*0.4536)+(onces*0.02834375)
    return(round(kilo, 4))

livres = float(input("Entrez le nombre de livres: "))
onces = float(input("Entrez le nombre d'onces: "))

print(livres if livres%1!=0 else int(livres), "livres et", onces if onces%1!=0 else int(onces), "onces équivalent à", convert(livres, onces), "kilogrammes.")
