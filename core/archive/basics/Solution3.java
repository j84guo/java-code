import java.util.*;

public class Solution3 {
    
    //key-value maps
    Map<String, Integer> magazineMap;
    Map<String, Integer> noteMap; 
    
    public Solution3(String magazine, String note) {
        magazineMap = new HashMap<String, Integer>();
        noteMap = new HashMap<String, Integer>(); 
        
        String[] marr = magazine.split("\\s+");
        String[] narr = note.split("\\s+");
        for (String a : marr) {
            Integer freq = magazineMap.get(a);
            magazineMap.put(a, (!magazineMap.containsKey(a)) ? 1 : freq + 1);
            //System.out.println(a);
        }
        for (String a : narr) {
            Integer freq = noteMap.get(a);
            noteMap.put(a, (!noteMap.containsKey(a)) ? 1 : freq + 1);
            //System.out.println(a);
        }
    }
    
    public boolean solve() {

        for (Map.Entry<String, Integer> entry : noteMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            
            if(magazineMap.get(key) < noteMap.get(key)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        
        // Eat whitespace to beginning of next line
        scanner.nextLine();
        
        Solution3 s = new Solution3(scanner.nextLine(), scanner.nextLine());
        scanner.close();
        
        boolean answer = s.solve();
        if(answer)
            System.out.println("Yes");
        else System.out.println("No");
      
    }
}
