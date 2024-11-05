package exercises;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import us.lsi.geometria.Cuadrante;
import us.lsi.geometria.Punto2D;

public class Exercise1 {
	public static Map<Cuadrante, String> ejercicio1NotacionFuncional(List<Punto2D> ls) {
        UnaryOperator<Punto2D> nx = punto -> {
            double nuevaX = punto.x() + 3;
            return Punto2D.of(nuevaX, punto.y());
        };

        return ls.stream()
                .filter(p -> p.x() % 5 != 0)  
                .map(nx)  
                .collect(Collectors.groupingBy(
                        Punto2D::cuadrante,
                        Collectors.reducing(
                                "",
                                p -> p.x() % 2 == 0 ? p.x() + "¡" : p.x() + "!",
                                (a, b) -> a.isEmpty() ? b : a + "-" + b
                        )
                ));
}
	
	public static Map<Cuadrante, String> ejercicio1Iterativo(List<Punto2D> ls) {
		Map<Cuadrante, String> ac = new HashMap<Cuadrante, String>();
		for(int e = 0; e < ls.size(); e++) {
			Punto2D pt = ls.get(e);
			if(pt.x() % 5 == 0) {
				continue;
			}
			String valueString = "";
			pt = Punto2D.of(pt.x() + 3, pt.y());
			if(pt.x() % 2 == 0) {
				valueString = pt.x() + "¡";
			}
			else {
				valueString = pt.x() + "!";
			}
			if(ac.containsKey(pt.cuadrante())) {
				ac.put(pt.cuadrante(), ac.get(pt.cuadrante()) + "-" + valueString);
			}
			else {
				ac.put(pt.cuadrante(), valueString);
			}
		}
		return ac;
	}
	
	public static Map<Cuadrante, String> ejercicio1Recursivo(List<Punto2D> ls) {
		return recFinal(0, new HashMap<Cuadrante, String>(), ls);
	}
	
	private static Map<Cuadrante, String> recFinal(Integer e, Map<Cuadrante, String> ac, List<Punto2D> ls) {
		Map<Cuadrante, String> resMap = ac;
		if(e < ls.size()) {
			Punto2D pt = ls.get(e);
			if(pt.x() % 5 != 0) {
				String valueString = "";
				pt = Punto2D.of(pt.x() + 3, pt.y());
				if(pt.x() % 2 == 0) {
					valueString = pt.x() + "¡";
				}
				else {
					valueString = pt.x() + "!";
				}
				if(resMap.containsKey(pt.cuadrante())) {
					resMap.put(pt.cuadrante(), resMap.get(pt.cuadrante()) + "-" + valueString);
				}
				else {
					resMap.put(pt.cuadrante(), valueString);
				}
			}
			resMap = recFinal(e+1, resMap, ls);
		}
		return resMap;
	}

}

