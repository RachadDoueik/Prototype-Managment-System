package views;
import java.util.Iterator;
import java.util.Map;

import javax.swing.*;
import classes.Project;
import classes.Statistics;
import classes.Task;
import helpers.MyObserver;

public class ProjectsView1 extends JComboBox<Project> implements MyObserver {
	private static final long serialVersionUID = 1L;
	private Statistics statistics;

	public ProjectsView1(DefaultComboBoxModel<Project> model, Statistics statistics) {
		super(model);
		statistics.addObserver(this);
		this.statistics = statistics;
	}

	public Project getSelectedProject() {
		return (Project) getSelectedItem();
	}

	public void updateProjects() {
		Project selectedProject = getSelectedProject();
		DefaultComboBoxModel<Project> model = (DefaultComboBoxModel<Project>) getModel();
		model.removeAllElements();
		model.addElement(new Project("Choose Prototype", "", "In Progress"));

		for (Project project : statistics.getProjects()) {
			
			model.addElement(project);
			

		}

		if (selectedProject != null && model.getIndexOf(selectedProject) != -1) {
			setSelectedItem(selectedProject);
		}

	}
	

	public void update() {
		updateProjects();
	}
}