
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