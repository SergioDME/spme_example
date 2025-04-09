package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AnalizeRepo {

    private ArrayList<Repo> analizeLayeredArchitectureRepo(){
        BufferedReader reader;
        ArrayList<Repo> repos = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("target/surefire-reports/LayeredArchitectureTest.txt"));
            String line = reader.readLine();

            while (line != null) {
                //System.out.println(line);
                if(line.startsWith("where layer ")){
                    Repo repo = new Repo();
                    int pos1 =line.indexOf("'");
                    int pos2= line.substring(pos1+1,line.length()).indexOf("'");
                    String layer1 = line.substring(pos1+1,line.length()).substring(0,pos2);
                    repo.setLayer(layer1);
//                  pos1 =line.indexOf("[");
//                  pos2= line.indexOf("]");
//                  String allows_layers = line.substring(pos1+1,pos2);
                    pos1= line.indexOf("(");
                    pos2 = line.indexOf(")");
                    int n_times = Integer.parseInt(line.substring(pos1+1,pos2).substring(0,line.substring(pos1+1,pos2).indexOf(" ")));
//                  System.out.println(layer1+" can be accessed only by "+allows_layers+" violated :"+n_times+" times");
                    ArrayList<String> list = new ArrayList<>();
                    for(int i=0;i<n_times;i++){
                        line = reader.readLine();
                        list.add(line);
                    }
                    repo.setViolatons(list);
                    repos.add(repo);
                }
                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  repos;
    }

    public static void main(String[]args) throws IOException {
        AnalizeRepo analizeRepo = new AnalizeRepo();
        ArrayList<Repo> repos = analizeRepo.analizeLayeredArchitectureRepo();
        GenerateReport generateReport = new GenerateReport();
        generateReport.generateExcelRepo(repos);
    }
}
