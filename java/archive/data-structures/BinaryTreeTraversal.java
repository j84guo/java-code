/*
each node has a key, a left and right child
*/
class Node{
	int data;
	Node left, right;

	public Node(int item){
		this.data = item;
		left = null;
		right = null;
	}
}

/*
binary tree with various traversals 
*/
class BinaryTreeTraversal{
	//reference to root node
	Node root;

	//constructor starts wiith empty tree
	BinaryTreeTraversal(){
		root = null;
	}

	//post order (LRN)
	void printPostorder(Node node){
		if(node == null){
			return;
		}

		printPostOrder(node.left);
		printPostOrder(node.right);
		System.out.println(node.data);
	}
	void printPostorder(){
    	printPostorder(this.root);
    }

	//in order (LNR)
	void printInOrder(Node node){
		if(node == null){
			return;
		}

		printPostOrder(node.left);
		System.out.println(node.data);
		printPostOrder(node.right);
	}
	void printInorder(){
    	printInorder(this.root);
    }

	//pre order (NLR)
	void printInOrder(Node node){
		if(node == null){
			return;
		}

		System.out.println(node.data);
		printPostOrder(node.left);
		printPostOrder(node.right);
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

