package classes;
import java.io.Serializable;

import helpers.MyObservable;

public class Material extends MyObservable implements Comparable<Material> , Serializable{
	private static final long serialVersionUID = 1L;
	private static int nextMaterial = 1;
     private int materialId;
     private double costPerUnit;
     private char materialType;
     private String materialName;

     public Material (char materialType , double costPerUnit , String materialName) {
          materialId = nextMaterial++;
          this.costPerUnit = costPerUnit;
          setMaterialType(materialType);
          this.materialName = materialName;
     }

     public int getMaterilaId(){
         return materialId;
     }

    
     public String getMaterialName(){
         return materialName;
     }
     
     
     public void setMaterialName(String newMaterialName){
         materialName = newMaterialName;
         setChanged();
         notifyObservers();
     }
     
     public double getCostPerunit(){
         return costPerUnit;
     }

     public void setCostPerUnit(double newCost){
         costPerUnit = newCost;
         setChanged();
         notifyObservers();
     }

     public char getMaterialType(){
          return materialType;
     }

     public void setMaterialType(char mt){
         materialType = mt == 'r' || mt == 'm' ? mt : 'x';
         setChanged();
         notifyObservers();
     }
     
     public int compareTo(Material m) {
    	 return Integer.compare(materialId, m.getMaterilaId());
     }

     public String toString(){
         return materialName;
     }
}
