import model.*;
import repository.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class AppMain {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Object usuarioLogado = chamaSelecaoUsuario();
        checaSenhaUsuario(usuarioLogado);
    }

    private static void chamaMenuCadastros() throws SQLException, ClassNotFoundException {
        String[] opcoesMenuCadastro = {"Cliente", "Colaborador", "Projeto","Atividade", "Voltar"};
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Cadastros",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menuCadastro) {
            case 0: //CLIENTE
                Cliente cliente = chamaCadastroCliente();
                if (cliente != null) getPessoaDAO().salvar(cliente);
                chamaMenuCadastros();
                break;
            case 1: //COLABORADOR
                Colaborador colaborador = chamaCadastroColaborador();
                if (colaborador != null) getColaboradorDAO().salvar(colaborador);
                chamaMenuCadastros();
                break;
            case 2: //PROJETO
                 Projeto projeto = chamaCadastroProjeto();
                if (projeto != null) getProjetoDAO().salvar(projeto);
                chamaMenuCadastros();
                break;
            case 3: //Atividade
               Atividade atividade = chamaCadastroAtividade();
                if (atividade != null) getAtividadeDAO().salvar(atividade);
                chamaMenuCadastros();
                break;
            case 4: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static Projeto chamaCadastroProjeto() throws SQLException, ClassNotFoundException {
        Integer opcaoCrud = chamaOpcaoCrud();
        Projeto projeto = null;
        switch (opcaoCrud) {
            case 0: //Inserção
                projeto = cadastroProjeto();
                break;
            case 1: //Alteração
                projeto = selecaoDeProjeto();
                projeto = editaProjeto(projeto);
                break;
            case 2: //Alteração
                projeto = selecaoDeProjeto();
                getProjetoDAO().remover(projeto);
                projeto = null;
                break;
            default: //Exclusão
                chamaMenuCadastros();
                break;
        }
        return projeto;
    }
    private static Projeto cadastroProjeto() throws SQLException, ClassNotFoundException {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do projeto: ");
        int valor = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o valor do projeto:  "));
        String status = JOptionPane.showInputDialog(null, "Digite o status do projeto: ");
        String data_inicio = JOptionPane.showInputDialog(null, "Digite data de inicio: ");
        String entrega_estimada = JOptionPane.showInputDialog(null, "Digite a data estimada: ");
        String progresso = JOptionPane.showInputDialog(null, "Digite o progresso do projeto: ");

        Object[] selectionValuesSeguradora = getPessoaDAO().findPessoasInArray();
        String initialSelectionSeguradora = (String) selectionValuesSeguradora[0];
        Object selectionSeguradora = JOptionPane.showInputDialog(null, "Selecione o Cliente",
                "SGPE", JOptionPane.QUESTION_MESSAGE, null, selectionValuesSeguradora, initialSelectionSeguradora);
        List<Cliente> clientes = getPessoaDAO().buscarPorNome((String) selectionSeguradora);

        Object[] selectionValuesColaborador = getColaboradorDAO().findColaboradorInArray();
        String initialSelectionColaborador = (String) selectionValuesColaborador[0];
        Object selectionColaborador = JOptionPane.showInputDialog(null, "Selecione o Colaborador",
                "SGPE", JOptionPane.QUESTION_MESSAGE, null, selectionValuesColaborador, initialSelectionColaborador);
        List<Colaborador> colaboradores = getColaboradorDAO().buscarPorNome((String) selectionColaborador);

        Projeto projeto = new Projeto();
        projeto.setNome(nome);
        projeto.setValor(valor);
        projeto.setStatus(status);
        projeto.setData_inicio(data_inicio);
        projeto.setEntrega_estimada(entrega_estimada);
        projeto.setProgresso(progresso);
        projeto.setCliente(clientes.get(0));
        projeto.setColaborador(colaboradores.get(0));

        return projeto;
    }
    private static Projeto selecaoDeProjeto() throws SQLException, ClassNotFoundException {
        Object[] selectionValuesCliente = getProjetoDAO().findProjetoInArray();
        String initialSelectionCliente = (String) selectionValuesCliente[0];
        Object selectionCliente = JOptionPane.showInputDialog(null, "Selecione o Projeto",
                "<<SGPE>>>>", JOptionPane.QUESTION_MESSAGE, null, selectionValuesCliente, initialSelectionCliente);
        List<Projeto> projeto = getProjetoDAO().buscarPorNome((String) selectionCliente);
        String[] opcaoCancelar = {"CANCELAR", "CONFIRMAR"};
        int tipoOpcao = JOptionPane.showOptionDialog(null, "Oque deseja?",
                "SAIR ",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcaoCancelar, opcaoCancelar[0]);

        if(tipoOpcao == 0) {
            JOptionPane.showMessageDialog(null, "EXCLUSÃO CANCELADO!");
            chamaMenuCadastros();
        }
        JOptionPane.showMessageDialog(null, "EXCLUSÃO CONCLUIDO!");
        return projeto.get(0);
    }
    private static Projeto editaProjeto(Projeto projetoEdit) throws SQLException, ClassNotFoundException {

        String nome = JOptionPane.showInputDialog(null, "Digite o nome do projeto: ",projetoEdit.getNome());
        int valor = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o valor do projeto:  ",projetoEdit.getValor()));
        String status = JOptionPane.showInputDialog(null, "Digite o status do projeto: ",projetoEdit.getStatus());
        String data_inicio = JOptionPane.showInputDialog(null, "Digite data de inicio: ",projetoEdit.getData_inicio());
        String entrega_estimada = JOptionPane.showInputDialog(null, "Digite a data estimada: ",projetoEdit.getEntrega_estimada());
        String progresso = JOptionPane.showInputDialog(null, "Digite o progresso do projeto: ",projetoEdit.getProgresso());

        Projeto projeto = new Projeto();
        projeto.setNome(nome);
        projeto.setValor(valor);
        projeto.setStatus(status);
        projeto.setData_inicio(data_inicio);
        projeto.setEntrega_estimada(entrega_estimada);
        projeto.setProgresso(progresso);
        projeto.setIU_PROJETO(projetoEdit.getIU_PROJETO());

        String[] opcaoCancelar = {"CANCELAR", "CONFIRMAR"};
        int tipoOpcao = JOptionPane.showOptionDialog(null, "Oque deseja?",
                "SAIR ",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcaoCancelar, opcaoCancelar[0]);

        if(tipoOpcao == 0) {
            JOptionPane.showMessageDialog(null, "UPDADE CANCELADO!");
            chamaMenuCadastros();
        }
        JOptionPane.showMessageDialog(null, "UPDADE CONCLUIDO!");

        return projeto;

    }


    private static Atividade chamaCadastroAtividade() throws SQLException, ClassNotFoundException {
        Integer opcaoCrud = chamaOpcaoCrud();
        Atividade atividade = null;
        switch (opcaoCrud) {
            case 0: //Inserção
                atividade = cadastroAtividade();
                break;
            case 1: //Alteração
                atividade = selecaoDeAtividade();
                atividade = editaAtividade(atividade);
                break;
            case 2: //Exclusão
                atividade = selecaoDeAtividade();
                getAtividadeDAO().remover(atividade);
                atividade = null;
                break;
            default:
                chamaMenuCadastros();
                break;
        }
        return atividade;
    }
    private static Atividade cadastroAtividade() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da atividade ");
        int valor = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o valor da atividade:  "));
        String status = JOptionPane.showInputDialog(null, "Digite o status da atividade: ");
        String data_inicio = JOptionPane.showInputDialog(null, "Digite data de inicio: ");
        String entrega_estimada = JOptionPane.showInputDialog(null, "Digite a data estimada: ");
        String progresso = JOptionPane.showInputDialog(null, "Digite o progresso da atividade: ");

        Object[] selectionValuesProjeto = getProjetoDAO().findProjetoInArray();
        String initialSelectionProjeto = (String) selectionValuesProjeto[0];
        Object selectionProjeto = JOptionPane.showInputDialog(null, "Selecione o Projeto",
                "SGPE", JOptionPane.QUESTION_MESSAGE, null, selectionValuesProjeto, initialSelectionProjeto);
        List<Projeto> projetos = getProjetoDAO().buscarPorNome((String) selectionProjeto);

        Atividade atividade = new Atividade();
        atividade.setNome(nome);
        atividade.setValor(valor);
        atividade.setStatus(status);
        atividade.setData_inicio(data_inicio);
        atividade.setEntrega_estimada(entrega_estimada);
        atividade.setProgresso(progresso);
        atividade.setProjeto(projetos.get(0));
        return atividade;
    }
    private static Atividade selecaoDeAtividade() throws SQLException, ClassNotFoundException {
        Object[] selectionValuesCliente = getAtividadeDAO().findAtividadeInArray();
        String initialSelectionCliente = (String) selectionValuesCliente[0];
        Object selectionCliente = JOptionPane.showInputDialog(null, "Selecione a atividade",
                "<<SGPE>>>>", JOptionPane.QUESTION_MESSAGE, null, selectionValuesCliente, initialSelectionCliente);
        List<Atividade> atividade = getAtividadeDAO().buscarPorNome((String) selectionCliente);
        String[] opcaoCancelar = {"CANCELAR", "CONFIRMAR"};
        int tipoOpcao = JOptionPane.showOptionDialog(null, "Oque deseja?",
                "SAIR ",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcaoCancelar, opcaoCancelar[0]);

        if(tipoOpcao == 0) {
            JOptionPane.showMessageDialog(null, "EXCLUSÃO CANCELADO!");
            chamaMenuCadastros();
        }
        JOptionPane.showMessageDialog(null, "EXCLUSÃO CONCLUIDO!");
        return atividade.get(0);
    }
    private static Atividade editaAtividade(Atividade atividadeEdit) throws SQLException, ClassNotFoundException {

        String nome = JOptionPane.showInputDialog(null, "Digite o nome do atividade: ",atividadeEdit.getNome());
        int valor = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o valor do atividade:  ",atividadeEdit.getValor()));
        String status = JOptionPane.showInputDialog(null, "Digite o status do atividade: ",atividadeEdit.getStatus());
        String data_inicio = JOptionPane.showInputDialog(null, "Digite data de inicio: ",atividadeEdit.getData_inicio());
        String entrega_estimada = JOptionPane.showInputDialog(null, "Digite a data estimada: ",atividadeEdit.getEntrega_estimada());
        String progresso = JOptionPane.showInputDialog(null, "Digite o progresso do atividade: ",atividadeEdit.getProgresso());

        Atividade atividade = new Atividade();
        atividade.setNome(nome);
        atividade.setValor(valor);
        atividade.setStatus(status);
        atividade.setData_inicio(data_inicio);
        atividade.setEntrega_estimada(entrega_estimada);
        atividade.setProgresso(progresso);
        atividade.setIU_ATIVIDADE(atividadeEdit.getIU_ATIVIDADE());

        String[] opcaoCancelar = {"CANCELAR", "CONFIRMAR"};
        int tipoOpcao = JOptionPane.showOptionDialog(null, "Oque deseja?",
                "SAIR ",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcaoCancelar, opcaoCancelar[0]);

        if(tipoOpcao == 0) {
            JOptionPane.showMessageDialog(null, "UPDADE CANCELADO!");
            chamaMenuCadastros();
        }
        JOptionPane.showMessageDialog(null, "UPDADE CONCLUIDO!");

        return atividade;

    }

    private static Cliente chamaCadastroCliente() throws SQLException, ClassNotFoundException {
        Integer opcaoCrud = chamaOpcaoCrud();
        Cliente cliente = null;
        switch (opcaoCrud) {
            case 0: //Inserção
                cliente = cadastroCliente();
                break;
            case 1: //Alteração
                cliente = selecaoDeCliente();
                cliente = editaCliente(cliente);
                break;
            case 2: //Exclusão
                cliente = selecaoDeCliente();
                getPessoaDAO().remover(cliente);
                cliente = null;
            default: //sair
            chamaMenuCadastros();
                break;
        }
        return cliente;
    }


    private static Cliente selecaoDeCliente() throws SQLException, ClassNotFoundException {
        Object[] selectionValuesCliente = getPessoaDAO().findPessoasInArray();
        String initialSelectionCliente = (String) selectionValuesCliente[0];
        Object selectionCliente = JOptionPane.showInputDialog(null, "Selecione o Cliente",
                "<<SGPE>>>>", JOptionPane.QUESTION_MESSAGE, null, selectionValuesCliente, initialSelectionCliente);
        List<Cliente> clientes = getPessoaDAO().buscarPorNome((String) selectionCliente);
        String[] opcaoCancelar = {"CANCELAR", "CONFIRMAR"};
        int tipoOpcao = JOptionPane.showOptionDialog(null, "Oque deseja?",
                "SAIR ",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcaoCancelar, opcaoCancelar[0]);

        if(tipoOpcao == 0) {
            JOptionPane.showMessageDialog(null, "EXCLUSÃO CANCELADO!");
            chamaMenuCadastros();
        }
        JOptionPane.showMessageDialog(null, "EXCLUSÃO CONCLUIDO!");
        return clientes.get(0);
    }


    private static Cliente cadastroCliente() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do cliente: ");
        String cpf = JOptionPane.showInputDialog(null, "Digite o cpf do cliente:  ");
        String telefone = JOptionPane.showInputDialog(null, "Digite o telefone do cliente: ");

        Cliente clientes = new Cliente();
        clientes.setNome(nome);
        clientes.setCpfCnpj(cpf);
        clientes.setTelefone(telefone);
        return clientes;
    }


    private static Cliente editaCliente(Cliente clienteEdit) throws SQLException, ClassNotFoundException {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do cliente ", clienteEdit.getNome());
        String cpf = JOptionPane.showInputDialog(null, "Digite o cpf do cliente ", clienteEdit.getCpfCnpj());
        String telefone = JOptionPane.showInputDialog(null, "Digite o telefone do cliente ", clienteEdit.getTelefone());


        Cliente clientes = new Cliente();
        clientes.setNome(nome);
        clientes.setCpfCnpj(cpf);
        clientes.setTelefone(telefone);
        clientes.setIU_CLIENTE(clienteEdit.getIU_CLIENTE());

        String[] opcaoCancelar = {"CANCELAR", "CONFIRMAR"};
        int tipoOpcao = JOptionPane.showOptionDialog(null, "Oque deseja?",
                "SAIR ",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcaoCancelar, opcaoCancelar[0]);

        if(tipoOpcao == 0) {
            JOptionPane.showMessageDialog(null, "UPDADE CANCELADO!");
            chamaMenuCadastros();
        }
        JOptionPane.showMessageDialog(null, "UPDADE CONCLUIDO!");
        return clientes;
    }

    private static Integer chamaOpcaoCrud() {
        String[] opcao = {"Inserção", "Alteração", "Exclusão","Voltar"};
        int tipoOpcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Operação no cadastro: ",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcao, opcao[0]);
        return tipoOpcao;
    }

    private static Colaborador chamaCadastroColaborador() throws SQLException, ClassNotFoundException {
        Integer opcaoCrud = chamaOpcaoCrud();
        Colaborador colaborador = null;
        switch (opcaoCrud) {
            case 0: //Inserção
                colaborador = cadastroColaborador();
                break;
            case 1: //Alteração
                colaborador = selecaoDeColaborador();
                colaborador = editaColaborador(colaborador);
                break;
            case 2: //Alteração
                colaborador = selecaoDeColaborador();
                getColaboradorDAO().remover(colaborador);
                colaborador = null;
                break;
            default: //Voltar
                chamaMenuCadastros();
                break;
        }
        return colaborador;
    }

    private static Colaborador cadastroColaborador() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do colaborador: ");
        String cpf = JOptionPane.showInputDialog(null, "Digite o cpf do colaborador: ");
        String telefone = JOptionPane.showInputDialog(null, "Digite o telefone do colaborador: ");
        String matricula = JOptionPane.showInputDialog(null, "Digite o Matricula do colaborador: ");


        Colaborador colaborador = new Colaborador();
        colaborador.setNome(nome);
        colaborador.setCpf(cpf);
        colaborador.setTelefone(telefone);
        colaborador.setMatricula(matricula);

        return colaborador;

    }

    private static Colaborador editaColaborador(Colaborador colaboradorEdit) throws SQLException, ClassNotFoundException {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do colaborador: ", colaboradorEdit.getNome());
        String cpf = JOptionPane.showInputDialog(null, "Digite o cpf do colaborador: ", colaboradorEdit.getCpf());
        String telefone = JOptionPane.showInputDialog(null, "Digite o telefone do colaborador: ", colaboradorEdit.getTelefone());
        String matricula = JOptionPane.showInputDialog(null, "Digite o matricula do colaborador: ", colaboradorEdit.getMatricula());

        Colaborador colaboradors = new Colaborador();
        colaboradors.setNome(nome);
        colaboradors.setCpf(cpf);
        colaboradors.setTelefone(telefone);
        colaboradors.setMatricula(matricula);
        colaboradors.setIU_COLABORADOR(colaboradorEdit.getIU_COLABORADOR());


        String[] opcaoCancelar = {"CANCELAR", "CONFIRMAR"};
        int tipoOpcao = JOptionPane.showOptionDialog(null, "Oque deseja?",
                "SAIR ",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcaoCancelar, opcaoCancelar[0]);

        if(tipoOpcao == 0) {
            JOptionPane.showMessageDialog(null, "UPDADE CANCELADO!");
            chamaMenuCadastros();
        }
        JOptionPane.showMessageDialog(null, "UPDADE CONCLUIDO!");

        return colaboradors;
    }

    private static Colaborador selecaoDeColaborador() throws SQLException, ClassNotFoundException {
        Object[] selectionValuesSeguradora = getColaboradorDAO().findColaboradorInArray();
        String initialSelectionSeguradora = (String) selectionValuesSeguradora[0];
        Object selectionSeguradora = JOptionPane.showInputDialog(null, "Selecione o colaborador",
                "<<SGPE>>>>", JOptionPane.QUESTION_MESSAGE, null, selectionValuesSeguradora, initialSelectionSeguradora);
        List<Colaborador> seguradoras = getColaboradorDAO().buscarPorNome((String) selectionSeguradora);
        String[] opcaoCancelar = {"CANCELAR", "CONFIRMAR"};
        int tipoOpcao = JOptionPane.showOptionDialog(null, "Oque deseja?",
                "SAIR ",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcaoCancelar, opcaoCancelar[0]);

        if(tipoOpcao == 0) {
            JOptionPane.showMessageDialog(null, "EXCLUSÃO CANCELADO!");
            chamaMenuCadastros();
        }
        JOptionPane.showMessageDialog(null, "EXCLUSÃO CONCLUIDO!");
        return seguradoras.get(0);
    }



    public static void chamaMenuRelatorio() throws SQLException, ClassNotFoundException {
        String[] opcoesMenuProcesso = {"Cliente","Colaborador" ,"Projeto", "Atividade", "Voltar"};
        int menu_processos = JOptionPane.showOptionDialog(null, "<<SGPE>>\n" +
                        "Escolha uma opção:\n" +
                        "Relatorio de Cadastros ",
                "Menu de RELATORIO",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuProcesso, opcoesMenuProcesso[0]);

        switch (menu_processos) {
            case 0: // CLIENTES
                chamaRelatorioCliente();
                break;
            case 1: //COLABORADORES
                chamaRelatorioColaborador();
                break;
            case 2: //PROJETOS
                chamaRelatorioProjeto();
                break;
            case 3: //ATIVIDADES
                chamaRelatorioAtividade();

                break;
            case 4: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static void chamaRelatorioCliente() throws SQLException, ClassNotFoundException {

        // RELATORIO DE CLIENTES
        ClienteDAO clienteProcurar = new ClienteDAO();
        List<Cliente> clienteSalvas = clienteProcurar.buscarTodos();
        StringBuilder listaPessoas = new StringBuilder("Lista de Clientes");
        for (Cliente clienteSalva : clienteSalvas) {
            listaPessoas.append("\n").append(
                    "» Codigo: " + clienteSalva.getIU_CLIENTE() + "\n" +
                            "» Nome: " + clienteSalva.getNome() + "\n" +
                            "» Telefone: " + clienteSalva.getTelefone() + "\n" +
                            "» CPF: " + clienteSalva.getCpfCnpj() + "\n__________________\n");
        }
        JOptionPane.showMessageDialog(null, listaPessoas.toString());
        chamaMenuRelatorio();
    }
    private static void chamaRelatorioColaborador() throws SQLException, ClassNotFoundException {

        // RELATORIO DE COLABORADORES
        ColaboradorDAO ColaboradorProcurar = new ColaboradorDAO();
        List<Colaborador> colaboradorSalvas = ColaboradorProcurar.buscarTodos();
        StringBuilder listaPessoas = new StringBuilder("Lista de Colaborador");
        for (Colaborador colaborador : colaboradorSalvas) {
            listaPessoas.append("\n").append(
                    "» Codigo: " + colaborador.getIU_COLABORADOR() + "\n" +
                            "» Nome: " + colaborador.getNome() + "\n" +
                            "» Telefone: " + colaborador.getTelefone() + "\n" +
                            "» Matricula: " + colaborador.getMatricula() + "\n" +
                            "» CPF: " + colaborador.getCpf() + "\n__________________\n");
        }
        JOptionPane.showMessageDialog(null, listaPessoas.toString());
        chamaMenuRelatorio();
    }
    private static void chamaRelatorioProjeto() throws SQLException, ClassNotFoundException {

        // RELATORIO DE PROJETO
        ProjetoDAO ProjetoProcurar = new ProjetoDAO();
        List<Projeto> projetoSalvas = ProjetoProcurar.buscarTodos();
        StringBuilder listaPessoas = new StringBuilder("Lista de Projeto");
        for (Projeto projeto : projetoSalvas) {
            listaPessoas.append("\n").append(
                    "» Codigo: " + projeto.getIU_PROJETO() + "\n" +
                            "» Nome: " + projeto.getNome() + "\n" +
                            "» Valor: " + projeto.getValor() + "\n" +
                            "» Status: " + projeto.getStatus() + "\n" +
                            "» Data Inicio " + projeto.getData_inicio() + "\n" +
                            "» Data estimada: " + projeto.getEntrega_estimada() + "\n" +
                            "» Progresso: " + projeto.getProgresso() +"%" + "\n" +
                            "\n__________________\n");
        }
        JOptionPane.showMessageDialog(null, listaPessoas.toString());
        chamaMenuRelatorio();
    }
    private static void chamaRelatorioAtividade() throws SQLException, ClassNotFoundException {

        // RELATORIO DE ATIVIDADE
        AtividadeDAO AtividadeoProcurar = new AtividadeDAO();
        List<Atividade> atividadeSalvas = AtividadeoProcurar.buscarTodos();
        StringBuilder listaPessoas = new StringBuilder("Lista de Atividade");
        for (Atividade atividade: atividadeSalvas) {
            listaPessoas.append("\n").append(
                    "» Codigo: " + atividade.getIU_ATIVIDADE() + "\n" +
                            "» Nome: " + atividade.getNome() + "\n" +
                            "» Valor: " + atividade.getValor() + "\n" +
                            "» Status: " + atividade.getStatus() + "\n" +
                            "» Data Inicio " + atividade.getData_inicio() + "\n" +
                            "» Data estimada: " + atividade.getEntrega_estimada() + "\n" +
                            "» Progresso: " + atividade.getProgresso() +"%" +"\n" +
                            "\n__________________\n");
        }
        JOptionPane.showMessageDialog(null, listaPessoas.toString());
        chamaMenuRelatorio();
    }


    private static void chamaAjuda() throws SQLException, ClassNotFoundException {
        JOptionPane.showMessageDialog(null, "<<SGMP>>\n\n" +
                "» Contato: (48) 4002-8922\n" +
                "» Email: sgpe@suportecliente.com\n" +
                "\n" +
                "Vesão: 1.10\n" +
                "Servidor: 192.168.0.200\n");
        chamaMenuPrincipal();
    }

    private static void chamaMenuPrincipal() throws SQLException, ClassNotFoundException {
        String[] opcoesMenu = {"Cadastros", "Relatorios","Ajuda", "Sair"};
        int opcao = JOptionPane.showOptionDialog(null, "<<SGPE>>\n" +
                        "Escolha uma opção:",
                "Menu Principal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenu, opcoesMenu[0]);
        switch (opcao) {
            case 0: //Cadastros GERAL
                chamaMenuCadastros();
                break;
            case 1: //RELATORIO CADASTRADO
                chamaMenuRelatorio();
                break;
            case 2: // AJUDA
                chamaAjuda();
                break;
            case 3: //SAIR
                chamaSair();
                break;
        }
    }

    private static int chamaSair() throws SQLException, ClassNotFoundException {
        String[] opcaoSair = {"SIM", "NÃO"};
        int tipoOpcao = JOptionPane.showOptionDialog(null, "Deseja realmente sair?",
                "SAIR ",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcaoSair, opcaoSair[0]);

        if(tipoOpcao == 0){
            System.exit(0);
        }else {
            chamaMenuPrincipal();
        }
        return tipoOpcao;
    }


    private static void checaSenhaUsuario(Object usuarioLogado) throws SQLException, ClassNotFoundException {
        String senhaDigitada = JOptionPane.showInputDialog(null,
                "<<SGPE>>\n" +
                        "Informe a senha do usuario\n" +
                        " (" + usuarioLogado + ")");
        Usuario usuarioByLogin = UsuarioDAO.findUsuarioByLogin((String) usuarioLogado);

        if (usuarioByLogin.getSenha().equals(senhaDigitada)) {
            chamaMenuPrincipal();
        } else {
            JOptionPane.showMessageDialog(null, "<<SGPE>>\n" +
                    "Senha incorreta!");
            checaSenhaUsuario(usuarioLogado);
        }
    }


    private static Object chamaSelecaoUsuario() {
        Object[] selectionValues = UsuarioDAO.findUsuariosSistemaInArray();
        String initialSelection = (String) selectionValues[0];
        Object selection = JOptionPane.showInputDialog(null, "<<SGPE>>\n" +
                        "Selecione o Usuario desejado: ",
                "SGPE", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        return selection;
    }

    public static ClienteDAO getPessoaDAO() {
        ClienteDAO pessoaDAO = new ClienteDAO();
        return pessoaDAO;
    }

    public static ColaboradorDAO getColaboradorDAO() {
        ColaboradorDAO colaboradorDao = new ColaboradorDAO();
        return colaboradorDao;
    }

    public static ProjetoDAO getProjetoDAO() {
        ProjetoDAO getProjetoDAO = new ProjetoDAO();
        return getProjetoDAO;
    }

    public static AtividadeDAO getAtividadeDAO() {
        AtividadeDAO getAtividadeDAO = new AtividadeDAO();
        return getAtividadeDAO;
    }

}