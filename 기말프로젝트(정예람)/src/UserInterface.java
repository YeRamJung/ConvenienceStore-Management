import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class UserInterface{
	public static void main(String strg[]) {
		String goodsName="";  //��ǰ��
		String groupName=""; //��з���
		int price=0;  //��ǰ����
		int goodsNum=0;  //��ǰ��ȣ
		int num=0;  //��ǰ��ȣ�� ������Ű�� ����
		
		String deleteGroupName="";  //������ ��ǰ�� ��з���
		int deleteGoodsNum=0;  //������ ��ǰ���� �޴� ��ȣ
		
		String sellGroupName=""; //�Ǹ��ϴ� ������ ��з���
		int sellGoodsNum=0; //�Ǹ��ϴ� ��ǰ���� �޴� ��ȣ
		int sellAmount=0; //�Ǹ��� ��ǰ�� ����
		int sellIndex=0;  //�Ǹ��� ��ǰ�� ��ǰ��ü�迭������ �ε��� ��ȣ
		
		String addGroupName=""; //��� ���� ��ǰ�� ��з���
		int addGoodsNum=0; //��� ���� ��ǰ���� �޴� ��ȣ
		int addAmount=0;  //�԰��� ����
		int addIndex=0;  //�԰��� ��ǰ�� ��ǰ��ü�迭������ �ε��� ��ȣ
		
		String subtractGroupName=""; //��� �� ��ǰ�� ��з���
		int subtractGoodsNum=0; //��� �� ��ǰ���� �޴� ��ȣ
		int subtractAmount=0;  //����� ����
		int subtractIndex=0;  //����� ��ǰ�� ��ǰ��ü�迭������ �ε��� ��ȣ
		
		Goods[] getArray; 
		Scanner sc = new Scanner(System.in);  //Scanner ��ü
		Management mg = new Management(10000);  //Management ��ü
		UserMenu um = new UserMenu();  //����� �޴�(UserMenu) ����
		ManagerMenu mm = new ManagerMenu();  //������ �޴�(ManagerMenu) ��ü	
	
		
		FileInputStream fis = null;
		DataInputStream dis = null;
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		//���α׷� ���� �� read
		try {
			fis = new FileInputStream("File.dat");
			dis = new DataInputStream(fis);
			mg.read(dis);
			num = dis.readInt();
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("������ ã�� �� �����ϴ�");
		}
		catch(IOException ioe) {
			System.out.println("������ ���� �� �����ϴ�");
		}
		catch(Exception e) {  //mg.read���� throw�ϴ� �ͼ��� catch
			System.out.println(e.getMessage());
		}
		
		//���� �޴�
		while(true) {
			int whatMainMenu=0; 
			
			//���θ޴� ����
			System.out.println("------******������ ���� ���α׷�******------");
			System.out.println("1. ���� �����  & ��ǰ���� ����");
			System.out.println("2. ����� �ý���");
			System.out.println("3. ������ �ý���");
			System.out.println("4. �����ϱ�");
			System.out.println("-------------------------------------");
			System.out.println("���Ͻô� ���α׷� ��ȣ�� �Է����ּ��� : ");
			
			try {	
				whatMainMenu = sc.nextInt();  // ���θ޴� ��ȣ �Է¹ޱ� 
				sc.nextLine();  //���� �����
			}
			
			//whatMainMenu�� ���� Ÿ���� �ƴ� ���� ������ ��
			catch(InputMismatchException e) {
				System.out.println();
				System.out.println("�ùٸ� �޴���ȣ�� �Է��ϼ���"); 
				sc.nextLine();  //���ۻ���
				continue;
			}
			
			if(whatMainMenu>4 || whatMainMenu<1){ //1~4�� ���� ��ȣ�� �Է����� ��
				System.out.println();
				System.out.println("�ùٸ� �޴���ȣ�� �Է��ϼ���");
			}
	
			//4.������ ��
			else if(whatMainMenu==4) {
				try {  
					sc.close(); //Scanner close
					dis.close(); //read�ߴ� �� close
					fis.close();
					dos.close();
					fos.close();
				}
				catch(Exception e) {
				}
				
				System.out.println("���α׷��� �����մϴ�");
				break;
			}
			
			//1. ���� �����  & ��ǰ���� ������ ��
			else if(whatMainMenu==1) {
				System.out.println("<< ��������� �����  & ��ϵ� ��ǰ����  >>");
				System.out.println();
				
				System.out.println("----------****�� �����****----------");
				System.out.println(mg.getTotalSales() + "��");
				System.out.println();
				
				System.out.println("--------****����� ��ǰ ����****--------");
				System.out.println("#��ϵ� ��ǰ: " + (mg.getListOfGoodsIndex()) + "��");
				System.out.println();
				
				for(int i=0;i<mg.getListOfGoodsIndex();i++) {
					System.out.println("<"+(i+1)+">");
					System.out.println("1)��ǰ��: " + mg.listOfGoods[i].getGoodsName());
					System.out.println("2)��з���: " + mg.listOfGoods[i].getGroupName());
					System.out.println("3)����: " + mg.listOfGoods[i].getPrice());
					System.out.println("4)��ǰ��ȣ: " + mg.listOfGoods[i].getGoodsNum());
					System.out.println("5)���: " + mg.listOfGoods[i].getStock());
					System.out.println("----------------------------------");
				}
				System.out.println();
				continue;
			}
			
			//2.������� ��
			else if(whatMainMenu==2) {  
				um.menu1();  //����� �޴� �����ֱ�
				int whatMenu1=0;
				
				try {
					whatMenu1=sc.nextInt();  //����� �޴� ��ȣ �Է¹ޱ�
					sc.nextLine();  //���� �����
				}
				
				//whatMenu1�� ���� Ÿ���� �ƴ� ���� ������ ��
				catch(InputMismatchException e) {
					System.out.println();
					System.out.println("�ùٸ� �޴���ȣ�� �Է��ϼ���");
					sc.nextLine();  //���ۻ���
					continue;
				}
				
				switch(whatMenu1) {  
				case 1: //��ǰ �Ǹ��ϱ�
					
					getArray = new Goods[mg.getGoodsInGroupIndex()]; //findGoodsArray�� ã�� ��з��� �ѱ��
						
					//��ϵ� ��ǰ�� �ϳ��� ���� ���
					if(mg.getListOfGoodsIndex() == 0) {
						System.out.println();
						System.out.println("��ϵ� ��ǰ�� �����ϴ�");
						break;
					}
						
					System.out.println("�Ǹ��Ϸ��� ��ǰ�� ��з���: ");
					try{
						sellGroupName = sc.nextLine(); //�Ǹ��� ��ǰ�� ��з��� �Է¹ޱ�
						getArray = mg.findGoodsArray(sellGroupName);
					}
					
					//���� ��з��� �Է����� ��
					catch(Exception e) {
						System.out.println();
						System.out.println(e.getMessage());
						break;
					}	
				
					//�Է¹��� ��з��� �ش��ϴ� ��ǰ�� ���(��� �����ֱ�)
					for(int i=0;i<mg.getGoodsInGroupIndex();i++) {
						System.out.println((i+1)+")" + getArray[i].getGoodsName());
					}	
					System.out.println();
							
					System.out.println("�Ǹ��� ��ǰ�� �޴���ȣ: ");  //find �Ǹ��� ��ǰ
					try {
						sellGoodsNum = sc.nextInt();  //�Ǹ��� ��ǰ �޴���ȣ �Է¹ޱ�
						sc.nextLine();  //���� ����
					}
					
					//�޴���ȣ�� ����Ÿ������ �Է����� �ʾ��� ��
					catch(InputMismatchException e) {
						System.out.println();
						System.out.println("�ùٸ� �޴���ȣ�� �Է��ϼ���");
						sc.nextLine();  //���ۻ���
						break;
					}
					
					try {	
						sellIndex = mg.findGoodsIndex(sellGoodsNum);  //�Ǹ��� ��ǰ�� �ε��� ��ȣ ã��
					}
					//���� �޴���ȣ�� �Է����� ��
					catch(Exception e) {
						System.out.println(); 
						System.out.println("�ùٸ� �޴���ȣ�� �Է��ϼ���");
						break;
					}
						
					System.out.println("�Ǹ� ����: ");
					try {
						sellAmount = sc.nextInt();  //�Ǹ��� ��ǰ�� ���� �Է¹ޱ�
						sc.nextLine(); //���� ����
					}
					
					//�Ǹż����� ���� Ÿ���� �ƴ� ���� �Է����� ��
					catch(InputMismatchException e) {
						System.out.println();
						System.out.println("���ڸ� �Է��ϼ���");
						sc.nextLine();
						break;
					}
				
					try {
						int priceSum = mg.sellEstimate(sellIndex, sellAmount); //�̸� �Ǹ� �׼� �����ֱ�
						System.out.println("���� �Ѿ�: " + priceSum); 
					}
					//��� ������ ��
					catch(Exception e) {
						System.out.println();
						System.out.println("��� �����մϴ�");
						break;
					}
					
					try {
						mg.sell(sellIndex, sellAmount); //�Ǹ��ϱ�
					}
					//��� ������ ��
					catch(Exception e) {
						System.out.println();
						System.out.println("��� �����մϴ�");
						break;
					}
						
					System.out.println("�ǸŰ� �Ϸ�Ǿ����ϴ�");
					System.out.println("������� �� �����: " + mg.getTotalSales());	
					System.out.println();
					break;
						
					case 2: //���� �� ���� �޴��� ����
						System.out.println();
						System.out.println("���α׷��� �����մϴ�");
						break;	
					
					default:
						System.out.println();
						System.out.println("�ùٸ� �޴���ȣ�� �Է��ϼ���"); 
						break;	
				}		
			}
				
			//3.�������� ��
			else if(whatMainMenu==3) {
				mm.menu2();  //������ �޴� �����ֱ�
				int whatMenu2=0;
				
				try {
					whatMenu2=sc.nextInt();   //������ �޴� ��ȣ �Է¹ޱ�
					sc.nextLine();  //���� �����
				}
				//whatMenu2�� ���� Ÿ���� �ƴ� ���� ������ ��
				catch(InputMismatchException e) {
					System.out.println();
					System.out.println("�ùٸ� �޴���ȣ�� �Է��ϼ���");
					sc.nextLine();  //���ۻ���
					continue;
				}
				
				switch(whatMenu2) {
				case 1: //��ǰ ����
					
					//����ڷκ��� ��ϵ� ��ǰ���� �Է¹ޱ�(��ǰ�ڵ��ȣ �ڵ� �ο�)
					System.out.println("����� ��ǰ��: ");
					goodsName=sc.nextLine();
					
					System.out.println("����� ��ǰ�� ��з���: ");
					groupName=sc.nextLine();
					
					System.out.println("��ǰ ����: ");
					try {
						price=sc.nextInt();  
						sc.nextLine();  //���� ����
					}	
					
					//���� Ÿ���� �ƴ� ���� ������ ��
					catch(InputMismatchException e) {
						System.out.println();
						System.out.println("���ڸ� �Է����ּ���");
						sc.nextLine(); //���ۻ���
						break;
					}
					
					goodsNum = 1000000 + num;  //��ǰ��ȣ �Ҵ�
					num++;
					System.out.println("��ǰ ��ȣ: " + goodsNum);

					Goods gd = new Goods(goodsName, groupName, price, goodsNum);  //Goods��ü
					
					try {
						mg.insertGoods(gd);  //��ǰ ������  Management�� �ִ� insertGoods�Լ��� �Ѱ��ֱ�
					}
					catch(Exception e) {
						System.out.println();
						System.out.println("�� �̻� ��ǰ �Է��� �Ұ��մϴ�");
						break;
					}
					break;
							
				case 2:	//���� ����
					deleteGoodsNum=0;
					getArray = new Goods[mg.getGoodsInGroupIndex()];  //findGoodsArray�� return�ϴ� �� �޴� �迭
					
					//��ϵ� ��ǰ�� �ϳ��� ���� ���
					if(mg.getListOfGoodsIndex() == 0) {
						System.out.println();
						System.out.println("��ϵ� ��ǰ�� �����ϴ�");
						break;
					}
					
					System.out.println("-------****��ǰ���� �����ϱ�****-------");
					System.out.println("������ ��ǰ�� ��з���: ");   
				
					try {
						deleteGroupName = sc.nextLine();  //������ ��ǰ�� ��и� �Է¹ޱ�	
						getArray = mg.findGoodsArray(deleteGroupName);  //findGoodsArray�� ã�� ��з��� �ѱ��
					}
				
					//���� ��з����� �Է����� ��
					catch(Exception e){
						System.out.println();
						System.out.println(e.getMessage());
						break;
					}
				
					System.out.println("�� ����: " + mg.getGoodsInGroupIndex());
					//�Է¹��� ��з��� �ش��ϴ� ��ǰ�� ���(��� �����ֱ�)
					for(int i=0;i<mg.getGoodsInGroupIndex();i++)
						System.out.println((i+1)+")" + getArray[i].getGoodsName());
					System.out.println();
					
					System.out.println("������ ��ǰ�� �޴���ȣ: ");  //find ������ ��ǰ				
					try {
						deleteGoodsNum=sc.nextInt();  //������ ��ǰ �޴���ȣ �Է¹ޱ�
						sc.nextLine();  //���� ����
					}
					
					//�޴���ȣ�� ���� Ÿ������ �Է����� �ʾ��� ��
					catch(InputMismatchException e) {		
						System.out.println();
						System.out.println("�ùٸ� �޴���ȣ�� �Է��ϼ���");
						sc.nextLine();
						break;
					}
					
					try {	
						mg.deleteGoods(mg.findGoodsIndex(deleteGoodsNum));  //�� ��ǰ �迭���� �����ϱ�
					}
					
					//���� �޴���ȣ�� �Է����� ��
					catch(Exception e) {
						System.out.println();
						System.out.println("�ùٸ� �޴���ȣ�� �Է��ϼ���");
						break;
					}
					System.out.println("������ �Ϸ�Ǿ����ϴ�");
					break;
					
				case 3: //��ǰ��� ���ϱ�
					addIndex=0;
					getArray = new Goods[mg.getGoodsInGroupIndex()];
					
					//��ϵ� ��ǰ�� �ϳ��� ���� ���
					if(mg.getListOfGoodsIndex() == 0) {
						System.out.println();
						System.out.println("��ϵ� ��ǰ�� �����ϴ�");
						break;
					}
					
					System.out.println("�԰��� ��ǰ�� ��з���: ");
					try {
						addGroupName = sc.nextLine();  //�԰��� ��ǰ�� ��и� �Է¹ޱ�
						getArray = mg.findGoodsArray(addGroupName);  //findGoodsArray�� ã�� ��з��� �ѱ��
					}
					
					//���� ��з����� �Է����� ��
					catch(Exception e){
						System.out.println();
						System.out.println(e.getMessage());
						break;
					}
					
					//�Է¹��� ��з��� �ش��ϴ� ��ǰ�� ���(��� �����ֱ�)
					for(int i=0;i < mg.getGoodsInGroupIndex();i++)
						System.out.println((i+1)+")" + getArray[i].getGoodsName());
					System.out.println();
				
					System.out.println("�԰��� ��ǰ�� �޴���ȣ: ");
					
					try {
						addGoodsNum = sc.nextInt();  //�԰��� ��ǰ�� �޴���ȣ �Է¹ޱ�
						sc.nextLine();  //���� ����
					}
					//�޴���ȣ�� ���� Ÿ������ �Է����� �ʾ��� ��
					catch(InputMismatchException e) {
						System.out.println();
						System.out.println("�ùٸ� �޴���ȣ�� �Է��ϼ���");
						sc.nextLine(); //���� ����
						break;
					}
					
					try {
						addIndex = mg.findGoodsIndex(addGoodsNum);  //�԰��� ��ǰ�� �ε��� ��ȣ ã��
					}
					//���� �޴���ȣ�� �Է����� ��
					catch(Exception e) {
						System.out.println();
						System.out.println("�ùٸ� �޴���ȣ�� �Է��ϼ���");
						break;
					}
					
					while(true) {
						System.out.println("�԰��� ����: ");
						try {
							addAmount = sc.nextInt();  //�԰��� ���� �Է¹ޱ�
							sc.nextLine();  //���� ����
							break;
						}
						
						catch(InputMismatchException e) {
							System.out.println("���ڸ� �Է��ϼ���");
							System.out.println();
							sc.nextLine(); //���� ����
						}
					}
					
					mg.listOfGoods[addIndex].addStock(addAmount); //�԰��Ű��
					System.out.println("�԰� �Ϸ�Ǿ����ϴ�");
					break;
					
				case 4:  //��ǰ��� ����
					subtractIndex=0;
					getArray = new Goods[mg.getGoodsInGroupIndex()];
					
					//��ϵ� ��ǰ�� �ϳ��� ���� ���
					if(mg.getListOfGoodsIndex() == 0) {
						System.out.println();
						System.out.println("��ϵ� ��ǰ�� �����ϴ�");
						break;
					}
					
					System.out.println("����� ��ǰ�� ��з���: ");
					try {
						subtractGroupName = sc.nextLine();  //����� ��ǰ�� ��и� �Է¹ޱ�
						getArray = mg.findGoodsArray(subtractGroupName);  //findGoodsArray�� ã�� ��з��� �ѱ��
					}
					
					//���� ��з����� �Է����� ��
					catch(Exception e){
						System.out.println();
						System.out.println(e.getMessage());
						break;
					}
					
					//�Է¹��� ��з��� �ش��ϴ� ��ǰ�� ���(��� �����ֱ�)
					for(int i=0;i < mg.getGoodsInGroupIndex();i++)
						System.out.println((i+1)+")" + getArray[i].getGoodsName());
					System.out.println();
				
					System.out.println("����� ��ǰ�� �޴���ȣ: ");
					
					try {
						subtractGoodsNum = sc.nextInt();  //����� ��ǰ�� �޴���ȣ �Է¹ޱ�
						sc.nextLine();  //���� ����
					}
					//�޴���ȣ�� ���� Ÿ������ �Է����� �ʾ��� ��
					catch(InputMismatchException e) {
						System.out.println();
						System.out.println("�ùٸ� �޴���ȣ�� �Է��ϼ���");
						sc.nextLine(); //���� ����
						break;
					}
					
					try {
						subtractIndex = mg.findGoodsIndex(subtractGoodsNum);  //����� ��ǰ�� �ε��� ��ȣ ã��
					}
					//���� �޴���ȣ�� �Է����� ��
					catch(Exception e) {
						System.out.println();
						System.out.println("�ùٸ� �޴���ȣ�� �Է��ϼ���");
						break;
					}
						
					System.out.println("����� ����: ");
					try {
						subtractAmount = sc.nextInt();  //����� ���� �Է¹ޱ�
						sc.nextLine();  //���� ����
					}
					
					//������ ����Ÿ���� �ƴ� ���� �Է����� ��
					catch(InputMismatchException e) {
						System.out.println();
						System.out.println("���ڸ� �Է��ϼ���");
						sc.nextLine(); //���� ����
						break;
					}
						
					try {
						mg.listOfGoods[subtractIndex].subtractStock(subtractAmount); //����Ű��	
					}
					
					//����� ������� ���� ��
					catch(Exception e) {
						System.out.println();
						System.out.println("��� �����մϴ�");
						break;
					}
					System.out.println("��� �Ϸ�Ǿ����ϴ�");
					break;
					
				case 5: //��ǰ���� ����
	
					System.out.println("----------****����� ��ǰ ����****----------");
					System.out.println("#��ϵ� ��ǰ ����: " + (mg.getListOfGoodsIndex()) + "��");
					System.out.println();
					
					for(int i=0;i<mg.getListOfGoodsIndex();i++) {
						System.out.println("<"+(i+1)+">");
						System.out.println("1)��ǰ��: " + mg.listOfGoods[i].getGoodsName());
						System.out.println("2)��з���: " + mg.listOfGoods[i].getGroupName());
						System.out.println("3)����: " + mg.listOfGoods[i].getPrice());
						System.out.println("4)��ǰ��ȣ: " + mg.listOfGoods[i].getGoodsNum());
						System.out.println("5)���: " + mg.listOfGoods[i].getStock());
						System.out.println("----------------------------");
					}
					System.out.println();
					break;
					
				case 6: // ����� ����
					
					System.out.println("----------****�� �����****----------");
					System.out.println(mg.getTotalSales() + "��");
					System.out.println();
					break;
					
				case 7:  //��ǰ���� & �Ѹ���� �����ϱ�(��ü �ý��� �����Ű�� ���� �����ؾ� ��)
				
					System.out.println("-----****���� �����ϱ�****-----");
					System.out.println();
					
					try {
						fos = new FileOutputStream("File.dat");
						dos = new DataOutputStream(fos);	
						mg.save(dos);  //���Ͽ� ����
						dos.writeInt(num); //�������� ��ǰ��ȣ �Ҵ� �� ��ȣ �Ȱ�ġ��
					}
					 
					catch(Exception e) {
						System.out.println("���� �����߻�");
						break;
					}
					
					System.out.println("����Ǿ����ϴ�");
					System.out.println();
					break;
			
				case 8:  //�����ϱ�  
					System.out.println();
					System.out.println("���α׷��� �����մϴ�");
					break;
					 
				default:
					System.out.println();
					System.out.println("�ùٸ� �޴���ȣ�� �Է��ϼ���"); 
					break;	
				}
			}		
		}
	}
}
