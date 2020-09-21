/*
COMP9417 Machine Learning
Major Project - Traffic Lights Reinforcement Learning
Beth Crane
Gill Morris
Nathan Wilson
*/

import interfaces.Intersect;
import interfaces.Road;
import java.util.List;
import java.util.ArrayList;

public class IntersectImpl implements Intersect {
    private List<Road> in, out;
    private int lightSetting;
    private List<Integer> delay;
    private final int maxDelay = 3; 