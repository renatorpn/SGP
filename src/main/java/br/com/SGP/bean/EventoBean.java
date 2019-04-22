/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.bean;

/**
 *
 * @author lucas
 */
 
import br.com.SGP.dao.EventoDAO;
import br.com.SGP.dao.TipoEventoDAO;
import br.com.SGP.entities.Cadastro;
import br.com.SGP.entities.Evento;
import br.com.SGP.entities.TipoEvento;
import br.com.SGP.entities.Usuario;
//import br.com.SGP.utils.TipoEvento;
import br.com.SGP.utils.VendaRealizadaEvento;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
 
@ManagedBean(name = "EventoMB")
@ViewScoped
public class EventoBean implements Serializable {
 
    private ScheduleModel eventModel;
     
  //  private ScheduleModel lazyEventModel;
    
    private EventoDAO eventoDAO = new EventoDAO();
    private Evento event;
    private List<Evento> listaEvento;
    private TipoEvento tipoEvento = new TipoEvento();
    private List<TipoEvento> listaTipoEvento;
    private TipoEventoDAO tipoEventoDAO = new TipoEventoDAO();
    
    public EventoDAO getEventoDAO() {
        return eventoDAO;
    }

    public void setEventoDAO(EventoDAO eventoDAO) {
        this.eventoDAO = eventoDAO;
    }

    public List<Evento> getListaEvento() {
        return listaEvento;
    }

    public void setListaEvento(List<Evento> listaEvento) {
        this.listaEvento = listaEvento;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public Evento getEvent() {
        return event;
    }

    public void setEvent(Evento event) {
        this.event = event;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public List<TipoEvento> getListaTipoEvento() {
        listaTipoEvento = tipoEventoDAO.findAll();
        return listaTipoEvento;
    }

    public void setListaTipoEvento(List<TipoEvento> listaTipoEvento) {
        this.listaTipoEvento = listaTipoEvento;
    }
    
    
    
    @PostConstruct
    public void init(){
        eventoDAO = new EventoDAO();
        event = new Evento();
        eventModel = new DefaultScheduleModel();
        listaEvento = eventoDAO.findAll();
        
        for(Evento ev : listaEvento){
            DefaultScheduleEvent evt = new DefaultScheduleEvent();
            evt.setEndDate(ev.getDataFim());
            evt.setStartDate(ev.getDataInicio());
            evt.setTitle(ev.getNomeevento());
            evt.setData(ev.getIdevento());
            evt.setDescription(ev.getDescricao());
            evt.setAllDay(true);
            evt.setEditable(true);
            
            eventModel.addEvent(evt);
        }
        
        
        
    }
    
    public void onSelect(SelectEvent selectEvent){
        
        if(event == null){
                event = new Evento();
            }else{
        
        ScheduleEvent evento = (ScheduleEvent) selectEvent.getObject();
        
        for(Evento ev : listaEvento){
            
            if(ev.getIdevento() == (Long) evento.getData()){
                event = ev;
                break;
            }
        }
    }
    
    }
    
    public void onCreate(SelectEvent selectEvent){
        ScheduleEvent event = new DefaultScheduleEvent("",(Date)selectEvent.getObject(), (Date)selectEvent.getObject());
        Evento evento = new Evento();
        evento.setDataInicio(new java.sql.Date(event.getStartDate().getTime()));
        evento.setDataFim(new java.sql.Date(event.getEndDate().getTime()));
        
    }
    
    public void salvar(){
        if(event.getIdevento() == null){
            
            if(event.getDataInicio().getTime() <= event.getDataFim().getTime()){
                eventoDAO = new EventoDAO();
                try{
                    eventoDAO.save(event);
                    init();
                    throw new SQLException();
                }catch(SQLException e){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar evento!", "Erro: " + e.getMessage()));    
                }
                event = new Evento();
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data inicial não pode ser maior que data final!", ""));
            }
            
        }else{
                //event.setRepresentante(u);
                eventoDAO.save(event);
                event = new Evento();
            }
    }
    
    public void deletar(){
        if(event.getIdevento() != null)
        eventoDAO.remove(event.getIdevento());
        init();
    }
    
    public VendaRealizadaEvento[] getVendaRealizada() {
        return VendaRealizadaEvento.values();
    }
    
    public void onMove(ScheduleEntryMoveEvent evento){
        for(Evento ev: listaEvento){
            if(ev.getIdevento() == (Long)evento.getScheduleEvent().getData()){
                event = ev;
                eventoDAO = new EventoDAO();
                try{
                    eventoDAO.save(event);
                    init();
                    throw new SQLException();
                }catch(SQLException e){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar evento!", "Erro: " + e.getMessage()));
                }
                break;
            }
        }
    }
    
    public void onResize(ScheduleEntryResizeEvent evento){
        for(Evento ev: listaEvento){
            if(ev.getIdevento() == (Long)evento.getScheduleEvent().getData()){
                event = ev;
                eventoDAO = new EventoDAO();
                try{
                    eventoDAO.save(event);
                    init();
                    throw new SQLException();
                }catch(SQLException e){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar evento!", "Erro: " + e.getMessage()));
                }
                break;
            }
        }
    }
    
    public void limpar(){
        event = new Evento();
    }
    
    public void cadastrarTipoEvento() {
            tipoEventoDAO.save(tipoEvento);
            tipoEvento = new TipoEvento();
            
        //return "/app/sucesso?faces-redirect=true";
    }
     
     public void removerTipoEvento() {
        tipoEventoDAO.remove(tipoEvento.getIdTipoEvento());
    }
        public void editarTipoEvento(TipoEvento t) {
        tipoEvento = t;
        //return "/app/produto/alterarcategoria?faces-redirect=true";
    }
    
}
