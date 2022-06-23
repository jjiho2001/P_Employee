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
		
		// 현재 등록되어있는 사원 목록
		EmpDataSet.dataSet(); // 초기 데이터 세팅
		
		do{
			try {
				System.out.print(menu());
				int inMenu = Integer.parseInt(scan.nextLine());
				
				if(inMenu == 5) { // 종료
					empStop();
				} else if (inMenu == 1) { // 사원 목록(모든 사원)
					empListOutput();
				} else if (inMenu == 2) { // 사원 등록
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

	// 사원명으로 검색
	public void empSearchbyName() {
		System.out.print("검색할 사원명 -> ");
		String searchName = scan.nextLine();
		
		titlePrint();
		
		// 다른 방법은 empListOutput 참조
		int check = 0;
		for(Integer i : EmpDataSet.empList.keySet()) {
			if (EmpDataSet.empList.get(i).getEmpName().equals(searchName)) {
				System.out.println(EmpDataSet.empList.get(i).toString());
				check++;
			} 
		}
		if(check == 0) {
			System.out.println("그런 사람 ㄴㄴ");
		}
		
	}
	
	// 사원 삭제
	public void empDel() {
		System.out.print("삭제할 사원의 사원번호 -> ");
		int empno = Integer.parseInt(scan.nextLine());
		
		EmpDataSet.empList.remove(empno); // 객체 지워짐
		System.out.println(empno + "번 사원의 정보를 삭제하였습니다.");
	}
	
	// 사원 수정
	public void empEdit() {
		
		// 어떤 사원을 수정할 것인지 입력받음
		System.out.print("수정할 사원 번호 -> ");
		int editEmpno = Integer.parseInt(scan.nextLine());
		
		System.out.print("수정할 항목[1. 부서명, 2. 연락처] -> ");
		String editMenu = scan.nextLine();
		
		if(editMenu.equals("1")) { // 부서 수정
			departmentEdit(editEmpno);
		} else if(editMenu.equals("2")){ // 연락처 수정
			telEdit(editEmpno);
		} else {
			System.out.println("수정할 메뉴를 잘못 선택하였습니다.");
		}
	}
	
	// 부서명 수정
	public void departmentEdit(int empno) {
		
		EmpVO vo = EmpDataSet.empList.get(empno);
		
		System.out.print("수정할 부서명 -> ");
		String editDepartName = scan.nextLine();
		
		vo.setDepartment(editDepartName); // 부서명이 변경됨.
		System.out.println(vo.getEmpName() + "의 부서명이" + vo.getDepartment() + "로 변경되었습니다.");
	}
	
	// 연락처 수정
	
	public void telEdit(int empno) {
		
		EmpVO vo = EmpDataSet.empList.get(empno);
		
		System.out.print("수정할 연락처 -> ");
		String editTel = scan.nextLine();
		
		vo.setTel(editTel);
		System.out.println(vo.getEmpName() + "의 연락처가" + vo.getTel() + "로 변경되었습니다");
	}
	
	// 사원 목록
	public void empListOutput() {
		titlePrint();
		
		// Map의 모든 value(vo 객체)를 구하여 목록을 출력한다.
		Collection<EmpVO> emp  = EmpDataSet.empList.values();
		Iterator<EmpVO> i = emp.iterator();
		
		while(i.hasNext()) {
			EmpVO v = i.next();
			System.out.println(v.toString());
		}
	}
	
	public void titlePrint() {
		System.out.println("사원 번호\t사원명\t부서명\t연락처");
	}
	
	// 사원 등록
	public void empInsert() {
		
		EmpVO vo = new EmpVO(); // 입력받은 사원 정보를 저장할 vo
		
		System.out.print("사원 번호 -> ");
		vo.setEmpno(Integer.parseInt(scan.nextLine()));
		System.out.print("사원명 -> ");
		vo.setEmpName(scan.nextLine());
		System.out.print("부서명 -> ");
		vo.setDepartment(scan.nextLine());
		System.out.print("연락처 -> ");
		vo.setTel(scan.nextLine());
		
		// collection에 vo 추가
		EmpDataSet.empList.put(vo.getEmpno(), vo);
		System.out.println("사원이 등록되었습니다.");
	}
	
	// 종료
	public void empStop() {
		try {
			// 사원 정보가 있는 empList를 파일로 저장하고 종료
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
		return "메뉴[1. 사원 목록, 2. 사원 등록, 3.사원 수정, 4. 사원 삭제, 5. 종료 6. 검색] -> ";
	}
	
	public static void main(String[] args) {
		new EmpStart();

	}

}
