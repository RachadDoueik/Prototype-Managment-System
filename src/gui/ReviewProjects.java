package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import classes.Employee;
import classes.HumanResource;
import classes.Job;
import classes.MaterialResource;
import classes.Process;
import classes.Project;
import classes.Statistics;
import classes.Task;
import classes.Material;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import views.ProcessesComboView;
import views.ProjectsView2;
import views.TasksComboView;
import javax.swing.JButton;

public class ReviewProjects extends JFrame{
	
	
//--------------------------------------------------------Components Initialization-------------------------------

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel topPanel;
	private DefaultComboBoxModel<Project> projectsModel;
	private ProjectsView2 projectsCombo;
	private DefaultComboBoxModel<Task> tasksModel;
	private TasksComboView tasksCombo;
	private DefaultComboBoxModel<Process> processesModel;
	private ProcessesComboView processesCombo;
	private  JLabel projectsLabel;
	private  JLabel tasksLabel;
	private  JLabel processesLabel;
	private JScrollPane tableContainer;
	private JTable statsTable;
	private DefaultTableModel statsTableModel;
    private JButton showJobs;
    private JButton showEmployees;
    private JButton showMaterials;

//-----------------------------------------Initial Frame Setup---------------------------------------------------
	public ReviewProjects(Statistics statistics) {
		setTitle("OMEGA'S Prototype Management System: Projects Statistics");
		setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		mainPanel = new JPanel();
		mainPanel.setBackground(SystemColor.activeCaption);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
	    topPanel = new JPanel();
		topPanel.setBounds(0, 0, 984, 94);
		mainPanel.add(topPanel);
		topPanel.setLayout(null);
		
//---------------------------------------------------Handle Choosing a Project-----------------------------------------------
		projectsModel = new DefaultComboBoxModel<Project>();
		projectsCombo = new ProjectsView2(projectsModel , statistics);
		projectsCombo.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
			    if(projectsCombo.getSelectedIndex() > 0) {
			    	statsTableModel.setColumnIdentifiers(new String[]{"Task ID", "Task Type", "Task Duration (Hours)","Task Cost"});
			    	statsTableModel.setRowCount(0);
			    	for (Iterator<Map.Entry<Character, Task>> tskIt = selectedProject().getTasks().entrySet().iterator(); tskIt.hasNext();) {
						Task task = tskIt.next().getValue();
						if(task != null) {
							if(task.calculateTaskCost() > 0) {
								statsTableModel.insertRow(statsTableModel.getRowCount(), new String[] {task.getTaskId()+"",task.toString(),task.calculateTaskDuration()+"",task.calculateTaskCost()+"$"});
							}
						}
			    	}
				    tasksCombo.setEnabled(true);
				    tasksCombo.setProject(selectedProject());
				    tasksCombo.update();
			    }
			    else {
			    	statsTableModel.setColumnIdentifiers(new String[]{});
			    	statsTableModel.setRowCount(0);
			    	tasksCombo.setEnabled(false);
			    	processesCombo.setEnabled(false);
			    }
			}
			
		});
		projectsCombo.setBounds(93, 40, 177, 22);
		topPanel.add(projectsCombo);
//----------------------------------------------------------------Handle Selecting a Task-------------------------------------------
		tasksModel = new DefaultComboBoxModel<Task>();
		tasksCombo = new TasksComboView(tasksModel , selectedProject());
		tasksCombo.setEnabled(false);
		tasksCombo.addItemListener(new ItemListener() {
			 public void itemStateChanged(ItemEvent ie) {
				   if(tasksCombo.getSelectedIndex() > 0) {
					   statsTableModel.setColumnIdentifiers(new String[]{"Process ID" , "Process Name" , "Process Description" , "Process Duration (Hours)","Process Cost"});
					   statsTableModel.setRowCount(0);
					   for(Iterator<Process> pIt = selectedTask().getProcesses().iterator(); pIt.hasNext();) {
						   Process p = pIt.next();
						   if(p!=null) {
							   statsTableModel.insertRow(statsTableModel.getRowCount(),new String[] {p.getProcessId() + "" , p.getProcessName() , p.getProcessDescription() , p.calculateProcessDuration() + "" , p.calculateProcessCost() + "$"});
						   }
					   }
					    processesCombo.setEnabled(true);
					    processesCombo.setTask(selectedTask());
					    processesCombo.updateProcessesCombo();
				   }
				   else {
					   processesCombo.setEnabled(false);
				   }
			 }
		});
		tasksCombo.setBounds(415, 40, 177, 22);
		topPanel.add(tasksCombo);
//---------------------------------------------------------Handle Process Selection--------------------------------------------------------
		processesModel = new DefaultComboBoxModel<Process>();
        processesCombo = new ProcessesComboView(processesModel , selectedTask());
        processesCombo.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
			     if(processesCombo.getSelectedIndex() > 0) {
			    	 statsTableModel.setColumnIdentifiers(new String[]{"Resource ID" , "Resource Type" , "Employee/Material Name","Wage By Hour/Cost Per Unit", "Resource Usage ","Resource Cost"});
			    	 statsTableModel.setRowCount(0);
			    	 for(Iterator<HumanResource> hrIt = selectedProcess().getHumanResourses().iterator(); hrIt.hasNext();) {
						   HumanResource hr = hrIt.next();
						   if(hr!=null) {
							   statsTableModel.insertRow(statsTableModel.getRowCount(),new String[] {hr.getResourceId() + "" , hr.getResourceType()+"" , hr.getEmployee().getEmployeeName(), hr.getEmployee().getJob().getHourlyRate()+"$", hr.getWorkHours() + " Hours" , hr.calculateResourceCost() + "$"});
						   }
					   }
			    	 for(Iterator<MaterialResource> mrIt = selectedProcess().getMaterialResourses().iterator(); mrIt.hasNext();) {
						   MaterialResource mr = mrIt.next();
						   if(mr!=null) {
							   statsTableModel.insertRow(statsTableModel.getRowCount(),new String[] {mr.getResourceId() + "" , mr.getResourceType()+"("+ (mr.getMaterial().getMaterialType() == 'm'  ? "Misc" : "Raw") + ")", mr.getMaterial().getMaterialName(), mr.getMaterial().getCostPerunit()+"$" , mr.getUnitsUsed() + " Units" , mr.calculateResourceCost() + "$"});
						   }
					   }
			     }
				
			}
        	  
        });
        processesCombo.setEnabled(false);
        
		processesCombo.setBounds(700, 40, 177, 22); 
		topPanel.add(processesCombo);
		
	    projectsLabel = new JLabel("Projects:");
		projectsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		projectsLabel.setBounds(93, 11, 177, 30);
		topPanel.add(projectsLabel);
		
		tasksLabel = new JLabel("Tasks:");
		tasksLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		tasksLabel.setBounds(415, 11, 177, 30);
		topPanel.add(tasksLabel);
		
		processesLabel = new JLabel("Processes:");
		processesLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		processesLabel.setBounds(700, 11, 177, 30);
		topPanel.add(processesLabel);
		
		tableContainer = new JScrollPane();
		tableContainer.setBounds(10, 102, 964, 372);
		mainPanel.add(tableContainer);
		
		
		statsTableModel = new DefaultTableModel();
		statsTable = new JTable(statsTableModel);
		
		tableContainer.setViewportView(statsTable);
//---------------------------------------------------Show Jobs--------------------------------------------------------------
		showJobs = new JButton("Show Jobs");
		showJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(projectsCombo.getSelectedIndex() > -1) {
					projectsCombo.setSelectedIndex(0);
				}
				statsTableModel.setColumnIdentifiers(new String[]{});
		    	statsTableModel.setRowCount(0);
		    	statsTableModel.setColumnIdentifiers(new String[]{"Job ID","Job Name","Wage Per Hour"});
		    	for(Iterator<Job> jIt = statistics.getJobs().iterator(); jIt.hasNext();) {
		    		 Job j = jIt.next();
		    		 statsTableModel.insertRow(statsTableModel.getRowCount() , new String[]{j.getJobId()+"",j.getJobName(),j.getHourlyRate()+""});
		    	}
			}

			
		});
		showJobs.setBounds(21, 502, 131, 36);
		mainPanel.add(showJobs);
		
		showEmployees = new JButton("Show Employees");
		showEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(projectsCombo.getSelectedIndex() > -1) {
					projectsCombo.setSelectedIndex(0);
				}
				statsTableModel.setColumnIdentifiers(new String[]{});
		    	statsTableModel.setRowCount(0);
		    	statsTableModel.setColumnIdentifiers(new String[]{"Employee ID","Employee Name","Job Title"});
		    	for(Iterator<Employee> eIt = statistics.getEmployees().iterator(); eIt.hasNext();) {
		    		 Employee e = eIt.next();
		    		 statsTableModel.insertRow(statsTableModel.getRowCount() , new String[]{e.getEmployeeId()+"",e.getEmployeeName(),e.getJob().getJobName()});
		    	}
			}

			
		});
		showEmployees.setBounds(166, 502, 131, 36);
		mainPanel.add(showEmployees);
		
		showMaterials = new JButton("Show Materials");
		showMaterials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(projectsCombo.getSelectedIndex() > -1) {
					projectsCombo.setSelectedIndex(0);
				}
				statsTableModel.setColumnIdentifiers(new String[]{});
		    	statsTableModel.setRowCount(0);
		    	statsTableModel.setColumnIdentifiers(new String[]{"Material ID","Material Name","Material Type","Cost Per Unit"});
		    	for(Iterator<Material> mIt = statistics.getMaterials().iterator(); mIt.hasNext();) {
		    		 Material m = mIt.next();
		    		 statsTableModel.insertRow(statsTableModel.getRowCount() , new String[]{m.getMaterilaId()+"",m.getMaterialName(),m.getMaterialType() == 'r' ? "Raw" : "Misc", m.getCostPerunit()+"$"});
		    	}
			}

			
		});
		showMaterials.setBounds(315, 502, 131, 36);
		mainPanel.add(showMaterials);
	}
	
	
//-----------------------------------Selections Shortcuts----------------------------------------------
	public Project selectedProject() {
		return (Project) projectsModel.getSelectedItem();
	}

	public Task selectedTask() {

		return tasksModel.getElementAt(tasksCombo.getSelectedIndex());
	}
	
	public Process selectedProcess() {
		return processesModel.getElementAt(processesCombo.getSelectedIndex());
	}
}
