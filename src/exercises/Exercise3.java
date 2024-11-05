package exercises;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Exercise3 {
	public static Set<Integer> ejercicio3RecursivoSinMemoria(Integer a, Integer b, Integer c) {
		Set<Integer> resSet = new HashSet<Integer>();
		if(a <= 5 || b <= 3 || c <= 2) {
			resSet.add(2*a);
			resSet.add(b+3);
			resSet.add(c);
		}
		else {
			resSet = ejercicio3RecursivoSinMemoria(a - 5, b/4, c-2);
			resSet.addAll(ejercicio3RecursivoSinMemoria(a/3, b-3, c/2));
		}
		return resSet;
	}
	
	public static Set<Integer> ejercicio3RecursivoConMemoria(Integer a, Integer b, Integer c) {
		return ejRecMem(Tupla.of(a, b, c));
	}
	
	// Map to store function results
	static Map<Tupla, Set<Integer>> memoryMap = new HashMap<Exercise3.Tupla, Set<Integer>>();
	public static Set<Integer> ejRecMem(Tupla params) {
		if(memoryMap.containsKey(params)) {
			return memoryMap.get(params);
		}
		Set<Integer> resSet = new HashSet<Integer>();
		if(params.a <= 5 || params.b <= 3 || params.c <= 2) {
			resSet.add(2*params.a);
			resSet.add(params.b+3);
			resSet.add(params.c);
		}
		else {
			resSet = ejercicio3RecursivoConMemoria(params.a - 5, params.b/4, params.c-2);
			resSet.addAll(ejercicio3RecursivoConMemoria(params.a/3, params.b-3, params.c/2));
		}
		memoryMap.put(params, resSet);
		return resSet;
	}
	
	public static Set<Integer> ejercicio3Iterativo(Integer a, Integer b, Integer c) {
		Map<Tupla, Set<Integer>> map = new HashMap<Exercise3.Tupla, Set<Integer>>();
		
		for(int i = 0; i <= a; i++) {
			for(int j = 0; j <= b; j++) {
				for(int k = 0; k <= c; k++) {
					Set<Integer> v = new HashSet<Integer>();
					if(i <= 5 || j <= 3 || k <= 2) {
						v.add(2*i);
						v.add(j+3);
						v.add(k);
					}
					else {
						v.addAll(map.get(Tupla.of(i-5, j/4, k-2)));
						v.addAll(map.get(Tupla.of(i/3, j-3, k/2)));
					}
					map.put(Tupla.of(i, j, k), v);
				}
			}
		}
		return map.get(Tupla.of(a, b, c));
	}
	
	public static record Tupla(Integer a, Integer b, Integer c) {
		public static Tupla of(Integer a, Integer b, Integer c) {
			return new Tupla(a, b, c);
		}
	}

}
