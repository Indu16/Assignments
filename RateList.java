package se.salt.jfs.test.currency;

import se.salt.jfs.test.currency.dto.RateListing;
import se.salt.jfs.test.currency.util.RatesListingReader;

import java.lang.reflect.Array;
import java.util.*;


public class RateList   {

  RatesListingReader ratesListingReader=  new RatesListingReader();
  List<String> dateRecordsAvailable = ratesListingReader.getRatesFiles();

  public Double getRateAtDate(String symbol, String date) throws IllegalArgumentException {

    if (dateRecordsAvailable.contains(date))
    {
      RateListing listing = ratesListingReader.readRatesAtDate(date);
      if (listing.rates().containsKey(symbol))
        return listing.rates().get(symbol);
    }
    throw new IllegalArgumentException("Symbol '" + symbol + "' not found");
  }

  public Double getRateDifference(String symbol, String startDate, String endDate) {
    return this.getRateAtDate(symbol,endDate) - this.getRateAtDate(symbol,startDate);
    // while getting rate using getRateAtDate, it already throws exception if symbol is not found.
  }

  public String[] getRatesForYear(String symbol, String year) throws IllegalArgumentException  {

    List<String> ratesInYear =new ArrayList<>();
    dateRecordsAvailable.stream()
            .filter(newDate->newDate.contains(year))
            .forEach(newDate->
                    {
                      RateListing listing = ratesListingReader.readRatesAtDate(newDate);
                      if (listing.rates().containsKey(symbol)) {
                        double rate = this.getRateAtDate(symbol,newDate);
                        ratesInYear.add("Date: " + newDate + " Rate: " + rate);
                      }
                    }
            );
    if(ratesInYear.isEmpty()){
      throw new IllegalArgumentException("No rates for '" +symbol+ "' found in year '"+year+"'");
    }
    List<String> resultString=ratesInYear.stream()
            .sorted(Comparator.comparing(i->i.substring(6,16)))
            .toList();
    return resultString.toArray(new String[0]);

  }

  public Double highestRateEver(String symbol) throws IllegalArgumentException {
    List<Double> allRatesOfSymbol = new ArrayList<>();
    dateRecordsAvailable.forEach(newDate->
            {
              RateListing listing = ratesListingReader.readRatesAtDate(newDate);
              if (listing.rates().containsKey(symbol)) {
                double rate = this.getRateAtDate(symbol, newDate);
                allRatesOfSymbol.add(rate);
              }
            });
    if(allRatesOfSymbol.isEmpty()){
      throw new IllegalArgumentException("Symbol '" +symbol+ "' not found");
    }
    List<Double> sortedList = allRatesOfSymbol.stream()
            .sorted()
            .toList();

    return sortedList.get(sortedList.size() - 1);
  }
}
