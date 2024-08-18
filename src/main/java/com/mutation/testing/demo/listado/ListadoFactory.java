package com.mutation.testing.demo.listado;

import com.mutation.testing.demo.enums.TipoPublicacion;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ListadoFactory {


    final Listado clasificado;

    final  Listado emprendimiento;

    public ListadoFactory(@Qualifier("clasificado") Listado clasificado, @Qualifier("emprendimiento") Listado emprendimiento) {
        this.clasificado = clasificado;
        this.emprendimiento = emprendimiento;
    }

    public Listado getListado(TipoPublicacion tipoPublicacion){
        return tipoPublicacion.equals(TipoPublicacion.CLASIFICADO) ? clasificado : emprendimiento;
    }


}
