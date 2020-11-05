package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import model.Station;

public class DataInput {
	public static LinkedHashSet<List<Station>> lineSet = new LinkedHashSet<List<Station>>(); // 线路集合

	public DataInput(String pathname) throws IOException {
		File filename = new File(pathname);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
		BufferedReader br = new BufferedReader(reader);
		String content = "";
		content = br.readLine(); // 获取线路总数
		int linenum = Integer.parseInt(content);
		for (int i = 0; i < linenum; i++) { // 添加线路
			content = br.readLine();
			List<Station> line = new ArrayList<Station>();
			String[] linedata = content.split(" ");
			String linename = linedata[0];
			for (int j = 1; j < linedata.length; j++) { // 添加站点
				int flag = 0;
				for (List<Station> l : lineSet) { // 处理换乘
					for (int k = 0; k < l.size(); k++) {
						if (l.get(k).getName().equals(linedata[j])) {
							List<String> updateline = l.get(k).getLine();
							updateline.add(linename);
							l.get(k).setLine(updateline);
							line.add(l.get(k));
							flag = 1;
							break;
						}
					}
					if (flag == 1)
						break;
				}
				if (j == linedata.length - 1 && linedata[j].equals(linedata[1])) { // 处理环线
					line.get(0).getLinkStations().add(line.get(line.size() - 1));
					line.get(line.size() - 1).getLinkStations().add(line.get(0));
					flag = 1;
				}
				if (flag == 0)
					line.add(new Station(linedata[j], linename));
			}
			for (int j = 0; j < line.size(); j++) { // 初始化相邻车站
				List<Station> newlinkStations = line.get(j).getLinkStations();
				if (j == 0) {
					newlinkStations.add(line.get(j + 1));
					line.get(j).setLinkStations(newlinkStations);
				} else if (j == line.size() - 1) {
					newlinkStations.add(line.get(j - 1));
					line.get(j).setLinkStations(newlinkStations);
				} else {
					newlinkStations.add(line.get(j + 1));
					newlinkStations.add(line.get(j - 1));
					line.get(j).setLinkStations(newlinkStations);
				}
			}
			lineSet.add(line);
		}
		br.close();
	}
}