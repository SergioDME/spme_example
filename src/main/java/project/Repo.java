package project;

import java.util.ArrayList;

public class Repo {
    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getLayer_allows() {
        return layer_allows;
    }

    public void setLayer_allows(String layer_allows) {
        this.layer_allows = layer_allows;
    }

    public ArrayList<String> getViolatons() {
        return violatons;
    }

    public void setViolatons(ArrayList<String> violatons) {
        this.violatons = violatons;
    }

    private String layer;
    private String layer_allows;
    private ArrayList<String> violatons;
    public Repo(){};
    public Repo(String layer,String allows, ArrayList<String> vio){
        this.layer=layer;
        this.layer_allows=allows;
        this.violatons=vio;
    }
}
