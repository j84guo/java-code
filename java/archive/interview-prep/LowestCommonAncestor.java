import java.util.*;

public class LowestCommonAncestor{
	
	public static class Node{
		int data;
		Node left;
		Node right;
		Node parent;

		Node(int data){
			this.data = data;
		}
	}

	private Node root;

	public void insert(int data){
		root = insert(root, null, data);
	}

	private Node insert(Node node, Node parent, int data){
		if(node == null){
			System.out.println("Inserting : " + data);
			node = new Node(data);
			node.parent = parent;
		}else{
			if(data <= node.data){
				node.left = insert(node.left, node, data);
			}else{
				node.right = insert(node.right, node, data);
			}
		}

		return node;
	}

	public boolean exists(int data){
		Node node = findNode(root, data);
		return (node != null);
	}

	public Node findNode(int data){
		return findNode(root, data);
	}

	private Node findNode(Node node, int data){
		if (node == null) return null;
		
		if(node.data == data){
			return node;
		}else if(node.data > data){
			return findNode(node.left, data);
		}else{
			return findNode(node.right, data);
		}
	}

	public void delete(int data){
		root = delete(root, data);
	}

	private Node delete(Node node, int data){
		if(node == null) return null;
		if(data < node.data){
			node.left = delete(node.left, data);
		}else if(data > node.data){
			node.right = delete(node.right, data);
		}else{
			if(node.left == null && node.right == null){
				return null;
			}else if(node.left == null){
				return node.right;
			}else if(node.right == null){
				return node.left;
			}else{
				node.data = getMin(node.right);
				node.right = delete(node.right, node.data);
			}
		}
		return node;
	}

	private int getMin(Node node){
		if(node == null) return -1;

		while(node.left != null){
			node = node.left;
		}
		return node.data;
	}

	public void inOrder(){
		inOrder(root);
	}

	private void inOrder(Node node){
		if(node == null) return;
		inOrder(node.left);
		System.out.println(node.data);
		inOrder(node.right);
	}

	public void preOrder(){
		preOrder(root);
	}

	private void preOrder(Node node){
		if(node == null) return;
		System.out.println(node.data);
		preOrder(node.left);
		preOrder(node.right);
	}

	public void postOrder(){
		postOrder(root);
	}

	private void postOrder(Node node){
		if(node == null) return;
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.data);
	}

	/*
	first approach : O(log(n))
	1. trace node to root 
	2. compare paths (from root) to the last common node 
	*/
	public int getLowestCommonAncestor(int uId, int vId){
		Node u = findNode(uId);
		Node v = findNode(vId);

		ArrayList<Node> pathU = getPath(u);
		ArrayList<Node> pathV = getPath(v);

		int uIndex = pathU.size();
		int vIndex = pathV.size();

		while(uIndex > 0 && vIndex > 0) {
			Node nodeU = pathU.get(uIndex-1);
			Node nodeV = pathV.get(vIndex-1);
			
			if(nodeU.data != nodeV.data){
				if(uIndex == pathU.size() && vIndex == pathV.size()){
					return -1;
				}else{
					return pathU.get(uIndex).data; // or pathV.get(vIndex)
				}
			}

			if(nodeU.data == v.data){
				return v.data;
			}
			if(nodeV.data == u.data){
				return u.data;
			}

			uIndex--;
			vIndex--;
		}

		return -1;
	}

	private ArrayList<Node> getPath(Node node){
		ArrayList<Node> path = new ArrayList<Node>();
		while(node != null){
			path.add(node);
			node = node.parent;
		}
		return path;
	}

	/*
	second approach : O(log(n))
	iterate up until intersection (i.e. intersecting linked list question)
	*/
	public int getLowestCommonAncestor2(int uId, int vId){
		Node u = findNode(uId);
		Node v = findNode(vId);

		if(u == null || v == null) return -1;
		
		int uLength = getInclusiveLength(u);
		int vLength = getInclusiveLength(v);
		int diff = Math.abs(uLength - vLength);

		Node longer = uLength > vLength? u : v;
		Node shorter = uLength > vLength? v : u;

		for(int i=0; i<diff; i++){
			longer = longer.parent;
		}		 

		while(longer != null && shorter != null && longer != shorter){
			longer = longer.parent;
			shorter = shorter.parent;
		}

		return (longer == null || shorter == null) ? -1 : longer.data;
	}

	private int getInclusiveLength(Node node){
		int length = 0;
		while(node != null){
			length++;
			node = node.parent;
		}
		return length;
	}

	/*
	third approach : O(t) or O(n)
	iterate up until we find a node which covers both
	*/
	public int getLowestCommonAncestor3(int uId, int vId){
		Node u = findNode(uId);
		Node v = findNode(vId);

		if(u == null || v == null || !covers(root, u) || !covers(root, v)) return -1;
		if(covers(u, v)) return u.data;
		if(covers(v, u)) return v.data;		

		while(u != null){
			Node sibling = getSibling(u);
			if(covers(sibling, v)){
				return u.parent.data;
			}
			u = u.parent;
		}

		return -1;
	}

	private Node getSibling(Node node){
		if (node == null || node.parent == null) return null;
		if(node.parent.left == node){
			return node.parent.right;
		}else{
			return node.parent.left;
		}
	}

	public boolean covers(int vId){
		Node u = root;
		Node v = findNode(vId);
		return covers(u, v);
	}

	private boolean covers(Node source, Node node){
		if(source == null || node == null) return false;
		if(source == node) return true;
		return covers(source.left, node) || covers(source.right, node);
	}

	private void printPath(int data){
		Node node = findNode(data);
		while(node != null){
			System.out.print(node.data + " ");
			node = node.parent;
		}

	}

	/*
	fourth approach :
	1. no parent pointers
	2. if both nodes are left, check left, it both nodes are right, check right
	3. when left and right are on different subtrees, found lca
	*/
	public int getLowestCommonAncestor4(int uId, int vId){
		Node u = findNode(uId);
		Node v = findNode(vId);

		if(!covers(root, u) || !covers(root, v)) return -1;
		
		if(u == v) 
			return u.data;
		else
			return getLowestCommonAncestor4(root, u, v);
	}

	private int getLowestCommonAncestor4(Node source, Node u, Node v){
		if(source == null || u == null || v == null) return -1;

		boolean uOnLeft = covers(source.left, u);
		boolean vOnLeft = covers(source.left, v);

		if(uOnLeft != vOnLeft) return source.data;

		if(uOnLeft && vOnLeft){
			return getLowestCommonAncestor4(source.left, u, v);
		}else{
			return getLowestCommonAncestor4(source.right, u, v);
		}
	}

	public static void main(String[] args){

		LowestCommonAncestor tree = new LowestCommonAncestor();
		tree.insert(10);
		tree.insert(5);
		tree.insert(15);
		tree.insert(8);
		tree.insert(3);
		tree.insert(12);
		tree.insert(17);
		tree.insert(6);
		tree.insert(9);

		System.out.println(tree.getLowestCommonAncestor(6, 6));
		System.out.println(tree.getLowestCommonAncestor2(6, 6));
		System.out.println(tree.getLowestCommonAncestor3(6, 6));
		System.out.println(tree.getLowestCommonAncestor4(6, 6));
	
	}
}