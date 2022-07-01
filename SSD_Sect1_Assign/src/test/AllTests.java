package test;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

/**
 * DO NOT EDIT THE CONTENTS OF THIS FILE.
 * 
 * @author mdixon
 */

// note: the command below sometimes causes an issue within Eclipse IDE.
// IF the following is displayed "WARNING: Ignoring test class using JUnitPlatform runner:"
// Go to run-configurations and select JUnit4 as the Test runner if this will not execute.
@RunWith(JUnitPlatform.class)
@SelectClasses( {Part1Test.class, Part2Test.class, Part3Test.class} )
public class AllTests {

}
