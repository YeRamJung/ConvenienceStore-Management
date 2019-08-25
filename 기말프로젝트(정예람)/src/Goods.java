import java.io.*;

public class Goods{
	String groupName; //��з���
	String goodsName; //��ǰ��
	int price=0; //����
	int goodsNum; //��ǰ��ȣ
	int stock=0; //������

	//����Ʈ ������
	public Goods(){ }

	//������(��ǰ����)
	public Goods(String goodsName, String groupName, int price, int goodsNum){
		this.goodsName=goodsName;
		this.groupName=groupName;
		this.price=price;
		this.goodsNum=goodsNum;
	}
	
	//��ǰ�� ������
	public String setGoodsName(String goodsName) {
		this.goodsName=goodsName;
		return goodsName;
	}
	
	//��ǰ�� ������
	public String getGoodsName() {
		return goodsName;
	}
	
	//��з��� ������
	public String setGroupName(String groupName) {
		this.groupName=groupName;
		return groupName;
	}
	
	//��з��� ������
	public String getGroupName() {
		return groupName;
	}
	
	//���� ������
	public int setPrice(int price) {
		this.price=price;
		return price;
	}
	
	//���� ������
	public int getPrice() {
		return price;
	}
	
	//��ǰ��ȣ ������
	public int setGoodsNum(int goodsNum) {
		this.goodsNum=goodsNum;
		return goodsNum;
	}
	
	//��ǰ��ȣ ������
	public int getGoodsNum() {
		return goodsNum;
	}
	
	//��� ������
	public int setStock(int stock) {
		this.stock=stock;
		return stock;
	}
	
	//��� ������
	public int getStock() {
		return stock;
	}

	//��� ����
	public void addStock(int stockAmount) {
		stock += stockAmount;
	}
	
	//��� ��
	public void subtractStock(int stockAmount) throws Exception{
		//ũ�� �ʰ� ��
		if(stock < stockAmount) {
			throw new Exception();
		} 
		else
			stock -= stockAmount;
	}
	
	//��ü ��ǰ���� ����
	public void save(DataOutputStream f) throws Exception{
		try{
			f.writeUTF(goodsName);
			f.writeUTF(groupName);
			f.writeInt(price);
			f.writeInt(goodsNum);
			f.writeInt(stock);
		}
		
		catch(Exception e) {
			throw new Exception("��ǰ���� save ����");
		}	
	}
	
	//read���ֱ�
	public Goods read (DataInputStream f) throws Exception{
		try {
			goodsName = f.readUTF();
			groupName = f.readUTF();
			price = f.readInt();
			goodsNum = f.readInt();
			stock = f.readInt();
			return this;
		}
		catch(Exception e) {
			throw new Exception("��ǰ���� read ����");
		}
	}
}