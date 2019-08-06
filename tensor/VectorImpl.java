package tensor;
import java.util.List;
import java.util.ArrayList;


class VectorImpl implements Vector{
	private List<Scalar> vector_;
	VectorImpl(int size){
		vector_=new ArrayList<Scalar>(size);
	}
	static Vector requestVector(int size, Scalar data) {		//3 지정한 하나의 값을 모든 요소로 하는 벡터 생성 
		VectorImpl newVector=new VectorImpl(size);
		for (int i=0;i<size;i++) {
			newVector.vector_.add(new ScalarImpl(data.getScalar()));
		}
		return newVector;
	}
	static Vector requestVectorbyRandom(int size, Scalar i, Scalar j) { //4 i-j 무작위 값 요소로 하는 벡터 생성 
		VectorImpl newVector=new VectorImpl(size);
		for (int k=0;k<size;k++) {
			Double randomValue=(Math.random()*(j.getScalar()-i.getScalar()+1));
			newVector.vector_.add(new ScalarImpl(randomValue));
		}
		return newVector;		
	}
	static Vector requestVectorbyArray(Scalar[] array) {	//5 1차원 배열로부터 벡터 생성 
		VectorImpl newVector=new VectorImpl(array.length);
		for (int i=0;i<array.length;i++) {
			newVector.vector_.add(new ScalarImpl(array[i].getScalar()));
		}
		return newVector;
	}
	public Scalar getVec(int index) { //11v 벡터 특정 위치 조회 
		index-=1;
		return vector_.get(index);
	}
	public void setVec(int index, Scalar data) { //11v 벡터 특정 위치 지정 
		index-=1;
		vector_.set(index, new ScalarImpl(data.getScalar()));
	}
	public int size() {		//13v 벡터 크기 정보 조회 
		return vector_.size();
	}
	@Override
	public String toString() {							//14v 벡터 객체 출력 
		String results="| ";
		for (Scalar i : vector_) {
			results+=(i.toString()+" ");
		}
		results+="|";
		return results;
	 }
	@Override
	public boolean equals(Object obj) {				//15v 벡터 객체 동등성 파악  
		VectorImpl p=(VectorImpl)obj;
		if (vector_.size()==p.vector_.size()) {
			for (int i=1;i<=vector_.size();i++) {
				if (getVec(i).getScalar().doubleValue()!=p.getVec(i).getScalar().doubleValue())
					return false;
			}
			return true;
		}
		else
			return false;
	}
	 @Override																
	 public Object clone() throws CloneNotSupportedException{		//17v  벡터 객체 복
		 VectorImpl clone=new VectorImpl(vector_.size());
		 for (Scalar i : vector_) {
			 clone.vector_.add((Scalar)i.clone());
		 }
		 return clone; 
	 }  
	 public void add(Vector value) throws SizeIncompatibleException  {  //20 다른벡터와 덧셈 
		if (size()!=value.size()) {throw new SizeIncompatibleException("벡터의 길이가 같지 않습니다");}
		Scalar newScalar;
		for (int i=1;i<=vector_.size(); i++) {
			newScalar=new ScalarImpl(value.getVec(i).getScalar());
			newScalar.add(vector_.get(i-1));
			vector_.set(i-1, newScalar);
		}
	 }
	public void mult(Scalar value) {  //21 다른 스칼라와 곱셈 
		Scalar newScalar;
		for (int i=1;i<=vector_.size();i++) {
			newScalar=new ScalarImpl(value.getScalar());
			newScalar.mult(vector_.get(i-1));
			vector_.set(i-1, newScalar);
		}
	}
	
	static Vector add(Vector a, Vector b) throws SizeIncompatibleException{		 //26 전달받은 두 벡터 덧셈 
		if (a.size()!=b.size()) {throw new SizeIncompatibleException("벡터의 길이가 같지 않습니다");}
		Vector newVector=Factory.createVector(a.size(),new ScalarImpl(0.0));
		Scalar newScalar;
		for (int i=1; i<=newVector.size();i++) {
			newScalar=ScalarImpl.add(a.getVec(i), b.getVec(i));
			newVector.setVec(i, newScalar);
		}
		return newVector; 
	}
	
	static Vector mult(Vector a, Scalar b) {  //27 전달받은 스칼라와 벡터 곱셈 
		Vector newVector=Factory.createVector(a.size(),new ScalarImpl(0.0));
		Scalar newScalar;
		for (int i=1; i<=newVector.size();i++) {
			newScalar=ScalarImpl.mult(a.getVec(i), b);
			newVector.setVec(i, newScalar);
		}
		return newVector; 
	}
	
	public Matrix get1xn() {		//31 1xn 행렬 반환 
		Scalar zero=new ScalarImpl(0);
		Matrix newMatrix=Factory.createMatrix(1, size(), zero);
		for (int i=1; i<=size(); i++) {
			newMatrix.setEntry(1,i,vector_.get(i-1));
		}
		return newMatrix;
	
	}
	public Matrix getnx1() {  //30 nx1 행렬 반환 
		Scalar zero=new ScalarImpl(0);
		Matrix newMatrix=Factory.createMatrix(size(), 1, zero);
		for (int i=1; i<=size(); i++) {
			newMatrix.setEntry(i,1,vector_.get(i-1));
		}	
		return newMatrix;
	}

	
}
