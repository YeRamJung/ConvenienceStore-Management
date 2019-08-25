import java.io.*;

public class Goods{
	String groupName; //대분류명
	String goodsName; //상품명
	int price=0; //가격
	int goodsNum; //상품번호
	int stock=0; //재고수량

	//디폴트 생성자
	public Goods(){ }

	//생성자(상품정보)
	public Goods(String goodsName, String groupName, int price, int goodsNum){
		this.goodsName=goodsName;
		this.groupName=groupName;
		this.price=price;
		this.goodsNum=goodsNum;
	}
	
	//상품명 설정자
	public String setGoodsName(String goodsName) {
		this.goodsName=goodsName;
		return goodsName;
	}
	
	//상품명 접근자
	public String getGoodsName() {
		return goodsName;
	}
	
	//대분류명 설정자
	public String setGroupName(String groupName) {
		this.groupName=groupName;
		return groupName;
	}
	
	//대분류명 접근자
	public String getGroupName() {
		return groupName;
	}
	
	//가격 설정자
	public int setPrice(int price) {
		this.price=price;
		return price;
	}
	
	//가격 접근자
	public int getPrice() {
		return price;
	}
	
	//상품번호 설정자
	public int setGoodsNum(int goodsNum) {
		this.goodsNum=goodsNum;
		return goodsNum;
	}
	
	//상품번호 접근자
	public int getGoodsNum() {
		return goodsNum;
	}
	
	//재고 설정자
	public int setStock(int stock) {
		this.stock=stock;
		return stock;
	}
	
	//재고 접근자
	public int getStock() {
		return stock;
	}

	//재고를 더함
	public void addStock(int stockAmount) {
		stock += stockAmount;
	}
	
	//재고를 뺌
	public void subtractStock(int stockAmount) throws Exception{
		//크기 초과 시
		if(stock < stockAmount) {
			throw new Exception();
		} 
		else
			stock -= stockAmount;
	}
	
	//전체 상품정보 저장
	public void save(DataOutputStream f) throws Exception{
		try{
			f.writeUTF(goodsName);
			f.writeUTF(groupName);
			f.writeInt(price);
			f.writeInt(goodsNum);
			f.writeInt(stock);
		}
		
		catch(Exception e) {
			throw new Exception("상품정보 save 오류");
		}	
	}
	
	//read해주기
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
			throw new Exception("상품정보 read 오류");
		}
	}
}