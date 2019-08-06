package tensor;

public interface Vector extends Cloneable{
	Scalar getVec(int dim); //11v 
	void setVec(int dim, Scalar s); //11v
	int size();	//13v
	Object clone() throws CloneNotSupportedException; //17v
	void add(Vector a); //20
	void mult(Scalar s); //21
	Matrix get1xn(); //30
	Matrix getnx1(); //31
}	

