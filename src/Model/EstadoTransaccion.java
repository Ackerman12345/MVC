
package Model;


public class EstadoTransaccion {
    static int estados[]={
        1,-1,2
    };
    static String estado[]={
        "Transaccion Exitosa",
        "Transaccion Fallido",
        "Datos no encontrados",
    };
    public static  String notificacion(int numero){
        if(numero==1)
            return "Transaccion Exitosa";
        else if(numero==-1)
             return  "Transaccion Fallido";
        else  
            return "Datos no encontrados";
      
    }
}
