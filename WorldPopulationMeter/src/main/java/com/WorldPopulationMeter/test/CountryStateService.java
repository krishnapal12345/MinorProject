package com.WorldPopulationMeter.test;


import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CountryStateService {

   private static final Map<String,List<String>> countryStateMap=new HashMap<>();
   
   static {
	    countryStateMap.put("India", Arrays.asList("Karnataka", "Maharashtra", "Gujarat", "Tamil Nadu", "Rajasthan", "Punjab", "Bihar", "West Bengal", "Odisha", "Haryana"));
	    countryStateMap.put("United States", Arrays.asList("California", "Texas", "Florida", "New York", "Illinois", "Pennsylvania", "Ohio", "Georgia", "North Carolina", "Michigan"));
	    countryStateMap.put("Australia", Arrays.asList("New South Wales", "Victoria", "Queensland", "Western Australia", "South Australia", "Tasmania", "Northern Territory"));
	    countryStateMap.put("Canada", Arrays.asList("Ontario", "Quebec", "British Columbia", "Alberta", "Manitoba", "Saskatchewan", "Nova Scotia", "New Brunswick", "Prince Edward Island", "Newfoundland and Labrador"));
	    countryStateMap.put("United Kingdom", Arrays.asList("England", "Scotland", "Wales", "Northern Ireland", "London", "Manchester", "Birmingham", "Glasgow", "Edinburgh", "Cardiff"));
	    countryStateMap.put("Germany", Arrays.asList("Bavaria", "Berlin", "Brandenburg", "Hesse", "Lower Saxony", "North Rhine-Westphalia", "Rhineland-Palatinate", "Saxony", "Saxony-Anhalt", "Thuringia"));
	    countryStateMap.put("France", Arrays.asList("Île-de-France", "Provence-Alpes-Côte d'Azur", "Auvergne-Rhône-Alpes", "Nouvelle-Aquitaine", "Occitanie", "Hauts-de-France", "Grand Est", "Brittany", "Normandy", "Corsica"));
	    countryStateMap.put("Italy", Arrays.asList("Lombardy", "Lazio", "Campania", "Sicily", "Veneto", "Emilia-Romagna", "Piedmont", "Tuscany", "Calabria", "Sardinia"));
	    countryStateMap.put("Brazil", Arrays.asList("São Paulo", "Rio de Janeiro", "Minas Gerais", "Bahia", "Paraná", "Pernambuco", "Santa Catarina", "Ceará", "Amazonas", "Paraíba"));
	    countryStateMap.put("China", Arrays.asList("Beijing", "Shanghai", "Guangdong", "Sichuan", "Zhejiang", "Jiangsu", "Hunan", "Anhui", "Shandong", "Henan"));
	    countryStateMap.put("Russia", Arrays.asList("Moscow", "Saint Petersburg", "Krasnodar", "Sverdlovsk", "Bashkortostan", "Tatarstan", "Primorsky", "Novosibirsk", "Krasnoyarsk", "Voronezh"));
	    countryStateMap.put("Japan", Arrays.asList("Tokyo", "Osaka", "Kyoto", "Fukuoka", "Hokkaido", "Okinawa", "Hiroshima", "Aichi", "Kanagawa", "Chiba"));
	    countryStateMap.put("South Korea", Arrays.asList("Seoul", "Busan", "Incheon", "Daegu", "Daejeon", "Gwangju", "Suwon", "Ulsan", "Changwon", "Jeonju"));
	    countryStateMap.put("Mexico", Arrays.asList("Mexico City", "Jalisco", "Nuevo León", "Puebla", "Yucatán", "Chiapas", "Guerrero", "Oaxaca", "Veracruz", "Baja California"));
	    countryStateMap.put("Argentina", Arrays.asList("Buenos Aires", "Córdoba", "Santa Fe", "Mendoza", "Salta", "Tucumán", "San Juan", "Entre Ríos", "Chaco", "Misiones"));
	    countryStateMap.put("South Africa", Arrays.asList("Gauteng", "KwaZulu-Natal", "Western Cape", "Eastern Cape", "Free State", "Limpopo", "Mpumalanga", "North West", "Northern Cape"));
	    countryStateMap.put("Saudi Arabia", Arrays.asList("Riyadh", "Jeddah", "Mecca", "Medina", "Dammam", "Al Khobar", "Tabuk", "Taif", "Abha", "Najran"));
	    countryStateMap.put("Turkey", Arrays.asList("Istanbul", "Ankara", "Izmir", "Antalya", "Bursa", "Konya", "Adana", "Gaziantep", "Mersin", "Kayseri"));
	    countryStateMap.put("Egypt", Arrays.asList("Cairo", "Alexandria", "Giza", "Shubra El-Kheima", "Port Said", "Suez", "Mansoura", "Tanta", "Asyut", "Ismailia"));
	    countryStateMap.put("Indonesia", Arrays.asList("Jakarta", "Surabaya", "Bandung", "Medan", "Semarang", "Makassar", "Palembang", "Tangerang", "Depok", "Bekasi"));
	    countryStateMap.put("Nigeria", Arrays.asList("Lagos", "Abuja", "Kano", "Ibadan", "Port Harcourt", "Benin City", "Kaduna", "Maiduguri", "Zaria", "Enugu"));
	    countryStateMap.put("Spain", Arrays.asList("Madrid", "Barcelona", "Valencia", "Seville", "Bilbao", "Zaragoza", "Malaga", "Murcia", "Palma", "Las Palmas"));
	    countryStateMap.put("Sweden", Arrays.asList("Stockholm", "Gothenburg", "Malmö", "Uppsala", "Västerås", "Örebro", "Linköping", "Helsingborg", "Jönköping", "Norrköping"));
	    countryStateMap.put("Poland", Arrays.asList("Warsaw", "Kraków", "Łódź", "Wrocław", "Poznań", "Gdańsk", "Szczecin", "Bydgoszcz", "Lublin", "Katowice"));
	    countryStateMap.put("Netherlands", Arrays.asList("Amsterdam", "Rotterdam", "The Hague", "Utrecht", "Eindhoven", "Tilburg", "Groningen", "Almere", "Breda", "Nijmegen"));
	    countryStateMap.put("Philippines", Arrays.asList("Manila", "Quezon City", "Davao City", "Cebu City", "Zamboanga", "Antipolo", "Pasig", "Taguig", "Cagayan de Oro", "Iloilo City"));
	    countryStateMap.put("Vietnam", Arrays.asList("Ho Chi Minh City", "Hanoi", "Da Nang", "Haiphong", "Can Tho", "Bien Hoa", "Hue", "Nha Trang", "Buon Ma Thuot", "Phan Thiet"));
	    countryStateMap.put("Thailand", Arrays.asList("Bangkok", "Chiang Mai", "Phuket", "Pattaya", "Khon Kaen", "Udon Thani", "Surat Thani", "Hat Yai", "Ubon Ratchathani", "Nakhon Ratchasima"));
	    countryStateMap.put("Malaysia", Arrays.asList("Kuala Lumpur", "Penang", "Johor Bahru", "Kuching", "Kota Kinabalu", "Ipoh", "Shah Alam", "Petaling Jaya", "Seremban", "Melaka"));
	    countryStateMap.put("Bangladesh", Arrays.asList("Dhaka", "Chittagong", "Khulna", "Rajshahi", "Sylhet", "Barisal", "Rangpur", "Comilla", "Narayanganj", "Mymensingh"));
	    countryStateMap.put("Kenya", Arrays.asList("Nairobi", "Mombasa", "Kisumu", "Nakuru", "Eldoret", "Thika", "Malindi", "Machakos", "Nyeri", "Kitale"));
	    countryStateMap.put("Colombia", Arrays.asList("Bogotá", "Medellín", "Cali", "Barranquilla", "Cartagena", "Cúcuta", "Soacha", "Ibagué", "Soledad", "Bucaramanga"));
	    countryStateMap.put("Chile", Arrays.asList("Santiago", "Valparaíso", "Concepción", "La Serena", "Antofagasta", "Temuco", "Rancagua", "Talca", "Chillán", "Arica"));
	    countryStateMap.put("Ukraine", Arrays.asList("Kyiv", "Kharkiv", "Odesa", "Dnipro", "Donetsk", "Zaporizhzhia", "Lviv", "Kryvyi Rih", "Mykolaiv", "Vinnytsia"));
	    countryStateMap.put("Iran", Arrays.asList("Tehran", "Mashhad", "Isfahan", "Shiraz", "Tabriz", "Qom", "Karaj", "Ahvaz", "Kermanshah", "Urmia"));
	    countryStateMap.put("Greece", Arrays.asList("Athens", "Thessaloniki", "Patras", "Heraklion", "Larissa", "Volos", "Ioannina", "Trikala", "Chania", "Rhodes"));
	    countryStateMap.put("Peru", Arrays.asList("Lima", "Arequipa", "Trujillo", "Chiclayo", "Iquitos", "Piura", "Cusco", "Tacna", "Huancayo", "Pucallpa"));
	    countryStateMap.put("Morocco", Arrays.asList("Casablanca", "Rabat", "Fez", "Marrakesh", "Tangier", "Agadir", "Oujda", "Kenitra", "Tetouan", "Safi"));
	    countryStateMap.put("Venezuela", Arrays.asList("Caracas", "Maracaibo", "Valencia", "Barquisimeto", "Ciudad Guayana", "Barcelona", "Maturín", "Puerto La Cruz", "Maracay", "Mérida"));
	    countryStateMap.put("Portugal", Arrays.asList("Lisbon", "Porto", "Amadora", "Braga", "Coimbra", "Aveiro", "Funchal", "Faro", "Setúbal", "Évora"));
	}

   
   public List<RandomDataDto> generateRandomDataList(int count) {
	    Random random = new Random();
	    List<RandomDataDto> dataList = new ArrayList<>();

	    for (int i = 0; i < count; i++) {
	        List<String> countries = new ArrayList<>(countryStateMap.keySet());
	        String randomCountry = countries.get(random.nextInt(countries.size()));

	        List<String> states = countryStateMap.get(randomCountry);
	        String randomState = states.get(random.nextInt(states.size()));

	        int blockNumber = random.nextInt(500) + 1;
	        int totalPopulation = random.nextInt(10000) + 100;
	        int malePopulation = (int) (totalPopulation * (random.nextDouble() * 0.5 + 0.25));
	        int femalePopulation = totalPopulation - malePopulation;
	        int totalEducated = (int) (totalPopulation * (random.nextDouble() * 0.5 + 0.4));
	        int maleEducated = (int) (totalEducated * (random.nextDouble() * 0.5 + 0.25));
	        int femaleEducated = totalEducated - maleEducated;
	        int avgAge = random.nextInt(20) + 20;

	        RandomDataDto randomData = new RandomDataDto(randomCountry, randomState, blockNumber,
	                totalPopulation, malePopulation, femalePopulation,
	                totalEducated, maleEducated, femaleEducated, avgAge);

	        
	        dataList.add(randomData);
	    }

	   
	    System.out.println("Generated Data List: " + dataList);

	    return dataList;
	}


}
