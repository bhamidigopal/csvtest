package com.svb.csvjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.relique.jdbc.csv.CsvDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsvJdbcController {

	@Autowired
	FtlJsonConvertor convertor;
	

    public List<Catalog> getDataAndTransform( String query) throws SQLException {
    	
    	 List<Map<String, String>> rows = new ArrayList<>();
        DriverManager.registerDriver(new CsvDriver());

        String url = "jdbc:relique:csv:/Users/bhamidi/Downloads";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            List<String> columnNames = new ArrayList<>();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = meta.getColumnName(i);
                columnNames.add(columnName);
            }

           
            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = columnNames.get(i);
                    String value = rs.getString(columnName);
                    row.put(columnName, value);
                }
                rows.add(row);
            }

           
        }
        
        System.out.println("Rows "+rows);
        
        List<Catalog> returnList = null;
        //Pass this to the FTL Transformer
        try {
			returnList = convertor.catalogTransformer(rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return  returnList ;
    }
	
	
	
	
	@GetMapping("/data")
    public List<Catalog> csvdata(@RequestParam("apiName") String apiName, 
            @RequestParam("apiVersion") String apiVersion) throws SQLException {
        String query = "SELECT * FROM catalog where api_name='"+apiName+"' and api_version='"+apiVersion+"'";

		
		return getDataAndTransform(query);
		
		
		
	}
	
	
	@GetMapping("/all")
    public List<Catalog> csvdataForAll() throws SQLException {
		
		  String query = "SELECT * FROM catalog";

			
			return getDataAndTransform(query);
			
			
		
		
	}
	

}
