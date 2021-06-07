package controladores;

import entidades.Maquinario;
import facade.MaquinarioFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class MaquinarioControle implements Serializable {
    private Maquinario maquinario= new Maquinario();
    @Inject
    transient private MaquinarioFacade maquinarioFacade;

    public void novo(){
        maquinario = new Maquinario();
    }

    public List<Maquinario> getListaMaquinario(){
        return maquinarioFacade.listaTodos();
    }

    public Maquinario getMaquinario() {
        return maquinario;
    }

    public void setMaquinario(Maquinario maquinario) {
        this.maquinario = maquinario;
    }

    public void editar(Maquinario maqui){
        maquinario = maqui;
    }

    public void excluir(Maquinario maqui){
        maquinarioFacade.remover(maqui);
    }


    public void salvar(){
        maquinarioFacade.salvar(maquinario);
        maquinario = new Maquinario();
    }
}
