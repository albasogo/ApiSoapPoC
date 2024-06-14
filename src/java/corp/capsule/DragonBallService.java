package corp.capsule;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.BindingType;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class DragonBallService {
 private static final Logger logger = Logger.getLogger(DragonBallService.class.getName());
    @WebMethod
    public Personaje obtenerPersonajePorNombre(String nombre) {
        try {
            List<Personaje> personajes = JsonUtil.readJson();
            for (Personaje personaje : personajes) {
                if (personaje.getNombre().equalsIgnoreCase(nombre)) {
                    return personaje;
                }
            }
        } catch (IOException e) {
           logger.log(Level.SEVERE, "Error al leer JSON: {0}", e.getMessage());
        }
        return null;
    }

    @WebMethod
    public String crearPersonaje(Personaje personaje) throws URISyntaxException {
        
        logger.info("Inicio de crearPersonaje");
    
            logger.severe(personaje.getNombre());
       
        try {
            logger.info("Leyendo JSON");
            List<Personaje> personajes = JsonUtil.readJson();
            personajes.add(personaje);
            logger.info("Escribiendo JSON");
            JsonUtil.writeJson(personajes);
            return "Personaje creado: " + personaje.getNombre();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al crear el personaje: {0}", e.getMessage());
            return "Error al crear el personaje";
        }
    }

    @WebMethod
    public List<Personaje> obtenerTodosLosPersonajes() {
        try {
            return JsonUtil.readJson();
        } catch (IOException e) {
           logger.log(Level.SEVERE, "Error al leer JSON: {0}", e.getMessage());
            return null;
        }
    }
}

