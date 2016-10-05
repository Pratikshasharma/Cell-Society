
# cellsociety_team12
###XML Documents - Written by Ryan Anders

Our program will read an xml document formatted in the following way:

(ALL CAPS indicates where the user should input their relevant data)

    <?xml version="1.0" encoding="UTF-8"?>
    <root SimType="SIMTYPE">
       <genparam>
          <simname>SIMNAME</simname>
          <simauthor>SIMAUTHOR</simauthor>
          <simwidth>WIDTH</simwidth>
          <simheight>HEIGHT</simheight>
       </genparam>
       <SIMULATIONPARAMETERS>VALUE</SIMULATIONPARAMETERS>
       <state id="ID">
          <statename>STATENAME</statename>
          <statecolor>STATECOLOR</statecolor>
          <percentage>PERCENTAGE</percentage>
       </state>
       <state id="ID">
          <statename>STATENAME</statename>
          <statecolor>STATECOLOR</statecolor>
          <percentage>PERCENTAGE</percentage>
       </state>
    </root>
          

Possible values:

 - SIMTYPE
	 - gameoflife
	 - fire
	 - predprey
	 - segregation
	 - slimemold
 - SIMNAME/SIMAUTHOR
	 - name and author of simulation (not vital)
 - WIDTH/HEIGHT
	- integer values of desired grid width/height
 - SIMULATIONPARAMETERS
(if none, leave this out. If multiple, list as siblings in any order)
	 - probcatch (Spreading of Fire)
		 - double between 0 and 1, percent chance of fire catching
	 - satisfaction (Segregation)
		 - double between 0 and 1, desired percentage of same-race neighbors
	 - fishturnstobreed (Predator-Prey)
		 - integer value, amount of turns before fish breed
	 - sharkturnstobreed (Predator-Prey)
		 - integer value, amount of turns before sharks breed
	 - sharkturnstostarve (Predator-Prey)
		 - integer value, amount of turns before sharks starve
 - ID
	 - integer, that state's ID
		 - no two states can have the same ID for one simulation
 - STATENAME
	 - name of the state
 - STATECOLOR
	 - desired color of cell in that state (all caps or all lowercase)
 - PERCENTAGE
	 - double between 0 and 1, desired percentage of cells to be in that state at initialization
		 - NOTE: total percentages of all states must add up to 1

An XMLFactoryException will be thrown if invalid inputs are detected, detailing the specific problem.