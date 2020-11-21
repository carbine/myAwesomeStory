
/*
 * COMP9417 Machine Learning
 * Major Project - Traffic Lights Reinforcement Learning
 * Beth Crane
 * Gill Morris
 * Nathan Wilson
*/

import interfaces.*;
import java.util.*;
import utils.*;

public class LearningModuleImpl implements LearningModule {
    private static final int nStates = 1000, nActions = 2;
    private double alpha = 0.7, gamma = 0.9, epsilon = 0.1;
    private double[] q;
    private int[] lastAction;

    LearningModuleImpl() {
        lastAction = new int[nStates];
        q = new double[nStates];
        for (int i = 0; i < nStates; ++i) {
            q[i] = 0;
        }
    }

    public void setRLParam(double alpha, double gamma, double epsilon) {
        this.alpha = alpha;
        this.gamma = gamma;
        this.epsilon = epsilon;
    }

    private boolean stoppedCar(Car c) {
        return c.velocity() == 0;
    }

    public int decide(int s) {
        int bestAction = 0;
        double bestQ = -100;
        for (int i = 0; i < nActions; ++i) {
            if (q[10*s + i] > bestQ) {
                bestQ = q[10*s + i];
                bestAction = i;
            }
        }
        lastAction[s] = bestAction;
        return bestAction;
    }

    public void learn(int a, int s, int sp) {
        double newQ = (1-alpha) * q[s*nActions + a] +
            alpha * (reward(sp) + gamma*maxQ(sp));
        q[s*nActions + a] = newQ;
    }
    
    public int stateCode(Intersect l, RoadMap m) {
        int ret = l.lightSetting();
    	for (int i = 0; i < l.in().size(); ++i) {
    		Road r = l.in().get(i);
    		int closestCar = 9;
    		for (Car c : m.carsOn(i)) {