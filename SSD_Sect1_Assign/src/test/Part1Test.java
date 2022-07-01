package test;

import manager.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * DO NOT EDIT THE CONTENTS OF THIS FILE.
 * 
 * Each of these tests covers Part 1 functionality.
 * 
 * There are a total of 10 tests within this test class.
 *  
 * @author mdixon
 */
class Part1Test {

	@Test
	void testPerson() {

		// create a Tenant, since Person is abstract
		Tenant tenant = new Tenant("E.Barry");
		
		assertNull(tenant.getAddr());
		assertNull(tenant.getEmail());
		assertNull(tenant.getPhone());
		
		tenant.setAddr("123 Baker Street");
		tenant.setEmail("e@test.com");
		tenant.setPhone("8981555-092-323-5555555");
		
		assertTrue("123 Baker Street".equals(tenant.getAddr()), "set/getAddr() failed.");
		assertTrue("e@test.com".equals(tenant.getEmail()), "set/getEmail() failed.");
		assertTrue("8981555-092-323-5555555".equals(tenant.getPhone()), "set/getPhone() failed.");
	}
	
	@Test
	void testTenantName() {
		Tenant tenant = new Tenant("B.Smith");
		assertTrue("B.Smith".equals(tenant.getName()), "Person.getName() failed");
		
		tenant.setName("L.Polker");
		assertTrue("L.Polker".equals(tenant.getName()), "Person.setName() failed");
	}

	@Test
	void testGuestName() {
		
		Guest guestA = new Guest("W.Thompson", "12345");
		Guest guestB = new Guest("J.Johnson", "54321");
		assertTrue("W.Thompson".equals(guestA.getName()), "Person.getName() failed");
		assertTrue("J.Johnson".equals(guestB.getName()), "Person.getName() failed");
		
		guestA.setName("I.Ignot");
		assertTrue("I.Ignot".equals(guestA.getName()), "Person.setName() failed");
		
		// ensure guestB unchanged
		assertTrue("J.Johnson".equals(guestB.getName()), "Person.getName() failed");
	}
	
	@Test
	
	void testGuestCard() {
		
		Guest guestA = new Guest("W.Thompson", "12345");
		Guest guestB = new Guest("J.Johnson", "54321");
		assertEquals("12345", guestA.getCardNo(), "Person.getCardNo() failed");
		assertEquals("54321", guestB.getCardNo(), "Person.getCardNo() failed");
	}
	
	@Test
	void testOwner() {
		
		PropertyOwner owner = new PropertyOwner("B.igMan");

		assertTrue("B.igMan".equals(owner.getName()), "Person.setName() failed");
	}
	
	@Test
	void testRoom() {
	
		Room r = new Room(123);
		assertEquals(123, r.getRoomNum(), "Room.getRoomNum() failed");
		
		r = new Room(99);
		assertEquals(99, r.getRoomNum(), "Room.getRoomNum() failed");
	}
	
	@Test
	void testHotel() {
	
		Hotel h = new Hotel("Cliffe Edge", 350);
		assertEquals(350, h.getRooms(), "Room.getRooms() failed");

		assertTrue("Cliffe Edge".equals(h.getAddress()), "Propery.getAddress() failed");
		
		Hotel h2 = new Hotel("Cliffe Edge2", 340);
		assertEquals(340, h2.getRooms(), "Room.getRooms() failed");
		
		assertTrue("Cliffe Edge2".equals(h2.getAddress()), "Propery.getAddress() failed");
	}
	
	@Test
	void testOffice() {
	
		Office office = new Office("Slough industrial estate");
		
		assertTrue("Slough industrial estate".equals(office.getAddress()), "Propery.getAddress() failed");
	}
	
	@Test
	void testHouse() {
	
		House house = new House("Hilltop Manor", 5, 3, 2, true);
		
		assertTrue("Hilltop Manor".equals(house.getAddress()), "Propery.getAddress() failed");
		assertEquals(5, house.getBedrooms(), "Domocile.getBedrooms() failed");
		assertEquals(3, house.getBathrooms(), "House.getBathrooms() failed");
		assertEquals(2, house.getReceptionRooms(), "House.getReceptionRooms() failed");
		assertTrue(house.hasGarage(), "House.hasGarage() failed");
		
		House house2 = new House("Low lake Manor", 7, 18, 10, false);
		
		assertTrue("Low lake Manor".equals(house2.getAddress()), "Propery.getAddress() failed");
		assertEquals(7, house2.getBedrooms(), "Domocile.getBedrooms() failed");
		assertEquals(18, house2.getBathrooms(), "House.getBathrooms() failed");
		assertEquals(10, house2.getReceptionRooms(), "House.getReceptionRooms() failed");
		assertFalse(house2.hasGarage(), "House.hasGarage() failed");
	}
	
	@Test
	void testFlat() {
	
		Flat flat = new Flat("Pokey Corner", 1);
		assertTrue("Pokey Corner".equals(flat.getAddress()), "Propery.getAddress() failed");
		assertEquals(1, flat.getBedrooms(), "Domocile.getBedrooms() failed");
		
		assertEquals(0,flat.getOccupants());
		flat.setOccupants(4);
		assertEquals(4,flat.getOccupants());
	}	
}
