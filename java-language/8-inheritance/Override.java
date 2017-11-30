// Method overriding allows a child to change a method implementation from the parent's (if it's not final).
// Note that the signatures must be the same (same name different signature is overloading)

// Method overriding is the basis of dynamic dispatch.
// The specific implementation to execute is determined at runtime, based on what object the reference points to.
// Therefore polymorphism is possible by assigning references to subclasses to a variable of type superclass/interface
// and invoking some method with different implementations.
// Recall that the reference though must have that method in its interface first. 

class A {
 int i, j;
 A(int a, int b) {
 i = a;
 j = b;
 }
 // display i and j
 void show() {
 System.out.println("i and j: " + i + " " + j);
 }
}
class B extends A {
 int k;
 B(int a, int b, int c) {
 super(a, b);
 k = c;
 }
 // display k – this overrides show() in A
 void show() {
 System.out.println("k: " + k);
 }
}
class Override {
 public static void main(String args[]) {
 B subOb = new B(1, 2, 3);
 subOb.show(); // this calls show() in B
 }
}