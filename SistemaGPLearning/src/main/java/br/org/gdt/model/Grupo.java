package br.org.gdt.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Grupo implements Serializable {

    @Id
    private int idGrupo;
    private String nomeGrupo;
    private String nomeLabel;

    public Grupo() {
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNomeLabel() {
        return nomeLabel;
    }

    public void setNomeLabel(String nomeLabel) {
        this.nomeLabel = nomeLabel;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.idGrupo;
        hash = 73 * hash + Objects.hashCode(this.nomeGrupo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Grupo other = (Grupo) obj;
        if (this.idGrupo != other.idGrupo) {
            return false;
        }
        if (!Objects.equals(this.nomeGrupo, other.nomeGrupo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomeLabel;
    }

}
