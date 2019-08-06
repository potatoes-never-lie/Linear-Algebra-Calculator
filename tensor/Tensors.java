package tensor;

public class Tensors {
	public static Scalar add(Scalar a, Scalar b) {	 //24
		return ScalarImpl.add(a,b);
	}
	public static Scalar mult(Scalar a, Scalar b) {	//25
		return ScalarImpl.mult(a,b);
	}
	public static Vector add(Vector a, Vector b){ //26
		return VectorImpl.add(a,b);
	}
	public static Vector mult(Vector a, Scalar b){ //27
		return VectorImpl.mult(a,b);
	}
	
	public static Matrix add(Matrix a, Matrix b){		//28
		return MatrixImpl.add(a,b);
	}
	public static Matrix mult(Matrix a, Matrix b){		//29
		return MatrixImpl.mult(a,b);
	} 
	public static Matrix mergeHorizontal(Matrix a, Matrix b) {		//32
		return MatrixImpl.mergeHorizontal(a,b);
	}
	public static Matrix mergeVertical(Matrix a, Matrix b) {		//33
		return MatrixImpl.mergeVertical(a,b);
	}
	
}

