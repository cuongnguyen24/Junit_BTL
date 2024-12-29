package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ThiSinh;
import model.Tinh;

import java.util.Date;

class ThiSinhTest {

    private ThiSinh thiSinh;

    @BeforeEach
    void setUp() {
        // Tạo đối tượng ThiSinh cho mỗi test
        thiSinh = new ThiSinh(1, "Nguyen Van A", new Tinh(1, "Ha Noi"), new Date(2000 - 1900, 5 - 1, 15), true, 8.5f, 7.5f, 9.0f);
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

    @Test
    void testSettersAndGetters() {
        // Kiểm tra setter và getter
        thiSinh.setMaThiSinh(2);
        thiSinh.setTenThiSinh("Tran Van B");
        thiSinh.setQueQuan(new Tinh(2, "Hai Phong"));
        thiSinh.setNgaySinh(new Date(1999 - 1900, 10 - 1, 20));
        thiSinh.setGioiTinh(false);
        thiSinh.setDiemMon1(6.0f);
        thiSinh.setDiemMon2(7.0f);
        thiSinh.setDiemMon3(8.0f);

        assertEquals(2, thiSinh.getMaThiSinh());
        assertEquals("Tran Van B", thiSinh.getTenThiSinh());
        assertEquals("Hai Phong", thiSinh.getQueQuan().getTenTinh());
        assertEquals(new Date(1999 - 1900, 10 - 1, 20), thiSinh.getNgaySinh());
        assertFalse(thiSinh.isGioiTinh());
        assertEquals(6.0f, thiSinh.getDiemMon1());
        assertEquals(7.0f, thiSinh.getDiemMon2());
        assertEquals(8.0f, thiSinh.getDiemMon3());
    }

    @Test
    void testEquals() {
        // Kiểm tra phương thức equals
        ThiSinh thiSinh2 = new ThiSinh(1, "Nguyen Van A", new Tinh(1, "Ha Noi"), new Date(2000 - 1900, 5 - 1, 15), true, 8.5f, 7.5f, 9.0f);
        ThiSinh thiSinh3 = new ThiSinh(2, "Tran Van B", new Tinh(2, "Hai Phong"), new Date(1999 - 1900, 10 - 1, 20), false, 6.0f, 7.0f, 8.0f);

        assertEquals(thiSinh, thiSinh2);
        assertNotEquals(thiSinh, thiSinh3);
    }

    @Test
    void testToString() {
        // Kiểm tra phương thức toString
        String expected = "ThiSinh [maThiSinh=1, tenThiSinh=Nguyen Van A, queQuan=Ha Noi, ngaySinh=Sun May 15 00:00:00 GMT 2000, gioiTinh=true, diemMon1=8.5, diemMon2=7.5, diemMon3=9.0]";
        assertEquals(expected, thiSinh.toString());
    }
}
