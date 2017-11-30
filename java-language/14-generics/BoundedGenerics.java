// bounded generic parameters
public static <T extends Comparable<T>> int compare(T t1, T t2){
		return t1.compareTo(t2);
}

// upper bound wildcard makes your method more flexible
// e.g. List<Integer> or List<Double>
public static double sum(List<? extends Number> list){
	double sum = 0;
	for(Number n : list){
		sum += n.doubleValue();
	}
	return sum;
}
	
// unbounded wildcard
// same as <? extends Object>
public static void printData(List<?> list){
	for(Object obj : list){
		System.out.print(obj + "::");
	}
}

// lower bounded wildcard
public static void addIntegers(List<? super Integer> list){
	list.add(new Integer(50));
}
