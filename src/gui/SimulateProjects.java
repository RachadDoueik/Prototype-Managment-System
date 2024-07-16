package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import classes.Statistics;
import classes.Project;
import classes.Task;
import helpers.MyObserver;
import classes.Employee;
import classes.HumanResource;
import classes.Material;
import classes.MaterialResource;
import classes.Process;
import javax.swing.JScrollPane;
import views.ProjectsView1;
import views.TasksView;
import views.ProcessesView;

public class SimulateProjects extends JFrame implements MyObserver {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JLabel empsCostLbl;
	private TasksView tasksList;
	private ProcessesView processesList;
	private JLabel processesLabel;
	private JLabel simulationStatesLabel;
	private JButton simulateTaskBtn;
	private JButton simulateProcessBtn;
	private JLabel employeesUsageLabel;
	private JLabel rawMatUsageLbl;
	private JLabel miscMatUsageLbl;
	private JLabel timeLbl;
	private JLabel empsUsageVal;
	private JLabel rawUsageVal;
	private JButton resetBtn;
	private JPanel topPanel;
	private JLabel mcostVal;
	private JLabel rcostVal;
	private JLabel ecostVal;
	private JLabel finalCostLbl;
	private JButton simulateProjectBtn;
	private ProjectsView1 projectsCombo;
	private JLabel projetcsLabel;
	private JLabel tasksLabel;
	private JLabel miscCostLbl;
	private JLabel rawCostsLbl;
	private JLabel timeVal;
	private JLabel miscUsageVal;
	private JScrollPane processesListContainer;
	private DefaultComboBoxModel<Project> projectsComboModel;
	private DefaultListModel<Task> tasksListModel;
	private DefaultListModel<Process> processesListModel;
	private JLabel finalCostVal;
	private int empsCount;
	private int rawMatsCount;
	private int miscMatCount;
	private double time;
	private double finalCost;
	private double empsCost;
	private double miscCost;
	private double rawCost;
	private JLabel projectCostLbl;
	private JLabel projectCostVal;
	private JLabel lblProjectDuration ;
	private JLabel finalProjectDuration;
	

//------------------------------------------------------------Initial Frame Setup------------------------------------------------------------------------
	public SimulateProjects(Statistics statistics) {
		setTitle("OMEGA's Prototype Management System: Simulate Project");
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
		this.empsCount = 0;
		this.rawMatsCount = 0;
		this.miscMatCount = 0;
		this.time = 0;
		this.finalCost = 0;
		this.empsCost = 0;
		this.miscCost = 0;
		this.rawCost = 0;
		statistics.addObserver(this);
		for(Iterator<Project> pit = statistics.getProjects().iterator(); pit.hasNext();) {
			  pit.next().addObserver(this);
		}

//------------------------------------------------Handle Project Selection------------------------------------------------------------------------------------
		projectsComboModel = new DefaultComboBoxModel<Project>();
		projectsCombo = new ProjectsView1(projectsComboModel, statistics);
		projectsCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				tasksList.setProject(selectedProject());
				processesList.setTasks(selectedProject());
				processesList.updateProcessesList();
				if (selectedProject() != null) {
					if (selectedProject().getProjectName().equals("Choose Prototype")) {
						simulateProjectBtn.setEnabled(false);
						resetBtn.setEnabled(false);
					} else {
						if (countProjectEmployees(selectedProject()) > 0) {
							simulateProjectBtn.setEnabled(true);
						} else {
							simulateProjectBtn.setEnabled(false);
						}

						resetBtn.setEnabled(true);
					}

				}

			}

		});
//---------------------------------------------Handle Task Selection-----------------------------------------------------------------------------------------------
		tasksListModel = new DefaultListModel<Task>();
		tasksList = new TasksView(tasksListModel, selectedProject());
		tasksList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				processesList.clearSelection();
				if (tasksList.getSelectedIndex() <= -1) {
					simulateTaskBtn.setEnabled(false);
				} else {
					simulateTaskBtn.setEnabled(true);

				}
			}
		});
		tasksList.setBounds(291, 111, 250, 143);
		mainPanel.add(tasksList);

		projectsCombo.setBounds(33, 111, 194, 22);
		mainPanel.add(projectsCombo);

		processesListModel = new DefaultListModel<Process>();
		processesLabel = new JLabel("Processes:");
		processesLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		processesLabel.setBounds(590, 84, 188, 14);
		mainPanel.add(processesLabel);

		simulationStatesLabel = new JLabel("Simulation States:");
		simulationStatesLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		simulationStatesLabel.setForeground(Color.RED);
		simulationStatesLabel.setBounds(33, 349, 119, 22);
		mainPanel.add(simulationStatesLabel);
//--------------------------------------------------Handle Single Task Simulation------------------------------------------
		simulateTaskBtn = new JButton("Simulate Task");
		simulateTaskBtn.setEnabled(false);
		simulateTaskBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedTaskIndex = tasksList.getSelectedIndex();
		        if (selectedTaskIndex >= 0 && selectedTaskIndex < tasksListModel.getSize()) {
		            Task selectedTask = tasksListModel.getElementAt(selectedTaskIndex);
		            if (selectedTask != null) {
		            	  resetProjectStats();
		  		        selectedTask.simulateTask();
		  		        simulateProcessBtn.setVisible(false);
		  		        selectedProject().setProjectCost(selectedProject().getProjectCost() + selectedTask.calculateTaskCost());
		  		        selectedProject().setProjectDuration(selectedProject().getProjectDuration() + selectedTask.calculateTaskDuration());
		  		        empsCount = empsCount + countTaskEmployees(selectedTask);
		  		        rawMatsCount = rawMatsCount + countTaskRawMats(selectedTask);
		  		        miscMatCount = miscMatCount + countTaskMiscMats(selectedTask);
		  		        time = time + selectedTask.calculateTaskDuration();
		  		        empsCost = empsCost + taskEmployeesCost(selectedTask);
		  		        miscCost = miscCost + taskMiscCost(selectedTask);
		  		        rawCost = rawCost + taskRawCost(selectedTask);
		  		        finalCost = finalCost + selectedTask.calculateTaskCost();
		  		        empsUsageVal.setText(empsCount + "");
		  		        rawUsageVal.setText(rawMatsCount + "");
		  		        miscUsageVal.setText(miscMatCount + "");
		  		        timeVal.setText(time + " Hours");
		  		        ecostVal.setText(empsCost + "$");
		  		        mcostVal.setText(miscCost + "$");
		  		        rcostVal.setText(rawCost + "$");
		  		        finalCostVal.setText(finalCost + "$");
		  		        tasksList.update();
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "No valid task selected.");
		        }
		    }
		});
		
		simulateTaskBtn.setBounds(351, 265, 138, 39);
		mainPanel.add(simulateTaskBtn);
//-----------------------------------------------Handle Single Process Simulation-------------------------------------
		simulateProcessBtn = new JButton("Simulate Process");
		simulateProcessBtn.setEnabled(false);
		simulateProcessBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedTaskIndex = tasksList.getSelectedIndex();
		        if (selectedTaskIndex >= 0 && selectedTaskIndex < tasksListModel.getSize()) {
		            Task selectedTask = tasksListModel.getElementAt(selectedTaskIndex);
		            if (selectedTask != null) {
		                int selectedProcessIndex = processesList.getSelectedIndex();
		                if (selectedProcessIndex >= 0 && selectedProcessIndex < processesListModel.getSize()) {
		                    Process selectedProcess = processesListModel.getElementAt(selectedProcessIndex);
		                    if (selectedProcess != null) {
		                    	resetProjectStats();
		        		        selectedProcess.simulateProcess();
		        		        simulateTaskBtn.setVisible(false);
		        		        selectedProject().setProjectCost(selectedProject().getProjectCost() + selectedProcess.calculateProcessCost());
		        		        selectedProject().setProjectDuration(selectedProject().getProjectDuration() + selectedProcess.calculateProcessDuration());
		        		        empsCount = empsCount + countProcessEmployees(selectedProcess);
		        		        rawMatsCount = rawMatsCount + countProcessRawMats(selectedProcess);
		        		        miscMatCount = miscMatCount + countProcessMiscMats(selectedProcess);
		        		        time = time + selectedProcess.calculateProcessDuration();
		        		        empsCost = empsCost + processEmployeesCost(selectedProcess);
		        		        miscCost = miscCost + processMiscCost(selectedProcess);
		        		        rawCost = rawCost + processRawCost(selectedProcess);
		        		        finalCost = finalCost + selectedProcess.calculateProcessCost();
		        		        empsUsageVal.setText(empsCount + "");
		        		        rawUsageVal.setText(rawMatsCount + "");
		        		        miscUsageVal.setText(miscMatCount + "");
		        		        timeVal.setText(time + " Hours");
		        		        ecostVal.setText(empsCost + "$");
		        		        mcostVal.setText(miscCost + "$");
		        		        rcostVal.setText(rawCost + "$");
		        		        finalCostVal.setText(finalCost + "$");
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "No valid process selected.");
		                }
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "No valid task selected.");
		        }
		    }
		});
		simulateProcessBtn.setBounds(723, 359, 138, 39);
		mainPanel.add(simulateProcessBtn);

		employeesUsageLabel = new JLabel("Employees Usage");
		employeesUsageLabel.setForeground(new Color(139, 0, 0));
		employeesUsageLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
		employeesUsageLabel.setBounds(40, 409, 160, 22);
		mainPanel.add(employeesUsageLabel);

		rawMatUsageLbl = new JLabel("Raw Materials Usage");
		rawMatUsageLbl.setForeground(new Color(139, 0, 0));
		rawMatUsageLbl.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
		rawMatUsageLbl.setBounds(246, 409, 160, 22);
		mainPanel.add(rawMatUsageLbl);

		miscMatUsageLbl = new JLabel("Miscellaneous Material Usage");
		miscMatUsageLbl.setForeground(new Color(139, 0, 0));
		miscMatUsageLbl.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
		miscMatUsageLbl.setBounds(461, 409, 240, 22);
		mainPanel.add(miscMatUsageLbl);

		timeLbl = new JLabel("Duration:");
		timeLbl.setForeground(new Color(0, 0, 128));
		timeLbl.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
		timeLbl.setBounds(846, 409, 138, 22);
		mainPanel.add(timeLbl);

		empsUsageVal = new JLabel(empsCount + "");
		empsUsageVal.setHorizontalAlignment(SwingConstants.CENTER);
		empsUsageVal.setFont(new Font("Tahoma", Font.BOLD, 24));
		empsUsageVal.setBounds(50, 452, 110, 51);
		mainPanel.add(empsUsageVal);

		rawUsageVal = new JLabel(rawMatsCount + "");
		rawUsageVal.setHorizontalAlignment(SwingConstants.CENTER);
		rawUsageVal.setFont(new Font("Tahoma", Font.BOLD, 24));
		rawUsageVal.setBounds(269, 452, 110, 51);
		mainPanel.add(rawUsageVal);

		miscUsageVal = new JLabel(miscMatCount + "");
		miscUsageVal.setHorizontalAlignment(SwingConstants.CENTER);
		miscUsageVal.setFont(new Font("Tahoma", Font.BOLD, 24));
		miscUsageVal.setBounds(513, 452, 110, 51);
		mainPanel.add(miscUsageVal);

		timeVal = new JLabel(time + "");
		timeVal.setFont(new Font("Tahoma", Font.BOLD, 15));
		timeVal.setBounds(846, 456, 138, 51);
		mainPanel.add(timeVal);

		empsCostLbl = new JLabel("COST:");
		empsCostLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		empsCostLbl.setBounds(40, 515, 58, 14);
		mainPanel.add(empsCostLbl);

		rawCostsLbl = new JLabel("COST:");
		rawCostsLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		rawCostsLbl.setBounds(246, 515, 58, 14);
		mainPanel.add(rawCostsLbl);

		miscCostLbl = new JLabel("COST:");
		miscCostLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		miscCostLbl.setBounds(462, 515, 58, 14);
		mainPanel.add(miscCostLbl);

		tasksLabel = new JLabel("Tasks:");
		tasksLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		tasksLabel.setBounds(294, 84, 188, 14);
		mainPanel.add(tasksLabel);

		projetcsLabel = new JLabel("Choose a prototype to simulate:");
		projetcsLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		projetcsLabel.setBounds(33, 85, 200, 14);
		mainPanel.add(projetcsLabel);

		finalCostLbl = new JLabel("Cost:");
		finalCostLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		finalCostLbl.setForeground(new Color(0, 0, 128));
		finalCostLbl.setBounds(723, 409, 101, 30);
		mainPanel.add(finalCostLbl);

		ecostVal = new JLabel(empsCost + "$");
		ecostVal.setHorizontalAlignment(SwingConstants.CENTER);
		ecostVal.setFont(new Font("Tahoma", Font.BOLD, 12));
		ecostVal.setForeground(new Color(0, 128, 0));
		ecostVal.setBounds(93, 516, 79, 14);
		mainPanel.add(ecostVal);

		rcostVal = new JLabel(rawCost + "$");
		rcostVal.setHorizontalAlignment(SwingConstants.CENTER);
		rcostVal.setForeground(new Color(0, 128, 0));
		rcostVal.setFont(new Font("Tahoma", Font.BOLD, 12));
		rcostVal.setBounds(314, 515, 79, 14);
		mainPanel.add(rcostVal);

		mcostVal = new JLabel(miscCost + "$");
		mcostVal.setHorizontalAlignment(SwingConstants.CENTER);
		mcostVal.setForeground(new Color(0, 128, 0));
		mcostVal.setFont(new Font("Tahoma", Font.BOLD, 12));
		mcostVal.setBounds(551, 515, 79, 14);
		mainPanel.add(mcostVal);

//------------------------------------------------Handle Project Simulation Button---------------------------------------------------------
		simulateProjectBtn = new JButton("Start Simulation");
		simulateProjectBtn.setEnabled(false);
		simulateProjectBtn.setBounds(33, 159, 139, 39);
		simulateProjectBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int ok = JOptionPane.showConfirmDialog(null, "Start Project Simulation ?", "Simulation Start",
						JOptionPane.YES_NO_OPTION);
				if (ok == 0) {
					if (empsCount > 0) {
						JOptionPane.showMessageDialog(null,
								"Simulation Already Done Or In Progress! Reset Stats And Try Again", "Simulation Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						resetProjectStats();
						selectedProject().simulateProject();
						simulateTaskBtn.setVisible(false);
						simulateProcessBtn.setVisible(false);
						selectedProject().setProjectCost(selectedProject().calculateProjectCost());
						selectedProject().setProjectDuration(selectedProject().calculateProjectDuration());
						empsCount = countProjectEmployees(selectedProject());
						empsUsageVal.setText(empsCount + "");
						rawMatsCount = countProjectRawMats(selectedProject());
						miscMatCount = countProjectMiscMats(selectedProject());
						rawUsageVal.setText(rawMatsCount + "");
						miscUsageVal.setText(miscMatCount + "");
						time = selectedProject().calculateProjectDuration();
						timeVal.setText(time + " Hours");
						empsCost = projectEmployeesCost(selectedProject());
						rawCost = projectRawCost(selectedProject());
						miscCost = projectMiscCost(selectedProject());
						ecostVal.setText(empsCost + "$");
						mcostVal.setText(miscCost + "$");
						rcostVal.setText(rawCost + "$");
						finalCost = selectedProject().calculateProjectCost();
						finalCostVal.setText(finalCost + "$");
						tasksList.update();
						projectsCombo.update();
						JOptionPane.showMessageDialog(null, "Simulation Complete !", "Simulation State",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		});
		mainPanel.add(simulateProjectBtn);

		topPanel = new JPanel();
		topPanel.setBounds(0, 0, 984, 51);
		mainPanel.add(topPanel);
		topPanel.setLayout(null);

//---------------------------------------------------------Handle Reset------------------------------------------------------------------
		resetBtn = new JButton("Reset");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (countProjectEmployees(selectedProject()) == 0 && (countProjectMiscMats(selectedProject()) == 0
						|| countProjectRawMats(selectedProject()) == 0)) {
					JOptionPane.showMessageDialog(null, "Cannot Reset Non Simulated Project!", "Simulation Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (selectedProject().getTasks().size() != 0) {
					selectedProject().resetProjectSimulation();
					selectedProject().setProjectCost(0);
					selectedProject().setProjectDuration(0);
					resetProjectStats();
					tasksList.update();
					processesList.update();
					simulateTaskBtn.setVisible(true);
					simulateProcessBtn.setVisible(true);

				}
			}
		});
		resetBtn.setEnabled(false);
		resetBtn.setBounds(31, 11, 115, 29);
		topPanel.add(resetBtn);

		processesListContainer = new JScrollPane();
		processesListContainer.setBounds(590, 108, 384, 236);
		mainPanel.add(processesListContainer);
		processesListModel = new DefaultListModel<Process>();
		processesList = new ProcessesView(processesListModel, selectedProject());
		processesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (processesList.getSelectedIndex() > -1) {
					simulateProcessBtn.setEnabled(true);
				} else {
					simulateProcessBtn.setEnabled(false);
				}
			}
		});
		processesListContainer.setViewportView(processesList);

		finalCostVal = new JLabel("0");
		finalCostVal.setForeground(new Color(0, 0, 0));
		finalCostVal.setHorizontalAlignment(SwingConstants.LEFT);
		finalCostVal.setFont(new Font("Tahoma", Font.BOLD, 15));
		finalCostVal.setBounds(723, 456, 98, 51);
		mainPanel.add(finalCostVal);
		
		projectCostLbl = new JLabel("Project Cost:");
		projectCostLbl.setForeground(new Color(255, 0, 0));
		projectCostLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		projectCostLbl.setBounds(26, 234, 101, 30);
		mainPanel.add(projectCostLbl);
		
		projectCostVal = new JLabel("0");
		projectCostVal.setHorizontalAlignment(SwingConstants.LEFT);
		projectCostVal.setForeground(Color.BLACK);
		projectCostVal.setFont(new Font("Tahoma", Font.BOLD, 15));
		projectCostVal.setBounds(26, 265, 98, 51);
		mainPanel.add(projectCostVal);
		
		lblProjectDuration = new JLabel("Project Duration");
		lblProjectDuration.setForeground(new Color(255, 0, 0));
		lblProjectDuration.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
		lblProjectDuration.setBounds(143, 238, 138, 22);
		mainPanel.add(lblProjectDuration);
		
		finalProjectDuration = new JLabel("0");
		finalProjectDuration.setFont(new Font("Tahoma", Font.BOLD, 15));
		finalProjectDuration.setBounds(143, 265, 138, 51);
		mainPanel.add(finalProjectDuration);

	}

	// --------------------------------------------------Selection Shortcut and Utility Methods------------------------------------------------------
	public Project selectedProject() {
		return (Project) projectsComboModel.getSelectedItem();
	}

	public Task selectedTask() {

		return tasksListModel.get(tasksList.getSelectedIndex());
	}
	
	public Process selectedProcess() {
		return processesListModel.get(processesList.getSelectedIndex());
	}

	// -------------------------------------Project Related Logic---------------------------------------------------
	public void resetProjectStats() {
		this.miscMatCount = 0;
		this.empsCount = 0;
		this.rawMatsCount = 0;
		this.miscMatCount = 0;
		this.time = 0;
		this.finalCost = 0;
		this.empsCost = 0;
		this.miscCost = 0;
		this.rawCost = 0;
		empsUsageVal.setText(empsCount + "");
		rawUsageVal.setText(rawMatsCount + "");
		miscUsageVal.setText(miscMatCount + "");
		timeVal.setText(time + " Hours");
		ecostVal.setText(empsCost + "$");
		mcostVal.setText(miscCost + "$");
		rcostVal.setText(rawCost + "$");
		finalCostVal.setText(finalCost + "$");
	}

	public int countProjectEmployees(Project p) {
		HashSet<Employee> emps = new HashSet<Employee>();
		for (Map.Entry<Character, Task> tEntry : p.getTasks().entrySet()) {
			Task task = tEntry.getValue();
			if (task != null) {
				for (Process process : task.getProcesses()) {
					if (process != null) {
						for (HumanResource hr : process.getHumanResourses()) {
							if (hr != null) {
								if (!emps.contains(hr.getEmployee())) {
									emps.add(hr.getEmployee());
								}
							}

						}
					}
				}
			}

		}
		return emps.size();
	}

	public double projectEmployeesCost(Project p) {
		double cost = 0;
		for (Map.Entry<Character, Task> tEntry : p.getTasks().entrySet()) {
			Task task = tEntry.getValue();
			if (task != null) {
				for (Process process : task.getProcesses()) {
					if (process != null) {
						for (HumanResource hr : process.getHumanResourses()) {

							if (hr != null) {
								cost += hr.getResourceCost();
							}

						}
					}

				}
			}

		}
		return cost;
	}

	public double projectRawCost(Project p) {
		int cost = 0;
		for (Map.Entry<Character, Task> tEntry : p.getTasks().entrySet()) {
			Task task = tEntry.getValue();
			if (task != null) {
				for (Process process : task.getProcesses()) {
					if (process != null) {
						for (MaterialResource mr : process.getMaterialResourses()) {

							if (mr.getMaterial().getMaterialType() == 'r') {
								if (mr != null) {
									cost += mr.getResourceCost();
								}

							}

						}
					}

				}
			}

		}
		return cost;
	}

	public double projectMiscCost(Project p) {
		int cost = 0;
		for (Map.Entry<Character, Task> tEntry : p.getTasks().entrySet()) {
			Task task = tEntry.getValue();
			if (task != null) {
				for (Process process : task.getProcesses()) {
					if (process != null) {
						for (MaterialResource mr : process.getMaterialResourses()) {

							if (mr.getMaterial().getMaterialType() == 'm') {
								if (mr != null) {
									cost += mr.getResourceCost();
								}

							}

						}
					}

				}
			}

		}
		return cost;
	}

	public double projectFinalCost(Project p) {
		return projectEmployeesCost(p) + projectMiscCost(p) + projectRawCost(p);
	}

	public int countProjectRawMats(Project p) {
		HashSet<Material> mats = new HashSet<Material>();
		for (Map.Entry<Character, Task> tEntry : p.getTasks().entrySet()) {
			Task task = tEntry.getValue();
			if (task != null) {
				for (Process process : task.getProcesses()) {
					if (process != null) {
						for (MaterialResource mr : process.getMaterialResourses()) {
							if (mr != null) {
								if (mr.getMaterial().getMaterialType() == 'r') {
									if (!mats.contains(mr.getMaterial())) {
										mats.add(mr.getMaterial());
									}
								}
							}

						}
					}
				}
			}

		}
		return mats.size();
	}

	public int countProjectMiscMats(Project p) {
		HashSet<Material> mats = new HashSet<Material>();
		for (Map.Entry<Character, Task> tEntry : p.getTasks().entrySet()) {
			Task task = tEntry.getValue();
			if (task != null) {
				for (Process process : task.getProcesses()) {
					if (process != null) {
						for (MaterialResource mr : process.getMaterialResourses()) {
							if (mr != null) {
								if (mr.getMaterial().getMaterialType() == 'm') {
									if (!mats.contains(mr.getMaterial())) {
										mats.add(mr.getMaterial());
									}
								}
							}

						}
					}
				}
			}

		}
		return mats.size();
	}

	// -------------------------------------------------Task Related
	// Logic------------------------------------------------
	public int countTaskEmployees(Task t) {
		HashSet<Employee> emps = new HashSet<Employee>();
		if (t != null) {
			for (Process process : t.getProcesses()) {
				if (process != null) {
					for (HumanResource hr : process.getHumanResourses()) {
						if (hr != null) {
							if (!emps.contains(hr.getEmployee())) {
								emps.add(hr.getEmployee());
							}
						}
					}

				}
			}
		}
		return emps.size();
	}

	public int countTaskMiscMats(Task t) {
		HashSet<Material> mats = new HashSet<Material>();
		if (t != null) {
			for (Process process : t.getProcesses()) {
				if (process != null) {
					for (MaterialResource mr : process.getMaterialResourses()) {
						if (mr != null) {
							if (mr.getMaterial().getMaterialType() == 'm') {
								if (!mats.contains(mr.getMaterial())) {
									mats.add(mr.getMaterial());
								}
							}

						}
					}

				}
			}
		}
		return mats.size();
	}

	public int countTaskRawMats(Task t) {
		HashSet<Material> mats = new HashSet<Material>();
		if (t != null) {
			for (Process process : t.getProcesses()) {
				if (process != null) {
					for (MaterialResource mr : process.getMaterialResourses()) {
						if (mr != null) {
							if (mr.getMaterial().getMaterialType() == 'r') {
								if (!mats.contains(mr.getMaterial())) {
									mats.add(mr.getMaterial());
								}
							}

						}
					}

				}
			}
		}
		return mats.size();
	}

	public double taskEmployeesCost(Task t) {
		double cost = 0;

		if (t != null) {
			for (Process process : t.getProcesses()) {
				if (process != null) {
					for (HumanResource hr : process.getHumanResourses()) {

						if (hr != null) {
							cost += hr.getResourceCost();
						}

					}
				}

			}
		}

		return cost;
	}

	public double taskRawCost(Task t) {
		int cost = 0;
		if (t != null)
			for (Process process : t.getProcesses()) {
				if (process != null) {
					for (MaterialResource mr : process.getMaterialResourses()) {

						if (mr.getMaterial().getMaterialType() == 'r') {
							if (mr != null) {
								cost += mr.getResourceCost();
							}

						}

					}
				}

			}

		return cost;
	}

	public double taskMiscCost(Task t) {
		int cost = 0;
		if (t != null)
			for (Process process : t.getProcesses()) {
				if (process != null) {
					for (MaterialResource mr : process.getMaterialResourses()) {

						if (mr.getMaterial().getMaterialType() == 'm') {
							if (mr != null) {
								cost += mr.getResourceCost();
							}

						}

					}
				}

			}

		return cost;
	}

	// ----------------------------------------------Process Related
	// Logic------------------------------------------------
	public int countProcessEmployees(Process p) {
		HashSet<Employee> emps = new HashSet<Employee>();
		if (p != null) {
			for (HumanResource hr : p.getHumanResourses()) {
				if (hr != null) {
					if (!emps.contains(hr.getEmployee())) {
						emps.add(hr.getEmployee());
					}
				}
			}
		}
		return emps.size();
	}

	public int countProcessMiscMats(Process p) {
		HashSet<Material> mats = new HashSet<Material>();
		if (p != null) {
			for (MaterialResource mr : p.getMaterialResourses()) {
				if (mr != null) {
					if (mr.getMaterial().getMaterialType() == 'm') {
						if (!mats.contains(mr.getMaterial())) {
							mats.add(mr.getMaterial());
						}
					}

				}
			}

		}
		return mats.size();
	}

	public int countProcessRawMats(Process p) {
		HashSet<Material> mats = new HashSet<Material>();
		if (p != null) {
			for (MaterialResource mr : p.getMaterialResourses()) {
				if (mr != null) {
					if (mr.getMaterial().getMaterialType() == 'r') {
						if (!mats.contains(mr.getMaterial())) {
							mats.add(mr.getMaterial());
						}
					}

				}
			}

		}
		return mats.size();
	}

	public double processEmployeesCost(Process p) {
		double cost = 0;

		if (p != null) {
			for (HumanResource hr : p.getHumanResourses()) {

				if (hr != null) {
					cost += hr.calculateResourceCost();
				}

			}
		}

		return cost;
	}

	public double processRawCost(Process p) {
		int cost = 0;
		if (p != null) {
			for (MaterialResource mr : p.getMaterialResourses()) {

				if (mr.getMaterial().getMaterialType() == 'r') {
					if (mr != null) {
						cost += mr.calculateResourceCost();
					}

				}

			}

		}
		return cost;
	}

	public double processMiscCost(Process p) {
		int cost = 0;
		if (p != null) {
			for (MaterialResource mr : p.getMaterialResourses()) {

				if (mr.getMaterial().getMaterialType() == 'm') {
					if (mr != null) {
						cost += mr.calculateResourceCost();
					}

				}

			}

		}
		return cost;
	}

	public void update(){
		if(selectedProject() != null) {
			projectCostVal.setText(selectedProject().getProjectCost() + "$");
			finalProjectDuration.setText(selectedProject().getProjectDuration() + " Hours");
		}
		}
		
	}

