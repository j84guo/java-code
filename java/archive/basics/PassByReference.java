public class PassByReference {

    /*
    
    The purpose of a nested class, considered a member of its enclosing (owner) class, is to clearly group the nested class with its surrounding class, 
    signaling that these two classes are to be used together. Or perhaps that the nested class is only to be used from inside its enclosing class.

    Since they are members, they can have access modifiers public, private, package-private or protected (as opposed to just 
    public and package-private for top-level classes). 

    Static nested classes are themselves not static at all. In java, no class is static. Static keyword in nested classes implies that it is 
    another static member of the outer class. But it is just another raw class . Thats why we can instantiate this class.
    
    */
  
    //main method
    public static void main(String args[]) {
       Car car = new Car("BMW");
       System.out.println("Brand of Car Inside main() before: "+ car.brand);
       printBrand(car);
       System.out.println("Brand of Car Inside main()after: "+ car.brand);
    }
  
    //static method
    public static void printBrand(Car car){
        car.brand = "Maruti";
        System.out.println("Brand of Car Inside printBrand(): "+car.brand);
    }
  
    //private static nested class
    //note : inner classes are non-static nested classes
    private static class Car{
        private String brand;
      
        public Car(String brand){
            this.brand = brand;
        }

    }
}
