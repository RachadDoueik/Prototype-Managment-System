package helpers;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyObservable {
     boolean changed;
     CopyOnWriteArrayList<MyObserver> observers;
     
    
     public MyObservable() {
    	 changed = false;
    	 observers = new CopyOnWriteArrayList<MyObserver>();
     }
     
    
     public void setChanged() {changed = true;}
     
     
     public void addObserver(MyObserver obs) { observers.add(obs); }
     
    
     public void removeObserver(MyObserver obs) {observers.remove(obs);}
     
     public void notifyObservers() {
    	 if(changed) 
    	 {
    		 for(Iterator<MyObserver> it = observers.iterator(); it.hasNext();) {
      		   it.next().update();
      	  }
    		 changed = false;
    	 }
    	 
     }
     
     
}
