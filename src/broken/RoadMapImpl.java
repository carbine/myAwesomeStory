
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
            }
        }

        //print new grid to screen
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(newGrid[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }*/

    // Hash = 3 digit number (longer if more roads are added)
    /*
    1st - Num cars with velocity 0 on road 1
    2nd - Num cars with velocity 0 on road 2
    3rd - light setting (ie 0-green, 1 red for one of the roads)
    //we don't want to include amber as a light setting because 
    //we do not learn when the light is amber
     */    
    public int stateCode(TrafficLight t) {
        int hash = 0;

        int lightSetting;
        if (t.horizontalGreen()) {
            lightSetting = 1;
        } else {
        	lightSetting = 0;
        }
        
        hash += lightSetting;
        
        // For each road off the traffic lights
        // Follow it back until we hit either 9 or a car
        // Mark that place         
        
        Coords c = new Coords(t.getCoords()).left().up();
        int i;
        // Road one we'll go vertically up
        for (i = 0; i < 9; i++) {
            c.setY(c.getY()-1);
            if (carAt(c)) {
                break;
            }
        }
        int v1 = i;
        c = new Coords(t.getCoords()).right().down();
        // Road one we'll go vertically down
        
        for (i = 0; i < 9; i++) {
        	c.setY(c.getY()+1);    
            if (carAt(c)) {
                break;
            }
        }
        int v2 = i;
        hash += 10*(Math.min(v1, v2));

        c = new Coords(t.getCoords()).left().down();
        // Road two we'll go horizontally left
        for (i = 0; i < 9; i++) {
            c.setX(c.getX()-1);
            if (carAt(c)) {
                break;
            }
        }
        int h1 = i;
        c = new Coords(t.getCoords()).right().up();
        // Road two we'll go horizontally right
        for (i = 0; i < 9; i++) {
            c.setX(c.getX()+1);
            if (carAt(c)) {
                break;
            }
        }
        int h2 = i;
        hash += 100*(Math.min(h1, h2));
    
        return hash;
    }
    
    
    @Override
    // Hash = 6 digit number (longer if more roads are added)
    //
    //  1st - boolean of roomToCrossIntersection for road 1
    //  2nd - boolean of roomToCrossIntersection for road 2
    //  3rd - closest car position from intersection for road 1 
    //        (0-8, 9 if no cars) X
    //  4th - closest car position from intersection for road 2 
    //        (0-8, 9 if no cars X
    //  5th - light setting (ie 0-green, 
    //        1 red for one of the roads, 2 for amber)
    // Needs to take in traffic light so it can tell which one to work
    // the things out for
    public int stateCode2(TrafficLight t) {
        int hash = 0;

        int lightSetting;
        if (t.horizontalGreen()) {
            lightSetting = 1;
        } else {
        	lightSetting = 0;
        }

        hash += lightSetting;
        
        // For each road off the traffic lights
        // Follow it back until we hit either 9 or a car
        // Mark that place         
        
        // Road one we'll go vertically up
        int i = 0;
        Coords c = new Coords(t.getCoords()).left().up();
        c.setY(c.getY()-1);  
        while (carAt(c)) {
        	i++; 
            c.setY(c.getY()-1); 
        }
        int v1 = i;
        
        // Road one we'll go vertically down
        i = 0;
        c = new Coords(t.getCoords()).right().down();
        c.setY(c.getY()+1);
        while (carAt(c)) {
            i++;
        	c.setY(c.getY()+1);
        }
        int v2 = i;
        
        hash += 10*(Math.min(v1+v2, 9));

        // Road two we'll go horizontally left
        i = 0;
        c = new Coords(t.getCoords()).left().down();
        c.setX(c.getX()-1);
        while(carAt(c)) {
        	i++;
            c.setX(c.getX()-1);
        }
        int h1 = i;
        
        // Road two we'll go horizontally right
        c = new Coords(t.getCoords()).right().up();
        c.setX(c.getX()+1);
        while(carAt(c)) {
            i++;
            c.setX(c.getX()+1);
        }
        int h2 = i;
        
        hash += 100*(Math.min(h1+h2,9));
    
        return hash;
    }

    //@Override
    // Hash = 5 digit number (longer if more roads are added)
    //
    //  1st - boolean of roomToCrossIntersection for road 1
    //  2nd - boolean of roomToCrossIntersection for road 2
    //  3rd - closest car position from intersection for road 1 
    //        (0-8, 9 if no cars) X
    //  4th - closest car position from intersection for road 2 
    //        (0-8, 9 if no cars X
    //  5th - light setting (ie 0-green, 1 red for one of the roads)
    // Needs to take in traffic light so it can tell which one to work
    // the things out for
   /* public int stateCode3(TrafficLight t, List<Car> cars) {
        int hash = 0;
        boolean room = true;
        
        int lightSetting = 0;
        if (t.horizontalGreen()) {
            lightSetting = 1;
        }

        hash += lightSetting;
        
        // For each road off the traffic lights
        // Follow it back until we hit either 9 or a car
        // Mark that place         
        
        // Road one we'll go vertically up
        int i = 0;
        Coords c = new Coords(t.getCoords()).left().up();  
        c.setY(c.getY()-1);  
        if (carAt(c)) {
        	for (Car car: cars) {
        		if (car.getCoords() == c) {
        			if (!roomToCrossIntersection(
                            c, car.getDirection(), t)) {
                    	room = false;
                    }
        			break;
        		}
        	}
        }
        while (carAt(c)) {
        	i++;
            c.setY(c.getY()-1); 
        }
        int v1 = i;
        
        // Road one we'll go vertically down
        i = 0;
        c = new Coords(t.getCoords()).right().down();
        c.setY(c.getY()+1);
        if (carAt(c)) {
        	for (Car car: cars) {
        		if (car.getCoords() == c) {
        			if (!roomToCrossIntersection(
                            c, car.getDirection(), t)) {
                    	room = false;
                    }
        			break;
        		}
        	}
        }
        
        while (carAt(c)) {
            i++;
        	c.setY(c.getY()+1);
        }
        int v2 = i;
        
        hash += 10*(Math.min(v1+v2,9));
        
        if (!!room) {
        	hash += 1000;
        } else {
        	room = true;
        }
        	

        // Road two we'll go horizontally left
        i = 0;
        c = new Coords(t.getCoords()).left().down();
        c.setX(c.getX()-1);
        if (carAt(c)) {
        	for (Car car: cars) {
        		if (car.getCoords() == c) {
        			if (!roomToCrossIntersection(
                            c, car.getDirection(), t)) {
                    	room = false;
                    }
        			break;
        		}
        	}
        }
        
        while(carAt(c)) {
        	i++;
            c.setX(c.getX()-1);
        }
        int h1 = i;
        
        // Road two we'll go horizontally right
        i = 0;
        c = new Coords(t.getCoords()).right().up();
        c.setX(c.getX()+1);
        if (carAt(c)) {
        	for (Car car: cars) {
        		if (car.getCoords() == c) {
        			if (!roomToCrossIntersection(
                            c, car.getDirection(), t)) {
                    	room = false;
                    }
        			break;
        		}
        	}
        }
        while(carAt(c)) {
            i++;
            c.setX(c.getX()+1);
        }
        int h2 = i;
        
        hash += 100*(Math.min(h1+h2,9));
        if (!!room) {
        	hash += 10000;
        } else {
        	room = true;
        }    
        return hash;
    }*/
    
    