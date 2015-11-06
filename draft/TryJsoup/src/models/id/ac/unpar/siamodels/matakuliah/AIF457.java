package models.id.ac.unpar.siamodels.matakuliah;

import java.util.List;

import models.id.ac.unpar.siamodels.Mahasiswa;
import models.id.ac.unpar.siamodels.matakuliah.interfaces.HasPrasyarat;
import models.id.ac.unpar.siamodels.matakuliah.interfaces.Pilihan;

public class AIF457 implements HasPrasyarat, Pilihan {

	@Override
	public boolean checkPrasyarat(Mahasiswa mahasiswa, List<String> reasonsContainer) {
		boolean ok = true;
		int sksLulus = mahasiswa.calculateSKSLulus();		
		if (sksLulus < 70) {
			reasonsContainer.add("SKS Lulus " + sksLulus + ", belum mencapai syarat minimal 70");			
			return false;
		}
		return ok;
	}

}