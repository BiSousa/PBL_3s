/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

/**
 *
 * @author fernando.cruz
 */
public class Usuario {
    private String Email;
    private String Nome;
    private String Senha;
    private int UsuarioID;
    
    public void setEmail(String email){
        Email = email;
    }
    public void setNome(String nome){
        Nome = nome;
    }
    
    public void setSenha(String senha){
        Senha = senha;
    }
    
    public void setUsuarioID(int usuarioID){
        UsuarioID = usuarioID;
    }
    public String getEmail(){
        return Email;
    }
    public String getNome(){
        return Nome;
    }
    
    public String getSenha(){
        return Senha;
    }
    
    public int getUsuarioID(){
        return UsuarioID;
    }
}
