package shared.main.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Contains Actors
 * @author Ty
 *
 * @param <C> The class that you want it to contain.  Could either be Entity or Ship, or something else
 */
public class Hollywood<C extends Entity> implements Serializable,Iterable<C>{
	private static final long serialVersionUID = -5241459214874342003L;
	
	private List<C> elements;
	
	public Hollywood(){
		elements = new ArrayList<C>(100);
	}
	public Hollywood(Hollywood<C> other){
		elements = new ArrayList<C>(other.size());
		for(C e:other){
			add(e);
		}
	}
	
	public void add(C element){
		element.setId(elements.size());
		elements.add(element);
	}
	
	public int size(){
		return elements.size();
	}
	
	public C get(int index){
		return elements.get(index);
	}

	@Override
	public Iterator<C> iterator() {
		return elements.iterator();
	}
}
