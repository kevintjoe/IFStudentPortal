import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.junit.*;
import static org.junit.Assert.*;
import static play.test.Helpers.running;

import java.io.IOException;

import static org.fluentlenium.core.filter.FilterConstructor.*;

/**
 * 
 * Kelas untuk mengetes permasalahan jadwal kuliah, jika jadwal kuliah pengguna
 * sudah tersedia akan ditampilkan jadwal kuliah dalam bentuk kalendar yang
 * sudah diurutkan berdasarkan hari
 * 
 * @author FTIS\i13006
 *
 */
public class TestJadwalKuliah extends FunctionalTest {

	public TestJadwalKuliah() throws IOException {
		super();
	}

	/**
	 * Jika pengguna menuju navigasi drawer dan melalukan click terhadap jadwal
	 * kuliah akan ditampilkan halaman jadwal kuliah dalam bentuk kalendar yang
	 * sudah diurutkan berdasarkan hari
	 */
	@Test
	public void testJadwalKuliahValid() {
		running(server, new Runnable() {
			@Override
			public void run() {
				browser.goTo("/");
				browser.find(".form-control", withId("email-input")).get(0).text(objFileConfReader.getEmailValid());
				browser.find(".form-control", withId("pw-input")).get(0).text(objFileConfReader.getPassValid());
				browser.find(".form-control", withName("submit")).get(0).click();
				browser.goTo("/jadwalkuliah");
				assertEquals("JADWAL KULIAH", browser.find(".row").get(0).find("h2").get(0).getText());

				FluentList<FluentWebElement> e1 = browser.find(".jadwal-cell");
				// System.out.println("SIZE: " + e1.size()); //debug
				// System.out.println(e1.get(0).getText()); //debug
				if (!(e1.size() > 0)) {
					assertEquals("Seharusnya size lebih dari 0", "tetapi ternyata 0");
				}
			}
		});
	}

	/**
	 * Jika pengguna menuju navigasi drawer dan melalukan click terhadap jadwal
	 * kuliah namun belum FRS, cuti studi, atau jadwal kuliah pengguna belum
	 * tersedia, Maka ditampilkan "JADWAL KULIAH BELUM TERSEDIA".
	 */
	@Test
	public void testJadwalKuliahBelumFRS() {
		running(server, new Runnable() {
			@Override
			public void run() {
				browser.goTo("/");
				browser.find(".form-control", withId("email-input")).get(0).text(objFileConfReader.getEmailValid());
				browser.find(".form-control", withId("pw-input")).get(0).text(objFileConfReader.getPassValid());
				browser.find(".form-control", withName("submit")).get(0).click();
				browser.goTo("/jadwalkuliah");
				FluentList<FluentWebElement> e1 = browser.find(".row").get(1).find("h5");
				if (e1.size() > 0) {
					assertEquals("JADWAL KULIAH BELUM TERSEDIA", e1.get(0).getText());
				} else {
					assertEquals("JADWAL KULIAH BELUM TERSEDIA", "testGagal");
				}
			}
		});
	}
}
