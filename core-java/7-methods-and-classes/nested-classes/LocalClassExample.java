public class LocalClassExample {
	
    static String regularExpression = "[^0-9]";
	
    public static void validatePhoneNumber(String phoneNumber1, String phoneNumber2) {
		final int numberLength = 10;
		
		//local clases are often just used within a block, such as inside one method only 
        class PhoneNumber {
			
            String formattedPhoneNumber = null;
			
			/*
			Enclosing class' variables : 
			A local class has access to the members of its enclosing class. 
			e.g. PhoneNumber constructor accesses LocalClassExample.regularExpression.
			
			Block's variables :
			Local class can only access effectively final local variables of the block. (captured variable)
			
			Shadowing :
			Declarations of a type in a local class shadow declarations in the enclosing scope 
			
			Static members :
			-Local classes have no static members (Similar to inner classes) unless they are 
			constants (primitive and public static final String foo = "bar")
			-Local classes in static methods can only refer to static members of the enclosing 
			class.
			-Since interfaces are implcitly static, no inner interfaces within local class
			*/
			
            PhoneNumber(String phoneNumber){
                
				String currentNumber = phoneNumber.replaceAll(regularExpression, "");
                
				if (currentNumber.length() == numberLength) 
					formattedPhoneNumber = currentNumber;
                else
                    formattedPhoneNumber = null;
            }

            public String getNumber() {
                return formattedPhoneNumber;
            }
        }

        PhoneNumber myNumber1 = new PhoneNumber(phoneNumber1);
        PhoneNumber myNumber2 = new PhoneNumber(phoneNumber2);

        if (myNumber1.getNumber() == null) 
            System.out.println("First number is invalid");
        else
            System.out.println("First number is " + myNumber1.getNumber());
		
        if (myNumber2.getNumber() == null)
            System.out.println("Second number is invalid");
        else
            System.out.println("Second number is " + myNumber2.getNumber());
    }

    public static void main(String... args) {
        validatePhoneNumber("123-456-7890", "456-7890");
    }
}