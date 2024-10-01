package pilas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Pilas {

	public static void main(String[] args) {
		Stack<Integer> holaEs = new Stack<Integer>();
		List<Integer> listaIntegers = new LinkedList<Integer>();
		
		listaIntegers.add(11);
		listaIntegers.add(224);
		listaIntegers.add(131);
		
		
		holaEs.push(12231);
		holaEs.push(24432);
		holaEs.push(31);
		holaEs.push(1231);
		
		System.out.println(listaIntegers.get(1));
		
		System.out.println(holaEs.pop());
		System.out.println(holaEs.pop());
		System.out.println(holaEs.pop());
		System.out.println(holaEs.pop());
		
	}

}
