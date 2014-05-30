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
	 * ����add����
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
	 * ��Ϊarraylist������Ĭ�ϴ�С��10�����Գ���10ʱ��list�ĳ����Ƿ����ӵ�10*1.5��
	 */
	public void testAddCapacity(){
		ArrayList<User> list=new ArrayList<User>();
		for(int i=0;i<11;i++){
			User u=new User("yy"+i, String.valueOf(i));
			//��ʼʱmodCount��0��ÿ�η����ṹ�Եı仯modcount����+1���������modCount�ı仯��ȷ��
			assertEquals(true, list.getModCount()==i);
			list.add(u);
		}
		//���������仯
		assertEquals(true, list.getCapacity()==10*1.5);
	}
	
	
	/**
	 * ����ɾ��ָ��λ�õ�Ԫ�أ�����Ҳ���Բ����Ƿ���index������Χʱ���Ƿ��׳��쳣
	 */
	public void testRemove(){
		List<User> list=getList();
		list.remove(0);
		assertEquals(3, list.size());
		
//		������׳��쳣����Ϊlist.remove(3),3�Ѿ�������list������ķ�Χ
//		assertEquals(false, list.remove(3));
	}
	
	/**
	 * ����clear�������
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