#Oliver Benning 7798804
#ITI1520 Devoir 1
#Question 2
#Note Question 2 etait fait comme ca avant de savoir question 4

def calcule(argent):
    types = (0.25, 0.10, 0.05, 0.01)
    pieces = 0
    for loop in range(4):
        pieces += argent//types[loop]
        argent = round((argent % types[loop]), 2)
    return(pieces)

argent = float(str.replace(input("Entrez le montant en dollars: "), "," , "."))
                       
print("Le nombre minimal de pieces que le caissier peut rendre est: ", int(calcule(argent)))
