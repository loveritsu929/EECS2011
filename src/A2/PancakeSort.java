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

	
	//private Node prefixLast;
	private DoublyLinkedList<E> list;
	
	Node<E> temp;
	
	//return a pointer to the node with maximum element within the specified prefix of the instance list
	public Node<E> getPrefixMaxNode(Node<E> prefixLast){
		Node<E> iter = (Node<E>) list.getHeader().getNext();
		Node<E> maxNode = iter;
		while(iter != prefixLast){
			if (iter.getNext().getElement().compareTo(maxNode.getElement()) > 0){
				//
				maxNode = iter.getNext();
				//System.out.printf("first:%d, now at iter=%d , findMax: %d\n", list.getHeader().getNext().getElement(),iter.getElement(),maxNode.getElement());
				
			}
			iter = iter.getNext();
		}
		
		return maxNode;
	}
	
	public void reversePrefix(Node prefixLast){
		Node<E> iter = (Node<E>) list.getHeader().getNext();
		int subLength = 1;
		
		while(iter != prefixLast){
			
			subLength++;
			iter = iter.getNext();
		}
		
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
			
			//Last two consecutive nodes.
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
			
			//preN= preN.getNext();
			preN = pnNext;
			sufN= snPre;
			}
			//System.out.printf("list = %s , subLength = %d, i = %d\n", list.toString(),subLength, i);
		}
	}
	
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
			//!!!!!
			Node<E> maxNode = getPrefixMaxNode(lastNode);
			//System.out.printf("max=%d\n", maxNode.getElement());
			
			if(lastNode == maxNode){
				for(int j =0; j<i+2;j++){
					
					temp = temp.getPrev();
					
					
					}
				//System.out.printf("temp=%d\n", temp.getElement());
				lastNode = temp;
			}
			else{
			reversePrefix(maxNode);
			reversePrefix(lastNode);
			
			//lastNode = lastNode.getPrev();
			for(int j =0; j<i+2;j++){
				
				temp = temp.getPrev();
				
				
				}
			//System.out.printf("temp=%d\n", temp.getElement());
			lastNode = temp;
			//maxNode = getPrefixMaxNode(lastNode);
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
		System.out.println("Given string: "+pancake.getList().toString());
		//System.out.println(pancake.getList().getHeader().getNext().getElement());
		//System.out.println(pancake.getPrefixMaxNode(list.getHeader().getNext().getNext().getNext().getNext()
		//		.getNext().getNext().getNext()).getElement());
		//System.out.println(list.size());
		pancake.pancakeSort();
		System.out.println("Sorted string"+pancake.getList().toString());
		
		pancake.setList(buildList(1,2,3,4,5,6,7)) ;
		System.out.println("Given string: "+pancake.getList().toString());
		pancake.pancakeSort();
		System.out.println("Sorted string"+pancake.getList().toString());

		pancake.setList(buildList(-1, 2, 4, 7, 5, 13, 0, 9)) ;
		System.out.println("Given string: "+pancake.getList().toString());
		pancake.pancakeSort();
		System.out.println("Sorted string"+pancake.getList().toString());
		
		PancakeSort<String> pancake2 = new PancakeSort<>();
		pancake2.setList(buildList("ab","tom","str","2011","york","foo")) ;
		System.out.println("Given string: "+pancake2.getList().toString());
		pancake2.pancakeSort();
		System.out.println("Sorted string"+pancake2.getList().toString());
		
		
		
	}
	
}
