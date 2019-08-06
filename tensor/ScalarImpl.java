package tensor;

class ScalarImpl implements Scalar {
	private Double scalar_;
	ScalarImpl(double data){
		scalar_=data;
	}
	 static Scalar requestScalar(Double data) {					//1: 값 지정하여 스칼라 생성 
		return new ScalarImpl(data);
	}
	 static Scalar requestScalarbyRandom(Double num1, Double num2) { //2: i-j 무작위 값 요소로 하는 스칼라 생
		 double randomValue=(Math.random()*(num2-num1+1)); 
		 return new ScalarImpl(randomValue);
	 }
	public Double getScalar() {		//11s: 스칼라 조회 
		return this.scalar_;
	}
	public void setScalar(Double data) { //11s: 스칼라 지정 
		scalar_=data;
	}
	 @Override 
	 public String toString() {						//14s 스칼라 객체값 출력 
		 return Double.toString(scalar_);
	 }
	 @Override 
	 public boolean equals(Object obj) {			//15s 스칼라 동등성 파악 
		 ScalarImpl p=(ScalarImpl)obj;
		 if (scalar_.doubleValue()==p.scalar_.doubleValue()) 
			 return true;
		 else 
			 return false; 
	 }
	 @Override
	 public int compareTo(Scalar o) {		//16 스칼라 대소 비교 
		 return (scalar_.doubleValue()<o.getScalar().doubleValue())?-1:((equals(o))?0:1);
	 }
	 @Override
	 public Object clone() throws CloneNotSupportedException{ //17s 스칼라 객체 복제 
		 ScalarImpl copy=(ScalarImpl)super.clone();
		 return copy;
	 } 
	 public void add(Scalar value) { 	//18 스칼라 덧셈 
		 scalar_+=value.getScalar();
	 }
	 public void mult(Scalar value) { //19 스칼라 곱
		 scalar_*=value.getScalar();
	 }
	 static Scalar add(Scalar a, Scalar b) {  //24 전달받은 두 스칼라 덧셈 
		 Scalar newScalar=new ScalarImpl(a.getScalar()+b.getScalar());
		 return newScalar;
	 }
	 static Scalar mult(Scalar a, Scalar b) { //25 전달받은 두 스칼라 곱셈 
		 Scalar newScalar=new ScalarImpl(a.getScalar()*b.getScalar());
		 return newScalar;
	 }
}
