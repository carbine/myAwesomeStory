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
    //priva