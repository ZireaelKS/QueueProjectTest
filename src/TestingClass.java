
public class TestingClass {
	public static void main(String[] arg) {
		ObjectQueue queue = new ObjectQueue();
				
		queue.pushBack("������ 1");
		queue.pushBack("������ 2");
		queue.pushBack("������ 3");
		
		queue.pushFront("������ 0");
		queue.pushFront("������ -1");
		
		queue.size();
	}
}
