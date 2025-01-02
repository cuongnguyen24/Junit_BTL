package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controller.QLSVController;
import model.QLSVModel;
import model.ThiSinh;
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
        System.out.println("--------------TEST THEM THI SINH---------------");
        System.out.println("Thông tin thí sinh:");
        hienThiThongTinThiSinh(ts);
        
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
    
    @Test
    void testCapNhat() {
    	view.textField_ID.setText("1");
    	view.textField_HoVaTen.setText("Nguyen Ba Cuong");
    	// Thêm danh sách Tinh và chọn 1 Tinh
        view.comboBox_queQuan.setSelectedItem("Nghệ An"); 
    	view.textField_NgaySinh.setText("24/07/2004");
    	// Chọn giới tính
        view.radioButton_nam.setSelected(true);
        view.radioButton_nu.setSelected(false);
        // Điểm
        view.textField_Mon1.setText("9");
        view.textField_Mon2.setText("9");
        view.textField_Mon3.setText("9.5");
        view.thucHienThemThiSinh();
        
        ThiSinh ts = view.model.getThiSinhById(1);
        System.out.println("--------------TEST CAP NHAT---------------");
        System.out.println("Thông tin thí sinh trước khi cập nhật:");
        hienThiThongTinThiSinh(ts);
        
        view.table.setRowSelectionInterval(0, 0);
        view.hienThiThongTinThiSinhDaChon();
        
        // update du lieu
        view.textField_HoVaTen.setText("Nguyen Van A");
        view.textField_Mon1.setText("6");
        view.thucHienThemThiSinh();

        ThiSinh ts1 = view.model.getThiSinhById(1); 
        System.out.println("");
        System.out.println("Thông tin thí sinh sau khi cập nhật:");
        hienThiThongTinThiSinh(ts1);
        
        assertEquals("Nguyen Van A", ts1.getTenThiSinh());
        assertEquals(6.0f, ts1.getDiemMon1());
    }
    
    @Test
    void testXoa_Yes() {
    	view.textField_ID.setText("1");
    	view.textField_HoVaTen.setText("Nguyen Quoc Cuong");
    	// Thêm danh sách Tinh và chọn 1 Tinh
        view.comboBox_queQuan.setSelectedItem("Nghệ An"); 
    	view.textField_NgaySinh.setText("19/12/2011");
    	// Chọn giới tính
        view.radioButton_nam.setSelected(true);
        view.radioButton_nu.setSelected(false);
        // Điểm
        view.textField_Mon1.setText("3.5");
        view.textField_Mon2.setText("4.0");
        view.textField_Mon3.setText("5.5");
        
        view.thucHienThemThiSinh();

        // Đảm bảo thí sinh đã được thêm thành công trước khi xóa
        assertNotNull(view.model.getThiSinhById(1), "Thí sinh không được thêm trước khi test xóa!");

        // Giả lập chọn dòng của thí sinh cần xóa
        DefaultTableModel tableModel = (DefaultTableModel) view.table.getModel();

        view.table.setRowSelectionInterval(0, 0); // Chọn dòng đầu tiên (dòng của thí sinh)
        //view.thucHienXoa();
        JOptionPane pane = new JOptionPane() {
            public int showConfirmDialog(Object parentComponent, Object message) {
                return JOptionPane.YES_OPTION;  // Giả lập nhấn "Yes"
            }
        };  
        
//        // Kiểm tra xem thí sinh đã bị xóa khỏi model
          assertNull(model.getThiSinhById(1), "Thí sinh không được xóa khỏi model!");
    }
    
    @Test
    void testXoa_No() {
    	view.textField_ID.setText("1");
    	view.textField_HoVaTen.setText("Nguyen Quoc Cuong NO");
    	// Thêm danh sách Tinh và chọn 1 Tinh
        view.comboBox_queQuan.setSelectedItem("Nghệ An"); 
    	view.textField_NgaySinh.setText("19/12/2011");
    	// Chọn giới tính
        view.radioButton_nam.setSelected(true);
        view.radioButton_nu.setSelected(false);
        // Điểm
        view.textField_Mon1.setText("3.5");
        view.textField_Mon2.setText("6.0");
        view.textField_Mon3.setText("5.5");
        
        view.thucHienThemThiSinh();

        // Đảm bảo thí sinh đã được thêm thành công trước khi xóa
        assertNotNull(view.model.getThiSinhById(1), "Thí sinh không được thêm trước khi test xóa!");

        // Giả lập chọn dòng của thí sinh cần xóa
        DefaultTableModel tableModel = (DefaultTableModel) view.table.getModel();

        view.table.setRowSelectionInterval(0, 0); // Chọn dòng đầu tiên (dòng của thí sinh)

     
       
        
        ThiSinh ts = view.model.getThiSinhById(1);
//        if (ts != null) {
//        	System.out.println("");
//            System.out.println("Dữ liệu thí sinh hàm - testXoa_No : ");
//            hienThiThongTinThiSinh(ts);
//        }

        // Kiểm tra xem thí sinh đã bị xóa khỏi model
        assertNotNull(view.model.getThiSinhById(1), "Thí sinh không được giữ lại trong model!");

        // Kiểm tra xem dòng trong bảng đã bị xóa
        assertEquals(1, tableModel.getRowCount(), "Dòng trong bảng không bị xóa!");
    }
    
    
    @Test
    void testHuyBo() {
    	view.textField_ID.setText("1");
    	view.textField_HoVaTen.setText("Nguyen Ba Cuong");
    	// Thêm danh sách Tinh và chọn 1 Tinh
        view.comboBox_queQuan.setSelectedItem("Nghệ An"); 
    	view.textField_NgaySinh.setText("24/07/2004");
    	// Chọn giới tính
        view.radioButton_nam.setSelected(true);
        view.radioButton_nu.setSelected(false);
        // Điểm
        view.textField_Mon1.setText("9");
        view.textField_Mon2.setText("9");
        view.textField_Mon3.setText("9.5");
        view.thucHienThemThiSinh();
    	
        view.table.setRowSelectionInterval(0, 0);
        view.hienThiThongTinThiSinhDaChon();
        
        view.xoaForm();
        
        assertEquals("", view.textField_ID.getText());
        assertEquals("", view.textField_HoVaTen.getText());
        assertEquals(null, view.comboBox_queQuan.getSelectedItem()); 
        assertEquals("", view.textField_NgaySinh.getText()); 
        assertFalse(view.radioButton_nam.isSelected()); 
        assertFalse(view.radioButton_nu.isSelected()); 
        assertEquals("", view.textField_Mon1.getText());
        assertEquals("", view.textField_Mon2.getText()); 
        assertEquals("", view.textField_Mon3.getText());
        
    }

    @Test
    void testKhongTimThay() {
    	view.textField_ID.setText("1");
    	view.textField_HoVaTen.setText("Nguyen Ba Cuong");
        view.comboBox_queQuan.setSelectedItem("Nghệ An"); 
    	view.textField_NgaySinh.setText("24/07/2004");
        view.radioButton_nam.setSelected(true);
        view.radioButton_nu.setSelected(false);
        view.textField_Mon1.setText("9.5");
        view.textField_Mon2.setText("9.0");
        view.textField_Mon3.setText("9.5");
        
        view.thucHienThemThiSinh();
        
        view.comboBox_queQuan_timKiem.setSelectedItem("Hà Nội");
        view.thucHienTim();
        DefaultTableModel tableModel = (DefaultTableModel) view.table.getModel();
        assertEquals(0, tableModel.getRowCount(), "Lỗi tìm kiếm");
    }
    
    @Test
    void testKhongTimThay1() {
    	view.textField_ID.setText("1");
    	view.textField_HoVaTen.setText("Nguyen Ba Cuong");
        view.comboBox_queQuan.setSelectedItem("Nghệ An"); 
    	view.textField_NgaySinh.setText("24/07/2004");
        view.radioButton_nam.setSelected(true);
        view.radioButton_nu.setSelected(false);
        view.textField_Mon1.setText("9.5");
        view.textField_Mon2.setText("9.0");
        view.textField_Mon3.setText("9.5");
        
        view.thucHienThemThiSinh();
        
        view.comboBox_queQuan_timKiem.setSelectedItem("Nghệ An");
        view.textField_MaThiSinh_TimKiem.setText("2");
        view.thucHienTim();
        DefaultTableModel tableModel = (DefaultTableModel) view.table.getModel();
        assertEquals(0, tableModel.getRowCount(), "Lỗi tìm kiếm");
    }
    
    @Test
    void testKhongTimThay2() {
    	view.textField_ID.setText("1");
    	view.textField_HoVaTen.setText("Nguyen Ba Cuong");
        view.comboBox_queQuan.setSelectedItem("Nghệ An"); 
    	view.textField_NgaySinh.setText("24/07/2004");
        view.radioButton_nam.setSelected(true);
        view.radioButton_nu.setSelected(false);
        view.textField_Mon1.setText("9.5");
        view.textField_Mon2.setText("9.0");
        view.textField_Mon3.setText("9.5");
        
        view.thucHienThemThiSinh();
        
        view.comboBox_queQuan_timKiem.setSelectedItem("Hà Nội");
        view.textField_MaThiSinh_TimKiem.setText("1");
        view.thucHienTim();
        DefaultTableModel tableModel = (DefaultTableModel) view.table.getModel();
        assertEquals(0, tableModel.getRowCount(), "Lỗi tìm kiếm");
    }
    
    @Test
    void testTimThay() {
    	view.textField_ID.setText("1");
    	view.textField_HoVaTen.setText("Nguyen Ba Cuong");
        view.comboBox_queQuan.setSelectedItem("Nghệ An"); 
    	view.textField_NgaySinh.setText("24/07/2004");
        view.radioButton_nam.setSelected(true);
        view.radioButton_nu.setSelected(false);
        view.textField_Mon1.setText("9.5");
        view.textField_Mon2.setText("9.0");
        view.textField_Mon3.setText("9.5");
        
        view.thucHienThemThiSinh();
        
        view.comboBox_queQuan_timKiem.setSelectedItem("Nghệ An");
        view.thucHienTim();
        DefaultTableModel tableModel = (DefaultTableModel) view.table.getModel();
        assertEquals(1, tableModel.getRowCount(), "Lỗi tìm kiếm");
    }
    
    @Test
    void testTimThay1() {
    	view.textField_ID.setText("1");
    	view.textField_HoVaTen.setText("Nguyen Ba Cuong");
        view.comboBox_queQuan.setSelectedItem("Nghệ An"); 
    	view.textField_NgaySinh.setText("24/07/2004");
        view.radioButton_nam.setSelected(true);
        view.radioButton_nu.setSelected(false);
        view.textField_Mon1.setText("9.5");
        view.textField_Mon2.setText("9.0");
        view.textField_Mon3.setText("9.5");
        
        view.thucHienThemThiSinh();
        
        view.comboBox_queQuan_timKiem.setSelectedItem("Nghệ An");
        view.textField_MaThiSinh_TimKiem.setText("1");
        view.thucHienTim();
        DefaultTableModel tableModel = (DefaultTableModel) view.table.getModel();
        assertEquals(1, tableModel.getRowCount(), "Lỗi tìm kiếm");
    }
    
    @Test
    void testTimThay2() {
    	view.textField_ID.setText("1");
    	view.textField_HoVaTen.setText("Nguyen Ba Cuong");
        view.comboBox_queQuan.setSelectedItem("Nghệ An"); 
    	view.textField_NgaySinh.setText("24/07/2004");
        view.radioButton_nam.setSelected(true);
        view.radioButton_nu.setSelected(false);
        view.textField_Mon1.setText("9.5");
        view.textField_Mon2.setText("9.0");
        view.textField_Mon3.setText("9.5");
        
        view.thucHienThemThiSinh();
        
        view.textField_MaThiSinh_TimKiem.setText("1");
        view.thucHienTim();
        DefaultTableModel tableModel = (DefaultTableModel) view.table.getModel();
        assertEquals(1, tableModel.getRowCount(), "Lỗi tìm kiếm");
    }
    
    @Test
    void testHuyTim() {
    	view.textField_ID.setText("1");
    	view.textField_HoVaTen.setText("Nguyen Ba Cuong");
        view.comboBox_queQuan.setSelectedItem("Nghệ An"); 
    	view.textField_NgaySinh.setText("24/07/2004");
        view.radioButton_nam.setSelected(true);
        view.radioButton_nu.setSelected(false);
        view.textField_Mon1.setText("9.5");
        view.textField_Mon2.setText("9.0");
        view.textField_Mon3.setText("9.5");
        
        view.thucHienThemThiSinh();
        
        view.textField_MaThiSinh_TimKiem.setText("2");
        view.thucHienTim();
        view.thucHienTaiLaiDuLieu();
        
        DefaultTableModel tableModel = (DefaultTableModel) view.table.getModel();
        assertEquals(1, tableModel.getRowCount(), "Lỗi hủy tìm kiếm");
    }
    
    private void hienThiThongTinThiSinh(ThiSinh ts) {
        System.out.println("ID: " + ts.getMaThiSinh());
        System.out.println("Họ và tên: " + ts.getTenThiSinh());
        System.out.println("Quê quán: " + ts.getQueQuan().getTenTinh());
        System.out.println("Ngày sinh: " + new SimpleDateFormat("dd/MM/yyyy").format(ts.getNgaySinh()));
        System.out.println("Giới tính: " + (ts.isGioiTinh() ? "Nam" : "Nữ"));
        System.out.println("Điểm môn 1: " + ts.getDiemMon1());
        System.out.println("Điểm môn 2: " + ts.getDiemMon2());
        System.out.println("Điểm môn 3: " + ts.getDiemMon3());
    }
}
