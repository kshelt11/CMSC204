import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GradeBookTester {

	static private GradeBook gradeBook1,gradeBook2;
	
	//gradebook1 should have 5 grades entered
	static private double[] gradeBook1Grades = {25.0,50.0,75.0,100.0,50.0};
	//gradebook2 should have 4 grades entered
	static private double[] gradeBook2Grades = {100.0,100.0,75.0,100.0};
	
	@BeforeAll
	static void setUp() throws Exception{
		//initializing instances
		gradeBook1 = new GradeBook(5);
		gradeBook2 = new GradeBook(5);
		
		//filling up gradebook1
		for(int i=0;i<gradeBook1Grades.length;i++)
			gradeBook1.addScore(gradeBook1Grades[i]);
		//filling up gradebook2
		for(int i=0;i<gradeBook2Grades.length;i++)
			gradeBook2.addScore(gradeBook2Grades[i]);
	}
	
	@Test
	void testAddScore() {
		String string1 = gradeBook1.toString();
		String string2 = gradeBook2.toString();
		
		//what the toString should return for each case
		String string1Actual = "25.0 50.0 75.0 100.0 50.0 ";
		String string2Actual = "100.0 100.0 75.0 100.0 ";

		assertTrue(string1.equals(string1Actual));
		assertTrue(string2.equals(string2Actual));
		
		//checking to see if score size matches
		assertEquals(gradeBook1Grades.length,gradeBook1.getScoreSize());
	}
	
	@Test
	void testSum() {
		//what the two gradebook's sums should be, respectively
		double[] sums = {300.0,375.0};
		
		assertEquals(gradeBook1.sum(),sums[0]);
		assertEquals(gradeBook2.sum(),sums[1]);
	}
	
	@Test
	void testMinimum() {
		//same as the sum, but for minimum
		double[] mins = {25.0,75.0};
		
		assertEquals(gradeBook1.minimum(),mins[0]);
		assertEquals(gradeBook2.minimum(),mins[1]);
	}
	
	@Test
	void testFinalScore() {
		double[] finalScores = {275.0,300.0};
		
		assertEquals(gradeBook1.finalScore(),finalScores[0]);
		assertEquals(gradeBook2.finalScore(),finalScores[1]);
	}

	@AfterAll
	static void tearDown() throws Exception{
		gradeBook1 = null;
		gradeBook2 = null;
	}
	
}
