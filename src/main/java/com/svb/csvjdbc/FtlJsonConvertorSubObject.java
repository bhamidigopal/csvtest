package com.svb.csvjdbc;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;



import freemarker.template.*;

public class FtlJsonConvertorSubObject {
    
    public static void main(String[] args) throws Exception {
        
     List<Map<String, Object>> inputList = new ArrayList<>();
        
     Map<String, Object> input1 = new HashMap<>();
     input1.put("first_name", "John");
     input1.put("last_name", "Doe");
     input1.put("age", 30);
     Map<String, String> contact1 = new HashMap<>();
     contact1.put("city", "New York");
     contact1.put("street", "123 Main St");
     input1.put("contact", contact1);
     inputList.add(input1);

     Map<String, Object> input2 = new HashMap<>();
     input2.put("first_name", "Jane");
     input2.put("last_name", "Doe");
     input2.put("age", 25);
     Map<String, String> contact2 = new HashMap<>();
     contact2.put("city", "Los Angeles");
     //contact2.put("street", "456 Elm St");
     input2.put("contact", contact2);
     inputList.add(input2);
        
        // Load the FreeMarker template from the classpath
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setClassForTemplateLoading(FtlJsonConvertorSubObject.class, "/"); // load from the root of the classpath
        Template template = cfg.getTemplate("mapping_sub.ftl");
        
        // Create a data model and add the input list to it
        SimpleHash dataModel = new SimpleHash();
        dataModel.put("inputList", inputList);
        
        // Apply the template to the data model
        StringWriter out = new StringWriter();
        template.process(dataModel, out);
        
   System.out.println("Json "+out);
        // Deserialize the JSON string into a List of MyObject instances
        ObjectMapper objectMapper = new ObjectMapper();
        List<MyObject> myObjectList = objectMapper.readValue(out.toString(), new TypeReference<List<MyObject>>(){});

        
        // Print the converted Java objects
        for (MyObject myObject : myObjectList) {
            System.out.println(myObject);
        }
    }
    }
    

