/*
each node has a key, a left and right child
insert method
*/
class Node{
	int data;
	Node left, right;

	public Node(int item){
		this.data = item;
		left = null;
		right = null;
	}

	void insert(int value){
    	if(value <= this.data){
    		if(this.left == null){
    			this.left = new Node(value);
    		}else{
    			this.left.insert(value);
    		}
    	}else{
    		if(this.right == null){
    			this.right = new Node(value);
    		}else{
    			this.right.insert(value);
    		}
    	}
    
    }

    public boolean contains(int value){
    	if(value == this.data){
    		return true;
    	}else if (value < this.data){
    		if(this.left == null){
    			return false;
    		}else{
    			return this.left.contains(value);
    		}
    	}else{
    		if(this.right == null){
    			return false;
    		}else{
    			return this.right.contains(value);
    		}
    	}
    }
}

/*
binary tree with various traversals 
*/
class BinaryTree{
	//reference to root node
	Node root;

	//constructor starts wiith empty tree
	BinaryTree(){
		root = null;
	}

	//post order (LRN)
	void printPostorder(Node node){
		if(node == null){
			return;
		}

		printPostorder(node.left);
		printPostorder(node.right);
		System.out.println(node.data);
	}
	void printPostorder(){
    	printPostorder(this.root);
    }

	//in order (LNR)
	void printInorder(Node node){
		if(node == null){
			return;
		}

		printInorder(node.left);
		System.out.println(node.data);
		printInorder(node.right);
	}
	void printInorder(){
    	printInorder(this.root);
    }

	//pre order (NLR)
	void printPreorder(Node node){
		if(node == null){
			return;
		}

		System.out.println(node.data);
		printPreorder(node.left);
		printPreorder(node.right);
	}
	void printPreorder(){
    	printPreorder(this.root);
    }

    //main method
    public static void main(String[] args){
    	//build tree
    	BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        //traverse
        System.out.println("Preorder traversal of binary tree is ");
        tree.printPreorder();
 
        System.out.println("\nInorder traversal of binary tree is ");
        tree.printInorder();
 
        System.out.println("\nPostorder traversal of binary tree is ");
        tree.printPostorder();
    }
}

