package utilidades;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * Clase CORSFilter 
 * @author Jhonny Maguana
 * @author Sandra Penaranda
 * @version 2.0
 *
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {

	/**
	 * Metodo filter de tipo void 
	 */
   @Override
   public void filter(final ContainerRequestContext requestContext,
                      final ContainerResponseContext cres) throws IOException {
      cres.getHeaders().add("Access-Control-Allow-Origin", "*");
      cres.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
      cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
      //cres.getHeaders().add("Access-Control-Allow-Headers", "X-Requested-With");
      cres.getHeaders().add("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS, HEAD");
      cres.getHeaders().add("Access-Control-Max-Age", "1209600");
   }
}