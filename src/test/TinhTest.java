package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Tinh;

import java.util.ArrayList;

class TinhTest {

    private Tinh tinh;

    @BeforeEach
    void setUp() {
        // Tạo đối tượng Tinh cho mỗi test
        tinh = new Tinh(1, "Hà Nội");
    }

    @Test
    void testConstructor() {
        // Kiểm tra constructor
        assertEquals(1, tinh.getMaTinh());
        assertEquals("Hà Nội", tinh.getTenTinh());
    }

    @Test
    void testSettersAndGetters() {
        // Kiểm tra setter và getter
        tinh.setMaTinh(2);
        tinh.setTenTinh("Hồ Chí Minh");

        assertEquals(2, tinh.getMaTinh());
        assertEquals("Hồ Chí Minh", tinh.getTenTinh());
    }

    @Test
    void testEquals() {
        // Kiểm tra phương thức equals
        Tinh tinh2 = new Tinh(1, "Hà Nội");
        Tinh tinh3 = new Tinh(2, "Hồ Chí Minh");

        assertEquals(tinh, tinh2);
        assertNotEquals(tinh, tinh3);
    }

    @Test
    void testToString() {
        // Kiểm tra phương thức toString
        String expected = "Tinh [maTinh=1, tenTinh=Hà Nội]";
        assertEquals(expected, tinh.toString());
    }

    @Test
    void testGetDSTinh() {
        // Kiểm tra phương thức getDSTinh
        ArrayList<Tinh> listTinh = Tinh.getDSTinh();
        assertNotNull(listTinh);
        assertEquals(63, listTinh.size()); // Việt Nam có 63 tỉnh thành
        assertEquals("An Giang", listTinh.get(0).getTenTinh());
        assertEquals("Yên Bái", listTinh.get(62).getTenTinh());
    }

    @Test
    void testGetTinhById_ValidId() {
        // Kiểm tra phương thức getTinhById với ID hợp lệ
        Tinh result = Tinh.getTinhById(0);
        assertNotNull(result);
        assertEquals("An Giang", result.getTenTinh());
    }

    @Test
    void testGetTinhById_InvalidId() {
        // Kiểm tra phương thức getTinhById với ID không hợp lệ
        Tinh result = Tinh.getTinhById(-1);
        assertNull(result);

        result = Tinh.getTinhById(100);
        assertNull(result);
    }

    @Test
    void testGetTinhByTen_ValidName() {
        // Kiểm tra phương thức getTinhByTen với tên hợp lệ
        Tinh result = Tinh.getTinhByTen("Hà Nội");
        assertNotNull(result);
        assertEquals("Hà Nội", result.getTenTinh());
    }

    @Test
    void testGetTinhByTen_InvalidName() {
        // Kiểm tra phương thức getTinhByTen với tên không hợp lệ
        Tinh result = Tinh.getTinhByTen("Không tồn tại");
        assertNull(result);
    }
}
