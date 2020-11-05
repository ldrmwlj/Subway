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
			System.out.print("输入'1'查看线路站点，输入'2'查询最短路线，输入其他数字退出：");
			Scanner in = new Scanner(System.in);
			int choice = in.nextInt();
			if (choice == 1) {
				System.out.print("输入具体线路查看对应线路站点：");
				Scanner input = new Scanner(System.in);
				String line = input.nextLine();
				new DataInput("E:\\data.txt");
				List<Station> stations = DataProcess.getLineStation(line);
				String content = "";
				if (stations == null)
					content = "不存在该地铁线路";
				else {
					for (int i = 0; i < stations.size(); i++)
						if (i == stations.size() - 1 && stations.get(i).getLinkStations().contains(stations.get(0)))
							content = content + stations.get(i).getName() + "	(该线路为环线)";
						else
							content = content + stations.get(i).getName() + " ";
				}
				System.out.print(content);
			} else if (choice == 2) {
				System.out.print("输入起始站：");
				Scanner input1 = new Scanner(System.in);
				String station1 = input1.nextLine();
				System.out.print("输入终点站：");
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
					content = "输入的起始站不存在";
				else if (end == null)
					content = "输入的终点站不存在";
				else {
					Data result = DataProcess.shortestPath(start, end);
					List<String> path = DataProcess.getPath(result);
					content = "【乘坐" + (result.getDistance() + 1) + "站】\n\n";
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