package classes;
import java.io.Serializable;

import helpers.MyObservable;
import interfaces.ResourceOperations;

public abstract class Resource extends MyObservable implements Serializable, ResourceOperations , Comparable<Resource>{
	private static final long serialVersionUID = 1L;
	private static int nextResource  = 1;
    protected int resourceId;
    protected char resourceType ;
    protected double resourceCost;

    public Resource(char resourceType){
        resourceId = nextResource++;
        setResourceType(resourceType);
        resourceCost = 0.0;
    }

    public int getResourceId(){
        return resourceId;
    }

    public char getResourceType(){
         return resourceType;
    }

    public void setResourceType(char rt){
        resourceType = rt == 'h' || rt == 'm' ? rt : 'x';
    }
    
    public int compareTo(Resource r){
    	return Integer.compare(this.resourceId, r.getResourceId());
    }

    public String toString(){
         return resourceId + "";
    }

}
