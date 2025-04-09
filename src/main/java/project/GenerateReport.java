package project;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GenerateReport {
    public static String PACKAGE_NAME_CONTROLLER ="CONTROLLER";
    public static String PACKAGE_NAME_DATABASE ="DAO";
    public static String PACKAGE_NAME_MODEL ="MODEL";
    public static String PACKAGE_NAME_VIEW ="VIEW";

    public static String ID_PROJECT="OOBD_2221_38";
    public void generateExcelRepo(ArrayList<Repo> repos)throws IOException {
        //FileInputStream fis = new FileInputStream("repo_OOBD_29.xlsx");
//        Workbook workbook = null;
//        workbook = new HSSFWorkbook();
//        Sheet sheet = workbook.createSheet("OOBD_2223");
//        Iterator<Repo> iterator  = repos.iterator();


        File xlsFile = new File("C:\\Users\\sergi\\Desktop\\ICSE2024-Educational\\DI MARTINO\\repo_OOBD_SDM.xls");
        FileInputStream inputStream = new FileInputStream(xlsFile);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int rowIndex = sheet.getLastRowNum();
        System.out.println(rowIndex);
        rowIndex++;
        Row row = sheet.createRow(rowIndex);
        Cell c0 = row.createCell(0);
        c0.setCellValue(ID_PROJECT);


        Map<String,Map<String,Integer>> res = getRelationsByLayers(repos);

        Cell c1 = row.createCell(1);
        c1.setCellValue(res.get("View").get("Model")>0 ? "yes": "no");
        Cell c2 = row.createCell(2);
        c2.setCellValue(res.get("View").get("Controller")>0 ? "yes": "no");
        Cell c3 = row.createCell(3);
        c3.setCellValue(res.get("View").get("Database")>0 ? "yes": "no");
        Cell c4 = row.createCell(4);
        c4.setCellValue(res.get("View").get("View")>0 ? "yes": "no");

        Cell c5 = row.createCell(5);
        c5.setCellValue(res.get("Model").get("View")>0 ? "yes": "no");
        Cell c6 = row.createCell(6);
        c6.setCellValue(res.get("Model").get("Controller")>0 ? "yes": "no");
        Cell c7 = row.createCell(7);
        c7.setCellValue(res.get("Model").get("Database")>0 ? "yes": "no");
        Cell c8 = row.createCell(8);
        c8.setCellValue(res.get("Model").get("Model")>0 ? "yes": "no");


        Cell c9 = row.createCell(9);
        c9.setCellValue(res.get("Controller").get("View")>0 ? "yes": "no");
        Cell c10 = row.createCell(10);
        c10.setCellValue(res.get("Controller").get("Model")>0 ? "yes": "no");
        Cell c11 = row.createCell(11);
        c11.setCellValue(res.get("Controller").get("Database")>0 ? "yes": "no");
        Cell c12 = row.createCell(12);
        c12.setCellValue(res.get("Controller").get("Controller")>0 ? "yes": "no");


        Cell c13 = row.createCell(13);
        c13.setCellValue(res.get("Database").get("View")>0 ? "yes": "no");
        Cell c14 = row.createCell(14);
        c14.setCellValue(res.get("Database").get("Model")>0 ? "yes": "no");
        Cell c15 = row.createCell(15);
        c15.setCellValue(res.get("Database").get("Controller")>0 ? "yes": "no");
        Cell c16 = row.createCell(16);
        c16.setCellValue(res.get("Database").get("Database")>0 ? "yes": "no");


        FileOutputStream fos = new FileOutputStream(xlsFile);
        workbook.write(fos);
        fos.close();
        System.out.println("repo written successfully");
    }


    public Map<String, Map<String, Integer>> getRelationsByLayers(ArrayList<Repo> repos){
        // vm vc vd mv mc md cv cm cd dv dm dc
        Map<String,Map<String,Integer>> res = new HashMap<>();
        res.put("View", new HashMap<>());
        res.get("View").put("Controller",0);
        res.get("View").put("Model",0);
        res.get("View").put("Database",0);
        res.get("View").put("View",0);

        res.put("Model", new HashMap<>());
        res.get("Model").put("View",0);
        res.get("Model").put("Controller",0);
        res.get("Model").put("Database",0);
        res.get("Model").put("Model",0);

        res.put("Controller", new HashMap<>());
        res.get("Controller").put("View",0);
        res.get("Controller").put("Model",0);
        res.get("Controller").put("Database",0);
        res.get("Controller").put("Controller",0);

        res.put("Database", new HashMap<>());
        res.get("Database").put("View",0);
        res.get("Database").put("Model",0);
        res.get("Database").put("Controller",0);
        res.get("Database").put("Database",0);

        for(Repo repo : repos) {
            for (String violation : repo.getViolatons()) {
                if (violation.substring(violation.indexOf("<") + 1, violation.length()).startsWith("project." + PACKAGE_NAME_CONTROLLER)) {
                    int n =  res.get(repo.getLayer()).get("Controller")+1;
                    res.get(repo.getLayer()).replace("Controller", n);
                } else if (violation.substring(violation.indexOf("<") + 1, violation.length()).startsWith("project." + PACKAGE_NAME_DATABASE)) {
                    int n =  res.get(repo.getLayer()).get("Database")+1;
                    res.get(repo.getLayer()).replace("Database", n);
                } else if (violation.substring(violation.indexOf("<") + 1, violation.length()).startsWith("project." + PACKAGE_NAME_MODEL)) {
                    int n =  res.get(repo.getLayer()).get("Model")+1;
                    res.get(repo.getLayer()).replace("Model", n);
                } else if (violation.substring(violation.indexOf("<") + 1, violation.length()).startsWith("project." + PACKAGE_NAME_VIEW)) {
                    int n =  res.get(repo.getLayer()).get("View")+1;
                    res.get(repo.getLayer()).replace("View", n);                }
            }
        }
        return res;
    }
}
