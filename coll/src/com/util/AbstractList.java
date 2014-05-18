package com.util;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {
	
	protected AbstractList(){
	}
	
	/**
	 * add方法继承AbstractCollection，但是其添加规则必须按list的规则进行添加
	 * 所以其内部实际上调用的是list.add(index,element),这种设计方法达到了最大的解耦
	 * 使得新的集合子类只要定制一个自己的add方法就可以实现自己的集合。
	 * @throws UnsupportedOperationException if the {@code add} operation
     *         is not supported by this list
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this list
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *         prevents it from being added to this list
     */
	 
	@Override
	public boolean add(E e){
		add(size(), e);
		return true;
	}
	
	
	/**
	 * @throws indexOutOfBoundsException 
	 */
	@Override
	public abstract E get(int index);
	
	
	/**
	 * 该实现就是抛出一个不支持异常，即当子类没有override该方法时就抛出不支持异常
	 */
	@Override
	public E set(int index,E element){
		throw new UnsupportedOperationException();
	}
	
	@Override
	public E remove(int index){
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int indexOf(Object o){
		ListIterator<E> it=listIterator();
		if(o==null){
			while(it.hasNext()){
				if(it.next()==null){
					return it.previousIndex();
				}
			}
		}else {
			while(it.hasNext()){
				if(o.equals(it.next())){
					return it.previousIndex();
				}
			}
		}
		return -1;
	}
	
	@Override
	public int lastIndexOf(Object o){
		
		ListIterator<E> it=listIterator();
		if(o==null){
			while(it.hasPrevious()){
				if(it.previous()==null){
					return it.nextIndex();
				}
			}
		}else {
			while(it.hasPrevious()){
				if(o.equals(it.previous())){
					return it.nextIndex();
				}
			}
		}
		return -1;
	}
	
	@Override
	public void clear(){
		removeRange(0, size());
	}
	
	/**
	 * 新增加方法，从指定位置获取一个listIterator，然后通过该遍历子 添加一个集合C，调用add（int ，e）
	 * @param index
	 * @param c
	 * @return
	 */
	public boolean addAll(int index,Collection<? extends E> c){
		rangeCheckForAdd(index);
		boolean modified=false;
		for(E e:c){
			add(index++, e);
			modified=true;
		}
		return modified;
	}
	
	/**
	 * 返回一个Iterator遍历子，该遍历子依赖于list的size函数，get（int）函数，remove（int）函数
	 * 可以参考父类的解释
	 */
	@Override
	public Iterator<E> iterator(){
		return new Itr();
		
	}
	
	/**
	 * 获取一个从0开始的遍历子，该遍历子是list特有的，可以改变遍历方向
	 */
	@Override
	public ListIterator<E> listIterator(){
		return listIterator(0);
	}
	
	@Override
	public ListIterator<E> listIterator(int index){
		rangeCheckForAdd(index);
		return new ListItr(index);
	}
	/**
	 * 通过这个变量可以判断是否有多个线程进入访问
	 */
	protected transient int modCount=0;
	
	/**
	 * 从下面可以看出remove必须和next搭配，不然lastRet就为-1；通过lastRet保证了只有在next之后才可以
	 * 调用remove 不然就是非法操作.同理可知lastRet保存的是最后一次调用next后保留的那个位置
	 * modcount是用于判断当前集合是否发生了结构性的变化，如果产生了变化那就会抛出不同步异常
	 */
	private class Itr implements Iterator<E>{
		int cursor=0;
		int lastRet=-1;
		int expectedModCount=modCount;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return cursor!=size();
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			checkForComodification();
			try {
				int i=cursor;
				E next=get(i);
				lastRet=i;
				cursor=i+1;
				return next;
			} catch (IndexOutOfBoundsException e) {
				checkForComodification();
				throw new NoSuchElementException();
			}
			
		}

		
		
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			if(lastRet<0){
				throw new IllegalStateException();
			}
			checkForComodification();
			try {
				AbstractList.this.remove(lastRet);
				if(lastRet<cursor){   //如果遍历指针和lastRet不相等，那么删除一个元素之后cursor必须减1，这样位置才能正确
					cursor--;          
				}
				lastRet=-1;
				expectedModCount=modCount;
			} catch (IndexOutOfBoundsException e) {    //产生indexoutofBoundsException 的条件就是并行操作
				throw new ConcurrentModificationException();
			}
		}
		
		final void checkForComodification(){
			if(modCount!=expectedModCount){
				throw new ConcurrentModificationException();
			}
		}
		
	}
	
	
	private class ListItr extends Itr implements ListIterator<E>{

		
		public ListItr(int index){
			cursor=index;
		}
		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return cursor!=0;
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return cursor-1;
		}
		
		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return cursor;
		}

		@Override
		public E previous() {
			// TODO Auto-generated method stub
			checkForComodification();
			try {
				int i=cursor-1;
				E previous=get(i);
				lastRet=cursor=i;
				return previous;
			} catch (IndexOutOfBoundsException e) {
				checkForComodification();
				throw new NoSuchElementException();
			}
		}

		

		@Override
		public void set(E e) {
			if(lastRet<0){
				throw new IllegalStateException();
			}
			checkForComodification();  //判断是否有产生结构变化非同步操作
			try {
				AbstractList.this.set(cursor, e);   //修改对同步没有影响，不产生结构变化
				expectedModCount=modCount;
			} catch (IndexOutOfBoundsException exception) {
				throw new ConcurrentModificationException();
			}
	
			
		}

		 /*
		  * 每次有这种变化操作都要去修改expectModCount，这样可以知道是否某个时候有多个线程进入访问
		  */
		@Override
		public void add(E e) {
			checkForComodification();
			try {
				AbstractList.this.add(cursor,e);
				cursor++;
				lastRet=-1;
				expectedModCount=modCount; 
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		
	}
	
	
	
	
	/**
	 * 该抽象类新增函数，主要用于删除元素，通过起始位置和结束位置
	 * @param fromIndex
	 * @param toIndex
	 */
	protected void removeRange(int fromIndex,int toIndex){
		ListIterator<E> it=listIterator(fromIndex);
		int len=toIndex-fromIndex;
		for(int i=0;i<len;i++){   //remove一定要在next之后调用
			it.next();
			it.remove(); 
		}
	}
	
	private void rangeCheckForAdd(int index){
		if(index>size()||index<0){
			throw new IndexOutOfBoundsException("outOfBoundsException "+index);
		}
	}
	
//	public  List<E> subList (int fromIndex,int toIndex){
//		return (this instanceof Rand)
//		
//	}
	
	
}
