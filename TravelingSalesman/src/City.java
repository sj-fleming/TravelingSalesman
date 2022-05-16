/*
 * Sarah Fleming
 * Dual Enrollment Final Project - the traveling salesman
 * City class: represents the nodes of the graph/map
 */
public class City {

	private int index;
	private String name;
	
	//Constructors
	
	//default constructor
	City() {
		index = 0;
		name = "DC";
	}
	
	City(int i, String s) {
		index = i;
		name = s;
	}
	
	public int getIndex() {
		return index;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
	
}
