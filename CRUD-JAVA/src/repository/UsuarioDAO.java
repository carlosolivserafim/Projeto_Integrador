package repository;

import model.Usuario;

import java.util.ArrayList;
import java.util.List;

public final class UsuarioDAO {

    public static List<Usuario> findUsuariosSistema() {
        List<Usuario> usuarios = new ArrayList<>();

        Usuario erick = new Usuario();
        erick.setLogin("OPS$COM10001");
        erick.setSenha("admin");

        Usuario carlos = new Usuario();
        carlos.setLogin("OPS$COM10002");
        carlos.setSenha("admin");

        Usuario igor = new Usuario();
        igor.setLogin("OPS$COM10003");
        igor.setSenha("admin");

        Usuario allysson = new Usuario();
        allysson.setLogin("OPS$COM10004");
        allysson.setSenha("admin");


        usuarios.add(erick);
        usuarios.add(carlos);
        usuarios.add(igor);
        usuarios.add(allysson);

        return usuarios;
    }

    public static Object[] findUsuariosSistemaInArray() {
        List<Usuario> usuarios = findUsuariosSistema();
        List<String> usuariosLogins = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            usuariosLogins.add(usuario.getLogin());
        }

        return usuariosLogins.toArray();
    }

    public static Usuario findUsuarioByLogin(String login) {
        List<Usuario> usuariosSistema = findUsuariosSistema();

        for (Usuario usuario : usuariosSistema) {
            if (login.equals(login)) {
                return usuario;
            }
        }
        return null;
    }

}
