import java.util.ArrayList;

public class ObjectQueue {
	
	// Указатель на первый элемент
    private ObjectBox head = null;
    // Указатель на последний элемент
    private ObjectBox tail = null;
    // Поле для хранения размера очереди
    private int size = 0;
	
	public void pushBack(Object obj) {
        ObjectBox ob = new ObjectBox();
        ob.setObject(obj);
        // Если очередь пустая - в ней еще нет элементов
        if (head == null) {
            head = ob;
        } 
        else {        	
        	ob.setNext(tail);
        	tail.setPrev(ob);
        }
        tail = ob;               
        size++;
    }
 
	public void pushFront(Object obj) {
		ObjectBox ob = new ObjectBox();
        ob.setObject(obj);
        if (tail == null) {
            tail = ob;
        } 
        else {
        	ob.setPrev(head);
        	head.setNext(ob);
        }
        head = ob;
        size++;
    }
	
    public Object popBack() {
    	if (head != null) {
    		ObjectBox ob = new ObjectBox();
    		ob = tail;
    		if (head == tail) {
    			head = null;
				tail = null;				
			}
			else {
				tail.getNext().setPrev(null);
				tail = tail.getNext();
			}
    		size--;
    		return ob.getObject();
		}
    	return null;
    }
 
    public Object popFront() {
    	if (head != null) {
    		ObjectBox ob = new ObjectBox();
    		ob = head;
			if (head == tail) {
				head = null;
				tail = null;
			}
			else {
				head.getPrev().setNext(null);
				head = head.getPrev();
			}
			size--;
			return ob.getObject();
		}
    	return null;
    }
    
    public Object back() {
    	if (tail != null) {
    		return tail.getObject();
    	}
        return null;
    }
    
    public Object front() {
    	if (head != null) {
    		return head.getObject();
    	}
        return null;
    }
    
    public int size() {
        return size;
    }
    
    public void clear() {
        head = tail = null;
        size = 0;
    }
    
    public Object[] toArray(ObjectQueue queue) {
    	Object[] arrayObj = new Object[queue.size()];
    	arrayObj = mass(head, arrayObj, 0);   	
    	return arrayObj;
    }
    
    public Object[] mass(ObjectBox ob, Object[] arr, int num) {
    	if (ob != null) {
			arr[num] = ob.getObject();
			mass(ob.getPrev(), arr, ++num);
		}
    	return arr;   	
    }
    
    public void printList() {
		print(head);
	}
	
	public void print(ObjectBox t) {
		if (t.getPrev() != null) {
			System.out.println(t.getObject());
			print(t.getPrev());
		}
		else {
			System.out.println(t.getObject());
		}
	}
}
