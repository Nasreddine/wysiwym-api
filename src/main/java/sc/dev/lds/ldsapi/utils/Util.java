package sc.dev.lds.ldsapi.utils;

import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.shared.impl.PrefixMappingImpl;
import sc.research.ldq.LdDataset;
import sc.research.ldq.LdDatasetFactory;

import static org.junit.Assert.fail;

public class Util {

    public static LdDataset getDBpediaDataset() {

        PrefixMapping prefixes = new PrefixMappingImpl();

        prefixes.setNsPrefix("dbpedia", "http://dbpedia.org/resource/");
        prefixes.setNsPrefix("dbpedia-owl", "http://dbpedia.org/ontology/");

        LdDataset dataset = null;

        try {
            dataset = LdDatasetFactory.getInstance()
                    .service("https://dbpedia.org/sparql")
                    .name("dbpedia")
                    .defaultGraph("http://dbpedia.org")
                    .prefixes(prefixes).create();

        } catch (Exception e) {

            fail("Error with dataset: " + e.getMessage());
        }

        return dataset;
    }
}
