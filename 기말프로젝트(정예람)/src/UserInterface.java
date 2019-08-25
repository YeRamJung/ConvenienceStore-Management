import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class UserInterface{
	public static void main(String strg[]) {
		String goodsName="";  //상품명
		String groupName=""; //대분류명
		int price=0;  //상품가격
		int goodsNum=0;  //상품번호
		int num=0;  //상품번호를 증가시키는 변수
		
		String deleteGroupName="";  //삭제할 상품의 대분류명
		int deleteGoodsNum=0;  //삭제할 상품명의 메뉴 번호
		
		String sellGroupName=""; //판매하는 상픔의 대분류명
		int sellGoodsNum=0; //판매하는 상품명의 메뉴 번호
		int sellAmount=0; //판매할 상품의 수량
		int sellIndex=0;  //판매할 상품의 상품객체배열에서의 인덱스 번호
		
		String addGroupName=""; //재고를 더할 상품의 대분류명
		int addGoodsNum=0; //재고를 더할 상품명의 메뉴 번호
		int addAmount=0;  //입고할 수량
		int addIndex=0;  //입고할 상품의 상품객체배열에서의 인덱스 번호
		
		String subtractGroupName=""; //재고를 뺄 상품의 대분류명
		int subtractGoodsNum=0; //재고를 뺄 상품명의 메뉴 번호
		int subtractAmount=0;  //출고할 수량
		int subtractIndex=0;  //출고할 상품의 상품객체배열에서의 인덱스 번호
		
		Goods[] getArray; 
		Scanner sc = new Scanner(System.in);  //Scanner 객체
		Management mg = new Management(10000);  //Management 객체
		UserMenu um = new UserMenu();  //사용자 메뉴(UserMenu) 객객
		ManagerMenu mm = new ManagerMenu();  //관리자 메뉴(ManagerMenu) 객체	
	
		
		FileInputStream fis = null;
		DataInputStream dis = null;
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		//프로그램 시작 시 read
		try {
			fis = new FileInputStream("File.dat");
			dis = new DataInputStream(fis);
			mg.read(dis);
			num = dis.readInt();
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("파일을 찾을 수 없습니다");
		}
		catch(IOException ioe) {
			System.out.println("파일을 읽을 수 없습니다");
		}
		catch(Exception e) {  //mg.read에서 throw하는 익셉션 catch
			System.out.println(e.getMessage());
		}
		
		//메인 메뉴
		while(true) {
			int whatMainMenu=0; 
			
			//메인메뉴 고르기
			System.out.println("------******편의점 관리 프로그램******------");
			System.out.println("1. 지난 매출액  & 상품정보 열람");
			System.out.println("2. 사용자 시스템");
			System.out.println("3. 관리자 시스템");
			System.out.println("4. 종료하기");
			System.out.println("-------------------------------------");
			System.out.println("원하시는 프로그램 번호를 입력해주세요 : ");
			
			try {	
				whatMainMenu = sc.nextInt();  // 메인메뉴 번호 입력받기 
				sc.nextLine();  //버퍼 지우기
			}
			
			//whatMainMenu에 정수 타입이 아닌 것을 적었을 때
			catch(InputMismatchException e) {
				System.out.println();
				System.out.println("올바른 메뉴번호를 입력하세요"); 
				sc.nextLine();  //버퍼삭제
				continue;
			}
			
			if(whatMainMenu>4 || whatMainMenu<1){ //1~4번 외의 번호를 입력했을 때
				System.out.println();
				System.out.println("올바른 메뉴번호를 입력하세요");
			}
	
			//4.종료할 때
			else if(whatMainMenu==4) {
				try {  
					sc.close(); //Scanner close
					dis.close(); //read했던 거 close
					fis.close();
					dos.close();
					fos.close();
				}
				catch(Exception e) {
				}
				
				System.out.println("프로그램을 종료합니다");
				break;
			}
			
			//1. 지난 매출액  & 상품정보 열람일 때
			else if(whatMainMenu==1) {
				System.out.println("<< 현재까지의 매출액  & 등록된 상품정보  >>");
				System.out.println();
				
				System.out.println("----------****총 매출액****----------");
				System.out.println(mg.getTotalSales() + "원");
				System.out.println();
				
				System.out.println("--------****저장된 상품 정보****--------");
				System.out.println("#등록된 상품: " + (mg.getListOfGoodsIndex()) + "개");
				System.out.println();
				
				for(int i=0;i<mg.getListOfGoodsIndex();i++) {
					System.out.println("<"+(i+1)+">");
					System.out.println("1)상품명: " + mg.listOfGoods[i].getGoodsName());
					System.out.println("2)대분류명: " + mg.listOfGoods[i].getGroupName());
					System.out.println("3)가격: " + mg.listOfGoods[i].getPrice());
					System.out.println("4)상품번호: " + mg.listOfGoods[i].getGoodsNum());
					System.out.println("5)재고량: " + mg.listOfGoods[i].getStock());
					System.out.println("----------------------------------");
				}
				System.out.println();
				continue;
			}
			
			//2.사용자일 때
			else if(whatMainMenu==2) {  
				um.menu1();  //사용자 메뉴 보여주기
				int whatMenu1=0;
				
				try {
					whatMenu1=sc.nextInt();  //사용자 메뉴 번호 입력받기
					sc.nextLine();  //버퍼 지우기
				}
				
				//whatMenu1에 정수 타입이 아닌 것을 적었을 때
				catch(InputMismatchException e) {
					System.out.println();
					System.out.println("올바른 메뉴번호를 입력하세요");
					sc.nextLine();  //버퍼삭제
					continue;
				}
				
				switch(whatMenu1) {  
				case 1: //상품 판매하기
					
					getArray = new Goods[mg.getGoodsInGroupIndex()]; //findGoodsArray에 찾는 대분류명 넘기기
						
					//등록된 상품이 하나도 없는 경우
					if(mg.getListOfGoodsIndex() == 0) {
						System.out.println();
						System.out.println("등록된 상품이 없습니다");
						break;
					}
						
					System.out.println("판매하려는 상품의 대분류명: ");
					try{
						sellGroupName = sc.nextLine(); //판매할 상품의 대분류명 입력받기
						getArray = mg.findGoodsArray(sellGroupName);
					}
					
					//없는 대분류명 입력했을 때
					catch(Exception e) {
						System.out.println();
						System.out.println(e.getMessage());
						break;
					}	
				
					//입력받은 대분류에 해당하는 상품들 출력(목록 보여주기)
					for(int i=0;i<mg.getGoodsInGroupIndex();i++) {
						System.out.println((i+1)+")" + getArray[i].getGoodsName());
					}	
					System.out.println();
							
					System.out.println("판매할 상품의 메뉴번호: ");  //find 판매할 상품
					try {
						sellGoodsNum = sc.nextInt();  //판매할 상품 메뉴번호 입력받기
						sc.nextLine();  //버퍼 삭제
					}
					
					//메뉴번호를 정수타입으로 입력하지 않았을 때
					catch(InputMismatchException e) {
						System.out.println();
						System.out.println("올바른 메뉴번호를 입력하세요");
						sc.nextLine();  //버퍼삭제
						break;
					}
					
					try {	
						sellIndex = mg.findGoodsIndex(sellGoodsNum);  //판매할 상품의 인덱스 번호 찾기
					}
					//없는 메뉴번호를 입력했을 때
					catch(Exception e) {
						System.out.println(); 
						System.out.println("올바른 메뉴번호를 입력하세요");
						break;
					}
						
					System.out.println("판매 수량: ");
					try {
						sellAmount = sc.nextInt();  //판매할 상품의 수량 입력받기
						sc.nextLine(); //버퍼 삭제
					}
					
					//판매수량에 정수 타입이 아닌 것을 입력했을 때
					catch(InputMismatchException e) {
						System.out.println();
						System.out.println("숫자를 입력하세요");
						sc.nextLine();
						break;
					}
				
					try {
						int priceSum = mg.sellEstimate(sellIndex, sellAmount); //미리 판매 액수 보여주기
						System.out.println("예상 총액: " + priceSum); 
					}
					//재고가 부족할 때
					catch(Exception e) {
						System.out.println();
						System.out.println("재고가 부족합니다");
						break;
					}
					
					try {
						mg.sell(sellIndex, sellAmount); //판매하기
					}
					//재고가 부족할 때
					catch(Exception e) {
						System.out.println();
						System.out.println("재고가 부족합니다");
						break;
					}
						
					System.out.println("판매가 완료되었습니다");
					System.out.println("현재까지 총 매출액: " + mg.getTotalSales());	
					System.out.println();
					break;
						
					case 2: //종료 후 메인 메뉴로 가기
						System.out.println();
						System.out.println("프로그램을 종료합니다");
						break;	
					
					default:
						System.out.println();
						System.out.println("올바른 메뉴번호를 입력하세요"); 
						break;	
				}		
			}
				
			//3.관리자일 때
			else if(whatMainMenu==3) {
				mm.menu2();  //관리자 메뉴 보여주기
				int whatMenu2=0;
				
				try {
					whatMenu2=sc.nextInt();   //관리자 메뉴 번호 입력받기
					sc.nextLine();  //버퍼 지우기
				}
				//whatMenu2에 정수 타입이 아닌 것을 적었을 때
				catch(InputMismatchException e) {
					System.out.println();
					System.out.println("올바른 메뉴번호를 입력하세요");
					sc.nextLine();  //버퍼삭제
					continue;
				}
				
				switch(whatMenu2) {
				case 1: //상품 삽입
					
					//사용자로부터 등록될 상품정보 입력받기(상품코드번호 자동 부여)
					System.out.println("등록할 상품명: ");
					goodsName=sc.nextLine();
					
					System.out.println("등록할 상품의 대분류명: ");
					groupName=sc.nextLine();
					
					System.out.println("상품 가격: ");
					try {
						price=sc.nextInt();  
						sc.nextLine();  //버퍼 삭제
					}	
					
					//정수 타입이 아닌 것을 적었을 때
					catch(InputMismatchException e) {
						System.out.println();
						System.out.println("숫자를 입력해주세요");
						sc.nextLine(); //버퍼삭제
						break;
					}
					
					goodsNum = 1000000 + num;  //상품번호 할당
					num++;
					System.out.println("상품 번호: " + goodsNum);

					Goods gd = new Goods(goodsName, groupName, price, goodsNum);  //Goods객체
					
					try {
						mg.insertGoods(gd);  //제품 정보를  Management에 있는 insertGoods함수에 넘겨주기
					}
					catch(Exception e) {
						System.out.println();
						System.out.println("더 이상 상품 입력이 불가합니다");
						break;
					}
					break;
							
				case 2:	//물건 삭제
					deleteGoodsNum=0;
					getArray = new Goods[mg.getGoodsInGroupIndex()];  //findGoodsArray가 return하는 값 받는 배열
					
					//등록된 상품이 하나도 없는 경우
					if(mg.getListOfGoodsIndex() == 0) {
						System.out.println();
						System.out.println("등록된 상품이 없습니다");
						break;
					}
					
					System.out.println("-------****상품정보 삭제하기****-------");
					System.out.println("삭제할 상품의 대분류명: ");   
				
					try {
						deleteGroupName = sc.nextLine();  //삭제할 상품의 대분명 입력받기	
						getArray = mg.findGoodsArray(deleteGroupName);  //findGoodsArray에 찾는 대분류명 넘기기
					}
				
					//없는 대분류명을 입력했을 때
					catch(Exception e){
						System.out.println();
						System.out.println(e.getMessage());
						break;
					}
				
					System.out.println("총 개수: " + mg.getGoodsInGroupIndex());
					//입력받은 대분류에 해당하는 상품들 출력(목록 보여주기)
					for(int i=0;i<mg.getGoodsInGroupIndex();i++)
						System.out.println((i+1)+")" + getArray[i].getGoodsName());
					System.out.println();
					
					System.out.println("삭제할 상품의 메뉴번호: ");  //find 삭제할 상품				
					try {
						deleteGoodsNum=sc.nextInt();  //삭제할 상품 메뉴번호 입력받기
						sc.nextLine();  //버퍼 삭제
					}
					
					//메뉴번호를 정수 타입으로 입력하지 않았을 때
					catch(InputMismatchException e) {		
						System.out.println();
						System.out.println("올바른 메뉴번호를 입력하세요");
						sc.nextLine();
						break;
					}
					
					try {	
						mg.deleteGoods(mg.findGoodsIndex(deleteGoodsNum));  //그 상품 배열에서 삭제하기
					}
					
					//없는 메뉴번호를 입력했을 때
					catch(Exception e) {
						System.out.println();
						System.out.println("올바른 메뉴번호를 입력하세요");
						break;
					}
					System.out.println("삭제가 완료되었습니다");
					break;
					
				case 3: //상품재고 더하기
					addIndex=0;
					getArray = new Goods[mg.getGoodsInGroupIndex()];
					
					//등록된 상품이 하나도 없는 경우
					if(mg.getListOfGoodsIndex() == 0) {
						System.out.println();
						System.out.println("등록된 상품이 없습니다");
						break;
					}
					
					System.out.println("입고할 상품의 대분류명: ");
					try {
						addGroupName = sc.nextLine();  //입고할 상품의 대분명 입력받기
						getArray = mg.findGoodsArray(addGroupName);  //findGoodsArray에 찾는 대분류명 넘기기
					}
					
					//없는 대분류명을 입력했을 때
					catch(Exception e){
						System.out.println();
						System.out.println(e.getMessage());
						break;
					}
					
					//입력받은 대분류에 해당하는 상품들 출력(목록 보여주기)
					for(int i=0;i < mg.getGoodsInGroupIndex();i++)
						System.out.println((i+1)+")" + getArray[i].getGoodsName());
					System.out.println();
				
					System.out.println("입고할 상품의 메뉴번호: ");
					
					try {
						addGoodsNum = sc.nextInt();  //입고할 상품의 메뉴번호 입력받기
						sc.nextLine();  //버퍼 삭제
					}
					//메뉴번호를 정수 타입으로 입력하지 않았을 때
					catch(InputMismatchException e) {
						System.out.println();
						System.out.println("올바른 메뉴번호를 입력하세요");
						sc.nextLine(); //버퍼 삭제
						break;
					}
					
					try {
						addIndex = mg.findGoodsIndex(addGoodsNum);  //입고할 상품의 인덱스 번호 찾기
					}
					//없는 메뉴번호를 입력했을 때
					catch(Exception e) {
						System.out.println();
						System.out.println("올바른 메뉴번호를 입력하세요");
						break;
					}
					
					while(true) {
						System.out.println("입고할 수량: ");
						try {
							addAmount = sc.nextInt();  //입고할 수량 입력받기
							sc.nextLine();  //버퍼 삭제
							break;
						}
						
						catch(InputMismatchException e) {
							System.out.println("숫자를 입력하세요");
							System.out.println();
							sc.nextLine(); //버퍼 삭제
						}
					}
					
					mg.listOfGoods[addIndex].addStock(addAmount); //입고시키기
					System.out.println("입고가 완료되었습니다");
					break;
					
				case 4:  //상품재고 빼기
					subtractIndex=0;
					getArray = new Goods[mg.getGoodsInGroupIndex()];
					
					//등록된 상품이 하나도 없는 경우
					if(mg.getListOfGoodsIndex() == 0) {
						System.out.println();
						System.out.println("등록된 상품이 없습니다");
						break;
					}
					
					System.out.println("출고할 상품의 대분류명: ");
					try {
						subtractGroupName = sc.nextLine();  //출고할 상품의 대분명 입력받기
						getArray = mg.findGoodsArray(subtractGroupName);  //findGoodsArray에 찾는 대분류명 넘기기
					}
					
					//없는 대분류명을 입력했을 때
					catch(Exception e){
						System.out.println();
						System.out.println(e.getMessage());
						break;
					}
					
					//입력받은 대분류에 해당하는 상품들 출력(목록 보여주기)
					for(int i=0;i < mg.getGoodsInGroupIndex();i++)
						System.out.println((i+1)+")" + getArray[i].getGoodsName());
					System.out.println();
				
					System.out.println("출고할 상품의 메뉴번호: ");
					
					try {
						subtractGoodsNum = sc.nextInt();  //출고할 상품의 메뉴번호 입력받기
						sc.nextLine();  //버퍼 삭제
					}
					//메뉴번호를 정수 타입으로 입력하지 않았을 때
					catch(InputMismatchException e) {
						System.out.println();
						System.out.println("올바른 메뉴번호를 입력하세요");
						sc.nextLine(); //버퍼 삭제
						break;
					}
					
					try {
						subtractIndex = mg.findGoodsIndex(subtractGoodsNum);  //출고할 상품의 인덱스 번호 찾기
					}
					//없는 메뉴번호를 입력했을 때
					catch(Exception e) {
						System.out.println();
						System.out.println("올바른 메뉴번호를 입력하세요");
						break;
					}
						
					System.out.println("출고할 수량: ");
					try {
						subtractAmount = sc.nextInt();  //출고할 수량 입력받기
						sc.nextLine();  //버퍼 삭제
					}
					
					//수량에 정수타입이 아닌 것을 입력했을 때
					catch(InputMismatchException e) {
						System.out.println();
						System.out.println("숫자를 입력하세요");
						sc.nextLine(); //버퍼 삭제
						break;
					}
						
					try {
						mg.listOfGoods[subtractIndex].subtractStock(subtractAmount); //출고시키기	
					}
					
					//출고량이 재고량보다 많을 때
					catch(Exception e) {
						System.out.println();
						System.out.println("재고가 부족합니다");
						break;
					}
					System.out.println("출고가 완료되었습니다");
					break;
					
				case 5: //상품정보 보기
	
					System.out.println("----------****저장된 상품 정보****----------");
					System.out.println("#등록된 상품 종류: " + (mg.getListOfGoodsIndex()) + "개");
					System.out.println();
					
					for(int i=0;i<mg.getListOfGoodsIndex();i++) {
						System.out.println("<"+(i+1)+">");
						System.out.println("1)상품명: " + mg.listOfGoods[i].getGoodsName());
						System.out.println("2)대분류명: " + mg.listOfGoods[i].getGroupName());
						System.out.println("3)가격: " + mg.listOfGoods[i].getPrice());
						System.out.println("4)상품번호: " + mg.listOfGoods[i].getGoodsNum());
						System.out.println("5)재고량: " + mg.listOfGoods[i].getStock());
						System.out.println("----------------------------");
					}
					System.out.println();
					break;
					
				case 6: // 매출액 보기
					
					System.out.println("----------****총 매출액****----------");
					System.out.println(mg.getTotalSales() + "원");
					System.out.println();
					break;
					
				case 7:  //상품정보 & 총매출액 저장하기(전체 시스템 종료시키기 전에 저장해야 함)
				
					System.out.println("-----****정보 저장하기****-----");
					System.out.println();
					
					try {
						fos = new FileOutputStream("File.dat");
						dos = new DataOutputStream(fos);	
						mg.save(dos);  //파일에 저장
						dos.writeInt(num); //다음번에 상품번호 할당 시 번호 안겹치게
					}
					 
					catch(Exception e) {
						System.out.println("저장 오류발생");
						break;
					}
					
					System.out.println("저장되었습니다");
					System.out.println();
					break;
			
				case 8:  //종료하기  
					System.out.println();
					System.out.println("프로그램을 종료합니다");
					break;
					 
				default:
					System.out.println();
					System.out.println("올바른 메뉴번호를 입력하세요"); 
					break;	
				}
			}		
		}
	}
}
