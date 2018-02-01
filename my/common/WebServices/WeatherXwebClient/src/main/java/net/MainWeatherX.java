package net;

import net.webservicex.Country;
import net.webservicex.CountrySoap;

public class MainWeatherX {
    public static void main(String[] args) {
        Country country = new Country();
        CountrySoap countrySoap = country.getCountrySoap();
        String countries = countrySoap.getCountries();
        System.out.println("countries: \n" + countries);

    }
}
