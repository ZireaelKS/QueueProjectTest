
public class TestingClass {
	public static void main(String[] arg) {
		ObjectQueue queue = new ObjectQueue();
				
		queue.pushBack("Строка 1");
		queue.pushBack("Строка 2");
		queue.pushBack("Строка 3");
		
		queue.pushFront("Строка 0");
		queue.pushFront("Строка -1");
				
		queue.printList();
		
		System.out.println("Элементов в очереди - " + queue.size());
	
		queue.popBack();
		queue.popFront();
		
		System.out.println(queue.front());
		System.out.println(queue.back());
		
		System.out.println("Элементов в очереди - " + queue.size());
		
		Object[] box = new Object[queue.size()];
		box = queue.toArray(queue);
		
		for (Object i:box) {
			System.out.println("Объект массива - " + i);
		}
		
		queue.clear();
		
		System.out.println("Элементов в очереди после очистки - " + queue.size());
	}
}
