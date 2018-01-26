# Devoir 5 | Question 2a
# Vincent Harvey | 8780303
# Oliver Benning | 7798804

"""
Cette question est modifie de l'originale. Elle utilise une fonction reursive pour
imprimer la suite deux fois, la deuxieme fois a l'envers. Ceci fait un diamant avec une
donne > 4.
"""
#Ici est la fonction originale, elle n'est pas appele sans modifier le code.
#######################################################################################
def printstar_old(n):
    for i in range(abs(n)):
        print("*", end = "")
    if(n != 0):
        print("")
    if(n > -(nombre)):
        printstar_old(n - 1)
#######################################################################################

def printstar(n):
    ''' (int) -> None
    Fonction principale, print une sequence d'etoiles de maniere recursif.
    '''

    #Imprime les etoiles correspondant a l'etape de n, abs() laisse la fonction
    #retourner.
    for i in range(abs(n)):
        print("*", end = "")
    #Imprime les espaces et la deuxieme range des etoiles, les deux boucles for
    #sont la modification
    if(n != 0):
        for j in range(2 * (nombre - abs(n))):
            print(" ", end = "") 
        for k in range(abs(n)):
            print("*", end = "")
        print("")
    #Appele la fontion de maniere recursive just'qua temps qu'elle s'a appelee
    #effectivement 2n fois, une fois positif et une fois negative, ce qui cree
    #l'image symmetrique.
    if(n > -(nombre)):
        printstar(n - 1)


def askuser():
    ''' None -> (int)
    Fontion simple pour prendre un nombre positif comme donne
    '''
    print("Nous allons faire un beau dessin")
    nombre = abs(int(input("Entrez un entier positif:")))
    print("")
    return nombre

nombre = askuser()   
printstar(nombre)
#printstar_old(nombre) #<-pour appeler la fonction vieux delete le '#' avant printstar_old