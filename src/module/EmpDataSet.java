package module;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class EmpDataSet {

	// ��������� ������ collection�� ����
	public static HashMap<Integer, EmpVO> empList = new HashMap<Integer, EmpVO>();
	
	public EmpDataSet() {
		
	}
	
	public static void dataSet() {
		// ���Ͽ� �ִ� object�� ���Ͽ� HashMap�� ����
		try {
			File f = new File("C://Users/Jiho Jung/Desktop/dev", "employee.txt");
			if(f.exists()) { // ������ ���� ���
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				EmpDataSet.empList = (HashMap)ois.readObject(); // �ʱ� ��� ��� readObject -> return object Ÿ��
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
