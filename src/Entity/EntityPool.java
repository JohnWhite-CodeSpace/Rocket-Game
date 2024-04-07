package Entity;

import java.util.ArrayList;
import java.util.List;

public class EntityPool<T extends Entity> {
	private List<T> pool;
	private int capacity;
	
	public EntityPool(int capacity) {
		this.capacity = capacity;
		this.pool = new ArrayList<>(capacity);
	}
	
	public synchronized T acquire(Class<T> clazz) throws Exception {
	    if (!pool.isEmpty()) {
	        return pool.remove(pool.size() - 1);
	    } else {
	        return createInstance(clazz);
	    }
	}
	
	public synchronized void release(T entity) {
		if(pool.size() < capacity) {
			pool.add(entity);
		}
	}
	
	private <T> T createInstance(Class<T> clazz) throws Exception {
	    return clazz.getDeclaredConstructor().newInstance();
	}
}
