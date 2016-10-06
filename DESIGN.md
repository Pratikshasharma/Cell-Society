###Provide the high-level design goals of your project


<<<<<<< HEAD
The problem our team is trying to solve is to simulate a 2-D array of cells based on the rules and the state of its neighboring cells. The design is flexible in extending it to other simulations, because we plan on implementing inheritance principles. Therefore new “rules” can be a new subclass and implementation can be added to the superclass. The 2-D array created will be open to the GUI class managers for access, but closed for modifications. The Cells information will be closed to the other classes and must be accessed through getters and setters. All information about rather the program should step or run all the way through will be open to the superclass. The major design goal of this project is to make everything modular, so that the program is extendible. 
=======
####Front End
There is a Controller.java and MainGUI.java that are running the front end and simulation displays. Controller.java is called by Main.java and displays the first screen along with its associated buttons. As buttons are clicked and the simulation starts to run Controller.java will call MainGUI.java to associate the buttons with their respective functions in the GUI. MainGUI.java will call all the other classes within the guipackage to initialize the grid, graph, and set up the sliders and various buttons associtated with the display once an XML file is selected. Once the simulation is displayed and the buttons are displayed and associated with their respective functions. Controller.java will communicate with the Back End, more specifically SimilationManager.java, to controll and run the simulation logic. 
>>>>>>> e07e1ed314d507e26c45e2e154dfa6220d77f308

####Back End
Once the file is selected in GUI, the Controller sends it to MainXML for reading. The Controller calls in SimulationManager to get the right type of Simulation. A 2D array of Cells, myGrid, is initialized and an instance of the specific simulation is created. A copy of the color of each Cell object in myGrid[][] is then passed to the front end in the form of a 2 D array of Paint[][]. When Step/Start is pressed, Controller calls in SimulationManager to call update the Grid in the back end. The 2 D array representing updated array is then passed onto the front.

###Explains, in detail, how to add new features to your project

#####Taking configuration Parameters in from the User :
It is fairly tough for now to take in parameters from the user and use it in the simulations. It could be done by creating a flash screen in the GUI that takes in these parameters, create a object with the parameters and pass it into the back end. However, since the back end and front end are fairly separated for now, it will be pretty challenging. 

####Adding new shapes to the simulation:
If the shapes have 8 neighbours, 3 on top and bottom, one on each sides then the Simulation logic we have for the neighbours should work. However, in the front end, visualization might be a problem since polygons need x and y indices in the Scene to be created. For rectangle and square, it is pretty easy since each Cell object can just contain a Shape attribute that has a defined width and height but does not necessarily need coordinates in the Scene. 

####However, for other shapes, the design we have for now is not extensible. The simulation logic changes.


####Adding a new simulation:
A [NewSimulation] class will have to be created, containing all the logic for the new simulation. Then, an XML document will need to be created according to the specifications in data/README_XML.md. Next, a [newSim]Model class (subclass of SimModel) should be created that can hold all the necessary parameters and states for the simulation. Then a [newSim]XMLFactorySim class (subclass of SimXMLFactory) should be created that extracts parameters and states from the XML document using the getSimGenParams and getSimGenStates commands and returns a [newSim]Model object containing the extracted parameters and states. An else-if statement will have to be added in MainXML that makes the [newSim]XMLFactory object when the new xml is chosen. One will also have to be added to two statements within SimulationManager -- the first will return an instance of the [NewSimulation] logic class, and the second will initialize a grid with the states and percentages contained in the [newSim]Model. The GUI should automatically handle the new simulation once these steps are complete.

###Justify major design choices, including trade-offs (i.e., pros and cons), made in your project

We have an abstract superclass for all the simulation logic subclasses that contains methods that are used in the individual simulations.

We also pass multiple unused variables in SimModel.java where certain variables are used for certain simulations. It was something we had to do so that because we don’t know necessarily which model will be chosen and it must contain enough information for all models. 

###State any assumptions or decisions made to simplify or resolve ambiguities in your the project's functionality

One relatively major decision we made to simplify ambiguities was to hard-code in the possible simulation types. This allows us to perform a simple check of the xml document and launch the appropriate XMLfactory/initialization/simulation logic. We realize that it is bad practice to do so, but we failed to find a suitable alternative with which we could determine the simulation type from xml data.
We should have used a resources file as we do in Button.properties. 

MainGUI for now does not just work as a template for creating nodes to be put in the Scene but also does functions such as updating color of certain Shape node in the Grid,  updating the Population Graph. 
The major purpose of Main GUI was just to instantiate all these different nodes in the scene, however,  my Controller had  to somehow get access to methods in PopulationGraph and Grid that updated the color and graph. Instead of creating another instance of Grid and PopulationGraph in the Controller, I decided to just access the instances created in MainGUI. I could have passed Grid and PopulationGraph objects from MainGUI to Controller, but that would be a bad idea, because the instances of those Objects are now exposed. This would violate encapsulation. 



