package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import model.Data;
import model.Station;

public class DataProcess {
	private static List<Station> analysisList = new ArrayList<>(); // �ѷ���վ��
	private static HashMap<Station, Data> resultMap = new HashMap<>(); // �����

	public static List<Station> getLineStation(String linename) { // ��ȡָ��·������վ��
		int flag = 1;
		for (List<Station> l : DataInput.lineSet) {
			flag = 1;
			for (Station s : l)
				if (!s.getLine().contains(linename))
					flag = 0;
			if (flag == 1)
				return l;
		}
		return null;
	}

	private static Station getNextStation() { // ��һ������վ��
		int min = 999999;
		Station rets = null;
		Set<Station> stations = resultMap.keySet();
		for (Station s : stations) {
			if (analysisList.contains(s))
				continue;
			Data result = resultMap.get(s);
			if (result.getDistance() < min) {
				min = result.getDistance();
				rets = result.getEnd();
			}
		}
		return rets;
	}

	private static List<String> getSameLine(List<String> line1, List<String> line2) { // ��ȡ�غ���·
		List<String> sameline = new ArrayList<String>();
		for (String l1 : line1)
			for (String l2 : line2)
				if (l1.equals(l2))
					sameline.add(l1);
		return sameline;
	}

	public static Data shortestPath(Station start, Station end) { // Dijkstra�������·��
		for (List<Station> l : DataInput.lineSet) { // ��������
			for (int i = 0; i < l.size(); i++) {
				Data result = new Data();
				result.setStart(start);
				result.setEnd(l.get(i));
				result.setDistance(999999);
				result.setChangeFlag(0);
				resultMap.put(l.get(i), result);
			}
		}
		for (Station s : start.getLinkStations()) { // ��ʼ�������
			resultMap.get(s).setDistance(1);
			resultMap.get(s).setLastStation(start);
			List<String> samelines = getSameLine(start.getLine(), s.getLine());
			resultMap.get(s).setLine(samelines.get(0));
		}
		resultMap.get(start).setDistance(0);

		analysisList.add(start);
		Station nextstation = getNextStation(); // ������һ��������վ��
		while (nextstation != null) { // �������·��
			for (Station s : nextstation.getLinkStations()) {
				if (resultMap.get(nextstation).getDistance() + 1 < resultMap.get(s).getDistance()) { // �������·��
					resultMap.get(s).setDistance(resultMap.get(nextstation).getDistance() + 1);
					resultMap.get(s).setLastStation(nextstation);
					List<String> samelines = getSameLine(nextstation.getLine(), s.getLine());
					if (!samelines.contains(resultMap.get(nextstation).getLine())) { // �ж��Ƿ񻻳�
						resultMap.get(s).setLine(samelines.get(0));
						resultMap.get(s).setChangeFlag(1);
					} else
						resultMap.get(s).setLine(resultMap.get(nextstation).getLine());
				}
			}
			analysisList.add(nextstation);
			nextstation = getNextStation();
		}
		return resultMap.get(end);
	}

	public static List<String> getPath(Data result) { // �������·��
		List<String> path = new ArrayList<String>();
		Stack<Station> tmp = new Stack<Station>();
		Station station = result.getLastStation();
		while (!station.equals(result.getStart())) {
			tmp.push(station);
			station = resultMap.get(station).getLastStation();
		}
		path.add(result.getStart().getName());
		while (!tmp.empty())
			if (resultMap.get(tmp.peek()).getChangeFlag() == 1) {
				path.add("--վ�ڻ���" + resultMap.get(tmp.peek()).getLine() + "--");
				path.add(tmp.pop().getName());
			} else
				path.add(tmp.pop().getName());
		if (result.getChangeFlag() == 1) {
			path.add("--վ�ڻ���" + result.getLine() + "--");
			path.add(result.getEnd().getName());
		} else
			path.add(result.getEnd().getName());
		return path;
	}
}