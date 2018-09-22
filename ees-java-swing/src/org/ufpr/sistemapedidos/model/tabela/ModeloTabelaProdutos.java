/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufpr.sistemapedidos.model.tabela;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.ufpr.sistemapedidos.model.entity.Produto;

/**
 *
 * @author samuel
 */
public class ModeloTabelaProdutos extends AbstractTableModel{
    
    private String[] colunas=new String[]{"id","descricao"};
    private List<Produto> lista=new ArrayList();

    public ModeloTabelaProdutos() {
    }
    
    public ModeloTabelaProdutos(List<Produto> lista) {
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
        Produto produto = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return produto.getId();//if column 0 (code)
            case 1: return produto.getDescricao();//if column 1 (name)
            default : return null;
        }
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        Produto produto = lista.get(row);
        switch (col) {
            case 0:
                produto.setId((int) value); //if column 0 (code)
                break;
            case 1:
                produto.setDescricao((String) value);
                break;
            default:
        }
        this.fireTableCellUpdated(row, col);
    }

    public boolean removeProduto(Produto produto) {
        int linha = this.lista.indexOf(produto);
        boolean result = this.lista.remove(produto);
        this.fireTableRowsDeleted(linha,linha);//update JTable
        return result;
    }

    public void adicionaProduto(Produto produto) {
        this.lista.add(produto);
        //this.fireTableDataChanged();
        this.fireTableRowsInserted(lista.size()-1,lista.size()-1);//update JTable
    }

    public void setListaProdutos(List<Produto> listProdutos) {
        this.lista = listProdutos;
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

    public Produto getProduto(int linha){
        return lista.get(linha);
    }
    
}
