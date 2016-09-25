# Cell Society Design
## Introduction


The problem our team is trying to solve is to simulate a 2-D array of cells based on the rules and the state of its neighbouring cells. The design is flexible in extending it to other simulations, because we plan on implementing inheritance principles. Therefore new “rules” can be a new subclass and implementation can be added to the superclass. The 2-D array created will be open to the GUI class managers for access, but closed for modifications. The Cells information will be closed to the other classes and must be accessed through getters and setters. All information about rather the program should step or run all the way through will be open to the superclass. The major design goals of this project is to make everything modular, so that the program is extendable. 

## Overview
![enter image description here](https://lh3.googleusercontent.com/-V5AeZrmxYiM/V94C6QXKcJI/AAAAAAAACLY/O_RFMtN8uYYLU6jCCkDLNGEGy4J2mcrSACLcB/s0/designpic.jpg "designpic.jpg")

### Default package - Main.java
The Main class will start the program
### GUI package - GUI class
The GUI class will be responsible for everything visual, as well as capturing user inputs.
### Simulation package
#### Simulation Superclass
The simulation class will read the XML, set up an initial grid of Cells, and decide which simulation subclass to use.
It will also call a method that sets all Cells’ current states to their nextStates.
#### Specific Simulation Subclasses
The Simulation subclasses will read the cells’ current states and calculate their next states.
#### Cell
The Cell class will know its state and have its nextState be set by a simulation subclass.

## User Interface

The user will first be prompted to input an XML file for the desired simulation. A grid is created based on the initial conditions in the XML file. There will be five buttons--stop, start, step, reset, and new simulation--and a slow-fast slider for the user to interact with, similar to the picture below:

![enter image description here](https://lh3.googleusercontent.com/-X2yO2L2AvdY/V94DLgodsVI/AAAAAAAACLg/7asv_Oe8BqUWmPK5gDqjXL2lwhNsXveBwCLcB/s0/GUIscreenshot.PNG "GUIscreenshot.PNG")

The stop and start buttons will stop the simulation or start it, with the speed indicated by the slider. The step button will take a single step forward in the simulation, and the reset button will revert the grid to the initial state described in the XML file. The new simulation button will restart the program, asking the user again to input an XML file.
Messages provided to the user will include “simulation complete” upon the simulation reaching completion, as well as a “unreadable or empty XML file” when an XML file is uploaded that cannot be read by the program.
## Design Details 
### Class Responsibilities:
#### Main (default):
Starts the Program, inherits the Application class
Has Start method that will set up the stage and the scene


#### GUI Package
Manages all the GUI aspects
Uploading a file
Displaying simulation of the cells
Control Buttons

#### Simulation package
##### Simulation Superclass
Reads in the XML file, 
passes in the initial state of cells and the updated state of the cells

##### Specific Simulation Subclasses
For each simulation, a class will be created that will extend Simulation super class. Each simulation will have the  updateState() , rule specific calculation methods. 
##### Cell 
Contains currentState and nextState of the cell
Contains getters and setters for the states

### Use Cases
#### Apply the rules to a middle cell: 
Specific simulation subclass calls read8Cells() method, performs calculation on the resulting neighbors. Sets Cell’s nextState to “dead.” After all cells have been calculated, simulation superclass runs update(), all Cells’ states are set equal to nextState and nextStates are set to null, 0, etc. 

#### Apply the rules to an edge cell:

Same as above^ read8Cells() and read4Cells() will account for missing neighbors

#### Move to the next generation: 

Simulation superclass calls update(), all Cells’ states are set equal to nextState and nextStates are set to null, 0, etc. This updated 2D Array of Cells is sent to GUI class, where it is interpreted and displayed.

#### Set a simulation parameter:
Upon upload of XML file, simulation superclass parses through XML file and sees which simulation to use, as well as what parameters should be set to.

#### Switch simulations: 

User presses “stop” (or lets simulation end), then presses “new simulation,” at which point the program restarts and asks for a new XML file. The user uploads an XML file containing the desired simulation and its parameters/initial conditions.

## Design Considerations 

### Questions we still have:
What are the contents of the XML
How will the XML be delivered/accessed?
#### Assumptions:
Assumption that all input will be good and correspond to the different rules and regulations laid out in the assignment page.
The GUI will assume that it gets a valid array from the simulator to display.
#### Dependencies:
All subclasses will naturally be dependent on the superclass and the GUI will be dependent upon the Simulator class. 
#### Things to consider:
Does the cell class need to be in the simulation package?
Does it really need access to everything in the package?
Would it be better to place it elsewhere so that GUI doesn’t have to import the package containing it?
Would it be better for GUI to receive simply an array of of color constants, that way it doesn’t need to know about cells at all?

## Team Responsibilities

Pratiksha sharma: Primary Front End (GUI)
Ryan Anders: Back end
Blake Becerra: Back end
We will split up the different cases between the people working on the back end. 
We will both work on the simulation class to ensure it can work with all of our cases. 



