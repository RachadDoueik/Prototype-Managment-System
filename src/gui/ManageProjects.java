package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import classes.*;
import classes.Process;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.awt.event.ItemEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.io.*;



public class ManageProjects extends JFrame {

	private static final long serialVersionUID = 1L;

//////////////////////////////////////////////////////Beans Initialization///////////////////////////////////////////////////////////////
	private JPanel mainPanel;
	private JPanel projectsSection;
	private JButton loadDataButton;
	private JButton saveDataToDisk;
	private JTextField projectIdField;
	private JTextField projectNameField;
	private JTextField processNameField;
	private JTextField nbrOfUnits;
	private ButtonGroup resourcesGroup;
	private JButton newProjectButton;
	private JLabel projectIdLabel;
	private JLabel projectNameLabel;
	private JLabel projectDescLbl;
	private JButton updateProjectInfoBtn;
	private JButton createProjectBtn;
	private JLabel tasksLabel;
	private JList<Task> tasksList;
	private JComboBox<String> tasksCombo;
	private JButton addTaskBtn;
	private JButton removeTaskBtn;
	private JLabel processesLabel;
	private JLabel processNameLabel;
	private JButton addProcessButton;
	private JButton removeProcessButton;
	private JLabel processDescriptionLabel;
	private JLabel resourcesLabel;
	private JScrollPane processDescContainer;
	private JScrollPane projectDescPane;
	private JRadioButton hrRadioButton;
	private JRadioButton materialRescRadio;
	private JButton manageRescButton;
	private JComboBox<Object> resourcesBox;
	private DefaultComboBoxModel<Object> resourcesComboModel;
	private JLabel chosenResourceLabel;
	private JLabel chosenValueLabel;
	private JLabel unitLabel;
	private JButton updateResource;
	private JButton removeRescBtn;
	private JButton addResBtn;
	private JLabel projectsComboLbl;
	private JButton cancelProjectButton;
	private JComboBox<Project> projectsComboBox;
	private JTextArea projectDescArea;
	private DefaultComboBoxModel<Project> projectsModel;
	private DefaultListModel<Task> tasksModel;
	private JScrollPane processesContainer;
	private JList<Process> processesList;
	private JScrollPane resourcesContainer;
	private JList<Resource> resourcesList;
	private DefaultListModel<Process> processesModel;
	private DefaultListModel<Resource> resourcesListModel;
	private JTextArea processDescArea;
	private JButton updateProcessInfo;
	private JButton newProcessButton;
	private JButton cancelProcessButton;
	////////////////////////////////////////////Employees/Jobs Window/////////////////////////////////////////
    private JFrame  employeesJobsEntry;
    private JPanel jobPanel;
    private JLabel jobNameLabel;
    private JLabel hourlyWageLabel;
    private JButton addJobButton;
    private JPanel employeePanel;
    private JLabel employeeNameLabel;
    private JLabel jobLabel;
    private JTextField jobNameField;
    private JTextField hourlyWageField;
    private JTextField employeeNameField;
    private JComboBox<Job> jobsComboBox;
    private JButton addEmployeeButton;
    private JRadioButton rawMaterialButton;
    private JRadioButton miscMaterialButton; 
    private ButtonGroup materialTypeGroup;
    private DefaultComboBoxModel<Employee> empsComboModel;
    private DefaultComboBoxModel<Job> jobsComboModel;
    private DefaultComboBoxModel<Job> jobsSecondComboModel;
    private JComboBox<Employee> empsCombo;
	private JComboBox<Job> jobsCombo;
	private JButton updateJobButton;
	private JButton updateEmployeeInfo;
////////////////////////////////////////////Employees/Jobs Window///////////////////////////////////////////////////////////////////////
    private JFrame  materialsEntry;
    private JPanel materialsPanel;
    private JLabel materialNameLabel;
    private JLabel pricePerUnitLabel;
    private JButton addMaterialButton;
    private JButton updateMaterialButton;
    private JTextField materialNameField;
    private JTextField pricePerUnitField;
	private DefaultComboBoxModel<Material> matsComboModel;
	private JComboBox<Material> matsCombo;
///////////////////////////////////////////////////////////Functionality Methods/////////////////////////////////////////////////////////

	
	
	
	//Forms Validations and Toggles--------------------------------------------------------------------------------------------
	public boolean projectNameEmpty() {
		return projectNameField.getText().equals("") || projectNameField.getText().equals(null);
	}

	public boolean projectDescEmpty() {
		return projectDescArea.getText().equals("") || projectDescArea.getText().equals(null);
	}

	public boolean processNameEmpty() {
		return processNameField.getText().equals("") || processNameField.getText().equals(null);
	}

	public boolean processDescEmpty() {
		return processDescArea.getText().equals("") || processDescArea.getText().equals(null);
	}

	public void clearProjectForm() {
		projectNameField.setText("");
		projectDescArea.setText("");
		projectIdField.setText("");
	}

	public void clearProcessesForm() {
		processNameField.setText("");
		processDescArea.setText("");
		processNameField.repaint();
		processDescArea.repaint();
	}

	public void clearProcessForm() {

	}

	public void ToggleProjectFields(boolean state) {
		projectIdField.setEnabled(state);
		projectNameField.setEnabled(state);
		projectDescArea.setEnabled(state);
	}

	public void ToggleTasksFields(boolean state) {
		tasksList.setEnabled(state);
		tasksCombo.setEnabled(state);
		tasksList.setEnabled(state);
	}

	public void ToggleProcessesArea(boolean state) {
		processNameField.setEnabled(state);
		processDescArea.setEnabled(state);
		addProcessButton.setEnabled(state);
	}

	public void ToggleResourcesSection(boolean state) {
		hrRadioButton.setEnabled(state);
		materialRescRadio.setEnabled(state);
	}
	
	public void clearResourcesSelection() {
		 hrRadioButton.setSelected(false);
		 materialRescRadio.setSelected(false);
	}
	
	public void resetResourcesLabels() {
		chosenResourceLabel.setText("Resource:");
		chosenValueLabel.setText("Usage:");
		unitLabel.setText("(value)");
	}
	
	
	public boolean jobNameEmpty() {
		return jobNameField.getText().equals("") || jobNameField.getText().equals(null);
	}

	public boolean jobHWEmpty() {
		return hourlyWageField.getText().equals("") || hourlyWageField.getText().equals(null);
	}
	
	public void clearJobsForm() {
		jobNameField.setText("");
		hourlyWageField.setText("");
		jobNameField.repaint();
		hourlyWageField.repaint();
	}
	
	public void ToggleJobsFields(boolean state) {
		jobNameField.setEnabled(state);
		hourlyWageField.setEnabled(state);
	}
	
	 public static boolean isDouble(String value) {
	        try {
	            Double.parseDouble(value);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	 
	 public static boolean isInt(String value) {
	        try {
	            Integer.parseInt(value);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	
	
	//Selections Shortcuts-----------------------------------------------------------------------------------------
	
	public Project selectedProject() {
		  return (Project) projectsModel.getElementAt(projectsComboBox.getSelectedIndex());
	}
	
	public Task selectedTask() {
		return tasksModel.get(tasksList.getSelectedIndex());
	}
	
	public Process selectedProcess() {
		return processesModel.get(processesList.getSelectedIndex());
	}
	
	public Job selectedJob() {
		 return (Job) jobsComboModel.getSelectedItem();
	}
	
	public Employee selectedEmployee() {
		 if(empsCombo.getSelectedIndex() > -1)  return (Employee) empsCombo.getSelectedItem();
		 return new Employee(" " , new Job("",0));
	}
	
	public Material selectedMaterial(){
		 return (Material) matsComboModel.getSelectedItem();
	}
	
	public Object selectedResFromBox() {
		   return resourcesComboModel.getSelectedItem();
	}
	
	public Resource selectedResource() {
		 if(resourcesList.getSelectedIndex() < 0) return null;
		 return  resourcesListModel.get(resourcesList.getSelectedIndex());
	}
	  
	

	
///////////////////////////////////////////////////////////Frame + Main Panel Setup//////////////////////////////////////////////////////////////
	
	
	
	public ManageProjects(Statistics statistics) {
		setForeground(new Color(0, 0, 0));
		setFont(new Font("Arial", Font.PLAIN, 13));
		setBackground(new Color(255, 255, 255));
		setTitle("OMEGA's Prototype Management System: Manage Projects");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		mainPanel = new JPanel();
		mainPanel.setBackground(SystemColor.menu);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		

		// Initial
		// Panel-------------------------------------------------------------------------------
		projectsSection = new JPanel();
		projectsSection.setLayout(null);
		projectsSection.setBackground(SystemColor.activeCaption);
		projectsSection.setBounds(0, 51, 984, 510);
		mainPanel.add(projectsSection);
		
////////////////////////////////////////////////////////////Read-Write Logic////////////////////////////////////////////////////////////
        //Load Data From Disk
		loadDataButton = new JButton("Load Data");
		loadDataButton.setBounds(25, 11, 115, 32);
		loadDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Statistics s = null;
				File storageFile = new File("src\\data\\data.dat");
				loadDataButton.setEnabled(false);
				projectsComboBox.setEnabled(true);

				if (!storageFile.exists()) {
					JOptionPane.showMessageDialog(projectsSection, "Data file does not exist!", "Load Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try (FileInputStream fileIn = new FileInputStream(storageFile);
						ObjectInputStream in = new ObjectInputStream(fileIn)) {

					s = (Statistics) in.readObject();

					if (s != null) {

						statistics.setProjects(s.getProjects());
						projectsModel.removeAllElements();
						projectsModel.addElement(new Project("Choose a Project", null, "In Progress"));
						for (Iterator<Project> pIt = statistics.getProjects().iterator(); pIt.hasNext();) {
							projectsModel.addElement(pIt.next());
						}
						projectsComboBox.repaint();

						statistics.setEmployees(s.getEmployees());
						empsComboModel.removeAllElements();
						empsComboModel.addElement(new Employee("Choose Employee", new Job("", 0)));
						for (Iterator<Employee> eIt = statistics.getEmployees().iterator(); eIt.hasNext();) {
							empsComboModel.addElement(eIt.next());
						}
						empsCombo.repaint();

						
						statistics.setJobs(s.getJobs());
						for (Iterator<Job> jIt = statistics.getJobs().iterator(); jIt.hasNext();) {
							jobsComboModel.addElement(jIt.next());
						}
						jobsCombo.repaint();
						
						for (Iterator<Job> jIt = statistics.getJobs().iterator(); jIt.hasNext();) {
							jobsSecondComboModel.addElement(jIt.next());
						}

						statistics.setMaterials(s.getMaterials());
						for (Iterator<Material> mIt = statistics.getMaterials().iterator(); mIt.hasNext();) {
							matsComboModel.addElement(mIt.next());
						}
						matsCombo.repaint();

						JOptionPane.showMessageDialog(projectsSection, "Data Loaded Successfully!", "Load Success",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						JOptionPane.showMessageDialog(projectsSection, "Loaded data is null!", "Load Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (IOException i) {
					i.printStackTrace();
					JOptionPane.showMessageDialog(projectsSection, "Failed To Load Data!", "Load Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (ClassNotFoundException c) {
					c.printStackTrace();
					JOptionPane.showMessageDialog(projectsSection, "Class not found!", "Load Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mainPanel.add(loadDataButton);

//Save Data to Disk
		saveDataToDisk = new JButton("Save Data");
		saveDataToDisk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					File storageDir = new File("src\\data");

					if (!storageDir.exists()) {
						storageDir.mkdirs();
					}

					File storageFile = new File(storageDir, "data.dat");

					int ok = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to save data? This cannot be undone.", "Save Data",
							JOptionPane.YES_NO_OPTION);
					if (ok == JOptionPane.YES_OPTION) {
						try (FileOutputStream os = new FileOutputStream(storageFile);
								ObjectOutputStream out = new ObjectOutputStream(os)) {
							if (statistics != null) {
								out.writeObject(statistics);
								JOptionPane.showMessageDialog(projectsSection, "Data Saved Successfully!",
										"Save Success", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(projectsSection, "No data to save!", "Save Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(projectsSection, "Error Saving Data!", "Save Error",
							JOptionPane.ERROR_MESSAGE);
					ioe.printStackTrace();
				}
			}

		});
		saveDataToDisk.setBounds(150, 11, 115, 32);
		mainPanel.add(saveDataToDisk);
/////////////////////////////////////////////////Employees/Jobs Window/////////////////////////////////////////////////////////////////
		//Frame Setup
		employeesJobsEntry = new JFrame();
		employeesJobsEntry.setTitle("Manage Employees");
		employeesJobsEntry.setSize(300, 400);
		employeesJobsEntry.setResizable(false);
		employeesJobsEntry.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		employeesJobsEntry.setLocationRelativeTo(this);
		employeesJobsEntry.addWindowListener(new java.awt.event.WindowAdapter() {
             @Override
             public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                 setVisible(true);
             }
         });
		employeesJobsEntry.getContentPane().setLayout(new GridLayout(2, 1));
		
//---------------------------------------------Jobs Panel Setup-----------------------------------------------------
		jobPanel = new JPanel();
        jobPanel.setLayout(new GridLayout(7, 1));
        jobPanel.setBackground(SystemColor.activeCaption);
        jobPanel.setBorder(BorderFactory.createTitledBorder("Job Entry"));
        jobNameLabel = new JLabel("Job Name:");
        jobNameField = new JTextField();
        hourlyWageLabel = new JLabel("Hourly Wage:");
        hourlyWageField = new JTextField();
        jobsComboModel = new DefaultComboBoxModel<Job>();
        jobsComboModel.addElement(new Job("Choose Job", 0));
        jobsCombo = new JComboBox<Job>(jobsComboModel);
        addJobButton = new JButton("Add Job");
        updateJobButton = new JButton("Update");
        updateJobButton.setEnabled(false);
        jobsSecondComboModel = new DefaultComboBoxModel<Job>();
        jobsSecondComboModel.addElement(new Job("Choose Job", 0));
        jobsComboBox = new JComboBox<Job>(jobsSecondComboModel);
        jobsComboBox.setSelectedIndex(0);
//------------------------------------Handle Select Employee's Job--------------------------------------------------
        jobsComboBox.addItemListener(new ItemListener() {
        	  public void itemStateChanged(ItemEvent ie) {
        		    if(jobsComboBox.getSelectedIndex() == 0) {
        		    	 addEmployeeButton.setEnabled(false);
        		    	 employeeNameField.setEnabled(false);
        		    	 employeeNameField.setText("");
        		    }
        		    else {
        		    	 addEmployeeButton.setEnabled(true);
        		    	 employeeNameField.setEnabled(true);
        		    }
        		     
        	  }
        });
//1------------------------------------Handle Adding a New Job------------------------------------------------------
       
        addJobButton.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent ae) {
        		int ok = JOptionPane.showInternalConfirmDialog(null, "Proceed Creating Job ?",
  						"Create Job", JOptionPane.YES_NO_OPTION);
  				if (ok == 0) {
        		  if (jobNameEmpty() || jobHWEmpty() || hourlyWageField.getText().equals("0") || hourlyWageField.getText().equals("0.0")) {
						if (jobNameEmpty()) {
							JOptionPane.showMessageDialog(null, "Please provide a name for the job created !",
									"Job Creation Error", JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(projectsSection,
									"Please provide an hourly wage for the concerned job !", "Job Creation Error",
									JOptionPane.ERROR_MESSAGE);
						}
        	  }
        	  else {
        		   
        		    if(isDouble(hourlyWageField.getText())) {
        		    	Job newJob = new Job(jobNameField.getText(), Double.parseDouble(hourlyWageField.getText()));
            		    statistics.addJob(newJob);
            		    jobsComboModel.addElement(newJob);
            		    jobsCombo.repaint();
            		    jobsSecondComboModel.removeAllElements();
            		    jobsSecondComboModel.addElement(new Job("Choose Job" , 0));
            		    for(Iterator<Job> jobIt = statistics.getJobs().iterator() ; jobIt.hasNext();) {
            	        	 jobsSecondComboModel.addElement(jobIt.next());
            	        }
            		    jobsComboBox.repaint();
            		    
            		    
    					JOptionPane.showMessageDialog(null, "Job Created Successfully!",
    							"Job Creation Success", JOptionPane.INFORMATION_MESSAGE);
    					jobNameField.setText("");
    					hourlyWageField.setText("");
        		    }
        		    else {
        		    	JOptionPane.showMessageDialog(projectsSection,
								"Please provide a valid hourly wage for the concerned job !", "Job Creation Error",
								JOptionPane.ERROR_MESSAGE);
        		    }
        		     
        		      
				}
        	  }}});
//-------------------------------------------Handle Job Selection-----------------------------------------------------------------------------------------------
        
        if(jobsComboModel.getSize() == 0) jobsCombo.setEnabled(false);
        jobsCombo.addItemListener(new ItemListener() {
        	   public void itemStateChanged(ItemEvent ie) {
        		     if(jobsCombo.getSelectedIndex() == 0){
        		    	 updateJobButton.setEnabled(false);
        		     }
        		     else {
        		    	 updateJobButton.setEnabled(true);
        		    	 ToggleJobsFields(true);
        		    	 jobNameField.setText(selectedJob().getJobName());
        		    	 hourlyWageField.setText(selectedJob().getHourlyRate() + "");
        		     }
        	   }
        });
        
        
//3----------------------------------------Handle Update Job----------------------------------------------------------------------------------------
        updateJobButton.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ae) 
                   {
                	  int ok = JOptionPane.showInternalConfirmDialog(null, "Proceed Updating Job ?",
        						"Create Job", JOptionPane.YES_NO_OPTION);
        				if (ok == 0) {
              		  if (jobNameEmpty() || jobHWEmpty()||hourlyWageField.getText().equals("0") ||hourlyWageField.getText().equals("0.0")) {
      						if (jobNameEmpty()) {
      							JOptionPane.showMessageDialog(null, "Please provide a name for the job created !",
      									"Job Update Error", JOptionPane.ERROR_MESSAGE);
      						} else {
      							JOptionPane.showMessageDialog(projectsSection,
      									"Please provide an hourly wage for the concerned job !", "Job Update Error",
      									JOptionPane.ERROR_MESSAGE);
      						}
              	  }
              	  else {
              		   
              		    if(isDouble(hourlyWageField.getText())) {
              		    	selectedJob().setJobName(jobNameField.getText());
              		    	double hr = Double.parseDouble( hourlyWageField.getText());
              		    	selectedJob().setHourlyRate(hr);
                  		    jobsCombo.repaint();
                  		    jobsComboBox.repaint();
                             
          					JOptionPane.showMessageDialog(null, "Job Updated Successfully!",
          							"Job Creation Success", JOptionPane.INFORMATION_MESSAGE);
              		    }
              		    else {
              		    	JOptionPane.showMessageDialog(projectsSection,
      								"Please provide a valid hourly wage for the concerned job !", "Job Update Error",
      								JOptionPane.ERROR_MESSAGE);
              		    }
              		     
              		      
      				}
              	  }
                }
        });
        jobPanel.add(jobNameLabel);
        jobPanel.add(jobNameField);
        jobPanel.add(hourlyWageLabel);
        jobPanel.add(hourlyWageField);
        jobPanel.add(updateJobButton);
        jobPanel.add(addJobButton);
        jobPanel.add(jobsCombo);
        

//-------------------------------------------Employees Panel Setup--------------------------------------------------------
        
        employeePanel = new JPanel();
        employeePanel.setBackground(SystemColor.activeCaption);
        employeePanel.setLayout(new GridLayout(7, 1));
        employeePanel.setBorder(BorderFactory.createTitledBorder("Employee Entry"));
        
        employeeNameLabel = new JLabel("Employee Name:");
        employeeNameField = new JTextField();
        jobLabel = new JLabel("Select Job:");
        
        updateEmployeeInfo = new JButton("Update");
        updateEmployeeInfo.setEnabled(false);
        empsComboModel = new DefaultComboBoxModel<Employee>();
        empsComboModel.addElement(new Employee("Choose Employee" , new Job("", 0)));
        empsCombo= new JComboBox<Employee>(empsComboModel);
//--------------------------------------------Handle Add New Employee-----------------------------------------------------
       
        
        addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.addActionListener(new ActionListener(){
        	 public void actionPerformed(ActionEvent ae) {
        		 int ok = JOptionPane.showInternalConfirmDialog(null, "Proceed Adding Employee ?",
 						"Add Employee", JOptionPane.YES_NO_OPTION);
 				if (ok == 0) {
       		  if (employeeNameField.getText().equals("") || jobsComboBox.getSelectedIndex() == 0) {
						if (employeeNameField.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Please provide a name for the new employee !",
									"Employee Adding Error", JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(projectsSection,
									"Please select a job for the new employee !", "Employee Create Error",
									JOptionPane.ERROR_MESSAGE);
						}
       	  }
       	  else {
       		       JOptionPane.showMessageDialog(projectsSection,
				  "Employee Added Successfully !", "Employee Adding Success",
				   JOptionPane.INFORMATION_MESSAGE);
       		       Employee newEmployee = new Employee(employeeNameField.getText(), (Job) jobsSecondComboModel.getSelectedItem());
       		       statistics.addEmployee(newEmployee);
       		       empsComboModel.addElement(newEmployee);
       		       resourcesComboModel.addElement(newEmployee);
       		       employeeNameField.setText("");
       		       empsCombo.repaint();
       		        
				}
       	  }
       }
        });
 //-------------------------------------------Handle Employee Selection For Update-----------------------------------------
        empsCombo.addItemListener(new ItemListener() {
        	  public void itemStateChanged(ItemEvent ie) {
        		    if(empsCombo.getSelectedIndex()  ==  0) {
        		    	updateEmployeeInfo.setEnabled(false);
        		    	employeeNameField.setText("");
        		    }
        		    else {
        		    	updateEmployeeInfo.setEnabled(true);
        		    	if(selectedEmployee().equals(null)) {
        		    		employeeNameField.setText("");
        		    		
        		    	}
        		    	else {
        		    		employeeNameField.setText(selectedEmployee().getEmployeeName());
        		    		jobsSecondComboModel.setSelectedItem(selectedEmployee().getJob());
        		    		employeeNameField.setEnabled(true);
        		    	}
        		    	
        		    }
        		     
        	  }
        });
 
//---------------------------------------------------Handle Employee Update-----------------------------------------------
        
        updateEmployeeInfo.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent ae) {
        		 int ok = JOptionPane.showInternalConfirmDialog(null, "Proceed Updating Employee ?",
  						"Update Employee", JOptionPane.YES_NO_OPTION);
  				if (ok == 0) {
        		  if (employeeNameField.getText().equals("") || jobsComboBox.getSelectedIndex() == 0) {
 						if (employeeNameField.getText().equals("")) {
 							JOptionPane.showMessageDialog(null, "Please provide a name for the new employee !",
 									"Employee Update Error", JOptionPane.ERROR_MESSAGE);
 						} else {
 							JOptionPane.showMessageDialog(projectsSection,
 									"Please select a job for the selected employee !", "Employee Update Error",
 									JOptionPane.ERROR_MESSAGE);
 						}
        	  }
        	  else {
        		       JOptionPane.showMessageDialog(projectsSection,
 				  "Employee Updated Successfully !", "Employee Update Success",
 				   JOptionPane.INFORMATION_MESSAGE);
        		       selectedEmployee().setEmployeeName(employeeNameField.getText());
        		       selectedEmployee().setJob((Job)jobsSecondComboModel.getSelectedItem());
        		       empsCombo.repaint();       
 				}
        	  }
        	 }
        });
        
        
        
        employeePanel.add(employeeNameLabel);
        employeePanel.add(employeeNameField);
        employeePanel.add(jobLabel);
        employeePanel.add(jobsComboBox);
        employeePanel.add(updateEmployeeInfo);
        employeePanel.add(addEmployeeButton);
        employeePanel.add(empsCombo);

        employeesJobsEntry.getContentPane().add(jobPanel);
        employeesJobsEntry.getContentPane().add(employeePanel);
        
        
////////////////////////////////////////////////////////////Materials Entry////////////////////////////////////////////////////////////
        materialsEntry = new JFrame();
		materialsEntry.setTitle("Manage Materials");
		materialsEntry.setSize(300, 300);
		materialsEntry.setResizable(false);
		materialsEntry.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		materialsEntry.setLocationRelativeTo(this);
		materialsEntry.addWindowListener(new java.awt.event.WindowAdapter() {
             @Override
             public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                 setVisible(true);
             }
         });
		materialsPanel = new JPanel();
		materialsPanel.setLayout(new GridLayout(10, 2));
		materialsPanel.setBackground(SystemColor.activeCaption);
		materialsPanel.setBorder(BorderFactory.createTitledBorder("Materials"));

        materialNameLabel = new JLabel("Material Name:");
        materialNameField = new JTextField();
        pricePerUnitLabel = new JLabel("Price Per Unit");
        pricePerUnitField = new JTextField();
        updateMaterialButton = new JButton("Update");
        rawMaterialButton = new JRadioButton("Raw Material");
        rawMaterialButton.setSelected(true);
        rawMaterialButton.setBackground(SystemColor.activeCaption);
        miscMaterialButton = new JRadioButton("Misc Material");
        miscMaterialButton.setBackground(SystemColor.activeCaption);
        materialTypeGroup = new ButtonGroup();
        materialTypeGroup.add(rawMaterialButton);
        materialTypeGroup.add(miscMaterialButton);
        addMaterialButton = new JButton("Add");
        updateMaterialButton = new JButton("Update");
        updateMaterialButton.setEnabled(false);
        matsComboModel = new DefaultComboBoxModel<Material>();
        matsComboModel.addElement(new Material('x', 0, "Choose Material"));
        matsCombo = new JComboBox<Material>(matsComboModel);
        matsCombo.setSelectedIndex(0);
//----------------------------------------------Handle Material Selection-------------------------------------------------
        matsCombo.addItemListener(new ItemListener() {
        	 public void itemStateChanged(ItemEvent ie) {
        		   if(matsCombo.getSelectedIndex() == 0) {
        			    addMaterialButton.setEnabled(true);
        			    updateMaterialButton.setEnabled(false);
        			    
        		   }
        		   else {
        			   addMaterialButton.setEnabled(false);
        			   updateMaterialButton.setEnabled(true);
    			       materialNameField.setText(selectedMaterial().getMaterialName());
       			       pricePerUnitField.setText(selectedMaterial().getCostPerunit() + "");
       			       if(selectedMaterial().getMaterialType() == 'r') {
       			    	    rawMaterialButton.setSelected(true);
       			       }
       			       else if(selectedMaterial().getMaterialType() == 'm') {
   			    	       miscMaterialButton.setSelected(true);
   			          }
        		   }
        	 }
        });
      
//----------------------------------------------Handle Adding A New Material--------------------------------------------------
        addMaterialButton.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent ae) {
        		  int ok = JOptionPane.showInternalConfirmDialog(null, "Proceed Adding Material ?",
    						"Add Material", JOptionPane.YES_NO_OPTION);
    			if (ok == 0) {
          		  if (materialNameField.getText().equals("") || pricePerUnitField.getText().equals("") ||  pricePerUnitField.getText().equals("0")|| pricePerUnitField.getText().equals("0.0")) {
   						if (materialNameField.getText().equals("")) {
   							JOptionPane.showMessageDialog(null, "Please provide a name for the new material !",
   									"Material Adding Error", JOptionPane.ERROR_MESSAGE);
   						} else {
   							JOptionPane.showMessageDialog(projectsSection,
   									"Please enter a price for the new material!", "Material Adding Error",
   									JOptionPane.ERROR_MESSAGE);
   						}
          	  }
          	  else {
          		  if(isDouble(pricePerUnitField.getText())) {
          			   double ppu =  Double.parseDouble(pricePerUnitField.getText());
          			   char mt = 'x';
          			   if(rawMaterialButton.isSelected()) {
          				   mt = 'r';
          			   }
          			   else if(miscMaterialButton.isSelected()){
          				  mt = 'm';
          			   }
          			   Material newMaterial = new Material(mt, ppu, materialNameField.getText());
          			   statistics.addMaterial(newMaterial);
          			   matsComboModel.addElement(newMaterial);
          			   resourcesComboModel.addElement(newMaterial);
          			   JOptionPane.showMessageDialog(projectsSection,
           			   "Material added successfully !", "Material Adding Success",
           			   JOptionPane.INFORMATION_MESSAGE); 
          			   matsCombo.repaint();
          			  
          		  }
          		  else {
          			JOptionPane.showMessageDialog(projectsSection,
             				  "Please provide a valide price price per unit !", "Material Adding Error",
             				   JOptionPane.ERROR_MESSAGE); 
          		  }
          		        
   				}
          	  }
        	  }
        });
//-------------------------------------------------Handle Update Material--------------------------------------------------------------------------
        updateMaterialButton.addActionListener(new ActionListener() {
        	    public void actionPerformed(ActionEvent ae) {
        	    int ok = JOptionPane.showInternalConfirmDialog(null, "Proceed Updating Material ?",
     						"Update Material", JOptionPane.YES_NO_OPTION);
     			if (ok == 0) {
           		  if (materialNameField.getText().equals("") || pricePerUnitField.getText().equals("") ||  pricePerUnitField.getText().equals("0") ||  pricePerUnitField.getText().equals("0.0")) {
    						if (materialNameField.getText().equals("")) {
    							JOptionPane.showMessageDialog(null, "Please provide a name for the new material !",
    									"Material Updating Error", JOptionPane.ERROR_MESSAGE);
    						} else {
    							JOptionPane.showMessageDialog(projectsSection,
    									"Please enter a price for this material!", "Material Updating Error",
    									JOptionPane.ERROR_MESSAGE);
    						}
           	  }
           	  else {
           		  if(isDouble(pricePerUnitField.getText())) {
           			   double ppu =  Double.parseDouble(pricePerUnitField.getText());
           			   char mt = 'x';
           			   if(rawMaterialButton.isSelected()) {
           				   mt = 'r';
           			   }
           			   else if(miscMaterialButton.isSelected()){
           				  mt = 'm';
           			   }
           			   selectedMaterial().setMaterialName(materialNameField.getText());
           			   selectedMaterial().setMaterialType(mt);
           			   selectedMaterial().setCostPerUnit(ppu);
           			   JOptionPane.showMessageDialog(projectsSection,
            			   "Material added successfully !", "Material Updating Success",
            			   JOptionPane.INFORMATION_MESSAGE); 
           			   matsCombo.repaint();
           			  
           		  }
           		  else {
           			JOptionPane.showMessageDialog(projectsSection,
              				  "Please provide a valide price price per unit !", "Material Updating Error",
              				   JOptionPane.ERROR_MESSAGE); 
           		  }
           		        
    				}
           	  }
        	    }
        });
        
        
        
        materialsPanel.add(materialNameLabel);
        materialsPanel.add(materialNameField);
        materialsPanel.add(pricePerUnitLabel);
        materialsPanel.add(pricePerUnitField);
        materialsPanel.add(new JLabel(""));
        materialsPanel.add(new JLabel(""));
        materialsPanel.add(miscMaterialButton);
        materialsPanel.add(rawMaterialButton);
        materialsPanel.add(new JLabel(""));
        materialsPanel.add(new JLabel(""));
        materialsPanel.add(addMaterialButton);
        materialsPanel.add(updateMaterialButton);
        materialsPanel.add(new JLabel(""));
        materialsPanel.add(new JLabel(""));
        materialsPanel.add(matsCombo);
        materialsPanel.add(new JLabel(""));
        materialsPanel.add(new JLabel(""));
        materialsPanel.add(new JLabel(""));
        materialsPanel.add(new JLabel(""));
     
        
        materialsEntry.getContentPane().add(materialsPanel);
        



//////////////////////////////////////////////////Not Commonly Used Components Declarations/////////////////////////////////////////

		// JLabels and JTextFields ----------------------------------------------
		projectIdLabel = new JLabel("Project ID:");
		projectIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		projectIdLabel.setBounds(10, 148, 96, 14);
		projectsSection.add(projectIdLabel);

		projectIdField = new JTextField();
		projectIdField.setEnabled(false);
		projectIdField.setEditable(false);
		projectIdField.setColumns(10);
		projectIdField.setBounds(116, 146, 76, 20);
		projectsSection.add(projectIdField);

		projectNameField = new JTextField();
		projectNameField.setEnabled(false);
		projectNameField.setColumns(10);
		projectNameField.setBounds(116, 182, 144, 20);
		projectsSection.add(projectNameField);

		projectNameLabel = new JLabel("Project Name:");
		projectNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		projectNameLabel.setBounds(10, 184, 96, 14);
		projectsSection.add(projectNameLabel);

		projectDescLbl = new JLabel("Description:");
		projectDescLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		projectDescLbl.setBounds(10, 224, 96, 14);
		projectsSection.add(projectDescLbl);

		tasksLabel = new JLabel("Tasks:");
		tasksLabel.setFont(new Font("Arial", Font.BOLD, 15));
		tasksLabel.setBounds(319, 26, 86, 14);
		projectsSection.add(tasksLabel);

		processesLabel = new JLabel("Processes:");
		processesLabel.setFont(new Font("Arial", Font.BOLD, 15));
		processesLabel.setBounds(319, 207, 86, 14);
		projectsSection.add(processesLabel);

		processNameLabel = new JLabel("Process Name:");
		processNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		processNameLabel.setBounds(319, 346, 96, 14);
		projectsSection.add(processNameLabel);

		processNameField = new JTextField();
		processNameField.setEnabled(false);
		processNameField.setColumns(10);
		processNameField.setBounds(499, 343, 144, 20);
		projectsSection.add(processNameField);

		processDescriptionLabel = new JLabel("Description:");
		processDescriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		processDescriptionLabel.setBounds(319, 380, 96, 14);
		projectsSection.add(processDescriptionLabel);

		resourcesLabel = new JLabel("Resources:");
		resourcesLabel.setFont(new Font("Arial", Font.BOLD, 15));
		resourcesLabel.setBounds(712, 26, 86, 14);
		projectsSection.add(resourcesLabel);

		processDescContainer = new JScrollPane();
		processDescContainer.setEnabled(false);
		processDescContainer.setBounds(448, 374, 195, 75);
		projectsSection.add(processDescContainer);

		processDescArea = new JTextArea();
		processDescContainer.setViewportView(processDescArea);

		projectDescPane = new JScrollPane();
		projectDescPane.setBounds(10, 249, 254, 135);
		projectsSection.add(projectDescPane);

		projectDescArea = new JTextArea();
		projectDescArea.setEnabled(false);
		projectDescPane.setViewportView(projectDescArea);
		chosenResourceLabel = new JLabel("Resource:");
		chosenResourceLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chosenResourceLabel.setBounds(702, 298, 96, 14);
		projectsSection.add(chosenResourceLabel);

		chosenValueLabel = new JLabel("Usage:");
		chosenValueLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chosenValueLabel.setBounds(702, 370, 96, 14);
		projectsSection.add(chosenValueLabel);

		nbrOfUnits = new JTextField();
		nbrOfUnits.setEnabled(false);
		nbrOfUnits.setColumns(10);
		nbrOfUnits.setBounds(821, 368, 86, 20);
		projectsSection.add(nbrOfUnits);

		unitLabel = new JLabel("(value)");
		unitLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		unitLabel.setBounds(917, 370, 46, 14);
		projectsSection.add(unitLabel);
		
		
		projectsComboLbl = new JLabel("Projects:");
		projectsComboLbl.setFont(new Font("Arial", Font.BOLD, 15));
		projectsComboLbl.setBounds(10, 26, 86, 14);
		projectsSection.add(projectsComboLbl);

		processesContainer = new JScrollPane();
		processesContainer.setBounds(319, 237, 321, 93);
		projectsSection.add(processesContainer);
		

		resourcesContainer = new JScrollPane();
		resourcesContainer.setBounds(695, 95, 279, 180);
		projectsSection.add(resourcesContainer);

////////////////////////////////////////////////////////////// Project Relate dLogic//////////////////////////////////////////////////////

//1-----------------------------------------------------------New Project-------------------------------------------------------------------
	
		newProjectButton = new JButton("New Project");
		newProjectButton.setEnabled(false);
		newProjectButton.setBounds(10, 83, 108, 35);
		newProjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				projectsComboBox.setEnabled(false);
				ToggleProjectFields(true);
				createProjectBtn.setEnabled(true);
				updateProjectInfoBtn.setEnabled(false);
				cancelProjectButton.setEnabled(true);
			}
		});
		projectsSection.add(newProjectButton);
		

//2----------------------------------------------------------Update Project--------------------------------------------------------------
		
		updateProjectInfoBtn = new JButton("Update Info");
		updateProjectInfoBtn.setEnabled(false);
		updateProjectInfoBtn.setBounds(10, 408, 98, 42);
		updateProjectInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int ok = JOptionPane.showInternalConfirmDialog(null, "Proceed Updating Project Information ?",
						"Update Project Info", JOptionPane.YES_NO_OPTION);
				if (ok == 0) {
					if (projectNameEmpty() || projectDescEmpty()) {
						if (projectNameEmpty()) {
							JOptionPane.showMessageDialog(projectsSection, "Please Provide A Name For Your Project !",
									"Project Update Error", JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(projectsSection,
									"Please Provide A Description For Your Project !", "Project Update Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						selectedProject().setProjectName(projectNameField.getText());
						selectedProject().setProjectDescription(projectDescArea.getText());
						JOptionPane.showMessageDialog(projectsSection, "Project Info Updated Successfully!",
								"Update Success", JOptionPane.INFORMATION_MESSAGE);
						projectsComboBox.repaint();

					}
				}
			}
		});
		projectsSection.add(updateProjectInfoBtn);
		

//3---------------------------------------------------------Submit New Project--------------------------------------------------------------
		
		
		createProjectBtn = new JButton("Create");
		createProjectBtn.setEnabled(false);
		createProjectBtn.setBounds(118, 408, 98, 42);
		createProjectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (projectNameEmpty() || projectDescEmpty()) {
					if (projectNameEmpty()) {
						JOptionPane.showMessageDialog(projectsSection, "Please provide a name for your project !",
								"Project Creation Error", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(projectsSection,
								"please provide a description for your project !", "Project Creation Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					Project newProject = new Project(projectNameField.getText(), projectDescArea.getText(),
							"In Progress");
					statistics.addProject(newProject);
					projectsModel.addElement(newProject);
					projectsComboBox.setEnabled(true);
					clearProjectForm();
					ToggleProjectFields(false);
					createProjectBtn.setEnabled(false);
					cancelProjectButton.setEnabled(false);

				}
			}
		});
		projectsSection.add(createProjectBtn);

		
//4----------------------------------------------------Select Project---------------------------------------------------------------------
		
		
		projectsModel = new DefaultComboBoxModel<Project>();
		projectsModel.addElement(new Project("Choose a Project", null, "In Progress"));
		projectsModel.setSelectedItem(new Project("Choose a Project", "", "In Progress"));

		projectsComboBox = new JComboBox<Project>(projectsModel);
		projectsComboBox.setEnabled(false);
		projectsComboBox.setSelectedIndex(0);
		projectsComboBox.setBounds(10, 51, 235, 22);
		projectsComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				if (ie.getStateChange() == ItemEvent.SELECTED) {
					tasksList.setSelectedIndex(-1);
					if (selectedProject().toString().equals("Choose a Project") && projectsComboBox.isEnabled()) {
						newProjectButton.setEnabled(true);
						ToggleTasksFields(false);
						ToggleProjectFields(false);
						clearProjectForm();
						resourcesGroup.clearSelection();
						tasksCombo.setSelectedIndex(0);
						tasksModel.clear();
						updateProjectInfoBtn.setEnabled(false);
					} else {
						ToggleProjectFields(true);
						projectIdField.setText(selectedProject().getProjectId() + "");
						projectNameField.setText(selectedProject().getProjectName() + "");
						projectDescArea.setText(selectedProject().getProjectDescription());
						newProjectButton.setEnabled(false);
						ToggleTasksFields(true);
						updateProjectInfoBtn.setEnabled(true);
						tasksModel.clear();
						clearProcessesForm();
						Iterator<Map.Entry<Character, Task>> entIt = selectedProject().getTasks().entrySet().iterator();
						 do {
							Map.Entry<Character, Task> ent = entIt.next();
							Task t = ent.getValue();
							if(t != null) tasksModel.addElement(t);
							}
						 while(entIt.hasNext());

					}
				}
			}
		});
		projectsSection.add(projectsComboBox);
		
		
/////////////////////////////////////////////////////////Task Related Logic/////////////////////////////////////////////////////////////////////////////////
		
		
//1--------------------------------------------------------- Tasks Choice--------------------------------------------------------------------------------------
				
		String[] tasksNames = { "Choose Task", "Conception", "Material Preparation", "Parts Production", "Assembly",
						"Test" };
				tasksCombo = new JComboBox<String>(tasksNames);
				tasksCombo.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent ie) {
						if (ItemEvent.SELECTED == ie.getStateChange()) {
							String selected = tasksCombo.getSelectedItem().toString();
							if (selected.equals("Choose Task")) {
								addTaskBtn.setEnabled(false);
								removeTaskBtn.setEnabled(false);
							}
						} else {
							addTaskBtn.setEnabled(true);
						}
					}
				});
				tasksCombo.setEnabled(false);
				tasksCombo.setBounds(319, 148, 123, 22);
				projectsSection.add(tasksCombo);
		
				
				
//2-------------------------------------------------------Task Selection-------------------------------------------------------------------------------------
			tasksModel = new DefaultListModel<Task>();
			tasksList = new JList<Task>(tasksModel);
			tasksList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
						if (tasksList.getSelectedIndex() < 0) {
							clearProcessForm();
							removeTaskBtn.setEnabled(false);
							ToggleProcessesArea(false);
							processesModel.clear();
							processesList.repaint();
							processesList.setEnabled(false);
							newProcessButton.setEnabled(false);

						} else {
							clearProcessForm();
							processesModel.clear();
							removeTaskBtn.setEnabled(true);
							processesList.setEnabled(true);
							newProcessButton.setEnabled(true);
							ToggleProcessesArea(false);
							HashSet<Process> processes = selectedTask().getProcesses();
							for (Iterator<Process> procIt = processes.iterator(); procIt.hasNext();) {
								processesModel.addElement(procIt.next());
							}

						}
					}
				});

				tasksList.setEnabled(false);
				tasksList.setVisibleRowCount(5);
				tasksList.setBounds(319, 44, 237, 93);
				projectsSection.add(tasksList);

				
//3-------------------------------------------------------- Add Task------------------------------------------------------------------------------------------
				addTaskBtn = new JButton("+");
				addTaskBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						String selectedTaskName = (String) tasksCombo.getSelectedItem();
						char taskType = 'x';
						switch (selectedTaskName) {
						case "Conception":
							taskType = 'c';
							break;
						case "Material Preparation":
							taskType = 'p';
							break;
						case "Parts Production":
							taskType = 'f';
							break;
						case "Assembly":
							taskType = 'a';
							break;
						case "Test":
							taskType = 't';
							break;
						default:
							taskType = 'x';
						}

						Task newTask = new Task(taskType, selectedTaskName);

						boolean taskExistsInList = tasksModel.contains(newTask);

						if (!taskExistsInList) {
							if ((selectedProject().getTasks().get(taskType) == null)) {
								tasksModel.addElement(newTask);
								Project selectedP = (Project) projectsModel.getSelectedItem();
								selectedP.addTask(newTask);
							} else {
								JOptionPane.showMessageDialog(projectsSection, "This task already exists in your project !",
										"Add Task Error", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(projectsSection, " This task already exists in the list!",
									"Add Task Error", JOptionPane.ERROR_MESSAGE);
						}

					}
				});

				addTaskBtn.setEnabled(false);
				addTaskBtn.setBounds(448, 148, 49, 22);
				projectsSection.add(addTaskBtn);
				
				
				
//4 ---------------------------------------------------------Remove Task---------------------------------------------------------------------------------------
				removeTaskBtn = new JButton("-");
				removeTaskBtn.setEnabled(false);
				removeTaskBtn.addActionListener(new ActionListener() {
				
					public void actionPerformed(ActionEvent ae) {
						boolean canRemove = false;
						if (selectedTask().getProcesses().isEmpty()) {
							canRemove = true;
						} else {
							JOptionPane.showMessageDialog(projectsSection, "Can not remove non-empty task !",
									"Task Removal Failed", JOptionPane.ERROR_MESSAGE);
						}
						if(canRemove) {
							Task st = tasksModel.get(tasksList.getSelectedIndex());
							selectedProject().removeTask(st);
							tasksModel.remove(tasksList.getSelectedIndex());
							removeTaskBtn.setEnabled(false);
						}
					}
				});
				removeTaskBtn.setBounds(507, 148, 49, 22);
				projectsSection.add(removeTaskBtn);
				
////////////////////////////////////////)///////////////Process Related Logic/////////////////////////////////////////////////////////////
		
				
//1-----------------------------------------------------------New Process-----------------------------------------------------------------------------
		    
				newProcessButton = new JButton("New Process");
				newProcessButton.setEnabled(false);
				newProcessButton.setBounds(496, 201, 144, 29);
				newProcessButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent ae) {
						
						resourcesGroup.clearSelection();
						clearProcessesForm();
						processesList.setEnabled(false);
						ToggleProcessesArea(true);
						newProcessButton.setEnabled(false);
						cancelProcessButton.setEnabled(true);
						removeProcessButton.setEnabled(false);
					}
				});
				projectsSection.add(newProcessButton);

				cancelProcessButton = new JButton("Cancel");
				cancelProcessButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						clearProcessesForm();

						ToggleProcessesArea(false);
						if (tasksList.getSelectedIndex() > -1) {
							newProcessButton.setEnabled(true);
						} else {
							newProcessButton.setEnabled(true);
						}
						processesList.setEnabled(true);
						cancelProcessButton.setEnabled(false);
						if (processesList.getSelectedIndex() > -1) {
							Process selectedProcess = processesModel.get(processesList.getSelectedIndex());
							ToggleResourcesSection(true);
							processNameField.setEnabled(true);
							processDescArea.setEnabled(true);
							processNameField.setText(selectedProcess.getProcessName());
							processDescArea.setText(selectedProcess.getProcessDescription());
						}
					}
				});
				cancelProcessButton.setEnabled(false);
				cancelProcessButton.setBounds(317, 458, 98, 24);
				projectsSection.add(cancelProcessButton);
				
				
				
//2--------------------------------------------------------------Add Process-------------------------------------------------------------------------				
				addProcessButton = new JButton("+");
				addProcessButton.setEnabled(false);
				addProcessButton.setBounds(535, 461, 49, 21);
				addProcessButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						String processName = processNameField.getText();
						String processDesc = processDescArea.getText();
						if (processNameEmpty() || processDescEmpty()) {
							if (processNameEmpty()) {
								JOptionPane.showMessageDialog(projectsSection, "Please provide a name for the process !",
										"Process Creation Error", JOptionPane.ERROR_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(projectsSection,
										"Please provide a description for your process !", "Process Creation Error",
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							Process process = new Process(processName, "In Progress", processDesc);
							selectedTask().addProcess(process);
							processesModel.addElement(process);
							addProcessButton.setEnabled(false);
							clearProcessesForm();
							ToggleProcessesArea(false);
							processesList.setEnabled(true);
							if (processesList.getSelectedIndex() > -1) {
								ToggleResourcesSection(true);
								processNameField.setEnabled(true);
								processDescArea.setEnabled(true);
								processNameField.setText(selectedProcess().getProcessName());
								processDescArea.setText(selectedProcess().getProcessDescription());
							}
							if (tasksList.getSelectedIndex() < 0) {
								newProcessButton.setEnabled(false);
							} else {
								newProcessButton.setEnabled(true);
							}
							cancelProcessButton.setEnabled(false);
						}
					}
				});
				projectsSection.add(addProcessButton);

				

//3-----------------------------------------------------Remove Process-----------------------------------------------------------------------------------------				
			
				
				
				removeProcessButton = new JButton("-");
				removeProcessButton.setEnabled(false);
				removeProcessButton.addActionListener(new ActionListener(){
					  public void actionPerformed(ActionEvent ae) {
							if (selectedTask().removeProcess(selectedProcess())) {
								processesModel.remove(processesList.getSelectedIndex());
								removeProcessButton.setEnabled(false);
								resourcesComboModel.removeAllElements();
								resourcesBox.setEnabled(false);
								nbrOfUnits.setText("");
								nbrOfUnits.setEnabled(false);
								resourcesGroup.clearSelection();
								removeRescBtn.setEnabled(false);
								clearProcessesForm();
							} else {
								JOptionPane.showMessageDialog(projectsSection, "Can not remove process with assigned resources !",
										"Process Removal Failed", JOptionPane.ERROR_MESSAGE);
							}
					  }
				});
				removeProcessButton.setBounds(594, 460, 49, 21);
				projectsSection.add(removeProcessButton);
				
				
				
				
//4-----------------------------------------------------------Cancel Process-----------------------------------------------------------------------------
				
		cancelProjectButton = new JButton("Cancel");
		cancelProjectButton.setEnabled(false);
		cancelProjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newProjectButton.setEnabled(false);
				clearProjectForm();
				projectDescArea.setEnabled(false);
				projectNameField.setEnabled(false);
				newProjectButton.setEnabled(true);
				projectsComboBox.setEnabled(true);
				createProjectBtn.setEnabled(false);
				cancelProjectButton.setEnabled(false);

			}
		});
		cancelProjectButton.setBounds(133, 83, 108, 35);
		projectsSection.add(cancelProjectButton);
		
		
		
//5-------------------------------------------------------Select Process ----------------------------------------------------------------------------------------------

		processesModel = new DefaultListModel<Process>();
		processesList = new JList<Process>(processesModel);
		processesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
                     resourcesGroup.clearSelection();
				if (processesList.getSelectedIndex() < 0) {
					ToggleResourcesSection(false);
					clearResourcesSelection();
					updateProcessInfo.setEnabled(false);
					removeProcessButton.setEnabled(false);
				} else {
					ToggleResourcesSection(true);
					processNameField.setEnabled(true);
					processDescArea.setEnabled(true);
					processNameField.setText(selectedProcess().getProcessName());
					processDescArea.setText(selectedProcess().getProcessDescription());
					updateProcessInfo.setEnabled(true);
					removeProcessButton.setEnabled(true);
				}
			}
		});
		processesContainer.setViewportView(processesList);
		
    
//6---------------------------------------------------Update Process------------------------------------------------------------------------------------
                                
     
		updateProcessInfo = new JButton("Update Info");
		updateProcessInfo.setEnabled(false);
		updateProcessInfo.setBounds(317, 408, 98, 42);
		updateProcessInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String processName = processNameField.getText();
				String processDesc = processDescArea.getText();
				int ok = JOptionPane.showInternalConfirmDialog(null, "Proceed Updating Process Information ?",
						"Update Project Info", JOptionPane.YES_NO_OPTION);
				if (ok == 0) {
					if (processNameEmpty() || processDescEmpty()) {
						if (processNameEmpty()) {
							JOptionPane.showMessageDialog(projectsSection, "Please provide a name for your process !",
									"Process Update Error", JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(projectsSection,
									"Please provide a description for your process !", "Process Update Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						selectedProcess().setProcessName(processName);
						selectedProcess().setProcessDescription(processDesc);
						JOptionPane.showMessageDialog(projectsSection, "Process Info Updated Successfully!",
								"Update Success", JOptionPane.INFORMATION_MESSAGE);
						processesList.repaint();
					}
				}
			}
		});
		projectsSection.add(updateProcessInfo);
		

//////////////////////////////////////////////////////////////Resources Related Logic///////////////////////////////////////////////////

//-----------------------------------Resources Radio Buttons Grouping + Event Handling-------------------------------------------------------
		// HR Radio------------------------------------------------------------------------------------------
		hrRadioButton = new JRadioButton("Human");
		hrRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(hrRadioButton.isSelected() && hrRadioButton.isEnabled()) {
					resourcesList.setEnabled(true);
					resourcesListModel.clear();
					for(Iterator<HumanResource> hrIt = selectedProcess().getHumanResourses().iterator(); hrIt.hasNext();) {
						   resourcesListModel.addElement(hrIt.next());
					}
					manageRescButton.setEnabled(true);
					manageRescButton.setText("Manage Employees");
					chosenResourceLabel.setText("Employee:");
					chosenValueLabel.setText("Nbr Of Hours:");
					unitLabel.setText("(Hours)");
					resourcesBox.setEnabled(true);
					nbrOfUnits.setEnabled(true);
					addResBtn.setEnabled(true);
					
					resourcesComboModel.removeAllElements();
					resourcesComboModel.addElement(new Employee("Employee", new Job("Job", 0)));
					for(Iterator<Employee> resIt = statistics.getEmployees().iterator(); resIt.hasNext();) {
						   resourcesComboModel.addElement(resIt.next());
						   resourcesBox.repaint();
					}
					resourcesBox.repaint();
				}
				else {
					resourcesListModel.clear();
					resourcesList.setEnabled(false);
					resourcesComboModel.removeAllElements();
					addResBtn.setEnabled(false);
					resourcesBox.setEnabled(false);
					nbrOfUnits.setEnabled(false);
					manageRescButton.setText("Manage Resources");
					resetResourcesLabels();
					manageRescButton.setEnabled(false);
				}
			}
		});
		hrRadioButton.setEnabled(false);
		hrRadioButton.setBackground(SystemColor.activeCaption);
		hrRadioButton.setBounds(700, 65, 65, 23);
		projectsSection.add(hrRadioButton);

		// Mat-Misc Radio----------------------------------------------------------------------
		materialRescRadio = new JRadioButton("Material(Raw/Misc)");
		materialRescRadio.setEnabled(false);
		materialRescRadio.setBackground(SystemColor.activeCaption);
		materialRescRadio.setBounds(783, 65, 155, 23);
		materialRescRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(materialRescRadio.isSelected() && materialRescRadio.isEnabled()) {
					resourcesList.setEnabled(true);
					resourcesListModel.clear();
					for(Iterator<MaterialResource> hrIt = selectedProcess().getMaterialResourses().iterator(); hrIt.hasNext();) {
						   resourcesListModel.addElement(hrIt.next());
					}
					manageRescButton.setEnabled(true);
					manageRescButton.setText("Raw/Misc Materials");
					chosenResourceLabel.setText("Material:");
					chosenValueLabel.setText("Nbr Of Units:");
					unitLabel.setText("(Units)");
					resourcesBox.setEnabled(true);
					nbrOfUnits.setEnabled(true);
					addResBtn.setEnabled(true);
					resourcesComboModel.removeAllElements();
					resourcesComboModel.addElement(new Material('x', 0, "Choose Material"));
					for(Iterator<Material> matIt = statistics.getMaterials().iterator(); matIt.hasNext();) {
						   resourcesComboModel.addElement(matIt.next());
						   resourcesBox.repaint();
					}
					resourcesBox.repaint();
				}
				else {
					resourcesListModel.clear();
					resourcesList.setEnabled(false);
					resourcesComboModel.removeAllElements();
					resourcesBox.setEnabled(false);
					addResBtn.setEnabled(false);
					nbrOfUnits.setEnabled(false);
					manageRescButton.setText("Manage Resources");
					resetResourcesLabels();
					manageRescButton.setEnabled(false);
				}
			}
		});
		projectsSection.add(materialRescRadio);

		resourcesGroup = new ButtonGroup();
		resourcesGroup.add(hrRadioButton);
		resourcesGroup.add(materialRescRadio);
		
		resourcesComboModel = new DefaultComboBoxModel<Object>();
		
		
		resourcesBox = new JComboBox<Object>(resourcesComboModel);
		resourcesBox.setEnabled(false);
		resourcesBox.setBounds(702, 323, 272, 22);
		projectsSection.add(resourcesBox);
//---------------------------------------------------Handle Update A Resource------------------------------------------------------------------------------------
		updateResource = new JButton("Update Info");
		updateResource.setEnabled(false);
		updateResource.setBounds(695, 440, 98, 42);
		updateResource.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent ae) {
				   int ok = JOptionPane.showInternalConfirmDialog(null, "Proceed Updating Resource Information ?",
							"Update Resource", JOptionPane.YES_NO_OPTION);
					if (ok == 0) {
						if (resourcesBox.getSelectedIndex() == 0 || nbrOfUnits.getText().equals("") || !isDouble(nbrOfUnits.getText())) {
							if (resourcesBox.getSelectedIndex() == 0 ) {
								if(hrRadioButton.isSelected()) {
									JOptionPane.showMessageDialog(projectsSection, "Please choose an employee for the resource !",
											"Resource Update Error", JOptionPane.ERROR_MESSAGE);
								}
								else if(materialRescRadio.isSelected()) {
									JOptionPane.showMessageDialog(projectsSection, "Please choose a material for the resource !",
											"Resource Update Error", JOptionPane.ERROR_MESSAGE);
								}
								
							} else {
								if(hrRadioButton.isSelected()) {
									JOptionPane.showMessageDialog(projectsSection, "Please set a valid work hours value for the resource !",
											"Resource Update Error", JOptionPane.ERROR_MESSAGE);
								}
								else if(materialRescRadio.isSelected()) {
									JOptionPane.showMessageDialog(projectsSection, "Please set a valid nbr of units value for the resource !",
											"Resource Update Error", JOptionPane.ERROR_MESSAGE);
								}
							}
						} else {
							if(hrRadioButton.isSelected()) {
								 if(isDouble(nbrOfUnits.getText())) {
									 HumanResource sr = ((HumanResource) selectedResource());
									 double wh = Double.parseDouble(nbrOfUnits.getText());
									 Employee emp = (Employee) selectedResFromBox();
							         sr.setEmployee(emp);
							         sr.setWorkHours(wh);
								     JOptionPane.showMessageDialog(projectsSection, "Resource Info Updated Successfully!",
													"Update Success", JOptionPane.INFORMATION_MESSAGE);
								         resourcesList.repaint();
							         
				
								 }
								 else {
									 JOptionPane.showMessageDialog(projectsSection, "Please provide a valid nbr of hours value !",
												"Update Error", JOptionPane.ERROR_MESSAGE);
								 }
						        
							}
							else if(materialRescRadio.isSelected()) {
								 if(isInt(nbrOfUnits.getText())) {
									 MaterialResource mr = ((MaterialResource) selectedResource());
									 int nbu = Integer.parseInt(nbrOfUnits.getText());
									 Material mat = (Material) selectedResFromBox();
									
							        	 mr.setMaterial(mat);
							        	 mr.setUnitsUsed(nbu);
								         JOptionPane.showMessageDialog(projectsSection, "Resource Info Updated Successfully!",
													"Update Success", JOptionPane.INFORMATION_MESSAGE);
								         resourcesList.repaint();
							         
								 }
								 else {
									 JOptionPane.showMessageDialog(projectsSection, "Please provide a valid nbr of units value !",
												"Update Error", JOptionPane.ERROR_MESSAGE);
								 }
							}
							
						
						}
					}
			   }
		});
			  
		
		projectsSection.add(updateResource);

//------------------------------------------------Handle Resource Removal------------------------------------------------
		
		removeRescBtn = new JButton("-");
		removeRescBtn.setEnabled(false);
		removeRescBtn.setBounds(838, 458, 49, 21);
		removeRescBtn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent ae) {
				    if(hrRadioButton.isSelected()) {
				    	 selectedProcess().removeHumanResource(((HumanResource) resourcesListModel.getElementAt(resourcesList.getSelectedIndex())));
				    }
				    else  if(materialRescRadio.isSelected()) {
				    	 selectedProcess().removeMaterialResource(((MaterialResource) resourcesListModel.getElementAt(resourcesList.getSelectedIndex())));
				    }
				    resourcesListModel.remove(resourcesList.getSelectedIndex());
				    resourcesList.repaint();
				    
			 }
			 
		});
		projectsSection.add(removeRescBtn);
        
		
		
		
//---------------------------------------------------Handle Adding A Resource to the selected Process------------------------------------------------------------
		addResBtn = new JButton("+");
		addResBtn.setEnabled(false);
		addResBtn.setBounds(897, 459, 49, 20);
		addResBtn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent ae) {
				    
				   //Case Of Adding A Human Resource
				   if(hrRadioButton.isSelected()) {
					   Employee selectedEmployee = (Employee) selectedResFromBox();
					   System.out.println(selectedEmployee.getEmployeeId());
					   int ok = JOptionPane.showInternalConfirmDialog(null, "Add New Resource ?: ("+selectedEmployee.getEmployeeName()+" , "+selectedEmployee.getJob().getJobName()+" , "+nbrOfUnits.getText()+" )",
								"Add Resource", JOptionPane.YES_NO_OPTION);
						if (ok == 0) {
							if (resourcesBox.getSelectedIndex() == 0 || nbrOfUnits.getText().equals("") || nbrOfUnits.getText().equals("0") || nbrOfUnits.getText().equals("0.0")) {
								if (resourcesBox.getSelectedIndex() == 0) {
									JOptionPane.showMessageDialog(projectsSection, "Please select an employee !",
											"Resource Adding Error", JOptionPane.ERROR_MESSAGE);
								} else if(nbrOfUnits.getText().equals("") || nbrOfUnits.getText().equals("0") || nbrOfUnits.getText().equals("0.0")){
									JOptionPane.showMessageDialog(projectsSection,
											"Please provide a valid number of hours for the chosen employee!", "Resource Adding Error",
											JOptionPane.ERROR_MESSAGE);
								}
							} else {
								if(isDouble(nbrOfUnits.getText())) {
									double workHours = Double.parseDouble(nbrOfUnits.getText());
									HumanResource hr = new HumanResource('h' , selectedEmployee , workHours);
									if(selectedProcess().addHumanResource(hr)) {
										resourcesListModel.addElement(hr);
										resourcesList.repaint();
										JOptionPane.showMessageDialog(projectsSection, "Resource added successfully!",
												"Resource Adding Success", JOptionPane.INFORMATION_MESSAGE);
										resourcesBox.setSelectedIndex(0);
										nbrOfUnits.setText("");
										processesList.repaint();
									}
									else {
										JOptionPane.showMessageDialog(projectsSection, "Resource already in use !",
												"Resource Adding Error", JOptionPane.ERROR_MESSAGE);
									}
									 
									
								}
								else {
									JOptionPane.showMessageDialog(projectsSection, "Please set a valid (double) work hours value !",
											"Resource Adding Error", JOptionPane.ERROR_MESSAGE);
								}
								
							}
							
							
						}
						
				   }
				   //Case Of Adding A Material/Misc Resource
				   else if(materialRescRadio.isSelected()){
					   Material selectedMaterial = (Material) selectedResFromBox();
					   int ok = JOptionPane.showInternalConfirmDialog(null, "Add New Resource ?: ("+selectedMaterial.getMaterialName()+" , "+selectedMaterial.getCostPerunit()+" , "+nbrOfUnits.getText()+" )",
								"Add Resource", JOptionPane.YES_NO_OPTION);
					   if (ok == 0) {
							if (resourcesBox.getSelectedIndex() == 0 || nbrOfUnits.getText().equals("") ||  nbrOfUnits.getText().equals("0") ||  nbrOfUnits.getText().equals("0.0") ||!isInt(nbrOfUnits.getText())) {
								if (resourcesBox.getSelectedIndex() == 0) {
									JOptionPane.showMessageDialog(projectsSection, "Please select a material !",
											"Resource Adding Error", JOptionPane.ERROR_MESSAGE);
								} else if(nbrOfUnits.getText().equals("") ||  nbrOfUnits.getText().equals("0") ||  nbrOfUnits.getText().equals("0.0") ||!isInt(nbrOfUnits.getText())){
									JOptionPane.showMessageDialog(projectsSection,
											"Please provide a valid nbr of ubits for the chosen material!", "Resource Adding Error",
											JOptionPane.ERROR_MESSAGE);
								}
							}
							else {
								if(isInt(nbrOfUnits.getText())) {
									int nbrUnits = Integer.parseInt(nbrOfUnits.getText());
									MaterialResource mr = new MaterialResource('m', nbrUnits , selectedMaterial);
									if(selectedProcess().addMaterialResource(mr)) {
										resourcesListModel.addElement(mr);
										resourcesList.repaint();
										JOptionPane.showMessageDialog(projectsSection, "Resource added successfully!",
												"Resource Adding Success", JOptionPane.INFORMATION_MESSAGE);
										resourcesBox.setSelectedIndex(0);
										nbrOfUnits.setText("");
										processesList.repaint();
									}
									else {
										JOptionPane.showMessageDialog(projectsSection, "Resource already in use !",
												"Resource Adding Error", JOptionPane.ERROR_MESSAGE);
									}
									 
									
								}
								else {
									JOptionPane.showMessageDialog(projectsSection, "Please set a valid (integer) nbr of units value !",
											"Resource Adding Error", JOptionPane.ERROR_MESSAGE);
								}
								
							}
					   
				   }
					   
					   
			    }
			 }
		});
		projectsSection.add(addResBtn);

		
        resourcesListModel = new DefaultListModel<Resource>();
		resourcesList = new JList<Resource>(resourcesListModel); 
		resourcesList.setEnabled(false);
		resourcesContainer.setViewportView(resourcesList);
		
		
//--------------------------------------------------Handle Resource Selection-------------------------------------------------------
	     
		  resourcesList.addListSelectionListener(new ListSelectionListener() {
			     public void valueChanged(ListSelectionEvent lse) {
			    	    if(resourcesList.getSelectedIndex()  < 0) {
			    	    	 updateResource.setEnabled(false);
			    	    	 removeRescBtn.setEnabled(false);
			    	    	 nbrOfUnits.setText("");
			    	    }
			    	    else {
			    	    	 updateResource.setEnabled(true);
			    	    	 removeRescBtn.setEnabled(true);
			    	    	 if(hrRadioButton.isSelected()) {
			    	    		  resourcesComboModel.setSelectedItem(((HumanResource) selectedResource()).getEmployee());
			    	    		  nbrOfUnits.setText(((HumanResource) selectedResource()).getWorkHours() + "");
			    	    	 }
			    	    	 else if(materialRescRadio.isSelected()) {
			    	    		  resourcesComboModel.setSelectedItem(((MaterialResource) selectedResource()).getMaterial());
			    	    		  nbrOfUnits.setText(((MaterialResource) selectedResource()).getUnitsUsed() + "");
			    	    	 }
			    	    }
			     }
		  });
		
		
				
//-------------------------------------Handle Resources Entry Selection Based On Radio Buttons---------------------------------------------
				
		
		        manageRescButton = new JButton("Manage Resource");
				manageRescButton.setBounds(733, 10, 202, 35);
				mainPanel.add(manageRescButton);
				manageRescButton.setEnabled(false);
				manageRescButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						  if(manageRescButton.getText().equals("Manage Employees")) {
							  setVisible(false);
							  employeesJobsEntry.setVisible(true);
						  }
						  else if(manageRescButton.getText().equals("Raw/Misc Materials")) {
							  setVisible(false);
							  materialsEntry.setVisible(true);
						  }
					}
				});
	}
}
