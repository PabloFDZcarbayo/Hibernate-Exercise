package DAO;

import Entities.Usuarios;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsuariosDAO {

    private static Session session = null;

    public UsuariosDAO() {
        session = HibernateUtil.getSession();
    }

    public static void crearUsuario(Usuarios usuario) {
        try {
            Transaction transaction = session.beginTransaction();
            session.persist(usuario);
            transaction.commit();
            System.out.println("Usuario insertado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void actualizarNombreUsuario(int idUsuario,String nombreUsuario) {
        try {
            Usuarios usuario = session.get(Usuarios.class, idUsuario);
            if (usuario != null) {
            Transaction transaction = session.beginTransaction();
                usuario.setNombre(nombreUsuario);
                session.update(usuario);
                transaction.commit();
                System.out.println("Usuario actualizado correctamente.");
            }else {
                System.out.println("El usuario no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void eliminarUsuario(int idUsuario) {
        try {
            Usuarios usuario = session.get(Usuarios.class, idUsuario);
            if (usuario != null) {
            Transaction transaction = session.beginTransaction();
            session.delete(usuario);
            transaction.commit();
            System.out.println("Usuario eliminado correctamente.");
            }else {
                System.out.println("El usuario no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  public static void BuscarUsuario(int idUsuario) {
        try {
            Usuarios usuario = session.get(Usuarios.class, idUsuario);
            if (usuario != null) {
                System.out.println("Usuario encontrado: ");
                System.out.println("-->");
                System.out.println("ID: " + usuario.getId());
                System.out.println("Nombre: " + usuario.getNombre());
                System.out.println("Entradas: " + usuario.getEntradas());
                System.out.println("--------");
            }else {
                System.out.println("El usuario no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public static void gastoMedioPorUsuario() {
            try {
                List<Object[]> resultados = session.createQuery(
                                "SELECT t.usuario.id, AVG(t.precioEntrada) " +  //Selecciona el ID del usuario y el precio medio de las entradas
                                        "FROM Tikects t " + //Desde la tabla tikects
                                        "GROUP BY t.usuario.id", Object[].class) //Agrupa por el ID del usuario
                        .getResultList(); //Devuelve una lista de resultados

                for (Object[] fila : resultados) { //Itera sobre la lista de resultados
                    Integer usuarioId = (Integer) fila[0]; //Obtiene el ID del usuario
                    Double gastoMedio = (Double) fila[1]; //Obtiene el gasto medio

                    System.out.println("Usuario ID: " + usuarioId + " - Gasto medio: " + gastoMedio);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

