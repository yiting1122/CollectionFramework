
/**
 * @ # this class Iterable write in 2014/5/13
 */


package com.util;
/**
 * iterable�����ݴ���������������ʵ�ָýӿڵ���ӵ�б����Ĺ��ܣ��Ӷ�ʹ��һ������ӵ�� foreach�Ĺ���
 * for-eachѭ���������κ�ʵ����Iterable�ӿڵĶ���һ������
 * @author yiting
 *
 * @param <T> ����������Ԫ�ص�����
 */
public interface Iterable<T> {
	
	/**
	 * ͨ���÷�����ȡһ������Iterator�ı����Ӷ���ͨ���ñ����ӶԼ��Ͻ��б���
	 * @return  iteraor����
	 */
	Iterator<T> iterator();
}
