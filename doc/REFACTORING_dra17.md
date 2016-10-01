## Refactoring ##
----------
Ryan Anders
dra17

Made changes mostly in the PredPrey class--previously the colors of the cells and various other properties were hard-coded into the algorithms. I altered this so that we are now using the information that is read in and stored from the xml documents. To do this, I added a new constructor in the GenState class.

Blake and I also refactored a few methods, adding things like methods for reading in neighbors/updating cells into our simulation superclass.