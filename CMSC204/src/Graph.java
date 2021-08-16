/**
 * @author Kenneth Shelton
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Graph implements GraphInterface<Town, Road> {

	private HashMap<Town,Set<Road>> graph;
	
	public Graph()
	{
		graph = new HashMap<Town,Set<Road>>();
	}
	
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		//can't make a road without start and end points
		if(sourceVertex == null || destinationVertex == null)
			return null;
		Set<Road> edges = edgeSet();
		//used an iterator here, could have used for:each loop 
		//either way works
		Iterator<Road> i = edges.iterator();
		while(i.hasNext())
		{
			Road r = i.next();
			if(r.getSource().equals(sourceVertex) && r.getDestination().equals(destinationVertex))
			{
				return r;
			}
		}
		return null;
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road r = new Road(sourceVertex,destinationVertex,weight,description);
		
		//can't make a road without start and end points
		if(!containsVertex(sourceVertex) || !containsVertex(destinationVertex))
			throw new IllegalArgumentException();
		
		graph.get(sourceVertex).add(r);
		return r;
	}

	@Override
	public boolean addVertex(Town v) {
		//check to see if town already exists
		if(graph.containsKey(v))
			return false;
		else
		{
			graph.put(v,new HashSet<Road>());
			return true;
		}
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		Collection<Set<Road>> vs = graph.values();
		//again, for:each would have been more elegant here
		Iterator<Set<Road>> i = vs.iterator();
		while(i.hasNext())
		{
			Iterator<Road> i2 = i.next().iterator();
			while(i2.hasNext())
			{
				Road r = i2.next();
				//check only source and destination for road
				//as that is all we are given here
				if(r.getSource().equals(sourceVertex) && r.getDestination().equals(destinationVertex))
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsVertex(Town v) {
		Set<Town> ks = graph.keySet();
		Iterator<Town> i = ks.iterator();
		while(i.hasNext())
		{
			//only compare name of town
			if(i.next().getName().equals(v.getName()))
				return true;
		}
		return false;
	}
	
	public Town getVertex(String v)
	{
		Set<Town> ks = graph.keySet();
		Iterator<Town> i = ks.iterator();
		while(i.hasNext())
		{
			Town t = i.next();
			//same as contains
			//just return town object instead of boolean
			if(t.getName().equals(v))
				return t;
		}
		return null;
	}

	@Override
	public Set<Road> edgeSet() {
		Set<Road> edges = new HashSet<Road>();
		Iterator<Set<Road>> i = graph.values().iterator();
		while(i.hasNext())
		{
			//go through each Town's Road Sets
			Iterator<Road> i2 = i.next().iterator();
			while(i2.hasNext())
			{	
				//go through each Road in the Road Set
				edges.add((Road) i2.next());
			}
		}
		return edges;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		//as I am using an adjacency matrix, this is straightforward by design
		return graph.get(vertex);
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if(weight <= -1 || description.equals(null) || !containsVertex(sourceVertex) || !containsVertex(destinationVertex))
			return null;
		Set<Road> edges = edgeSet();
		Iterator<Road> i = edges.iterator();
		while(i.hasNext())
		{
			//same process as containsEdge
			Road r = i.next();
			if(r.getSource().equals(sourceVertex) && r.getDestination().equals(destinationVertex) && r.getWeight() == weight && r.getName().equals(description))
			{
				//...but remove instead of check to see if it is there
				graph.get(r.getSource()).remove(r);
				return r;
			}
		}
		return null;
	}
	//overloaded this because TownGraphManager calls this method without a weight
	//I overloaded instead of ignoring weight in the other method because the assignment sheet specifically wants to compare weight > -1
	public Road removeEdge(Town sourceVertex, Town destinationVertex, String description) {
		if(description.equals(null) || !containsVertex(sourceVertex) || !containsVertex(destinationVertex))
			return null;
		Set<Road> edges = edgeSet();
		Iterator<Road> i = edges.iterator();
		while(i.hasNext())
		{
			Road r = i.next();
			if(r.getSource().equals(sourceVertex) && r.getDestination().equals(destinationVertex) && r.getName().equals(description))
			{
				graph.get(r.getSource()).remove(r);
				return r;
			}
		}
		return null;
	}

	@Override
	public boolean removeVertex(Town v) {
		//one-liner with a HashMap
		return graph.remove(v) != null;
	}

	@Override
	public Set<Town> vertexSet() {
		Set<Town> vertices = new HashSet<Town>();
		Iterator<Town> i = graph.keySet().iterator();
		while(i.hasNext())
		{
			//I tried using graph.keySet() as the return value, but it caused mismatch errors
			vertices.add(i.next());
		}
		return vertices;
	}

	
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		//path consisting of just Towns, will work backward afterwards
        List<Town> path = new ArrayList<Town>();
        //description for path as a return value
        ArrayList<String> pathDesc = new ArrayList<String>();
        //iterate backwards through final point to initial
        for(Town town = destinationVertex; town != null; town = town.previous)
        {
        	path.add(town);
        }
        for(Town town : path)
        {
        	if(town.previous==null) continue;
        	Road road = getEdge(town.previous,town);
        	//a kind of toString
        	pathDesc.add(town.previous+" via "+road.getName()+" to "+town+" "+road.getWeight()+" mi");
        }
        //..then reverse to get a straightforward path
        Collections.reverse(pathDesc);
        //System.out.println(pathDesc);
		return pathDesc;
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		//set all Town minDistances to the highest possible int value
		for(Town t : graph.keySet())
			t.minDistance = Integer.MAX_VALUE;
		//..all except for the start point
		sourceVertex.minDistance = 0;
		//PriorityQueue is very useful
		PriorityQueue<Town> vertexQueue = new PriorityQueue<Town>();
        vertexQueue.add(sourceVertex);
        //while loop for "unsettled" vertices
        while(!vertexQueue.isEmpty())
        {
        	//dequeue the queue
        	Town current = vertexQueue.poll();
        	//get all edges adjacent to current town
        	for(Road r : graph.get(current))
        	{
        		//each neighbor
        		Town target = r.getDestination();
        		//calculate distance to that neighbor
        		int distanceThroughCur = current.minDistance + r.getWeight();
        		//if it is the best so far..
        		if(distanceThroughCur<target.minDistance)
        		{
        			//"settle" the town
        			vertexQueue.remove(target);
        			//replace minDistance with distance through current path
        			target.minDistance = distanceThroughCur;
        			//add to path
                    target.previous = current;
                    //enqueue for next iteration
                    vertexQueue.add(target);
        		}
        	}
        }
	}

}
