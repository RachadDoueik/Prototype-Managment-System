package classes;
import  helpers.MyObservable;
import java.io.*;

public class Job extends MyObservable implements Serializable{

	private static final long serialVersionUID = 1L;

	private static int nextJobId;

    private int jobId;

    private String jobName;

    private double hourlyRate;
    
    boolean changed;

    public Job(String jobName , double hourlyRate){
        jobId = nextJobId++;
        this.jobName = jobName;
        this.hourlyRate = hourlyRate;
    }


    public int getJobId(){
        return jobId;
    }

    public String getJobName(){
        return jobName;
    }

    public void setJobName(String newJobName){
        jobName = newJobName;
        setChanged();
        notifyObservers();
    }

    public double getHourlyRate(){
        return hourlyRate;
    }

    public void setHourlyRate(double newHourlyRate){
        hourlyRate = newHourlyRate;
        setChanged();
        notifyObservers();
    }


    public String toString(){
        return jobName;
    }
}
