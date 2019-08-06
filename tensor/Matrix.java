package tensor;

public interface Matrix extends Cloneable{
	Scalar getEntry(int row, int col); //11m 
	void setEntry(int row, int col, Scalar data); //11m		
	
	int getRow(); int getCol();	//13m
	
	Matrix clone() throws CloneNotSupportedException; //17m   
	
	Matrix add(Matrix mtx);         //22
	Matrix multR(Matrix mtx);		//23
	Matrix multL(Matrix mtx);		//23
	void mergeHorizontal(Matrix mtx); //32
	void mergeVertical(Matrix mtx);	//33
	
	
	Vector getRowVec(int index);	//34
	Vector getColVec(int index);	//35
	
	Matrix extract(int from1, int to1, int from2, int to2); //36
	
	Matrix minor(int rrow, int rcol); //37
	Matrix transpose(); //38
	double trace(); //39
	boolean isSquar(); //40
	boolean isUpperTri(); //41
	boolean isLowerTri();	//42
	boolean isIdentity();		//43
	boolean isNull();			//44
	
	void swapRow(int a, int b); //45
	void swapCol(int a, int b);	//46
	void multAtRow(int index, double value); //47   
	void multAtCol(int index, double value); //48	
	
	void addMultRow(int source, int mulIndex, double value); //49
	void addMultCol(int source, int mulIndex, double value); //50
	
	Matrix rref(); //51
	boolean isRREF(); //52
	double det();	//53
	Matrix inverse();	//54
	
} 
