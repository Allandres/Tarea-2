package com.allan.zoologico;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Bean administrado para autenticación
 * @author Reyner González M
 */
@ViewScoped
@Named("loginBean")
public class LoginBean implements Serializable{
  
    private static final long serialVersionUID = 1L;
    private String user;//Valor del input del formulario de autenticacion
    private String password;//Valor del input del formulario de autenticacion
    private String message;//Mensaje de retorno

    /**
     * Obtiene el usuario (Solo para enlace de formulario de autenticación)
     * @return Nombre de usuario
     */
    public String getUser() {
        return user;
    }

    /**
     * Guarda valores para el usuario (Solo para enlace de formulario de autenticación)
     * @param user Usuario
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Obtiene clave de usuario (Solo para enlace de formulario de autenticación)
     * @return Clave de usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Guarda clave de usuario (Solo para enlace de formulario de autenticación)
     * @param password Clave de usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el mensaje de resultado (Solo para enlace de formulario de autenticación)
     * @return Mensaje de resultado
     */
    public String getMessage() {
        return message;
    }

    /**
     * Guarda mensaje de resultado (Solo para enlace de formulario de autenticación)
     * @param message Mensaje de resultado
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Autentica el Usuario
     * @return Mensaje con URL de redirección
     */
    public String loginProject() {
        if(user.equals("allan") && password.equals("123")){
            saveUserSession();
            return this.redirectUser("Info","Bien!","Autenticado correctamente!", "/animales/List.xhtml?faces-redirect=true");
        }else{
            return this.redirectUser("Warn","Incorrecto!","Credenciales invalidas!", "/index.xhtml?faces-redirect=false");
        }
    }

    /**
     * Cierre de sesion
     * @return Mensaje con URL de redirección
     */
    /*
    public String logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        return this.redirectUser("Info","Bien!", "Ha cerrado la sesión exitosamente", "/index.xhtml?faces-redirect=true");
    }
    */

    /**
     * Guarda el estado de la sesión
     */
    public void saveUserSession() {
        //Una idea es guardar los valores de usuario en sesion, por ejemplo utilizando HttpSession
        /*
        HttpSession session = Util.getSession();
        session.setAttribute("userId", user.getId());//
        session.setAttribute("userName", user.getName());//
        */
    }

    /**
     * Redireccionamiento del usuario
     * @param severity Severidad del mensaje
     * @param message Mensaje de resultado
     * @param detail Detalle mensaje de resultado
     * @param return_page URL pagina de retorno
     * @return 
     */
    public String redirectUser(String severity, String message, String detail, String return_page) {
        switch (severity) {
            case "Info":
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        message,
                        detail));
                break;
            case "Warn":
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        message,
                        detail));
                break;
            case "Error":
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        message,
                        detail));
                break;
            case "Fatal":
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        message,
                        detail));
                break;
        }
        return return_page;
    }
}