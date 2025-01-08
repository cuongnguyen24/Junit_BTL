package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.QLSVModel;
import model.ThiSinh;
import model.Tinh;

class ModelTest {

	private QLSVModel qlSVModel;
    private ThiSinh thiSinh1;
    private ThiSinh thiSinh2;
    private Tinh tinhHaNoi;

    @BeforeEach
    public void setUp() {
        // Khởi tạo đối tượng Tinh
        tinhHaNoi = new Tinh(1, "Ha Noi");

        // Khởi tạo đối tượng Ngày sinh
        Date ngaySinh = new Date(1995, 5, 15);  // Ví dụ ngày sinh

        // Khởi tạo đối tượng ThiSinh
        thiSinh1 = new ThiSinh(1, "Nguyen Van A", tinhHaNoi, ngaySinh, true, 8.5f, 7.5f, 9.0f);
        thiSinh2 = new ThiSinh(2, "Nguyen Van B", tinhHaNoi, ngaySinh, false, 6.0f, 7.0f, 8.0f);

        // Khởi tạo đối tượng QLSVModel và thêm thí sinh
        qlSVModel = new QLSVModel();
        qlSVModel.insert(thiSinh1);
        qlSVModel.insert(thiSinh2);
    }

    @Test
    public void testInsert() {
        ThiSinh thiSinh3 = new ThiSinh(3, "Nguyen C", new Tinh(2, "Hai Duong"), 
        		new Date(), true, 7.5f, 8.0f, 9.0f);
        qlSVModel.insert(thiSinh3);
        assertEquals(3, qlSVModel.getDsThiSinh().size());
    }

    @Test
    public void testDelete() {
        qlSVModel.delete(thiSinh1);
        assertEquals(1, qlSVModel.getDsThiSinh().size());
        assertFalse(qlSVModel.getDsThiSinh().contains(thiSinh1));
    }

    @Test
    public void testUpdate() {
        ThiSinh updatedThiSinh = new ThiSinh(1, "Nguyen Van A Updated", tinhHaNoi,
        		new Date(), true, 9.0f, 8.5f, 9.5f);
        qlSVModel.update(updatedThiSinh);
        ThiSinh result = qlSVModel.getThiSinhById(1);
        assertNotNull(result);
        assertEquals("Nguyen Van A Updated", result.getTenThiSinh());
    }

    @Test
    public void testKiemTraTonTai() {
        assertTrue(qlSVModel.kiemTraTonTai(thiSinh1));  // Thí sinh đã có
        ThiSinh thiSinhNotExist = new ThiSinh(3, "Nguyen C", new Tinh(2, "Hai Duong"),
        		new Date(), true, 7.5f, 8.0f, 9.0f);
        assertFalse(qlSVModel.kiemTraTonTai(thiSinhNotExist));  // Thí sinh chưa có
    }

    @Test
    public void testGetThiSinhById() {
        ThiSinh result = qlSVModel.getThiSinhById(1);
        assertNotNull(result);
        assertEquals(1, result.getMaThiSinh());

        ThiSinh resultNotFound = qlSVModel.getThiSinhById(3);
        assertNull(resultNotFound);  // Không tìm thấy thí sinh với ID 3
    }

}
