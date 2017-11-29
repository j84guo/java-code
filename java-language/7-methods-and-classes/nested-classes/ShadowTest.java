//outer class
public class ShadowTest {

	//outer class field
    public int x = 0;

	//innter class
    class FirstLevel {

		//inner class field
        public int x = 1;

		//inner class method
        void methodInFirstLevel(int x) {
			
            //local variable within inner class method
			System.out.println("x = " + x);
			
			//this refers to inner class
            System.out.println("this.x = " + this.x);
			
			//ShadowTest.this refers to enclosing class
            System.out.println("ShadowTest.this.x = " + ShadowTest.this.x);
        }
    }

    public static void main(String[] args) {
		
        ShadowTest st = new ShadowTest(); 
        ShadowTest.FirstLevel fl = st.new FirstLevel();
        fl.methodInFirstLevel(23); 
    }
}