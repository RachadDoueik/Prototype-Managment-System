package classes;
import java.io.Serializable;
import helpers.MyObserver;

import  helpers.MyObservable;

public class Employee extends MyObservable implements Comparable<Employee>, Serializable , MyObserver{
	private static final long serialVersionUID = 1L;
	private static int nextEmployee = 1;
    private int employeeId;
    private String employeeName;
    private Job job;
    boolean changed;

    public Employee(String employeeName , Job job) {
         employeeId = nextEmployee++;
         this.employeeName = employeeName;
         this.job = job;
    }

    public int getEmployeeId(){
        return employeeId;
    }

    public String getEmployeeName(){
        return employeeName;
    }

    public void setEmployeeName(String newEmployeeName){
         employeeName = newEmployeeName;
         setChanged();
         notifyObservers();
    }

    public Job getJob(){
        return job;
    }

    public void setJob(Job newJob){
        job = newJob;
        setChanged();
        notifyObservers();
    }

    public String toString(){
        return employeeName + " : " + job.getJobName();
    }
    
    public int compareTo(Employee e) {
    	  return Integer.compare(employeeId , e.getEmployeeId());
    }
    
    public void update() {
    	 setChanged();
    	 notifyObservers();
    }
     
}
