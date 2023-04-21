package se.salt.jfs.test.currency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 *
 * {
 *   "disclaimer": "Usage subject to terms: https://openexchangerates.org/terms",
 *   "license": "https://openexchangerates.org/license",
 *   "timestamp": 915210000,
 *   "base": "USD",
 *   "rates": {
 *     "EUR": 0.853515,
 *     "GBP": 0.602941,
 *     "SEK": 8.117873
 *   }
 * }
 */
public record RateListing (String disclaimer, String license, long timestamp, String base, Map<String,Double> rates){
}
