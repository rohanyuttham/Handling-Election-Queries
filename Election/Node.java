// package col106.assignment3.BST;
public class Node<T extends Comparable, E extends Comparable>
{
    public T key;
    public E value;
    public Node<T, E> left, right;
    public String name;
    //public String candID;
    public String state;
    public String district;
    public String constituency;
    public String party;
    
    //public String votes;


    //Constructors for node
    public Node() 
    {
        this.left = null; 
        this.right = null;
    }
    public Node(T key, E value) 
    {
        this.key = key;
        this.value = value; 
        this.left = null; 
        this.right = null;
    }
    public Node(T key, E value, String name, String state, String district, String constituency, String party)
    {
        this.key = key;
        this.value = value;
        this.name = name;
        this.state = state;
        this.district = district;
        this.constituency = constituency;
        this.party = party;
    }
    //=================================================
    //Methods for node
    public void setLeft(Node<T,E> l)
    {
        this.left = l;
    }
    public void setRight(Node<T,E> r)
    {
        this.right = r;
    }

    
    public Node<T,E> parentNode(Node root) 
	{
        E value = this.value;
        if(root==null)
        return null;

        int compareResult = value.compareTo(root.value);

        if(root.left != null)
        {
            if(value.compareTo(root.left.value)==0)
            compareResult = 0;
        }
        if(root.right!=null)
        {
            if(value.compareTo(root.right.value)==0)
            compareResult = 0;
        }

        if(compareResult<0)
        return parentNode(root.left);

        else if(compareResult>0)
        return parentNode(root.right);
        else
        return root;
       
	} 
}