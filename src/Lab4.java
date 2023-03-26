/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pl.edu.wsiz.lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.lang.Iterable;
import java.util.stream.Collectors;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
//import static pl.edu.wsiz.lab4.Lab4.wypiszOsoby;

/**
 *
 * @author leszek
 */
public class Lab4 {

    class Add implements Operation{

        @Override
        public double operation(double a, double b) {
            return a+b;
        }
    }
    
    public double calc(double a,double b,Operation op){
        return op.operation(a,b);
    }

    public double calc2(double a,double b,DoubleBinaryOperator op){
        return op.applyAsDouble(a,b);
    }
    
    /**
     * Wypisuje obiekty o zadanych warunkach <br>
     * https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
     * @param lista lista osób
     * @param tester obiekt lub wyrażenie lambda sprawdzające warunki
     */
    public static void wypiszOsoby(
        List<Osoba> lista, SprawdzOsoba tester) {
        for (Osoba o : lista) {
            if (tester.test(o)) {
                o.wypisz();
            }
        }
    }

    public static void wypiszOsobyPredicate(
        List<Osoba> lista, Predicate<Osoba> tester) {
        for (Osoba o : lista) {
            if (tester.test(o)) {
                o.wypisz();
            }
        }
    }

    public static void przetwarzajOsoby(
        List<Osoba> lista, 
        Predicate<Osoba> tester,
        Consumer<Osoba> block) {
            for (Osoba o : lista) {
                if (tester.test(o)) {
                    block.accept(o);
                }
            }
    }

    public static void przetwarzajOsobyzFunkcja(
        List<Osoba> lista, 
        Predicate<Osoba> tester,
        Function<Osoba, String> mapper,
        Consumer<String> block) {
            for (Osoba o : lista) {
                if (tester.test(o)) {
                    String dane=mapper.apply(o);
                    block.accept(dane);
                }
            }
    }

    public static <X, Y> void przetwarzajElementy(
        Iterable<X> lista,
        Predicate<X> tester,
        Function<X, Y> mapper,
        Consumer<Y> block) {
            for (X o : lista) {
                if (tester.test(o)) {
                    Y dane=mapper.apply(o);
                    block.accept(dane);
                }
            }
    }

    public void run(){
        double result1 = calc(3, 4, new Add());
        System.out.println("Wynik 1:" +result1);
        
        Operation op2= (a,b) -> a+b;
        double result2 = calc(3, 4, op2);
        System.out.println("Wynik 2:" +result2);
        
        double result3 = calc(3, 4, (a,b)->a+b);
        System.out.println("Wynik 3:" +result3);

        double result4 = calc(3, 4, new Operation(){
            @Override
            public double operation(double a, double b){
                return a+b;
            }
        });
        System.out.println("Wynik 4:" +result4);
        
        double result5 = calc2(3, 4, (a,b)->a+b);
        System.out.println("Wynik 5:" +result5);
        
        ArrayList<Osoba> lista_osob=new ArrayList<>();
        lista_osob.add(new Osoba("Jan","Kowalski", 1950, Osoba.Plec.M));
        lista_osob.add(new Osoba("Janina","Kowalska", 1951, Osoba.Plec.K));
        lista_osob.add(new Osoba("Anna","Nowak", 2000, Osoba.Plec.K));
        lista_osob.add(new Osoba("Zofia","Kowalska", 2004, Osoba.Plec.K));
        
        System.out.println();
        wypiszOsoby(lista_osob, (Osoba o) -> o.getPlec() == Osoba.Plec.K
            && o.wiek() >= 18
            && o.wiek() <= 25);
        System.out.println();
        /*
        interface Predicate<T> {
            boolean test(T t);
        }
        interface Predicate<Osoba> {
            boolean test(Osoba o);
        }        
        */
        
        wypiszOsobyPredicate(lista_osob, (Osoba o) -> o.getPlec() == Osoba.Plec.K
            && o.wiek() >= 18
            && o.wiek() <= 25);
        System.out.println();
        przetwarzajOsoby(lista_osob, o -> o.getPlec() == Osoba.Plec.K
            && o.wiek() >= 18
            && o.wiek() <= 25,
        o -> o.wypisz());
        System.out.println();
        
        przetwarzajOsobyzFunkcja(lista_osob, o -> o.getPlec() == Osoba.Plec.K
            && o.wiek() >= 18
            && o.wiek() <= 25,
        o -> o.getNazwisko(),        
        nazw -> System.out.println(nazw));
        System.out.println();

	przetwarzajElementy(lista_osob, o -> o.getPlec() == Osoba.Plec.K
            && o.wiek() >= 18
            && o.wiek() <= 25,
        o -> o.getNazwisko(),
        nazw -> System.out.println(nazw));
        System.out.println();

	lista_osob
	    .stream()
            .filter(
	        o -> o.getPlec() == Osoba.Plec.K
                    && o.wiek() >= 18
                    && o.wiek() <= 25)
            .map(o -> o.getNazwisko())
            .forEach(nazw -> System.out.println(nazw));
        System.out.println();


	ArrayList<Integer> lista1=new ArrayList<>(Arrays.asList(1, 7, 5, 2, 150, 10, 100));
	List<Integer> wynik_lista=lista1.stream()
		.filter( el -> el >=7)
		.collect(Collectors.toList());
	for(int el:wynik_lista)
        {
            System.out.print(el + ", ");
        }
        System.out.println();        
        lista1.stream()
		.filter( el -> el >=7)
                .sorted((a,b)-> - a.compareTo(b))
                .forEach(el -> System.out.print(el + ", "));
        System.out.println();
        
        lista_osob
	    .stream()
            .filter(
	        o -> o.getNazwisko().startsWith("K"))
            .map(o -> o.getImie()+" "+o.getNazwisko())
            .forEach(System.out::println);
        System.out.println();
    }

    public static void main(String[] args) {
        Lab4 app=new Lab4();
        app.run();
    }
}
//https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
//https://www.baeldung.com/java-8-lambda-expressions-tips
//https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html
//http://mw.home.amu.edu.pl/zajecia/PRA2020/PRA03ENG.html
