package tools;

import model.City;
import daos.CityDAO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Loader {
    static public void loadCitiesCapitals(CityDAO cities) throws SQLException {
        OPCPackage pkg = null;
        try {
            pkg = OPCPackage.open(new File("E:\\AN2\\ProiectePA\\PA_LAB8\\src\\main\\resources\\cities.xlsx"));
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
//creating workbook instance that refers to .xls file
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(pkg);
        } catch (IOException e) {
            e.printStackTrace();
        }
//creating a Sheet object to retrieve the object
        XSSFSheet sheet = wb.getSheetAt(0);
//evaluating cell type
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
        int skipHeader = 1;
        for (Row row : sheet)     //iteration over row using for each loop
        {
            if (skipHeader == 1) {
                skipHeader = 0;
                continue;
            }
            int columnIndex = 1;
            City tempCity = new City();
            for (Cell cell : row)    //iteration over cell using for each loop
            {
                switch (columnIndex) {
                    case 1:
                        tempCity.setName(cell.getStringCellValue());
                        break;
                    case 2:
                        tempCity.setCountry(cell.getStringCellValue());
                        break;
                    case 3:
                        tempCity.setLatitude(cell.getNumericCellValue());
                        break;
                    case 4:
                        tempCity.setLongitutde(cell.getNumericCellValue());
                        cities.create(tempCity.getName(), tempCity.getCountry(), true, tempCity.getLatitude(), tempCity.getLongitutde());
                        System.out.println(tempCity);
                        break;
                }
                columnIndex++;
            }
            System.out.println();
        }
    }

    static public void loadCities(CityDAO cities) throws SQLException {
        OPCPackage pkg = null;
        try {
            pkg = OPCPackage.open(new File("E:\\AN2\\ProiectePA\\PA_LAB8\\src\\main\\resources\\citiesGood.xlsx"));
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
//creating workbook instance that refers to .xls file
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(pkg);
        } catch (IOException e) {
            e.printStackTrace();
        }
//creating a Sheet object to retrieve the object
        XSSFSheet sheet = wb.getSheetAt(0);
//evaluating cell type
        int skipHeader = 1;
        for (Row row : sheet)     //iteration over row using for each loop
        {
            if (skipHeader == 1) {
                skipHeader = 0;
                continue;
            }
            int columnIndex = 1;
            City tempCity = new City();
            for (Cell cell : row)    //iteration over cell using for each loop
            {
                switch (columnIndex) {
                    case 1:
                        tempCity.setName(cell.getStringCellValue());
                        break;
                    case 2:
                        tempCity.setCountry(cell.getStringCellValue());
                        break;

                    case 3:
                        String tempString = cell.getStringCellValue();
                        if (tempString.equals("true"))
                            tempCity.setIsCapital(true);
                        else
                            tempCity.setIsCapital(false);
                        break;
                    case 4:
                        tempCity.setLatitude(cell.getNumericCellValue());
                        break;
                    case 5:
                        tempCity.setLongitutde(cell.getNumericCellValue());
                        cities.create(tempCity.getName(), tempCity.getCountry(), tempCity.getIsCapital(), tempCity.getLatitude(), tempCity.getLongitutde());
                        /**
                         * aici printez orasul adaugat
                         */
                        // System.out.println(tempCity);
                        break;

                }
                columnIndex++;
            }
            //System.out.println();
        }
    }

}
