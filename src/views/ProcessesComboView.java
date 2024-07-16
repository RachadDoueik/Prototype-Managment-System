package views;


import java.util.Iterator;
import javax.swing.*;
import classes.Task;
import classes.Process;
import helpers.MyObserver;

public class ProcessesComboView extends JComboBox<Process> implements MyObserver {
	private static final long serialVersionUID = 1L;
	private Task task;
    private DefaultComboBoxModel<Process> processesComboModel;

    public ProcessesComboView(DefaultComboBoxModel<Process> processesComboModel , Task task) {
        super(processesComboModel);
        this.processesComboModel = processesComboModel;
        setTask(task);
       
    }

    
    public void setTask(Task task) {
    	          
             	  if(task != null) {
             		  this.task = task;
             		  this.task.addObserver(this);
             		 
             		 for(Iterator<Process> pit = task.getProcesses().iterator(); pit.hasNext();) {
             			  Process p = pit.next();
             			  if(!p.getHumanResourses().isEmpty() && !p.getMaterialResourses().isEmpty()) {
             				    p.addObserver(this);
             			  }
             		  }
             	  }
             	  
              }
    	 
    	
 

    public void updateProcessesCombo() {
        processesComboModel.removeAllElements();
        processesComboModel.addElement(new Process("Choose Process","","In Progress"));
      	   if(task != null) {
      		  if(!task.getProcesses().isEmpty()){
          		  for(Iterator<Process> pit = task.getProcesses().iterator(); pit.hasNext();) {
          			  Process p = pit.next();
          			  if(!p.getHumanResourses().isEmpty() && !p.getMaterialResourses().isEmpty()) {
          				  processesComboModel.addElement(p);
          			  }
          		  }
          		 
          	  }
       }
      
    }
    

	
	public void update() {
		updateProcessesCombo();
	}
 
}