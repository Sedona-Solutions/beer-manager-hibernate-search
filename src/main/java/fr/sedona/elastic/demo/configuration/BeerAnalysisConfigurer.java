package fr.sedona.elastic.demo.configuration;

import org.hibernate.search.backend.elasticsearch.analysis.ElasticsearchAnalysisConfigurationContext;
import org.hibernate.search.backend.elasticsearch.analysis.ElasticsearchAnalysisConfigurer;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 * Configurer for ES search indexes
 */
@Dependent
@Named("beerAnalysisConfigurer")
public class BeerAnalysisConfigurer implements ElasticsearchAnalysisConfigurer {

    @Override
    public void configure(ElasticsearchAnalysisConfigurationContext context) {
        // Define a custom token filter of type edgeNgram
        context.tokenFilter("partialFilter")
                .type("edge_ngram")
                .param("min_gram", 3)
                .param("max_gram", 10);

        // Use it in a custom analyzer
        context.analyzer("custom")
                .custom()
                .tokenizer("whitespace")
                .tokenFilters("asciifolding", "lowercase", "partialFilter");
    }
}
