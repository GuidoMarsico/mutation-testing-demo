package com.mutation.testing.demo.datasource;

import com.mutation.testing.demo.model.Publicacion;
import com.mutation.testing.demo.util.Util;

import java.util.ArrayList;
import java.util.List;

public final class  DataSource {

       private static DataSource instance;
       public List<Publicacion> publicacionList;

    public DataSource(String pathData) {
         publicacionList = new ArrayList<>();
         List<List<String>> records = Util.read(pathData);
         for(List<String> row : records){
             publicacionList.add(Util.transform(row));
         }
    }


    public static DataSource getInstance(String pathData) {
        if(instance == null){
            instance = new DataSource(pathData);
        }
        return instance;
    }
}
