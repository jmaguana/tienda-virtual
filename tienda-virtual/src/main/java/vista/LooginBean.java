package vista;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import datos.UsuarioDAO;

@ManagedBean
@SessionScoped
public class LooginBean {

	@Inject
	private UsuarioDAO uDao;
	
	private String email;
	private String pass;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();	
		return "Login";
	}
	
	public String login() {
		Boolean aux = uDao.login(email, pass);
		if(aux) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("user", email);
			return "TopClientes";
		}else {
			FacesMessage fm = new FacesMessage("Login Error", "Error en inicio de sesion");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, fm);
			return "Login.xhtml";
		}
		
	}
	
}
