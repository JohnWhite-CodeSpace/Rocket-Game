package game;

import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class BestRoute {
    private GamePanel gp;
    private ArrayList<Integer> bestRoute;
    private double bestDistance;
    /*
     * Konstruktor klasy BestRoute obliczającej najlepszą trasę do pokonania między planetami 
     * przy zastosowaniu algorytmu genetycznego.
     * @param gp - obiekt klasy GamePanel rozszerzający klasę Pane. W tej klasie generowane są wszystkie obiekty gry,
     * w szczególności planety i gracz, wykorzystywane do wyznaczanie najlepszej trasy.
     */
    public BestRoute(GamePanel gp) {
        this.gp = gp;
    }

    /*
     * Metoda inicjalizująca rozwiązywanie problemu komiwojażera.
     */
    public void solveTSP() {
        if (gp.planetList.isEmpty()) return;

        int populationSize = 100; //rozmiar populacji
        int generations =500; //ilość pokoleń, czyli iteracji pętli ewolucyjnej.
        double mutationRate = 0.02; // prawdopodobieństwo zajścia mutacji

        ArrayList<ArrayList<Integer>> population = generateInitialPopulation(populationSize);// Generowanie poczatkowej populacji
        evaluatePopulation(population);

        for (int gen = 0; gen < generations; gen++) {
            population = evolvePopulation(population, mutationRate);
            evaluatePopulation(population);
        }
    }
    /*
     * Metoda inicjalizująca początkową populację będącą listą zbiorów tras(indeksów planet)
     * @param populationSize - wielkość populacji (w solveTSP = 100)
     */
    private ArrayList<ArrayList<Integer>> generateInitialPopulation(int populationSize) {
        ArrayList<ArrayList<Integer>> population = new ArrayList<>(); // lista przechowująca zbiory indeksów planet (wszystkie trasy)

        ArrayList<Integer> baseRoute = new ArrayList<>(); //lista przechowująca startowe indeksy planet. Ogólnie indeksy te same jak w gp.planetList (bazowa trasa).
        for (int i = 0; i < gp.planetList.size(); i++) {
        	baseRoute.add(i);
        }
        // Chcemy tutaj wygenerować populationSize tras
        for (int i = 0; i < populationSize; i++) {
            ArrayList<Integer> route = new ArrayList<>(baseRoute);//tworzymy kopię, żeby trasy były niezależne od innych
            Collections.shuffle(route); //permutacja
            population.add(route);//dodajemy do listy z trasami
        }
        return population;
    }
    /*
     * Metoda do ewaluacji populacji, czyli przeliczenia z populacji najlepszej (najkrótszej trasy)
     * @param population - przyjmowanie listy osobników do ewaluacji
     */
    private void evaluatePopulation(ArrayList<ArrayList<Integer>> population) {
        int counter = 0;
        for (ArrayList<Integer> route : population) {
            double distance = calculateTotalDistance(route); // Obliczamy długość całkowitą trasy
            
            // Sprawdzenie czy jest to pierwsza trasa do ocenienia lub czy obliczona trasa 
            // jest obecnie krótsza od poprzedniej 
            if (bestRoute == null || distance < bestDistance) {
                bestRoute = new ArrayList<>(route);
                bestDistance = distance;
            }
            if (counter%100 == 0) {
                System.out.println("Trasa " + counter + ": Długość = " + distance);
            }
            counter++;
        }
    }
    /*
     * Metoda tworząca nową populację tras bazującą na najlepszuch trasach z poprzedniej populacji ulepszona o mutację i krzyżowanie.
     */
    private ArrayList<ArrayList<Integer>> evolvePopulation(ArrayList<ArrayList<Integer>> population, double mutationRate) {
        ArrayList<ArrayList<Integer>> newPopulation = new ArrayList<>(); //Lista do przechowania nowej populacji
        population.sort((route1, route2) -> {
            double distance1 = calculateTotalDistance(route1);
            double distance2 = calculateTotalDistance(route2);
            if (Double.isNaN(distance1) || Double.isNaN(distance2)) {
                return Double.isNaN(distance1) ? 1 : -1;
            }
            population.forEach(route -> {
                double distance = calculateTotalDistance(route);
                if (Double.isNaN(distance)) {
                    System.out.println("NaN found for route: " + route);
                }
            });
            return Double.compare(distance1, distance2);
        });//Sortujemy poprzednią listę, tak aby najkrótsze trasy były na początku

        int eliteCount = Math.max(1, population.size() / 10); //liczba najlepszych tras (min 1 trasa, max 10% populacji)
        newPopulation.addAll(population.subList(0, eliteCount)); //dodajemy fragment z najlepszymi trasami z poprzedniej populacji do nowej populacji

        Random random = new Random();
        //Pętla tworząca bachory. Działa do osiągnięcia pełnej populacji.
        while (newPopulation.size() < population.size()) {
        	//randomowo wybieramy rodziców zpośród wcześniej wyselekcjonowanej elity 
            ArrayList<Integer> parent1 = population.get(random.nextInt(eliteCount));
            ArrayList<Integer> parent2 = population.get(random.nextInt(eliteCount));
            // robimy bachora poprzez krzyżowanie się starych
            ArrayList<Integer> child = crossover(parent1, parent2);
            //mutacja czyli randomowa zamiana indeksów planet w dziecku
            if (random.nextDouble() < mutationRate) mutate(child);
            newPopulation.add(child); //dodajemy dziecko do populacji
        }
        return newPopulation;
    }
    /*
     * Krzyżowanie rodziców i robienie dziecka
     */
    private ArrayList<Integer> crossover(ArrayList<Integer> parent1, ArrayList<Integer> parent2) {
        Random random = new Random();
        int start = random.nextInt(parent1.size()); //początek indeksów do skopiowania
        int end = random.nextInt(parent1.size() - start) + start; // koniec indeksów do skopiowania

        ArrayList<Integer> child = new ArrayList<>(Collections.nCopies(parent1.size(), -1));
        for (int i = start; i <= end; i++) child.set(i, parent1.get(i)); //ustawiamy indeksy od start do end u dziecka od pierwszego rodzica

        int index = 0;
        //pętla uzupełniająca brakujące indeksy w dziecku o indeksy z drugiego rodzica
        for (int gene : parent2) {
            if (!child.contains(gene)) {
                while (child.get(index) != -1) index++;
                child.set(index, gene);
            }
        }
        return child;
    }
    /*
     * Mutacja - zamieniamy randomowe indeksy w osobniku
     */
    private void mutate(ArrayList<Integer> route) {
        Random random = new Random();
        int index1 = random.nextInt(route.size());
        int index2 = random.nextInt(route.size());
        Collections.swap(route, index1, index2);
    }
    /*
     * Obliczanie pełnej trasy między planetami
     */
    private double calculateTotalDistance(ArrayList<Integer> route) {
        double totalDistance = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            Entity a = gp.planetList.get(route.get(i));
            Entity b = gp.planetList.get(route.get(i + 1));
            totalDistance += calculateDistance(a, b);
        }
        return totalDistance;
    }
    /*
     * Obliczanie odcinka trasy między planetami a i b
     */
    private double calculateDistance(Entity a, Entity b) {
        double dx = a.worldX - b.worldX;
        double dy = a.worldY - b.worldY;
        return Math.sqrt(dx*dx + dy *dy);
    }

    public ArrayList<Integer> getBestRoute() {
        return bestRoute;
    }
    /*
     * rysowanie trasy na pełnej mapie
     */
    public void draw(GraphicsContext gc) {
        if (bestRoute == null || bestRoute.isEmpty()) return;

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);

        for (int i = 0; i < bestRoute.size() - 1; i++) {
            Entity current = gp.planetList.get(bestRoute.get(i));
            Entity next = gp.planetList.get(bestRoute.get(i + 1));
                int currentScreenX = current.worldX - gp.player.worldX + gp.player.screenX;
                int currentScreenY = current.worldY - gp.player.worldY + gp.player.screenY;
                int nextScreenX = next.worldX - gp.player.worldX + gp.player.screenX;
                int nextScreenY = next.worldY - gp.player.worldY + gp.player.screenY;

                gc.strokeLine(currentScreenX, currentScreenY, nextScreenX, nextScreenY);
        }
    }
    //Dodanie zależności od zużycia paliwa i włączenie do kalkulacji pozycji asteroid z pasa między Marsem a Jowiszem
    //Ewentualnie uwzględnienie free roaming komet i asteroiud
}
