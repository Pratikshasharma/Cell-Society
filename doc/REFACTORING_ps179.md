## Refactoring Excercise

### Design Issue
I chose to refactor a class that was named as "CreateGrid.java" but did much more than just creating a grid.
I refactored it such that in the end I had my MainGUI.java which did the setting of all the GUI components
and a separate class Grid.java to create a grid. I also created a Grid Interface for the Grid class.

I also chose to refactor my ButtonCreater Class into a hierarchial sturcture, where the buttons I create in my Scene each have their own class that extends from 
ButtonClass super class. 
Even though it did not change the process of how I could access the buttons, this made things neater, and the button layout settings and Labels
could all be set in their respective classes.


### Why is the new version better?
The new version is better because my classes follow the sinlge responsibility principle. I have a Grid class that all it does is to create a grid, instead
of having the creation of Grid as a method in my Main GUI class. (GUI set up class).

For the buttons, creating a hierarchial stucture makes sense because of their shared behaviour.


### Links
[Commit 1] (https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team12/commit/8e97c0663ac9e71e929887fc860ea2df6cc492c6)
[Commit 2] (https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team12/commit/d6a46900631b1e6e1b67b166f1a673a94d483c58)


