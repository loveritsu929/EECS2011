/* *********************************************************
 * EECS2011A: Fundamentals of Data Structures,  Fall 2017
 * Assignment 2, Problem 1
 * 
 * Student Full Name:     Chenxing Zheng
 * Student eecs account:  cxing95
 * Student ID number:     214634901 
 **********************************************************/

package A2;

import A2.DoublyLinkedList.Node;

public class PancakeSort<E extends Comparable<E>> {

	private DoublyLinkedList<E> list;
	
	Node<E> temp;
	
	/**return a pointer to the node with maximum element within the specified prefix of the instance list*/
	public Node<E> getPrefixMaxNode(Node<E> prefixLast){
		Node<E> iter = (Node<E>) list.getHeader().getNext(); // the first node
		Node<E> maxNode = iter;
		while(iter != prefixLast){
			if (iter.getNext().getElement().compareTo(maxNode.getElement()) > 0){
				
				// if find a larger element, update
				maxNode = iter.getNext();
			}
			iter = iter.getNext();
		}
		
		return maxNode;
	}
	
	/**reverse the sequence of nodes of the specified prefix in the instance list */
	public void reversePrefix(Node prefixLast){
		Node<E> iter = (Node<E>) list.getHeader().getNext(); // the first node
		int subLength = 1; // length of the sublist to be reversed
		
		while(iter != prefixLast){ // count the length of the sublist
			
			subLength++;
			iter = iter.getNext();
		}
		
		// every step we exchange the first and the last node in the remaining list 
		Node<E> preN = (Node<E>) list.getHeader().getNext(); 
		Node<E> sufN = prefixLast;
		//System.out.println( subLength);
		
		for(int i =0; i < subLength / 2; i++){
				
			Node<E> pnPre = preN.getPrev();
			Node<E> pnNext = preN.getNext();
			Node<E> snPre = sufN.getPrev();
			Node<E> snNext = sufN.getNext();
			//System.out.printf("\t preN=%d sufN=%d ", (int)preN.getElement(), (int)sufN.getElement());
			//System.out.printf("\n snNext=%d ",(int)snNext.getElement());
			//System.out.print( sufN.getElement()+"\n");
			
			// Last two consecutive nodes.
			// pnNext == snPrev
			// only 4 nodes involved, handled separately
			if(subLength %2 ==0 && i == subLength/2 - 1) {
				preN.setPrev(sufN);
				preN.setNext(snNext);
				//snPre.setNext(preN);
				snNext.setPrev(preN);
				
				sufN.setPrev(pnPre);
				sufN.setNext(preN);
				pnPre.setNext(sufN);
				//pnNext.setPrev(sufN);
			
			}
			else{
			
			preN.setPrev(snPre);
			preN.setNext(snNext);
			snPre.setNext(preN);
			snNext.setPrev(preN);
			
			sufN.setPrev(pnPre);
			sufN.setNext(pnNext);
			pnPre.setNext(sufN);
			pnNext.setPrev(sufN);
		    
			preN = pnNext;
			sufN= snPre;
			}
			//System.out.printf("list = %s , subLength = %d, i = %d\n", list.toString(),subLength, i);
		}
	}
	
	/**return a doubly linked list consisting of the elements in the given order */
	public static <E extends Comparable<E>> DoublyLinkedList<E> buildList(E... elements){
		
		DoublyLinkedList<E> newList = new DoublyLinkedList<E>();
		for(E e: elements){
			newList.addLast(e);
		}
		return newList;
	}
	
	public void pancakeSort(){
		Node<E> header = list.getHeader();
		Node<E> lastNode = list.getTrailer().getPrev();
		
		
		
		for(int i = 0; i < list.size(); i++){
			temp = list.getTrailer();
			
			Node<E> maxNode = getPrefixMaxNode(lastNode);
			
			if(lastNode == maxNode){ // if the maxNode is already located at the right position
				
				//update lastNode, i+2 before the trailer
				for(int j =0; j<i+2;j++){				
					temp = temp.getPrev();				
					}
	
				lastNode = temp;
			}
			else{
			reversePrefix(maxNode); //bring the maxNode to the front
			reversePrefix(lastNode); // send the maxNode to the end of the unsorted sublist
			
			for(int j =0; j<i+2;j++){
				temp = temp.getPrev();
				}

			lastNode = temp;
			
			}
		}
	}
	
	public void setList(DoublyLinkedList<E> list){
		this.list = list;
	}
	
	public DoublyLinkedList<E> getList(){
		return (DoublyLinkedList<E>) this.list;
		
	}
	
	public static void main(String[] args) {
		//Test
		PancakeSort<Integer> pancake = new PancakeSort<>();
		pancake.setList(buildList(9, 5, 4, 3, 6, 8, 10,1)) ;
		System.out.println("Given list: "+pancake.getList().toString());
		pancake.pancakeSort();
		System.out.println("Sorted list"+pancake.getList().toString());
		System.out.println();
		
		pancake.setList(buildList(1,2,3,4,5,6,7)) ;
		System.out.println("Given list: "+pancake.getList().toString());
		pancake.pancakeSort();
		System.out.println("Sorted list"+pancake.getList().toString());
		System.out.println();
		
		pancake.setList(buildList(-1, 2, 4, 7, 5, 13, 0, 9)) ;
		System.out.println("Given list: "+pancake.getList().toString());
		pancake.pancakeSort();
		System.out.println("Sorted list"+pancake.getList().toString());
		System.out.println();
		
		PancakeSort<String> pancake2 = new PancakeSort<>();
		pancake2.setList(buildList("ab","tom","str","2011","york","foo")) ;
		System.out.println("Given list: "+pancake2.getList().toString());
		pancake2.pancakeSort();
		System.out.println("Sorted list"+pancake2.getList().toString());
		
		
		
	}
	
}
