package com.util;
import java.io.Serializable;
import java.util.Arrays;



public class ArrayList<E> extends AbstractList<E> implements RandomAccess,List<E>,
		Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2168845844289964350L;
	private Object[] elementData;
	private int size;
	
	public ArrayList(){
		this(10);
		
	}
	
	public ArrayList(int initCapacity){
		super();
		if(initCapacity<0){
			throw new IllegalArgumentException("initCapacity < 0");
		}
		this.elementData=new Object[initCapacity];
	}
	
	public ArrayList(Collection<E> c){
		if(c==null){
			throw new NullPointerException("Collection C is null");
		}
		elementData=c.toArray();
		size=elementData.length;
		if(elementData.getClass()!=Object[].class){
			elementData=Arrays.copyOf(elementData, size,Object[].class);
		}
		
	}
	
	public void trimToSize(){
		modCount++;
		int oldCapacity=elementData.length;
		if(size<oldCapacity){
			elementData=Arrays.copyOf(elementData, size);
		}
	}
	
	public void ensureCapacity(int minCapacity){
		if(minCapacity>0){
			ensureCapacityInternal(minCapacity);
		}
	}
	
	private void ensureCapacityInternal(int minCapacity){
		modCount++;
		if(minCapacity-elementData.length>0){
			grow(minCapacity);
		}
	}
	
	private static final int MAX_ARRAY_SIZE=Integer.MAX_VALUE-8;
	private void grow(int minCapacity){
		int oldCapacity=elementData.length;
		int newCapacity=oldCapacity+oldCapacity>>1;
		if(newCapacity-minCapacity<0){
			newCapacity=minCapacity;
		}
		if(newCapacity-MAX_ARRAY_SIZE>0){
			newCapacity=hugeCapacity(minCapacity);
		}
		elementData=Arrays.copyOf(elementData, newCapacity);
	}
	
	private int hugeCapacity(int minCapacity){
		if(minCapacity<0){
			throw new OutOfMemoryError();
		}
		return (minCapacity>MAX_ARRAY_SIZE)?Integer.MAX_VALUE:MAX_ARRAY_SIZE;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean isEmpty(){
		return size==0;
	}
	
	@Override 
	public boolean contains(Object o){
		return indexOf(o)>=0;
	}
	
	@Override
	public int indexOf(Object o){
		if(o==null){
			for(int i=0;i<size;i++){
				if(elementData[i]==null){
					return i;
				}
			}
		}else {
			for(int i=0;i<size;i++){
				if(o.equals(elementData[i])){
					return i;
				}
			}
		}
		return -1;
	}

	
	@Override 
	public int lastIndexOf(Object o){
		if(o==null){
			for(int i=size-1;i>=0;i--){
				if(elementData[i]==null){
					return i;
				}
			}
		}else {
			for(int i=size-1;i>=0;i--){
				if(o.equals(elementData[i])){
					return i;
				}
			}
		}
		return -1;
	}
	
	
	@Override
	protected Object clone()   {
		// TODO Auto-generated method stub
		try {
			@SuppressWarnings("unchecked")
			ArrayList<E> v=(ArrayList<E>)super.clone();
			v.elementData=Arrays.copyOf(elementData, size);
			v.modCount=0;
			return v;
		} catch (CloneNotSupportedException e) {
			// TODO: handle exception
			throw new InternalError();
		}
	}
	
	@Override
	public Object[] toArray(){
		return Arrays.copyOf(elementData, size);
	}
	

	@Override
	public <T> T[] toArray(T[] a){
		if(a.length<size){
			return (T[])Arrays.copyOf(elementData, size,a.getClass());
		}
		System.arraycopy(elementData, 0, a, 0, size);
		if(a.length>size){
			a[size]=null;
		}
		return a;
	}
	
	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return (E) elementData[index];
	}

	

	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return super.add(e);
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return super.set(index, element);
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return super.remove(index);
	}

	

	
	
	

}
