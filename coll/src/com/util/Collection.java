/**
 * @auEhor yiting ,collection.java write in 2014/5/13
 * collection ���������ϵĸ��࣬��̳���iterable�����ӿ�  E(element)�����˼����д洢��Ԫ�����ͣ��Ӵ˴����Կ���һ�������д洢��������һ����
 *
 *��ǰ��û�м����쳣��˵����������Ȥ�߿��Լ����쳣��˵����
 *
 */
package com.util;

public interface Collection<E> extends Iterable<E> {
	
	
	/**
	 * 
	 * @return ���ؼ��ϵĴ�С��������ϵĴ�С������Integer.MAX_VALUE(2^32) �򷵻�Integer.MAX_VALUE
	 */
	
	public int size();
	
	
	
	/**
	 * �жϼ����Ƿ�Ϊ��
	 * @return false ������Ϊ�գ�true�������д���Ԫ��
	 */
	
	public boolean isEmpty();
	
	
	
	/**
	 * �жϵ�ǰ�������Ƿ���ڶ���O �ɹ�����true��
	 * @param o
	 * @return
     * @throws ClassCastException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
	 */
	
	public boolean contains(Object o);
	
	
	
	/**������������������������������������������������������������������ some problems is waiting to undstand.
	 * ����һ�������Ӷ��󣬵���Iterable ���Ѿ���iterator�������������Ϊʲô�˴���Ҫ��Ҫiterator�������� ���д�������ϣ���������ܹ����˴�����
	 * answer by yiting:Ϊʲô��ôд����Ϊ�˷����Ŀ�� ʵ�������ڵ�����Ժܷ���Ĳ�ѯ��������Ҫʵ�ֵķ�����
	 * ������Ҫȥ������ѯ����Ҫʵ������Щ������ֻҪ��ѯ����ӿھͿ����ˡ�
	 * ���˵���п���ƽ̨��������ǿ����Զ����ɣ����Ǽ�����û�п���ƽ̨�� һ����ȥ��ѯ�����൱������
	 */
	
	public Iterator<E> iterator();
	
	
	
	/**
	 * �Ѽ���ת��Ϊһ��objecE���飬����colleEion��˳����ʲô������ֵ��˳�������һ����
	 * @return
	 */
	
	public Object[] toArray();
	
	
	
	
	/**
	 * ������toArray���� �����Ҫ������Ч���������ڴ棬�жϼ��ϵĴ�С ������ϵĴ�С������
	 * a[]����Ĵ�С ͨ��������ƣ���������a��������������û�г���a����Ĵ�С������a�������������null  a[size]=null ���������ڼ�����󷵻ص�����
	 * ��ʵ�ʳ��ȣ����û�֪�� ���ļ�����û��nullԪ�ص�ʱ��
	 * 
	 * @param aһ�����ڱ��淵��ֵ������  ���������ֵ����return a���ؼ������a�Ĵ�С���������Ĵ�С�ͼ��ϵĴ�С���Ƚ϶�̬�ı仯��С
	 *  if (a.length < size)   
     *      a = (E[])java.lang.reflect.Array.   
     *            newInstance(a.getClass().getComponentType(), size);   
     *       System.arraycopy(elementData, 0, a, 0, size);   
     *   if (a.length > size)   
     *       a[size] = null; 
     *       return a��  
	 * @return a��
	 */
	
	public <T> T[] toArry(T a[]);
	
	
	
	/**
	 * �����������һ������ΪE�Ķ��󣬱�������ΪE����Ϊ���ϴ洢��Ԫ��������E��������ӵ�Ԫ�ص�����ҲΪE
	 * @param e
	 * @return ��ӳɹ�����true
	 */
	
	public boolean  add(E e);
	
	/**
	 * ɾ�������д��ڵ�Ԫ�أ���������ڸ�Ԫ���򷵻�false
	 * @param e
	 * @return ɾ���ɹ�����true
	 */
	
	public boolean  remove(E e);
	
	
	
	/**
	 * �ж�һ���������Ƿ���ڼ���C�����ж�C�ǲ��ǵ�ǰ���ϵ�һ���Ӽ�
	 *
	 * @param c c������Ԫ�ص������ǣ� ��������Ԫ�ص������Ƕ�̬������
	 * @return ������Ӽ���ϵ����Ϊtrue
	 */
	
	public boolean containsAll(Collection<?> c);
	
	
	/**
	 * ��ǰ���������һ������
	 * @param c ��Ԫ�ص������Ƕ�̬�����ģ�����Ԫ�����ͱ�����E������
	 * @return
	 */
	
	public boolean addAll(Collection<? extends E> c);
	
	/**
	 * ɾ����ǰ������C���ϵĽ�������ɾ����������ͬ��Ԫ�أ�
	 * @param c c������Ԫ�ص������Ƕ�̬�����ģ�
	 * @return
	 */
	public boolean removeAll(Collection<?> c);
	
	
	/**
	 * ɾ��������C���������Ԫ�� ��ֻ������ǰ������C���ϵĽ��� ����Ԫ�ض�ɾ��
	 * @param c
	 * @return
	 */
	public boolean retainAll(Collection<?> c);
	
	
	
	/**
	 * �������������Ԫ��
	 */
	
	public void clear();
	
	
	
	/**
	 * �ж����������Ƿ���ȣ���ò�Ҫ��д���������һ���д��Ŀ�ľ��Ǹı�ͨ���������ж������������
	 * �����Ϊ�������е�ĳЩֵ���ж϶�����ȡ�
	 * equalsӵ�м�������  ������ �Է��� ��������  x.equals(y)=>y.equals(x)
	 * warning:��д��equals�Ǿͱ����дhashcode����
	 * @param o
	 * @return
	 */
	
	public boolean equals(Object o);
	
	/**
	 * hashcode,�Ƕ���洢���ڴ��е�һ��ɢ��ֵ��hashֵ��һ�����ʣ����x.equals(y)=true ��x.hashcode()=y.hashcode()
	 * ���Ƿ������ǲ�������  ��x.hashcode()=y.hashcode() !=>x.equals(y)=true
	 * @return
	 */
	public int hashCode();
	
	
}
