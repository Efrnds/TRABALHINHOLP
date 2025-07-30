/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.view;


/**
 *
 * @author Aluno
 */
public class ConsoleView {
    
    public static void main(String[] args) {
        /*
        UsuarioDAO daoUsuario = new UsuarioDAO();
        List<Usuario> usuarios = daoUsuario.findAll();
        
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("");
        }
        */
        /*
        // Teste do delete
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o Id do usuário: ");
        String idString = scanner.nextLine();
        int id = Integer.valueOf(idString);

        UsuarioDAO daoUsuario = new UsuarioDAO();
        if(daoUsuario.delete(id)) {
            System.out.println("Apagou um registro.");
        } else {
            System.out.println("Não havia exemplo para apagar.");
        }
        */
        
        /*
        // Teste do update
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o Id do usuário: ");
        String idString = scanner.nextLine();
        int id = Integer.valueOf(idString);

        UsuarioDAO daoUsuario = new UsuarioDAO();
        Usuario u3 = daoUsuario.retrieve(id);
        
        if (u3 != null) {
                System.out.println("ID: " + u3.getId());
                System.out.println("Nome: " + u3.getNome());
                System.out.println("Email: " + u3.getEmail());
        } else {
                System.out.println("Usuário não cadastrado!");
        }
        
        System.out.print("Digite o novo email: ");
        String email = scanner.nextLine();
        u3.setEmail(email);
        
        daoUsuario.update(u3);
        */
        
        
        /* Teste do retreive
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o Id do usuário: ");
            String idString = scanner.nextLine();
            int id = Integer.valueOf(idString);

            UsuarioDAO daoUsuario = new UsuarioDAO();
            Usuario u2 = daoUsuario.retrieve(id);

            if (u2 != null) {
                System.out.println("ID: " + u2.getId());
                System.out.println("Nome: " + u2.getNome());
                System.out.println("Email: " + u2.getEmail());
            } else {
                System.out.println("Usuário não cadastrado!");
            }
        }
        */
        
        /* // Teste do Create
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        UsuarioDAO daoUsuario = new UsuarioDAO();
        
        Usuario u1 = new Usuario(nome, email);        
        daoUsuario.create(u1);
        
        System.out.println("Id gerado do Usuário: " + u1.getId());
        */
    }
}
