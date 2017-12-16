
	//heapify a d-arry heap
	public void d_heapify(int[] A, int i, int n, int d){
		int largest = i;

		//loop through all children of node
		for (int j = 1; j <= d; j++){
			child = getChildIndex(i, j); // returns i * d + j
			
			if(l <= n && A[child] > A[largest]){
				largest = child;
			}

			if(largest != i){
				swap(largest, i); //swap elements in the array corresponding to these indices
				heapify(A, largest);
			}
		}
	}

	//extracts max element
	public int extractMax(int[] A, int i; int n, int d){
		int max = A[0];
		A[0] = A[n];
		n--;
		d_heapify(A, i, n, d); //note : i denotes the root node of the subtree we are looking at, it would most likely by passed in as 0	
		return max; 
	}

	//inserts
	public void insert(int[] A, int i, int n, int d, int value){
		n++; //increment heap size
		int j = n; //set counter
		
		//"bubble" the inserted value upwards until it is at a valid position
		// note : I'ave allowed the parameter i to dictate the root of the tree, probably it would just be 0 passed in
		while(j > i && A[ getParentIndex(j) ] < value){
			A[j] = A[ getParentIndex(j) ];
			j =  getParentIndex(j);
		}

		A[j] = value;
	}





