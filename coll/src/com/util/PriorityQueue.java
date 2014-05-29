package com.util;

import java.util.*;


/**
 * @author youyou; write in 2014/5/28
 * 基于优先级堆的无界优先级
 * 优先级队列依靠自然顺序的也不允许插入不可比较的对象
 */

public class PriorityQueue<E> extends AbstractQueue<E> implements java.io.Serializable {
	private static final long serialVersionUID = -7720805057305804111L;
	private static final int DEFAULT_INITIAL_CAPACITY = 11;
	
	/**
	 *优先级队列表示为一个平衡二叉堆 ；
	 *queue[n]的两个孩子分别为：queue[2*n+1]、queue[2*(n+1)]
	 *优先级队列是由比较器命令或者通过元素的自然顺序来排序，如果比较器为空，为堆中每
	 *个节点n 和 每个n的后代 d，n <= d；
	 *最低的元素值在队列queue[0]，假设队列非空；
	 */
	private transient Object[] queue;
	
	/**
	 * 在这个有限队列中元素的个数
	 */
	private int size = 0;
	
	
	/**
	 * 比较器，或null如果优先级队列使用元素的自然顺序
	 */
	private final Comparator<? super E> comparator;
	
	
	/**
	 * 这个优先级队列被结构上的修改的次数
	 */
	private transient int modCount = 0;
	
	/**
	 * 创建一个默认初始值的队列，队列中元素的顺序根据他们的自然顺序
	 */
	public PriorityQueue(){
		this(DEFAULT_INITIAL_CAPACITY,null);
	}
	
	
	/**
	 * 创建一个指定初始容量的队列,队列中元素的顺序根据他们的自然顺序
	 * @param intialCapacity 优先队列的初始容量
	 * @throws 如果初始容量少于1，则抛出异常
	 */
	public PriorityQueue(int intialCapacity){
		this (intialCapacity,null);
	}
	
	
	/**
	 * 创建一个指定初始容量的队列，队列中元素的顺序根据指定的比较器进行排序(传入一个派对策略，对元素进行比较)
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
