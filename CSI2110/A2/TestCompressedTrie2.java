import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Name: Oliver Benning
 * Student Number: 7798804
 * Uottawa Email: obenn009@uottawa.ca
 * 
 *
 */

public class TestCompressedTrie2 {
	

    public static void Test1() {
    	System.out.println("----------------------\nTest 1:");
    	
    	System.out.println("Testing contructor method:");

    	MyTrie tree = new MyTrie();
    	System.out.println("Inserting 0, 1, 00, 101, 110, 1111, 1101 into Trie and compressing");
    	tree.insert("0");
    	tree.insert("1");
    	tree.insert("00");
    	tree.insert("101");
    	tree.insert("110");
    	tree.insert("1111");
    	tree.insert("1101");
    	MyCompressedTrie newTree = new MyCompressedTrie(tree);
    	if ( tree.numNodes() - 2 == newTree.numNodes() ) {
    		System.out.println( "Number of nodes is correct, passed." );
    	} else {
    		System.out.println( "Number of nodes is incorrect, failed." );
    	}

    	System.out.println("----------------------");
    }

    public static void Test2() {
    	System.out.println("----------------------\nTest 2:");
    	
    	System.out.println("Testing printStringsInLexicoOrder():");
    	MyTrie tree = new MyTrie();
    	System.out.println("Inserting 0, 1, 001, 1010, 110111, 11, 00");
    	tree.insert("0");
    	tree.insert("1");
    	tree.insert("001");
    	tree.insert("1010");
    	tree.insert("110111");
    	tree.insert("11");
    	tree.insert("00");
    	MyCompressedTrie newTree = new MyCompressedTrie(tree);
    	if ( newTree.numNodes()  != tree.numNodes() - 5 ) {
    		System.out.println( "Tree construction is flawed, exiting test");
    		return;
    	}
    	System.out.println("Printing Strings in Lexicographical Order:");
                
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        tree.printStringsInLexicoOrder();
        System.out.flush();
        System.setOut(old);
        System.out.print(baos.toString());

        String[] correct = new String[]{"0","00","001","1","1010","11","110111"};
        if(!cmpLexTest1(baos.toString(), correct)) {
            System.out.println("\nWrong Lexicographical order.");
            System.out.println("Expected: 0,00,001,1,1010,11,110111");
        }
        else {
            System.out.println("\nCorrect Lexicographical order.");
        }

    }

    public static boolean cmpLexTest1(String a, String[] correct)
    {
        String[] s = a.split("[,]+|[ ]+|[\\r\\n]+", 0);
        
        if(s.length != correct.length)
            return false;
        for(int i = 0; i < correct.length; i++)
            if(!correct[i].equals(s[i]))
                return false;
        return true;
    }

    public static void main(String[] args) {
    	Test1();	
    	Test2();
    }
}