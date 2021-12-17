package fr.sedona.elastic.demo.search;

import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;

/**
 * Value Bridge used to convert country to a specific enum value
 */
public class OriginValueBridge implements ValueBridge<String, String> {

    @Override
    public String toIndexedValue(String value, ValueBridgeToIndexedValueContext valueBridgeToIndexedValueContext) {
        switch (value) {
            case "France":
                return "France";
            case "Belgique":
            case "Allemagne":
            case "Espagne":
            case "Danemark":
                return "Europe";
            default:
                return "Monde";
        }
    }
}
