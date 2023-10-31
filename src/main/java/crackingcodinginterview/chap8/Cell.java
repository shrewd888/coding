package crackingcodinginterview.chap8;

public class Cell<K,V>
{
	K key;
	V value;

	public Cell(K k, V v) {
		this.key = k;
		this.value = v;
	}

	public boolean equivalent(K k)
	{
		return this.key.equals(k);
	}

	public K getKey()
	{
		return this.key;
	}

	public V getValue()
	{
		return this.value;
	}
}
