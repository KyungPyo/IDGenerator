import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class INGenerator {
	public static void main(String[] args) {
		if (args.length == 0) {
			// Argument로 입력된 값이 없는 경우
			System.out.println("만들 사원번호의 개수를 입력해 주세요.");
			
		} else {
			// Argument로 값이 입력된 경우
			try {
				int count = Integer.parseInt(args[0]);	// 만들 사원번호 개수 입력
				if (count <= 0) {
					throw new NumberFormatException();
				}
				
				ArrayList<IDNumber> memberList = new ArrayList<>();
				for (int i=0; i<count; i++) {
					IDNumber newID = new IDNumber();
					newID.generateIN(memberList);
					memberList.add(newID);
					
					// 사원을 한명 추가할 때마다 번호 순으로 정렬한다.
					Collections.sort(memberList, new Comparator<IDNumber>(){

						// 랜덤으로 생성된 번호 순으로 정렬
						@Override
						public int compare(IDNumber arg0, IDNumber arg1) {
							int result;
							if (arg0.getRandomNumber() < arg1.getRandomNumber()) {
								result = -1;
							} else if (arg0.getRandomNumber() > arg1.getRandomNumber()) {
								result = 1;
							} else {
								result = 0;
							}
							return result;
						}
					}); 
				}
				
				// 저장된 사원 리스트에서 사원번호와 점수를 차례대로 출력한다.
				for (int j=0; j<memberList.size(); j++){
					System.out.println(memberList.get(j).getIDString() + " " + memberList.get(j).getScore());
				}
				
			} catch (NumberFormatException e) {
				System.out.println("만들 사원번호의 개수는 1이상의 자연수로 입력해 주세요.");
			}
			
		}
	}
}
