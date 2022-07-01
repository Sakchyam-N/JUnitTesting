package test;


import manager.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * DO NOT EDIT THE CONTENTS OF THIS FILE.
 * 
 * Each of these tests covers Part 3 functionality.
 * 
 * There are a total of 5 tests within this test class.
 *  
 * @author mdixon
 */
class Part3Test {
	
	@Test
	void testPropertyTenant() {
		
		Office office = new Office("1600 Pennsylvania Ave");
		Tenant tenantA = new Tenant("D.Trump");
		Tenant tenantB = new Tenant("J. Biden");
		
		assertFalse(office.hasTenant());
		
		office.setTenant(tenantA);
		assertTrue(office.hasTenant());
		
		office.removeTenant();
		assertFalse(office.hasTenant());
		
		office.setTenant(tenantB);
		assertTrue(office.hasTenant());
	}
	
	@Test
	void testOwnedProperties() {

		PropertyOwner owner = new PropertyOwner("D.Trump");
		Flat flat1 = new Flat("123 Story Lane", 4);
		Flat flat2 = new Flat("125 Story Lane", 3);
		Hotel hotel = new Hotel("The Bronx, NYC", 455);

		assertEquals(0, owner.getPropertyCount());
		
		assertTrue(owner.addProperty(flat1));
		assertEquals(1, owner.getPropertyCount());
		
		assertTrue(owner.addProperty(flat2));
		assertEquals(2, owner.getPropertyCount());
				
		assertTrue(owner.addProperty(hotel));
		assertEquals(3, owner.getPropertyCount());
		
		assertFalse(owner.addProperty(flat2), "property added twice?");	// attempt to add same property again
		assertEquals(3, owner.getPropertyCount());
	}
	
	@Test
	void testRemoveOwnedProperties() {

		PropertyOwner owner = new PropertyOwner("D.Trump");
		Flat flat1 = new Flat("128 Story Lane", 2);
		Flat flat2 = new Flat("130 Story Lane", 3);
		Flat flat3 = new Flat("132 Story Lane", 5);
		Flat flat4 = new Flat("134 Story Lane", 3);
		
		assertTrue(owner.addProperty(flat1));	
		assertTrue(owner.addProperty(flat2));
		assertTrue(owner.addProperty(flat3));
		assertEquals(3, owner.getPropertyCount());

		assertTrue(owner.removeProperty(flat1));
		assertEquals(2, owner.getPropertyCount());
		
		assertFalse(owner.removeProperty(flat1), "Property removed twice?");
		assertEquals(2, owner.getPropertyCount());
		
		assertTrue(owner.addProperty(flat4));
		assertEquals(3, owner.getPropertyCount());
		
		owner.clearOwnedProperties();
		assertEquals(0, owner.getPropertyCount());
	}
	
	@Test
	void testRoomOccupant() {

		Room room = new Room(1);
		Guest guestA = new Guest("Billbo Bosher", "98472");
		Guest guestB = new Guest("Cliffedge Claro", "98472");
		
		assertFalse(room.hasOccupant());
		room.setOccupant(guestA);
		assertTrue(room.hasOccupant());
		
		room.setOccupant(guestB);
		assertTrue(room.hasOccupant());
		
		room.removeOccupant();
		assertFalse(room.hasOccupant());
	}
	
	@Test
	void testHotelRoomOccupant() {

		Hotel hotel = new Hotel("The Bronx, NYC", 455);
		
		Guest guestA = new Guest("Billbo Bosher", "98472");
		Guest guestB = new Guest("Cliffedge Claro", "98472");
		
		assertEquals(0, hotel.getOccupiedRoomCount());
		
		hotel.occupyRoom(1, guestA);
		assertEquals(1, hotel.getOccupiedRoomCount());
		
		hotel.occupyRoom(2, guestB);
		assertEquals(2, hotel.getOccupiedRoomCount());
		
		hotel.freeRoom(2);
		assertEquals(1, hotel.getOccupiedRoomCount());
		
		hotel.freeRoom(2);	// attempt to free already free room
		assertEquals(1, hotel.getOccupiedRoomCount(), "room freed when not occupied");
		
		hotel.occupyRoom(1, guestB);	// occupy with a different guest
		assertEquals(1, hotel.getOccupiedRoomCount());
		
		hotel.freeRoom(1);
		assertEquals(0, hotel.getOccupiedRoomCount());
	}
	
}
