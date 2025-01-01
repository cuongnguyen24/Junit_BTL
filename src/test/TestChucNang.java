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
     // Mock hành động nhấn "Yes" trong JOptionPane
        JOptionPane pane = new JOptionPane() {
            public int showConfirmDialog(Object parentComponent, Object message) {
                return JOptionPane.YES_OPTION;  // Giả lập nhấn "Yes"
            }
        };
        
        
        
        
//        // Kiểm tra xem thí sinh đã bị xóa khỏi model
          assertNull(model.getThiSinhById(1), "Thí sinh không được xóa khỏi model!");

//        // Kiểm tra xem dòng trong bảng đã bị xóa
 //         assertEquals(0, tableModel.getRowCount(), "Dòng trong bảng không bị xóa!");
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
        if (ts != null) {
        	System.out.println("");
            System.out.println("Dữ liệu thí sinh hàm - testXoa_No : ");
            System.out.println("ID: " + ts.getMaThiSinh());
            System.out.println("Tên: " + ts.getTenThiSinh());
            System.out.println("Quê quán: " + ts.getQueQuan());
            System.out.println("Ngày sinh: " + ts.getNgaySinh());
            System.out.println("Giới tính: " + (ts.isGioiTinh() ? "Nam" : "Nữ"));
            System.out.println("Điểm môn 1: " + ts.getDiemMon1());
            System.out.println("Điểm môn 2: " + ts.getDiemMon2());
            System.out.println("Điểm môn 3: " + ts.getDiemMon3());
        }

        // Kiểm tra xem thí sinh đã bị xóa khỏi model
        assertNotNull(view.model.getThiSinhById(1), "Thí sinh không được giữ lại trong model!");

        // Kiểm tra xem dòng trong bảng đã bị xóa
        assertEquals(1, tableModel.getRowCount(), "Dòng trong bảng không bị xóa!");
    }
    
//    @Test
//    void testXoa_No1() {
//        view.textField_ID.setText("1");
//        view.textField_HoVaTen.setText("Nguyen Quoc Cuong NO");  
//        view.comboBox_queQuan.setSelectedItem("Nghệ An");
//        view.textField_NgaySinh.setText("19/12/2011");
//        view.radioButton_nam.setSelected(true);
//        view.radioButton_nu.setSelected(false);
//        view.textField_Mon1.setText("3.5");
//        view.textField_Mon2.setText("6.0");
//        view.textField_Mon3.setText("5.5");
//
//        view.thucHienThemThiSinh();
//
//        // Đảm bảo thí sinh đã được thêm thành công trước khi xóa
//        assertNotNull(view.model.getThiSinhById(1), "Thí sinh không được thêm trước khi test xóa!");
//
//        // Giả lập chọn dòng của thí sinh cần xóa
//        DefaultTableModel tableModel = (DefaultTableModel) view.table.getModel();
//        view.table.setRowSelectionInterval(0, 0); // Chọn dòng đầu tiên (dòng của thí sinh)
//
//        // Mock JOptionPane
//        JOptionPane mockPane = mock(JOptionPane.class);
//        // Không sử dụng any() mà trả về giá trị trực tiếp
//        when(mockPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION)).thenReturn(JOptionPane.NO_OPTION);
//
//        // Giả lập hành động xóa
//        controller.setOptionPane(mockPane); 
//        view.thucHienXoa();
//
//        // Kiểm tra thí sinh vẫn còn trong model
//        assertNotNull(view.model.getThiSinhById(1), "Thí sinh không được giữ lại trong model!");
//
//        // Kiểm tra dòng trong bảng không bị xóa
//        assertEquals(1, tableModel.getRowCount(), "Dòng trong bảng bị xóa không đúng!");
//    }

}
