// ReflectionTest.java

package ed.db;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import org.testng.annotations.Test;

import ed.*;

public class ReflectionTest extends TestCase {
    
    public static class Person extends ReflectionDBObject {
        
        public Person(){

        }

        Person( String name ){
            _name = name;
        }

        public String getName(){
            return _name;
        }

        public void setName(String name){
            _name = name;
        }

        String _name;
    }

    public ReflectionTest()
        throws IOException {
        _db = new Mongo( "127.0.0.1" , "reflectiontest" );        
    }
    

    @Test
    public void test1(){
        DBCollection c = _db.getCollection( "persen.test1" );
        c.drop();
        c.setObjectClass( Person.class );
        
        Person p = new Person( "eliot" );
        c.save( p );

        DBObject out = c.findOne();
        assertEquals( "eliot" , out.get( "Name" ) );
        assertTrue( out instanceof Person , "didn't come out as Person" );
    }
    
    final Mongo _db;
    
    public static void main( String args[] )
        throws Exception {
        (new ReflectionTest()).runConsole();
    }

}