import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/*
 * Sarah Fleming
 * Dual Enrollment Final Project - the traveling salesman
 * Map class: represents the graph that holds the different cities and the "distances" between them
 * A map is a weighted undirected graph represented by an adjacency list
 */
public class Map {
	
	static int numCities;
	//adjacency list - array of linked lists?
	static City[] cities;
	/*
	 * 2D array of integers that holds the distances between cities
	 */
	static int[][] map;
	public static String citiesFile = "cities.txt";
	
	public static void createMap() {
		//read in list of cities and add them to the array
//		for (int i = 0; i < numCities; i++) { //yassslay (- Natalie)
//			cities[i] = new City(i, cityNames[i]);
//		}
		
		
		File file = new File(citiesFile);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException ex) {
			System.out.println("*** Cannot open map input***"); // if input is invalid
			System.exit(0);
		}
		
		if (scanner.hasNextLine()) {
			numCities = Integer.parseInt(scanner.nextLine());
			cities = new City[numCities];
			map = new int[numCities][numCities];
//			System.out.println("test 1, num cities: " + numCities);
			
			int i = 0;
			while (scanner.hasNextLine()) {
				//each line holds city, distance, and next city, etc.
				cities[i] = new City(i, scanner.next()); //gets the first word on each line
//				System.out.println(cities[i].getName());
				scanner.nextLine();
				i++;
			}
		}

		//new scanner to read the file again but for adjacent cities and distances
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException ex) {
			System.out.println("*** Cannot open map input***"); // if input is invalid
			System.exit(0);
		}			
		
			while (scanner.hasNextLine()) {
				scanner.nextLine();
				//each line holds city, distance, and next city, etc.
				if(!scanner.hasNext())
					break;
				String cityName = scanner.next();
				int index = findIndex(cityName);
					while(scanner.hasNextInt()) {
						int distance = Integer.parseInt(scanner.next());
						if(scanner.hasNext()) { //is this necessary?
							int index2 = findIndex(scanner.next()); //cities with two words?
							map[index][index2] = distance;	
							System.out.println("city 1: " + cities[index].getName() + " city 2: " + cities[index2].getName() + " distance: " + map[index][index2]);
						}
					}
				System.out.println(scanner.hasNextLine());
			}
		}
//	}
	
	public static int findIndex(String s) {
		for(City c : cities)
			if (c.getName().equals(s))
				return c.getIndex();
		return -1;
	}
	
	public static void printMap() {
		System.out.printf("%15s", "");
		for(int i = 0; i < numCities; i++)
			System.out.printf("%15s", cities[i].getName()); //prints the top row of the map with the city names
		System.out.println();
		for (int i = 0; i < numCities; i++) {
			System.out.printf("%15s", cities[i].getName());
			for(int j = 0; j < numCities; j++) {
				System.out.printf("%15d", map[i][j]);
			}
			System.out.println();
		}
				
	}
	
	public static City[] traverse(int index, City[] visited, Stack<City> stack, City root) {
		if (index == visited.length)
			return visited;
		if(index == 0) {
			visited[index] = root;
			//push cities adjacent to root onto stack
		}
		else
			visited[index] = stack.pop();
		for(int i = 0; i < numCities; i++) {
			if (map[visited[index].getIndex()][i] != 0 && !Arrays.asList(visited).contains(cities[i])) //if the city a and city b are adjacent to each other and city b has not already been visited
				stack.push(cities[i]);
		}
		return visited;
	}
	
	public static void findBestPath(int minDistance, int currentDistance, City[] visited) {
		
	}
	
	public static void main(String[] args) {
		System.out.println("test");
		createMap();
		System.out.println("num cities: " + numCities);
		printMap();
	}

}
