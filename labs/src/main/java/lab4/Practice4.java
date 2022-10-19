package lab4;

import java.io.IOException;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Practice4 {
    public static class City
    {
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population)
        {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        public String getName()
        {
            return name;
        }

        public String getState()
        {
            return state;
        }

        public int getPopulation()
        {
            return population;
        }

        public String toString(){
            return "City{name='"+name+"', state='"+state+"', population="+population+"}";
        }

    }

    public static Stream<City> readCities(String filename) throws IOException
    {
        return Files.lines(Paths.get(filename).toAbsolutePath())
                .map(l -> l.split(", "))
                .map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        Stream<City> cities = readCities("cities.txt");
        // Q1: count how many cities there are for each state
        // TODO: Map<String, Long> cityCountPerState = ...
        Map<String, Long> cityCountPerState = cities.collect(Collectors.groupingBy(City::getState,Collectors.counting()));
        System.out.println("Cities per state: ");
        System.out.println(cityCountPerState);
        System.out.println();


        cities = readCities("cities.txt");
        // Q2: count the total population for each state
        // TODO: Map<String, Integer> statePopulation = ...
        Map<String, Integer> statePopulation = cities.collect(Collectors.groupingBy(City::getState,Collectors.summingInt(City::getPopulation)));
        System.out.println("population per state:");
        System.out.println(statePopulation);
        System.out.println();


        cities = readCities("cities.txt");
        // Q3: for each state, get the set of cities with >500,000 population
        // TODO: Map<String, Set<City>> largeCitiesByState = ...
//        Map<String, Set<City>> largeCitiesByState = cities.filter(city -> city.population > 500000).collect(Collectors.groupingBy(City::getState, Collectors.toSet()));
        Map<String, List<City>> temp = cities.collect(Collectors.groupingBy(City::getState));
        System.out.println("cities with > 500,000 population for each state:");
        for (Map.Entry<String,List<City>> item : temp.entrySet()){
            String state = item.getKey();
            List<City> cityList = item.getValue();
            Set<City> citySet = cityList.stream().filter(city -> city.population>500000).collect(Collectors.toSet());
            System.out.print(state + ": [");
            int count = 0, size = citySet.size();
            for (City city : citySet){
                if (count < size-1){
                    System.out.print(city.toString() + ", ");
                    count++;
                }else
                    System.out.print(city.toString());

            }
            System.out.println("]");

        }
//        for (Map.Entry<String, Set<City>> item : largeCitiesByState.entrySet()){
//            String state = item.getKey();
//            Set<City> citySet = item.getValue();
//            System.out.println(state+": " + citySet);
//        }

    }
}
