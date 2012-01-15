package shared.main.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Contains Actors
 * @author Ty
 *
 * @param <C> The class that you want it to contain.  Could either be Actor or PlayerShip
 */
public class Hollywood<C extends Entity> implements Serializable,Iterable<C>{
	private static final long serialVersionUID = -5241459214874342003L;
	
	private List<C> elements;
	private int runningTotal = 0;
	
	public Hollywood(){
		elements = new ArrayList<C>(100);
	}
	public Hollywood(Hollywood<C> other){
		elements = new ArrayList<C>(other.getNextId());
		for(C e:other){
			add(e);
		}
	}
	
	
	public void add(C element){
		elements.add(element);
	}
	
	public int getNextId(){
		return runningTotal;
	}

	@Override
	public Iterator<C> iterator() {
		return elements.iterator();
	}
}
