/**
 * this was writen in 2014/5/15
 * @author yiting
 * 这个接口继承了Iterator接口的方法，但是又提供了其独特的方法，实现这个接口的集合
 * 可以改变遍历的方向，其可以向前也可以向后，主要用于满足对list集合的遍历需求
 * 同时该接口也提供了在遍历的时候进行增加，更新 删除等功能
 */
package com.util;

public interface ListIterator<E> extends Iterator<E> {
	
	

	public boolean hasNext();
	public E next();
	public void remove();
	
	
	/**
	 * 判断当前位置元素是否有前驱，主要用于当list集合反转遍历方向的时候
	 * @return 返回true代表当前元素拥有前驱，其前卖弄还有节点
	 */
	public boolean hasPrevious();
	
	/**
	 * 返回前驱节点的位置index，
	 * 其与previous（）连用
	 * @return  返回index，当现在处于集合的起始位置的时候，返回-1
	 */
	public int previousIndex();
	
	/**
	 * 返回当前节点的前驱节点
	 * @return 前驱节点
	 * @throws exception NoSuchElementException 当没有前驱节点的时候
	 */
	public E previous();
	
	/**
	 * 返回后继节点的位置index
	 * 与next连用
	 * 当list达到最后一个元素时，可知其当前位置为n-1,那么nextindex就返回n，就是list的长度
	 * @return
	 */
	public int nextIndex();
	public int set(E e);
	public int add(E e);
	

}
