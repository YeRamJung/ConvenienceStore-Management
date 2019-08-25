import java.io.*;

public class Management {
	Goods gd = new Goods(); //Goods ��ü
	Goods listOfGoods[];  //Goods��ü�� ���� �迭
	Goods goodsInGroup[]; //��з� �ӿ� �ִ� ��� ��ǰ���� ���� ��ü�� ��� �迭
	int index1=0;  //listOfGoods �迭�� �ε���
	int index2=0;   //goodsInGroup�� �ε��� 
	int theIndex=0;  //�����ϰ� ���� ��ǰ�� ��ǰ �迭(listOfGoods)������ �ε��� ��ȣ
	int totalSales=0; //�� �����
	
	//����Ʈ ������
	public Management() { } 
	
	//������
	public Management(int count){
		try {
			listOfGoods= new Goods[count];
			index1=0;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//goods�� ��ü�� goods���(����Ʈ)�� �����ϴ� �Լ�
	public void insertGoods(Goods aboutGoods) throws Exception{
		listOfGoods[index1]=aboutGoods;  //UI���� ���� ��ǰ������ ��ü�� ��� �迭�� ���
		index1++;
		
		//��ǰ ��ü�迭�� �� �� ���
		if(index1 > listOfGoods.length)
			throw new Exception();
	}
	
	//��ǰ�迭(listOfGoods)�� �ε�����ȣ ������
	public int getListOfGoodsIndex() {
		return index1;
	}
	
	//���� & �Ǹ��� ��ǰ�� ��з� �ε��� ã�� �Լ� 
	public Goods[] findGoodsArray(String groupName) throws Exception {
		goodsInGroup = new Goods[10000]; //��з� �ӿ� �ִ� ��� ��ǰ���� ���� ��ü�� ��� �迭
		index2=0;  //goodsInGroup�� �ε��� 
		
		if(index1 == 0) //��ϵ� ��ǰ�� �ϳ��� ���µ� �Է¹��� ��� 
			throw new Exception("��ϵ� ��ǰ�� �����ϴ�");
		
		//�Է¹��� ��з��� ���ϴ� ��ǰ���� �ε��� ��ȣ ã��
		for(int i=0;i<index1;i++) {
			if(listOfGoods[i].getGroupName().equals(groupName)) {  //�Է¹��� ��з���� listOfGoods�迭 �� ��ü �� ��з����� ���� ��
				goodsInGroup[index2] = listOfGoods[i];  //goodsInGroup �迭�� �ش� ��ǰ��ü �־��ֱ� 
				index2++;  //goodInGroup�� �ε��� ������Ű��	
			}
			
			//����ó��: i�� ������ ���Ҵµ� index2�� 0�� ���(goodsInGroup �迭�� �ƹ� �͵� �ȵ�� �ִ� ���) ��, ���� ��з����� �Է����� ��
			else if(i==(index1-1) && index2==0) {
				throw new Exception("���� ��з����Դϴ�");
			}
		}
		return goodsInGroup;
	}
	
	//goodsInGroup�迭�� �ε�����ȣ ������
	public int getGoodsInGroupIndex() {
		return index2;
	}
	
	//������ ��ǰ�� �ε��� ã�� �Լ�
	public int findGoodsIndex(int goodsMenuNum) throws Exception{
		theIndex=0;  //�� ��з� �Է� �� ��ǰ ã�� ���� ���� �ʱ�ȭ
		String goodsName = goodsInGroup[goodsMenuNum-1].getGoodsName(); //�Է¹��� ��ǰ �޴���ȣ�� �ش��ϴ� ��ǰ���� goodsName������ �ѱ��
		
		//���� �޴���ȣ�� �Է����� �� ����ó��
		if((goodsMenuNum-1) > index2 || goodsMenuNum < 0)
			throw new Exception();  
		
		//������ ��ǰ���� listOfGoods �迭 �� �ε��� ã��
		for(int i=0;i<index1;i++) {			
			if(listOfGoods[i].getGoodsName().equals(goodsName))
				theIndex=i;
		}
		
		return theIndex;
	}
	
	//��ǰ �����ϴ� �Լ�
	public void deleteGoods(int theIndex) {
		for(int r=theIndex;r<(index1-1);r++)  
			listOfGoods[r]=listOfGoods[r+1];  //�ش� �ε����� ���� �ε������� �迭 �������� ������ ������ ���� 
		index1-=1;
	}
	
	//�̸� ����� ��ǰ�� ���� �׼��� �˷��ִ� �Լ�
	public int sellEstimate(int index, int sellAmount) throws Exception{
		int price=0, priceSum=0;
		
		//��� ������ ���
		if(listOfGoods[index].getStock() < sellAmount) {
			throw new Exception();
		}
		
		price = listOfGoods[index].getPrice(); //�Ǹ��Ϸ��� ��ǰ�� 1�� ���� �ҷ�����
		priceSum = price * sellAmount; //�Ǹ� �� ����
		return priceSum;
	}
	
	//��ǰ �Ĵ� �Լ�
	public int sell(int index, int sellAmount) throws Exception{
		//��� ������ ���
		if(listOfGoods[index].getStock() < sellAmount) {
			throw new Exception();
		}
		
		int priceSum=0, price=0;
		price = listOfGoods[index].getPrice(); //�Ǹ��Ϸ��� ��ǰ�� 1�� ���� �ҷ�����
		priceSum = price * sellAmount; //�Ǹ� �� ����
		listOfGoods[index].subtractStock(sellAmount); //����� �Ǹŷ���ŭ ���ֱ�
		totalSales+=priceSum; //���� ������Ű��
		
		return priceSum;  //���� �� ����
	}
	
	//���� �Ѿ� ������
	public int getTotalSales() {
		return totalSales;
	}
	
	//��ǰ���� & �Ѹ��� ����
	public void save(DataOutputStream f) throws Exception{
		try{
			f.writeInt(totalSales);  //�� ���� ����
			f.writeInt(index1);
			
			for(int i=0;i<index1;i++) {  //��ü ��ǰ���� ����
				listOfGoods[i].save(f);
			}
		}
		
		catch(Exception e) {
			throw new Exception("���� save ����");
		}
	}
	
	//read���ֱ�
	public Management read(DataInputStream f) throws Exception{
		try {
			totalSales = f.readInt();  //�� ���� read
			index1 = f.readInt();  //�� ���� ��ǰ read�ϴ���
			
			for(int i=0;i<index1;i++) {  //��ü ��ǰ���� read
				gd = new Goods();  //Goods��ü�� ���� ������༭ ��ǰ������ �����Ѵ� 
				listOfGoods[i] = gd.read(f);
			}
			return this;
		}
		
		catch(Exception e) {
			throw new Exception("���� read ����");
		}
	}	
}
