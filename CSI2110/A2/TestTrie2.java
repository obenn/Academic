import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Name: Oliver Benning
 * Student Number: 7798804
 * Uottawa Email: obenn009@uottawa.ca
 * 
 *
 */

public class TestTrie2 {
    public static void Test1() {
        System.out.println("----------------------\nTest 1:");
        System.out.println("Testng insert() method.");
        MyTrie tree = new MyTrie();
        System.out.println("Inserting 0, 1, 00, 101, 110, 1111, 1101");
        // Test initial return values.
        if (
        !tree.insert("0")    ||
        !tree.insert("1")    ||
        !tree.insert("00")   ||
        !tree.insert("101")  ||
        !tree.insert("110")  ||
        !tree.insert("1111") ||
        !tree.insert("1101")
        ) System.out.println( "Got false boolean inserting for the first time, check return values.");
        
        // 10 or 11 depending on whether root is included
        if ( tree.numNodes() == 10 || tree.numNodes() == 11 ) {
            System.out.println( "Correct number of nodes." );
        } else {
            System.out.println( "Wrong number of nodes." );
        }

        if (
        tree.insert("0")    ||
        tree.insert("1")    ||
        tree.insert("00")   ||
        tree.insert("101")  ||
        tree.insert("110")  ||
        tree.insert("1111") ||
        tree.insert("1101")
        ) System.out.println( "Got true boolean inserting a second time, check return values.");

    }
    public static void Test2() {
        System.out.println("----------------------\nTest 2:");
        System.out.println("Testing search() method.");
        MyTrie tree = new MyTrie();
        System.out.println("Inserting 0, 001, 1011, 1100, 111, 110111");
        tree.insert("0");
        tree.insert("001");
        tree.insert("1011");
        tree.insert("1100");
        tree.insert("111");
        tree.insert("110111");
        if (
            !tree.search("0")    ||
            !tree.search("001")  ||
            !tree.search("1011") ||
            !tree.search("1100") ||
            !tree.search("111")  ||
            !tree.search("110111")
        ) System.out.println("False boolean when searching for existing nodes, test failed.");
        else if (
            tree.search("0101") ||
            tree.search("1") ||
            tree.search("10101010010100")
        ) System.out.println("True boolean when searching for non-existing nodes, test failed.");
        else
          System.out.println("Search test passed.");
    }
    public static void Test3() {
        System.out.println("----------------------\nTest 3:");
        System.out.println("Testing printStringsInLexicoOrder() method.");
        System.out.println("Inserting 0, 001, 1011, 1100, 111, 110111");
        MyTrie tree = new MyTrie();
        tree.insert("0");
        tree.insert("001");
        tree.insert("1011");
        tree.insert("1100");
        tree.insert("111");
        tree.insert("110111");

        System.out.println("Printing Strings in Lexicographical Order:");
                
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        tree.printStringsInLexicoOrder();
        System.out.flush();
        System.setOut(old);
        System.out.print(baos.toString());

        String[] correct = new String[]{"0","001","1011","1100","110111","111"};
        if(!cmpLexTest1(baos.toString(), correct)) {
            System.out.println("\nWrong Lexicographical order.");
            System.out.println("Expected: 0,001,1011,1100,110111,111");
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
        Test3();
    }
}