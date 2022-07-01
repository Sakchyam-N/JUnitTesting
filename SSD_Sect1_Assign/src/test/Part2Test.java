package test;

import manager.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * DO NOT EDIT THE CONTENTS OF THIS FILE.
 * 
 * Each of these tests covers Part 2 functionality.
 * 
 * There are a total of 10 tests within this test class.
 * 
 * @author mdixon
 */
class Part2Test {

	@Test
	void testInitialRoomCode() {

		Room r = new Room(123);

		assertTrue(r.checkCode("9999"));
		assertFalse(r.checkCode("0000"));
		assertFalse(r.checkCode("1234"));
	}

	@Test
	void testInitialOfficeCode() {

		Office office = new Office("CAE 118");

		assertTrue(office.checkCode("1234"));
		assertFalse(office.checkCode("0000"));
		assertFalse(office.checkCode("9999"));
	}

	@Test
	void testSetRoomCode() {

		Room r = new Room(321);

		r.setCode("5676");

		assertTrue(r.checkCode("5676"));
		assertFalse(r.checkCode("9999"));
	}

	@Test
	void testSetOfficeCode() {

		Office office = new Office("CAE 118");

		office.setCode("9898");

		assertTrue(office.checkCode("9898"));
		assertFalse(office.checkCode("1234"));
	}

	@Test
	void testResetRoomCode() {

		Room r = new Room(321);

		assertTrue(r.checkCode("9999"));
		
		r.setCode("4444");

		assertTrue(r.checkCode("4444"));
		assertFalse(r.checkCode("9999"));

		r.resetToDefault();
		assertTrue(r.checkCode("9999"), "reset did not work");
		assertFalse(r.checkCode("4444"));
	}

	@Test
	void testResetOfficeCode() {

		Office office = new Office("CAE 118");

		assertTrue(office.checkCode("1234"));
		
		office.setCode("666666");

		assertTrue(office.checkCode("666666"));
		assertFalse(office.checkCode("1234"));

		office.resetToDefault();
		assertTrue(office.checkCode("1234"), "reset did not work");
		assertFalse(office.checkCode("666666"));
	}
	
	@Test
	void testOfficeLockOut() {

		Office office = new Office("CAE 118");

		assertFalse(office.isLockedOut());

		// attempt 6 incorrect codes
		assertFalse(office.checkCode("6443"));
		assertFalse(office.isLockedOut());

		assertFalse(office.checkCode("7635"));
		assertFalse(office.isLockedOut());

		assertFalse(office.checkCode(""));
		assertFalse(office.isLockedOut());

		assertFalse(office.checkCode("1313"));
		assertFalse(office.isLockedOut());

		assertFalse(office.checkCode("9876"));
		assertFalse(office.isLockedOut());

		// sixth incorrect attempt, should lock out
		assertFalse(office.checkCode("4333"));
		assertTrue(office.isLockedOut(), "should be locked out");

		// correct code should no longer work
		assertFalse(office.checkCode("1234"), "correct code should fail when locked out");
		assertTrue(office.isLockedOut());

		office.resetToDefault(); // reset should remove lock
		assertTrue(office.checkCode("1234"), "correct code should work after reset");
		assertFalse(office.isLockedOut(), "should no longer be locked out");
	}

	@Test
	void testOfficeIncorrectCount() {

		Office office = new Office("CAE 118");

		assertEquals(0, office.getIncorrectAttempts());

		// attempt 2 incorrect codes
		assertFalse(office.checkCode("6443"));
		assertEquals(1, office.getIncorrectAttempts(), "incorrect attempt count incorrect");

		assertFalse(office.checkCode("7635"));
		assertEquals(2, office.getIncorrectAttempts(), "incorrect attempt count incorrect");

		assertTrue(office.checkCode("1234")); // enter correct code, so attempts should be reset
		assertEquals(0, office.getIncorrectAttempts(), "incorrect attempt count incorrect");

	}

	@Test
	void testRoomLockOut() {

		Room room = new Room(321);

		assertFalse(room.isLockedOut(), " should never be locked out of a room");

		// attempt multiple incorrect codes
		for (int i = 0; i < 100; i++) {
			assertFalse(room.checkCode("765432" + i));
			assertFalse(room.isLockedOut(), " should never be locked out of a room");
		}

		assertTrue(room.checkCode("9999"));
	}

	@Test
	void testRoomIncorrectCount() {

		Room room = new Room(321);

		assertEquals(0, room.getIncorrectAttempts());

		// attempt 2 incorrect codes
		assertFalse(room.checkCode("6443"));
		assertEquals(0, room.getIncorrectAttempts(), "attempt count should always be 0");

		assertFalse(room.checkCode("7635"));
		assertEquals(0, room.getIncorrectAttempts(), "attempt count should always be 0");

	}
}
