package tensor;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
//import java.util.Arrays;

class MatrixImpl implements Matrix{
	private List<ArrayList<Scalar>> matrix_;
	int row, col;
	MatrixImpl(int m,int n){
		matrix_=new ArrayList<ArrayList<Scalar>>(m);
		for (int i=0; i<m; i++) {
			matrix_.add(new ArrayList<Scalar>(n));
		}
		row=m; col=n;
	}
	static Matrix requestMatrix(int m, int n, Scalar data) {		//6 지정한 하나의 값을 모든 요소로 하는 mxn 행렬 생성  
		MatrixImpl newMatrix=new MatrixImpl(m,n);
		for (int i=0;i<m;i++) {
			for (int j=0;j<n;j++) {
				newMatrix.matrix_.get(i).add(new ScalarImpl(data.getScalar()));
			}
		}
		return newMatrix;
	}
	static Matrix requestMatrixbyRandom(int m, int n, Scalar i, Scalar j) { //7 i-j 무작위 값 요소로 하는 mxn 행렬 생성 
		MatrixImpl newMatrix=new MatrixImpl(m,n);
		Double randomValue; 
		for (int row=0;row<m;row++) {
			for (int col=0;col<n;col++) {
				randomValue=(Math.random()*(j.getScalar()-i.getScalar()+1));
				newMatrix.matrix_.get(row).add(new ScalarImpl(randomValue));
			}
		}
		return newMatrix;
	}
	
	static Matrix requestMatrixcsv(File file){					//8 csv 파일 읽어들여 배열 생성 
		MatrixImpl newMatrix=null;	
		List<String[]> rowList=new ArrayList<String[]>();
		BufferedReader br=null;
		try { 
			File csv=file;
			br=new BufferedReader(new FileReader(csv));
			String line="";
			while((line=br.readLine())!=null) {
				String[] token=line.split(",");
				rowList.add(token);
			}
			br.close();
		}
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		catch (IOException e) {
			e.printStackTrace();
		}
		String[][] matrix=new String[rowList.size()][];
		for (int i=0; i<rowList.size(); i++) {
			String[] row=rowList.get(i);
			matrix[i]=row;
		}
		return newMatrix;
	}
	
	
	static Matrix requestMatrixbyArray(Scalar[][] array_) {  //9 2차원 배열로부터 mxn 행렬 생성  
		int row=array_.length;
		int column=array_[0].length;
		MatrixImpl newMatrix=new MatrixImpl(row,column);
		for (int i=0;i<row;i++) {
			for (int j=0;j<column;j++) {
				newMatrix.matrix_.get(i).add(new ScalarImpl(array_[i][j].getScalar()));
			}
		}
		return newMatrix;
	}
	static Matrix requestIdentityMatrix(int n) {  //10 단위행렬 생성 
		MatrixImpl newMatrix=new MatrixImpl(n,n);		
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				if (i==j)
					newMatrix.matrix_.get(i).add(new ScalarImpl(1));
				else
					newMatrix.matrix_.get(i).add(new ScalarImpl(0));
			} 
		}
		return newMatrix;
	} 
	public Scalar getEntry(int row, int col) {	//11m 특정 위치 조회 
		row-=1; col-=1;
		return matrix_.get(row).get(col);
	}
	public void setEntry(int row, int col, Scalar data) {		//11m 특정 위치 지정 
		row-=1; col-=1;
		matrix_.get(row).set(col, new ScalarImpl(data.getScalar()));
	}
	
	public int getRow() {		//13m 행렬 행 크기 조회 
		return row;
	}
		
	public int getCol() {		//13m 행렬 열 크기 조회 
		return col;
	}
	@Override
	public String toString() {					//14m  행렬 객체 출력 
		String results="";
		results+="\n";
		for (int i=0; i<row; i++) {
			results+="|";
			for (Scalar j: matrix_.get(i)) {
				results+=(j.toString()+"|");
			}
			results+="\n";
		}
		return results;
	 }
	@Override 
	public boolean equals(Object obj) {				//15m 행렬 객체 동등성 파악 
		MatrixImpl p=(MatrixImpl)obj;
		if ((matrix_.size() == p.matrix_.size()) && (matrix_.get(0).size()==p.matrix_.get(0).size())) {
			for (int i=0;i<row;i++) {
				for (int j=0;j<col;j++) {
					if (matrix_.get(i).get(j).getScalar().doubleValue()!=p.matrix_.get(i).get(j).getScalar().doubleValue())
						return false;
				}
			}
			return true;
		}
		else
			return false;
	}
	 @Override																
	 public Matrix clone() throws CloneNotSupportedException{		//17m 행렬 객체 복제 
		 MatrixImpl clone=new MatrixImpl(this.row,this.col);
		 for (int i=0; i<row; i++) {
			 for (Scalar j : matrix_.get(i)) {
				 clone.matrix_.get(i).add((Scalar)j.clone());
			 }
		 }
		 return clone;
	 } 
	 public Matrix add(Matrix mtx) throws SizeIncompatibleException{  //22 다른 행렬과 덧셈 
		if ((row!=mtx.getRow()) || (col!=mtx.getCol())) {throw new SizeIncompatibleException("두 행렬이 크기가 다릅니다");}
		Scalar newScalar;
		for (int i=1;i<=row;i++) {
			for (int j=1;j<=col;j++) {
				newScalar=Tensors.add(this.getEntry(i,j), mtx.getEntry(i,j));
				setEntry(i,j,newScalar);
			}
		}
		return this;
	 }
	 public Matrix multR(Matrix mtx) throws SizeIncompatibleException {	//23 다른 행렬이 오른쪽 행렬로써 곱해지는 곱셈 
		 if (getCol()!=mtx.getRow()) {throw new SizeIncompatibleException("연산 할 수 없는 행렬입니다");}
		 int i,j,k;
		 MatrixImpl newMatrix=new MatrixImpl(row,mtx.getCol());
		 Scalar temp; ScalarImpl sum;
		 for (i=1;i<=row; i++) {
			for (j=1; j<=mtx.getCol(); j++) {
				sum=new ScalarImpl(0.0);
				 for (k=1;k<=col;k++) {
					 	temp=Tensors.mult(getEntry(i,k), mtx.getEntry(k, j));
					 	sum.add(temp);
				 }
				 newMatrix.matrix_.get(i-1).add(sum);
			}
		} 
			matrix_=new ArrayList<ArrayList<Scalar>>(newMatrix.getRow());
			for (i=0; i<newMatrix.getRow(); i++) {
				matrix_.add(new ArrayList<Scalar>(newMatrix.getCol()));
			}
			row=newMatrix.getRow(); col=newMatrix.getCol();
			for (i=1;i<=row;i++) {
				for (j=1;j<=col;j++) {
					matrix_.get(i-1).add(new ScalarImpl(newMatrix.getEntry(i,j).getScalar()));
				}
			}
			return this;

	} 
	 
	 public Matrix multL(Matrix mtx) throws SizeIncompatibleException {  //23 다른 행렬이 왼쪽 행렬로써 곱해지는 곱셈 
		 if (getRow()!=mtx.getCol()) {throw new SizeIncompatibleException("연산 할 수 없는 행렬입니다");}
		 int i,j,k;
		 MatrixImpl newMatrix=new MatrixImpl(mtx.getRow(),col);
		 Scalar temp; ScalarImpl sum;
		 for (i=1;i<=mtx.getRow(); i++) {
			for (j=1; j<=col; j++) {
				sum=new ScalarImpl(0.0);
				 for (k=1;k<=mtx.getCol();k++) {
					 	temp=Tensors.mult(mtx.getEntry(i,k), getEntry(k, j));
					 	sum.add(temp);
				 }
				 newMatrix.matrix_.get(i-1).add(sum);
			}
		 } 
			matrix_=new ArrayList<ArrayList<Scalar>>(newMatrix.getRow());
			for (i=0; i<newMatrix.getRow(); i++) {
				matrix_.add(new ArrayList<Scalar>(newMatrix.getCol()));
			}
			row=newMatrix.getRow(); col=newMatrix.getCol();
			for (i=1;i<=row;i++) {
				for (j=1;j<=col;j++) {
					matrix_.get(i-1).add(new ScalarImpl(newMatrix.getEntry(i,j).getScalar()));
				}
			}
			return this;
	 }
	 static Matrix add(Matrix a, Matrix b)throws SizeIncompatibleException {	 //28 전달받은 두 행렬의 덧셈 		
		 
		 if ((a.getRow()!=b.getRow()) || (a.getCol()!=b.getCol())) {throw new SizeIncompatibleException("두 행렬이 크기가 다릅니다");}
		 MatrixImpl newMatrix=new MatrixImpl(a.getRow(), a.getCol());
		 Scalar newScalar;
		 for (int i=1; i<=newMatrix.getRow(); i++) {
			 for (int j=1; j<=newMatrix.getCol(); j++) {
				 newScalar=Tensors.add(a.getEntry(i, j), b.getEntry(i,j));
				 newMatrix.matrix_.get(i-1).add(newScalar);
			 }
		 }
		 return newMatrix;
	 }
	 
	 static Matrix mult(Matrix a, Matrix b) throws SizeIncompatibleException{  //29 전달받은 두 행렬의 곱셈 
		 if (a.getCol()!=b.getRow()) {throw new SizeIncompatibleException("연산할 수 없는 행렬입니다");}
		 int i,j,k;
		 MatrixImpl newMatrix=new MatrixImpl(a.getRow(), b.getCol());
		 Scalar temp; ScalarImpl sum;
		 for (i=1;i<=a.getRow(); i++) {
			for (j=1; j<=b.getCol(); j++) {
				sum=new ScalarImpl(0.0);
				 for (k=1;k<=a.getCol();k++) {
					 	temp=Tensors.mult(a.getEntry(i,k), b.getEntry(k, j));
					 	sum.add(temp);
				 }
				 newMatrix.matrix_.get(i-1).add(sum);
			}
		 } 
		 return newMatrix;
	 }
	 
	 public void mergeHorizontal(Matrix mtx) throws SizeIncompatibleException{   //32 다른 행렬과 가로로 합쳐짐 
		if (getRow()!=mtx.getRow()) {throw new SizeIncompatibleException("두 행렬의 행 수가 같지 않습니다");}
		int i,j;
		Scalar newScalar;
		for (i=1; i<=row; i++) {
			for (j=1; j<=mtx.getCol(); j++) {
				newScalar=new ScalarImpl(mtx.getEntry(i, j).getScalar());
				matrix_.get(i-1).add(newScalar);
			}
		}
		col=matrix_.get(0).size();
	 }
	 public void mergeVertical(Matrix mtx) throws SizeIncompatibleException{		//33 다른 행렬과 세로로 합쳐짐 
		if (getCol()!=mtx.getCol()) {throw new SizeIncompatibleException("두 행렬의 열 수가 같지 않습니다");}
		int i,j;
		int length;
		Scalar newScalar;
		for (i=1; i<=mtx.getRow(); i++) {
			matrix_.add(new ArrayList<Scalar>(mtx.getCol()));
			length=matrix_.size();
			for (j=1; j<=col; j++) {
				newScalar=new ScalarImpl(mtx.getEntry(i, j).getScalar());
				matrix_.get(length-1).add(newScalar);
			}
		} 
		row=matrix_.size();
	}
	static Matrix mergeHorizontal(Matrix a, Matrix b) throws SizeIncompatibleException { //32 전달받은 두 행렬이 가로로 합쳐짐 
		if (a.getRow()!=b.getRow()) {throw new SizeIncompatibleException("두 행렬의 행 수가 같지 않습니다");}
		int i,j;
		MatrixImpl newMatrix=new MatrixImpl(a.getRow(), a.getCol());
		Scalar newScalar;
		for (i=1; i<=newMatrix.getRow(); i++) {
			for (j=1; j<=newMatrix.getCol(); j++) {
				newScalar=new ScalarImpl(a.getEntry(i, j).getScalar());
				newMatrix.matrix_.get(i-1).add(newScalar);
			}
		}
		newMatrix.mergeHorizontal(b);
		return newMatrix;
	}
	static Matrix mergeVertical(Matrix a, Matrix b) throws SizeIncompatibleException { //33 전달받은 두 행렬이 세로로 합쳐짐 
		if (a.getCol()!=b.getCol()) {throw new SizeIncompatibleException("두 행렬의 열 수가 같지 않습니다");}
		int i,j;
		MatrixImpl newMatrix=new MatrixImpl(a.getRow(), a.getCol());
		Scalar newScalar;
		for (i=1; i<=newMatrix.getRow(); i++) {
			for (j=1; j<=newMatrix.getCol(); j++) {
				newScalar=new ScalarImpl(a.getEntry(i, j).getScalar());
				newMatrix.matrix_.get(i-1).add(newScalar);
			}
		}
		newMatrix.mergeVertical(b);
		return newMatrix;
	}
	public Vector getRowVec(int index) { //34 특정 행을 벡터 형태로 추출 
		int i;
		Scalar zero=new ScalarImpl(0.0);
		Vector newVector=Factory.createVector(col,zero);
		for (i=1; i<=col; i++) {
			newVector.setVec(i, new ScalarImpl(getEntry(index,i).getScalar()));
		}
		return newVector;
	}
	public Vector getColVec(int index) {	//35 특정 열을 벡터 형태로 추출 
		int i;
		Scalar zero=new ScalarImpl(0.0);
		Vector newVector=Factory.createVector(row,zero);
		for (i=1; i<=row; i++) {
			newVector.setVec(i, new ScalarImpl(getEntry(i,index).getScalar()));
		}
		return newVector;
	}

	public Matrix extract(int from1, int to1, int from2, int to2) throws SizeIncompatibleException  { //36 특정 범위의 부분 행렬을 추출 
	if (!(from1>=1 && from1<=getRow() && from2>=1 && from2<=getCol() && to1>=1 && to1<=getRow() && to2>=1 && to2<=getCol()))  {throw new SizeIncompatibleException("범위가 잘못 설정되었습니다");}
	if ((from1>=to1) || (from2>=to2)) {throw new SizeIncompatibleException("범위가 잘못 설정되었습니다");}
	int i,j;
	Scalar zero=new ScalarImpl(0.0);
	Scalar newScalar;
	Matrix newMatrix=Factory.createMatrix(to1-from1+1, to2-from2+1, zero);
	for (i=1; i<=newMatrix.getRow(); i++) {
		for (j=1; j<=newMatrix.getCol(); j++) {
			newScalar=new ScalarImpl(getEntry(from1+i-1,from2+j-1).getScalar());
			newMatrix.setEntry(i, j, newScalar);
		}
	}
	
	return newMatrix;
} 
	
	public Matrix minor(int rrow, int rcol) throws SizeIncompatibleException { //37 특정 범위의 부분 행렬(minor)을 추출 
		if (!(rrow>=1 && rrow<=getRow() && rcol>=1 && rcol<=getCol()))  {throw new SizeIncompatibleException("범위가 잘못 설정되었습니다");}
		int i, j;
		int newMatrixRow=0;
		MatrixImpl newMatrix=new MatrixImpl(row-1, col-1);
		for (i=1; i<=row; i++) {
			if (i!=rrow) {newMatrixRow++;}
			else {continue;} 
			for (j=1; j<=col; j++) {
				if (j!=rcol) {
					newMatrix.matrix_.get(newMatrixRow-1).add(new ScalarImpl(getEntry(i,j).getScalar()));
				}
			} 
		}
		return newMatrix; 
	}
	public Matrix transpose() {  //38 전치행렬 새로 생성하여 구해줌 
		int i,j;
		MatrixImpl newMatrix=new MatrixImpl(col,row);
		for (i=1; i<=newMatrix.getRow(); i++) {
			for (j=1; j<=newMatrix.getCol(); j++) {
				newMatrix.matrix_.get(i-1).add(new ScalarImpl(getEntry(j,i).getScalar()));
			}
		}
		
		return newMatrix;
	}
	public double trace() throws SizeIncompatibleException{		//39 대각 요소의 합을 구해줌 
		if (!isSquar()) {throw new SizeIncompatibleException("nxn 행렬이 아닙니다");} 
		Scalar newScalar=new ScalarImpl(0.0);
		for (int i=1; i<=getRow(); i++) {
			newScalar.add(getEntry(i,i));
		}
		return newScalar.getScalar(); 
	}
	public boolean isSquar() {			//40 정사각 행렬인지 여부 판별 
		if (row==col)
			return true;
		else
			return false;
	}
	public boolean isUpperTri() throws SizeIncompatibleException{  //41 상삼각 행렬인지 여부 판별 
		if (!isSquar()) {throw new SizeIncompatibleException("nxn 행렬이 아닙니다");} 
		int i,j, flag=0;
		for (i=1; i<=row; i++) {
			for (j=1; j<i; j++) {
				if (getEntry(i,j).getScalar()!=0) {flag=1; break;}
			}
			if (flag==1) {return false;}
		}
		return true;
	}
	public boolean isLowerTri() throws SizeIncompatibleException{  //42 하삼각 행렬인지 여부 판별 
		if (!isSquar()) {throw new SizeIncompatibleException("nxn 행렬이 아닙니다");} 
		int i,j, flag=0;
		for (i=1; i<row; i++) {
			for (j=i+1; j<=row; j++) {
				if (getEntry(i,j).getScalar()!=0) {flag=1; break;}
			}
			if (flag==1) {return false;}
		}
		return true;
	}
	public boolean isIdentity() throws SizeIncompatibleException {  //43 단위 행렬인지 여부 판별 
		if (!isSquar()) {throw new SizeIncompatibleException("nxn 행렬이 아닙니다");} 
		int i,j, flag=0;
		for (i=1; i<=row; i++) {
			for (j=1; j<=col; j++) {
				if (i!=j) {
					if (getEntry(i,j).getScalar()!=0) {flag=1; break;}
				}
				else {
					if (getEntry(i,j).getScalar()!=1) {flag=1; break;}
				}
			}
			if (flag==1) {return false;}
		}
		return true;
	}
	public boolean isNull() {  //44 영 행렬인지 여부 판별 
		int i,j, flag=0;
		for (i=1; i<=row; i++) {
			for (j=1; j<=col; j++) {
				if (getEntry(i,j).getScalar()!=0) {flag=1; break;}
			}
			if (flag==1) {return false;}
		}
		return true;
	}
	public void swapRow(int a, int b) throws SizeIncompatibleException{  //45 특정 두 행의 위치 맞교환 
		if (!(a>=1 && a<=getRow() && b>=1 && b<=getRow())) {throw new SizeIncompatibleException("범위가 잘못 설정되었습니다");}
		int i;
		Scalar zero=new ScalarImpl(0.0);
		Matrix temp=Factory.createMatrix(1, col, zero);
		for (i=1; i<=col; i++) {temp.setEntry(1, i, new ScalarImpl(getEntry(a,i).getScalar()));}
		for (i=1; i<=col; i++) {setEntry(a,i, new ScalarImpl(getEntry(b,i).getScalar()));}
		for (i=1; i<=col; i++) {setEntry(b,i, new ScalarImpl(temp.getEntry(1,i).getScalar()));}
	}
	
	public void swapCol(int a, int b) throws SizeIncompatibleException{  //46 특정 두 열의 위치 맞교환 
		if (!(a>=1 && a<=getCol() && b>=1 && b<=getCol())) {throw new SizeIncompatibleException("범위가 잘못 설정되었습니다");}
		int i;
		Scalar zero=new ScalarImpl(0.0);
		Matrix temp=Factory.createMatrix(row, 1, zero);
		for (i=1; i<=row; i++) {temp.setEntry(i, 1, new ScalarImpl(getEntry(i,a).getScalar()));}
		for (i=1; i<=row; i++) {setEntry(i,a, new ScalarImpl(getEntry(i,b).getScalar()));}
		for (i=1; i<=row; i++) {setEntry(i,b, new ScalarImpl(temp.getEntry(i,1).getScalar()));}
	}
	
	public void multAtRow(int index, double value) throws SizeIncompatibleException{ //47 특정 행에 상수배를 함 
		if (index<1 || index>getRow()) {throw new SizeIncompatibleException("존재하지 않는 행입니다.");}
		Scalar value1=new ScalarImpl(value);
		int i;
		for (i=1; i<=col; i++) {
			getEntry(index,i).mult(value1); 
		} 
	}
	
	public void multAtCol(int index, double value) throws SizeIncompatibleException { //48 특정 열에 상수배를 함 
		if (index<1 || index>getCol()) {throw new SizeIncompatibleException("존재하지 않는 열입니다.");}
		Scalar value1=new ScalarImpl(value);
		int i;
		for (i=1; i<=row; i++) {
			getEntry(i,index).mult(value1); 
		} 
	}
	public void addMultRow(int source, int mulIndex, double value) throws SizeIncompatibleException{ //49 특정 행에 다른 행의 상수배를 더함 
		if (!(source>=1 && source<=getRow() && mulIndex>=1 && mulIndex<=getRow())) {throw  new SizeIncompatibleException("존재하지 않는 행입니다.");}
		Scalar value1=new ScalarImpl(value);
		int i;
		Vector mulVector=getRowVec(mulIndex);
		mulVector.mult(value1);
		for (i=1; i<=col; i++) {
			getEntry(source,i).add(mulVector.getVec(i));
		}
	}
	public void addMultCol(int source, int mulIndex, double value) throws SizeIncompatibleException{ //50 특정 열에 다른 열의 상수배를 더함 
		if (!(source>=1 && source<=getRow() && mulIndex>=1 && mulIndex<=getRow())) {throw  new SizeIncompatibleException("존재하지 않는 열입니다.");}
		Scalar value1=new ScalarImpl(value);
		int i;
		Vector mulVector=getColVec(mulIndex);
		mulVector.mult(value1);
		for (i=1; i<=row; i++) {
			getEntry(i,source).add(mulVector.getVec(i));
		}
	}
	public Matrix rref() {		//51 RREF행렬을 구해서 반환 
		int i,j,k;
		int a=0;
		double divide;
		MatrixImpl rref=new MatrixImpl(row,col);
		for (i=0; i<row; i++) {
			for (j=0; j<col; j++) {
				rref.matrix_.get(i).add(new ScalarImpl(getEntry(i+1,j+1).getScalar()));
			}
		}  
		
		for (i=0; i<col && a<row; i++) {
			int b=a;
			for (k=a+1; k<row; k++) {
				if (rref.getEntry(k+1,i+1).getScalar()>rref.getEntry(b+1,i+1).getScalar()) {
					b=k;
				}
			}
			rref.swapRow(a+1,b+1);
			divide=1.0/(rref.getEntry(a+1,i+1).getScalar());
			for (b=0; b<col; b++) {rref.getEntry(a+1,b+1).mult(new ScalarImpl(divide));}
			for (j=0; j<row; j++) {
				if (j!=a) {
					for (b=0; b<col; b++) {
						Scalar newScalar1=Tensors.mult(rref.getEntry(j+1,i+1), new ScalarImpl(-1));
						Scalar newScalar2=Tensors.mult(newScalar1, rref.getEntry(a+1,b+1));
						rref.getEntry(j+1,b+1).add(newScalar2);
					}
				}
			}
			a++;
		}
		return rref;  
	}
	
	public boolean isRREF() {		//52 RREF 행렬 여부인지 판별 
		int i,j;
		int index;
		ArrayList<Integer> leading=new ArrayList<Integer>(row);
		double val;
		for (i=1; i<=row; i++) {
			for (j=1; j<=col; j++) {
				val=getEntry(i,j).getScalar();
				if (val!=0 && val!=1) {return false;}
				else if (val==1) {
					leading.add(j);
					if (leading.size()!=1 && (leading.get(leading.size()-2)>=leading.get(leading.size()-1))) {return false;}
					break;
				}
			}
		}
		if (leading.isEmpty()) {return false;}
		else {
			for (j=1; j<=leading.size(); j++) {
				index=leading.get(j-1);
				for (i=1; i<=row; i++) {
					val=getEntry(i,index).getScalar();
					if (val!=1 && val!=0) {return false;}
				}
			}
			return true;
		} 
	}
	public double det() throws SizeIncompatibleException{		 //53 행렬식 구해줌 	
		if (getCol()!=getRow()) {throw new SizeIncompatibleException("nxn 행렬이 아닙니다");}
		Scalar det=new ScalarImpl(0.0);
		if (row==2) {
			Scalar a=Tensors.mult(getEntry(1,1), getEntry(2,2));
			Scalar b=Tensors.mult(getEntry(1,2), getEntry(2,1));
			b.mult(new ScalarImpl(-1));
			return Tensors.add(a, b).getScalar();
		 }
		for (int i=1; i<=col; i++) {
			Scalar c=Tensors.mult(getEntry(1,i),new ScalarImpl(minor(1,i).det()));
			c.mult(new ScalarImpl(Math.pow(-1, 1+i)));
			det.add(c);
		}
		
		return det.getScalar();
	}
	
	public Matrix inverse() throws SizeIncompatibleException, NonInvertableMatrixException{  //54 역행렬 구해줌 
		if (getCol()!=getRow()) {throw new SizeIncompatibleException("nxn 행렬이 아닙니다");}
		if (this.det()==0) {throw new NonInvertableMatrixException("역함수가 없는 행렬입니다");}
		MatrixImpl cof=new MatrixImpl(row,col);
		for (int i=1; i<=row; i++) {
			for (int j=1; j<=col; j++) {
				cof.matrix_.get(i-1).add(new ScalarImpl(minor(i,j).det()));
				cof.getEntry(i,j).mult(new ScalarImpl(Math.pow(-1, i+j)));
			}
		}
		Scalar det=new ScalarImpl(1.0/det());
		Matrix adj=cof.transpose();
		for (int i=1; i<=row; i++) {
			for (int j=1; j<=col; j++) {
				adj.getEntry(i, j).mult(det);
			}
		}
		return adj;
	}
	
	
	
} 
	
	
