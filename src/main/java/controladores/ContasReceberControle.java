/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import converter.MoneyConverter;
import entidades.BaixaContasReceber;
import entidades.ContasReceber;
import facade.ContasReceberFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class ContasReceberControle implements Serializable {

    private ContasReceber contasReceber;
    @EJB
    private ContasReceberFacade contasReceberFacade;
    private BaixaContasReceber baixaContasReceber;
    private MoneyConverter moneyConverter;
    
    public void addBaixa(){
        baixaContasReceber.setContasReceber(contasReceber);
        contasReceber.getBaixaContasRecebers().add(baixaContasReceber);
        baixaContasReceber = new BaixaContasReceber();
        salvar();
    }

    public MoneyConverter getMoneyConverter() {
        if(moneyConverter == null){
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }

    public BaixaContasReceber getBaixaContasReceber() {
        return baixaContasReceber;
    }

    public void setBaixaContasReceber(BaixaContasReceber baixaContasReceber) {
        this.baixaContasReceber = baixaContasReceber;
    }

    public void novo() {
        contasReceber = new ContasReceber();
    }

    public void salvar() {
        contasReceberFacade.salvar(contasReceber);
    }

    public void baixar(ContasReceber cr) {
        contasReceber = cr;
        baixaContasReceber = new BaixaContasReceber();
    }

    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }

    public List<ContasReceber> getListaContasRecebers() {
        return contasReceberFacade.listaTodos();
    }

}
