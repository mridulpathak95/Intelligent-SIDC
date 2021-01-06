import java.util.ArrayList;
import java.util.List;

public class CustomHashTable<K, V> {

	private int size = 10;
	private ArrayList<Entry<K, V>> bucketdata;
	private int numOfBuckets;
/**
 * 
 * @author mridulpathak
 *
 * @param <K>
 * @param <V>
 */
	static class Entry<K, V> {
		K key;
		V value;
		Entry<K, V> nextValue;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	

	public CustomHashTable() {

		this.size = 0;
		this.numOfBuckets = 10;
		this.bucketdata = new ArrayList<Entry<K, V>>();
		for (int i = 0; i < numOfBuckets; i++) {
			bucketdata.add(null);
		}
	}
	/**
	 * 
	 * @return
	 */

	public List<K> getAllKeys() {
		List<K> list = new ArrayList<K>();
		for (int i = 0; i < numOfBuckets; i++) {
			Entry<K, V> head = bucketdata.get(i);
			while (head != null) {
				list.add(head.key);
				head = head.nextValue;
			}
		}
		return list;
	}
	/**
	 * 
	 * @return
	 */

	public int size() {
		return this.size;
	}
	/**
	 * 
	 * @param key
	 * @return
	 */

	private int getBucketIndex(K key) {
		int hashCode = key.hashCode();
		int index = hashCode % numOfBuckets;
		return index;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */

	public V remove(K key) {
		int bucketIndex = getBucketIndex(key);
		Entry<K, V> head = bucketdata.get(bucketIndex);
		Entry<K, V> prev = null;
		while (head != null) {
			if (head.key.equals(key))
				break;
			prev = head;
			head = head.nextValue;
		}

		if (head == null)
			return null;
		size--;
		if (prev != null)
			prev.nextValue = head.nextValue;
		else
			bucketdata.set(bucketIndex, head.nextValue);

		return head.value;
	}
	/**
	 * 
	 * @param key
	 * @return
	 */

	public V get(K key) {
		int bucketIndex = getBucketIndex(key);
		Entry<K, V> head = bucketdata.get(bucketIndex);
		while (head != null) {
			if (head.key.equals(key)) {
				return head.value;
			}
			head = head.nextValue;
		}
		return null;
	}
	/**
	 * 
	 * @param key
	 * @return
	 */

	public K nextKey(K key) {
		int bucketIndex = getBucketIndex(key);
		Entry<K, V> head = bucketdata.get(bucketIndex);
		while (head != null) {
			if (head.key.equals(key)) {
				if (head.nextValue != null)
					return head.nextValue.key;
				else
					return null;
			}
			head = head.nextValue;
		}
		return null;

	}
	/**
	 * 
	 * @param key
	 * @return
	 */

	public K prevKey(K key) {

		List<K> keys = getAllKeys();
		K prev = null;
		for (K k : keys) {
			if (k == key)
				return prev;
			else
				prev = k;
		}
		return prev;

	}
	/**
	 * 
	 * @param key
	 * @param value
	 */

	public void add(K key, V value) {
		int bucketIndex = getBucketIndex(key);
		Entry<K, V> head = bucketdata.get(bucketIndex);

		while (head != null) {
			if (head.key.equals(key)) {
				head.value = value;
				return;
			}
			head = head.nextValue;
		}

		size++;

		head = bucketdata.get(bucketIndex);
		Entry<K, V> newNode = new Entry<K, V>(key, value);
		newNode.nextValue = head;
		bucketdata.set(bucketIndex, newNode);

		// If load factor goes beyond threshold, then
		// double hash table size
		if ((1.0 * size) / numOfBuckets >= 0.7) {
			ArrayList<Entry<K, V>> temp = bucketdata;
			bucketdata = new ArrayList<>();
			numOfBuckets = 2 * numOfBuckets;
			size = 0;
			for (int i = 0; i < numOfBuckets; i++)
				bucketdata.add(null);

			for (Entry<K, V> headNode : temp) {
				while (headNode != null) {
					add(headNode.key, headNode.value);
					headNode = headNode.nextValue;
				}
			}
		}
	}
	/**
	 * 
	 * @param key1
	 * @param key2
	 * @return
	 */

	public List<Long> rangeKey(K key1, K key2) {
		boolean inRange = false;
		List<Long> keys = (List<Long>) getAllKeys();
		List<Long> filtered = new ArrayList();
		for (Long k : keys) {
			if (k >= (Long) key1 && k <= (Long) key2) {
				filtered.add(k);
			}
		}
		return filtered;

	}
	
}