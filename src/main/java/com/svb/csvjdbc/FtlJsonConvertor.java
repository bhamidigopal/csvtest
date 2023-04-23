package com.svb.csvjdbc;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;

@Component
public class FtlJsonConvertor {
    
  
    
    
    public List<Catalog> catalogTransformer(List<Map<String, String>> inputList) throws Exception{
        
		/*
		 * List<Map<String, Object>> inputList = new ArrayList<>();
		 * 
		 * Map<String, Object> input1 = new HashMap<>(); input1.put("first_name",
		 * "John"); input1.put("last_name", "Doe"); input1.put("age", 30);
		 * inputList.add(input1);
		 * 
		 * Map<String, Object> input2 = new HashMap<>(); input2.put("first_name",
		 * "Jane"); input2.put("last_name", "Doe"); input2.put("age", 25);
		 * inputList.add(input2);
		 */
    	
    	
        
        // Load the FreeMarker template from the classpath
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setClassForTemplateLoading(FtlJsonConvertor.class, "/"); // load from the root of the classpath
        Template template = cfg.getTemplate("mapping_catalog.ftl");
        
        // Create a data model and add the input list to it
        SimpleHash dataModel = new SimpleHash();
        dataModel.put("inputList", inputList);
        
        // Apply the template to the data model
        StringWriter out = new StringWriter();
        template.process(dataModel, out);
        
        System.out.println("Json "+out);
        // Deserialize the JSON string into a List of MyObject instances
        ObjectMapper objectMapper = new ObjectMapper();
        List<Catalog> myObjectList = objectMapper.readValue(out.toString(), new TypeReference<List<Catalog>>(){});

        
        // Print the converted Java objects
        for (Catalog myObject : myObjectList) {
            System.out.println(myObject);
        }
        
        
        
        return myObjectList;
    }
    }
    

