package main;
import gui.ManageProjects;
import gui.SimulateProjects;
import gui.ReviewProjects;
import classes.*;


public class Main {
	public static void main(String[] args) {
		//A Shared Instance Of The Class Statistics Shared Between View And Controller
		Statistics statistics = new Statistics();
		
		//Controller Frame (Manage Projects)-----------------------------------------------------------------
		ManageProjects projectsFrame = new ManageProjects(statistics);
		
		//View Frame (Simulation Section)--------------------------------------------------------------------
		SimulateProjects simulationFrame = new SimulateProjects(statistics);
		
		//View Frame (Filtering Section)--------------------------------------------------------------------
		ReviewProjects reviewProjects = new ReviewProjects(statistics);
	}
}