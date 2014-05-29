/**
 * write in 2014/5/21
 * @author yiting
 * AbstractList class ��List��һ������ʵ�֣������Ѿ�ʵ���˴󲿷ֵĺ�����ͬʱ�ڸ�������ʵ���������ڲ�������ӽӿڣ�Itr��ListItr��
 * �ڲ����൱�ڸ����һ����������������Է��ʸ���ĳ�Ա�����ͱ���
 * �Լ�������SubList������List�����Ƿ�������һ��list���󣬶��Ǹ�list����ͬ�����ϣ�ͨ��offestƫ�������в�����
 * AbstratList��ʵ����ֻҪ��дget size set remove �������ɡ�
 * ����ǲ��ɱ伯����ֻҪʵ��get size����
 * 
 *  Fail-Fast(����ʧ��)����
 *                    ��ϸ�۲������ĸ���������������Դ���оͻᷢ��һ���ر������modCount��API�������£�
 *                The number of times this list has been structurally modified. Structural modifications are those
 *                that change the size of the list, or otherwise perturb it in such a fashion that iterations in progress
 *                may yield incorrect results.
 *                   ��¼�޸Ĵ��б�Ĵ����������ı��б�Ľṹ���ı��б�Ĵ�С�������б��˳���ʹ���ڽ���
 *              ������������Ľ����Tips:��������Ԫ�ص�ֵ�����ǽṹ���޸�
 *                  ����֪������ArrayList���̲߳���ȫ�ģ������ʹ�õ������Ĺ��������������߳��޸���List�ͻ�
 *               �׳�ConcurrentModificationException�����Fail-Fast���ơ�   
 *                  ��ô����ʧ�ܾ����Ǹ�ʲô��˼�أ�
 *              ��ArrayList�ഴ��������֮�󣬳���ͨ������������remove��add���б�ṹ�����޸ģ�����������
 *               �߳������κ���ʽ���б�����޸ģ����������ϻ��׳��쳣������ʧ�ܡ�   
 * 
 * 
 */



package com.util;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

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
	 * ���get/set����������ӵ� �����ڲ��ԣ���������ɾ��,�����������modCount��ֵ ����Ҫͨ���ϵ㣬ͬʱ����ͨ��set����
	 * ��ʹ��һЩ�����׳��쳣������ģ�Ⲣ�л���
	 * @return
	 */
	public int getModCount() {
		return modCount;
	}

	public void setModCount(int modCount) {
		this.modCount = modCount;
	}


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
        /**
         * ����������ǰ
         */
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

		
        /**
         * ����һ������
         */
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
	
	
/**
 * �ж�index�Ƿ񳬳���Χ
 * @param index
 */
	
	private void rangeCheckForAdd(int index){
		if(index>size()||index<0){
			throw new IndexOutOfBoundsException("outOfBoundsException "+index);
		}
	}
	
	/**
	 * ��ʵ���ն��ǵ��õ�return  new SubList<E>(this, fromIndex, toIndex)
	 * ֻ�����е�listʵ���������ȡ���������RandomAccessSubList<E>
	 */
	public  List<E> subList (int fromIndex,int toIndex){
		return (this instanceof RandomAccess?new RandomAccessSubList<E>(this, fromIndex, toIndex):
			new SubList<E>(this, fromIndex, toIndex));
		
	}
	
	@Override
	public boolean equals(Object o){
		if(o==this){
			return true;
		}
		if(!(o instanceof List)){
			return false;
		}
		ListIterator<E> e1=listIterator();
		ListIterator e2=((List)o).listIterator();
		while(e1.hasNext()&&e2.hasNext()){
			E o1=e1.next();
			Object o2=e2.next();
			if(!(o1==null?o2==null:o1.equals(o2))){
				return false;
			}
		}
		return !(e1.hasNext()||e2.hasNext());
	}
	
	
	/**
	 * hashcode ��������������������
	 */
	@Override
	public int hashCode(){
		int hashCode=1;
		for(E e:this){
			hashCode=31*hashCode+(e==null?0:e.hashCode());
		}
		return hashCode;
	}
	
	
	
}

/**
 * ����list������list������ԭ����list��
 * @author yiting
 *
 * @param <E>
 */

class SubList<E> extends AbstractList<E>{
	private final AbstractList<E> l;
	private final int offset;
	private int size;
	public SubList(AbstractList<E> list,int fromIndex,int toIndex){
		if(fromIndex<0||fromIndex>list.size()){
			throw  new IndexOutOfBoundsException("fromIndex <0 or fromIndex>list.size()");
		}
		if(toIndex<0||toIndex>list.size()){
			throw new IndexOutOfBoundsException("toIndex<0 or toIndex>list.size()");
		}
		if(toIndex<fromIndex){
			throw new IndexOutOfBoundsException("toIndex < fromIndex");
		}
		this.l=list;
		this.offset=fromIndex;
		this.size=toIndex-fromIndex;
		this.modCount=l.modCount;
	}
	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
        checkForComodification();
        l.add(index+offset, element);
        this.modCount = l.modCount;
        size++;
		
	}
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return new SubList<E>(this, fromIndex, toIndex);
	}
	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		 rangeCheck(index);
	     checkForComodification();
	     return l.get(index+offset);
	}
	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		checkForComodification();
		return l.set(index+offset, element);
	}
	/**
	 * ͨ��modecount��֤ÿ�εĲ���������һ��״̬�µĲ���
	 * @param index
	 * @return
	 */
	@Override
	public E remove(int index) {
		rangeCheck(index);
		checkForComodification();
		E result=l.remove(index+offset);
		this.modCount=l.modCount;
		size--;
		return result;
	}
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		rangeCheck(index);
		//jdk ֱ�Ӳ��õ�c.size()�������cΪnull��ʱ���Ƿ�ᱨ�쳣�أ� �о����c==null���ǲ�����c.size�����ģ�������Բ���
		int cSize=c.size();
		if(cSize==0){
			return false;
		}
		checkForComodification();
		l.addAll(index+offset, c);
		size+=cSize;
		return true;
	}
	
	@Override
	public Iterator<E> iterator(){
		return listIterator();
	}
	
	
	@Override
	public ListIterator<E> listIterator(final int index) {
		// TODO Auto-generated method stub
		checkForComodification();
		rangeCheckForAdd(index);
		return new ListIterator<E>() {
			private final ListIterator<E> i=l.listIterator(index+offset);
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return nextIndex()<size;
			}

			@Override
			public E next() {
				// TODO Auto-generated method stub
				if(hasNext()){
					return i.next();
				}else{
					throw new NoSuchElementException();
				}
			}

			@Override
			public void remove() {
				i.remove();
				SubList.this.modCount=l.modCount;
				size--;
				
			}

			@Override
			public boolean hasPrevious() {
				// TODO Auto-generated method stub
				return previousIndex()>0;
			}

			@Override
			public int previousIndex() {
				// TODO Auto-generated method stub
				return i.previousIndex()-offset;
			}

			@Override
			public E previous() {
				// TODO Auto-generated method stub
				if(hasPrevious()){
					return i.previous();
				}else {
					throw new NoSuchElementException();
				}
			}

			@Override
			public int nextIndex() {
				// TODO Auto-generated method stub
				return i.nextIndex()-offset;
			}

			@Override
			public void set(E e) {
				// TODO Auto-generated method stub
				i.set(e);
			}

			@Override
			public void add(E e) {
				// TODO Auto-generated method stub
				i.add(e);
				SubList.this.modCount=l.modCount;
				size++;
			}
		};

	}
	@Override
	protected void removeRange(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		 checkForComodification();
	        l.removeRange(fromIndex+offset, toIndex+offset);
	        this.modCount = l.modCount;
	        size -= (toIndex-fromIndex);
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		checkForComodification();
		return size;
	}
	
	 private void rangeCheck(int index) {
	        if (index < 0 || index >= size)
	            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	    }
	 private String outOfBoundsMsg(int index) {
	        return "Index: "+index+", Size: "+size;
	    }

	 private void checkForComodification() {
	        if (this.modCount != l.modCount)
	            throw new ConcurrentModificationException();
	    }
	 private void rangeCheckForAdd(int index) {
	        if (index < 0 || index > size)
	            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	    }


}

class RandomAccessSubList<E> extends SubList<E> implements RandomAccess{

	public RandomAccessSubList(AbstractList<E> list, int fromIndex,
			int toIndex) {
		super(list, fromIndex, toIndex);
		// TODO Auto-generated constructor stub
	}




	
	
}
