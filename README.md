# Currency rates finder

In this exercise you will build a couple of methods (in other words an API) that lets the user find, list and compare currencies over time.

In order to solve this problem you will have to:

- Implement the methods in the class `RateList`
- create a suitable object structure, that helps you solve the problem
  - it is in other words a good idea to read the entire instruction before you start...
  - but do build your models iteratively
- iterate, map, filter and loop through the collections using the Stream API, to solve the requirements

## Specification

- Implement a `getRateAtDate` method, that

  - Takes a `symbol`-string and a `date`-string
    - You can assume the `date`-string to be in the `YYYY-MM-DD` format
  - Return the currency rate for that symbol at the date
    - The rate should be of type `double`
  - Throws an `IllegalArgumentException` with the message `Symbol 'IDR' not found`, when a symbol (where `IDR` is an example) is not found among the rates for the given date

- Implement a `getRateDifference` method that

  - Takes a `symbol`-string, a `startDate`-string and an `endDate`-string
  - returns the difference in rate between the rate at `startDate`, and the rate at `endDate`
    - subtract the `endDate - startDate`
    - only subtract the values - no fancy math needed
    - the difference should be returned as a `double`
  - Throws an `IllegalArgumentException` with the message `Symbol 'FAKE' not found`, when a symbol (where `FAKE` is an example) is not found among the rates for the given date

- Implement a `getRatesForYear` method that

  - Takes a `symbol`-string and a `year`-string
    - The `year`-string is in the `YYYY`-format
  - Returns an array of the rates (as a `String[]`) for that year
    - Each row in the array should have the format `"Date: <date> Rate: <rate>`, where `date` is a string (YYYY-MM-DD) and rate is a `double`
    - The timestamp stored in the rateListing is measured in **seconds**.
    - Sort the array in ascending order per date, so that the earliest date comes first and the latest date last in the list
    - For example `getRatesForYear("GBP", "1999")` should return

      ```java
      [
        "Date: 1999-01-01 Rate: 0.602941",
        "Date: 1999-06-01 Rate: 0.621195",
        "Date: 1999-11-01 Rate: 0.60824"
      ]
      ```

  - Throw an `IllegalArgumentException` with the message `"No rates for 'APA' found in year '1999'"`, when a symbol  (`APA` and `1999` are examples) is not found in any of the dates for the supplied year.
    - That is; if none of the files for a year contains the symbol then throw the error.
    - If there are no files for that year, throw the exception.
    - If one file doesn't contain the symbol, return the result for the other two

- Implement a `HighestRateEver` method that:
  - Takes a `symbol`-string
  - returns the highest rate on record for that symbol
  - Throws an `IllegalArgumentException` with the message `Symbol 'BANAN' not found`, when a symbol (where `BANAN` is an example) is not found among the rates

## Some help

### Reading the rate data files

The data is located in files in the `resources/data` directory, and we have supplied you with two helper methods to read that data:

1. `se.salt.jfs.test.currency.util.RatesListingReader.getRatesFiles` that returns a `List<String>` of all the dates that has a data file in the `data`-directory 
2. `se.salt.jfs.test.currency.util.RatesListingReader.readRatesAtDate` that returns the `RateListing` for a date.

The `se.salt.jfs.test.currency.util.RatesListingReader` is a utility class and you can call the methods like this:

```java
List<String> files = new RatesListingReader().getRatesFiles();
RateListing listing = new RatesListingReader().readRatesAtDate("1999-10-01");
```

We have created two tests that shows how these methods can be used.

### Tackling the problem

The problem has two parts:

1. Read the data from the files into a suitable structure to answer the questions.
2. Implement the methods according to the specifications using the Stream API

## Get started

Clone this repository:

```bash
git clone git@github.com:saltams/winter23-jfs-test-currencyRates.git
cd winter23-jfs-test-currencyRates
```

Then to run the tests

```bash
mvn clean test
```

This will run the first few tests that will help you get started, with the first few requirements. 
You should also consider adding more test cases, than the examples we have added.

## Evaluation

Evaluation will be done by:

- running our own test suite (not supplied, that validates the specification above with more cases) against your code.
- looking through the code and making sure that it is easy to understand and well written
- ensuring that you have used the Stream API to your advantage

## Handing in the solution

Upload the `src/main/java` directory and its contents into a folder called `currencyRates` in your Google Drive weekend tests folder. 


# FAQ

Should we also submit our test or resource files?

> Nope. only your java files.

Should we catch the errors?

> No... but you need to throw some, according to the specification.

Will I need to write my own tests?

> Yes. We have given you a fair few, but always think about adding more.

The files are wrong, I want it to be structured different (or correct the exchange rates)

> You should not change the data files... We will use the same data files for correcting the tests. You change the data - our tests will fail.

Can I add my own dependencies?

> No. You have all the dependencies you need. We will only import your java files, not your pom.xml.
