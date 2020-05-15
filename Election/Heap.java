// package col106.assignment3.Heap ;
import java.util.Arrays;
import java.util.Stack;

public class Heap<T extends Comparable, E extends Comparable> implements HeapInterface <T, E> 
{
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() 
	{
		HeapDriverCode HDC = new HeapDriverCode();
		System.setOut(HDC.fileout());
	}
	/*
	 * end code
	 */
		/*
	 * end code
	 */
	
	public T[] keyarr = (T[]) new Comparable[10];
	public int keyIndex = 0;

	public E[] valuearr = (E[]) new Comparable[10];
	private int valueIndex = 0;
	
	public void insert(T key, E value) 
	{

		if(valueIndex >= this.keyarr.length)
		{
			keyarr = Arrays.copyOf(keyarr, valueIndex*2);
			valuearr = Arrays.copyOf(valuearr, keyIndex*2);
		}

		if(this.keyIndex == 0)
		{
			keyarr[0] = key;
			valuearr[0] = value;
			keyIndex++;
			valueIndex++;
			return;
		}
		else
		{
			keyarr[keyIndex] = key;
			valuearr[valueIndex] = value;
			int vi = valueIndex;
			while(true)
			{
				if(vi%2 != 0)
				{
					vi = (vi-1)/2;
					if(valuearr[vi].compareTo(valuearr[2*vi+1]) >= 0)
					break;

					E temp = valuearr[vi];
					valuearr[vi] = valuearr[2*vi+1];
					valuearr[2*vi+1] = temp;

					T temp2 = keyarr[vi];
					keyarr[vi] = keyarr[2*vi+1];
					keyarr[2*vi+1] = temp2;

					if(vi <= 0)
					break;
				}
				else
				{
					vi = (vi-2)/2;
					if(valuearr[vi].compareTo(valuearr[2*vi+2]) >= 0)
					break;

					E temp = valuearr[vi];
					valuearr[vi] = valuearr[2*vi+2];
					valuearr[2*vi+2] = temp;

					T temp2 = keyarr[vi];
					keyarr[vi] = keyarr[2*vi+2];
					keyarr[2*vi+2] = temp2;

					if(vi <= 0)
					break;
				}
			}
			valueIndex++;
			keyIndex++;
		}
	}

	public E extractMax() {
		//write your code here

		E max = this.valuearr[0];
		this.delete(this.keyarr[0]);

		return max;
	}

	public void delete(T key) 
	{
		//write your code here
		int i;
		for(i = 0; i<this.keyarr.length;i++)
		{
			if((this.keyarr[i]).equals(key))
			break;
		}
		// System.out.println("i= "+i+" and index is at "+valueIndex +" and valuearri= "+valuearr[i]+" and keyarri= "+keyarr[i]);

		E temp = valuearr[i];
		valuearr[i] = valuearr[valueIndex-1];
		valuearr[valueIndex-1] = temp;

		T temp2 = keyarr[i];
		keyarr[i] = keyarr[keyIndex-1];
		keyarr[keyIndex-1] = temp2;

		valueIndex--;
		keyIndex--;

		int vi = i;

		while(true)
		{
			int left = 2*vi + 1;
			int right = 2*vi +2;
			if(left>=valueIndex || right>=valueIndex)
			break;

			if(valuearr[vi].compareTo(valuearr[left])>0 && valuearr[i].compareTo(valuearr[right])>0)
			break;
			
			if(valuearr[left].compareTo(valuearr[right])>0)
			{
				E temp3 = valuearr[vi];
				valuearr[vi] = valuearr[left];
				valuearr[left] = temp3;

				T temp4 = keyarr[vi];
				keyarr[vi] = keyarr[left];
				keyarr[left] = temp4;
				vi = left;
			}
			else
			{
				E temp3 = valuearr[vi];
				valuearr[vi] = valuearr[right];
				valuearr[right] = temp3;

				T temp4 = keyarr[vi];
				keyarr[vi] = keyarr[right];
				keyarr[right] = temp4;
				vi = right;
			}
		}
	}

	public void increaseKey(T key, E value) 
	{
		//write your code here
		int i;
		for(i = 0; i<this.keyarr.length;i++)
		{
			if((this.keyarr[i]).equals(key))
			break;
		}

		valuearr[i] = value;
		// System.out.println(i+ "when key is "+key+", valuearri= "+valuearr[i]);

		//int vi = i;
		if(i == 0)
		return;


		if(i%2 == 0)
		{
			if(valuearr[i].compareTo(valuearr[(i-2)/2])>0)
			{
				//go up
				int vi = i;
				while(true)
				{
					if(vi%2 != 0)
					{
						vi = (vi-1)/2;
						if(valuearr[vi].compareTo(valuearr[2*vi+1]) >= 0)
						break;

						E temp = valuearr[vi];
						valuearr[vi] = valuearr[2*vi+1];
						valuearr[2*vi+1] = temp;

						T temp2 = keyarr[vi];
						keyarr[vi] = keyarr[2*vi+1];
						keyarr[2*vi+1] = temp2;

						if(vi <= 0)
						break;
					}
					else
					{
						vi = (vi-2)/2;
						if(valuearr[vi].compareTo(valuearr[2*vi+2]) >= 0)
						break;

						E temp = valuearr[vi];
						valuearr[vi] = valuearr[2*vi+2];
						valuearr[2*vi+2] = temp;

						T temp2 = keyarr[vi];
						keyarr[vi] = keyarr[2*vi+2];
						keyarr[2*vi+2] = temp2;

						if(vi <= 0)
						break;
					}
				}
				return;
			}
		}
		else
		{
			if(valuearr[i].compareTo(valuearr[(i-1)/2])>0)
			{
				//go up
				int vi = i;
				while(true)
				{
					if(vi%2 != 0)
					{
						vi = (vi-1)/2;
						if(valuearr[vi].compareTo(valuearr[2*vi+1]) >= 0)
						break;

						E temp = valuearr[vi];
						valuearr[vi] = valuearr[2*vi+1];
						valuearr[2*vi+1] = temp;

						T temp2 = keyarr[vi];
						keyarr[vi] = keyarr[2*vi+1];
						keyarr[2*vi+1] = temp2;

						if(vi <= 0)
						break;
					}
					else
					{
						vi = (vi-2)/2;
						if(valuearr[vi].compareTo(valuearr[2*vi+2]) >= 0)
						break;

						E temp = valuearr[vi];
						valuearr[vi] = valuearr[2*vi+2];
						valuearr[2*vi+2] = temp;

						T temp2 = keyarr[vi];
						keyarr[vi] = keyarr[2*vi+2];
						keyarr[2*vi+2] = temp2;

						if(vi <= 0)
						break;
					}
				}
				return;
			}

		}
	}

	public void printHeap() 
	{
		//write your code here
		for(int i= 0; i<this.keyIndex; i++)
		{
			System.out.println(this.keyarr[i]+", "+this.valuearr[i]);
		}
	}
	public void TopList(Heap<T,E> voteHeap)
	{
		Stack st = new Stack();
		Heap<Integer, T> winnerHeap = new Heap<Integer, T>();
		int winIndex = 0;
		int n=0;
		while(true)
		{
			// System.out.println("valuearrn= "+voteHeap.valuearr[n]+ " and valuearr0= "+voteHeap.valuearr[0]);
			if((voteHeap.valuearr[n]).compareTo(voteHeap.valuearr[0])==0)
			{	
				// System.out.println("in target if");
				winnerHeap.insert(winIndex, voteHeap.keyarr[n]);
				winIndex++;
			}	
			n++;
			if(n >= voteHeap.keyIndex)
			break;
		}

		while(winnerHeap.keyIndex != 0)
		{
			st.push(winnerHeap.extractMax());
		}

		while(!st.isEmpty())
		System.out.println(st.pop());
	}	
}

