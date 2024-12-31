package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.QLSVController;
import model.QLSVModel;
import model.ThiSinh;
import model.Tinh;
import view.QLSVView;

class TestChucNang {
	
	private QLSVView view;
    private QLSVController controller;
    private QLSVModel model;
    private ThiSinh ts;

    @BeforeEach
    void setUp() {
     	view = new QLSVView();
     	model = new QLSVModel();
        controller = new QLSVController(view);
        ts = new ThiSinh();
    }
    
    @Test
    void testThemThiSinh(){
    	ArrayList<ThiSinh> danhSach = new ArrayList<>();
    	view.textField_ID.setText("1");
    	view.textField_HoVaTen.setText("Nguyen Ba Cuong");
    	// Thêm danh sách Tinh và chọn 1 Tinh
        view.comboBox_queQuan.setSelectedItem("Nghệ An"); 
    	view.textField_NgaySinh.setText("24/07/2004");
    	// Chọn giới tính
        view.radioButton_nam.setSelected(true);
        view.radioButton_nu.setSelected(false);
        // Điểm
        view.textField_Mon1.setText("9.5");
        view.textField_Mon2.setText("9.0");
        view.textField_Mon3.setText("9.5");
        
        view.thucHienThemThiSinh();
        
        ThiSinh ts = view.model.getThiSinhById(1); // Giả sử có phương thức này để lấy theo ID
        System.out.println("Thông tin thí sinh:");
        System.out.println("ID: " + ts.getMaThiSinh());
        System.out.println("Họ và tên: " + ts.getTenThiSinh());
        System.out.println("Quê quán: " + ts.getQueQuan().getTenTinh());
        System.out.println("Ngày sinh: " + new SimpleDateFormat("dd/MM/yyyy").format(ts.getNgaySinh()));
        System.out.println("Giới tính: " + (ts.isGioiTinh() ? "Nam" : "Nữ"));
        System.out.println("Điểm môn 1: " + ts.getDiemMon1());
        System.out.println("Điểm môn 2: " + ts.getDiemMon2());
        System.out.println("Điểm môn 3: " + ts.getDiemMon3());
        
        ts = view.model.getThiSinhById(1); 
        assertNotNull(ts, "Thí sinh không được thêm vào model!");
        assertEquals("Nguyen Ba Cuong", ts.getTenThiSinh(), "Tên thí sinh không đúng!");
        assertEquals("Nghệ An", ts.getQueQuan().getTenTinh(), "Quê quán không đúng!");
        assertEquals("24/07/2004", new SimpleDateFormat("dd/MM/yyyy").format(ts.getNgaySinh()), "Ngày sinh không đúng!");
        assertTrue(ts.isGioiTinh(), "Giới tính không đúng!");
        assertEquals(9.5f, ts.getDiemMon1(), "Điểm môn 1 không đúng!");
        assertEquals(9.0f, ts.getDiemMon2(), "Điểm môn 2 không đúng!");
        assertEquals(9.5f, ts.getDiemMon3(), "Điểm môn 3 không đúng!");
        
    }
}
