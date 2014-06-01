package com.util;
/**
 * ����ӿ���һ�����ϵĳ�Ա,�̳���Collection
 
	 * ��Collection�У��д˽ӿ����к�������ϸ����
	 * 
 * @author youyou
 *  write in 2014/6/1
 *
 * @param <E> �����������ɵ�Ԫ������
 */
public interface Set<E> extends Collection<E>{
	//��ѯ����
	/**
	 * ���� ������Ԫ�صĸ���
	 * �����������������Ԫ�س��������ֵ���򷵻����ֵMAX_VALUE
	 */
	 int size();
	
	
	/**
	 *  �жϼ����Ƿ�Ϊ�գ�Ϊ�շ���True,����false
	 * @return ����˼���û��Ԫ�أ��򷵻�true
	 */
	 boolean isEmpty();
	
	/**
	 * �жϼ������Ƿ���ָ����Ԫ�أ���������򷵻�Ture
	 * @throws ClassCastException ���ָ��Ԫ�ص����Ͳ����뼯�ϵ�����һ�£����׳��쳣
	 * @throws NullPointerExeception ���ָ��Ԫ��Ϊ�գ����Ҵ˼��ϲ������Ԫ�أ����׳��쳣
	 *
	 */
	 boolean contains(Object o);
	 
	 
	 /**
		 * Returns an iterator over the elements in this queue. The iterator
	     * does not return the elements in any particular order.
	     * Iterator ��һ����������ͨ���������������Զ�collection ������б���
		 */
	Iterator<E> iterator();
	

	 /**
		 * ����һ�����飬�����������˴˶����е�����Ԫ�ء���ЩԪ��û���ض�������˳��
		 * ���ַ����������һ���µ����飬��˵����߿������ɵ����޸ķ��ص����顣
		 */
	 Object[] toArray();
	 
	 
	 /**
	  * ������toArray���� �����Ҫ������Ч���������ڴ棬�жϼ��ϵĴ�С ������ϵĴ�С������
	 * a[]����Ĵ�С ͨ��������ƣ���������a��������������û�г���a����Ĵ�С������a�������������null  a[size]=null ���������ڼ�����󷵻ص�����
	 * ��ʵ�ʳ��ȣ����û�֪�� ���ļ�����û��nullԪ�ص�ʱ��
	  */
	 <T> T[] toArray(T[] a);
	 
	 
		
		/**
		 * �����������һ������ΪE�Ķ��󣬱�������ΪE����Ϊ���ϴ洢��Ԫ��������E��������ӵ�Ԫ�ص�����ҲΪE
		 * @param e
		 * @return ��ӳɹ�����true
		 */
	 boolean add(E e);
	 
	 
	 /**
		 * ɾ�������д��ڵ�Ԫ�أ���������ڸ�Ԫ���򷵻�false
		 * @param e
		 * @return ɾ���ɹ�����true
		 */	
	 boolean remove(Object o);
	 
	 
	 /**
		 * �ж�һ���������Ƿ���ڼ���C�����ж�C�ǲ��ǵ�ǰ���ϵ�һ���Ӽ�
		 *
		 * @param c c������Ԫ�ص������ǣ� ��������Ԫ�ص������Ƕ�̬������
		 * @return ������Ӽ���ϵ����Ϊtrue
		 */
	 boolean containsAll(Collection<?> c);
		

	 boolean addAll(Collection<? extends E> c);
	 
	 boolean retainAll(Collection<?> c);
	 
	 boolean removeAll(Collection<?> c);
	 
	 void clear();
	 
	 boolean equals (Object o);
	 	 
	 int hashCode();
}
