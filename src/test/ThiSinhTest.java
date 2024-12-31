package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ThiSinh;
import model.Tinh;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

class ThiSinhTest {
	// kiểm thử đơn vị
    private ThiSinh thiSinh;

    @BeforeEach
    void setUp() {
        // Tạo ngày tháng từ LocalDate
        LocalDate localDate = LocalDate.of(2000, 5, 15);
        Date ngaySinh = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        // Khởi tạo đối tượng ThiSinh
        thiSinh = new ThiSinh(1, "Nguyen Van A", new Tinh(1, "Ha Noi"), ngaySinh, true, 8.5f, 7.5f, 9.0f);
    }

    @Test
    void testConstructor() {
        // Kiểm tra constructor đầy đủ
        assertEquals(1, thiSinh.getMaThiSinh());
        assertEquals("Nguyen Van A", thiSinh.getTenThiSinh());
        assertEquals("Ha Noi", thiSinh.getQueQuan().getTenTinh());
        assertEquals(new Date(2000 - 1900, 5 - 1, 15), thiSinh.getNgaySinh());
        assertTrue(thiSinh.isGioiTinh());
        assertEquals(8.5f, thiSinh.getDiemMon1());
        assertEquals(7.5f, thiSinh.getDiemMon2());
        assertEquals(9.0f, thiSinh.getDiemMon3());
    }

    // cách thực hiện: Thay đổi giá trị của các thuộc tính qua setter. 
    // Kiểm tra lại giá trị của các thuộc tính qua getter, đối chiếu với giá trị mong đợi.
    @Test
    void testSettersAndGetters() {
    	 // Khởi tạo ngày bằng LocalDate
        LocalDate localDate = LocalDate.of(1999, 10, 20);
        Date ngaySinh = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    	
        // Kiểm tra setter và getter
        thiSinh.setMaThiSinh(2);
        thiSinh.setTenThiSinh("Tran Van B");
        thiSinh.setQueQuan(new Tinh(2, "Hai Phong"));
        thiSinh.setNgaySinh(ngaySinh);
        thiSinh.setGioiTinh(false);
        thiSinh.setDiemMon1(6.0f);
        thiSinh.setDiemMon2(7.0f);
        thiSinh.setDiemMon3(8.0f);

        assertEquals(2, thiSinh.getMaThiSinh());
        assertEquals("Tran Van B", thiSinh.getTenThiSinh());
        assertEquals("Hai Phong", thiSinh.getQueQuan().getTenTinh());
        assertEquals(ngaySinh, thiSinh.getNgaySinh());
        assertFalse(thiSinh.isGioiTinh());
        assertEquals(6.0f, thiSinh.getDiemMon1());
        assertEquals(7.0f, thiSinh.getDiemMon2());
        assertEquals(8.0f, thiSinh.getDiemMon3());
    }

    // kiểm tra phương thức equals xem so sánh có đúng không
    @Test
    void testEquals() {
    	// Khởi tạo ngày bằng LocalDate
        LocalDate localDate1 = LocalDate.of(2000, 5, 15);
        LocalDate localDate2 = LocalDate.of(1999, 10, 20);
        Date ngaySinh1 = Date.from(localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date ngaySinh2 = Date.from(localDate2.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        // Kiểm tra phương thức equals
        ThiSinh thiSinh2 = new ThiSinh(1, "Nguyen Van A", new Tinh(1, "Ha Noi"), ngaySinh1, true, 8.5f, 7.5f, 9.0f);
        ThiSinh thiSinh3 = new ThiSinh(2, "Tran Van B", new Tinh(2, "Hai Phong"), ngaySinh2, false, 6.0f, 7.0f, 8.0f);

        assertEquals(thiSinh, thiSinh2);
        assertNotEquals(thiSinh, thiSinh3);
    }

    @Test
    void testToString() {
        // Kiểm tra phương thức toString
        String expected = "ThiSinh [maThiSinh=1, tenThiSinh=Nguyen Van A, queQuan=Ha Noi, ngaySinh=15/05/2000, gioiTinh=true, diemMon1=8.5, diemMon2=7.5, diemMon3=9.0]";
        assertEquals(expected, thiSinh.toString());
    }
}
