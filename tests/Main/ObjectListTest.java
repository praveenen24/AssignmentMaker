package Main;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ObjectListTest {
	private ObjectList list;
	private AssignmentObject o1;
	private AssignmentObject o2;
	private AssignmentObject o3;
	private AssignmentObject o4;
	
	@Before
	public void setup() {
		list = new ObjectList("List1");
		o1 = new AssignmentObject("Object1");
		o2 = new AssignmentObject("Test2");
		o3 = new AssignmentObject("Test3");
		o4 = new AssignmentObject("Test4");
		list.add(o1);
		list.add(o2);
		list.add(o3);
		list.add(o4);
	}
	
	@Test
	public void nameTest() {
		System.out.println("-------------Name Test Start-------------");
		System.out.println("List Name Expected: List1 | Actual : " + list.getName());
		assertTrue(list.getName().equals("List1"));
		list.setName("Test");
		System.out.println("List Name Expected: Test | Actual : " + list.getName());
		assertTrue(list.getName().equals("Test"));
		System.out.println("-----------------------------------------");
	}
	
	@Test
	public void addToListTest() {
		System.out.println("------------Add To List Test-------------");
		System.out.println("List Size Expected: 4 | Actual : " + list.size());
		assertTrue(list.size() == 4);
		
		list.add(new AssignmentObject("Test5"));
		System.out.println("List Size Expected: 5 | Actual : " + list.size());
		assertTrue(list.size() == 5);
		System.out.println("-----------------------------------------");
	}
	
	@Test
	public void removeFromListTest() {
		System.out.println("------------Remove From List Test-------------");
		System.out.println("List Size Expected: 4 | Actual : " + list.size());
		assertTrue(list.size() == 4);
		
		list.remove(o1);
		System.out.println("List Size Expected: 3 | Actual : " + list.size());
		assertTrue(list.size() == 3);
		
		list.remove(o2);
		System.out.println("List Size Expected: 2 | Actual : " + list.size());
		assertTrue(list.size() == 2);
		
		list.remove(o3);
		System.out.println("List Size Expected: 1 | Actual : " + list.size());
		assertTrue(list.size() == 1);
		
		list.remove(o4);
		System.out.println("List Size Expected: 0 | Actual : " + list.size());
		assertTrue(list.size() == 0);
		
		System.out.println("----------------------------------------------");
	}
}
