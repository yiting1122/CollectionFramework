/**
 * AbstractCollection.java writed in 2014/5/18
 * @author yiting
 * AbstractCollection������ʵ����Collection�ӿڣ��ṩ�˽ӿ��з�����Ĭ��ʵ��
 * ͬʱ�����һ��tostring������Ĭ��ʵ�֡�
 * ����̳иó�����ļ�����һ�������Ըı�ļ��ϣ���ô�ü���ֻ��Ҫʵ��size������Iterator��hasnext������next�������ɡ�
 * ����̳и���ļ�����һ�����Ըı�ļ��ϣ��������������ϱ�������override add������ʵ��Iterator�ӿڷ����е�remove����
 * 
 * �Ƽ�Ϊÿ���������ṩһ��protected �޲����Ĺ��췽��������java��һ���淶��
 */


package com.util;

import java.util.Arrays;

public abstract class AbstractCollection<E> implements Collection<E> {

	/**
	 * �ó�����Ĺ��캯������Ϊprotected����������ֻ��������Ĺ��캯�����ܵ��ø÷�����
	 */
	protected AbstractCollection() {
	}

	public abstract int size();

	public abstract Iterator<E> iterator();

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size() == 0;
	}

	@Override
	/**
	 * �жϼ������Ƿ��ж���o
	 * �жϵ�ʱ��һ��Ҫ�����Ƿ�oΪnull�����oΪnull�ǲ��ܲ���equal�������бȽϵġ�
	 * @throws ClassCastException   {@inheritDoc}
	 * @throws NullPointerException {@inheritDoc}
	 */
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		Iterator<E> it = iterator();

		if (o == null) {
			while (it.hasNext()) {
				if (it.next() == null) {
					return true;
				}
			}
		} else {
			while (it.hasNext()) {
				// �˴�ǧ����ʹ��it.next().equals(o),��Ϊit.next���ǿ���Ϊ�� �����쳣
				if (o.equals(it.next())) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * ͨ���÷�����ȡ�ü��ϵ�һ�����飬�����鷵�ص������м����е�Ԫ��
	 * ��it.hasNextΪfalseʱ��ͨ������arrays.copy�Ӷ����Ա��⵼��copy�Ĺ�����
	 * ������nullԪ�أ��Ӷ����´���arays.copy����һ�����顣 �ü��ϵ�Ԫ�ؿ����Ǳ仯��
	 * ���ܱ��Ҳ���ܼ���,�ڲ���������size�����Ĵ�С�ǿ��ܱ仯�ģ������ڸ��� �����з��صĴ�С������iterator�����ĸ�����̬������
	 * 
	 */

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		Object[] objects = new Object[size()];
		Iterator<E> it = iterator();
		for (int i = 0; i < objects.length; i++) {
			if (!it.hasNext()) {
				return Arrays.copyOf(objects, i); // ѹ�������С
			}
			objects[i] = it.next();
		}
		return it.hasNext() ? finishToArray(objects, it) : objects;
	}
	
	
	


	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		int size=size();
		T[] r=a.length>size?a:(T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
		Iterator<E> it=iterator();
		for(int i=0;i<size;i++){
			if(!it.hasNext()){
				if(a!=r){
					return Arrays.copyOf(r, size);
				}
				r[i]=null;  //���������β�˼���nullԪ��
				return r;
			}
			r[i]=(T) it.next();
		}
		return it.hasNext()?finishToArray(r, it):r;
	}





	/**
	 * �����������ֵ���˴�ΪʲôҪ��ȥ8����Ϊ�е������������Ǯ���걣����һЩhead words �������outofMemoryError
	 */
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	/**
	 * �÷���ʵ��Ԫ�صĸ��ƣ��ܹ���̬����չT[]���������������i���µ�ǰλ��
	 * ͨ���ж�i��cap��ֵ�ж��Ƿ���Ҫ��������cap�������ٶ�Ϊcap*1.5+1����󷵻ص�ʱ�����
	 * ����û�����Ļ�����ѹ������֤��������nullԪ��
	 * @param r
	 * @param it
	 * @return
	 */
	private static <T> T[] finishToArray(T[] r, Iterator<?> it) {
		int i = r.length;
		while (it.hasNext()) {
			int cap = r.length;
			if (i == cap) {
				int newCap = cap + cap >> 1 + 1;
				if (newCap - MAX_ARRAY_SIZE > 0) {
					newCap = hugeCapacity(cap + 1);
				}
				r = Arrays.copyOf(r, newCap); // ���������С
			}
			r[i++] = (T) it.next();
		}
		return (i == r.length) ? r : Arrays.copyOf(r, i);
	}

	private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) {
			throw new OutOfMemoryError("required array size too largy");
		}
		return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE
				: MAX_ARRAY_SIZE;
	}



	@Override
	public  boolean add(E e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public  boolean remove(E e) {
		Iterator<E> it=iterator();
		if(e==null){
			while(it.hasNext()){
				if(it.next()==null){
					it.remove();
					return true;
				}
			}	
		}else{
			while(it.hasNext()){
				if(e.equals(it.next())){
					it.remove();
					return true;
				}
			}
		}
		return false;
		
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		for(Object e:c){
			if(!contains(e)){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		boolean modified=false;
		for(E e:c){
			if(add(e)){
				modified=true;
			}
		}
		return modified;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		Iterator<E> it=iterator();
		boolean modified=false;
		while(it.hasNext()){
			if(c.contains(it.next())){
				it.remove();
				modified=true;
			}
		}
		
		return modified;
	}

	@Override
	/**
	 * ������ǰ��������C��ͬ��Ԫ��
	 */
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		Iterator<E> it=iterator();
		boolean modified=false;
		while(it.hasNext()){
			if(!c.contains(it.next())){
				it.remove();
				modified=true;
			}
		}
		
		return modified;
	}

	@Override
	/**
	 * remove����next֮�� �����п��ܱ��쳣��
	 */
	public void clear() {
		Iterator<E> it=iterator();
		while(it.hasNext()){
			it.next();
			it.remove();
		}

	}
	
	@Override
	public String toString(){
		  Iterator<E> it = iterator();
	        if (! it.hasNext())
	            return "[]";

	        StringBuilder sb = new StringBuilder();
	        sb.append('[');
	        for (;;) {
	            E e = it.next();
	            sb.append(e == this ? "(this Collection)" : e);
	            if (! it.hasNext())
	                return sb.append(']').toString();
	            sb.append(',').append(' ');
	        }
	}

}
