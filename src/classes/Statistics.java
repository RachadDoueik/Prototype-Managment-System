package classes;

import helpers.MyObservable;
import helpers.MyObserver;

import java.io.Serializable;
import java.util.*;


public class Statistics extends MyObservable implements Serializable , MyObserver{
	

	private static final long serialVersionUID = 1L;

	private HashSet<Project> projects;
	
	private HashSet<Employee> employees;
	
	private HashSet<Material> materials;
	
	private HashSet<Job> jobs;
	
	
	
	public Statistics () {
		 projects = new HashSet<Project>();
		 
		 employees = new HashSet<Employee>();
		 
		 materials = new HashSet<Material>();
		 
		 jobs = new HashSet<Job>();
	}
	
	public HashSet<Project> getProjects(){
		return projects;
	}
	
	public void setProjects(HashSet<Project> p) {
		 projects.clear();
		 for(Iterator<Project> it = p.iterator(); it.hasNext();) {
			 it.next().addObserver(this);
		 }
		 projects.addAll(p);
		 setChanged();
		 notifyObservers();
	}
	
	public void setEmployees(HashSet<Employee> e) {
		 employees.clear();
		 for(Iterator<Employee> it = e.iterator(); it.hasNext();) {
			 it.next().addObserver(this);
		 }
		 employees.addAll(e);
		 setChanged();
		 notifyObservers();
	}
	
	public void setJobs(HashSet<Job> j) {
		 jobs.clear();
		 for(Iterator<Job> it = j.iterator(); it.hasNext();) {
			 it.next().addObserver(this);
		 }
		 jobs.addAll(j);
		 setChanged();
		 notifyObservers();
	}
	
	public void setMaterials(HashSet<Material> m) {
		 materials.clear();
		 for(Iterator<Material> it = m.iterator(); it.hasNext();) {
			 it.next().addObserver(this);
		 }
		 materials.addAll(m);
		 setChanged();
		 notifyObservers();
	}
	
	
	public void addProject(Project p) {
		 p.addObserver(this);
		 projects.add(p);
		 setChanged();
		 notifyObservers();
	}
	
	public HashSet<Employee> getEmployees(){
		return employees;
	}
	
	public HashSet<Job> getJobs(){
		return jobs;
	}
	
	public void addJob(Job j) {
		 j.addObserver(this);
		 jobs.add(j);
		 setChanged();
		 notifyObservers();
	}
	
	public void addEmployee(Employee e) {
		 e.addObserver(this);
		 employees.add(e);
		 setChanged();
		 notifyObservers();
	}
	
	public HashSet<Material> getMaterials(){
		return materials;
	}
	
	
	public void addMaterial(Material m) {
		 m.addObserver(this);
		 materials.add(m);
		 setChanged();
		 notifyObservers();
	}
	
	public void update() {
		setChanged();
		notifyObservers();
	}
}