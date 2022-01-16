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
	
    /*public Object popBack() {
        // Пока реализации нет
    }
 
    public Object popFront() {
        // Пока реализации нет
    }
    
    public Object back() {
        // Пока реализации нет
    }
    
    public Object front() {
        // Пока реализации нет
    }*/
    
    public int size() {
        return size;
    }
    
    /*public void clear() {
        // Пока реализации нет
    }
    
    public ArrayList toArray() {
        // Пока реализации нет
    }*/
}
