package heapAndPriorityQueues;

public class HeapData<T>{
	T data;
	double key;
	int heapIndex;
	
	public HeapData(T data, double key) {
		this.data = data;
		this.key = key;
	}

	public double getKey() {
		return key;
	}

	protected void setKey(double key) {
		this.key = key;
	}

	protected int getHeapIndex() {
		return heapIndex;
	}

	protected void setHeapIndex(int heapIndex) {
		this.heapIndex = heapIndex;
	}

	public T getData() {
		return data;
	}
	
	protected int getParent() {
		int res = (int) Math.floor(heapIndex/2);
		if(res > 0) return res;
		else return (Integer) null;
	}
	
	protected int getLeftChild() {
		return 2*heapIndex;
	}
	
	protected int getRightChild() {
		return 2*heapIndex + 1;
	}

	protected int compareTo(HeapData<T> o) {
		double firstKey = this.getKey();
		double secondKey = o.getKey();
		if(firstKey<secondKey) return -1;
		else if(firstKey>secondKey) return 1;
		else return 0;
	}
	
	public String toString() {
		if(data!= null) return data.toString()+" Key = " + this.key;
		else return "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HeapData other = (HeapData) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	
	
	
}
