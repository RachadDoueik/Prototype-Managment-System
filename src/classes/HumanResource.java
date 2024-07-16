package classes;



public class HumanResource extends Resource{
    
	private static final long serialVersionUID = 1L;
	private Employee employee;
      private double workHours;
      private double resourceCost;
      public HumanResource(char resourceType , Employee employee , double workHours){
            super(resourceType);
            this.employee = employee;
            this.workHours = workHours;
      }
      
      public Employee getEmployee(){
            return employee;
      }

      public void setEmployee(Employee newEmployee){
            employee = newEmployee;
            setChanged();
            notifyObservers();
      }
      

      public double getWorkHours(){
            return workHours;
      }

      public void setWorkHours( double newWorkHours){
            workHours = newWorkHours;
            setChanged();
            notifyObservers();
      }

      public double calculateResourceCost(){
            double rc =  employee.getJob().getHourlyRate() * workHours;
            return rc;
      }
      
      
      public double getResourceCost(){
          return resourceCost;
    }      
      
      
      public String toString() {
    	   return resourceId + "    -   " + employee.getEmployeeName() + "   -   " + employee.getJob().getJobName() + "   -   "+workHours;
      }
      
}
