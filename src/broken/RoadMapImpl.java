
/*
COMP9417 Machine Learning
Major Project - Traffic Lights Reinforcement Learning
Beth Crane
Gill Morris
Nathan Wilson
 */

import interfaces.Car;
import interfaces.RoadMap;
import interfaces.Action;
import interfaces.TrafficLight;
import utils.Coords;
import utils.Velocity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Roadmap Implementation class -
//implements methods from interfaces.RoadMap
public class RoadMapImpl implements RoadMap {
    public final int gridSize = 60;
    private final Coords[] defaultEntrances = {