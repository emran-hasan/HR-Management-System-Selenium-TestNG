package utlis;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import setup.EmpModel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static void saveUsers(EmpModel model) throws IOException, ParseException {
       String fileLocation = "./src/test/resources/user.json";
        JSONParser jsonParser = new JSONParser();
        JSONArray empArray = (JSONArray) jsonParser.parse( new FileReader(fileLocation));

        JSONObject empObject = new JSONObject();
        empObject.put("fName",model.getfName());
        empObject.put("lName",model.getlName());
        empObject.put("empID",model.getEmpID());
        empObject.put("username",model.getUsername());
        empObject.put("password",model.getPassword());

        empArray.add(empObject);

        FileWriter fw = new FileWriter(fileLocation);
        fw.write(empArray.toJSONString());
        fw.flush();
        fw.close();

    }
    public static JSONArray getUser() throws IOException, ParseException {
        String fileLocation = "./src/test/resources/user.json";
        JSONParser jsonParser = new JSONParser();
        JSONArray empArray = (JSONArray) jsonParser.parse(new FileReader(fileLocation));
        return empArray;
    }
    public static void windowScroll(WebDriver driver, int height){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0, "+height+")");

    }

}
