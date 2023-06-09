
# Q-Learning-Traffic-Lights
Traffic light simulation for our Machine Learning project on reinforcement learning (Copy of bitbucket repo for easier sharing).

Authors: Nathan Wilson, Gill Morris, Beth Crane

This program is designed to simulate a number of road intersections and learn the optimal time to switch traffic lights to have as few cars stopped at any time as possible.

== Running the Program ==

Once all the files have been downloaded type `make` and then type `Java Main [rewardNum intensity]`

The two arguments are optional but both or neither are required. 
The first is rewardNum, which is 1-3, and allows you to trial the three reward functions we experimented with.
The second is intensity, which is a float in the range of 0.0 and 0.5 to adjust how many cars are spawned are onto the screen. 

Once the program has been started the machine will learn for a time, and then the graphical interface will start displaying the traffic lights and cars interacting. The run time is set to be billion timeSteps, and if the program reaches that end it outputs some information about its success in terms of cars stopped at any point.