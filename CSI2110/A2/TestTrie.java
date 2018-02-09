import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

/*** 
 * This is class is for testing the class MyTrie.
 * 
 * author: Raudel Ravelo (rrave070@uottawa.ca)
 */


public class TestTrie {
    
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
  
    public static void Test1(){
    
        // This main method is doing a simple test on the class with 
  // the same tree as Figure 1-2 in the handout
  System.out.println("-------------------------------------------");
                System.out.println("Test1\n");
  MyTrie tree = new MyTrie();
  System.out.println("Populating the trie:");
                boolean result;
  int cnt = 0;
                
                System.out.println("Inserting 111: " + (result = tree.insert("111")));
                if(!result)
                {
                    cnt++;
                    System.out.println("Wrong Answer, Expected true, Given " + result);
                }
                
                int nod = 0;
                int counter = 0;
  System.out.println("Number of Nodes: " + (nod = tree.numNodes()));
                if(nod != 4)
                {
                    counter++;
                    System.out.println("Wrong Answer, Expected 4, Given " + nod);
                }
                
                System.out.println("Inserting 000: " + (result = tree.insert("000")));
                if(!result)
                {
                    cnt++;
                    System.out.println("Wrong Answer, Expected true, Given " + result);
                }
                System.out.println("Inserting 0: " + (result = tree.insert("0")));
                if(!result)
                {
                    cnt++;
                    System.out.println("Wrong Answer, Expected true, Given " + result);
                }
                System.out.println("Inserting 01: " + (result = tree.insert("01")));
                if(!result)
                {
                    cnt++;
                    System.out.println("Wrong Answer, Expected true, Given " + result);
                }
                System.out.println("Inserting 0100: " + (result = tree.insert("0100")));
                if(!result)
                {
                    cnt++;
                    System.out.println("Wrong Answer, Expected true, Given " + result);
                }
                System.out.println("Inserting 011: " + (result = tree.insert("011")));
                if(!result)
                {
                    cnt++;
                    System.out.println("Wrong Answer, Expected true, Given " + result);
                }
                System.out.println("Inserting 0101: " + (result = tree.insert("0101")));
                if(!result)
                {
                    cnt++;
                    System.out.println("Wrong Answer, Expected true, Given " + result);
                }
                
                nod = 0;
  System.out.println("Number of Nodes: " + (nod = tree.numNodes()));
                if(nod != 12)
                {
                    counter++;
                    System.out.println("Wrong Answer, Expected 12, Given " + nod);
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
                tree.printStringsInLexicoOrder();
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
                
                System.out.println("\nTest1 results:");
                System.out.println("Correct Answers (Inserting): " + (10-cnt) + "/10");
                System.out.println("Correct Answers (Number of Nodes): " + (2-counter) + "/2");
                System.out.println("Correct Answers (Lexicographical): " + (1-lex) + "/1");
  System.out.println("-------------------------------------------\n");
    }
    
   
    
    public static void Test2()
    {
        System.out.println("-------------------------------------------");
        System.out.println("Test2\n");
        MyTrie tree = new MyTrie();  
        int counter = 0;
        int nod = 0;
        boolean result;
        int cnt = 0;
        System.out.println("Inserting repeated strings:");

        tree.insert("000");
        System.out.println("Trying to insert 000 again: " + (result = tree.insert("000")));
        if(result)
        {
            cnt++;
            System.out.println("Wrong Answer, Expected false, Given " + result);
        }
        tree.insert("0100");
        System.out.println("Trying to insert 0100 again: " + (result = tree.insert("0100")));
        if(result)
        {
            cnt++;
            System.out.println("Wrong Answer, Expected false, Given " + result);
        }
        tree.insert("011");
        System.out.println("Trying to insert 011 again: " + (result = tree.insert("011")));
        if(result)
        {
            cnt++;
            System.out.println("Wrong Answer, Expected false, Given " + result);
        }
        tree.insert("111");
        System.out.println("Trying to insert 111 again: " + (result = tree.insert("111")));
        if(result)
        {
            cnt++;
            System.out.println("Wrong Answer, Expected false, Given " + result);
        }
        tree.insert("0000001100000");
        System.out.println("Trying to insert 0000001100000 again: " + (result = tree.insert("0000001100000")));
        if(result)
        {
            cnt++;
            System.out.println("Wrong Answer, Expected false, Given " + result);
        }

        nod = 0;
        System.out.println("Number of Nodes: " + (nod = tree.numNodes()));
        if(nod != 21)
        {
            counter++;
            System.out.println("Wrong Answer, Expected 21, Given " + nod);
        }
        System.out.println("\nTest2 results:");
        System.out.println("Correct Answers (Repeated strings): " + (5-cnt) + "/5");
        System.out.println("Correct Answers (Number of nodes): " + (1-counter) + "/1");
        System.out.println("-------------------------------------------\n");
    }
    
    public static void Test3()
    {
        System.out.println("-------------------------------------------");
        System.out.println("Test3\n");
        MyTrie tree = new MyTrie();
        System.out.println("Inserting: 0, 000, 01, 0100, 0101, 011, 111");
        tree.insert("0");
        tree.insert("000");
        tree.insert("01");
        tree.insert("0100");
        tree.insert("0101");
        tree.insert("011");
        tree.insert("111");
        boolean result;
        int cnt = 0;
        
        System.out.println("Testing search method:");
        System.out.println("Searching for 0: " + (result = tree.search("0")));
        if(!result)
        {
            cnt++;
            System.out.println("Wrong Answer, Expected false, Given " + result);
        }
        System.out.println("Searching for 010: " + (result = tree.search("010")));
        if(result)
        {
            cnt++;
            System.out.println("Wrong Answer, Expected false, Given " + result);
        }
        System.out.println("Searching for 1: " + (result = tree.search("1")));
        if(result)
        {
            cnt++;
            System.out.println("Wrong Answer, Expected false, Given " + result);
        }
        System.out.println("Searching for 111: " + (result = tree.search("111")));
        if(!result)
        {
            cnt++;
            System.out.println("Wrong Answer, Expected false, Given " + result);
        }
        System.out.println("Searching for 111: " + (result = tree.search("111")));
        if(!result)
        {
            cnt++;
            System.out.println("Wrong Answer, Expected false, Given " + result);
        }
        
        System.out.println("Searching for 01001: " + (result = tree.search("01001")));
        if(result)
        {
            cnt++;
            System.out.println("Wrong Answer, Expected false, Given " + result);
        }
        
        System.out.println("\nTest3 results:");
        System.out.println("Correct Answers (Searching): " + (6-cnt) + "/6");
        System.out.println("-------------------------------------------\n");
    }
    
    public static void Test5()
    {
        System.out.println("-------------------------------------------");
        System.out.println("Test5\n");
        
        System.out.println("Inserting long strings (100x 100 long strings, run 10 times) Expected time smaller than 60ms");
        int numberOfStrings = 100;
        int sizeOfStrings = 100;
        int timesRun = 10;
        long total = 0;
        Random r = new Random();
        MyTrie tree = new MyTrie();
        while(timesRun-->0)
        {
            tree = new MyTrie();
            long start = System.nanoTime()/(int)1e6;
            for(int i = 0; i < numberOfStrings; i++)
            {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < sizeOfStrings; j++)
                {
                    if(r.nextBoolean())
                        sb.append('0');
                    else sb.append('1');
                }
                tree.insert(sb.toString());
            }
            long end = System.nanoTime()/(int)1e6;
            total += (end-start);
        }
        System.out.println("\nTest5 results:");
        System.out.println("Time taken (miliseconds): " + (total));
        System.out.println("Number of nodes: " + tree.numNodes());
        System.out.println("-------------------------------------------\n");
    }
    
    public static void Test6()
    {
        System.out.println("-------------------------------------------");
        System.out.println("Test6\n");
        
        System.out.println("Inserting long strings (1x 1000 long string, run 10000 times) Expected time smaller than 1000ms");
        int numberOfStrings = 1;
        int sizeOfStrings = 1000;
        int timesRun = 10000;
        long total = 0;
        Random r = new Random();
        MyTrie tree = new MyTrie();
        while(timesRun-->0)
        {
            tree = new MyTrie();
            long start = System.nanoTime()/(int)1e6;
            for(int i = 0; i < numberOfStrings; i++)
            {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < sizeOfStrings; j++)
                {
                    if(r.nextBoolean())
                        sb.append('0');
                    else sb.append('1');
                }
                tree.insert(sb.toString());
            }
            long end = System.nanoTime()/(int)1e6;
            total += (end-start);
        }
        System.out.println("\nTest6 results:");
        System.out.println("Time taken (miliseconds): " + (total));
        System.out.println("Number of nodes: " + tree.numNodes());
        System.out.println("-------------------------------------------\n");
    }
    
    
    public static void Test7()
    {
        System.out.println("-------------------------------------------");
        System.out.println("Test7\n");
        
        System.out.println("Inserting long strings (500x 500 long strings, run 10 times) Expected time smaller than 500ms");
        int numberOfStrings = 500;
        int sizeOfStrings = 500;
        int timesRun = 10;
        long total = 0;
        Random r = new Random();
        MyTrie tree = new MyTrie();
        while(timesRun-->0)
        {
            tree = new MyTrie();
            long start = System.nanoTime()/(int)1e6;
            for(int i = 0; i < numberOfStrings; i++)
            {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < sizeOfStrings; j++)
                {
                    if(r.nextBoolean())
                        sb.append('0');
                    else sb.append('1');
                }
                tree.insert(sb.toString());
            }
            long end = System.nanoTime()/(int)1e6;
            total += (end-start);
        }
        System.out.println("\nTest7 results:");
        System.out.println("Time taken (miliseconds): " + (total));
        System.out.println("Number of nodes: " + tree.numNodes());
        System.out.println("-------------------------------------------\n");
    }
    
    
    
    public static void Test8()
    {
        System.out.println("-------------------------------------------");
        System.out.println("Test8\n");
        
        System.out.println("Inserting long strings (1000x 1000 long strings, run 10 times) Expected time smaller than 1200ms");
        int numberOfStrings = 1000;
        int sizeOfStrings = 1000;
        int timesRun = 10;
        long total = 0;
        Random r = new Random();
        MyTrie tree = new MyTrie();
        while(timesRun-->0)
        {
            tree = new MyTrie();
            long start = System.nanoTime()/(int)1e6;
            for(int i = 0; i < numberOfStrings; i++)
            {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < sizeOfStrings; j++)
                {
                    if(r.nextBoolean())
                        sb.append('0');
                    else sb.append('1');
                }
                tree.insert(sb.toString());
            }
            long end = System.nanoTime()/(int)1e6;
            total += (end-start);
        }
        System.out.println("\nTest8 results:");
        System.out.println("Time taken (miliseconds): " + (total));
        System.out.println("Number of nodes: " + tree.numNodes());
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
            
      System.out.println("Printing tree inOrder:");
      tree.printInOrder();
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
