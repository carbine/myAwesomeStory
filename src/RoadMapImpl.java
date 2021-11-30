/*
COMP9417 Machine Learning
Major Project - Traffic Lights Reinforcement Learning
Beth Crane
Gill Morris
Nathan Wilson
 */

import interfaces.Car;
import interfaces.RoadMap;
import interfaces.Road;
import interfaces.Intersect;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import utils.Coords;

public class RoadMapImpl implements RoadMap {
    private int nRoads;
    private int nIntersect;
    private Road[] roads;
    private List<Intersect> intersections;
    private List<Car>[] carsOn;
    private LinkedList<Coords> intersectCoords = new LinkedList<Coords>();
    //private HashMap<Road, Coords[]> roadLocations = new HashMap<Road, Coords[]>();
    private Coords [][]roadCoords = {
        {new Coords(0, 20), new Coords(19, 20)}, 
        {new Coords(19, 22), new Coords(0, 22)}, 
        {new Coords (20, 19), new Coords(20, 0)},
        {new Coords(22, 0), new Coords(22, 19)}, 
    	{new Coords(23, 20), new Coords(42, 20)}, 
        {new Coords(42, 22), new Coords(23, 22)}, 
        {new Coords(43, 19), new Coords(43, 0)}, 
        {new Coords(45, 0), new Coords(45, 19)},
        {new Coords(46, 20), new Coords(65, 20)}, 
        {new Coords(65, 22), new Coords(46, 22)}, 
        {new Coords(45, 23), new Coords(45, 42)},
        {new Coords(43, 42), new Coords(43, 23)},
        {new Coords(22, 23), new Coords(22, 42)}, 
        {new Coords(20, 42), new Coords(20, 23)}, 
        {new Coords(0, 43), new Coords(19, 43)}, 
        {new Coords(19, 45), new Coords(0, 45)},
        {new Coords(20, 65), new Coords(20, 46)}, 
        {new Coords(22, 46), new Coords(22, 65)}, 
        {new Coords(23, 43), new Coords(42, 43)}, 
        {new Coords(42, 45), new Coords(24, 45)},
        {new Coords(43, 65), new Coords(43, 46)}, 
        {new Coords(45, 46), new Coords(45, 65)}, 
        {new Coords(45, 43), new Coords(65, 43)}, 
        {new Coords(45, 45), new Coords(64, 45)}
    };
    
    public RoadMapImpl() {
        nIntersect = 4;
    	nRoads = 24;
        roads = new Road[nRoads];
    	intersectCoords.add(new Coords(20,20));
    	intersectCoords.add(new Coords(20,40));
    	intersectCoords.add(new Coords(40,40));
    	intersectCoords.add(new Coords(40,20));
    	for (int i = 0; 