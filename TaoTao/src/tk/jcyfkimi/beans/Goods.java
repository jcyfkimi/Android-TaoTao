package tk.jcyfkimi.beans;

public class Goods {

	private int gid;
	private String gname;
	private int gstorage;
	private double gprice;
	private int guid;
	public Goods() {
		super();
	}
	public Goods(int gid, String gname, int gstorage, double gprice, int guid) {
		super();
		this.gid = gid;
		this.gname = gname;
		this.gstorage = gstorage;
		this.gprice = gprice;
		this.guid = guid;
	}
	public int getGid() {
		return gid;
	}
	public String getGname() {
		return gname;
	}
	public double getGprice() {
		return gprice;
	}
	public int getGstorage() {
		return gstorage;
	}
	public int getGuid() {
		return guid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public void setGprice(double gprice) {
		this.gprice = gprice;
	}
	public void setGstorage(int gstorage) {
		this.gstorage = gstorage;
	}
	public void setGuid(int guid) {
		this.guid = guid;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return gid + ":" + gname + ":" + gstorage + ":" +gprice + ":" +guid;
	}
}
