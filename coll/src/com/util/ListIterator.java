/**
 * this was writen in 2014/5/15
 * @author yiting
 * ����ӿڼ̳���Iterator�ӿڵķ������������ṩ������صķ�����ʵ������ӿڵļ���
 * ���Ըı�����ķ����������ǰҲ���������Ҫ���������list���ϵı�������
 * ͬʱ�ýӿ�Ҳ�ṩ���ڱ�����ʱ��������ӣ����� ɾ���ȹ���
 */
package com.util;

public interface ListIterator<E> extends Iterator<E> {
	
	

	public boolean hasNext();
	public E next();
	public void remove();
	
	
	/**
	 * �жϵ�ǰλ��Ԫ���Ƿ���ǰ������Ҫ���ڵ�list���Ϸ�ת���������ʱ��
	 * @return ����true����ǰԪ��ӵ��ǰ������ǰ��Ū���нڵ�
	 */
	
	public boolean hasPrevious();
	
	
	
	/**
	 * ����ǰ���ڵ��λ��index��
	 * ����previous��������
	 * @return  ����index�������ڴ��ڼ��ϵ���ʼλ�õ�ʱ�򣬷���-1
	 */
	public int previousIndex();
	
	/**
	 * ���ص�ǰ�ڵ��ǰ���ڵ�
	 * @return ǰ���ڵ�
	 * @throws exception NoSuchElementException ��û��ǰ���ڵ��ʱ��
	 */
	
	public E previous();
	
	
	
	
	/**
	 * ���غ�̽ڵ��λ��index
	 * ��next����
	 * ��list�ﵽ���һ��Ԫ��ʱ����֪�䵱ǰλ��Ϊn-1,��ônextindex�ͷ���n������list�ĳ���
	 * @return
	 */
	
	public int nextIndex();
	
	
	/**
	 * ���øո�ͨ��next���ص��Ǹ�elementλ�õĽڵ�Ϊe���������next������ã������add����remove�����
	 * �ͻ�����쳣���÷�����ʵ���˶�����޸ġ�
	 * @param e 
	 * @throws ClassCastException ����ת���쳣
	 * @throws IllegalStateException �ڵ�����add ����remove������ʹ�ø÷������׳��쳣
	 * @return
	 */
	public void set(E e);
	
	
	
	
	/**
	 * ����Ԫ�أ�Ԫ�ز����λ����next����Ԫ�ص�ǰ�棬����previous����Ԫ�صĺ���
	 * @param e
	 */
	public void add(E e);
	

}
