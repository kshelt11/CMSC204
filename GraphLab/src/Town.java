/**
 * @author Kenneth Shelton
 */

public class Town extends Object implements Comparable<Town>{

	private String name;
	public int minDistance;
	public Town previous;
	
	public Town(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Town o) {
		if(o.getName().equals(this.name))
			return 0;
		return -1;
	}
	
	@Override
	public boolean equals(Object o) {
		return o.toString().equals(toString());
	}

	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public String toString() {
		return name;
	}

}
