package com.mutation.testing.demo.util;

import com.mutation.testing.demo.enums.Nivel;
import com.mutation.testing.demo.enums.TipoPropiedad;
import com.mutation.testing.demo.enums.XFactor;
import com.mutation.testing.demo.model.Publicacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Util {


    static final List<Nivel> niveles = List.of(Nivel.GRATIS,Nivel.NORMAL,Nivel.EXCELENTE,Nivel.EXCELENTE_DESA,Nivel.NORMAL_DESA);
    static final List<TipoPropiedad> tipopropiedades = List.of(TipoPropiedad.CASA,TipoPropiedad.DEPARTAMENTO,TipoPropiedad.DESARROLLO);
    static final List<XFactor> xfactors = List.of(XFactor.XBLUE,XFactor.XBLACK,XFactor.XGOLD,XFactor.XGREEN,XFactor.XRED);

     public static List<List<String>> read(String path)  {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
         return records;
    }

     static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public static Publicacion transform(List<String> row){
        Integer id = Integer.parseInt(row.get(0));
        Integer idPadre = null;
        if(!"".equals(row.get(1))){
            idPadre = Integer.parseInt(row.get(1));
        }
        Integer idTipoProp = Integer.parseInt(row.get(2));
        String anunciante = row.get(3);
        String ubicacion = row.get(4);
        Integer precio = null;
        if(!"".equals(row.get(5))){
            precio = Integer.parseInt(row.get(5));
        }
        Integer idNivel = Integer.parseInt(row.get(6));
        Integer xFactor = Integer.parseInt(row.get(7));
        String fechaPublicacion = row.get(8);
        Publicacion p = new Publicacion(id,idPadre,idTipoProp,anunciante,ubicacion,precio,idNivel,xFactor,fechaPublicacion);
        return p;
    }


    public static TipoPropiedad getTipoPropiedadById(Integer id){
         return tipopropiedades.stream().filter(tipoPropiedad -> tipoPropiedad.id.equals(id)).findFirst().get();
    }

    public static Nivel getNivelByNombre(String nombre){
         return niveles.stream().filter(n -> n.nombre.equals(nombre)).findFirst().get();
    }

    public static Nivel getNivelById(Integer id){
        return niveles.stream().filter(n -> n.id.equals(id)).findFirst().get();
    }

    public static XFactor getXfactor(Integer valor){
         XFactor xf = XFactor.XBLUE ;
        for (XFactor xfactor : xfactors) {
            if (xfactor.min <=valor & valor < xfactor.max)
                xf =  xfactor;
        }
        return  xf;
    }

    public static XFactor getXfactorByNombre(String nombre){
         return xfactors.stream().filter(xFactor -> xFactor.xFactor.equals(nombre)).findFirst().get();
    }



}
