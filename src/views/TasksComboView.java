package views;

import java.util.Iterator;
import java.util.Map;

import javax.swing.*;

import classes.Process;
import classes.Project;
import classes.Task;
import helpers.MyObserver;

public class TasksComboView extends JComboBox<Task> implements MyObserver {
	private static final long serialVersionUID = 1L;
	private Project project;
	private DefaultComboBoxModel<Task> tasksComboModel;

	public TasksComboView(DefaultComboBoxModel<Task> tasksComboModel, Project project) {
		super(tasksComboModel);
		this.tasksComboModel = tasksComboModel;
		setProject(project);
	}

	public void updateTasksCombo() {
		tasksComboModel.removeAllElements();
		tasksComboModel.addElement(new Task('x',"In Progress"));
		if (project != null) {
			for (Iterator<Map.Entry<Character, Task>> it = project.getTasks().entrySet().iterator(); it.hasNext();) {
				Task t = it.next().getValue();
				if (t != null) {
					if (!t.getProcesses().isEmpty()) {
						if (hasNonEmptyProcesses(t)) {
							tasksComboModel.addElement(t);
						}
						else {
							tasksComboModel.removeElement(t);
						}

					}
				}
			}
		}
		this.setSelectedIndex(0);
	}

	public boolean hasNonEmptyProcesses(Task t) {

		if (t != null) {
			for (Iterator<Process> procIt = t.getProcesses().iterator(); procIt.hasNext();) {
				Process process = procIt.next();
				if (process != null) {
					if (!process.getHumanResourses().isEmpty() && !process.getMaterialResourses().isEmpty()) {
						return true;
					}
				}
			}
		} else {
			return false;
		}

		return false;
	}
	public void setProject(Project project) {
		this.project = project;
		if (this.project != null) {
			this.project.addObserver(this);
		}
		updateTasksCombo();
	}

	@Override
	public void update() {
		updateTasksCombo();
	}
}