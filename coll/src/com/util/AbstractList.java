package com.util;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {
	
	protected AbstractList(){
	}
	
	/**
	 * add�����̳�AbstractCollection����������ӹ�����밴list�Ĺ���������
	 * �������ڲ�ʵ���ϵ��õ���list.add(index,element),������Ʒ����ﵽ�����Ľ���
	 * ʹ���µļ�������ֻҪ����һ���Լ���add�����Ϳ���ʵ���Լ��ļ��ϡ�
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
	 * ��ʵ�־����׳�һ����֧���쳣����������û��override�÷���ʱ���׳���֧���쳣
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
	 * �����ӷ�������ָ��λ�û�ȡһ��listIterator��Ȼ��ͨ���ñ����� ���һ������C������add��int ��e��
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
	 * ����һ��Iterator�����ӣ��ñ�����������list��size������get��int��������remove��int������
	 * ���Բο�����Ľ���
	 */
	@Override
	public Iterator<E> iterator(){
		return new Itr();
		
	}
	
	/**
	 * ��ȡһ����0��ʼ�ı����ӣ��ñ�������list���еģ����Ըı��������
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
	 * ͨ��������������ж��Ƿ��ж���߳̽������
	 */
	protected transient int modCount=0;
	
	/**
	 * ��������Կ���remove�����next���䣬��ȻlastRet��Ϊ-1��ͨ��lastRet��֤��ֻ����next֮��ſ���
	 * ����remove ��Ȼ���ǷǷ�����.ͬ���֪lastRet����������һ�ε���next�������Ǹ�λ��
	 * modcount�������жϵ�ǰ�����Ƿ����˽ṹ�Եı仯����������˱仯�Ǿͻ��׳���ͬ���쳣
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
				if(lastRet<cursor){   //�������ָ���lastRet����ȣ���ôɾ��һ��Ԫ��֮��cursor�����1������λ�ò�����ȷ
					cursor--;          
				}
				lastRet=-1;
				expectedModCount=modCount;
			} catch (IndexOutOfBoundsException e) {    //����indexoutofBoundsException ���������ǲ��в���
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
			checkForComodification();  //�ж��Ƿ��в����ṹ�仯��ͬ������
			try {
				AbstractList.this.set(cursor, e);   //�޸Ķ�ͬ��û��Ӱ�죬�������ṹ�仯
				expectedModCount=modCount;
			} catch (IndexOutOfBoundsException exception) {
				throw new ConcurrentModificationException();
			}
	
			
		}

		 /*
		  * ÿ�������ֱ仯������Ҫȥ�޸�expectModCount����������֪���Ƿ�ĳ��ʱ���ж���߳̽������
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
	 * �ó�����������������Ҫ����ɾ��Ԫ�أ�ͨ����ʼλ�úͽ���λ��
	 * @param fromIndex
	 * @param toIndex
	 */
	protected void removeRange(int fromIndex,int toIndex){
		ListIterator<E> it=listIterator(fromIndex);
		int len=toIndex-fromIndex;
		for(int i=0;i<len;i++){   //removeһ��Ҫ��next֮�����
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
