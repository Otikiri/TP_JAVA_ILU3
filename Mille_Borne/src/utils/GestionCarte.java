package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCarte {
	
	public static <T> T extraire(List<T> list) {
		if (list.isEmpty()) {
			return null;
		}

		Random intGen = new Random();
		int index = intGen.nextInt(list.size());
		return list.remove(index);
	}

	public static <T> T extraireIte(List<T> list) {
		if (list.isEmpty()) {
			return null;
		}

		boolean found = false;
		int compteur = 0;
		T t = null;
		Random intGen = new Random();
		int index = intGen.nextInt(list.size());
		for (ListIterator<T> ite = list.listIterator(); ite.hasNext() && !found;) {
			t = ite.next();
			if (compteur == index) {
				found = true;
				ite.remove();
			}
			compteur++;
		}
		return t;
	}

	public static <T> List<T> melanger(List<T> list) {
		List<T> l = new ArrayList<>();
		while (!list.isEmpty()) {
			l.add(extraire(list));
		}
		return l;
	}

	public static <T> boolean verifierMelange(List<T> l1, List<T> l2) {
		if (l1.size() != l2.size()) {
			return false;
		}
		for (T t : l1) {
			if (Collections.frequency(l1, t) != Collections.frequency(l2, t)) {
				return false;
			}
		}
		return true;
	}

	public static <T> List<T> rassembler(List<T> list) {
		List<T> listRass = new ArrayList<>();
		List<T> listCopie = new ArrayList<>(list);
		while (!listCopie.isEmpty()) {
			T teteList = listCopie.get(0);
			for (ListIterator<T> ite = listCopie.listIterator(); ite.hasNext();) {
				T currentT = ite.next();
				if (teteList.equals(currentT)) {
					listRass.add(currentT);
					ite.remove();
				}
				
			}
		}
		return listRass;
	}
	private static <T> boolean verifTTelemEq(T elemPred , List<T> l1,int index) {
		for (ListIterator<T> ite = l1.listIterator(index); ite.hasNext();) {
			T current = ite.next();
			if (current.equals(elemPred)) {
				return false;
			}
		}
		return true;
	}
	
	public static <T> boolean verifierRassembler(List<T> l1) {
		if (l1.isEmpty()) {return false; }
		// init de l'iterateur et de la copie de la liste a parcourir 
		
		List<T> copieList = new ArrayList<>(l1);
		ListIterator<T> iteList = copieList.listIterator();
		T elemPredRead = null; 
		while(iteList.hasNext()) {
			T elemCurrentRead = iteList.next();
			if(elemPredRead != null && !elemCurrentRead.equals(elemPredRead)) {
				int indElemApresCurr = iteList.nextIndex();
				if(!verifTTelemEq(elemPredRead, l1, indElemApresCurr)) {
					return false;
				}
			}
			elemPredRead = elemCurrentRead;
		}
		return true;
	}
	
}
