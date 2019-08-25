import java.io.*;

public class Management {
	Goods gd = new Goods(); //Goods 객체
	Goods listOfGoods[];  //Goods객체를 담을 배열
	Goods goodsInGroup[]; //대분류 속에 있는 모든 상품들의 정보 객체를 담는 배열
	int index1=0;  //listOfGoods 배열의 인덱스
	int index2=0;   //goodsInGroup의 인덱스 
	int theIndex=0;  //삭제하고 싶은 상품의 상품 배열(listOfGoods)에서의 인덱스 번호
	int totalSales=0; //총 매출액
	
	//디폴트 생성자
	public Management() { } 
	
	//생성자
	public Management(int count){
		try {
			listOfGoods= new Goods[count];
			index1=0;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//goods의 객체를 goods목록(리스트)에 삽입하는 함수
	public void insertGoods(Goods aboutGoods) throws Exception{
		listOfGoods[index1]=aboutGoods;  //UI에서 받은 상품정보를 객체에 담아 배열에 담기
		index1++;
		
		//상품 객체배열이 꽉 찬 경우
		if(index1 > listOfGoods.length)
			throw new Exception();
	}
	
	//상품배열(listOfGoods)의 인덱스번호 접근자
	public int getListOfGoodsIndex() {
		return index1;
	}
	
	//삭제 & 판매할 상품의 대분류 인덱스 찾는 함수 
	public Goods[] findGoodsArray(String groupName) throws Exception {
		goodsInGroup = new Goods[10000]; //대분류 속에 있는 모든 상품들의 정보 객체를 담는 배열
		index2=0;  //goodsInGroup의 인덱스 
		
		if(index1 == 0) //등록된 상품이 하나도 없는데 입력받은 경우 
			throw new Exception("등록된 상품이 없습니다");
		
		//입력받은 대분류에 속하는 상품들의 인덱스 번호 찾기
		for(int i=0;i<index1;i++) {
			if(listOfGoods[i].getGroupName().equals(groupName)) {  //입력받은 대분류명과 listOfGoods배열 속 객체 속 대분류명이 같을 때
				goodsInGroup[index2] = listOfGoods[i];  //goodsInGroup 배열에 해당 상품객체 넣어주기 
				index2++;  //goodInGroup의 인덱스 증가시키기	
			}
			
			//예외처리: i는 끝까지 돌았는데 index2가 0인 경우(goodsInGroup 배열에 아무 것도 안들어 있는 경우) 즉, 없는 대분류명을 입력했을 때
			else if(i==(index1-1) && index2==0) {
				throw new Exception("없는 대분류명입니다");
			}
		}
		return goodsInGroup;
	}
	
	//goodsInGroup배열의 인덱스번호 접근자
	public int getGoodsInGroupIndex() {
		return index2;
	}
	
	//삭제할 상품의 인덱스 찾는 함수
	public int findGoodsIndex(int goodsMenuNum) throws Exception{
		theIndex=0;  //새 대분류 입력 후 상품 찾을 때를 위해 초기화
		String goodsName = goodsInGroup[goodsMenuNum-1].getGoodsName(); //입력받은 상품 메뉴번호에 해당하는 상품명을 goodsName변수에 넘기기
		
		//없는 메뉴번호를 입력했을 때 예외처리
		if((goodsMenuNum-1) > index2 || goodsMenuNum < 0)
			throw new Exception();  
		
		//삭제할 상품명의 listOfGoods 배열 속 인덱스 찾기
		for(int i=0;i<index1;i++) {			
			if(listOfGoods[i].getGoodsName().equals(goodsName))
				theIndex=i;
		}
		
		return theIndex;
	}
	
	//상품 삭제하는 함수
	public void deleteGoods(int theIndex) {
		for(int r=theIndex;r<(index1-1);r++)  
			listOfGoods[r]=listOfGoods[r+1];  //해당 인덱스의 다음 인덱스부터 배열 끝까지의 정보를 앞으로 당기기 
		index1-=1;
	}
	
	//미리 사려는 상품의 총합 액수를 알려주는 함수
	public int sellEstimate(int index, int sellAmount) throws Exception{
		int price=0, priceSum=0;
		
		//재고가 부족한 경우
		if(listOfGoods[index].getStock() < sellAmount) {
			throw new Exception();
		}
		
		price = listOfGoods[index].getPrice(); //판매하려는 상품의 1개 가격 불러오기
		priceSum = price * sellAmount; //판매 총 가격
		return priceSum;
	}
	
	//상품 파는 함수
	public int sell(int index, int sellAmount) throws Exception{
		//재고가 부족한 경우
		if(listOfGoods[index].getStock() < sellAmount) {
			throw new Exception();
		}
		
		int priceSum=0, price=0;
		price = listOfGoods[index].getPrice(); //판매하려는 상품의 1개 가격 불러오기
		priceSum = price * sellAmount; //판매 총 가격
		listOfGoods[index].subtractStock(sellAmount); //재고에서 판매량만큼 빼주기
		totalSales+=priceSum; //매출 증가시키기
		
		return priceSum;  //실제 판 가격
	}
	
	//매출 총액 접근자
	public int getTotalSales() {
		return totalSales;
	}
	
	//상품정보 & 총매출 저장
	public void save(DataOutputStream f) throws Exception{
		try{
			f.writeInt(totalSales);  //총 매출 저장
			f.writeInt(index1);
			
			for(int i=0;i<index1;i++) {  //전체 상품정보 저장
				listOfGoods[i].save(f);
			}
		}
		
		catch(Exception e) {
			throw new Exception("정보 save 오류");
		}
	}
	
	//read해주기
	public Management read(DataInputStream f) throws Exception{
		try {
			totalSales = f.readInt();  //총 매출 read
			index1 = f.readInt();  //몇 개의 상품 read하는지
			
			for(int i=0;i<index1;i++) {  //전체 상품정보 read
				gd = new Goods();  //Goods객체를 새로 만들어줘서 상품정보를 저장한다 
				listOfGoods[i] = gd.read(f);
			}
			return this;
		}
		
		catch(Exception e) {
			throw new Exception("정보 read 오류");
		}
	}	
}
