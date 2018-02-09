import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;

import net.datastructures.*;

/**
*	CSI 2110 Fall 2017 taught by Dr. Lucia Moura
*	Assignment 4
*	By Oliver Benning
*	obenn009@uottawa.ca
*	7798804
*/
public class ParisMetro {
	// To store the metro's graph datastructure
	private static Graph<Integer, Integer> metro;
	// Array of station names, used only for output
	private static String[] stationNames;
	// ArrayList of vertices in the graph
	private static ArrayList<Vertex<Integer>> vertices = new ArrayList<Vertex<Integer>>();
	// Final variable to store the walking distance
	private static final int WALK_DISTANCE = 90;


	/**
	*	Main method
	*	Param: String array of arguments from command line
	*	Return: void
	*/
	public static void main( String[] args ) throws Exception, IOException {
		// Question 1, construct metro Graph object from metro.txt file
		readMetro( "metro.txt" );

		// Question 2 i)
		if ( args.length == 1 ) {
			// Get the queue of stations along line from appropriate method
			Queue<Vertex<Integer>> q = stationsAlongLine(Integer.parseInt(args[0]));
			// Empty the queue printing each of it's elements
			while(!q.isEmpty()) {
				int n = q.dequeue().getElement();
				String s = stationNames[n];
				System.out.println( n + ": " + s );
			}
		}
		// Question 2 ii)
		else if ( args.length == 2 ) {
			// Call printS on shortest path using -1 as the third parameter to exclude it
			printS(shortestPath(Integer.parseInt(args[0]), Integer.parseInt(args[1]), -1 ));
			
		}
		// Question 2 iii)
		else if ( args.length == 3 ) {
			// Call printS on shortest path with args[2]'s lines excluded
			printS(shortestPath(Integer.parseInt(args[0]), Integer.parseInt(args[1]),
			Integer.parseInt(args[2])));
		}
	}

	/**
	*	Helper method to read initial file and construct graph for Question 1
	*	Param: File name string
	*	Return: void
	*	---
	*	This method draws heavily from:
	*	SimpleGraph.java by Jochen Lang from the CSI2110 lab #9.
	*/
	public static void readMetro(String filename) throws Exception, IOException {
		String line;
		StringTokenizer st;
		// Initialize metro as a directed graph with integer valued vertices and edges
		metro = new AdjacencyMapGraph<Integer, Integer>(true);
		BufferedReader file = new BufferedReader(new FileReader(filename));  

		// Grab the next line from the BufferedReader, skipping it
		line = file.readLine();

		// Initialize station names array with fixed size corresponding to the number of edges
		// I could get this number from the document but I was encountering format issues 
		stationNames = new String[376];

		// First "half" of document, get the station names associated with their line number and collect the verices.
		// The names are purely cosmetic for the output and serve little function
		while(!(line = file.readLine()).equals("$") ) {
			// Tokenize the line
			st = new StringTokenizer(line);
			// Length of name is number of tokens minus the line number token
			int nameSize = st.countTokens() - 1;
			// Parse the first integer to act as our vertex
			int vertex = Integer.parseInt(st.nextToken());

			// Reassemble the station name and...
			String stationName = st.nextToken();
			for (int i = 1; i < nameSize; i++) {
				stationName = stationName + " " + st.nextToken();
			}
			// ... store it in the array corresponding to the vertex
			stationNames[vertex] = stationName;

			// Add the integer vertex to our Graph
			vertices.add(metro.insertVertex(vertex));
		}

		// Going through the second half of our doc and grabbing the edges between stations
		while( ( line = file.readLine() ) != null ) {
			st = new StringTokenizer(line);
			int fromVertex, toVertex, distance;
			fromVertex = Integer.parseInt(st.nextToken());
			toVertex = Integer.parseInt(st.nextToken());
			distance = Integer.parseInt(st.nextToken());
			// Insert the edge using the information we obtained from each line
			metro.insertEdge(vertices.get(fromVertex), vertices.get(toVertex), distance);
		}
	}


	/**
	*	Helper method for question 2 ii) and 2 iii)
	*	prints predetermined outputs for special conditions 0 and -1
	*	else it will print the stack populated by shortest path and the min value
	*	Param: the path length value
	*	Return: void
	*/
	private static void printS(int min) {
		if (min == 0) {
			System.out.println("You're already there!");
		} else if (min == -1) {
			System.out.println("It is impossible to reach your destination.");
		} else {
			while (!D.s.isEmpty()) {
				int i = D.s.pop().getElement();
				System.out.println(i + " " + stationNames[i]);
			}
			System.out.println("With a travel time of " + min + " seconds");
			if (min >= 60) {
				System.out.println("which is " + (int)min/60 +" minutes and " + min%60 +" seconds.");
			}
		}
	}


	/**
	*	Main method for question 2 i)
	*	Param: integer value of the initial station
	*	Return: the queue containing all the vertices along the line of the intial station
	*/
	public static Queue<Vertex<Integer>> stationsAlongLine(int station) {
		ArrayList<Vertex<Integer>> stationsAlongLine = new ArrayList<Vertex<Integer>>();
		Queue<Vertex<Integer>> q = new LinkedQueue<Vertex<Integer>>();
		Vertex<Integer> vert = vertices.get(station);

		// Initially call our recursive method
		propagateAdd(stationsAlongLine, vert);

		// Empty our ArrayList into the return queue
		for(Vertex<Integer> v : stationsAlongLine) {
			q.enqueue(v);
		}
		return q;
	}
	/**
	*	Helper recursive algorithm to traverse line and add each element not yet in the list as we go
	*	the output is not from one end of the line to the other as that was not required
	*	Param: ArrayList of stations along line and Vertice for current method passthrough
	*	Return: void
	*/
	public static void propagateAdd(ArrayList<Vertex<Integer>> stationsAlongLine, Vertex<Integer> vert) {
		// Cycle through each outgoing edge
		for(Edge<Integer> e : metro.outgoingEdges(vert)) {
			// Skip if a walking edge, meaning the next vertice is not on the same line
			if (e.getElement() == -1) continue;
			// Save the vertex on the opposite end of the edge
			Vertex<Integer> nextVert = metro.endVertices(e)[1];

			// If the value is not yet contained in the array
			if (!stationsAlongLine.contains(nextVert)) {
				// Add the value to the list
				stationsAlongLine.add(nextVert);
				// Continue traversal with the respective vertice
				propagateAdd(stationsAlongLine, nextVert);
			}
		}
		// Cycle through each incoming edge, very similar logic
		for(Edge<Integer> e : metro.incomingEdges(vert)) {
			// Skip if a walking edge, meaning the next vertice is not on the same line
			if (e.getElement() == -1) continue;
			// Save the vertex on the opposite end of the edge
			Vertex<Integer> nextVert = metro.endVertices(e)[0];

			// If the value is not yet contained in the array list
			if (!stationsAlongLine.contains(nextVert)) {
				// Add the value to the list
				stationsAlongLine.add(nextVert);
				// Continue traversal with the respective vertice
				propagateAdd(stationsAlongLine, nextVert);
			}
		}
	}

	/**
	*	Helper class, it only serves to hold the data structes used in the recursive Dijkstra
	*	method and to prevent an excessive amount of parameters from being passed
	*/
	private static class D {
		public final static Stack<Edge<Integer>> edges = new LinkedStack<Edge<Integer>>();
		public final static Map<Vertex<Integer>, Integer> disc = new ProbeHashMap<>();
		public final static Map<Vertex<Integer>, Integer> cloud = new ProbeHashMap<>();
		public final static PriorityQueue<Integer, Edge<Integer>> pq = new UnsortedPriorityQueue<Integer, Edge<Integer>>();
		public final static Stack<Vertex<Integer>> s = new LinkedStack<Vertex<Integer>>();
	}

	/**
	*	Question 2 ii) and iii)
	*	function to compute shortest path using Dijkstra's algorithm
	*	datastructures stored in the D object are populated during it's run
	*	it also returns the min value.
	*	Param: Integer values for source vertex, destination vertex and optional
	*	exclusion vertex (-1 not specified)
	*	Return: Final value for shortest path
	*/
	public static int shortestPath(int n1, int n2, int n3 ){
		int min = 0;
		// Convert int values to appropriate verices
		Vertex<Integer> v1 = vertices.get(n1);
		Vertex<Integer> v2 = vertices.get(n2);

		// If the exclusion vertex was specified
		if (n3 != -1) {
			// Grab the queue of vertices along the same line
			Queue<Vertex<Integer>> q = stationsAlongLine(n3);
			while (!q.isEmpty()) {
				Vertex<Integer> vx = q.dequeue();
				// Check if it's one of our start/end vertices, in which case we have no path
				if (vx == v1 || vx == v2) {
					return -1;
				}
				// Otherwise put it in the cloud right away so the algorithm thinks it's already visited
				// and therefore excludes it from the path
				D.cloud.put(vx, Integer.MAX_VALUE);
			}
		// If our start and end are the same then we're already there
		} if (n1 == n2) {
			return 0;
		}

		// Call our recursive method, after this call we'll have an approriate min
		// as well as our structues in D populated
		min = dijkstraRecursive(v1, v2, min);

		// We have our edges but now we need to use them to backtrack our path from v2
		// to v1, we know our last edge is connected to v2, then we keep going down the stack
		// finding the edge who's destination is the previous edges origin, ignoring unrelated ones.
		// We push the vertices on a stack so we can empty it later to find the path from v1 to v2.
		while(!D.edges.isEmpty()) {
			Edge<Integer> e = D.edges.pop();
			if (metro.endVertices(e)[1] == v2) {
				D.s.push(v2);
				v2 = metro.endVertices(e)[0];
			}
			if (v2 == v1) {
				D.s.push(v1);
				return min;
			}
		}

		// Finish method
		return min;
	}
	/**
	*	Recursive helper method for shortestPath, executing the Dijkstra algorithm
	*	Param: Currently evaluated vertex, destination vertex and current minimum
	*	Return: Shortest path value
	*/
	public static int dijkstraRecursive(Vertex<Integer> v1, Vertex<Integer> v2, int min) {
		
		// This is the vertex we're looking at during the run, start by putting it in the cloud
		// along with its associated minimum value.
		D.cloud.put(v1, min);

		// Iterate along v1's outgoing edges only as we're finding a path from it
		for(Edge<Integer> e : metro.outgoingEdges(v1)) {
			Vertex<Integer> v = metro.endVertices(e)[1];
			// Grab the magnitude of this edge
			int p = e.getElement();
			// If it's a walk edge give it the appropriate value
			if (p == -1) {
				p = WALK_DISTANCE;
			}
			// Add it to the shortest distance to our vertx in question
			p = p + min;

			// First check if the vertex is not already in the cloud, then add it to discovered
			// if it's our first time seeing it, or if the path is smaller than the previous shortest path
			if(D.cloud.get(v) == null && (D.disc.get(v) == null || D.disc.get(v) > p)) {
				// we insert it into discovered with the new shortest distance
				D.disc.put(v, p);
				// and add the associated edge to the priority queue
				D.pq.insert(p,e);
			}
		}

		// To determine the next vertex to visit we grab the smallest known minimum in our
		// priority queue.
		Entry<Integer, Edge<Integer>> e = D.pq.removeMin();
		// push it on our stack to help us find the path later
		D.edges.push(e.getValue());
		// grab the associated vertex
		v1 = metro.endVertices(e.getValue())[1];
		// set min to the key, which is the minimum value associated with the edge and
		// the destination vertex
		min = e.getKey();

		// if we just found our shortest path to v2, then we return
		if (v1 == v2) {
			return min;
		}
		// if not we keep going, v1 is now the next vertex to use
		return dijkstraRecursive(v1, v2, min);
	}	
}