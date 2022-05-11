import java.util.LinkedList;

/*
 * Sarah Fleming
 * Dual Enrollment Final Project - the traveling salesman
 * Map class: represents the graph that holds the different cities and the "distances" between them
 * A map is a weighted undirected graph represented by an adjacency list
 */
public class Map {
	
	int numCities;
	//adjacency list - array of linked lists?
	LinkedList<String>[] cities = new LinkedList<String>[numCities];
	
	public String[] createMap() {
		for (int i = 0; i < numCities; i++) {
			cities[i] = new LinkedList();
		}
	}

}
