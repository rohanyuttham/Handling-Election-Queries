// package col106.assignment3.BST;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Stack;

public class BST<T extends Comparable, E extends Comparable> implements BSTInterface<T, E>  
{
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() 
	{
		BSTDriverCode BDC = new BSTDriverCode();
		System.setOut(BDC.fileout());
	}
	/*
	 * end code
	 * start writing your code from here
	 */
	
	//write your code here 
	Node<T,E> root ;
	public T[] keyarr = (T[]) new Comparable[100];
	public int Index = 0;
	public E[] valuearr = (E[]) new Comparable[100];
	public String[] constituencyarr = new String[100];
	public Integer[] svalue = new Integer[100];
	public String[] skey = new String[100];
	public String[] sname = new String[100];
	public String[] sparty = new String[100];
	public String[] sstate = new String[100];


	public Node<T,E> find(T key)
	{
		int i;
		int k=0;
		for(i = 0; i<this.Index;i++)
		{
			// if(this.keyarr[i]==null)
			// break;
			//System.out.println(k);
			if((this.keyarr[i]).equals(key))
			k=i;
			
		}
		// System.out.println(i);
		// System.out.println(valuearr[i]);
		// System.out.println(root.value);
			
		E value2find = valuearr[k];
		//System.out.println(value2find.compareTo(this.root.value));

		if(value2find.compareTo(this.root.value)==0)
		{
			//System.out.println("inside equal");
			return root;
		}

		Node root1 = this.root;
		//System.out.println(root1.value.compareTo(value2find));
		while(root1 != null && root1.value.compareTo(value2find)!=0)
		{
			//System.out.println("inside while");
			if(value2find.compareTo(root1.value) < 0)
			root1 = root1.left;

			else
			root1 = root1.right;

		}
		if(root1 == null)
		return null;

		return root1;
	}

	public void insert(T key, E value) 
	{
		if(Index >= this.keyarr.length)
		{
			keyarr = Arrays.copyOf(keyarr, Index*2);
			valuearr = Arrays.copyOf(valuearr, Index*2);
		}
		keyarr[Index] = key;
		valuearr[Index] = value;
		Index++;

		Node Node1 = new Node(key, value);
		if(root == null)
		{
			root = Node1;
		}
		else
		{
			Node node2 = root;
			while(true)
			{
				if(value.compareTo(node2.value) < 0)
				{
					if(node2.left == null)
					{
						node2.left = Node1;
						break;
					}
					else
					node2 = node2.left;
				}
				else
				{
					if(node2.right == null)
					{
						node2.right = Node1;
						break;
					}
					else
					node2 = node2.right;
				}
			}
		}

	}
	public void insert(T key, E value, String name, String state, String district, String constituency, String party) 
	{
		if(Index >= this.keyarr.length)
		{
			keyarr = Arrays.copyOf(keyarr, Index*2);
			valuearr = Arrays.copyOf(valuearr, Index*2);
			constituencyarr = Arrays.copyOf(constituencyarr, Index*2);
			svalue = Arrays.copyOf(svalue, Index*2);
			skey = Arrays.copyOf(skey, Index*2);
			sname = Arrays.copyOf(sname, Index*2);
			sparty = Arrays.copyOf(sparty, Index*2);
			sstate = Arrays.copyOf(sstate, Index*2);
		}
		keyarr[Index] = key;
		valuearr[Index] = value;
		constituencyarr[Index] = constituency;
		svalue[Index] = (Integer)value;
		skey[Index] = (String)key;
		sname[Index] = (String)name;
		sparty[Index] = (String)party;
		sstate[Index] = (String)state;
		Index++;


		Node Node1 = new Node(key, value, name, state, district, constituency, party);
		if(root == null)
		{
			root = Node1;
		}
		else
		{
			Node node2 = root;
			while(true)
			{
				if((value).compareTo(node2.value)  < 0)
				{
					if(node2.left == null)
					{
						node2.left = Node1;
						break;
					}
					else
					node2 = node2.left;
				}
				else
				{
					if(node2.right == null)
					{
						node2.right = Node1;
						break;
					}
					else
					node2 = node2.right;
				}
			}
		}
	}
	//================================================
	public void update(T key, E value) 
	{
		this.delete(key);
		this.insert(key, value);
	}
	
	public void delete(T key) 
	{
		//System.out.println("deleting");
		int l;
		int a =0;
		for( l=0;l<this.Index;l++)
		{
			// System.out.println("keyarrl= "+ keyarr[l]);
			if(keyarr[l].compareTo(key)==0)
			{
				// System.out.println("l is "+l);
				skey[l]="";
				sstate[l]="";
				// keyarr[l]=null;
				a = l;

			}
		}
		// System.out.println("to delete index a= "+a);
		Node Node2delete = this.find(key);
		//keyarr[a]=null;
		// System.out.println("node2delete is= "+ Node2delete.value);

		if(Node2delete.left == null && Node2delete.right == null)
		{
			// System.out.println("is a leaf node");
			if(Node2delete.value.compareTo(Node2delete.parentNode(this.root).value)<0)	
			{	
				Node2delete.parentNode(this.root).left = null;
				return;
			}
			if(Node2delete.value.compareTo(Node2delete.parentNode(this.root).value)>0)
			{
				Node2delete.parentNode(this.root).right = null;
				return;
			}			
		}
		if(Node2delete.left == null || Node2delete.right == null)
		{
			// System.out.println("is a node with one child");

			if(Node2delete.value.compareTo(this.root.value)==0)
			{
				// System.out.println("is root");
				if(Node2delete.right == null)
				{
					Node nodel = Node2delete.left;
					Node2delete.left = null;
					this.root = nodel;
				}
				else
				{
					Node noder = Node2delete.right;
					Node2delete.right = null;
					this.root = noder;
				}
				return;
			}
			if(Node2delete.right == null)
			{
				if(Node2delete.value.compareTo(Node2delete.parentNode(this.root).value)<0)
				Node2delete.parentNode(this.root).left = Node2delete.left;
				else
				Node2delete.parentNode(this.root).right = Node2delete.left;
				return;
			}
			if(Node2delete.left == null)
			{
				if(Node2delete.value.compareTo(Node2delete.parentNode(this.root).value)<0)
				Node2delete.parentNode(this.root).left = Node2delete.right;
				else
				Node2delete.parentNode(this.root).right = Node2delete.right;
				return;
			}
		}
		else
		{
			// System.out.println("is a node with 2 child");
			Node node2replacewith = Node2delete.right;

			while(true)
			{
				if(node2replacewith.left == null)
				break;
				node2replacewith = node2replacewith.left;		
			}
			// System.out.println("node2replacewith is= "+node2replacewith.value);
			Node parentofreplace = node2replacewith.parentNode(root);
			boolean b = true;
			if(node2replacewith.value.compareTo(parentofreplace.value)>0)
			node2replacewith.parentNode(root).right = null;
			else
			{
				node2replacewith.parentNode(root).left = null;
				b=false;
			}	
			if(Node2delete == root)
			{
				// System.out.println("is root");
				if(Node2delete.value.compareTo(parentofreplace.value)==0)
				{
					// System.out.println("child will replace");
					if(b)
					node2replacewith.left = Node2delete.left;
					else
					node2replacewith.right = Node2delete.right;
					root = node2replacewith;
					return;
					
				}
				
				node2replacewith.left = Node2delete.left;
				if(node2replacewith.right != null)
				{
					// System.out.println("left is null");
					if(node2replacewith.value.compareTo(parentofreplace.value)<0)
					parentofreplace.left = node2replacewith.right;
					else
					parentofreplace.right = node2replacewith.right;

					//node2replacewith.right = Node2delete.right;

				}
				node2replacewith.right = Node2delete.right;
				root = node2replacewith;
				return;

			}

			if(Node2delete.value.compareTo(Node2delete.parentNode(this.root).value)<0)
			{
				Node2delete.parentNode(this.root).left = node2replacewith;
				node2replacewith.left = Node2delete.left;   

				if(node2replacewith.right != null)
				{
					if(node2replacewith.value.compareTo(parentofreplace.value)<0)
					parentofreplace.left = node2replacewith.right;
					else
					parentofreplace.right = node2replacewith.right;

					node2replacewith.right = Node2delete.right;					

				}
				else
				{
					if(Node2delete.right!=null)
					node2replacewith.setRight(Node2delete.right);
				}
			}
			else
			{
				Node2delete.parentNode(this.root).setRight(node2replacewith);
				node2replacewith.setLeft(Node2delete.left); 
				
				if(node2replacewith.right != null)  
				{
					if(node2replacewith.value.compareTo(parentofreplace.value)<0)
					parentofreplace.left = node2replacewith.right; 
					else
					parentofreplace.right = node2replacewith.right;

					node2replacewith.right = Node2delete.right; 					

				}
				else
				{
					if(Node2delete.right!=null) 
					node2replacewith.setRight(Node2delete.right);
				}
			}
		}
	}
	
	
	public void printBST () 
	{
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);

		while(true)
		{
			int nodeCount = q.size(); 
            if(nodeCount == 0) 
                break; 
            while(nodeCount > 0) 
            { 
				Node node = q.peek(); 
				if(node.name==null)
				System.out.println(node.key + ", "+node.value);
				else
				System.out.println(node.name+", "+node.key+", "+node.state+", "+node.district+", "+node.constituency+", "+node.party+", "+node.value); 
                q.remove(); 
                if(node.left != null) 
                    q.add(node.left); 
                if(node.right != null) 
                    q.add(node.right); 
                nodeCount--; 
            } 
		}
	}

}

//package col106.assignment3.BST;





