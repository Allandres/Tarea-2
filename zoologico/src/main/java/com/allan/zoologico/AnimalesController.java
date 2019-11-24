package com.allan.zoologico;

import com.allan.zoologico.util.JsfUtil;
import com.allan.zoologico.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.chart.PieChartModel;

@Named("animalesController")
@SessionScoped
public class AnimalesController implements Serializable {

    @EJB
    private com.allan.zoologico.AnimalesFacade ejbFacade;
    private List<Animales> items = null;
    private Animales selected;

    public AnimalesController() {
    }

    public Animales getSelected() {
        return selected;
    }

    public void setSelected(Animales selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AnimalesFacade getFacade() {
        return ejbFacade;
    }

    public Animales prepareCreate() {
        selected = new Animales();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AnimalesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AnimalesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AnimalesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Animales> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Animales getAnimales(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Animales> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Animales> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public PieChartModel createPieModel1() {
        PieChartModel pieModel1 = new PieChartModel();
        int color=1;
        for (Animales Animal : items) {
            pieModel1.set(Animal.getTipo(),color*=10);
        }
        
//        pieModel1.set("Brand 1", 540);
//        pieModel1.set("Brand 2", 325);
//        pieModel1.set("Brand 3", 702);
//        pieModel1.set("Brand 4", 421);
// 
        pieModel1.setTitle("Tipos de animales");
        pieModel1.setLegendPosition("e");
        pieModel1.setFill(false);
        pieModel1.setShowDataLabels(true);
        pieModel1.setDiameter(150);
        pieModel1.setShadow(false);
        return pieModel1;
    }

    @FacesConverter(forClass = Animales.class)
    public static class AnimalesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AnimalesController controller = (AnimalesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "animalesController");
            return controller.getAnimales(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Animales) {
                Animales o = (Animales) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Animales.class.getName()});
                return null;
            }
        }

    }
    
     
    

}
