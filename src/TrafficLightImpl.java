
/*
COMP9417 Machine Learning
Major Project - Traffic Lights Reinforcement Learning
Beth Crane
Gill Morris
Nathan Wilson
 */

import interfaces.TrafficLight;
import utils.Coords;

import java.util.ArrayList;
import java.util.List;

//Contains implementation of methods in interfaces.TrafficLight
public class TrafficLightImpl implements TrafficLight {
    private final int maxDelay = 3;
    private Coords coords;
    private boolean horizontalGreen;
    private int lightSetting;
    private int delay;

    public TrafficLightImpl(Coords coords, boolean horizontalGreen) {
        this.coords = coords;
        this.horizontalGreen = horizontalGreen;
        this.lightSetting = 0; //change this
    }

    @Override
    public void switchLight(boolean newAction) {
    	if (newAction) {
    	if (getDelay() == 0) {
    		delay = maxDelay;
    	}
    	}
    }

    public Coords getCoords() {
        return coords;