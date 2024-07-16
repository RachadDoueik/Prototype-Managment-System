package classes;
import interfaces.ProcessOperations;

import java.io.Serializable;
import java.util.*;
import helpers.MyObservable;

public class Process extends MyObservable implements Serializable ,ProcessOperations , Comparable<Process> {
 
	private static final long serialVersionUID = 1L;
	private static int nextProcess = 1;
    private int processId;
    private String processName;
    private String processState;
    private String processDescription;
    private HashSet<HumanResource> humanResources;
    private HashSet<MaterialResource> materialResources;
    private double processCost;
    private double processDuration;


    public Process(String processName , String processState , String processDescription){
          this.processId = nextProcess++;
          this.processName = processName;
          this.processDescription = processDescription;
          setProcessState(processState);
          humanResources = new HashSet<HumanResource>();
          materialResources = new HashSet<MaterialResource>();
          this.processCost = 0.0;
          this.processDuration = 0.0;
    }

    public int getProcessId(){
        return processId;
    }

    public String getProcessName(){
        return processName;
    }
    
    public String getProcessDescription(){
        return processDescription;
    }
    
    public double calculateProcessCost() {
    	double hrc = 0;
    	double mrc = 0;
        for(HumanResource hr : humanResources){
             hrc += hr.calculateResourceCost();
        }
        for(MaterialResource mr : materialResources){
            mrc += mr.calculateResourceCost();
        }
        return mrc + hrc;
    }
    
    public double calculateProcessDuration() {
    	 double pd = 0;
    	 for(Iterator<HumanResource> hrIt = humanResources.iterator(); hrIt.hasNext();) {
        	 pd += hrIt.next().getWorkHours();
    	 }
         return pd;
    }
    
    public void setProcessDescription(String newProcessDescription){
        processDescription = newProcessDescription;
        setChanged();
        notifyObservers();
    }

    public void setProcessName(String newProcessName){
        processName = newProcessName;
        setChanged();
        notifyObservers();
    }

    public String getProcessState(){
         return processState;
    }

    public void setProcessState(String newProcessState){
    	this.processState = newProcessState.equals("Done") || newProcessState.equals("In Progress") ? newProcessState : "In Progress";
        setChanged();
        notifyObservers();
    }

    public double humanResourcesCost(){
        double hrCost = 0.0;
        for(HumanResource hr : humanResources){
             hrCost += hr.calculateResourceCost();
        }
        return hrCost;
    }

    public double materialResourcesCost(){
         double mrCost = 0.0;
         for(MaterialResource mr : materialResources){
             mrCost += mr.calculateResourceCost();
         }
         return mrCost;
    }
    
    public void simulateProcess() {
    	 setProcessState("Done");
    }
    
    public void resetProcessSimulation() {
    	 setProcessState("In Progress");
    }
    
    public HashSet<HumanResource> getHumanResourses(){
    	  return humanResources;
    }
    
    public HashSet<MaterialResource> getMaterialResourses(){
  	  return materialResources;
  }

    public boolean addHumanResource(HumanResource hr){
          for(Iterator<HumanResource> hrIt = humanResources.iterator(); hrIt.hasNext();) {
        	   HumanResource chr = hrIt.next();
        	   if((chr.getEmployee().compareTo(hr.getEmployee())) == 0) {
        		     return false;
        	   }
          }
          humanResources.add(hr);
          setChanged();
          notifyObservers();
          return true;
    }
    
    public void removeHumanResource(HumanResource hr) {
    	if(humanResources.contains(hr)) {
    	  humanResources.remove(hr);
    	  setChanged();
    	  notifyObservers();
    	}
    }
    
    
    public void removeMaterialResource(MaterialResource mr) {
    	if(materialResources.contains(mr)) {
      	  materialResources.remove(mr);
      	  setChanged();
      	  notifyObservers();
      	}
    }

    public boolean addMaterialResource(MaterialResource mr){
    	for(Iterator<MaterialResource> mtIt = materialResources.iterator(); mtIt.hasNext();) {
     	   if((mtIt.next().getMaterial().compareTo(mr.getMaterial())) == 0) {
     		     return false;
     	   }
       }
        materialResources.add(mr);
        setChanged();
        notifyObservers();
        return true;
    }
    
    public int compareTo(Process p) {
    	return Integer.compare(processId, p.getProcessId());
    }

    public String toString(){
        return processName;
    }
    
}
