import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

/*** 
 * This is class is for testing the class MyCompressedTrie.
 * 
 * author: Raudel Ravelo (rrave070@uottawa.ca)
 */


public class TestCompressedTrie {
    
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
    
    public static void Test1()
    {
        System.out.println("-------------------------------------------");
        System.out.println("Test1\n");
        MyTrie tree = new MyTrie();
        System.out.println("Populating the trie wiht: 111, 01, 0, 0100, 000, 011, 0101");
        tree.insert("111");
        tree.insert("01");
        tree.insert("0");
        tree.insert("0100");
        tree.insert("000");
        tree.insert("011");
        tree.insert("0101");
        System.out.println("Number of Nodes in the Trie: " + tree.numNodes());
        System.out.println("\nTest1 results:");
        MyCompressedTrie compTree= new MyCompressedTrie(tree);
        int nod = 0;
        int counter = 0;
        System.out.println("Number of Nodes in the compressed Trie: " + (nod = compTree.numNodes()));
        if(nod != 9)
        {
            counter++;
            System.out.println("Wrong Answer, Expected 9, Given " + nod);
        }
        
        System.out.println("Printing Strings in Lexicographical Order:");
        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        // Print some output: goes to your special stream
        compTree.printStringsInLexicoOrder();
        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
        System.out.print(baos.toString());
        int lex = 0;
        String[] correct = new String[]{"0","000","01","0100","0101","011","111"};
        if(!cmpLexTest1(baos.toString(), correct))
        {
            lex++;
            System.out.println("Wrong Answer (Lexicographical Order)");
            System.out.println("Expected: 0,000,01,0100,0101,011,111");
        }
        
        System.out.println("Correct Answers (Number of Nodes): " + (1-counter) + "/1");
        System.out.println("Correct Answers (Lexicographical): " + (1-lex) + "/1");
        System.out.println("-------------------------------------------\n");
    }
    
    public static void Test2()
    {
        System.out.println("-------------------------------------------");
        System.out.println("Test2\n");
        MyTrie tree =new MyTrie();
        System.out.println("Populating the trie with: 0001, 00010101");
        tree.insert("0001");
        tree.insert("00010101");
        System.out.println("Number of Nodes in the Trie: " + tree.numNodes());

        System.out.println("\nTest2 results:");
        MyCompressedTrie compTree= new MyCompressedTrie(tree);//should compress like this: (0001)->(0101)
        int nod = 0;
        int counter = 0;
        System.out.println("Number of Nodes in the compressed Trie: " + (nod = compTree.numNodes()));
        if(nod != 3)
        {
            counter++;
            System.out.println("Wrong Answer, Expected 3, Given " + nod);
        }
        System.out.println("Printing Strings in Lexicographical Order:");
        
        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        // Print some output: goes to your special stream
        compTree.printStringsInLexicoOrder();
        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
        System.out.print(baos.toString());
        int lex = 0;
        String[] correct = new String[]{"0001", "00010101"};
        if(!cmpLexTest1(baos.toString(), correct))
        {
            lex++;
            System.out.println("Wrong Answer (Lexicographical Order)");
            System.out.println("Expected: 0001, 00010101");
        }
        System.out.println("Correct Answers (Number of Nodes): " + (1-counter) + "/1");
        System.out.println("Correct Answers (Lexicographical): " + (1-lex) + "/1");
        System.out.println("-------------------------------------------\n");
            
    }
    
    public static void Test8()
    {
        System.out.println("-------------------------------------------");
        System.out.println("Test8\n");
        
        System.out.println("Compressing long strings (1000 x 1000 long strings, run 10 times). Expected time smaler than 1000ms");
        
        MyTrie tree = new MyTrie();
        int numberOfStrings = 1000;
        int sizeOfStrings = 1000;
        int timesRun = 10;
        
        while(numberOfStrings-->0){
            StringBuilder sb = new StringBuilder();
            Random r = new Random();
            
            for(int i = 0; i < sizeOfStrings; i++)
            {
                if(r.nextBoolean())
                    sb.append('0');
                else sb.append('1');
            }
            tree.insert(sb.toString());
        }
        
        long timeTaken = 0;
        
        
        MyCompressedTrie cmpTree = new MyCompressedTrie();
        while(timesRun-->0)
        {
            long start = System.nanoTime()/(int)1e6;
            cmpTree = new MyCompressedTrie(tree);
            long end = System.nanoTime()/(int)1e6;
            timeTaken += end - start;
        }
        
        System.out.println("\nTes8 results:");
        System.out.println("Time taken (miliseconds): " + timeTaken);
        System.out.println("Number of nodes in the Trie: " + tree.numNodes());
        System.out.println("Number of nodes in the compressed Trie: " + cmpTree.numNodes());
        System.out.println("-------------------------------------------\n");
    
    }
    
    public static void Test7()
    {
        System.out.println("-------------------------------------------");
        System.out.println("Test7\n");
        
        System.out.println("Compressing long strings (500 x 500 long strings, run 10 times). Expected time smaler than 200ms");
        
        MyTrie tree = new MyTrie();
        int numberOfStrings = 500;
        int sizeOfStrings = 500;
        int timesRun = 10;
        
        while(numberOfStrings-->0){
            StringBuilder sb = new StringBuilder();
            Random r = new Random();
            
            for(int i = 0; i < sizeOfStrings; i++)
            {
                if(r.nextBoolean())
                    sb.append('0');
                else sb.append('1');
            }
            tree.insert(sb.toString());
        }
        
        long timeTaken = 0;
        
        
        MyCompressedTrie cmpTree = new MyCompressedTrie();
        while(timesRun-->0)
        {
            long start = System.nanoTime()/(int)1e6;
            cmpTree = new MyCompressedTrie(tree);
            long end = System.nanoTime()/(int)1e6;
            timeTaken += end - start;
        }
        
        System.out.println("\nTes7 results:");
        System.out.println("Time taken (miliseconds): " + timeTaken);
        System.out.println("Number of nodes in the Trie: " + tree.numNodes());
        System.out.println("Number of nodes in the compressed Trie: " + cmpTree.numNodes());
        System.out.println("-------------------------------------------\n");
    
    }
    
    public static void Test4()
    {
      System.out.println("-------------------------------------------");
      System.out.println("Test4\n");          
      MyTrie tree = new MyTrie();
      System.out.println("Inserting: 0, 000, 01, 0100, 0101, 011, 111");
      tree.insert("111");
      tree.insert("0");
      tree.insert("01");
      tree.insert("0100");
      tree.insert("000");
      tree.insert("011");
      tree.insert("0101");
      MyCompressedTrie compTree= new MyCompressedTrie(tree);      
      System.out.println("Printing tree inOrder:");
      compTree.printInOrder();
      System.out.println("-------------------------------------------\n");
    }
    
    public static void Test5()
    {
        System.out.println("-------------------------------------------");
        System.out.println("Test5\n");
        
        System.out.println("Compressing long strings (100 x 100 long strings, run 10 times). Expected time smaler than 60ms");
        
        MyTrie tree = new MyTrie();
        int numberOfStrings = 100;
        int sizeOfStrings = 100;
        int timesRun = 10;
        
        while(numberOfStrings-->0){
            StringBuilder sb = new StringBuilder();
            Random r = new Random();
            
            for(int i = 0; i < sizeOfStrings; i++)
            {
                if(r.nextBoolean())
                    sb.append('0');
                else sb.append('1');
            }
            tree.insert(sb.toString());
        }
        
        long timeTaken = 0;
        
        
        MyCompressedTrie cmpTree = new MyCompressedTrie();
        while(timesRun-->0)
        {
            long start = System.nanoTime()/(int)1e6;
            cmpTree = new MyCompressedTrie(tree);
            long end = System.nanoTime()/(int)1e6;
            timeTaken += end - start;
        }
        
        System.out.println("\nTes5 results:");
        System.out.println("Time taken (miliseconds): " + timeTaken);
        System.out.println("Number of nodes in the Trie: " + tree.numNodes());
        System.out.println("Number of nodes in the compressed Trie: " + cmpTree.numNodes());
        System.out.println("-------------------------------------------\n");
    
    }
    
    public static void Test6()
    {
        System.out.println("-------------------------------------------");
        System.out.println("Test6\n");
        
        System.out.println("Compressing long strings (1x 1000 long strings, run 10000 times). Expected time smaler than 1000ms");
        
        MyTrie tree = new MyTrie();
        int numberOfStrings = 1000;
        int sizeOfStrings = 1000;
        int timesRun = 10;
        
        while(numberOfStrings-->0){
            StringBuilder sb = new StringBuilder();
            Random r = new Random();
            
            for(int i = 0; i < sizeOfStrings; i++)
            {
                if(r.nextBoolean())
                    sb.append('0');
                else sb.append('1');
            }
            tree.insert(sb.toString());
        }
        
        long timeTaken = 0;
        
        
        MyCompressedTrie cmpTree = new MyCompressedTrie();
        while(timesRun-->0)
        {
            long start = System.nanoTime()/(int)1e6;
            cmpTree = new MyCompressedTrie(tree);
            long end = System.nanoTime()/(int)1e6;
            timeTaken += end - start;
        }
        
        System.out.println("\nTest6 results:");
        System.out.println("Time taken (miliseconds): " + timeTaken);
        System.out.println("Number of nodes in the Trie: " + tree.numNodes());
        System.out.println("Number of nodes in the compressed Trie: " + cmpTree.numNodes());
        System.out.println("-------------------------------------------\n");
    
    }
    
    public static void Test3()
    {
        System.out.println("-------------------------------------------");
        System.out.println("Test3\n");
        MyTrie tree =new MyTrie();
        System.out.println("Populating the trie with: 111, 000");
        tree.insert("111");
        tree.insert("000");
        System.out.println("Number of Nodes in the Trie: " + tree.numNodes());

        System.out.println("\nTest3 results:");
        MyCompressedTrie compTree= new MyCompressedTrie(tree);
        int nod = 0;
        int counter = 0;
        System.out.println("Number of Nodes in the compressed Trie: " + (nod = compTree.numNodes()));
        if(nod != 3)
        {
            counter++;
            System.out.println("Wrong Answer, Expected 3, Given " + nod);
        }
        System.out.println("Printing Strings in Lexicographical Order:");
        
        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        // Print some output: goes to your special stream
        compTree.printStringsInLexicoOrder();
        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
        System.out.print(baos.toString());
        int lex = 0;
        String[] correct = new String[]{"000", "111"};
        if(!cmpLexTest1(baos.toString(), correct))
        {
            lex++;
            System.out.println("Wrong Answer (Lexicographical Order)");
            System.out.println("Expected: 000, 111");
        }
        System.out.println("Correct Answers (Number of Nodes): " + (1-counter) + "/1");
        System.out.println("Correct Answers (Lexicographical): " + (1-lex) + "/1");
        System.out.println("-------------------------------------------\n");
    }
    
    public static void main(String[] args) {
    
        Test1();
        Test2();
        Test3();
        Test4();
        Test5();
        Test6();
        Test7();
        Test8();
          
        
    }
    
    
}
