package ua.nure.cs.koshurnykov.usermanagement.domain;

import java.util.Calendar;

import junit.framework.TestCase;

public class UserTest extends TestCase {

	private static final int ETALONE_OF_AGE1 = 19;
	private static final int ETALONE_OF_AGE2=21;
	private static final int MONTH_OF_BIRTH = 4;
	private static final int YEAR_OF_BIRTH = 2000;
	private static final int DAY_OF_BIRTH = 11;

	private static final int MONTH_OF_BIRTH2 =Calendar.MONTH ;
	private static final int DAY_OF_BIRTH2 = Calendar.DATE;

	private static final int YEAR_OF_BIRTH5 = 2030;

	private User user;

	public void testGetFullName() {
		user.setFirstName("John");
		user.setLastName("Doe");
		assertEquals("Doe, John", user.getFullName());
	}

	public void testGetAge1() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
		user.setDateOfBirth(calendar.getTime());
		assertEquals(ETALONE_OF_AGE1, user.getAge());
	}

	public void testGetAge2() {
		boolean boolValue=false;
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH,DAY_OF_BIRTH);
		user.setDateOfBirth(calendar.getTime());
		if(user.getAge()<ETALONE_OF_AGE2) {
			boolValue=true;
		}
		assertTrue("Your age is less than 21!",boolValue);
	}
	public void testGetAge3() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH2, DAY_OF_BIRTH2);
		user.setDateOfBirth(calendar.getTime());
		assertEquals(ETALONE_OF_AGE1,user.getAge());
	}
	public void testGetAge4() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH2, DAY_OF_BIRTH2);
		user.setDateOfBirth(calendar.getTime());
		assertEquals(ETALONE_OF_AGE1,user.getAge());
	}
	public void testGetAge5() {
		boolean boolValue=false;
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH5, MONTH_OF_BIRTH, DAY_OF_BIRTH);
		user.setDateOfBirth(calendar.getTime());
		if(user.getAge()<0) {
			boolValue=true;
		}
		assertTrue("Wrong user`s year of birth",boolValue);
	}
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}