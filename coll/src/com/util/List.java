/**
 * this write in 2014/5/14
 * @author yiting
 * ���ӿ���û��˵���Ľӿں�����������collection�ӿ��У���鿴�ӿں���˵������ת�� collection��
 * �ӿں�������˵���ľ��ǵ�ǰ�ӿ������ӵĺ������������������֣���ǰ�ӿ���������Щ���ܡ�
 * ���ֱ�ʾ������û�в���java�ı�ʾ������
 */
package com.util;

public interface List<E> extends Collection<E> {

	public int size();

	public boolean isEmpty();

	public boolean contains(Object o);

	public Iterator<E> iterator();

	public Object[] toArray();

	public <T> T[] toArry(T a[]);

	public boolean add(E e);

	public boolean remove(E e);

	public boolean containsAll(Collection<?> c);

	public boolean addAll(Collection<? extends E> c);

	public boolean removeAll(Collection<?> c);

	public boolean retainAll(Collection<?> c);

	public void clear();

	public boolean equals(Object o);

	public int hashCode();

	/**
	 * ��ȡһ�������е�һ��Ԫ��
	 * 
	 * @param index
	 *            Ԫ���ڼ����е�λ�ã����index�����˼��ϵĳ��ȣ��׳��쳣
	 * @return ���ؼ�����ָ��λ�õĶ���ֵ
	 * @throws IndexOutOfBoundsException
	 */

	public E get(int index);

	/**
	 * �滻indexλ�õĶ���
	 * 
	 * @param index ָ��λ��
	 * @param element Ҫ�滻�Ķ���
	 * @return �����滻���Ķ���
	 * @throws ClassCastException ,���ò���Ԫ�ص����Ͳ�����ת��ΪE����ʱ���׳��쳣
	 * @throws IndexOutOfBoundsException ��index�����˷�Χ
	 * @throws NullPointerException ��elementΪnullʱ������ǰ���ϲ��������null��ʱ��
	 * @throws IndexOutOfBoundsException    
	 */

	public E set(int index, E element);

	/**
	 * ��indexλ�ò���element���൱������Ĳ��룬�����Ԫ��Ҫ���ƣ��ڳ�λ��
	 * 
	 * @param index ����λ��
	 * @param element Ҫ����Ķ���
	 * @throws ClassCastException ���ò���Ԫ�ص����Ͳ�����ת��ΪE����ʱ���׳��쳣
	 * @throws IndexOutOfBoundsException ��index�����˷�Χ
	 * @throws NullPointerException ��elementΪnullʱ������ǰ���ϲ��������null��ʱ��
	 */

	public void add(int index, E element);

	/**
	 * ɾ��indexλ�õĶ���ͬʱ���ظ�ɾ���Ķ������������ɾ����ɾ��֮�� �ұߵ�Ԫ��Ҫ����
	 * 
	 * @param indexָ��λ��
	 * @return ɾ������
	 * @throws IndexOutOfBoundsException ��index�����˷�Χ
	 */

	public E remove(int index);

	/**
	 * ����ָ��������Ԫ���е�λ�ã�����һ��λ�ã�
	 * 
	 * @param o ָ������
	 * @return ����position�����list��û�иö����򷵻�-1��
	 * @throws ClassCastException ��ǰ������ת��ΪE���͵Ķ���
	 * @throws NullPointerException ��ǰ����o=null�����������ǲ��������nullԪ�� �׳��쳣
	 */
	public int indexOf(Object o);

	/**
	 * ������ö���O��ȵ����һ��λ�ã������п��ܴ��ڶ����ֵͬ
	 * 
	 * @param o ָ������
	 * @return ����position�����list������û�и�Ԫ���򷵻�-1����ô���Բ�ȡ�Ӻ���ǰ�ҵ���һ������
	 * @throws ClassCastException ��ǰ������ת��ΪE���͵Ķ���
	 * @throws NullPointerException ��ǰ����o=null�����������ǲ��������nullԪ�� �׳��쳣
	 */

	public int lastIndexOf(Object o);

	/**
	 * �������������������������������������������������������������������ʹ��Ҫ�ر�ע��
	 * 
	 * ��ȡ��ǰList�����һ���Ӽ����Ӽ���λ��������,��fromIndex=toIndexʱ����null ������ԭ��list�Ĵ�[fromIndex,
	 * toIndex)֮����һ���ֵ���ͼ��֮����˵����ͼ������Ϊʵ���ϣ����ص�list�ǿ�ԭ����list֧�ֵġ�
	 * ���ԣ����ԭ����list�ͷ��ص�list���ġ��ǽṹ���޸ġ�(non-structural changes)������Ӱ�쵽�˴˶Է���
	 * ��ν�ġ��ǽṹ���޸ġ�����ָ���漰��list�Ĵ�С�ı���޸ġ��෴���ṹ���޸ģ�ָ�ı���list��С���޸ġ�
	 * ����ԭ����list�����˽ṹ�޸ģ���ͨ��sublist���ص�list�������� һ��ʹ�÷���
	 *  list.subList(from,
	 * to).clear();
	 * 
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 * @throws IndexOutOfBoundsException ��fromIndex<0 ����toIndex>size;
	 * @throws IllegalArgumentException fromIndex>toIndex
	 * 
	 *             �������һ��ʵ��  ����ʵ�־Ϳ�֪���÷�����ʵ���صľ���list ֻ��������list���еļ�������
	 * SubList(AbstractList<E> list, int fromIndex, inttoIndex) { 
	 *           if (fromIndex < 0) throw newIndexOutOfBoundsException("fromIndex = " + fromIndex);
	 *           if(toIndex > list.size()) throw new IndexOutOfBoundsException("toIndex = " + toIndex);
	 *           if(fromIndex > toIndex) throw newIllegalArgumentException("fromIndex(" + fromIndex +
	 *             ") > toIndex(" + toIndex + ")"); 
	 *             l = list; 
	 *             offset = fromIndex;
	 *             size = toIndex - fromIndex;
	 *              expectedModCount =
	 *             l.modCount;
	 *             }
	 */
	
	public List<E> subList(int fromIndex, int toIndex);
	
	/**
	 * ����һ��ListIterator�����ӣ�ͨ���ñ����ӽ��б�����ListIterator�̳���Itarotor�����Iterator
	 * �����˸���ķ�����������ʵ����������� ͬʱ���Խ��и������ӵȹ���
	 * @return
	 */
	public ListIterator<E> listIterator();
	
	/**
	 * ����һ����ָ��λ�ÿ�ʼ��ListIterator������
	 * @param index  �����ӵĳ�ʼλ��
	 * @return
	 */
	public ListIterator<E> listIterator(int index);
	
	

}
