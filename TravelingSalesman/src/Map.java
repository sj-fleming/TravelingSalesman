import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/*
 * Sarah Fleming
 * Dual Enrollment Final Project - the traveling salesman
 * Map class: represents the graph that holds the different cities and the "distances" between them
 * A map is a weighted undirected graph represented by an adjacency list
 */
public class Map {
	
	int numCities;
	//adjacency list - array of linked lists?
	City[] cities;
	/*
	 * 2D array of integers that holds the distances between cities
	 */
	int[][] map = new int[numCities][numCities];
	public static String citiesFile = "cities.txt";
	
	public void createMap(int[] distances, String[] cityNames) {
		//read in list of cities and add them to the array
//		for (int i = 0; i < numCities; i++) { //yassslay (- Natalie)
//			cities[i] = new City(i, cityNames[i]);
//		}
		
		cities = new City[numCities];
		File file = new File(citiesFile);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException ex) {
			System.out.println("*** Cannot open map input***"); // if input is invalid
			System.exit(0);
		}
		
		while (scanner.hasNextLine()) {
			numCities = Integer.parseInt(scanner.nextLine());
			//each line holds city, distance, and next city?
		}
	}

}
