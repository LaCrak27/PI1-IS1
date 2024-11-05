package tests;

import java.util.List;

import exercises.Exercise4;
import exercises.Exercise5;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import exercises.Exercise5;
import us.lsi.common.Pair;
import us.lsi.common.String2;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class TestExercise5 {
		
	private static Integer nMin = 1000; // n mínimo para el cálculo de potencia
	private static Integer nMax = 10000; // n máximo para el cálculo de potencia
	private static Integer razon = 100; // incremento en los valores de n del cálculo de potencia
	private static Integer nIter = 500; // número de iteraciones para cada medición de tiempo
	private static Integer nIterWarmup = 5000; // número de iteraciones para warmup
	
	public static void genData (Consumer<Integer> algorithm, String file) {
		Function<Integer,Long> f1 = GenData.time(algorithm);
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,razon,nIter,nIterWarmup);

	}
	
	public static void show(Fit pl, String file, String label) {
		List<WeightedObservedPoint> data = DataFile.points(file);
		pl.fit(data);
		MatPlotLib.show(file, pl.getFunction(), String.format("%s = %s",label,pl.getExpression()));
	}
	
	
	         
	public static void showCombined() {
		MatPlotLib.showCombined("Tiempos",
				List.of("ficheros_generados/p1/ej5bi.txt","ficheros_generados/p1/ej5d.txt"),
				List.of("Rec Big","Rec D"));
	}
	
	public static void main(String[] args) {
		genData(t -> Exercise5.ejercicio5BigInteger(t),"ficheros_generados/p1/ej5bi.txt");
		genData(t -> Exercise5.ejercicio5Double(t),"ficheros_generados/p1/ej5d.txt");
		
		show(PowerLog.of(List.of(Pair.of(2, 1.77),Pair.of(3, 3.76))), "ficheros_generados/p1/ej5bi.txt","Rec Big");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p1/ej5d.txt","Rec D");
		showCombined();
	}

}
