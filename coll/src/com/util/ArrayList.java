package com.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class ArrayList<E> extends AbstractList<E> implements RandomAccess,
		List<E>, Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2168845844289964350L;
	private Object[] elementData;
	private int size;

	public ArrayList() {
		this(10);

	}

	public ArrayList(int initCapacity) {
		super();
		if (initCapacity < 0) {
			throw new IllegalArgumentException("initCapacity < 0");
		}
		this.elementData = new Object[initCapacity];
	}

	public ArrayList(Collection<E> c) {
		if (c == null) {
			throw new NullPointerException("Collection C is null");
		}
		elementData = c.toArray();
		size = elementData.length;
		if (elementData.getClass() != Object[].class) {
			elementData = Arrays.copyOf(elementData, size, Object[].class);
		}

	}

	public void trimToSize() {
		modCount++;
		int oldCapacity = elementData.length;
		if (size < oldCapacity) {
			elementData = Arrays.copyOf(elementData, size);
		}
	}

	public void ensureCapacity(int minCapacity) {
		if (minCapacity > 0) {
			ensureCapacityInternal(minCapacity);
		}
	}

	private void ensureCapacityInternal(int minCapacity) {
		modCount++;
		if (minCapacity - elementData.length > 0) {
			grow(minCapacity);
		}
	}

	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	private void grow(int minCapacity) {
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity + oldCapacity >> 1;
		if (newCapacity - minCapacity < 0) {
			newCapacity = minCapacity;
		}
		if (newCapacity - MAX_ARRAY_SIZE > 0) {
			newCapacity = hugeCapacity(minCapacity);
		}
		elementData = Arrays.copyOf(elementData, newCapacity);
	}

	private int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) {
			throw new OutOfMemoryError();
		}
		return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE
				: MAX_ARRAY_SIZE;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	@Override
	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(elementData[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		if (o == null) {
			for (int i = size - 1; i >= 0; i--) {
				if (elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = size - 1; i >= 0; i--) {
				if (o.equals(elementData[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	@Override
	protected Object clone() {
		// TODO Auto-generated method stub
		try {
			@SuppressWarnings("unchecked")
			ArrayList<E> v = (ArrayList<E>) super.clone();
			v.elementData = Arrays.copyOf(elementData, size);
			v.modCount = 0;
			return v;
		} catch (CloneNotSupportedException e) {
			// TODO: handle exception
			throw new InternalError();
		}
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elementData, size);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < size) {
			return (T[]) Arrays.copyOf(elementData, size, a.getClass());
		}
		System.arraycopy(elementData, 0, a, 0, size);
		if (a.length > size) {
			a[size] = null;
		}
		return a;
	}

	/**
	 * ������Ȩ�ޣ�ֻ����ͬһ��������������ʵ������arraylist�������øú���
	 * 
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unchecked")
	E elementData(int index) {
		return (E) elementData[index];
	}

	@Override
	public E get(int index) {
		rangeCheck(index);
		return (E) elementData[index];
	}

	@Override
	public boolean add(E e) {
		ensureCapacity(size + 1);
		elementData[size++] = e;
		return true;
	}

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		// ��Ԫ�������ƶ� �ճ�indexλ�á���������Ӳ���
		System.arraycopy(elementData, index, elementData, index + 1, size
				- index);
		elementData[index] = element;
		size++;
	}

	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		E oldValue = get(index);
		elementData[index] = element;
		return oldValue;

	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		modCount++;
		E oldValue = elementData(index);
		int numMoved = size - index - 1;
		if (numMoved > 0) {
			System.arraycopy(elementData, index + 1, elementData, index,
					numMoved);
		}
		elementData[--size] = null;
		return oldValue;
	}

	@Override
	public boolean remove(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (elementData[i] == null) {
					fastRemove(i);
					return true;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(elementData[i])) {
					fastRemove(i);
					return true;
				}
			}
		}
		return false;
	}

	private void fastRemove(int index) {
		this.modCount++;
		int numMoved = size - index - 1;
		if (numMoved > 0) {
			System.arraycopy(elementData, index + 1, elementData, index,
					numMoved);
		}
		elementData[--size] = null;
	}

	public void clear() {
		modCount++;
		for (int i = 0; i < size; i++) {
			elementData[i] = null; // ͨ����ֵΪnull��������ڴ��ͷŽ�����GC����������
		}
		size = 0;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		Object[] newValue = c.toArray();
		int numNew = newValue.length;
		ensureCapacity(size + numNew);
		System.arraycopy(newValue, 0, elementData, size, numNew);
		size += numNew;
		return numNew != 0;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		rangeCheckForAdd(index);
		Object[] newValue = c.toArray();
		int numNew = newValue.length;
		ensureCapacity(size + numNew);

		int numMoved = size - index;
		// ��index�����nummoved��Ԫ��������Ų��numNew��λ��
		if (numMoved > 0) {
			System.arraycopy(elementData, index, elementData, index + numNew,
					numMoved);
		}
		// ���µ�ֵ���뵽elementData
		System.arraycopy(newValue, 0, elementData, index, numNew);
		size += numNew;
		return true;
	}

	/**
	 * ��Ԫ�شӺ���ǰ�ƶ���Ȼ��ѿճ�����λ�ø���Ϊnull�������ᴥ��GC�������ж���Ļ���
	 */
	@Override
	protected void removeRange(int fromIndex, int toIndex) {
		this.modCount++;
		int numMoved = size - toIndex;
		System.arraycopy(elementData, toIndex, elementData, fromIndex, numMoved);
		int newSize = size - (toIndex - fromIndex);

		while (size != newSize) {
			elementData[--size] = null;
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return batchRemove(c, false);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return batchRemove(c, true);
	}

	/**
	 * ����ɾ������complementΪtrueʱ����������������ͬ�Ķ���Ϊfalseʱ������ ��ǰ�����е�Ԫ�أ���Ԫ��Ҳ�������뼯��c�У�
	 * 
	 * @param c
	 * @param complement
	 * @return
	 */

	private boolean batchRemove(Collection<?> c, boolean complement) {
		final Object[] elementData = this.elementData;
		int r = 0, w = 0;
		boolean modified = false;
		try {
			for (; r < size; r++) {
				if (c.contains(elementData[r]) == complement) {
					elementData[w++] = elementData[r];
				}
			}
		} finally {
			// ���ּ����ԣ���ʹc.contains�׳����쳣
			if (r != size) {
				// ���׳��쳣ʱ�Ѻ��滹û�д����Ԫ����ǰ�ƶ����������ѹ��
				System.arraycopy(elementData, r, elementData, w, size - r);
			}
			// wΪѹ�����β�ˣ������仹û�ﵽsize��������Ԫ�ظ�ֵΪnull��ͬʱҲ����
			// ��������������������Щ����Ļ���
			w += size - r;
			if (w != size) {
				for (int i = w; i < size; i++) {
					elementData[i] = null;
				}
				modCount += size - w;
				size = w;
				modified = true;
			}
		}
		return modified;
	}

	/**
	 * ͨ�������������� ����һ��Ҫ����size��length������size��Ԫ�صĸ�����length������ĳ���
	 * 
	 * @param s
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream s) throws IOException {
		int expectedModCount = modCount;
		s.defaultWriteObject();
		s.writeInt(elementData.length);

		for (int i = 0; i < size; i++) {
			s.writeObject(elementData[i]);
		}
		// д�����������жϣ��Ƿ��ж��̶߳���������˽ṹ�Եĸ��ģ���������˽ṹ�Ը���
		// ����д����������ģ�����ǰ��һ�£����Ա����׳��쳣
		if (modCount != expectedModCount) {
			throw new ConcurrentModificationException();
		}
	}

	private void readObject(ObjectInputStream s) throws IOException,
			ClassNotFoundException {
		s.defaultReadObject();
		int arrayLength = s.readInt();
		Object[] a = elementData = new Object[arrayLength];
		for (int i = 0; i < size; i++) {
			a[i] = s.readObject();
		}
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return listIterator(0);
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index: " + index);
		}
		return new ListItr(index);
	}

	private void rangeCheck(int index) {
		if (index >= size) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}

	private void rangeCheckForAdd(int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}

	private String outOfBoundsMsg(int index) {
		return "Index :" + index + ", size :" + size;
	}

	private class Itr implements Iterator<E> {
		int cursor;
		int lastRet = -1;
		int expectedModCount = modCount;

		@Override
		public boolean hasNext() {
			return cursor != size;
		}

		@Override
		public E next() {
			checkForComodification();
			int i = cursor;
			if (i >= size) {
				throw new NoSuchElementException();
			}
			Object[] elementData = ArrayList.this.elementData;
			if (i >= elementData.length) {
				throw new ConcurrentModificationException();
			}
			cursor = i + 1;
			return (E) elementData[lastRet = i];
		}

		@Override
		public void remove() {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			checkForComodification();
			try {
				ArrayList.this.remove(lastRet);
				cursor = lastRet;
				lastRet = -1;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}

		}

		final void checkForComodification() {
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}

		}

	}

	private class ListItr extends Itr implements ListIterator<E> {

		public ListItr(int index) {
			super();
			cursor = index;
		}

		@Override
		public boolean hasPrevious() {
			return cursor != 0;
		}

		@Override
		public int previousIndex() {
			return cursor - 1;
		}

		@Override
		public E previous() {
			checkForComodification();
			int i = cursor - 1;
			if (i < 0)
				throw new NoSuchElementException();
			Object[] elementData = ArrayList.this.elementData;
			if (i >= elementData.length)
				throw new ConcurrentModificationException();
			cursor = i;
			return (E) elementData[lastRet = i];
		}

		@Override
		public int nextIndex() {
			return cursor;
		}

		@Override
		public void set(E e) {
			if (lastRet < 0)
				throw new IllegalStateException();
			checkForComodification();

			try {
				ArrayList.this.set(lastRet, e);
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}

		@Override
		public void add(E e) {
			checkForComodification();

			try {
				int i = cursor;
				ArrayList.this.add(i, e);
				cursor = i + 1;
				lastRet = -1;
				expectedModCount = modCount;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}

	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		subListRangeCheck(fromIndex, toIndex, size);
		return new SubList(this, 0, fromIndex, toIndex);
	}

	static void subListRangeCheck(int fromIndex, int toIndex, int size) {
		if (fromIndex < 0)
			throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
		if (toIndex > size)
			throw new IndexOutOfBoundsException("toIndex = " + toIndex);
		if (fromIndex > toIndex)
			throw new IllegalArgumentException("fromIndex(" + fromIndex
					+ ") > toIndex(" + toIndex + ")");
	}

	private class SubList extends AbstractList<E> implements RandomAccess {

		private final AbstractList<E> parent;
		private final int parentoffset;
		private final int offset;
		int size;

		public SubList(AbstractList<E> parent, int offset, int fromIndex,
				int toIndex) {
			this.parent = parent;
			this.parentoffset = fromIndex;
			this.offset = offset + fromIndex;
			this.size = toIndex - fromIndex;
			this.modCount = ArrayList.this.modCount;
		}

		@Override
		public void add(int index, E element) {
			rangeCheck(index);
			checkForComodification();
			this.parent.add(parentoffset + index, element);
		}

		@Override
		public E get(int index) {
			rangeCheck(index);
			checkForComodification();
			return ArrayList.this.elementData(offset + index);
		}

		@Override
		public int size() {
			checkForComodification();
			return this.size;
		}

		@Override
		public E set(int index, E element) {
			rangeCheck(index);
			checkForComodification();
			E oldValue = ArrayList.this.elementData(offset + index);
			ArrayList.this.elementData[index + offset] = element;
			return oldValue;
		}

		@Override
		public E remove(int index) {
			rangeCheck(index);
			checkForComodification();
			E result = parent.remove(index + offset);
			this.modCount = parent.modCount;
			this.size--;
			return result;
		}

		@Override
		protected void removeRange(int fromIndex, int toIndex) {
			checkForComodification();
			parent.removeRange(parentoffset + fromIndex, parentoffset + toIndex);
			this.modCount = parent.modCount;
		}

		/**
		 * �Ѽ���c�����ĩβ
		 * 
		 * @param c
		 * @return
		 */
		@Override
		public boolean addAll(Collection<? extends E> c) {
			return addAll(this.size(), c);
		}

		@Override
		public boolean addAll(int index, Collection<? extends E> c) {
			rangeCheck(index);
			int cSize = c.size();
			if (cSize == 0) {
				return false;
			}
			checkForComodification();
			parent.addAll(parentoffset + index, c);
			this.modCount = parent.modCount;
			this.size += cSize;
			return true;

		}

		@Override
		public List<E> subList(int fromIndex, int toIndex) {
			subListRangeCheck(fromIndex, toIndex, size);
			return new SubList(this, offset, fromIndex, toIndex);
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @return
		 */
		@Override
		public Iterator<E> iterator() {
			// TODO Auto-generated method stub
			return listIterator();
		}

		@Override
		public ListIterator<E> listIterator(final int index) {
			checkForComodification();
			rangeCheck(index);
			final int offset = this.offset;
			return new ListIterator<E>() {
				int cursor = index;
				int lastRet = -1;
				int expectedModCount = ArrayList.this.modCount;

				public boolean hasNext() {
					return cursor != SubList.this.size;
				}

				@SuppressWarnings("unchecked")
				public E next() {
					checkForComodification();
					int i = cursor;
					if (i >= SubList.this.size)
						throw new NoSuchElementException();
					Object[] elementData = ArrayList.this.elementData;
					if (offset + i >= elementData.length)
						throw new ConcurrentModificationException();
					cursor = i + 1;
					return (E) elementData[offset + (lastRet = i)];
				}

				public boolean hasPrevious() {
					return cursor != 0;
				}

				@SuppressWarnings("unchecked")
				public E previous() {
					checkForComodification();
					int i = cursor - 1;
					if (i < 0)
						throw new NoSuchElementException();
					Object[] elementData = ArrayList.this.elementData;
					if (offset + i >= elementData.length)
						throw new ConcurrentModificationException();
					cursor = i;
					return (E) elementData[offset + (lastRet = i)];
				}

				public int nextIndex() {
					return cursor;
				}

				public int previousIndex() {
					return cursor - 1;
				}

				public void remove() {
					if (lastRet < 0)
						throw new IllegalStateException();
					checkForComodification();

					try {
						SubList.this.remove(lastRet);
						cursor = lastRet;
						lastRet = -1;
						expectedModCount = ArrayList.this.modCount;
					} catch (IndexOutOfBoundsException ex) {
						throw new ConcurrentModificationException();
					}
				}

				public void set(E e) {
					if (lastRet < 0)
						throw new IllegalStateException();
					checkForComodification();

					try {
						ArrayList.this.set(offset + lastRet, e);
					} catch (IndexOutOfBoundsException ex) {
						throw new ConcurrentModificationException();
					}
				}

				public void add(E e) {
					checkForComodification();

					try {
						int i = cursor;
						SubList.this.add(i, e);
						cursor = i + 1;
						lastRet = -1;
						expectedModCount = ArrayList.this.modCount;
					} catch (IndexOutOfBoundsException ex) {
						throw new ConcurrentModificationException();
					}
				}

				final void checkForComodification() {
					if (expectedModCount != ArrayList.this.modCount)
						throw new ConcurrentModificationException();
				}
			};
		}

	}

	private void checkForComodification() {
		if (ArrayList.this.modCount != this.modCount)
			throw new ConcurrentModificationException();
	}

}
