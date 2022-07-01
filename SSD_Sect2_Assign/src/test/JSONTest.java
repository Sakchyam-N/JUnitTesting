package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import org.junit.jupiter.api.Test;

import json.*;

class JSONTest {

	

	@Test
	void JSONBooleantest() {
		JSONBoolean booleanValue = JSONFactory.createBoolean(false);
		
		assertTrue(booleanValue.equals(JSONFactory.createBoolean(false)));
		
		assertFalse(booleanValue.equals(null));
		
		assertTrue(booleanValue.isBoolean());
		
		assertFalse(booleanValue.asBoolean());
		
		assertEquals("false",booleanValue.toString());
		
		JSONBoolean boolValue2 = JSONFactory.createBoolean(true);
		
		assertEquals("true",boolValue2.toString());
		
		assertEquals(1237,booleanValue.hashCode());
		
		assertEquals(1231,boolValue2.hashCode());
		
	}
	
	@SuppressWarnings( "unlikely-arg-type")
	@Test
	void JSONNumber() throws JSONException {
		
		JSONNumber dbValue = JSONFactory.createNumber(33.1);
		assertFalse(dbValue.equals(JSONFactory.createNumber(0)));
		assertTrue(dbValue.equals(JSONFactory.createNumber(33.1)));
		assertTrue(dbValue.equals(dbValue));
		assertFalse(dbValue.equals(null));
		assertFalse(dbValue.equals(dbValue.getClass()));
		
		JSONNumber longMinVal = JSONFactory.createNumber(-2147483650L);
		JSONNumber intValue = JSONFactory.createNumber(10);
		JSONNumber longMaxVal = JSONFactory.createNumber(2147483649L);
		assertEquals(-2147483650L,longMinVal.asLong());
		assertEquals(2147483649L,longMaxVal.asLong());
		assertEquals(10L,intValue.asLong());
		
		assertThrows(JSONException.class,()->dbValue.asLong());
		
		JSONNumber floatVal = JSONFactory.createNumber(45.45f);
		assertEquals(10,intValue.asInteger());
		assertThrows(JSONException.class,()->dbValue.asInteger());
		assertThrows(JSONException.class,()->floatVal.asInteger());
		
		JSONNumber shortVal = JSONFactory.createNumber(-2768);
		assertEquals(10,intValue.asShort());
		assertEquals(-2768,shortVal.asShort());
		assertThrows(JSONException.class,()->floatVal.asShort());
		
		JSONNumber byteVal = JSONFactory.createNumber(120);
		assertEquals(120,byteVal.asByte());
		assertThrows(JSONException.class,()->longMinVal.asByte());
		assertThrows(JSONException.class,()->longMaxVal.asByte());
		
		
		assertTrue(dbValue.isNumber());
		assertEquals(33.1,dbValue.getNumber());
		assertEquals("33.1",dbValue.toString());
		assertEquals(JSONFactory.createNumber(33.1).hashCode(),dbValue.hashCode());
		
		assertEquals(33.1f,dbValue.asFloat());
		assertEquals(-2147483649f,longMinVal.asFloat());
		assertEquals(2147483648f,longMaxVal.asFloat());
		assertEquals(45.45f,floatVal.asFloat());
		assertEquals(10f,intValue.asFloat());
		
		assertEquals(33.1,dbValue.asDouble());
		assertEquals(-2.14748365E9,longMinVal.asDouble());
		assertEquals(2.147483649E9,longMaxVal.asDouble());
		assertEquals(45.45000076293945,floatVal.asDouble());
		assertEquals(10,intValue.asDouble());
			
	}
	
	@Test
	void JSONNulltest() {
		JSONNull objNull = JSONFactory.createNull();
		assertTrue(objNull.isNull());
		assertFalse(objNull.isArray());
		assertFalse(objNull.isBoolean());
		assertFalse(objNull.isNumber());
		assertFalse(objNull.isObject());
		assertFalse(objNull.isString());
		
		assertEquals(953,objNull.hashCode());
		assertEquals("null",objNull.toString());
		assertTrue(objNull.equals(JSONFactory.createNull()));
		assertFalse(objNull.equals(null));
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	void JSONStringTest() throws JSONException {
		JSONString stringObj = JSONFactory.createString("");
		JSONString stringObj1 = JSONFactory.createString("good morning");
		JSONString stringObj2 = JSONFactory.createString("\"\\\\\\\\/\b\f\n\r\t");
		
		assertEquals("\"\\\"\\\\\\\\\\\\\\\\\\/\\b\\f\\n\\r\\t\"",stringObj2.toString());
		assertEquals("\"\"",stringObj.toString());
		
		
		assertEquals("good morning",stringObj1.asString());
		
		assertEquals("good morning",stringObj1.getString());
		
		assertTrue(stringObj1.equals(JSONFactory.createString("good morning")));
		
		assertTrue(stringObj1.isString());
		
		assertEquals(-1001961631,stringObj1.hashCode());
		
		assertTrue(stringObj1.equals(stringObj1));
		assertFalse(stringObj1.equals(null));
		
		assertFalse(stringObj1.equals(stringObj1.getClass()));
		assertEquals("\"good morning\"",stringObj1.toString());
		
		JSONString eschar = JSONFactory.createString("\u0016");
		
		
		eschar.toString();
	}
	
	@Test
	void JSONArray() throws JSONException {
		JSONArray arrayValue = JSONFactory.createArray(5);
		for(int i =0;i<5;i++) {
			arrayValue.addValue(i);
		}
		assertEquals("[0,1,2,3,4]",arrayValue.toString());
		assertTrue(arrayValue.equals(arrayValue));
		assertFalse(arrayValue.equals(null));
		assertFalse(arrayValue.equals(arrayValue.getClass()));
		assertEquals(arrayValue,arrayValue.asArray());
		assertTrue(arrayValue.isArray());
		assertEquals(JSONFactory.createNumber(4),arrayValue.get(4));
	}
	
	@Test
	void JSONParseTest() throws IOException, JSONException {
		JSONValue valueObject = JSONParser.parse("{ }");
		assertNotNull(valueObject);
		assertTrue(valueObject.isObject());
		assertEquals("{}",valueObject.toString());
		
		JSONValue fileObj = JSONParser.parseFile("example.json");

		assertNotNull(fileObj);
		assertTrue(fileObj.isObject());
		
		JSONObject jsonObj = fileObj.asObject();
		
		JSONValue prop1 = jsonObj.getMember("prop1");
		assertTrue(prop1.isString());
		assertEquals("A string", prop1.asString());
	
		JSONValue prop2 = jsonObj.getMember("prop2");
		assertTrue(prop2.isNumber());
		assertEquals(0, prop2.asInteger());
		
		JSONValue prop3 = jsonObj.getMember("prop3");
		assertTrue(prop3.isNumber());
		assertEquals(3.142, prop3.asDouble());
		
		JSONValue prop4 = jsonObj.getMember("prop4");
		assertTrue(prop4.isBoolean());
		assertEquals(true, prop4.asBoolean());
		
		JSONValue prop5 = jsonObj.getMember("prop5");
		assertTrue(prop5.isBoolean());
		assertEquals(false, prop5.asBoolean());
		
		JSONValue prop6 = jsonObj.getMember("prop6");
		assertTrue(prop6.isNull());
		
		
		
		assertThrows(JSONException.class,()->{JSONParser.parseFile("example1.json");});
		assertThrows(JSONException.class,()->{JSONParser.parseFile("example3.json");});
		assertThrows(JSONException.class,()->{JSONParser.parseFile("example2.json");});
		assertThrows(JSONException.class,()->{JSONParser.parseFile("example4.json");});
		assertThrows(JSONException.class,()->{JSONParser.parseFile("example5.json");});
		
		char c = '\u003E';
		assertThrows(JSONException.class,()->JSONParser.parse("{\"Unexpected Error\" :\""+c));
		assertThrows(JSONException.class,()->JSONParser.parse("{\"Unexpected Error\" :$"));
		assertThrows(JSONException.class,()->JSONParser.parse("{\"Unexpected Error\" :-"));
		
		assertThrows(JSONException.class, ()->JSONParser.parse("{\"Unexpected Error\" : \"\\u123\"}"));
		assertThrows(JSONException.class, ()->JSONParser.parse("{\"Unexpected Error\" : \"\\u\"}"));
		assertThrows(JSONException.class, ()->JSONParser.parse("{\"Unexpected Error\" :t}"));
		assertThrows(JSONException.class, ()->JSONParser.parse("{\"Unexpected Error\" :f}"));
		assertThrows(JSONException.class, ()->JSONParser.parse("{\"Unexpected Error\" :n}"));
		assertThrows(JSONException.class,()->JSONParser.parse("{\"Unexpected Error\" :\"\\x}"));
		assertThrows(JSONException.class,()->JSONParser.parse("{\"Unexpected Error\" :\""));
		assertThrows(JSONException.class,()->JSONParser.parse("{\"Unexpected Error\" : [1,]}"));
		
		
	}
	
	@Test
	void JSONObjectTest() throws FileNotFoundException, IOException, JSONException {
		JSONValue parseFileObject = JSONParser.parseFile("example.json");
		JSONObject jsonValue = parseFileObject.asObject();
		JSONObject jsonValue2 = JSONFactory.createObject(parseFileObject.asObject());
		JSONObject jsonValue2Copy = jsonValue2.copy();
		assertEquals(11,jsonValue.size());
		assertEquals(jsonValue.values(),jsonValue.values());
		
		assertEquals(2095311018,jsonValue2.hashCode());
		assertTrue(jsonValue2.equals(jsonValue2Copy));
		assertFalse(jsonValue2.equals(null));
		assertFalse(jsonValue2.equals(jsonValue2.getClass()));
		assertTrue(jsonValue2.equals(jsonValue2));
		
		assertTrue(jsonValue2.hasMember("prop1"));
		assertFalse(jsonValue2.hasMember("property"));
		assertFalse(jsonValue2.hasBooleanMember(null));
		assertTrue(jsonValue2.hasBooleanMember("prop4"));
		assertFalse(jsonValue2.hasBooleanMember("prop5"));
		assertFalse(jsonValue2.hasBooleanMember("prop1"));
        
		JSONArray arr = JSONFactory.createArray();
		arr.addValue(23);
		arr.addValue("ram");
		arr.addValue(true);
		arr.addValue(33.33);
		assertEquals(4,arr.size());
		jsonValue2.addMember("array4",arr);
		assertEquals(12,jsonValue2.size());
		jsonValue2.addMember("jsonObjProp", jsonValue);
		
		assertEquals(13,jsonValue2.size());
		assertThrows(JSONException.class,()->jsonValue2.asArray());
		assertThrows(JSONException.class,()->jsonValue2.asString());
		assertThrows(JSONException.class,()->jsonValue2.asLong());
		
	}
	
	
	
}
