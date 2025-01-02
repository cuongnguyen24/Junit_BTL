package model;

import java.util.ArrayList;

public class QLSVModel {
	private ArrayList<ThiSinh> dsThiSinh;
	private String luaChon;
	private String tenFile;

	public QLSVModel() {
		this.dsThiSinh = new ArrayList();
		this.luaChon = "";
		this.tenFile="";
	}

	public QLSVModel(ArrayList<ThiSinh> dsThiSinh) {
		this.dsThiSinh = dsThiSinh;
	}

	public ArrayList<ThiSinh> getDsThiSinh() {
		return dsThiSinh;
	}

	public void setDsThiSinh(ArrayList<ThiSinh> dsThiSinh) {
		this.dsThiSinh = dsThiSinh;
	}
	
	public void insert(ThiSinh thiSinh) {
		this.dsThiSinh.add(thiSinh);
	}
	
	public void delete(ThiSinh thiSinh) {
		this.dsThiSinh.remove(thiSinh);
	}
	
//	public void update(ThiSinh thiSinh) {
//		this.dsThiSinh.remove(thiSinh);
//		this.dsThiSinh.add(thiSinh);
//	}
	public void update(ThiSinh ts) {
	    for (int i = 0; i < dsThiSinh.size(); i++) {
	        if (dsThiSinh.get(i).getMaThiSinh() == ts.getMaThiSinh()) {
	        	dsThiSinh.set(i, ts); // Thay thế đối tượng bằng bản mới
	            return; // Thoát khỏi vòng lặp sau khi cập nhật
	        }
	    }
	}


	public String getLuaChon() {
		return luaChon;
	}

	public void setLuaChon(String luaChon) {
		this.luaChon = luaChon;
	}

	public String getTenFile() {
		return tenFile;
	}

	public void setTenFile(String tenFile) {
		this.tenFile = tenFile;
	}

	public boolean kiemTraTonTai(ThiSinh ts) {
		for(ThiSinh thiSinh: dsThiSinh) {
			if(thiSinh.getMaThiSinh() == ts.getMaThiSinh())
				return true;
		}
		return false;
	}

	public ThiSinh getThiSinhById(int id) {
	    // Duyệt qua tất cả các thí sinh trong danh sách để tìm thí sinh theo ID
	    for (ThiSinh ts : this.dsThiSinh) {
	        if (ts.getMaThiSinh() == id) {
	            return ts;  // Trả về thí sinh nếu tìm thấy
	        }
	    }
	    return null;  // Trả về null nếu không tìm thấy thí sinh có ID tương ứng
	}

	
	
}