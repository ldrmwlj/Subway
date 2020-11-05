import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import model.Data;
import model.Station;
import util.DataInput;
import util.DataProcess;

public class SubwayLine {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		while (true) {
			System.out.print("����'1'�鿴��·վ�㣬����'2'��ѯ���·�ߣ��������������˳���");
			Scanner in = new Scanner(System.in);
			int choice = in.nextInt();
			if (choice == 1) {
				System.out.print("���������·�鿴��Ӧ��·վ�㣺");
				Scanner input = new Scanner(System.in);
				String line = input.nextLine();
				new DataInput("E:\\data.txt");
				List<Station> stations = DataProcess.getLineStation(line);
				String content = "";
				if (stations == null)
					content = "�����ڸõ�����·";
				else {
					for (int i = 0; i < stations.size(); i++)
						if (i == stations.size() - 1 && stations.get(i).getLinkStations().contains(stations.get(0)))
							content = content + stations.get(i).getName() + "	(����·Ϊ����)";
						else
							content = content + stations.get(i).getName() + " ";
				}
				System.out.print(content);
			} else if (choice == 2) {
				System.out.print("������ʼվ��");
				Scanner input1 = new Scanner(System.in);
				String station1 = input1.nextLine();
				System.out.print("�����յ�վ��");
				Scanner input2 = new Scanner(System.in);
				String station2 = input2.nextLine();
				System.out.println();
				new DataInput("E:\\data.txt");
				Station start = null;
				Station end = null;
				for (List<Station> l : DataInput.lineSet)
					for (int i = 0; i < l.size(); i++) {
						if (l.get(i).getName().equals(station1)) {
							start = l.get(i);
						}
						if (l.get(i).getName().equals(station2)) {
							end = l.get(i);
						}
					}
				String content = "";
				if (start == null)
					content = "�������ʼվ������";
				else if (end == null)
					content = "������յ�վ������";
				else {
					Data result = DataProcess.shortestPath(start, end);
					List<String> path = DataProcess.getPath(result);
					content = "������" + (result.getDistance() + 1) + "վ��\n\n";
					for (String s : path)
						content = content + s + "\n";
				}
				System.out.print(content);
			} else
				break;
			System.out.println();
			System.out.println();
		}
	}
}