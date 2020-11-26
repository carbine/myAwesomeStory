
/*
COMP9417 Machine Learning
Major Project - Traffic Lights Reinforcement Learning
Beth Crane
Gill Morris
Nathan Wilson
 */

import interfaces.Car;
import interfaces.LearningModule;
import interfaces.RoadMap;
import interfaces.Road;
import interfaces.Intersect;
import utils.Coords;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main (String[] args) {
        //Graphics and runtime parameters
        int runTime = 50200;
        int quietTime = 50000;
        boolean graphicalOutput = true;
        boolean consoleOutput = false;
        boolean output = graphicalOutput || consoleOutput;
        double trafficIntensity = 0.2;

        RoadMap map = new RoadMapImpl();
        LearningModule learner = new LearningModuleImpl();