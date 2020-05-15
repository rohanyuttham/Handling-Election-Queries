// package col106.assignment3.Election;
// import col106.assignment3.BST.BST;
// import col106.assignment3.BST.Node;
// import col106.assignment3.Heap.Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
public class Election implements ElectionInterface 
{
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() {
		ElectionDriverCode EDC = new ElectionDriverCode();
		System.setOut(EDC.fileout());
	}
	/*
	 * end code
	 */
	//write your code here
	BST<String, Integer> ElectionDS = new BST<String, Integer>();	
	public void insert(String name, String candID, String state, String district, String constituency, String party, String votes)
	{
		this.ElectionDS.insert(candID, Integer.parseInt(votes), name, state, district, constituency, party);	
	}
	public void updateVote(String name, String candID, String votes){
		Node candNode = this.ElectionDS.find(candID);
		this.ElectionDS.delete(candID);
		this.ElectionDS.insert(candID, Integer.parseInt(votes), name, candNode.state, candNode.district, candNode.constituency, candNode.party);	

	}
	public void topkInConstituency(String constituency, String k){
		//write your code here
		Heap<String, Integer> ElectionHeap= new Heap<String, Integer>();
		for(int i=0; i<this.ElectionDS.constituencyarr.length;i++)
		{	
			if(this.ElectionDS.constituencyarr[i]==null)
			break;
			if(this.ElectionDS.constituencyarr[i].equals(constituency))
			{	if(this.ElectionDS.skey[i]!="")
				{
					ElectionHeap.insert(this.ElectionDS.skey[i], this.ElectionDS.svalue[i]);
				}	
			}
		}
		int topk = 0;
		while(true)
		{
			if(topk==Integer.parseInt(k))
			break;
			if(ElectionHeap.keyIndex<=0)
			break;
			int maxvote = ElectionHeap.extractMax();

			int j;
			for(j = 0; j<(this.ElectionDS.skey).length;j++)
			{
				if(this.ElectionDS.svalue[j]==maxvote)
				break;
			}
			Node y = this.ElectionDS.find(this.ElectionDS.skey[j]);
			System.out.println(this.ElectionDS.sname[j]+", "+this.ElectionDS.skey[j]+", "+this.ElectionDS.sparty[j]);
			topk++;
		}
	}
	public void leadingPartyInState(String state){
		//write your code here
		int voteparty;
		ArrayList<String> partylist = new ArrayList<String>();
		for(int partyIndex = 0; partyIndex<this.ElectionDS.Index; partyIndex++)
		{
			if(!partylist.contains(this.ElectionDS.sparty[partyIndex]) && this.ElectionDS.sstate[partyIndex].equals(state))
			partylist.add(this.ElectionDS.sparty[partyIndex]);
		}
		Heap<String, Integer> voteHeap = new Heap<String, Integer>();
		for(int voteCount = 0; voteCount<partylist.size(); voteCount++)
		{
			voteparty = 0;
			for(int i=0; i<this.ElectionDS.Index;i++)
			{	
				if(this.ElectionDS.sparty[i].equals(partylist.get(voteCount)) && this.ElectionDS.sstate[i].equals(state))
				{
					if(!(this.ElectionDS.skey[i]).equals(""))
					{
						voteparty += this.ElectionDS.svalue[i];
					}	
				}
			}
			voteHeap.insert(partylist.get(voteCount), voteparty);
		}
		// voteHeap.printHeap();
		voteHeap.TopList(voteHeap);
	}
	public void cancelVoteConstituency(String constituency)
	{
		Heap<Integer, String> HeapForCancel= new Heap<Integer, String>();
		String[] StforCancel = new String[10];
		int j = 0;
		for(int i=0; i<this.ElectionDS.Index;i++)
		{	
			if(this.ElectionDS.constituencyarr[i].equals(constituency))
			{	if(!(this.ElectionDS.skey[i]).equals(""))
				{
					StforCancel[j] = this.ElectionDS.skey[i];
					HeapForCancel.insert(j,this.ElectionDS.skey[i]);
					j++;
				}	
			}

		}
		Stack st = new Stack();
		for(int i = 0; i<j; i++)
		{
			st.push(HeapForCancel.extractMax());
		}
		for(int i = 0; i<j; i++)
		this.ElectionDS.delete((String)st.pop());
	}
	public void leadingPartyOverall()
	{
		ArrayList<String> partylist = new ArrayList<String>();
		for(int partyIndex = 0; partyIndex<this.ElectionDS.Index; partyIndex++)
		{
			if(!partylist.contains(this.ElectionDS.sparty[partyIndex]))
			partylist.add(this.ElectionDS.sparty[partyIndex]);
		}
		Heap<String, Integer> voteHeap = new Heap<String, Integer>();
		for(int voteCount = 0; voteCount<partylist.size(); voteCount++)
		{
			int voteparty = 0;
			for(int i=0; i<this.ElectionDS.Index;i++)
			{	
				if(this.ElectionDS.sparty[i].equals(partylist.get(voteCount)))
				{	if(!(this.ElectionDS.skey[i]).equals(""))
					{
						voteparty += this.ElectionDS.svalue[i];
					}	
				}
			}
			voteHeap.insert(partylist.get(voteCount), voteparty);
		}
		// voteHeap.printHeap();
		voteHeap.TopList(voteHeap);
	}
	public void voteShareInState(String party,String state){
		int voteparty = 0;
		int totalvote = 0;
		for(int i=0; i<this.ElectionDS.Index;i++)
		{	
			if(this.ElectionDS.sparty[i].equals(party)&&this.ElectionDS.sstate[i].equals(state))
			{	if(!(this.ElectionDS.skey[i]).equals(""))
				{
					voteparty += this.ElectionDS.svalue[i];

				}	
			}
			if(this.ElectionDS.sstate[i].equals(state))
			{
				if(!(this.ElectionDS.skey[i]).equals(""))
				totalvote += this.ElectionDS.svalue[i];
			}
		}
		System.out.println((int)(100*((float)voteparty)/(float)totalvote));
	}
	
	public void printElectionLevelOrder() {
		this.ElectionDS.printBST();
	}
}