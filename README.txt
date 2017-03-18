Course: 	CS 4365
Program 2: 	CSP Solver
Description:	This program implements a CSP solver that takes in a constraint file and a variable file. It will pick the most constrained variable in the variable file, breaking ties by using the most constraining variable, (and then alphabetically if ties remain). It will then pick a value for the variable by using a least constraining value hueristic and then breaking ties by using the smaller value. After the variable and value are chosen, it will then check the constraints to make sure none are violated, and then pick another variable and value until it finds a valid assignment for all variables or 30 violations occur. The program will also take in a flag that determines if a forward checking procedure will be applied to check constraint consistency.

Group Members:
    - Colleen Cousins (ctc130030)
	- Matthew Villarreal (miv140130)

Source Files:
	- AI2.java		    : This contains our Main and our general CSP algorithm.
	- Node.java		    : This contains our class of Nodes to traverse our search tree, as well as the variable heuristics, value heuristics, constraint checking, and stdout methods.  
	- CSPCompare.java	: Used for priority queue to sort child search nodes by least constraining value.
	
Platform:
	- Windows and Linux

Problems We Had:
	- Most constraining variable heuristic       : Would not always break ties correctly when two variables were equally constrained.
    - Least constraining value heuristic         : Would have issues accumulating the number of valid values for each assignment and thus would not always pick the value that is the least constraining.
    - Backtracking                               : To select the least constraining value, all possible values of that variable would have a node created for it, then sorted in the priority queue based on which one is least constraining. When backtracking after a failure it would still create a node for the value which has already failed and thus loop back and forth between this creation and failure. 
    - Forward checking                           : Could not even attempt because of previously mentioned issues.

How to Run:
	Running in Linux
	1) Compile: javac AI2.java
	2) Compile: javac Node.java
    3) Compile: javac CSPCompare.java
	3) Run: java AI2 <*.var> <*.con> <none | fc>
	
	
