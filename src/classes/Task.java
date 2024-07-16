package classes;
import interfaces.TaskOperations;

import java.io.*;
import helpers.MyObservable;

import java.util.*;


public class Task extends MyObservable implements Serializable , TaskOperations , Comparable<Task>{
	private static final long serialVersionUID = 1L;
	private static int nextTask = 1;
     private int taskId;
     private char taskType;
     private double taskCost;
     private double taskDuration;
     private String taskState;
     private HashSet<Process> processes;
     boolean changed;

     public Task(char taskType , String taskState) {
         taskId = nextTask++;
         taskCost = 0.0;
         taskDuration = 0.0;
         setTaskState(taskState);
         setTaskType(taskType);
         processes = new HashSet<Process>();
         changed = false;
     }

     public int getTaskId(){
         return taskId;
     }

     public char getTaskType(){
         return taskType;
     }

     public void setTaskType(char tt){
         taskType = tt =='c' || tt == 'p' || tt == 'f' || tt == 'a' || tt == 't' ? tt : 'x';
         changed = false;
     }

     public String getTaskState(){
         return taskState;
     }

     public void setTaskState(String ts){
         taskState = ts.equals("Done") || ts.equals("In Progress") ? ts : "In Progress";
         setChanged();
         notifyObservers();
         changed = false;
     }
     
     public double calculateTaskCost() {
    	 double tc = 0;
    	 for(Process pr : processes){
    		 if(pr.getHumanResourses().size() > 0 && pr.getMaterialResourses().size() > 0) {
    			 tc += pr.calculateProcessCost();
    		 }	
         }
         return tc;
     }
     
     public double calculateTaskDuration(){
    	 double td = 0;
         for(Process pr : processes){
             td += pr.calculateProcessDuration();
         }
         return td;
    }
     
     public double getTaskCost() {
    	  return taskCost;
     }
     
     public double getTaskDuration() {
    	  return taskDuration;
     }
     
     public void resetTaskSimulation() {
    	 for(Process p : processes) {
    		 p.resetProcessSimulation();
    	 }
    	 setTaskState("In Progress");
     }
     
     public void simulateTask() {
    	 for(Process p : processes) {
    		 p.simulateProcess();
    	 }
    	 setTaskState("Done");
     }


     public void addProcess(Process pr){
           processes.add(pr);
           setChanged();
           notifyObservers();
           changed = false;
     }
     
     public boolean removeProcess(Process process) {
		  if(process.getHumanResourses().isEmpty() && process.getMaterialResourses().isEmpty()) {
			  processes.remove(process);
			  setChanged();
			  notifyObservers();
			  changed = false;
			  return true;
		  }
		  return false;
		 
	}
     
     public HashSet<Process> getProcesses(){
    	 return processes;
     }
     
     public int compareTo(Task t) {
    	  return Integer.compare(this.taskId , t.getTaskId());
     }
     
     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         Task task = (Task) o;
         return taskType == task.taskType;
     }

     @Override
     public int hashCode() {
         return Objects.hash(taskType);
     }

     public String toString(){
          return taskType == 'c' ? "Conception" : taskType == 'p' ? "Material Preparation" : taskType == 'f' ? "Elements Production" : taskType == 'a' ? "Assembly" : taskType == 't' ? "Testing" : "Choose Task";
     }
    
}
