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
//							System.out.println("city 1: " + cities[index].getName() + " city 2: " + cities[index2].getName() + " distance: " + map[index][index2]);
						}
					}
//				System.out.println(scanner.hasNextLine());
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
	
	//traverses the map from the specified index until it reaches the root
	public static City[] traverse(int index, City[] visited, Stack<City> stack, City start, City root) {
		if(index == visited.length)
			return visited;
		if (index != 0 && visited[index-1] != null && visited[index-1].equals(root)) //returns when it gets back to the starting city (root)
			return visited;
		if(index == 0) {
			visited[index] = start;
		}
		else if(stack.isEmpty())
			visited[index] = cities[index];
		else
			visited[index] = stack.pop();
		//push adjacent cities onto stack
		for(int i = 0; i < visited.length; i++) {
			if (map[visited[index].getIndex()][i] != 0 && !Arrays.asList(visited).contains(cities[i])) //if the city a and city b are adjacent to each other and city b has not already been visited
				stack.push(cities[i]);
		}
		traverse(index+1, visited, stack, start, root);
		return visited;
	}
	
	//visits all the cities
	public static City[] traverseAllCities(int index, City[] visited, Stack<City> stack, City root) {
		if (index == visited.length)
			return visited;
		if(index == 0) {
			visited[index] = root;
		}
		else
			visited[index] = stack.pop();
		//push adjacent cities onto stack
		for(int i = 0; i < numCities; i++) {
			if (map[visited[index].getIndex()][i] != 0 && !Arrays.asList(visited).contains(cities[i])) //if the city a and city b are adjacent to each other and city b has not already been visited
				stack.push(cities[i]);
		}
		System.out.println(Arrays.toString(visited));
		System.out.println(stack);
		traverseAllCities(index+1, visited, stack, root);
		return visited;
	}
	
	public static int totalDistance(City[] visited) {
		int distance = 0;
		for(int i = 0; i < visited.length - 1; i++)
			distance += map[visited[i].getIndex()][visited[i+1].getIndex()]; //check if null?
		if (visited[visited.length-1] != null)
			distance += map[0][visited[visited.length-1].getIndex()];
		return distance;
	}
	
	public static City[] findBestPath(int x, int y, City[] bestPath, City[] visited, Stack<City> stack) {
		if(x == visited.length)
			return bestPath;
		if(y == visited.length) {
			if(totalDistance(visited) < totalDistance(bestPath))
				bestPath = visited;
			return findBestPath(0, 0, bestPath, new City[numCities], new Stack<City>());
		}
		
		//idk how to do this:
			//set index 0 of visited, then use traverse method to find all possible permutations starting with index 0 (then increase index)
				//won't work - traverse could repeat (check before transferring to visited array?)
		
		
//		for(int i = y; i < visited.length; i++)
//			visited[x] = cities[y];
		
		
		
//		if(stack.isEmpty())
//			visited[x] = cities[0];
//		else
//			visited[x] = stack.pop();
//		//push adjacent cities onto stack
//		for(int i = 0; i < numCities; i++) {
//			if (map[visited[x].getIndex()][i] != 0 && !Arrays.asList(visited).contains(cities[i])) //if the city a and city b are adjacent to each other and city b has not already been visited
//				stack.push(cities[i]);
//		}
		
		return visited;

	}
	
	public static void main(String[] args) {
		createMap();
//		System.out.println("num cities: " + numCities);
		printMap();
		System.out.println(Arrays.toString(traverseAllCities(0, new City[numCities], new Stack<City>(), cities[0])));
//		System.out.println(Arrays.toString(traverse(0, new City[numCities], new Stack<City>(), cities[2], cities[0])));
	}

}
