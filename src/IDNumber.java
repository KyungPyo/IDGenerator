import java.util.ArrayList;
import java.util.Random;

public class IDNumber {
	private int randomNumber;
	private int score;
	
	public IDNumber(){}
	
	// method : 사원번호 및 점수 생성
	// return : void
	// parameter : memberList(생성된 사원번호 리스트)
	// use : 새로운 사원번호와 점수를 랜덤으로 생성한다. List에 값이 있으면 중복여부 체크한다.
	public void generateIN(ArrayList<IDNumber> memberList){
		Random random = new Random();
		
		if (memberList == null || memberList.size() == 0) {
			// 리스트에 저장된 값이 없으면 중복체크를 하지 않고 만든다.
			randomNumber = random.nextInt(100000);
			score = random.nextInt(101);
			
		} else {
			// 중복되는 사원번호가 없을 때까지 재생성
			do{
				randomNumber = random.nextInt(100000);
			} while (checkOverlap(memberList, 0, memberList.size()-1, randomNumber));
			
			score = random.nextInt(101);
		}
	}
	
	// method : 사원번호 중복 여부 확인
	// return : boolean(중복되면 true, 아니면 false)
	// parameter : memberList(생성된 사원번호 리스트), min(최소 인덱스), max(최대 인덱스), target(중복 체크할 값)
	// use : 정렬된 사원 리스트를 가지고 재귀적으로 중복을 확인한다. 가운데 값과 크기를 비교하여 영역을 반씩 좁혀가는 식으로 체크한다.
	private boolean checkOverlap(ArrayList<IDNumber> memberList, int min, int max, int target){
		if (min == max) {
			return memberList.get(min).getRandomNumber() == target;
		} else {
			int mid = min + (max-min)/2;
			int midValue = memberList.get(mid).getRandomNumber(); 
			if (midValue == target) {
				return true;
			} else if (midValue < target) {
				return checkOverlap(memberList, mid+1, max, target);
			} else {	// (midValue > target)
				if (mid-1 < min) {
					return false;
				} else {
					return checkOverlap(memberList, min, mid-1, target);
				}
			}
		}
	}
	
	public int getRandomNumber(){
		return randomNumber;
	}
	
	public int getScore(){
		return score;
	}
	
	// method : 사원번호 양식대로 받아오기
	// return : String(사원번호 양식에 맞춰서 리턴)
	// parameter : void
	// use : 사원번호를 양식에 맞춰서 앞에 NT 를 붙이고 숫자가 5자리 미만이면 0을 붙여 넣은 후 리턴한다.
	public String getIDString(){
		StringBuffer number = new StringBuffer();
		number.append(randomNumber);
		int length = number.length();
		for (int i=0; i < 5-length; i++)
			number.insert(0, 0);
		
		return "NT" + number.toString();
	}
}
