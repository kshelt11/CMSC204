/**
 * @author Kenneth Shelton
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class TownGraphManager implements TownGraphManagerInterface {

	Graph graph;
	
	public TownGraphManager()
	{
		graph = new Graph();
	}
	
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		//conversion from String town to Town objects
		Town source = graph.getVertex(town1);
		Town destination = graph.getVertex(town2);
		if(!graph.containsVertex(source) || !graph.containsVertex(destination))
			throw new IllegalArgumentException();
		return graph.addEdge(source, destination, weight, roadName) != null;
	}

	@Override
	public String getRoad(String town1, String town2) {
		//conversion from String town to Town objects
		Town source = graph.getVertex(town1);
		Town destination = graph.getVertex(town2);
		if(!graph.containsVertex(source) || !graph.containsVertex(destination))
			throw new IllegalArgumentException();
		for(Road r : graph.edgesOf(source))
		{
			if(r.getDestination().equals(destination))
				return r.getName();
		}
		return null;
	}

	@Override
	public boolean addTown(String v) {
		//call Graph's method
		return graph.addVertex(new Town(v));
	}

	@Override
	public boolean containsTown(String v) {
		//call Graph's method
		return graph.containsVertex(new Town(v));
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		//call Graph's method
		return getRoad(town1,town2)!=null;
	}

	@Override
	public ArrayList<String> allRoads() {
		Iterator<Road> i = graph.edgeSet().iterator();
		ArrayList<String> roads = new ArrayList<String>();
		while(i.hasNext())
		{
			roads.add(i.next().getName());
		}
		return roads;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		//call Graph's method
		return graph.removeEdge(graph.getVertex(town1),graph.getVertex(town2),road) != null;
	}

	@Override
	public boolean deleteTown(String v) {
		//call Graph's method
		return graph.removeVertex(graph.getVertex(v));
	}

	@Override
	public ArrayList<String> allTowns() {
		Iterator<Town> i = graph.vertexSet().iterator();
		ArrayList<String> towns = new ArrayList<String>();
		while(i.hasNext())
		{
			towns.add(i.next().getName());
		}
		return towns;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		Town source = graph.getVertex(town1);
		Town destination = graph.getVertex(town2);
		return graph.shortestPath(source,destination);
	}

	@Override
	public Town getTown(String name) {
		//call Graph's method
		return graph.getVertex(name);
	}

	public void addRoad(Object name, Object name2, int weight, String name3) {
		//call Graph's method
		//also convert Objects into Strings
		graph.addEdge(graph.getVertex((String) name), graph.getVertex((String) name2), weight, name3);
	}

	public ArrayList<String> getPath(Object name, Object name2) {
		//call Graph's method
		return getPath((String)name,(String)name2);
	}

	public void populateTownGraph(File selectedFile) throws FileNotFoundException {
		//not enough time to implement
	}

}
