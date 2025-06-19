package pcscol.test.etudiant;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

public class TestsRegex {

	
	@Test
	public void tetsRegex() {
		String codesPeriodesChargementFormations = "PER-2020,PER-2021,PER-19,PER-2022";
		String[] test1=codesPeriodesChargementFormations.split(",");
		System.out.println(Arrays.asList(test1));
	}
	
	@Test
	public void test2Regex() {
		String codesPeriodesChargementFormations = "PER-2020         ,,,,,       ,PER-2021 		,PER-19	PER-2022;PER-2023  ";
		String[] test1=codesPeriodesChargementFormations.split("[,;\\s]+");
		System.out.println(Arrays.asList(test1));
	}
}
