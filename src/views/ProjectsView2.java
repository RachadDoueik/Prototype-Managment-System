package views;
import java.util.Iterator;
import java.util.Map;

import javax.swing.*;
import classes.Project;
import classes.Statistics;
import classes.Task;
import helpers.MyObserver;

public class ProjectsView2 extends JComboBox<Project> implements MyObserver {
	private static final long serialVersionUID = 1L;
	private Statistics statistics;

	public ProjectsView2(DefaultComboBoxModel<Project> model, Statistics statistics) {
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
            
			boolean add = true;
			Iterator<Map.Entry<Character, Task>> iterator = project.getTasks().entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Character, Task> entry = iterator.next();
				if (!(entry.getValue() == null)) {
					 if(entry.getValue().getTaskState().equals("Done")) {
						 add = true;
						 continue;
					 }
					 else {
						 add = false;
						 break;
					 }
				}
			}
			if(add && (project.getTasks().get('c') != null || project.getTasks().get('p') != null || project.getTasks().get('f') != null || project.getTasks().get('a') != null || project.getTasks().get('t') != null)) {
				model.addElement(project);
			}
			else {
				model.removeElement(project);
			}
		}

		if (selectedProject != null && model.getIndexOf(selectedProject) != -1) {
			setSelectedItem(selectedProject);
		}

	}


	public void update() {
		updateProjects();
	}
}