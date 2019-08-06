package tensor;

public class NonInvertableMatrixException extends RuntimeException {
	public  NonInvertableMatrixException(){
		
	}
	public  NonInvertableMatrixException(String message) {
		super(message);
	}
}
