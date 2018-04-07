/*
simple binding converts between java core types and json
object - Map<String, Object>
array - ArrayList<Object>
string - String
whole number - Integer, Long, BigInteger
fractional number - Double, BigDecimal
boolean - Boolean
null - null
*/

import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JacksonSimpleBindingTester {
   public static void main(String args[]) {
      JacksonSimpleBindingTester tester = new JacksonSimpleBindingTester();

      try {
         ObjectMapper mapper = new ObjectMapper();

         Map<String,Object> studentDataMap = new HashMap<String,Object>();
         int[] marks = {1,2,3};
         Student student = new Student();
         student.setAge(20);
         student.setName("Jackson");

         studentDataMap.put("student", student); // object
         studentDataMap.put("name", "Jackson"); // string
         studentDataMap.put("verified", false); // boolean
         studentDataMap.put("marks", marks); // array

         // write map to json file
         mapper.writeValue(new File("student.json"), studentDataMap);

         // read json into map
         studentDataMap = mapper.readValue(new File("student.json"), Map.class);

         // print
         System.out.println(studentDataMap.get("student"));
         System.out.println(studentDataMap.get("name"));
         System.out.println(studentDataMap.get("verified"));
         System.out.println(studentDataMap.get("marks"));

      } catch (JsonParseException e) {
         e.printStackTrace();
      } catch (JsonMappingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}

class Student {
   private String name;
   private int age;

   public Student() {}

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public String toString() {
      return "Student [ name: "+name+", age: "+ age+ " ]";
   }
}
