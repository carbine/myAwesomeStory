package interfaces;

/*
COMP9417 Machine Learning
Major Project - Traffic Lights Reinforcement Learning
Beth Crane
Gill Morris
Nathan Wilson
*/

import utils.Coords;

//interface for trafficLight object class - 
//represents a traffic light, its position and current state
public interface TrafficLight {
    //Changes direction of traffic light if getDelay() == 0
    void switchLight(boolean newAction);

    //Get coordinates of traffic light
    Coords getCoords();

    //Time until the currently amber light goes red
    int getD