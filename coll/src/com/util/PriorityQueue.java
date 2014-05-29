package com.util;

import java.util.*;


/**
 * @author youyou; write in 2014/5/28
 * �������ȼ��ѵ��޽����ȼ�
 * ���ȼ�����������Ȼ˳���Ҳ��������벻�ɱȽϵĶ���
 */

public class PriorityQueue<E> extends AbstractQueue<E> implements java.io.Serializable {
	private static final long serialVersionUID = -7720805057305804111L;
	private static final int DEFAULT_INITIAL_CAPACITY = 11;
	
	/**
	 *���ȼ����б�ʾΪһ��ƽ������ ��
	 *queue[n]���������ӷֱ�Ϊ��queue[2*n+1]��queue[2*(n+1)]
	 *���ȼ��������ɱȽ����������ͨ��Ԫ�ص���Ȼ˳������������Ƚ���Ϊ�գ�Ϊ����ÿ
	 *���ڵ�n �� ÿ��n�ĺ�� d��n <= d��
	 *��͵�Ԫ��ֵ�ڶ���queue[0]��������зǿգ�
	 */
	private transient Object[] queue;
	
	/**
	 * ��������޶�����Ԫ�صĸ���
	 */
	private int size = 0;
	
	
	/**
	 * �Ƚ�������null������ȼ�����ʹ��Ԫ�ص���Ȼ˳��
	 */
	private final Comparator<? super E> comparator;
	
	
	/**
	 * ������ȼ����б��ṹ�ϵ��޸ĵĴ���
	 */
	private transient int modCount = 0;
	
	/**
	 * ����һ��Ĭ�ϳ�ʼֵ�Ķ��У�������Ԫ�ص�˳��������ǵ���Ȼ˳��
	 */
	public PriorityQueue(){
		this(DEFAULT_INITIAL_CAPACITY,null);
	}
	
	
	/**
	 * ����һ��ָ����ʼ�����Ķ���,������Ԫ�ص�˳��������ǵ���Ȼ˳��
	 * @param intialCapacity ���ȶ��еĳ�ʼ����
	 * @throws �����ʼ��������1�����׳��쳣
	 */
	public PriorityQueue(int intialCapacity){
		this (intialCapacity,null);
	}
	
	
	/**
	 * ����һ��ָ����ʼ�����Ķ��У�������Ԫ�ص�˳�����ָ���ıȽ�����������(����һ���ɶԲ��ԣ���Ԫ�ؽ��бȽ�)
	 */
	public PriorityQueue(int initialCapacity, Comparator<? super E>comparator){
		if(initialCapacity < 1)
			throw new IllegalArgumentException();
		this.queue = new Object[initialCapacity];
		this.comparator = comparator;
	}
	
	
	/**
	 * 
	 */
	
	
	
	
	@Override
	public boolean offer(E arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
