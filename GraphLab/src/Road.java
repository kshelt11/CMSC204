/**
 * @author Kenneth Shelton
 */

public class Road implements Comparable<Road>{

	private Town source;
	private Town destination;
	private int degrees;
	private String name;
	
	public Road(Town source, Town destination, int degrees, String name) {
		this.source = source;
		this.destination = destination;
		this.degrees = degrees;
		this.name = name;
	}

	@Override
	public int compareTo(Road r) {
		return equals(r)?0:-1;
	}

	public String getName() {
		return name;
	}
	
	public Town getSource() {
		return source;
	}
	
	public Town getDestination() {
		return destination;
	}
	
	public boolean contains(Town town)
	{
		return source.compareTo(town)==1||destination.compareTo(town)==1;
	}
	
	public int getWeight()
	{
		return degrees;
	}
	
	public String toString()
	{
		return name+": "+source.toString() + " to " + destination.toString()+ ", "+degrees;
	}
	
	public boolean equals(Road r)
	{
		return r.getSource().equals(source) && r.getDestination().equals(destination) && r.getWeight()==degrees && r.getName().equals(name);
	}

}
