package views;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.*;
import classes.Project;
import classes.Task;
import classes.Process;
import helpers.MyObserver;

public class ProcessesView extends JList<Process> implements MyObserver {
	private static final long serialVersionUID = 1L;
	private HashMap<Character , Task> tasks;
    private DefaultListModel<Process> processesListModel;

    public ProcessesView(DefaultListModel<Process> processesListModel , Project project) {
        super(processesListModel);
        this.processesListModel = processesListModel;
        setTasks(project);
       
    }

    
    public void setTasks(Project project) {
    	 if(project != null) {
    		 this.tasks = project.getTasks();
        	 for(Iterator<Map.Entry<Character, Task>> it = tasks.entrySet().iterator(); it.hasNext();) {
             	  Task t = it.next().getValue();
             	  if(t != null) {
             		  t.addObserver(this);
             		 for(Iterator<Process> pit = t.getProcesses().iterator(); pit.hasNext();) {
             			  Process p = pit.next();
             			  if(!p.getHumanResourses().isEmpty() && !p.getMaterialResourses().isEmpty() && !p.getProcessState().equals("Done")) {
             				    p.addObserver(this);
             			  }
             		  }
             	  }
             	  
              }
    	 }
    	
    }

    public void updateProcessesList() {
        processesListModel.clear();
        for(Iterator<Map.Entry<Character, Task>> it = tasks.entrySet().iterator(); it.hasNext();) {
      	  Task t = it.next().getValue();
      	   if(t != null) {
      		  if(!t.getProcesses().isEmpty() && !t.getTaskState().equals("Done")) {
          		  for(Iterator<Process> pit = t.getProcesses().iterator(); pit.hasNext();) {
          			  Process p = pit.next();
          			  if(!p.getHumanResourses().isEmpty() && !p.getMaterialResourses().isEmpty()) {
          				   
          				    if(!p.getProcessState().equals("Done")) 
          				    {
          				    	 processesListModel.addElement(p);
          				    }
          				    else {
          				    	processesListModel.removeElement(p);
          				    }
          			  }
          		  }
          		 
          	  }
      		  setSelectedIndex(0);
      	   }
      	
       }
    }

	
	public void update() {
		updateProcessesList();
	}
 
}
