package model;

public class Data {
	private int changeFlag; // 标记换乘
	private int distance; // 距离
	private String line; // 几号线
	private Station start; // 起始站
	private Station end; // 终点站
	private Station lastStation; // 上一站

	public int getChangeFlag() {
		return changeFlag;
	}

	public void setChangeFlag(int changeFlag) {
		this.changeFlag = changeFlag;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public Station getStart() {
		return start;
	}

	public void setStart(Station start) {
		this.start = start;
	}

	public Station getEnd() {
		return end;
	}

	public void setEnd(Station end) {
		this.end = end;
	}

	public Station getLastStation() {
		return lastStation;
	}

	public void setLastStation(Station lastStation) {
		this.lastStation = lastStation;
	}

	public Data(Station start, Station end, int distance) {
		this.start = start;
		this.end = end;
		this.distance = distance;
	}

	public Data() {

	}
}