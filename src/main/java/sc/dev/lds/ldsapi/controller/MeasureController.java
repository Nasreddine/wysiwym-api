package sc.dev.lds.ldsapi.controller;

import lds.engine.LdSimilarityEngine;
import lds.measures.LdConfFactory;
import lds.measures.Measure;
import lds.resource.R;
import org.springframework.web.bind.annotation.*;
import sc.dev.lds.ldsapi.model.SimilarityMeasureDescription;
import sc.dev.lds.ldsapi.model.SimilarityResult;
import sc.dev.lds.ldsapi.utils.Util;
import sc.research.ldq.LdDataset;
import slib.utils.i.Conf;


import java.util.Arrays;

@RestController
public class MeasureController {
    @GetMapping(value = "measure/{name}")
    public SimilarityResult MeasureResult(@PathVariable String name) {

        // TODO: get two compared resources

        // TODO: get config

        // TODO construct and load engine

        // TODO return response

        LdDataset dataset = Util.getDBpediaDataset();

        Conf config = new Conf();
        //using indexes for calculation, change to false of no data indexing is wanted
        config.addParam("useIndexes", true);

        //specifying the main dataset that will be used for querying, in our case DBpedia
        config.addParam("LdDatasetMain", dataset);

        //specifiying the number of resources -only resources and not literals- found in the dataset to be used in calculation
        config.addParam("resourcesCount", 2350906);

        R r1 = new R("http://dbpedia.org/resource/The_Noah");
        R r2 = new R("http://dbpedia.org/resource/The_Pack_(2010_film)");


        //Initialzie the engine class object
        LdSimilarityEngine engine = new LdSimilarityEngine();

        //creates a new similarity class object and passes the config that contains necessary parameters to it, also loads needed indexes if necessary
        //PICSS similarity calculaton
        engine.load(lds.measures.Measure.PICSS, config);


        double score = engine.similarity(r1, r2);

        //ends calculation for the chosen similaarity and closes all indexes if created
        engine.close();

        return new SimilarityResult(name, score);

    }

    @GetMapping(value = "describe/{name}")
    public SimilarityMeasureDescription Describe(@PathVariable String name) throws Exception {

        // TODO: if measure does not exist, return error message
        Conf config = LdConfFactory.createDeafaultConf(Measure.valueOf(name));
        SimilarityMeasureDescription description = new SimilarityMeasureDescription(name, config);

        return description;
    }


    @GetMapping(value = "allMeasures/")
    public String[] AllMeasures() {
        String[] measures = Arrays.stream(Measure.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
        return measures;
    }
}
