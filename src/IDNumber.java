import java.util.ArrayList;
import java.util.Random;

public class IDNumber {
	private int randomNumber;
	private int score;
	
	public IDNumber(){}
	
	// method : �����ȣ �� ���� ����
	// return : void
	// parameter : memberList(������ �����ȣ ����Ʈ)
	// use : ���ο� �����ȣ�� ������ �������� �����Ѵ�. List�� ���� ������ �ߺ����� üũ�Ѵ�.
	public void generateIN(ArrayList<IDNumber> memberList){
		Random random = new Random();
		
		if (memberList == null || memberList.size() == 0) {
			// ����Ʈ�� ����� ���� ������ �ߺ�üũ�� ���� �ʰ� �����.
			randomNumber = random.nextInt(100000);
			score = random.nextInt(101);
			
		} else {
			// �ߺ��Ǵ� �����ȣ�� ���� ������ �����
			do{
				randomNumber = random.nextInt(100000);
			} while (checkOverlap(memberList, 0, memberList.size()-1, randomNumber));
			
			score = random.nextInt(101);
		}
	}
	
	// method : �����ȣ �ߺ� ���� Ȯ��
	// return : boolean(�ߺ��Ǹ� true, �ƴϸ� false)
	// parameter : memberList(������ �����ȣ ����Ʈ), min(�ּ� �ε���), max(�ִ� �ε���), target(�ߺ� üũ�� ��)
	// use : ���ĵ� ��� ����Ʈ�� ������ ��������� �ߺ��� Ȯ���Ѵ�. ��� ���� ũ�⸦ ���Ͽ� ������ �ݾ� �������� ������ üũ�Ѵ�.
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
	
	// method : �����ȣ ��Ĵ�� �޾ƿ���
	// return : String(�����ȣ ��Ŀ� ���缭 ����)
	// parameter : void
	// use : �����ȣ�� ��Ŀ� ���缭 �տ� NT �� ���̰� ���ڰ� 5�ڸ� �̸��̸� 0�� �ٿ� ���� �� �����Ѵ�.
	public String getIDString(){
		StringBuffer number = new StringBuffer();
		number.append(randomNumber);
		int length = number.length();
		for (int i=0; i < 5-length; i++)
			number.insert(0, 0);
		
		return "NT" + number.toString();
	}
}
