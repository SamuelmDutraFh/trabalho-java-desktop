/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufpr.sistemapedidos.model.entity;

import java.util.List;

/**
 *
 * @author samue
 */
public class Pedido {
    
    private int id;
    private String data;
    private Cliente cliente;
    private List<ItemDoPedido> listItens;

    public Pedido() {
    }
    
    

    public Pedido(int id, String data, Cliente cliente, List<ItemDoPedido> listItens) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        this.listItens = listItens;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemDoPedido> getListItens() {
        return listItens;
    }

    public void setListItens(List<ItemDoPedido> listItens) {
        this.listItens = listItens;
    }
    
}
