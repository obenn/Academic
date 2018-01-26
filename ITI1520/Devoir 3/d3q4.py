#Oliver Benning 7798804
#ITI1520: Devoir 3 Question 4

# Jeu de cartes appelé "Pouilleux" 

# L'ordinateur est le donneur des cartes.

# Une carte est une chaine de 2 caractères. 
# Le premier caractère représente une valeur et le deuxième une couleur.
# Les valeurs sont des caractères comme '2','3','4','5','6','7','8','9','10','J','Q','K', et 'A'.
# Les couleurs sont des caractères comme : ♠, ♡, ♣, et ♢.
# On utilise 4 symboles Unicode pour représenter les 4 couleurs: pique, coeur, trèfle et carreau.
# Pour les cartes de 10 on utilise 3 caractères, parce que la valeur '10' utilise deux caractères.

import random

def attend_le_joueur():
    '''()->None
    Pause le programme jusqu'au l'usager appui Enter
    '''
    try:
         input("Appuyez Enter pour continuer. ")
    except SyntaxError:
         pass


def prepare_paquet():
    '''()->list of str
        Retourne une liste des chaines de caractères qui représente tous les cartes,
        sauf le valet noir.
    '''
    paquet=[]
    couleurs = ['\u2660', '\u2661', '\u2662', '\u2663']
    valeurs = ['2','3','4','5','6','7','8','9','10','J','Q','K','A']
    for val in valeurs:
        for couleur in couleurs:
            paquet.append(val+couleur)
    paquet.remove('J\u2663') # élimine le valet noir (le valet de trèfle)
    return paquet

def melange_paquet(p):
    '''(list of str)->None
       Melange la liste des chaines des caractères qui représente le paquet des cartes    
    '''
    random.shuffle(p)

def donne_cartes(p):
     '''(list of str)-> tuple of (list of str,list of str)

     Retournes deux listes qui représentent les deux mains des cartes.  
     Le donneur donne une carte à l'autre joueur, une à lui-même,
     et ça continue jusqu'à la fin du paquet p.
     '''
     
     donneur=[]
     autre=[]

     cycle = 0
     for loop in p:
        #Chaque cycle paire
        if (cycle%2)==0:
            donneur.append(loop)
        else:
            autre.append(loop)
        cycle+=1

     return (donneur, autre)


def elimine_paires(l):
    '''
     (list of str)->list of str

     Retourne une copy de la liste l avec tous les paires éliminées 
     et mélange les éléments qui restent.

     Test:
     (Notez que l’ordre des éléments dans le résultat pourrait être différent)
     
     >>> elimine_paires(['9♠', '5♠', 'K♢', 'A♣', 'K♣', 'K♡', '2♠', 'Q♠', 'K♠', 'Q♢', 'J♠', 'A♡', '4♣', '5♣', '7♡', 'A♠', '10♣', 'Q♡', '8♡', '9♢', '10♢', 'J♡', '10♡', 'J♣', '3♡'])
     ['10♣', '2♠', '3♡', '4♣', '7♡', '8♡', 'A♣', 'J♣', 'Q♢']
     >>> elimine_paires(['10♣', '2♣', '5♢', '6♣', '9♣', 'A♢', '10♢'])
     ['2♣', '5♢', '6♣', '9♣', 'A♢']
    '''
    couleurs = ['\u2660', '\u2661', '\u2662', '\u2663']
    resultat=[]

    for carte in l:    
        #Ici pour eviter le probleme que 10 est deux characteres alors carte[0] est 1
        if carte[0]+carte[1]=='10':
            nombre = '10'
        #Nombre est la valeur du premier charactere de carte
        else:
            nombre = carte[0]  
        #Deuxieme for pour essayer chaque couleur
        for couleur in couleurs:
            trouver = False         
            #Si un paire est deja dans resultat, prend aussi le vieux
            if nombre+couleur in resultat:
                trouver = True
                resultat.remove(nombre+couleur)
                break
        #Sinon ajoute la dans resultat
        if trouver == False:
            resultat.append(carte)
    random.shuffle(resultat)
    return resultat

def affiche_cartes(p):
    '''
    (list)-None
    Affiche les éléments de la liste p séparées par d'espaces
    '''
    for loop in p:
        print(loop, end=" ")
    print()

    

def entrez_position_valide(n):
     '''
     (int)->int
     Retourne un entier du clavier, de 1 à n (1 et n inclus).
     Continue à demander si l'usager entre un entier qui n'est pas dans l'intervalle [1,n]
     
     Précondition: n>=1
     '''

     #-1 a la fin car ma liste prend "0" a "n", et non "1" a "n+1"
     donnee = int(input('SVP entrez un entier de 1 a '+str(n)+": "))-1
     while donnee not in range(0, n):
         donnee = int(input('Position invalide. SVP entrez un entier de 1 a '+ str(n)+": "))-1
     return donnee


     

def joue():
     '''()->None
     Cette fonction joue le jeu'''
    
     p=prepare_paquet()
     melange_paquet(p)
     tmp=donne_cartes(p)
     donneur=tmp[0]
     humain=tmp[1]

     print("Bonjour. Je m'appelle Robot et je distribue les cartes.")
     print("Votre main est:")
     affiche_cartes(humain)
     print("Ne vous inquiétez pas, je ne peux pas voir vos cartes ni leur ordre.")
     print("Maintenant défaussez toutes les paires de votre main. Je vais le faire moi aussi.")
     attend_le_joueur()
     
     donneur=elimine_paires(donneur)
     humain=elimine_paires(humain)

     #Instance = 1, tour humain, instance = 2, tour ordi
     instance = 1
     while instance in range(1,2): 
        
        #Tour Humain
        if instance == 1:
            print('***********************************************************')
            #Montre main
            print('Votre tour.')
            print('Votre main est:')
            affiche_cartes(humain)
            
            #Prend carte
            print("J'ai ", len(donneur), "cartes. Si 1 est la position de ma première carte et ")
            print(len(donneur), "est la position de ma dernière carte, laquelle de mes cartes voulez-vous?")
            entree = entrez_position_valide(len(donneur))
            print("Vous avez demande ma ", entree+1,"-eme carte.")
            print("La voilà. C'est un ", donneur[entree])
            print("Avec ", donneur[entree]," ajoute, votre main est:")
            humain.append(donneur[entree])
            donneur.remove(donneur[entree])
            affiche_cartes(humain)
            humain=elimine_paires(humain)
            
            #Regarde si humain a gagne (sa main est vide)
            if len(humain)==0:
                print("J'ai terminé toutes les cartes.")
                win = True
                break
            
            #Attend joueur
            print("Après avoir défaussé toutes les paires et mélangé les cartes, votre main est:")
            affiche_cartes(humain)
            attend_le_joueur()
            
            #Tour a l'ordi
            instance = 2
            pass

        #Tour ordi
        if instance == 2:
            print('***********************************************************')

            #Prend carte
            print('Mon tour')
            prend_carte = random.randint(0,len(humain)-1)
            donneur.append(humain[prend_carte])
            humain.remove(humain[prend_carte])
            print("J'ai pris votre ", prend_carte+1,"eme carte")
            donneur=elimine_paires(donneur)

            #Regarde si l'ordi a gagner
            if len(donneur)==0:
                print("J'ai terminé toutes les cartes.")
                win = False
                break

            #Attend et apres commence tour humain
            attend_le_joueur()
            instance = 1
            pass
     
     #Message finale
     if win == True:
        print("Felicitations! Vous, Humain, vous avez gagne.")
     else:
        print("Vous avez perdu! Moi, Robot, j'ai gagne.")

# programme principale
joue()

