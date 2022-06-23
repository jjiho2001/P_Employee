import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import module.EmpDataSet;
import module.EmpVO;

public class EmpStart {

	Scanner scan = new Scanner(System.in);
	
	public EmpStart() {
		
		// ���� ��ϵǾ��ִ� ��� ���
		EmpDataSet.dataSet(); // �ʱ� ������ ����
		
		do{
			try {
				System.out.print(menu());
				int inMenu = Integer.parseInt(scan.nextLine());
				
				if(inMenu == 5) { // ����
					empStop();
				} else if (inMenu == 1) { // ��� ���(��� ���)
					empListOutput();
				} else if (inMenu == 2) { // ��� ���
					empInsert();
				} else if (inMenu == 3) {
					empEdit();
				} else if (inMenu == 4) {
					empDel();
				} else if (inMenu == 6) {
					empSearchbyName();
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while(true);
	}

	// ��������� �˻�
	public void empSearchbyName() {
		System.out.print("�˻��� ����� -> ");
		String searchName = scan.nextLine();
		
		titlePrint();
		
		// �ٸ� ����� empListOutput ����
		int check = 0;
		for(Integer i : EmpDataSet.empList.keySet()) {
			if (EmpDataSet.empList.get(i).getEmpName().equals(searchName)) {
				System.out.println(EmpDataSet.empList.get(i).toString());
				check++;
			} 
		}
		if(check == 0) {
			System.out.println("�׷� ��� ����");
		}
		
	}
	
	// ��� ����
	public void empDel() {
		System.out.print("������ ����� �����ȣ -> ");
		int empno = Integer.parseInt(scan.nextLine());
		
		EmpDataSet.empList.remove(empno); // ��ü ������
		System.out.println(empno + "�� ����� ������ �����Ͽ����ϴ�.");
	}
	
	// ��� ����
	public void empEdit() {
		
		// � ����� ������ ������ �Է¹���
		System.out.print("������ ��� ��ȣ -> ");
		int editEmpno = Integer.parseInt(scan.nextLine());
		
		System.out.print("������ �׸�[1. �μ���, 2. ����ó] -> ");
		String editMenu = scan.nextLine();
		
		if(editMenu.equals("1")) { // �μ� ����
			departmentEdit(editEmpno);
		} else if(editMenu.equals("2")){ // ����ó ����
			telEdit(editEmpno);
		} else {
			System.out.println("������ �޴��� �߸� �����Ͽ����ϴ�.");
		}
	}
	
	// �μ��� ����
	public void departmentEdit(int empno) {
		
		EmpVO vo = EmpDataSet.empList.get(empno);
		
		System.out.print("������ �μ��� -> ");
		String editDepartName = scan.nextLine();
		
		vo.setDepartment(editDepartName); // �μ����� �����.
		System.out.println(vo.getEmpName() + "�� �μ�����" + vo.getDepartment() + "�� ����Ǿ����ϴ�.");
	}
	
	// ����ó ����
	
	public void telEdit(int empno) {
		
		EmpVO vo = EmpDataSet.empList.get(empno);
		
		System.out.print("������ ����ó -> ");
		String editTel = scan.nextLine();
		
		vo.setTel(editTel);
		System.out.println(vo.getEmpName() + "�� ����ó��" + vo.getTel() + "�� ����Ǿ����ϴ�");
	}
	
	// ��� ���
	public void empListOutput() {
		titlePrint();
		
		// Map�� ��� value(vo ��ü)�� ���Ͽ� ����� ����Ѵ�.
		Collection<EmpVO> emp  = EmpDataSet.empList.values();
		Iterator<EmpVO> i = emp.iterator();
		
		while(i.hasNext()) {
			EmpVO v = i.next();
			System.out.println(v.toString());
		}
	}
	
	public void titlePrint() {
		System.out.println("��� ��ȣ\t�����\t�μ���\t����ó");
	}
	
	// ��� ���
	public void empInsert() {
		
		EmpVO vo = new EmpVO(); // �Է¹��� ��� ������ ������ vo
		
		System.out.print("��� ��ȣ -> ");
		vo.setEmpno(Integer.parseInt(scan.nextLine()));
		System.out.print("����� -> ");
		vo.setEmpName(scan.nextLine());
		System.out.print("�μ��� -> ");
		vo.setDepartment(scan.nextLine());
		System.out.print("����ó -> ");
		vo.setTel(scan.nextLine());
		
		// collection�� vo �߰�
		EmpDataSet.empList.put(vo.getEmpno(), vo);
		System.out.println("����� ��ϵǾ����ϴ�.");
	}
	
	// ����
	public void empStop() {
		try {
			// ��� ������ �ִ� empList�� ���Ϸ� �����ϰ� ����
			File f = new File("C://Users/Jiho Jung/Desktop/dev/", "employee.txt");
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(EmpDataSet.empList);
			
			oos.close();
			fos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public String menu() {
		return "�޴�[1. ��� ���, 2. ��� ���, 3.��� ����, 4. ��� ����, 5. ���� 6. �˻�] -> ";
	}
	
	public static void main(String[] args) {
		new EmpStart();

	}

}
