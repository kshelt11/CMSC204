/**
 * @author Kenneth Shelton
 */
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("unused")
public class MorseCodeProject_StudentTEST {

	MorseCodeTree mct;
	
	@Before
	public void setUp() throws Exception {
		mct = new MorseCodeTree();
	}

	@After
	public void tearDown() throws Exception {
		mct = null;
	}
	
	@Test
	public void testMorseCodeToArrayList() {	
		assertEquals(27,mct.toArrayList().size());
	}
	
	@Test
	public void testConvertToEnglishString() {	
		String converter1 = MorseCodeConverter.convertToEnglish(".... . .-.. .-.. --- / .-- --- .-. .-.. -.. ");
		assertEquals("hello world",converter1);
	}
	
	@Test
	public void testConvertToEnglishFile() throws FileNotFoundException {
		String converter2 = MorseCodeConverter.convertToEnglish(new File("MorseCodeTextFile.txt"));
		assertEquals("the quick brown fox jumped over the lazy dog",converter2);
	}
}