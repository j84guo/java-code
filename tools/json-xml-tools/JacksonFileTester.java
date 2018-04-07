import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JacksonFileTester {
   public static void main(String args[]) {
      JacksonFileTester tester = new JacksonFileTester();

      try {

         // 1. build object and write to file
         Student student = new Student();
         student.setAge(10);
         student.setName("Jackson");
         tester.writeJSON(student);

         // 2. read json into object and print
         Student student1 = tester.readJSON();
         System.out.println(student1);

      } catch (JsonParseException e) {
         e.printStackTrace();
      } catch (JsonMappingException e) {
         e.printStackTrace();
      }  catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void writeJSON(Student student) throws JsonGenerationException, JsonMappingException, IOException {
      // object mapper can create file and write a serialized object using writeValue()
      ObjectMapper mapper = new ObjectMapper();
      mapper.writeValue(new File("student.json"), student);
   }

   private Student readJSON() throws JsonParseException, JsonMappingException, IOException {
      // object mapper can read file and write a deserialized the string into object using readValue()
      ObjectMapper mapper = new ObjectMapper();
      Student student = mapper.readValue(new File("student.json"), Student.class);
      return student;
   }
}

class Student {
   private String name;
   private int age;

   public Student() {
   }

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
