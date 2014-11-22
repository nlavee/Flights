import java.util.Comparator;

public class vertexComparator<T> implements Comparator<Vertex<T>>{
	
	Comparator<T> dataComp;
	
	public vertexComparator(Comparator<T> data) {
		dataComp = data;
	}

	@Override
	public int compare(Vertex<T> o1, Vertex<T> o2) {
		return dataComp.compare(o1.getCityInQuestion(), o2.getCityInQuestion());
	}
}
