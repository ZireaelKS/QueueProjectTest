
public class TestingClass {
	public static void main(String[] arg) {
		ObjectQueue queue = new ObjectQueue();
				
		queue.pushBack("������ 1");
		queue.pushBack("������ 2");
		queue.pushBack("������ 3");
		
		queue.pushFront("������ 0");
		queue.pushFront("������ -1");
				
		queue.printList();
		
		System.out.println("��������� � ������� - " + queue.size());
	
		queue.popBack();
		queue.popFront();
		
		System.out.println(queue.front());
		System.out.println(queue.back());
		
		System.out.println("��������� � ������� - " + queue.size());
		
		Object[] box = new Object[queue.size()];
		box = queue.toArray(queue);
		
		for (Object i:box) {
			System.out.println("������ ������� - " + i);
		}
		
		queue.clear();
		
		System.out.println("��������� � ������� ����� ������� - " + queue.size());
	}
}
