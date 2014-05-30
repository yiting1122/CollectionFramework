package com.test;

import com.util.ArrayList;
import com.util.List;
import junit.framework.TestCase;

public class ArrayListTest extends TestCase {
	

	public List<User> getList(){
		List<User> list=new ArrayList<User>();
		User user=new User("yiting","25");
		list.add(user);
		user=new User("wujiayong","24");
		list.add(user);
		user=new User("yrr","54");
		list.add(user);
		user=new User("yifda","29");
		list.add(user);
		return list;
	}
	
	
	/**
	 * 测试add函数
	 */
	public void testAdd(){
		List<User> list=getList();
		
		for(User u:list){
			System.out.println("userName "+u.getUserName()+"age: "+u.getAge());
		}
		assertEquals(4, list.size());
		assertTrue("the size is eror", list.size()==4);
	}	
	
	/**
	 * 因为arraylist的数组默认大小是10，测试超过10时，list的长度是否增加到10*1.5；
	 */
	public void testAddCapacity(){
		ArrayList<User> list=new ArrayList<User>();
		for(int i=0;i<11;i++){
			User u=new User("yy"+i, String.valueOf(i));
			//初始时modCount是0，每次发生结构性的变化modcount都会+1，这里测试modCount的变化正确性
			assertEquals(true, list.getModCount()==i);
			list.add(u);
		}
		//测试容量变化
		assertEquals(true, list.getCapacity()==10*1.5);
	}
	
	
	/**
	 * 测试删除指定位置的元素，其中也可以测试是否在index超出范围时候是否抛出异常
	 */
	public void testRemove(){
		List<User> list=getList();
		list.remove(0);
		assertEquals(3, list.size());
		
//		这个会抛出异常，因为list.remove(3),3已经超出了list中数组的范围
//		assertEquals(false, list.remove(3));
	}
	
	/**
	 * 测试clear清除函数
	 */
	public void testClear(){
		List<User> list=getList();
		list.clear();
		assertEquals(true, list.size()==0);
	}
	
	
}

class User {
	private String userName;
	private String age;
	
	
	public User(String userName, String age) {
		this.userName = userName;
		this.age = age;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
}