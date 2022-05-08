
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
                new Coords(0, 21),
                new Coords(0, 41),
                new Coords(59, 19),
                new Coords(59, 39),
                new Coords(19, 0),
                new Coords(21, 59),
                new Coords(39, 0),
                new Coords(41, 59)
        };
    private final char carChar = 'C';
    private final int roadChar = ' ';
    private char[][] grid;
    private List<Coords> roadEntrances = new ArrayList<Coords>();

    RoadMapImpl() {
        Collections.addAll(roadEntrances, defaultEntrances);
        grid = new char[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = 'x';
                for (Coords k : roadEntrances) {
                    if ((0 < i && i < gridSize-1 && i == k.getY()) || 
                        (0 < j && j < gridSize-1 && j == k.getX()))
                    {
                        grid[i][j] = roadChar;
                    }
                }
            }
        }
    }

    public RoadMapImpl(char[][] newGrid) {
        grid = copyGrid(newGrid);
        Collections.addAll(roadEntrances, defaultEntrances);
    }

   /* @Override
    public void print(List<Car> cars, List<TrafficLight> trafficLights){
        //copy grid and place cars onto it
        char[][] newGrid = copyGrid(grid);
        for (Car car : cars) {
            int y = car.getCoords().getX(), x = car.getCoords().getY();
            int dx = car.getDirection().getXSpeed();
            int dy = car.getDirection().getYSpeed();
            newGrid[y][x] = 
                dx == 0 ? dy < 0 ? '^' : 'v' :
                dy == 0 ? dx < 0 ? '<' : '>' :
                    '6';
        }
        for(TrafficLight light : trafficLights) {
            int x=light.getCoords().getX(), y=light.getCoords().getY();
            if (light.getDelay() != 0) {
                newGrid[y][x] = 'o';
            } else {
                newGrid[y][x] = light.horizontalGreen() ? '>' : 'v';