/*
object mapping requires a class (with appropriate publlic fields or getters/setters) to read from and write to string
*/

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.util.List;

// uses object mapping
public class JacksonObjectTester {
   public static void main(String args[]) {

      // 1. create an ObjectMapper
      ObjectMapper mapper = new ObjectMapper();
      String jsonString = "{\"test\" : [1, 2, 3, 4, 5]}";

      try {

         // 2. deserialize json string to object using readValue(string, class)
         // reads json string to object
         Student student = mapper.readValue(jsonString, Student.class);
         System.out.println("The student object has been read by mapper.readValue(jsonString, Student.class) as :\n" + student + "\n");

         // 3. serialize object to json using writeValueAsString()
         // convert object to string
         mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
         jsonString = mapper.writeValueAsString(student);
         System.out.println("The student object has been serialized to json by mapper.writeValueAsString(student) as :\n" + jsonString);

      } catch (JsonParseException e) {
         e.printStackTrace();
      } catch (JsonMappingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}

// jackson library either looks for public fields matching a json key or getter and setter methods named as per normal Java conventions
// e.g. name -> setName()
class Student {
   private String name;
   private int age;
   public List<Integer> test;

   public Student() {}

   public String getName() {
      return this.name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public int getAge() {
      return this.age;
   }
   public void setAge(int age) {
      this.age = age;
   }

   public String toString() {
      return "Student [ name: "+name+", age: "+ age+ " ]";
   }
}
