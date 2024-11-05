package exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.math3.analysis.function.Floor;

public class Exercise2 {
	public static List<String> ejercicio2RecursivoNoFinal(Integer a, String s){
		List<String> res = new ArrayList<String>();
		if(a <= 3 || s.length() <= 2) {
			res.add(a.toString() + s);
		}
		else {
			res = ejercicio2RecursivoNoFinal(a/2, s.substring(a%s.length()));
			res.add(a.toString() + s);
		}
		return res;
	}
	
	public static List<String> ejercicio2Iterativo(Integer a, String s){
		List<String> ac = new ArrayList<String>();
		while (!(a <= 3 || s.length() <= 2)) {
			ac.add(0, a.toString() + s);
			s = s.substring(a%s.length());
			a /= 2;
		}
		ac.add(0, a.toString() + s);
		return ac;
	}
	
	public static List<String> ejercicio2RecursivoFinal(Integer a, String s){
		return recFinal(a, s, new ArrayList<String>());
	}
	
	public static List<String> recFinal(Integer a, String s, List<String> ac) {
		List<String> r = null;
		if(a <= 3 || s.length() <= 2) {
			ac.add(0, a.toString() + s);
			r = ac;
		}
		else {
			ac.add(0, a.toString() + s);
			r = recFinal(a/2, s.substring(a%s.length()), ac);
		}
		return r;
	}
	
	public static List<String> ejercicio2NotacionFuncional(Integer a, String s){
		Tupla t = Stream.iterate(Tupla.first(a, s),e -> e.next())
				.filter(e -> e.a() <= 3 || e.s().length() <= 2).findFirst().get();
		List<String> res = t.ac;
		res.add(0, t.a.toString() + t.s);
		return res;
	}
	
	private static record Tupla(List<String> ac, Integer a, String s) {
		public static Tupla of(List<String> ba, Integer a, String s) {
			return new Tupla(ba, a, s);
		}
		
		public static Tupla first(Integer a, String s) {
			return of(new ArrayList<String>(), a, s);
		}
		
		public Tupla next() {
			ac.add(0, a.toString() + s);
			return of(ac, a/2, s.substring(a%s.length()));
		}
	}
}
