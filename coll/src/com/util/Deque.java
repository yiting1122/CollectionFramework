package com.util;
/**
 * 
 * @wirte by youyou 2014/5/15 in the evening
 * #############��ʾ �в����ĵط�
 * 
 */
public interface Deque<E> extends Queue<E> {
	/**
	 * ˫���С�˫�˶���
	 * @param e
	 */
	public void addFirst(E e);
	/**
	 * �ڲ���������������ǰ���£������˫���еĶ�β����һ���ض���Ԫ��
	 * 
	 * ��ʹ��һ�����������Ƶ�˫���У���ͨ�����õ�ʹ��link �����offerLast����// 
	 * 
	 * @param elemҪ�����Ԫ��
	 * @throw �����Ϊ�������������ƶ����ܲ���Ԫ�أ� ���׳�illegalStateException
	 * @throw ��������ض���Ԫ����ֹ�䱻���뵽˫�˶����У����׳�classCastException
	 * @throw �����������Ԫ���ǿ�ֵ�Ҵ�˫�˶��в������Ԫ�أ����׳�NullPointerException
	 * @throw ����������Ԫ�ص�һЩ����ʹ�䲻�ܲ��뵽���˫�˶����У�������illegalArgumentException
	 */
	
	public void addLast(E e);
	/**
	 * public void addLast(E e){
	 * 	addBefore(e, header);
	 * }
	 * ��˫�˶��е�ǰ�����Ԫ�ء�
	 *###################### @return ���Ԫ�سɹ����뵽���˫�˶����У��ͷ���true,����false####Ϊʲô�Ƿ����������
	 * @param elemҪ�����Ԫ�أ���List�в����ڵ� 
	 * 
	 **/
	public boolean offerFirst(E e);
	/**
	 * ��˫�˶��еĶ�β�����ض���Ԫ��
	 * @param e Ҫ�����Ԫ��
	* @return ���Ԫ�سɹ����뵽���˫�˶����У��ͷ���true,����false
	 */
	public boolean offerLast(E e);
	/**
	 * ��ȡ��ɾ�����˫�˶����еĵ�һ��Ԫ��
	 * public boolean offerLast(E e){
	 * 	addLast(e);
	 *  return true;
	 * }
	 * @return �������˫�˶��е�ͷ
	 * @throw �������Ϊ�գ����׳�noSuchElementException
	 */
	
	public E removeFirst();
	/**
	 * ��ȡ��ɾ�����˫�˶��е����һ��Ԫ��
	 * ############@return the tail of this deque##########�����ô����ȽϺã�
	 * @return �������˫�˶��е�β
	 * @throw �������Ϊ�գ����׳�noSuchElementException
	 */
	public E removeLast();
	/**
	 * ��ȡ��ɾ�����˫�˶��еĵ�һ��Ԫ��
	 * @return �������˫�˶��е�ͷ����������˶���Ϊ�գ�����NULL
	 */
	public E getFirst();
	/**
	 * ע�⣺��peekFirst������
	 * ��ȡ������ɾ������˫�˶��е����һ��Ԫ�أ�
	 * @return �������˫�˶��е�β
	 * @throw �������Ϊ�գ����׳�noSuchElementException
	 */
	public E getLast();
	/**
	 * ��ȡ(����ɾ��)��˫�˶��еĵ�һ��Ԫ��
	 * @return �������˫�˶��е�ͷ����������˶���Ϊ�գ�����NULL
	 */
	public E peekFirst();
	/**
	 * ��ȡ������ɾ������˫�˶��е����һ��Ԫ�أ�
	 * @return �������˫�˶��е�β
	 * �������Ϊ�գ��򷵻�null
	 */
	public E peekLast();
	/**
	 * ɾ����һ�γ����ڴ���˫�˶����е�ָ��Ԫ��
	 * �����������в��������Ԫ�أ���ô�Ͳ����ı�
	 * @Param oָ���Ķ���Ԫ�أ������˫�˶����д�ɾ��������������
	 * @return 
	 */
}
