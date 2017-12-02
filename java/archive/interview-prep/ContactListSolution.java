public class ContactListSolution {
	Node root;

	ContactListSolution(){
		root = new Node();
	}

	void insert(String str){
		insert(str, root, 0);
	}
	private void insert(String str, Node node, int index){
		if(index == str.length()){
			node.count++;
			return;
		}

		char c = str.charAt(index);
		if(node.letters[c - 'a'] == null){
			node.letters[c - 'a'] = new Node();
		}
		node.count++;
		
		insert(str, node.letters[c - 'a'], index+1);
	}

	int find(String str){
		return find(str, root, 0);
	}
	private int find(String str, Node node, int index){
		if(index == str.length()){
			return node.count;
		}

		char c = str.charAt(index);
		if(node.letters[c - 'a'] == null){
			return 0;
		}
		return find(str, node.letters[c - 'a'], index + 1);
	}

	public static void main(String[] args){
		
		ContactListSolution cl = new ContactListSolution();
		
		cl.insert("jackson"); 
		cl.insert("jab");
		cl.insert("jam");
		cl.insert("jal");
		cl.insert("kal");
		cl.insert("k");

		//System.out.println(cl.root.letters['j' - 'a'].letters[0].count);
		System.out.println(cl.find("a"));
		
	}
}

class Node{
	Node[] letters;
	int count;

	Node(){
		count = 0;
		letters = new Node[26];
	}
}