package com.util;


/**
 * 
 * @author Administrator
 *Iterator ��һ����������ͨ���������������Զ�collection ������б���
 * @param <E> E�������б������������
 */
public interface Iterator<E> extends java.util.Iterator<E>{
	/**
	 * 
	 * hasNext�����ж��Ƿ���element�����û�з���false
	 * 
	 * 
	 */
	public boolean hasNext();
	
	/**
	 * next��ȡ��һ��Ԫ�أ�ͬʱ�������������ƶ����Ӷ����Լ������ʺ���� �����������
	 * @return
	 */
	public E next();
	
	/**
	 * remove����ɾ��һ��element�����������next����֮ǰ����remove�����´�next����ʱ���ܲ����쳣
	 * һ�㶼��next֮���һ��remove
	 * ��������ɾ���ĺô����Ƿ��㣬������Ԫ��ɾ��ʱ��Ҫ���б������߱����λ�á�
	 */
	public void remove();
}
