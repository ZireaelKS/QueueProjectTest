import java.util.ArrayList;

public class ObjectQueue {
	
	// ��������� �� ������ �������
    private ObjectBox head = null;
    // ��������� �� ��������� �������
    private ObjectBox tail = null;
    // ���� ��� �������� ������� �������
    private int size = 0;
	
	public void pushBack(Object obj) {
        ObjectBox ob = new ObjectBox();
        ob.setObject(obj);
        // ���� ������� ������ - � ��� ��� ��� ���������
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
        // ���� ���������� ���
    }
 
    public Object popFront() {
        // ���� ���������� ���
    }
    
    public Object back() {
        // ���� ���������� ���
    }
    
    public Object front() {
        // ���� ���������� ���
    }*/
    
    public int size() {
        return size;
    }
    
    /*public void clear() {
        // ���� ���������� ���
    }
    
    public ArrayList toArray() {
        // ���� ���������� ���
    }*/
}
