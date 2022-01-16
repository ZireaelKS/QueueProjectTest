
public class TestingClass {
	public static void main(String[] arg) {
		ObjectQueue queue = new ObjectQueue();
				
		queue.pushBack("Строка 1");
		queue.pushBack("Строка 2");
		queue.pushBack("Строка 3");
		
		queue.pushFront("Строка 0");
		queue.pushFront("Строка -1");
		
		queue.size();
	}
}
