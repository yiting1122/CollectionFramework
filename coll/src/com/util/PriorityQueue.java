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
	 * ����һ��ָ����ʼ�����Ķ��У�������Ԫ�ص�˳�����ָ���ıȽ�����������(����һ��������ԣ���Ԫ�ؽ��бȽ�)
	 */
	public PriorityQueue(int initialCapacity, Comparator<? super E>comparator){
		if(initialCapacity < 1)
			throw new IllegalArgumentException();
		this.queue = new Object[initialCapacity];
		this.comparator = comparator;
	}
	
	/**
	 * ����һ�����а�����ָ�������е�Ԫ��
	 * @param c
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue(Collection<? extends E> c){
		if(c instanceof SortedSet<?>){
			SortedSet<? extends E> ss = (SortedSet<? extends E>) c;
			this.comparator = (Comparator<? super E>) ss.comparator();
			initElementsFromCollection(ss);
		}
		else if (c instanceof PriorityQueue<?>){
			PriorityQueue<? extends E> pq = (PriorityQueue<? extends E>) c;
			this.comparator = (Comparator<? super E>) pq.comparator();
			initFromPriorityQueue(pq);
		}
		else{
			this.comparator = null;
			initElementsFromCollection(c);
		}
	}
	
	
	/**
	 *����һ�����а�����ָ�������е�Ԫ��
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue(PriorityQueue<? extends E> c){
		this.comparator = (Comparator<? super E>) c.comparator();
		initFromPriorityQueue(c);
	}
	
	
	/**
	 *����һ�����а�����ָ�����򼯺��е�Ԫ��
	 */
	public PriorityQueue(SortedSet<? extends E> c){
		this.comparator =  (Comparator<? super E> )c.comparator();
		initElementsFromCollection(c);
	}
	
	private void initFromPriorityQueue(PriorityQueue<? extends E> c){
		if(c.getClass() == PriorityQueue.class){
			this.queue = c.toArray();
			this.size = c.size();
		}else{
			initFromCollection(c);
		}
	}
		
	private void initElementsFromCollection(Collection<? extends E> c){
		Object[] a = c.toArray();
		//if c.toArray incorrectly doesn't return Object[],copy it.
		if(a.getClass() != Object[].class)
			a = Arrays.copyOf(a,a.length,Object[].class);
		int len = a.length;
		if(len == 1 || this.comparator != null)
			for(int i=0; i<len; i++){
				if(a[i] == null)
					throw new NullPointerException();
			}
		this.queue = a;
		this.size = a.length;
				
	}
	
	/**
	 *��ʼ�����е������Ԫ���ǴӸ��������л�ȡ��
	 *@param c �Ǽ���
	 */
	private void initFromCollection(Collection<? extends E> c ){
		initElementsFromCollection(c);
		heapify();
	}
	
	/** ����Ĵ�С*/
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	
	
	/**
	 * �������������
	 */
	private void grow (int minCapacity){
		int oldCapacity = queue.length;
		int newCapacity  = oldCapacity + ((oldCapacity < 64)?
										  (oldCapacity +2) :
										  (oldCapacity >> 1));
		if(newCapacity - MAX_ARRAY_SIZE > 0)
			newCapacity = hugeCapacity(minCapacity);
		queue = Arrays.copyOf(queue, newCapacity);
	}
	
	private static int hugeCapacity(int minCapacity){
		if(minCapacity < 0 )//overflow
			throw new OutOfMemoryError();
		return (minCapacity > MAX_ARRAY_SIZE) ?
				Integer.MAX_VALUE:
				MAX_ARRAY_SIZE;
	}
	
	public boolean add(E e){
		return offer(e);
	}
	
	@Override
	/**
	 * ��������ȼ������в���ָ����Ԫ��
	 */
	public boolean offer(E e) {
		// TODO Auto-generated method stub
		if(e == null)
			throw new NullPointerException();
		modCount++;
		int i = size;
		if(i >= queue.length)
			grow(i + 1);
		size = i + 1;
		if(i == 0)
			queue[0] = e;
		else
			siftUp(i,e);
		return true;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		if(size == 0 )
			return null;
		return (E) queue[0];
	}
	
	private int indexOf(Object o){
		if(o != null){
			for(int i = 0; i < size; i++)
				if(o.equals(queue[i]))
					return i;
		}
		return -1;
	}

	
	/**
	 * �����������ɾ��ָ��Ԫ�صĵ�һʵ�������������
	 */
	public boolean remove(Object o){
		int i = indexOf(o);
		if(i == -1)
			return false;
		else{
			removeAt(i);
			return true;
		}
	}
	
	/**
	 * �汾��ɾ����ʹ������ƽ�Ȼ�ƽ��
	 */
	boolean removeEq(Object o){
		for (int i= 0; i<size; i++){
			if(o == queue[i]){
				removeAt(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ���������а������ָ����Ԫ�أ��򷵻�true������False
	 */
	public boolean contains(Object o){
		return indexOf(o) != -1;
	}
	
	/**
	 * ����һ�����飬�����������˴˶����е�����Ԫ�ء���ЩԪ��û���ض�������˳��
	 * ���ַ����������һ���µ����飬��˵����߿������ɵ����޸ķ��ص����顣
	 */
	public Object[] toArray(){
		return Arrays.copyOf(queue, size);
	}
	
	
	/**
	 * ���ص����������������ָ������
	 * ������з���ָ�������飬�������أ�����һ���µ����鱻����ָ��������������ͺʹ˶��еĴ�С
	 */
	public <T> T[] toArray(T[] a){
		if(a.length < size)
			return (T[]) Arrays.copyOf(queue, size,a.getClass());
		System.arraycopy(queue, 0, a, 0, size);
		if(a.length > size)
			a[size] = null;
		return a;
	}
	
	/**
	 * Returns an iterator over the elements in this queue. The iterator
     * does not return the elements in any particular order.
     * Iterator ��һ����������ͨ���������������Զ�collection ������б���
	 */
	@Override
	public Iterator<E> iterator(){
		return new Itr();
	}
	
	private final class Itr implements Iterator<E>{
		
		private int cursor = 0;
		
		private int lastRet = -1;
		
		/**
		 * �����е�Ԫ�شӶ���δ���ʵĲ����ƶ����ѷ��ʵĲ��֣��ڵ����Ĺ�������Ϊ�������ˡ���Ԫ���ƶ����
		 */
		private ArrayDeque<E> forgetMeNot = null;
		
		private E lastRetElt = null;
		
		/**
		 *modCountֵ����ʾ��������ΪӦ��֧�� ���У����Υ������һ��������������Ⲣ���޸�
		 */
		private int exceptedModCount = modCount;
		
		public boolean hasNext(){
			return cursor < size || (forgetMeNot != null && !forgetMeNot.isEmpty());
		}
		
		public E next(){
			if(exceptedModCount != modCount)
				throw new ConcurrentModificationException();
			if(cursor < size)
				return (E) queue[lastRet = cursor++];
			if(forgetMeNot != null){
				lastRet = -1;
				lastRetElt = forgetMeNot.poll();
				if(lastRetElt != null)
					return lastRetElt;
			}
			throw new NoSuchElementException();
		}
		
		public void remove(){
			if(exceptedModCount != modCount)
				throw new ConcurrentModificationException();
			if(lastRet != -1){
				E moved = priorityQueue.this.removeAt(lastRet);
				lastRet = -1;
				if(moved == null)
					cursor--;
				else{
					if (forgetMeNot == null)
						forgetMeNot = new ArrayDeque<>();
					forgetMeNot.add(moved);
				}
			}else if(lastRetElt != null){
				PriorityQueue.this.removeEq(lastRetElt);
				lastRetElt = null;
			}else{
				throw new IllegalStateException();
			}
			exceptedModCount = modCount;
		}	
	}
	@Override
	public int size(){
		return size;
	}
	
	/**
	 * ɾ�����ȶ����е�ȫ��Ԫ��
	 * ��������÷���֮��������л�Ϊ��
	 */
	public void clear(){
		modCount ++;
		for(int i=0; i<size; i++){
			queue[i] = null;
		}
		size = 0;
	}
	
	public E poll(){
		if(size == 0)
			return null;
		int s = --size;
		modCount ++;
		E result = (E) queue[0];
		E x = (E) queue[s];
		queue[s] = null;
		if(s != 0)
			siftDown(0,x);
		return result;
	}
	
	/**
	 * �Ӷ�����ɾ����i��Ԫ��
	 */
	private E removeAt(int i){
		assert i >= 0 && i < size; 
		modCount++;
		int s = --size;
		if(s == i)
			queue[i] = null;
		else{
			E moved = (E) queue[s];
			queue[s] = null;
			siftDown(i,moved);
			if(queue[i] == moved){
				siftUp(i,moved);
				if(queue[i] != moved)
					return moved;
			}
		}
		return null;
	}
	
	/**
	 * ��λ��K�ϲ���X��ά�ֶѲ���ͨ����X�������ƶ���ֱ�������ڻ��ߵ������ĸ��ڵ���߸��ڵ��ʱ��
	 * @param k ��ʾ�����λ��
	 * @param x ���������
	 */
	private void siftUp(int k, E x){
		if(comparator != null)
			siftUpUsingComparator(k,x);
		else
			siftUpComparable(k,x);
	}
	
	private void siftUpComparable(int k,E x){
		Comparable <? super E> key = (Comparable<? super E>) x;
		while( k > 0 ){
			int parent = (k -1) >>> 1;//>>>��ʾ����һλ
			Object e  = queue[parent];
			if(key.compareTo((E) e) >= 0)
				break;
			queue[k] = e;
			k = parent;
		}
		queue[k] = key;
	}
	
	private void siftUpUsingComparator(int k, E x){
		while(k > 0){
			int parent = (k - 1)>>>1;
			Object e = queue[parent];
			if(comparator.compare(x, (E) e) >= 0)
				break;
			queue[k] = e;
			k = parent;
		}
		queue[k] = x;	
	}
	
	
	/**
	 * ��λ��K�ϲ���X��ά�ֶѲ���ͨ����X�������ƶ���ֱ����С�ڻ��ߵ������ĺ��ӽڵ����Ҷ�ӽڵ��ʱ��
	 * @param k ��ʾ�����λ��
	 * @param x ���������
	 */
	private void siftDown(int k, E x){
		if(comparator != null)
			siftDownUsingComparator(k,x);
		else
			siftDownComparable(k,x);
	}
	
	private void siftDownComparable(int k,E x){
		Comparable<? super E> key = (Comparable<? super E >)x;
		int half = size >>> 1;//loop while a non-leaf
		while(k < half){
			int child = (k << 1) + 1;// assume left chlid is least
			Object c = queue[child];
			int right = child + 1;
			if(right < size && ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0 )
				c = queue[child = right];
			if(key.compareTo((E) c) <= 0)
				break;
			queue[k] = c;
			k = child ;
		}
		queue[k] = key;
	}
	
	private void siftDownUsingComparator(int k,E x){
		int half =  size >>> 1;
		while(k < half){
			int child  = (k << 1) + 1;
			Object c = queue[child];
			int right = child + 1;
			if(right < size && comparator.compare((E) c, (E) queue[right]) > 0)
				c= queue[child = right];
			if(comparator.compare(x, (E) c ) <= 0)
				break;
			queue[k] = c;
			k = child;	
		}
		queue[k] = x;
	}
	
	
	/**
	 * ����ȫ���Ͻ���һ������Ķ�
	 * �����ڵ���֮ǰ��Ԫ�ص�˳���޹�
	 */
	private void heapify(){
		for(int i = (size >>> 1) - 1; i >= 0; i--)
			siftDown(i, (E) queue[i]);
	}
	
	
	/**
	 * �Ƚ����������Դ˶��н������򣬻������������е������Ǹ���Ԫ�ص���Ȼ˳��
	 *@return comparator
 	 */
	public Comparator<? super E> comparator(){
	 return comparator;
	}
	
	
	/**
	 * ����ʵ����״̬���������л���
	 */
	private void writeObject(java.io.ObjectOutputStream s)
		throws java.io.IOException{
		//write out element count, and any hidden stuff
		s.defaultWriteObject();
		
		//write out array length, for compatibility with 1.5 version
		s.writeInt(Math.max(2, size + 1));
		
		//write out all elements in the "proper order";
		for(int i = 0; i< size; i++)
			s.writeObject(queue[i]);
	}
	
	/**
	 * �������ؽ�ʵ����Ҳ����˵�������л���
	 */
	private void readObject(java.io.ObjectInputStream s)
		throws java.io.IOException,ClassNotFoundException{
		//read in size , and any hidden stuff
		s.defaultReadObject();
		
		//read in(and discard) array length
		s.readInt();
		
		queue = new Object[size];
		
		//read in all elements
		for(int i = 0;i < size; i++){
			queue[i] = s.readObject();
			
		//elements are guaranteed to be in "proper order",but the
		//sepc has never explained what that might be
		heapify();
		}
	}
	
	
	

}
