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
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof City))
			return false;
		if(((City)other).getName().equals(this.getName()) && ((City)other).getIndex() == this.getIndex())
			return true;
		return false;
	}
	
}
