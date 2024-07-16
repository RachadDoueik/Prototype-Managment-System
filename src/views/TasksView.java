package views;

import java.util.Iterator;
import java.util.Map;

import javax.swing.*;

import classes.Process;
import classes.Project;
import classes.Task;
import helpers.MyObserver;

public class TasksView extends JList<Task> implements MyObserver {

    private static final long serialVersionUID = 1L;
    private Project project;
    private DefaultListModel<Task> tasksListModel;

    public TasksView(DefaultListModel<Task> tasksListModel, Project project) {
        super(tasksListModel);
        this.tasksListModel = tasksListModel;
        setProject(project);
    }

    public void updateTasksList() {
        tasksListModel.clear();
        if (project != null) {
            for (Iterator<Map.Entry<Character, Task>> it = project.getTasks().entrySet().iterator(); it.hasNext();) {
                Task t = it.next().getValue();
                if (t != null) {
                    if (!t.getProcesses().isEmpty()) {
                        if (hasNonEmptyProcesses(t)) {
                            tasksListModel.addElement(t);
                        } else {
                        	tasksListModel.removeElement(t);
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
                    if (!process.getHumanResourses().isEmpty() && !process.getMaterialResourses().isEmpty()
                            && !process.getProcessState().equals("Done")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    public void setProject(Project project) {
        this.project = project;
        if (this.project != null) {
            this.project.addObserver(this);
        }
        updateTasksList();
    }

    @Override
    public void update() {
        updateTasksList();
    }
}
