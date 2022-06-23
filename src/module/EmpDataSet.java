package module;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class EmpDataSet {

	// 사원정보를 저장할 collection을 선언
	public static HashMap<Integer, EmpVO> empList = new HashMap<Integer, EmpVO>();
	
	public EmpDataSet() {
		
	}
	
	public static void dataSet() {
		// 파일에 있는 object을 구하여 HashMap에 세팅
		try {
			File f = new File("C://Users/Jiho Jung/Desktop/dev", "employee.txt");
			if(f.exists()) { // 파일이 있을 경우
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				EmpDataSet.empList = (HashMap)ois.readObject(); // 초기 사원 목록 readObject -> return object 타입
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
