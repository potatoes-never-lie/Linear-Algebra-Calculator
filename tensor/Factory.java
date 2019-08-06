package tensor;
import java.io.*;


public class Factory {
	public static Scalar createScalar(Double data) {		//1
		return ScalarImpl.requestScalar(data);
	} 
	public static Scalar randomScalar(Double num1, Double num2){	//2    
		return ScalarImpl.requestScalarbyRandom(num1,num2);
	}
	public static Vector createVector(int size, Scalar data) {		//3
		return VectorImpl.requestVector(size,data);
	}
	public static Vector randomVector(int size,Scalar i, Scalar j) {		//4
		return VectorImpl.requestVectorbyRandom(size,i,j);
	}
	public static Vector arrayToVector(Scalar[] array_) {			//5
		return VectorImpl.requestVectorbyArray(array_);		
	}
	
	
	public static Matrix createMatrix(int m, int n, Scalar data) {			//6
		return MatrixImpl.requestMatrix(m,n,data);
	}
	public static Matrix randomMatrix(int m, int n, Scalar i, Scalar j) {		//7
		return MatrixImpl.requestMatrixbyRandom(m,n,i,j);
	}
	
	public static Matrix csvToMatrix(File file) throws FileNotFoundException, IOException  {			//8 
		return MatrixImpl.requestMatrixcsv(file); 
	}  
	
	public static Matrix arrayToMatrix(Scalar[][] array_) {		//9
		return MatrixImpl.requestMatrixbyArray(array_);
	}
	public static Matrix createIdentity(int n) {			//10
		return MatrixImpl.requestIdentityMatrix(n);
	}
	
	
}; 