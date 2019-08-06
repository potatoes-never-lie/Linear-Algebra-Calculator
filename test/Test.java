package test;

import tensor.*;


public class Test {
	public static void main(String[] args) {
		Scalar scalar0=Factory.createScalar(0.0);
		Scalar scalar1=Factory.createScalar(5.0);
		Scalar scalar2=Factory.createScalar(0.2);
		Scalar scalar3=Factory.createScalar(4.5);
		Scalar scalar4=Factory.createScalar(-1.0);
		Scalar scalar5=Factory.createScalar(-2.0);
		Scalar scalar6=Factory.createScalar(-3.0);
		Matrix temp1=Factory.createMatrix(3, 5, scalar4); System.out.println("temp1 Matrix: "+temp1);
		Matrix temp2=Factory.createMatrix(3, 5, scalar4); System.out.println("temp2 Matrix: "+temp2);
		Matrix temp3=Factory.createMatrix(4, 2, scalar2);	System.out.println("temp3 Matrix: "+temp3);
		Matrix temp4=Factory.createMatrix(2, 3, scalar1); System.out.println("temp4 Matrix: "+temp4);
		Matrix temp5=Factory.createIdentity(4); System.out.println("temp5 Matrix: "+temp5);
		Scalar[] s_array1= {scalar2, scalar3, scalar5, scalar6};
		Scalar[][] s_array2= {{scalar1,scalar2,scalar3},{scalar4,scalar5,scalar6}};
		Scalar s_1=Factory.createScalar(5.0); System.out.println("1: (s_1=) "+s_1);
		Scalar s_2=Factory.randomScalar(3.0,7.5); System.out.println("2: (s_2=) "+s_2);
		Vector v_3=Factory.createVector(5, s_1); System.out.println("3: (v_3=) "+v_3);
		Vector v_31=Factory.createVector(5, scalar4); 
		Vector v_4=Factory.randomVector(3,s_1,s_2); System.out.println("4: (v_4=) "+v_4);
		Vector v_5=Factory.arrayToVector(s_array1); System.out.println("5: (v_5=) "+v_5);
		Matrix m_6=Factory.createMatrix(3, 5, scalar3); System.out.println("6: (m_6=) "+m_6);
		Matrix m_7=Factory.randomMatrix(2, 3, scalar3, scalar1); System.out.println("7: (m_7=) "+m_7);
		//8 
		Matrix m_9=Factory.arrayToMatrix(s_array2); System.out.println("9: (m_9=) "+m_9);
		Matrix m_10=Factory.createIdentity(4); System.out.println("10: (m_10=) "+m_10);
		System.out.println("11v(조회): v_3.getVec(5)-> "+v_3.getVec(5)); 
		v_3.setVec(4,scalar3); //11v 지정 함  
		System.out.println("11v(지정): v_3.setVec(4,scalar3)-> "+v_3); //11v 지정 한 후 나온 결과 
		System.out.println("11m(조회): m_6.getEntry(3,4)-> "+m_6.getEntry(3,4));
		m_6.setEntry(3, 4, scalar6); //11m 지정함
		System.out.println("11m(지정): m_6.setEntry(3, 4, scalar6)-> "+m_6.getEntry(3, 4));  //11m 지정한 후 나온 결과
		System.out.println("12 (조회): scalar2.getScalar()-> "+scalar2.getScalar());
		scalar2.setScalar(3.6); //12 지정함
		System.out.println("12 (지정): scalar2.setScalar(3.6)-> "+scalar2.getScalar()); //12 지정한 후 나온 결과 
		System.out.println("13v: v_4.size()-> "+v_4.size());
		System.out.println("13m: 행 개수 : "+m_9.getRow()+" 열 개수: "+m_9.getCol());
		System.out.println("14s: s_2출력-> "+s_2.toString());System.out.println("14v: v_5출력-> "+v_5.toString());System.out.println("14m: m_7출력-> "+m_7.toString());
		System.out.println("15s: s1.equals(scalar1)-> "+s_1.equals(scalar1)); System.out.println("15v: v_3.equals(v_5)-> "+v_3.equals(v_5)); System.out.println("15m: temp1.equals(temp2)-> "+temp1.equals(temp2));
		System.out.println("16: scalar0.compareTo(scalar1)-> "+scalar0.compareTo(scalar1));
		try{Scalar clone1=(Scalar)s_1.clone(); System.out.println("17s: clone1=s_1.clone()-> "+clone1);} catch(CloneNotSupportedException e) {e.printStackTrace();}
		try{Vector clone2=(Vector)v_3.clone(); System.out.println("17v: clone2=v_3.clone()-> "+clone2);} catch(CloneNotSupportedException e) {e.printStackTrace();}
		try{Matrix clone3=(Matrix)m_9.clone(); System.out.println("17m: clone3=m_9.clone()-> "+clone3);} catch(CloneNotSupportedException e) {e.printStackTrace();}
		scalar1.add(scalar2); System.out.println("18: scalar1.add(scalar2)-> "+scalar1);
		scalar1.mult(s_1); 	System.out.println("19: scalar1.mult(s_1)-> "+scalar1);	
		try {v_3.add(v_31);System.out.println("20: v_3.add(v_31)-> "+v_3);} catch(RuntimeException e) {System.out.println("20: v_3.add(v_31)-> "+e);}
		v_3.mult(scalar6);System.out.println("21: v_3.mult(scalar6)-> "+v_3); 
		try{System.out.println("22: m_9.add(temp1)-> "+m_9.add(temp1));} catch(RuntimeException e) {System.out.println("22: m_9.add(temp1)-> "+e);}
		try{System.out.println("22: m_6.add(temp1)-> "+m_6.add(temp1));} catch(RuntimeException e) {System.out.println("22: m_6.add(temp1)-> "+e);}
		try {System.out.println("23-1: m_6.multR(temp1)-> "+m_6.multR(temp1));}  catch(RuntimeException e) {System.out.println("23-1: m_6.multR(temp1)-> "+e);}
		try {System.out.println("23-1: m_10.multR(temp3)-> "+m_10.multR(temp3));} catch(RuntimeException e) {System.out.println("23-1: m_10.multR(temp3)-> "+e);}
		try {System.out.println("23-2: temp4.multL(temp3)-> "+temp4.multL(temp3));} catch(RuntimeException e) {System.out.println("23-2: temp4.multL(temp3)-> "+e);}
		Scalar s_24=Tensors.add(scalar1, scalar2); System.out.println("24: Tensors.add(scalar1, scalar2)-> "+s_24);
		Scalar s_25=Tensors.mult(scalar1, scalar2); System.out.println("25: Tensors.mult(scalar1, scalar2)-> "+s_25);
		try {Vector v_26=Tensors.add(v_3,v_31); System.out.println("26: Tensors.add(v_3,v_31)-> "+v_26);} catch(RuntimeException e) {System.out.println("26: Tensors.add(v_3,v_31)-> "+e);}
		Vector v_27=Tensors.mult(v_4,scalar4); System.out.println("27: Tensors.mult(v_4,scalar4)-> "+v_27);
		try{Matrix m_28=Tensors.add(temp1, temp2); System.out.println("28: Tensors.add(temp1, temp2)-> "+m_28);} catch(RuntimeException e) {System.out.println("28: Tensors.add(temp1, temp2)-> "+e);}
		try {Matrix m_29=Tensors.mult(temp5, temp3); System.out.println("29: Tensors.mult(temp5, temp3)-> "+m_29);} catch(RuntimeException e) {System.out.println("29: Tensors.mult(temp5, temp3)-> "+e);}
		System.out.println("30: v_3.getnx1()-> "+v_3.getnx1());
		System.out.println("31: v_3.get1xn()-> "+v_3.get1xn());
		try {m_6.mergeHorizontal(temp1); System.out.println("32-1: m_6.mergeHorizontal(temp1)-> "+m_6);} catch(RuntimeException e) {System.out.println("32-1: m_6.mergeHorizontal(temp1)-> "+e);}
		try {Matrix m_32=Tensors.mergeHorizontal(temp3, temp5); System.out.println("32-2: Tensors.mergeHorizontal(temp3, temp5)-> "+m_32);} catch(RuntimeException e) {System.out.println("32-2: Tensors.mergeHorizontal(temp3, temp5)-> "+e);}
		try {m_7.mergeVertical(temp4); System.out.println("33-1: m_7.mergeVertical(temp4)-> "+m_7);} catch(RuntimeException e) {System.out.println("33-1: m_7.mergeHorizontal(temp4)-> "+e);}
		try {Matrix m_33=Tensors.mergeVertical(temp4,m_7); System.out.println("33-2: Tensors.mergeVertical(temp4,m_7) -> "+m_33);} catch (RuntimeException e) {System.out.println("33-2: Tensors.mergeVertical(temp4,m_7) -> "+e);}
		System.out.println("34: m_6.getRowVec(3)-> "+m_6.getRowVec(3));
		System.out.println("35: m_6.getColVec(4)-> "+m_6.getColVec(4));
		try{System.out.println("36: m_6.extract(1, 3, 3, 4)-> "+m_6.extract(1,3,3,4));} catch(RuntimeException e) {System.out.println("36: m_6.extract(1, 3, 3, 4)-> "+e);}
		try {System.out.println("37: temp5.minor(1,1)-> "+temp5.minor(1,1));} catch(RuntimeException e) {System.out.println("37: temp5.minor(1,1)-> "+e);}
		System.out.println("38: m_6.transpose()-> "+m_6.transpose());
		try {System.out.println("39: temp5.trace()-> "+temp5.trace());} catch(RuntimeException e) {System.out.println("39: temp5.trace()-> "+e);}
		System.out.println("40: m_6.isSquar()-> "+m_6.isSquar());
		temp5.setEntry(3, 4, scalar5);//temp5를 상삼각행렬로 변경
		try {System.out.println("41: temp5.isUpperTri()-> "+temp5.isUpperTri());} catch(RuntimeException e) {System.out.println("41: temp5.isUpperTri()-> "+e);}
		temp5.setEntry(4, 1, scalar5); temp5.setEntry(3,4,scalar0); //temp5를 하삼각행렬로 변경
		try {System.out.println("42: temp5.isLowerTri()-> "+temp5.isLowerTri());} catch(RuntimeException e) {System.out.println("42: temp5.isLowerTri()-> "+e);}
		try {System.out.println("43: temp5.isIdentity()-> "+temp5.isIdentity());} catch(RuntimeException e) {System.out.println("43: temp5.isIdentity()-> "+e);}
		System.out.println("44: temp5.isNull()-> "+temp5.isNull());
		try {m_6.swapRow(1,3); System.out.println("45: m_6.swapRow(1,3)-> "+m_6);} catch(RuntimeException e) {System.out.println("45: m_6.swapRow(1,3)-> "+e);}
		try {m_6.swapCol(4,6); System.out.println("46: m_6.swapCol(4,6)-> "+m_6);} catch(RuntimeException e) {System.out.println("46: m_6.swapCol(4,6)-> "+e);}
		try {m_6.multAtRow(1,2.0); System.out.println("47: m_6.multAtRow(3,2.0)-> "+m_6);} catch(RuntimeException e) {System.out.println("47: m_6.multAtRow(3,2.0)-> "+e);}
		try {m_6.multAtCol(4,3.0); System.out.println("48: m_6.multAtCol(4,3.0)-> "+m_6);} catch(RuntimeException e) {System.out.println("48: m_6.multAtCol(4,3.0)-> "+e);}
		try {m_6.addMultRow(1,2,2.0); System.out.println("49: m_6.addMultRow(1,2,2.0)-> "+m_6);} catch(RuntimeException e) {System.out.println("49: m_6.addMultRow(1,2,2.0)-> "+e);}
		try {m_6.addMultCol(1,2,2.0); System.out.println("50: m_6.addMultCol(1,2,2.0)-> "+m_6);} catch(RuntimeException e) {System.out.println("50: m_6.addMultCol(1,2,2.0)-> "+e);}
		System.out.println("51: m_9.rref()-> "+m_9.rref()); 
		System.out.println("52: m_9.rref().isRREF()-> "+m_9.rref().isRREF());
		Matrix m_53=m_6.extract(1, 3, 1, 3); System.out.println("행렬 m53 생성 "+m_53);
		try {System.out.println("53: m_53.det()-> "+m_53.det());} catch(RuntimeException e) {System.out.println("53: m_53.det()-> "+e);}
		try {System.out.println("54: m_54.inverse()-> "+m_53.inverse());} catch(RuntimeException e) {System.out.println("54: m_53.inverse()-> "+e);}
		try {System.out.println("54: m_9.inverse()-> "+m_9.inverse());} catch(RuntimeException e) {System.out.println("54: m_9.inverse()-> "+e);}
		Matrix m_54=Factory.createIdentity(3); m_54.setEntry(2, 3, scalar4); m_54.setEntry(1, 1, scalar5); 
		System.out.println("행렬 m54 생성 "+m_54);
		try {System.out.println("54: m_54.inverse()-> "+m_54.inverse());} catch(RuntimeException e) {System.out.println("54: m_54.inverse()-> "+e);}
		
	}	

}
