package classes;

import interfaces.PrototypeOperations;
import helpers.MyObservable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Project extends MyObservable implements Serializable, PrototypeOperations, Comparable<Project>{
	static final long serialVersionUID = 1L;
	private static int nextProject = 1;
	private int projectId;
	private String projectName;
	private String projectDescription;
	private String projectState;
	private double projectCost;
	private double projectDuration;
	private HashMap<Character, Task> tasks;

	public Project(String projectName, String projectDescription, String projectState) {
		projectId = nextProject++;
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		tasks = new HashMap<Character, Task>();
		tasks.put('c', null);
		tasks.put('p', null);
		tasks.put('f', null);
		tasks.put('a', null);
		tasks.put('t', null);
		setProjectState(projectState);
		this.projectCost = 0;
		projectDuration = 0;

	}

	public int getProjectId() {
		return projectId;
	}

	public String getProjectName() {
		return projectName;
	}
	
	
	public void resetProjectSimulation(){
		Iterator<Map.Entry<Character, Task>> iterator = tasks.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Character, Task> entry = iterator.next();
			if (!(entry.getValue() == null)) {
				    entry.getValue().resetTaskSimulation();
			}
		}
		setProjectState("In Progress");
	}
	

	public void setProjectName(String newProjectName) {
		projectName = newProjectName;
		setChanged();
		notifyObservers();
	}
	
	public void setProjectDuration(double pd) {
		 projectDuration = pd;
		 setChanged();
		 notifyObservers();
	}
	
	public double calculateProjectCost() {
		double pc = 0;
		Iterator<Map.Entry<Character, Task>> iterator = tasks.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Character, Task> entry = iterator.next();
			if (!(entry.getValue() == null)) {
				pc += entry.getValue().calculateTaskCost();
			}

		}
		return pc;
	}
	
	public double getProjectCost() {
		 return projectCost;
	}
	
	public void setProjectCost(double newPC) {
		 projectCost = newPC;
		 setChanged();
		 notifyObservers();
	}
	
	public double calculateProjectDuration() {
		double pd = 0;
		Iterator<Map.Entry<Character, Task>> iterator = tasks.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Character, Task> entry = iterator.next();
			if (!(entry.getValue() == null)) {
				pd += entry.getValue().calculateTaskDuration();
			}
		}
		return pd;
	}
	
	public double getProjectDuration() {
		return projectDuration;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String description) {
		projectDescription = description;
		setChanged();
		notifyObservers();

	}

	public String getProjectState() {
		return projectState;
	}

	public void setProjectState(String newState) {
		projectState = newState.equals("Done") || newState.equals("In Progress") ? newState : "In Progress";
		setChanged();
		notifyObservers();
	}

	public void addTask(Task task) {
		tasks.put(task.getTaskType(), task);
		setChanged();
		notifyObservers();
	}

	public synchronized void removeTask(Task task) {
		tasks.remove(task.getTaskType(), task);
		setChanged();
		notifyObservers();
	}

	public void simulateProject() {
		Iterator<Map.Entry<Character, Task>> iterator = tasks.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Character, Task> entry = iterator.next();
			if (!(entry.getValue() == null)) {
				  entry.getValue().simulateTask();
			}
		}
		setProjectState("Done");
	}

	public HashMap<Character, Task> getTasks() {
		return tasks;
	}

	public int compareTo(Project p) {
		return Integer.compare(projectId, p.getProjectId());
	}

	public String toString() {
		return projectName;
	}


}
