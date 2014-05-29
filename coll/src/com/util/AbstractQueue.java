package com.util;
import java.util.*;
/**
 * 
 * @author youyou; write in 2014/5/28
 * ������ṩ��һЩ��Ҫ��ʵ��
 * ͨ�����ӵķ���Ҳ�ᱻ����
 */
public class AbstractQueue<E> extends AbstractCollection<E> implements Queue<E>{

	/**
	 * ʹ�����๹�캯��
	 */
	protected AbstractQueue(){	
	}
	
	
	/**
	 * �ڲ�Υ�������������Ƶ�ǰ���£�����ָ����Ԫ��
	 * �������ɹ��ͷ���true,����ʧ���׳��쳣
	 * @param e �����Ԫ��
	 */
	public boolean add(E e){
		if(offer(e))
			return true;
		else
			throw new IllegalStateException("Queue full");
	}
	
	
	/**
	 * ��ȡ��ɾ�������е�ͷ
	 * @return ���ض��е�ͷ
	 * @throws �������Ϊ�գ����׳��쳣
	 */
	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * ��ȡ��������ɾ�������е�ͷԪ��
	 * @return ���ض��е�ͷԪ��
	 * @throws �������Ϊ�����׳��쳣
	 */
	@Override
	public E element() {
		// TODO Auto-generated method stub
		E x = peek();
		if(x != null)
			return x;
		else 
			throw new NoSuchElementException();	
	}
	
	/**
	 * �Ӵ˶�����ɾ�����е�Ԫ��
	 * �������������֮�󣬶��л�Ϊ��
	 */
	public void clear(){
		while(poll() != null)
			;
	}
	
	/**
	 * ��ָ�����ϵ�����Ԫ����ӵ��˶���
	 * ���⣬�ò�������Ϊ��δ����Ĳ���ʱ�����ָ���ļ��ϱ��޸ģ�ͬʱ����Ҳ�ڽ���
	 * ���ʵ�ֱ���ָ���ļ��ϣ��������������ص�ÿһ��Ԫ����ӵ��˶��С�
	 */
	public boolean addAll(Collection <? extends E> c){
		if(c == null)
			throw new NullPointerException();
		if(c == this)
			throw new IllegalArgumentException();
		boolean modified = false;
		for(E e : c)
			if(add(e))
				modified = true;
		return modified;
			
	}
	
	@Override
	public boolean offer(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}


}
