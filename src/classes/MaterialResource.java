package classes;


public class MaterialResource extends Resource{

   
	private static final long serialVersionUID = 1L;
	private int unitsUsed;
    private Material material;

    public MaterialResource(char resourceType , int unitsUsed , Material material){
         super(resourceType);
         this.unitsUsed = unitsUsed;
         this.material = material;
    }
    
    public Material getMaterial() {
    	  return material;
    }
    
    public void setMaterial(Material material) {
  	  this.material = material;
      setChanged();
      notifyObservers();
  }

    public int getUnitsUsed(){
        return unitsUsed;
    }

    public void setUnitsUsed(int unitsUsed){
         this.unitsUsed = unitsUsed;
         setChanged();
         notifyObservers();
    }

    public double calculateResourceCost(){
        double rc =  material.getCostPerunit() * unitsUsed;
        return rc;
    }
    
    public double getResourceCost(){
        return resourceCost;
    }
    
    public String toString() {
    	 return resourceId + "    -    "+material.getMaterialName()+"    -    "+material.getMaterialType()+"    -    "+unitsUsed;
    }
    
    
      
}
