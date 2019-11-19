package ua.nure.koshurnykov.usermanagement;

import junit.framework.TestCase;

import java.util.Calendar;

public class SystemUserTest extends TestCase {

    private static final int CURRENT_YEAR = 2019;
    private static final int YEAR_OF_BIRTH = 1999;

    /**
     * Data when birthday in the past
     */
    private static final int DAY_OF_BIRTH_PAST = 9;
    private static final int MONTH_OF_BIRTH_PAST = Calendar.SEPTEMBER;
    private static final int AGE_PAST = CURRENT_YEAR - YEAR_OF_BIRTH;

    /**
     * Data when birthday today
     */
    private static final int DAY_OF_BIRTH_TODAY = 28;
    private static final int MONTH_OF_BIRTH_TODAY = Calendar.OCTOBER;
    private static final int AGE_TODAY = CURRENT_YEAR - YEAR_OF_BIRTH;

    /**
     * Data when birthday tomorrow
     */
    private static final int DAY_OF_BIRTH = 29;
    private static final int MONTH_OF_BIRTH = Calendar.OCTOBER;
    private static final int AGE_TOMORROW = CURRENT_YEAR - YEAR_OF_BIRTH - 1;

    /**
     * Data when birthday was yesterday
     */
    private static final int DAY_OF_BIRTH_YESTERDAY = 27;
    private static final int MONTH_OF_BIRTH_YESTERDAY = Calendar.OCTOBER;
    private static final int AGE_YESTERDAY = CURRENT_YEAR - YEAR_OF_BIRTH;

    /**
     * Data when birthday in the future
     */
    private static final int DAY_OF_BIRTH_FUTURE = 14;
    private static final int MONTH_OF_BIRTH_FUTURE = Calendar.DECEMBER;
    private static final int AGE_FUTURE = CURRENT_YEAR - YEAR_OF_BIRTH - 1;

    private SystemUser user;

    protected void setUp() throws Exception {
        super.setUp();
        user = new SystemUser();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetFullName() {
        user.setFirstName("Danyil");
        user.setLastName("koshurnykov");
        assertEquals("koshurnykov, Danyil", user.getFullName());

    }

    public void testGetAgeIfBirthdayInThePast() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_PAST, DAY_OF_BIRTH_PAST);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(AGE_PAST, user.getAge());
    }

    public void testGetAgeIfBirthdayInTheFuture() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_FUTURE, DAY_OF_BIRTH_FUTURE);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(AGE_FUTURE, user.getAge());
    }

    public void testGetAgeIfBirthdayYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_YESTERDAY,
                DAY_OF_BIRTH_YESTERDAY);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(AGE_YESTERDAY, user.getAge());
    }

    public void testGetAgeIfBirthdayToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_TODAY, DAY_OF_BIRTH_TODAY);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(AGE_TODAY, user.getAge());
    }

    public void testGetAgeIfBirthdayTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(AGE_TOMORROW, user.getAge());
    }

}
