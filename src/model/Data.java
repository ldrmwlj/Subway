package model;

public class Data {
	private int changeFlag; // ��ǻ���
	private int distance; // ����
	private String line; // ������
	private Station start; // ��ʼվ
	private Station end; // �յ�վ
	private Station lastStation; // ��һվ

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