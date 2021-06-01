package sc.dev.lds.ldsapi.controller;

import lds.engine.LdSimilarityEngine;
import lds.conf.LdConfFactory;
import lds.measures.Measure;
import lds.measures.weight.WeightMethod;
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

    @GetMapping(value = "measure/{name}/{ressource1}/{ressource2}")
    public SimilarityResult MeasureResult(@PathVariable String name, @PathVariable String ressource1, @PathVariable String ressource2) {

        System.out.println(ressource1 + " " + ressource2);
        // TODO: get two compared resources


        // TODO: get config

        LdDataset dataset = Util.getDBpediaDataset();
        Conf config = new Conf();
        config.addParam("useIndexes", true);
        config.addParam("LdDatasetMain", dataset);
        // TODO construct and load engine

        LdSimilarityEngine engine = new LdSimilarityEngine();

        System.out.println(name);
        switch (name) {
            case "LDSD_d":
                engine.load(Measure.LDSD_d, config);
                break;
            case "LDSD_dw":
                engine.load(Measure.LDSD_dw, config);
                break;
            case "LDSD_i":
                engine.load(Measure.LDSD_i, config);
                break;
            case "LDSD_iw":
                engine.load(Measure.LDSD_iw, config);
                break;
            case "LDSD_cw":
                engine.load(Measure.LDSD_cw, config);
                break;
            case "TLDSD":
                engine.load(Measure.TLDSD_cw, config);
                break;
            case "WLDSD":
                config.addParam("LdDatasetSpecific", dataset);
                config.addParam("WeightMethod", WeightMethod.ITW);
                engine.load(Measure.WLDSD_cw, config);
                break;
            case "Resim":
                engine.load(Measure.Resim, config);
                break;
            case "TResim":
                engine.load(Measure.TResim, config);
                break;
            case "WResim":
                config.addParam("LdDatasetSpecific", dataset);
                config.addParam("WeightMethod", WeightMethod.ITW);
                engine.load(Measure.WResim, config);
                break;
            case "WTResim":
                config.addParam("LdDatasetSpecific", dataset);
                config.addParam("WeightMethod", WeightMethod.ITW);
                engine.load(Measure.WTResim, config);
                break;
            case "PICSS":
                config.addParam("resourcesCount", 2350906);
                engine.load(Measure.PICSS, config);
                break;
        }

        R r1 = new R("http://dbpedia.org/resource/" + ressource1);
        R r2 = new R("http://dbpedia.org/resource/" + ressource2);

        double score = engine.similarity(r1, r2);

        engine.close();

        return new SimilarityResult(name, score);


        // TODO return response
        /**
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
         **/
    }

    @GetMapping(value = "check")
    public String MeasureResulM() {
        return "ok";
    }

    @GetMapping(value = "similarity")
    public SimilarityResult MeasureResultPost(String name, String r1, String r2) {

        System.out.println(r1 + " " + r2);
        // TODO: get two compared resources


        // TODO: get config

        LdDataset dataset = Util.getDBpediaDataset();
        Conf config = new Conf();
        config.addParam("useIndexes", true);
        config.addParam("LdDatasetMain", dataset);
        // TODO construct and load engine

        LdSimilarityEngine engine = new LdSimilarityEngine();

        System.out.println(name);
        switch (name) {
            case "LDSD_d":
                engine.load(Measure.LDSD_d, config);
                break;
            case "LDSD_dw":
                engine.load(Measure.LDSD_dw, config);
                break;
            case "LDSD_i":
                engine.load(Measure.LDSD_i, config);
                break;
            case "LDSD_iw":
                engine.load(Measure.LDSD_iw, config);
                break;
            case "LDSD_cw":
                engine.load(Measure.LDSD_cw, config);
                break;
            case "TLDSD":
                engine.load(Measure.TLDSD_cw, config);
                break;
            case "WLDSD":
                config.addParam("LdDatasetSpecific", dataset);
                config.addParam("WeightMethod", WeightMethod.ITW);
                engine.load(Measure.WLDSD_cw, config);
                break;
            case "Resim":
                engine.load(Measure.Resim, config);
                break;
            case "TResim":
                engine.load(Measure.TResim, config);
                break;
            case "WResim":
                config.addParam("LdDatasetSpecific", dataset);
                config.addParam("WeightMethod", WeightMethod.ITW);
                engine.load(Measure.WResim, config);
                break;
            case "WTResim":
                config.addParam("LdDatasetSpecific", dataset);
                config.addParam("WeightMethod", WeightMethod.ITW);
                engine.load(Measure.WTResim, config);
                break;
            case "PICSS":
                config.addParam("resourcesCount", 2350906);
                engine.load(Measure.PICSS, config);
                break;
        }

        R r1_ = new R("http://dbpedia.org/resource/" + r1);
        R r2_ = new R("http://dbpedia.org/resource/" + r2);

        double score = engine.similarity(r1_, r2_);

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
