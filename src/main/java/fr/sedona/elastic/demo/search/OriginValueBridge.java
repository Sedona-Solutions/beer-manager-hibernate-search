package fr.sedona.elastic.demo.search;

import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;

/**
 * Value Bridge used to convert ingredients to hasAllergen boolean
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
                return "Europe";
            default:
                return "Monde";
        }
    }
}
