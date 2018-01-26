
import java.util.Scanner;
import java.util.Random;

/**
 * The class <b>A1Q4</b> is an implementation of the
 * ``Old Maid'' card game, based on the Python implementation
 * given in ITI1020
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

public class A1Q4{

	/**
	* Array used to store the full deck of cards,
	*/
	private static String[] deck;

	/**
	* The current number of cards in the full deck of cards
	*/
	private static int sizeDeck;

	/**
	* Array used to store the player's deck of cards
	*/
	private static String[] playerDeck;

	/**
	* The current number of cards in the player's deck of cards
	*/
	private static int sizePlayerDeck;

	/**
	* Array used to store the computer's deck of cards
	*/
	private static String[] computerDeck;

	/**
	* The current number of cards in the computer's deck of cards
	*/
	private static int sizeComputerDeck;


	/**
	* An instance of java.util.Scanner, which will get input from the
	* standard input
	*/
 	private static Scanner sc;

	/**
	* An instance of java.util.Random, to generate random numbers
	*/
 	private static Random generator;

	/** 
     * Constructor of the class. Creates the full deck of cards
     */
 
 	public  A1Q4(){
		
		sc = new Scanner(System.in);
		generator = new Random();

		String[] suits = {"\u2660", "\u2661", "\u2662", "\u2663"};
		String[] ranks = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		sizeDeck = suits.length*ranks.length - 1;
		deck = new String[sizeDeck];
		int index = 0;
		for(int i =0 ; i < ranks.length; i++){
			for(int j =0 ; j < suits.length; j++){
				if(!(ranks[i]=="Q" && suits[j]=="\u2663")){
					deck[index++]= ranks[i] + " of " + suits[j];
				}
			}
		}
	}

	/** 
     * Waits for user input
     */
	private static void waitForUserInput(){
		System.out.print("Please press enter:");
		sc.nextLine();
	}

	/**
	*  Deals the cards, taking sizeDeck cards out of deck, and deals them
	*  into playerDeck and computerDeck, starting with playerDeck
	*/
	private static void dealCards(){

		for (int i = 0; i < sizeDeck; i++){

			if (i%2==0){ //Player
				ArrayStringsTools.appendItem(playerDeck, sizePlayerDeck, deck[i]);
				sizePlayerDeck++;
			}

			else{ //Computer
				ArrayStringsTools.appendItem(computerDeck, sizeComputerDeck, deck[i]);
				sizeComputerDeck++;
			}
			
		}

	}

	/**
	*  Removes all the pairs of cards from the array deckOfCards, that currently
	* contains currentSize cards. deckOfCards is unsorted. It should also not
	* be sorted once the method terminates. 
	*
    *   @param deckOfCards the array of Strings representing the deck of cards
    *   @param currentSize the number of strings in the arrayOfStrings,
    *			stored from arrayOfStrings[0] to arrayOfStrings[currentSize-1] 
    *   @return the number of cards in deckOfCards once the pair are removed
    */

	private static int removePairs(String[] deckOfCards, int currentSize){
		
		boolean noPair = true;
		int i = 0;
		
		while (i < currentSize){
			
			noPair = true;
			
			for (int j = i+1; j < currentSize; j++) {
				
				if (deckOfCards[i].charAt(0) == deckOfCards[j].charAt(0)){
					currentSize = ArrayStringsTools.removeItemByIndex(deckOfCards, currentSize, j);
					currentSize = ArrayStringsTools.removeItemByIndex(deckOfCards, currentSize, i);
					
					i = 0;
					noPair = false;
					break;
				}
			}
			//Increment if prev for didn't find pair
			if (noPair){
				i++;	
			}
		}
		return currentSize;
	}//removePairs

	/**
	*  Get a valid index of a card to be removed from computerDeck.
	*	Note: this method does not check that the input is indeed an integer and
	*	will crash if something else is provided.
	*  @return the valid input.
	*/
	private static int getValidInput(){
	
	System.out.println("I have "+sizeComputerDeck+" cards. If 1 stands for my first card and");
	System.out.println((sizeComputerDeck)+" for my last card. Which card would you like?");
	
	System.out.print("Please enter a value: ");
	int temp = Integer.parseInt(sc.nextLine());

	//Range check
	while (temp < 1 || temp > sizeComputerDeck){
		System.out.println("Please try again.");
		temp = Integer.parseInt(sc.nextLine());
	}
	
	//User input -1, avoid OBOE
	return temp-1;
	}


	/**
	*  The actual game, as per the Python implementation
	*/
	public static void playGame(){

	//Init decks, shuffle, deal
	playerDeck = new String[sizeDeck];
	computerDeck = new String[sizeDeck];
	sizePlayerDeck = 0;
	sizeComputerDeck = 0;
 	ArrayStringsTools.shuffleArray(deck, sizeDeck);
 	dealCards();


 	//Initial dialogue, remove pairs.
 	System.out.println("Hello. My name is Robot and I am the dealer.");
 	System.out.println("Welcome to my card game!");
 	System.out.println("Your current deck of cards is:");

 	ArrayStringsTools.printArray(playerDeck, sizePlayerDeck);

 	System.out.println("Do not worry. I cannot see the order of your cards.");
 	System.out.println("Now discard all the pairs from your deck. I will do the same.");

 	sizeComputerDeck = removePairs(computerDeck, sizeComputerDeck);
 	sizePlayerDeck = removePairs(playerDeck, sizePlayerDeck);
 	waitForUserInput();


 	//Start of turns
 	boolean playerTurn = true;
 	int cardposition;
 	while ((sizeComputerDeck > 0) && (sizePlayerDeck > 0)){
 		
 		//Player
 		if (playerTurn == true){
 			
 			System.out.println("***********************************************************");
 			System.out.println("Your turn.");
 			System.out.println("Your current deck of cards is:");

 			ArrayStringsTools.printArray(playerDeck, sizePlayerDeck);

 			cardposition = getValidInput();

 			System.out.println("You asked for card "+(cardposition+1)+".");
 			System.out.println("Here it is. It is "+computerDeck[cardposition]+".");
 			System.out.println("With "+computerDeck[cardposition]+" added, your current deck of cards is:");

 			sizePlayerDeck = ArrayStringsTools.appendItem(playerDeck, sizePlayerDeck, computerDeck[cardposition]);
 			sizeComputerDeck = ArrayStringsTools.removeItemByIndex(computerDeck, sizeComputerDeck, cardposition);
 			ArrayStringsTools.printArray(playerDeck, sizePlayerDeck);

 			System.out.println("And after discarding pairs and shuffling it is:");

 			sizePlayerDeck = removePairs(playerDeck, sizePlayerDeck);
 			ArrayStringsTools.shuffleArray(playerDeck, sizePlayerDeck);
 			ArrayStringsTools.printArray(playerDeck, sizePlayerDeck);

 			waitForUserInput();

 			playerTurn = false;
 		}

 		//Computer
 		else{

 			System.out.println("***********************************************************");
 			System.out.println("My turn.");

 			cardposition = generator.nextInt(sizePlayerDeck);

 			sizeComputerDeck = ArrayStringsTools.appendItem(computerDeck, sizeComputerDeck, playerDeck[cardposition]);
 			sizePlayerDeck = ArrayStringsTools.removeItemByIndex(playerDeck, sizePlayerDeck, cardposition);
 			sizeComputerDeck = removePairs(computerDeck, sizeComputerDeck);

 			System.out.println("I took card "+ (cardposition+1)+".");

 			waitForUserInput();

 			playerTurn = true;
 		}
 	}//while

 	//Win-check Computer
 	if (sizeComputerDeck == 0) {
 		System.out.println("I do not have any more cards");
 		System.out.println("You lost! I, Robot, win");
 	}
 	//Win-check Player
 	else {
 		System.out.println("***********************************************************");
 		System.out.println("You do not have any more cards");
 		System.out.println("Congratulations! You, Human, win");
 	
 	}//while
	}//getValidInput


	/**
     * The main method of this program. Creates the game object
     * and calls the playGame method on it.
     * @param args ignored
	 */    

 
	public static void main(String[] args){
	
		A1Q4 game = new A1Q4();		

		game.playGame();
	}
}