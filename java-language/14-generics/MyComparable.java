import java.util.*;

public interface MyComparable<T> {
    public int compareTo(T o);
}

/*
Some common naming conventions for generic types : 

E – Element (used extensively by the Java Collections Framework, for example ArrayList, Set etc.)
K – Key (Used in Map)
N – Number
T – Type
V – Value (Used in Map)
S,U,V etc. – 2nd, 3rd, 4th types

*/