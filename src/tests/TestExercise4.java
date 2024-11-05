package tests;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import exercises.Exercise4;
import us.lsi.common.Pair;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.Polynomial;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class TestExercise4 {
	
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
				List.of("ficheros_generados/p1/funcRecBig.txt","ficheros_generados/p1/funcItBig.txt","ficheros_generados/p1/funcRecDouble.txt", "ficheros_generados/p1/funcItDouble.txt"), 
				List.of("Rec Big","It Big","Rec Double", "It Double"));
	}
	
	public static void main(String[] args) {
		genData(t -> Exercise4.funcRecBig(t),"ficheros_generados/p1/funcRecBig.txt");
		genData(t -> Exercise4.funcItBig(t),"ficheros_generados/p1/funcItBig.txt");
		genData(t -> Exercise4.funcRecDouble(t),"ficheros_generados/p1/funcRecDouble.txt");
		genData(t -> Exercise4.funcItDouble(t),"ficheros_generados/p1/funcItDouble.txt");
		
		show(PowerLog.of(List.of(Pair.of(2, 1.77),Pair.of(3, 3.76))), "ficheros_generados/p1/funcRecBig.txt","Rec Big");
		show(PowerLog.of(List.of(Pair.of(2, 1.77),Pair.of(3, 3.76))), "ficheros_generados/p1/funcItBig.txt","It Big");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p1/funcRecDouble.txt","Rec Double");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p1/funcItDouble.txt","It Double");	
		showCombined();
	}
}
