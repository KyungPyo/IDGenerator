import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class INGenerator {
	public static void main(String[] args) {
		if (args.length == 0) {
			// Argument�� �Էµ� ���� ���� ���
			System.out.println("���� �����ȣ�� ������ �Է��� �ּ���.");
			
		} else {
			// Argument�� ���� �Էµ� ���
			try {
				int count = Integer.parseInt(args[0]);	// ���� �����ȣ ���� �Է�
				if (count <= 0) {
					throw new NumberFormatException();
				}
				
				ArrayList<IDNumber> memberList = new ArrayList<>();
				for (int i=0; i<count; i++) {
					IDNumber newID = new IDNumber();
					newID.generateIN(memberList);
					memberList.add(newID);
					
					// ����� �Ѹ� �߰��� ������ ��ȣ ������ �����Ѵ�.
					Collections.sort(memberList, new Comparator<IDNumber>(){

						// �������� ������ ��ȣ ������ ����
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
				
				// ����� ��� ����Ʈ���� �����ȣ�� ������ ���ʴ�� ����Ѵ�.
				for (int j=0; j<memberList.size(); j++){
					System.out.println(memberList.get(j).getIDString() + " " + memberList.get(j).getScore());
				}
				
			} catch (NumberFormatException e) {
				System.out.println("���� �����ȣ�� ������ 1�̻��� �ڿ����� �Է��� �ּ���.");
			}
			
		}
	}
}
