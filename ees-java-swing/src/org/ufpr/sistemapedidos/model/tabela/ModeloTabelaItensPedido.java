/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufpr.sistemapedidos.model.tabela;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.ufpr.sistemapedidos.model.entity.ItemDoPedido;
import org.ufpr.sistemapedidos.model.entity.Produto;

/**
 *
 * @author samue
 */
public class ModeloTabelaItensPedido extends AbstractTableModel{
    
    private String[] colunas=new String[]{"Produto","Quantidade"};
    private List<ItemDoPedido> lista=new ArrayList();

    public ModeloTabelaItensPedido() {
    }
    
    public ModeloTabelaItensPedido(List<ItemDoPedido> lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return this.lista.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }
    
    @Override
    public String getColumnName(int index) {
        return this.colunas[index];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
        //if(column==0)
            //return false;
        //return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ItemDoPedido item = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return item.getProduto();//if column 0 (code)
            case 1: return item.getQuantidade();//if column 1 (name)
            default : return null;
        }
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        ItemDoPedido item = lista.get(row);
        switch (col) {
            case 0:
                item.setProduto((Produto) value); //if column 0 (code)
                break;
            case 1:
                item.setQuantidade((int) value);
                break;
            default:
        }
        this.fireTableCellUpdated(row, col);
    }

    public boolean removeItemDoPedido(ItemDoPedido item) {
        int linha = this.lista.indexOf(item);
        boolean result = this.lista.remove(item);
        this.fireTableRowsDeleted(linha,linha);//update JTable
        return result;
    }

    public void adicionaItemDoPedido(ItemDoPedido item) {
        this.lista.add(item);
        //this.fireTableDataChanged();
        this.fireTableRowsInserted(lista.size()-1,lista.size()-1);//update JTable
    }

    public void setListaProdutos(List<ItemDoPedido> listItens) {
        this.lista = listItens;
        this.fireTableDataChanged();
        //this.fireTableRowsInserted(0,contatos.size()-1);//update JTable
    }

    public void limpaTabela() {
        int indice = lista.size()-1;
        if(indice<0)
            indice=0;
        this.lista = new ArrayList();
        this.fireTableRowsDeleted(0,indice);//update JTable
    }

    public ItemDoPedido getItemDoPedido(int linha){
        return lista.get(linha);
    }
    
}
