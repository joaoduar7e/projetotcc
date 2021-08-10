/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.util.JsfUtil;
import converter.ConverterGenerico;
import entidades.*;
import facade.*;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewAccessScoped
public class AjusteEstControle implements Serializable {

    private AjusteEst ajusteEst;
    @Inject
    transient private AjusteEstFacade ajusteEstFacade;
    @Inject
    transient private PecasFacade pecasFacade;
    private ConverterGenerico pecasConverter;
    private Pecas pecas;

    public ConverterGenerico getPecasConverter() {
        if (pecasConverter == null) {
            pecasConverter = new ConverterGenerico(pecasFacade);
        }
        return pecasConverter;
    }

    public void setPecasConverter(ConverterGenerico pecasConverter) {
        this.pecasConverter = pecasConverter;
    }


    public List<Pecas> getListaPecaFiltrando(String parte) {
        return pecasFacade.listaFiltrando(parte, "descricao");
    }

    public List<Pecas> getListaPecas() {
        return pecasFacade.listaTodos();
    }

    public List<AjusteEst> getListaAjusteEstq() {
        return ajusteEstFacade.listaTodos();
    }

    public Double getQuantidadeEstoque() {
        Double quantidadeAtual = 0d;
        try {
            if (ajusteEst.getPecas() != null) {
                quantidadeAtual = ajusteEst.getPecas().getQtdEst();
                return quantidadeAtual;
            } else {
                return null;
            }
        } catch (Exception ex) {
            JsfUtil.adicionarMenssagemErro("");
        }
        return quantidadeAtual;
    }

    public void novo() {
        ajusteEst = new AjusteEst();
    }

    public List<Pecas> getPecasList() {
        List<Pecas> pecas = new ArrayList<>();
        if (ajusteEst.getPecas() != null) {
            pecas.add(ajusteEst.getPecas());
        }
        return pecas;
    }

    public void salvar() throws Exception {
        for (Pecas p : getPecasList()) {
            if (ajusteEst.getTipo().equals("S")) {
                p.setQtdEst(p.getQtdEst() - ajusteEst.getQtdTotal());
            } else  if (ajusteEst.getTipo().equals("E")){
                p.setQtdEst(p.getQtdEst() + ajusteEst.getQtdTotal());
            }
            if (p != null) {
                pecasFacade.pecaMerge(p);
            }
        }
        ajusteEstFacade.salvar(ajusteEst);

    }


    public void editar(AjusteEst ae) {
        ajusteEst = ae;
    }

    public void excluir(AjusteEst ae) {
        ajusteEstFacade.remover(ae);
    }

    public AjusteEst getAjusteEst() {
        return ajusteEst;
    }

    public void setAjusteEst(AjusteEst ajusteEst) {
        this.ajusteEst = ajusteEst;
    }

    public Pecas getPecas() {
        return pecas;
    }

    public void setPecas(Pecas pecas) {
        this.pecas = pecas;
    }
}
