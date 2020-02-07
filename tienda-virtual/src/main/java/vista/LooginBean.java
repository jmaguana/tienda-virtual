package vista;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import datos.UsuarioDAO;

/**
 * En esta clase tenemos los atributos necesarios para realizar un xhtml para
 * realizar un login, asi como metodos los cuales hacen uso de los metodos del
 * controladorWeb
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
@ManagedBean
@SessionScoped
public class LooginBean {

	/**
	 * Atributo de tipo UsuarioDAO
	 */
	@Inject
	private UsuarioDAO uDao;

	/**
	 * Atributos de tipo String
	 */
	private String email;
	private String pass;

	/**
	 * 
	 * @return email del Usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email de tipo String y pertenenciente al Usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return contrasenia del Usuario
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * 
	 * @param pass que es la contrasenia del usuario de tipo String
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * Metodo para cerrar sesion
	 * @return un String 
	 */
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();
		return "Login";
	}

	/**
	 * Metodo para iniciar sesion
	 * @return un String
	 */
	public String login() {
		Boolean aux = uDao.login(email, pass);
		if (aux) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("user", email);
			return "TopClientes";
		} else {
			FacesMessage fm = new FacesMessage("Login Error", "Error en inicio de sesion");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, fm);
			return "Login.xhtml";
		}
	}
}