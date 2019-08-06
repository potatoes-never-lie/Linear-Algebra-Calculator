package tensor;

public interface Scalar extends Comparable<Scalar>, Cloneable {
	Double getScalar();						//12
	void setScalar(Double a);			//12
	Object clone() throws CloneNotSupportedException;	//17s
	void add(Scalar a); //18
	void mult(Scalar a); //19
} 
 