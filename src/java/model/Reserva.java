/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.primefaces.model.ScheduleEvent;
import static util.DateTools.prepareCalendar;

/**
 *
 * @author charles
 */
@Entity
public class Reserva implements Serializable, ScheduleEvent {

    @ManyToOne(fetch = FetchType.EAGER)
    private GrupoReserva grupoReserva;

    public GrupoReserva getGrupoReserva() {
        return grupoReserva;
    }

    public void setGrupoReserva(GrupoReserva grupoReserva) {
        this.grupoReserva = grupoReserva;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long iid;
    @ManyToOne
    private Operador operador;
//    @ManyToOne
//    private Recurso recurso;
    @ManyToOne(fetch = FetchType.EAGER)
    private Pessoa reservante;
    @ManyToOne
    private Centro centro;
    String motivo;

//    @OneToMany(mappedBy = "reserva", fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.EAGER)
    protected List<Recurso> recursos = new ArrayList<Recurso>();

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public void addRecurso(Recurso recurso) {
        this.recursos.add(recurso);
    }

    public void setRecursos(List<Recurso> recursos) {
        this.recursos.addAll(recursos);
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    @Transient
    String id = null;

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

//    public Recurso getRecurso() {
//        return recurso;
//    }
//
//    public void setRecurso(Recurso recurso) {
//        this.recurso = recurso;
//    }
    public Pessoa getReservante() {
        return reservante;
    }

    public void setReservante(Pessoa reservante) {
        this.reservante = reservante;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Date getRealizacao() {
        return realizacao;
    }

    public void setRealizacao(Date realizacao) {
        this.realizacao = realizacao;
    }

   

    public Reserva createClone(Reserva reserva) {
       
        reserva.centro = this.centro;
        reserva.fim = this.fim;
        reserva.inicio = this.inicio;
        reserva.fim = this.fim;
        reserva.realizacao = this.realizacao;
        reserva.recursos = this.recursos;
        reserva.reservante = this.reservante;
        reserva.motivo = this.motivo;
        return reserva;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date inicio;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fim;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date realizacao;

    public Long getIid() {
        return iid;
    }

    public void setIid(Long id) {
        this.iid = id;
    }

    @Override
    public int hashCode() {
        /*int hash = 5;
         hash = 61 * hash + (this.titulo != null ? this.titulo.hashCode() : 0);
         hash = 61 * hash + (this.inicio != null ? this.inicio.hashCode() : 0);
         hash = 61 * hash + (this.fim != null ? this.fim.hashCode() : 0);
         return hash;*/
        int hash = 0;
        hash += (iid != null ? iid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.iid == null && other.iid != null) || (this.iid != null && !this.iid.equals(other.iid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return reservante.getNome() + (motivo != null ? (" - " + motivo) : "");
    }

    // Métodos da Interface ScheduleEvent
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String string) {
        id = string;
    }

    @Override
    public Object getData() {
        return realizacao;
    }

    @Override
    public String getTitle() {
        if (recursos != null && !recursos.isEmpty()) {
            String title = " " + reservante.getNome() + (motivo == null || motivo.isEmpty() ? "\n :: nao especificado" : " \n :: " + motivo);

            for (Recurso recursoAssociado : recursos) {
                title += "\n" + recursoAssociado.toString();
            }

            return title;
        }
        return " " + reservante.getNome() + (motivo == null || motivo.isEmpty() ? "\n :: nao especificado" : " \n :: " + motivo);
    }

    @Override
    public Date getStartDate() {
        return inicio;
    }

    @Override
    public Date getEndDate() {
        return fim;
    }

    @Override
    public boolean isAllDay() {
        return false;
    }

    @Override
    public String getStyleClass() {
        return null;
    }

    @Override
    public boolean isEditable() {
        return true;
    }
}
