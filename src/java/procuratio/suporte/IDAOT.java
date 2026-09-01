/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procuratio.suporte;

/**
 *
 * @author vitor
 */
public interface IDAOT <T> {

    public boolean salvar(T o);

    public boolean excluir(int id);

    public T consultar(int id);
    
    public Integer consultarUltimoId();
}
