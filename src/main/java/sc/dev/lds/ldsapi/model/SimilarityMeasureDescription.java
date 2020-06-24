package sc.dev.lds.ldsapi.model;

import slib.utils.i.Conf;

public class SimilarityMeasureDescription {
    String name;
    Conf config;

    public SimilarityMeasureDescription(String name, Conf config) {
        this.name = name;
        this.config = config;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Conf getConfig() {
        return config;
    }

    public void setConfig(Conf config) {
        this.config = config;
    }
}
